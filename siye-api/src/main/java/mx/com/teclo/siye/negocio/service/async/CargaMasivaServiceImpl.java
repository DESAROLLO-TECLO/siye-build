package mx.com.teclo.siye.negocio.service.async;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import mx.com.teclo.siye.persistencia.vo.async.ColumnaArchivoVO;
import mx.com.teclo.siye.persistencia.vo.async.ColumnaVO;
import mx.com.teclo.siye.persistencia.vo.async.ConfigCargaMasivaVO;
import mx.com.teclo.siye.persistencia.vo.async.InsercionTablaVO;
import mx.com.teclo.siye.persistencia.vo.async.TipoLayoutVO;
import mx.com.teclo.siye.util.enumerados.ArchivoSeguimientoEnum;
import mx.com.teclo.siye.util.enumerados.SeccionLayoutEnum;
import mx.com.teclo.siye.util.enumerados.TipoDirectorioStorageEnum;
import mx.com.tecloreporte.jar.utils.comun.ConnectionUtilBd;

@Service
public class CargaMasivaServiceImpl implements CargaMasivaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CargaMasivaServiceImpl.class);
	private static final String STRING_PATTERN_INSERT = "INSERT INTO ";
	private static final String PIPE = "|";
	private static final String DOS_PUNTOS = ":";
	private static final String CARACTER_COMA = ",";
	private static final String MSG_ERROR_QUERY_INVALIDO = "El comando es inseguro para su ejecucion";
	private static final String MSG_ERROR_INSERCION_INCOMPLETA = "Fallaron inserts de la linea {0}";
	private static final String MSG_CONTANDO_LINEAS_CARGA_MASIVA = "Inspeccionando archivo recibido con ID {0}";
	private static final String MSG_LEYENDO_ARCHIVO_LOTE = "Leyendo el archivo {0} para procesar lineas";
	private static final String MSG_ACCEDIENDO_A_LA_RUTA = "Accediendo a la ruta de {0} {1} del archivo ID {2}";
	private static final String MSG_ERROR_LECTURA_ARCHIVO = "No fue posible iniciar el proceso del archivo {0}";
	private static final String MSG_ERROR_CONEXION_DB = "No fue posible establecer conexion con la bd para cargar el archivo {0}";
	private static final String MSG_ERROR_SQL = "Error al ejecutar comando SQL {0}";
	private static final String MSG_COLS_INCORRECTAS = "Columnas desajustadas (esperadas {0}, recibidas {1})";

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

	@Autowired
	private ConnectionUtilBd conection;

	@Override
	public void procesarLineas(ConfigCargaMasivaVO config) throws BusinessException {
		Connection con = null;
		try {
			LOGGER.info(MessageFormat.format(MSG_LEYENDO_ARCHIVO_LOTE, config.getConfigLote().getIdLoteOds()));

			String rutaRecibido = config.getConfigLote().getNbLoteOds();
			String rutaEntregado = rutaRecibido.replace(TipoDirectorioStorageEnum.INPUT.getCdTipo(),
					TipoDirectorioStorageEnum.OUTPUT.getCdTipo());

			LOGGER.info(MessageFormat.format(MSG_ACCEDIENDO_A_LA_RUTA, TipoDirectorioStorageEnum.INPUT.getCdTipo(),
					rutaRecibido, config.getConfigLote().getIdLoteOds()));
			LOGGER.info(MessageFormat.format(MSG_ACCEDIENDO_A_LA_RUTA, TipoDirectorioStorageEnum.OUTPUT.getCdTipo(),
					rutaEntregado, config.getConfigLote().getIdLoteOds()));

			con = conection.conectarBD();
			con.setAutoCommit(false);

			File file = new File(rutaRecibido);
			File fileResultado = new File(rutaEntregado);
			FileReader fr = new FileReader(file);
			FileWriter fw = new FileWriter(fileResultado);

			LineNumberReader reader = new LineNumberReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);

			String linea;
			String nbUltimaTbl = extraerNbUltimaTablaAInsertar(config);
			LOGGER.info(nbUltimaTbl);
			boolean isConHeader = isConSeccionTipo(config, SeccionLayoutEnum.HEADER);
			boolean isConFooter = isConSeccionTipo(config, SeccionLayoutEnum.FOOTER);

			while ((linea = reader.readLine()) != null) {
		
				if (isConHeader && reader.getLineNumber() == 1) {
					bw.write("Resultado," + linea);
					bw.newLine();
					continue;
				}
				if (isConFooter && reader.getLineNumber() == config.getConfigLote().getNuOdsReportados()) {
					bw.write("," + linea);
					bw.newLine();
					continue;
				}
				try {
					validarLinea(config, linea, CARACTER_COMA);
					bw.write("0," + linea);
					bw.newLine();
				} catch (BusinessException e) {
					bw.write("," + e.getMessage());
					bw.newLine();
					continue;
				}

			}

			bw.flush();
			fr.close();
			fw.close();
		} catch (NamingException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			throw new BusinessException(
					MessageFormat.format(MSG_ERROR_LECTURA_ARCHIVO, config.getConfigLote().getIdLoteOds()));
		} catch (SQLException e) {
			throw new BusinessException(MessageFormat.format(MSG_ERROR_SQL, e.getMessage()));
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private String formatearSQL(String molde, String linea, String separador) {
		String nvaLineaValores = separador + linea;
		String[] arrayValores = nvaLineaValores.split(separador);
		String query = "";
		try {
			query = MessageFormat.format(molde, arrayValores);
		} catch (Exception e) {
			query = molde;
		}
		if (StringUtils.isBlank(query)) {
			query = molde;
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
		List<ColumnaVO> cols = config.getConfigSecciones().get(seccionTipo.getCdIndicadorReg());
		return (cols != null && !cols.isEmpty());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void iniciarCargaMasiva(Long idArchivoLote) throws BusinessException {
		LOGGER.info(MessageFormat.format(MSG_CONTANDO_LINEAS_CARGA_MASIVA, idArchivoLote));
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

	@Override
	@Transactional
	public List<Long> ejecutarSQL(List<String> queries, boolean isModoSelect)
			throws BusinessException, HibernateException {

		return null;

	}

	private HashMap<String, InsercionTablaVO> generarQueriesSQL(ConfigCargaMasivaVO config, String linea) {
		HashMap<String, InsercionTablaVO> map = new LinkedHashMap<>();
		for (String nbTabla : config.getConfigInsercion()) {
			InsercionTablaVO insercionTablaVO = config.getConfigMoldesSQL().get(nbTabla.trim());
			String selectFormateado = formatearSQL(insercionTablaVO.getSelectSQL(), linea,
					LayoutServiceImpl.CARACTER_COMA);
			String insertFormateado = formatearSQL(insercionTablaVO.getInsertSQL(), linea,
					LayoutServiceImpl.CARACTER_COMA);

			map.put(nbTabla.trim(), new InsercionTablaVO(insertFormateado, selectFormateado));
		}
		return map;
	}

	private String extraerNbUltimaTablaAInsertar(ConfigCargaMasivaVO config) {
		int totalTablas = config.getConfigInsercion().size();
		if (totalTablas == 1) {
			return config.getConfigInsercion().get(0);
		}
		return config.getConfigInsercion().get(totalTablas - 1);

	}

	public Map<Object, Object> resultSetToArrayMap(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		LinkedHashMap<Object, Object> row = null;
		while (rs.next()) {
			row = new LinkedHashMap<Object, Object>();
			for (int i = 1; i <= columns; ++i) {
				row.put("key", md.getColumnName(i));
				row.put("value", rs.getObject(i));
			}
		}
		return row;
	}

	private void validarLinea(ConfigCargaMasivaVO config, String linea, String separador) throws BusinessException {

		List<String> arrayValores = Arrays.asList(linea.split(separador));
		int colsEsperadas = config.getTotalColsEsperadas();
		int colsRecibidas = arrayValores.size();

		StringBuilder sbErrores = new StringBuilder();
		boolean isLineaValida = true;

		for (ColumnaArchivoVO col : config.getColumnasEnArchivo()) {
			String valor = arrayValores.get(col.getNuOrden());
			String msgErrorCol = validarColumna(col, valor);
			if (StringUtils.isNotBlank(msgErrorCol)) {
				String resultado = StringUtils.isBlank(msgErrorCol) ? "OK" : "ERROR: " + msgErrorCol;
				sbErrores.append(",").append(resultado);
				isLineaValida = false;
			}
		}
		if (colsEsperadas != colsRecibidas) {
			sbErrores.append(",").append(MessageFormat.format(MSG_COLS_INCORRECTAS, colsEsperadas, colsRecibidas));
			isLineaValida = false;
		}

		if (!isLineaValida) {
			throw new BusinessException(sbErrores.toString().replaceFirst(",", ""));
		}

	}

	/**
	 * Valida si falta un dato, si excede tama&ntilde;o o es formato inv&aacute;lido
	 * 
	 * @param col
	 * @param valor
	 * @return
	 */
	private String validarColumna(ColumnaArchivoVO col, String valor) {
		StringBuilder sbErrCol = new StringBuilder();
		boolean continuar = true;
		if (col.getIsRequerido() && StringUtils.isBlank(valor)) {
			sbErrCol.append("Falta dato. ");
			continuar = false;
		}
		if (continuar && valor.length() > col.getLongMax()) {
			sbErrCol.append("Tamaño excede. ");
			continuar = false;

		}
		if (continuar && StringUtils.isNotBlank(col.getTipoDato())) {
			try {
				validarTipoObjeto(col.getTipoDato(), valor, col.getTxValorDefecto());
			} catch (BusinessException e) {
				sbErrCol.append("Formato inv\u00E1lido. ");
			}
		}
		return sbErrCol.toString();
	}

	/**
	 * Comprueba que el dato recibido es convertible al tipo objeto java deseado
	 * 
	 * @param tipo
	 * @param valor
	 * @param pattern
	 * @throws BusinessException
	 */
	private void validarTipoObjeto(String tipo, String valor, String pattern) throws BusinessException {
		try {
			switch (tipo) {
			case "Integer":
				new Integer(valor);
				break;
			case "Long":
				new Long(valor);
				break;
			case "Boolean":
				new Boolean(valor);
				break;
			case "Double":
				new Double(valor);
				break;
			case "Date":
				if (StringUtils.isBlank(pattern)) {
					throw new IllegalArgumentException("No hay formato para las fechas");
				}
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				Date c = sdf.parse(valor);
				break;
			}
		} catch (Exception e) {
			throw new BusinessException("Formato inv\u00E1lido");
		}
	}
}
