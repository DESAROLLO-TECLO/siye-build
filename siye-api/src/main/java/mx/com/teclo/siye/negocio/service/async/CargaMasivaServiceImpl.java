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
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.jboss.logging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import mx.com.teclo.siye.persistencia.vo.async.InsercionTablaVO;
import mx.com.teclo.siye.persistencia.vo.async.TipoLayoutVO;
import mx.com.teclo.siye.util.enumerados.ArchivoSeguimientoEnum;
import mx.com.teclo.siye.util.enumerados.SeccionLayoutEnum;
import mx.com.teclo.siye.util.enumerados.TipoDirectorioStorageEnum;

@Service
public class CargaMasivaServiceImpl implements CargaMasivaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CargaMasivaServiceImpl.class);
	private static final String STRING_PATTERN_INSERT = "INSERT INTO ";
	private static final String PIPE = "|";
	private static final String MSG_ERROR_QUERY_INVALIDO = "El comando es inseguro para su ejecucion";
	private static final String MSG_ERROR_INSERCION_INCOMPLETA = "Fallaron inserts de la linea {0}";
	private static final String MSG_INICIANDO_CARGA_MASIVA = "Iniciando carga masiva del arhivo {0}";
	private static final String MSG_LEYENDO_ARCHIVO_LOTE = "Leyendo el archivo {0} para procesar lineas";
	private static final String MSG_ACCEDIENDO_A_LA_RUTA = "Accediendo a la ruta de {0} {1} del archivo ID {2}";
	private static final String MSG_INICIO_PROCESAMIENTO_LINEAS_FALLIDO = "No fue posible iniciar el proceso del archivo {0}";

	@Autowired
	private LayoutDAO layoutDAO;
	@Autowired
	private TipoLayoutDAO tipoLayoutDAO;
	@Autowired
	private LoteOrdenServicioDAO loteDAO;
	@Autowired
	private StSeguimientoDAO seguimientoDAO;
	@Autowired
	private LayoutService layoutService;
	@Autowired
	private FileStorageService fileStorageService;

	@Override
	@Async
	@Transactional
	public void procesarLineas(ConfigCargaMasivaVO config) throws BusinessException {
		LOGGER.info(MessageFormat.format(MSG_LEYENDO_ARCHIVO_LOTE, config.getConfigLote().getIdLoteOds()));

		String rutaRecibido = config.getConfigLote().getNbLoteOds();
		String rutaEntregado = rutaRecibido.replace(TipoDirectorioStorageEnum.INPUT.getCdTipo(),
				TipoDirectorioStorageEnum.OUTPUT.getCdTipo());

		LOGGER.info(MessageFormat.format(MSG_ACCEDIENDO_A_LA_RUTA, TipoDirectorioStorageEnum.INPUT.getCdTipo(), rutaRecibido, config.getConfigLote().getIdLoteOds()));
		LOGGER.info(MessageFormat.format(MSG_ACCEDIENDO_A_LA_RUTA, TipoDirectorioStorageEnum.OUTPUT.getCdTipo(), rutaEntregado, config.getConfigLote().getIdLoteOds()));
		try {
			File file = new File(rutaRecibido);
			File fileResultado = new File(rutaEntregado);
			FileReader fr = new FileReader(file);
			FileWriter fw = new FileWriter(fileResultado);

			LineNumberReader reader = new LineNumberReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);

			String linea;
			//
			while ((linea = reader.readLine()) != null) {

				for (Map.Entry<String, InsercionTablaVO> entry : config.getConfigMoldesSQL().entrySet()) {
					InsercionTablaVO insercion = entry.getValue();
		
						String sqlFormateado = formatearInsert(insercion.getQuerySQL(), linea,
								LayoutServiceImpl.CARACTER_COMA);
						LOGGER.info(sqlFormateado);					
					
				}
				
				bw.write(linea);
				bw.newLine();

			}
			bw.flush();
			fr.close();
			fw.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
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
					LOGGER.info(formatearInsert(molde, linea, LayoutServiceImpl.CARACTER_COMA));
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
		String nvaLineaValores = separador + linea;
		String[] arrayValores = nvaLineaValores.split(separador);
		String query = "";
		try {
			query = MessageFormat.format(molde, arrayValores);
		} catch (Exception e) {
			query = "ERROR" + molde;
		}
		if(StringUtils.isBlank(query)) {
			query = "ERROR" + molde;
		}
		return query;

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
	@Async
	@Transactional
	public void iniciarCargaMasiva(Long idArchivoLote) throws BusinessException {
		LOGGER.info(MessageFormat.format(MSG_INICIANDO_CARGA_MASIVA, idArchivoLote));
		LoteOrdenServicioDTO loteDTO = loteDAO.findOne(idArchivoLote);
		Long lineas = BigDecimal.ZERO.longValue();
		try {
			lineas = (long) countLines(new File(loteDTO.getNbLoteOds()));
		} catch (IOException e) {
			lineas--;
		}
		TipoLayoutVO layoutVigente = tipoLayoutDAO.getLayoutVigente();
		if (layoutVigente == null) {
			throw new BusinessException(LayoutServiceImpl.MSG_LAYOUT_VIGENTE_NULO);
		}

		TipoLayoutDTO layoutVigenteDTO = tipoLayoutDAO.findOne(layoutVigente.getIdTipoLayout());
		StSeguimientoDTO seguimientoDTO = seguimientoDAO.findOne(ArchivoSeguimientoEnum.CARGANDO.getIdArchivoSeg());

		loteDTO.setIdTipoLayout(layoutVigenteDTO);
		loteDTO.setIdStSeguimiento(seguimientoDTO);
		loteDTO.setFhModificacion(new Date());
		loteDTO.setNuOdsReportados(lineas);
		loteDAO.update(loteDTO);
		loteDAO.flush();
	}

}
