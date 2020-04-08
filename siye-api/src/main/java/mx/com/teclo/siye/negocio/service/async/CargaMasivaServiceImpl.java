package mx.com.teclo.siye.negocio.service.async;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.hibernate.dao.async.LayoutDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.async.TipoLayoutDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.LoteOrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.StSeguimientoDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.async.TipoLayoutDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.LoteOrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.StSeguimientoDTO;
import mx.com.teclo.siye.persistencia.vo.async.ConfigCargaMasivaVO;
import mx.com.teclo.siye.persistencia.vo.async.TipoLayoutVO;
import mx.com.teclo.siye.util.enumerados.ArchivoSeguimientoEnum;
import mx.com.teclo.siye.util.enumerados.SeccionLayoutEnum;

@Service
public class CargaMasivaServiceImpl implements CargaMasivaService {
	private static final String STRING_PATTERN_INSERT = "INSERT INTO ";
	private static final String MSG_ERROR_QUERY_INVALIDO = "El comando es inseguro para su ejecucion";
	private static final String MSG_ERROR_INSERCION_INCOMPLETA = "Fallaron inserts de la linea {0}";

	@Autowired
	private LayoutDAO layoutDAO;
	@Autowired
	private TipoLayoutDAO tipoLayoutDAO;
	@Autowired
	private LoteOrdenServicioDAO loteDAO;
	@Autowired
	private StSeguimientoDAO seguimientoDAO;

	@Override
	public void procesarLineas(ConfigCargaMasivaVO config) {
		try {
			File file = new File("C:\\file\\ort" + config.getConfigLote().getNbLoteOds());
			final int primeraLinea = BigDecimal.ONE.intValue();
			final int ultimaLinea = countLines(file);

			File fileResultado = new File("C:\\file\\ods" + config.getConfigLote().getNbLoteOds());
			FileReader fr = new FileReader(file);
			FileWriter fw = new FileWriter(fileResultado);

			LineNumberReader reader = new LineNumberReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);

			String linea;

			boolean isConHeader = isConSeccionTipo(config, SeccionLayoutEnum.HEADER);
			boolean isConPie = isConSeccionTipo(config, SeccionLayoutEnum.FOOTER);

			while ((linea = reader.readLine()) != null) {
				if ((isConHeader && reader.getLineNumber() == primeraLinea)
						|| (isConPie && reader.getLineNumber() == ultimaLinea)) {
					bw.write("ID," + linea);
					bw.newLine();
				} else {
					Long idOrdenServicio;
					try {
						idOrdenServicio = insertarEnTablas(config, linea);
					} catch (HibernateException | BusinessException e) {
						idOrdenServicio = BigDecimal.ZERO.longValue();
					}
					bw.write(idOrdenServicio.toString() + "," + linea);
					bw.newLine();
				}

			}
			bw.flush();
			fr.close();
			fw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {

		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long insertarEnTablas(ConfigCargaMasivaVO config, String linea)
			throws BusinessException, HibernateException {
		final int ultimaInsercion = config.getConfigInsercion().size();
		List<Long> arregloIDs = new ArrayList<Long>();

		for (int i = 0; i < ultimaInsercion; i++) {
			String nbTabla = config.getConfigInsercion().get(i);
			String molde = (config.getConfigMoldesSQL().get(nbTabla)).getQuerySQL();
			try {
				if (i < (ultimaInsercion - 1)) {
					String sqlFormateado = formatearInsert(molde, linea, LayoutServiceImpl.CARACTER_COMA);
					Long nvoID = layoutDAO.ejecutarQueryConcatenado(sqlFormateado);
					arregloIDs.add(nvoID);
				} else {
					System.out.println(formatearInsert(molde, linea, LayoutServiceImpl.CARACTER_COMA));
				}
			} catch (Exception e) {
				arregloIDs.add(BigDecimal.ZERO.longValue());
			}
		}
		if (arregloIDs.size() != ultimaInsercion) {
			throw new BusinessException("");
		}
		return arregloIDs.get(arregloIDs.size() - 1);
	}

	private String formatearInsert(String molde, String linea, String separador) {
		String nvaLineaValores = separador + separador;
		String[] arrayValores = nvaLineaValores.split(separador);
		return MessageFormat.format(molde, arrayValores);
	}

	public static int countLines(File aFile) throws IOException {
		LineNumberReader reader = null;
		try {
			reader = new LineNumberReader(new FileReader(aFile));
			while ((reader.readLine()) != null)
				;
			return reader.getLineNumber();
		} catch (Exception ex) {
			return -1;
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	private boolean isConSeccionTipo(ConfigCargaMasivaVO config, SeccionLayoutEnum seccionTipo) {
		return config.getConfigSecciones().get(seccionTipo.getCdIndicadorReg()).size() != BigDecimal.ZERO.intValue();

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void iniciarCargaMasiva(Long idArchivoLote) throws BusinessException {
		TipoLayoutVO layoutVigente = tipoLayoutDAO.getLayoutVigente();
		if(layoutVigente == null) {
			throw new BusinessException(LayoutServiceImpl.MSG_LAYOUT_VIGENTE_NULO);
		}
		TipoLayoutDTO layoutVigenteDTO = tipoLayoutDAO.findOne(layoutVigente.getIdTipoLayout());
		StSeguimientoDTO seguimientoDTO = seguimientoDAO.findOne(ArchivoSeguimientoEnum.CARGANDO.getIdArchivoSeg());
		LoteOrdenServicioDTO loteDTO = loteDAO.findOne(idArchivoLote);
		loteDTO.setIdTipoLayout(layoutVigenteDTO);
		loteDTO.setIdStSeguimiento(seguimientoDTO);
		loteDTO.setFhModificacion(new Date());
		loteDAO.update(loteDTO);				
	}

}
