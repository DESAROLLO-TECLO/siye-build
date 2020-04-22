package mx.com.teclo.siye.negocio.service.async;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import java.util.Map.Entry;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
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
import mx.com.teclo.siye.persistencia.vo.async.TablaDestinoVO;
import mx.com.teclo.siye.persistencia.vo.async.TipoLayoutVO;
import mx.com.teclo.siye.util.enumerados.ArchivoSeguimientoEnum;
import mx.com.teclo.siye.util.enumerados.SeccionLayoutEnum;
import mx.com.teclo.siye.util.enumerados.TipoDirectorioStorageEnum;
import mx.com.tecloreporte.jar.utils.comun.ConnectionUtilBd;

@Service
public class CargaMasivaServiceImpl implements CargaMasivaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CargaMasivaServiceImpl.class);
	private static final String MSG_BLOQUEANDO_ARCHIVO = "Bloqueando archivo ID {0} para iniciar su carga masiva ";
	private static final String MSG_LEYENDO_ARCHIVO_LOTE = "Leyendo el archivo {0} para procesar lineas";
	private static final String MSG_ACCEDIENDO_A_LA_RUTA = "Accediendo a la ruta de {0} {1} del archivo ID {2}";
	private static final String MSG_ERROR_LECTURA_ARCHIVO = "No fue posible iniciar el proceso del archivo {0}";
	private static final String MSG_ERROR_SQL = "Error al ejecutar comando SQL {0}";
	private static final String MSG_ERROR_TAMANIO_EXCEDE = "Err: Excede +{0} ";
	private static final String MSG_ERROR_LINEA_DESAJUSTADA = "Err: Excede +{0} columnas";
	private static final String MSG_ERROR_FORMATO_INVALIDO = "Err: No es {0}";
	private static final String MSG_ERROR_DATO_REQUERIDO = "Err: Requerido ";
	private static final String MSG_LINEA_NOK = "0";
	private static final String TIE025D_IE_LOTE_ODS = ":TIE025D_IE_LOTE_ODS";

	@Autowired
	private TipoLayoutDAO tipoLayoutDAO;
	@Autowired
	private LoteOrdenServicioDAO loteDAO;
	@Autowired
	private StSeguimientoDAO seguimientoDAO;
	@Autowired
	private ConnectionUtilBd conection;

	@Override
	@Async
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
			boolean isConHeader = isConSeccionTipo(config, SeccionLayoutEnum.HEADER);
			boolean isConFooter = isConSeccionTipo(config, SeccionLayoutEnum.FOOTER);

			while ((linea = reader.readLine()) != null) {

				if (isConHeader && reader.getLineNumber() == 1) {
					bw.write("Resultado," + linea);
					bw.newLine();
					continue;
				}
				if (isConFooter && reader.getLineNumber() == config.getConfigLote().getNuOdsReportados()) {
					bw.write(LayoutServiceImpl.CARACTER_COMA + linea);
					bw.newLine();
					continue;
				}
				try {
					validarLinea(config, linea, LayoutServiceImpl.CARACTER_COMA);
				} catch (IllegalArgumentException | BusinessException e) {
					bw.write(e.getMessage());
					bw.newLine();
					continue;
				}
				Long idODS = 0L;
				try {
					idODS = insertarEnTablas(config, linea, LayoutServiceImpl.CARACTER_COMA, con);
					bw.write(idODS.toString() + LayoutServiceImpl.CARACTER_COMA + linea);
					bw.newLine();
				} catch (Exception e) {
					bw.write(BigDecimal.ZERO.longValue() + LayoutServiceImpl.CARACTER_COMA + e.getMessage());
					bw.newLine();
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
				LOGGER.error(e.getMessage());
			}
		}

	}

	/**
	 * Busca registros por un campo filtro y recupera el ID para despu&eacute;s
	 * reemplazarlo en el insert de la orden de servicio y ejecutar el comando SQL
	 * 
	 * @param config
	 * @param linea
	 * @param separador
	 * @param con
	 * @return
	 * @throws BusinessException
	 */
	private Long insertarEnTablas(ConfigCargaMasivaVO config, String linea, String separador, Connection con)
			throws BusinessException {
		HashMap<String, Long> mapaIds = new HashMap<String, Long>();
		try {
			mapaIds = obtenerIDsTblsRef(config, linea, separador, con);
		} catch (Exception e) {
			return 0L;
		}

		String[] arrVal = linea.split(separador);

		boolean isExitoso = true;
		for (Entry<String, Long> refTbl : mapaIds.entrySet()) {
			ColumnaVO campoFiltro = config.getConfigMoldesSQL().get(refTbl.getKey()).getColumnaFiltro();
			int ordenColumna = campoFiltro.getNuOrden();
			int ordenEnArray = ordenColumna == 0 ? 0 : ordenColumna -1;
			if (refTbl.getValue() == null) {
				arrVal[ordenEnArray] = "Err: Error en BD";
				isExitoso = false;
			} else if (refTbl.getValue().longValue() == BigDecimal.ZERO.longValue()) {				
				arrVal[ordenEnArray] = "Err: No encontrado";
				isExitoso = false;
			}
		}
		if (!isExitoso) {
			String arrLinea = Arrays.toString(arrVal);
			throw new BusinessException(arrLinea);
		}

		List<TablaDestinoVO> nbTbls = config.getConfigInsercion();
		TablaDestinoVO ultimaTabla = nbTbls.get(nbTbls.size() - BigDecimal.ONE.intValue());

		InsercionTablaVO configSQL = config.getConfigMoldesSQL().get(ultimaTabla.getNbTabla());
		String queryInsert = new String(configSQL.getInsertSQL());
		LOGGER.debug("Q1 original " + queryInsert);

		for (Entry<String, Long> tblRef : mapaIds.entrySet()) {
			queryInsert = queryInsert.replace(LayoutServiceImpl.CARACTER_DOS_PUNTOS + tblRef.getKey(),
					tblRef.getValue().toString());
		}

		// Aplicamos en duro el id del lote
		queryInsert = queryInsert.replace(TIE025D_IE_LOTE_ODS, config.getConfigLote().getIdLoteOds().toString());

		queryInsert = formatearSQL(queryInsert, linea, separador);
		LOGGER.debug("Q2 aplicado formatearSQL " + queryInsert);

		queryInsert = queryInsert.replace("\\,", ",");
		LOGGER.debug("Q3 removiendo slash " + queryInsert);

		configSQL.setInsertSQL(queryInsert);
		// Long id = insertarOrdenServicio(config, linea, separador, con);

		LOGGER.info("Insertando ODS");
		Long idGenerado = 0L;

		String campoID = configSQL.getCampoID().getNbColumna();

		try {
			LOGGER.debug("Q4 previo a la busqueda " + queryInsert);

			PreparedStatement stmt = con.prepareStatement(queryInsert);
			stmt = con.prepareStatement(queryInsert, new String[] { campoID });
			stmt.executeUpdate();
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (null != generatedKeys) {
				Map<Object, Object> m = resultSetToArrayMap(generatedKeys);
				BigDecimal id = (BigDecimal) m.get("value");
				idGenerado = id.longValue();
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new BusinessException("Err: Guardado fallido");
		}

		return idGenerado;
	}

	/**
	 * Obtiene el mapa de identificadores de los registros a los que hace referencia
	 * la orden de servicio que ser&aacute; insertada
	 * 
	 * @param config
	 * @param linea
	 * @param separador
	 * @param con
	 * @return
	 * @throws BusinessException
	 */
	private HashMap<String, Long> obtenerIDsTblsRef(ConfigCargaMasivaVO config, String linea, String separador,
			Connection con) throws BusinessException {
		HashMap<String, Long> mapaIds = new HashMap<String, Long>();
		List<TablaDestinoVO> nbTbls = config.getConfigInsercion();
		for (TablaDestinoVO nbTbl : nbTbls) {
			try {
				InsercionTablaVO configSQL = config.getConfigMoldesSQL().get(nbTbl.getNbTabla());
				if (!nbTbl.getIsTblBase()) {
					Long idGenerado = 0L;
					String querySelect = formatearSQL(configSQL.getSelectSQL(), linea, separador);
					String queryInsert = formatearSQL(configSQL.getInsertSQL(), linea, separador);
					PreparedStatement stmt = con.prepareStatement(querySelect);
					ResultSet resultSet = stmt.executeQuery();
					if (resultSet.next()) {
						//mapaIds.put(nbTbl.getNbTabla(), resultSet.getLong(1));
						idGenerado = resultSet.getLong(1);
					} else {						
						if (!nbTbl.getIsReadOnly()) {
							LOGGER.debug(queryInsert);
							stmt = con.prepareStatement(queryInsert,
									new String[] { configSQL.getCampoID().getNbColumna() });
							stmt.executeUpdate();
							ResultSet generatedKeys = stmt.getGeneratedKeys();
							if (null != generatedKeys) {
								Map<Object, Object> m = resultSetToArrayMap(generatedKeys);
								BigDecimal id = (BigDecimal) m.get("value");
								//mapaIds.put(nbTbl.getNbTabla(), id.longValue());
								idGenerado = id.longValue();
							}
						}
						
					}
					mapaIds.put(nbTbl.getNbTabla(), idGenerado);
				}
			} catch (SQLException e) {
				mapaIds.put(nbTbl.getNbTabla(), null);
			}
		}
		return mapaIds;

	}

	/**
	 * Recupera los datos del resultado de la ejecuci&oacute;n de un comando SQL
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Map<Object, Object> resultSetToArrayMap(ResultSet rs) throws SQLException {
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

	/**
	 * Cuenta las l&iacute;neas de un archivo
	 * 
	 * @param aFile
	 * @return
	 * @throws IOException
	 */
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

	/**
	 * Regresa verdadero si se espera encontrar una l&iacute;nea de t&iacute;tulos o
	 * resumen en el csv (header y footer)
	 * 
	 * @param config
	 * @param seccionTipo
	 * @return
	 */
	private boolean isConSeccionTipo(ConfigCargaMasivaVO config, SeccionLayoutEnum seccionTipo) {
		List<ColumnaVO> cols = config.getConfigSecciones().get(seccionTipo.getCdIndicadorReg());
		return (cols != null && !cols.isEmpty());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void iniciarCargaMasiva(Long idArchivoLote) throws BusinessException {
		LOGGER.info(MessageFormat.format(MSG_BLOQUEANDO_ARCHIVO, idArchivoLote));
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

	/**
	 * Valida tama&ntilde;o, longitud y formato de todas las columnas de una
	 * l&iacute;nea
	 * 
	 * @param config
	 * @param linea
	 * @param separador
	 * @throws IllegalArgumentException
	 * @throws BusinessException
	 */
	private void validarLinea(ConfigCargaMasivaVO config, String linea, String separador)
			throws IllegalArgumentException, BusinessException {

		List<String> arrayValores = Arrays.asList(linea.split(separador));
		int colsEsperadas = config.getColumnasEnArchivo().size();
		int colsRecibidas = arrayValores.size();

		StringBuilder sbErrores = new StringBuilder();
		boolean isLineaValida = true;
		if (colsEsperadas != colsRecibidas) {
			throw new IllegalArgumentException(MessageFormat.format(MSG_ERROR_LINEA_DESAJUSTADA + separador + linea,
					colsEsperadas, colsRecibidas));
		}

		for (ColumnaArchivoVO col : config.getColumnasEnArchivo()) {
			String valor = arrayValores.get(col.getNuOrden() - 1);
			String msgErrorCol = validarColumna(col, valor);

			if (StringUtils.isNotBlank(msgErrorCol)) {
				isLineaValida = false;
			}
			sbErrores.append(separador).append(StringUtils.isBlank(msgErrorCol) ? valor : msgErrorCol);
		}
		String resultado = sbErrores.toString().replaceFirst(separador, "");
		if (!isLineaValida) {
			throw new BusinessException(MSG_LINEA_NOK + separador + resultado);
		}

	}

	/**
	 * Valida por columna si falta un dato, si excede tama&ntilde;o o es formato
	 * inv&aacute;lido
	 * 
	 * @param col
	 * @param valor
	 * @return
	 */
	private String validarColumna(ColumnaArchivoVO col, String valor) {
		StringBuilder sbErrCol = new StringBuilder();
		boolean continuar = true;
		if (col.getIsRequerido() && StringUtils.isBlank(valor)) {
			sbErrCol.append(MSG_ERROR_DATO_REQUERIDO);
			continuar = false;
		}
		if (continuar && valor.length() > col.getLongMax()) {
			sbErrCol.append(MessageFormat.format(MSG_ERROR_TAMANIO_EXCEDE, col.getLongMax()));
			continuar = false;

		}
		if (continuar && StringUtils.isNotBlank(col.getTipoDato())) {
			try {
				validarTipoObjeto(col.getTipoDato(), valor, col.getTxMascara());
			} catch (Exception e) {
				String tipoTraducido = traducirTipo(col.getTipoDato(), col.getTxMascara());
				sbErrCol.append(MessageFormat.format(MSG_ERROR_FORMATO_INVALIDO, tipoTraducido));
			}
		}
		return sbErrCol.toString();
	}

	/**
	 * Comprueba que el dato recibido es convertible al tipo objeto java configurado
	 * en BD
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
				sdf.setLenient(false);
				sdf.parse(valor);
				break;
			}
		} catch (Exception e) {
			throw new BusinessException("Formato inv\u00E1lido");
		}
	}

	/**
	 * Reemplaza el tipo dato java por uno reconocido por un usuario no t&eacute;cnico
	 * 
	 * @param tipoDato
	 * @param formato
	 * @return
	 */
	private String traducirTipo(String tipoDato, String formato) {
		switch (tipoDato) {
		case "Integer":
			return "num\u00E9rico";
		case "Long":
			return "num\u00E9rico";
		case "Boolean":
			return "1 o 0";
		case "Double":
			return "decimal";
		case "Date":
			return "fecha " + formato;
		default:
			return "alfanum\u00E9ricos";

		}

	}

	/**
	 * Sustituye en el query molde el valor que le corresponde por columna
	 * 
	 * @param molde
	 * @param linea
	 * @param separador
	 * @return
	 */
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
}
