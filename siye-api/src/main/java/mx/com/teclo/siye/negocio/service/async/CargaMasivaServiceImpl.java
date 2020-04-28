package mx.com.teclo.siye.negocio.service.async;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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

import javax.sql.DataSource;

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
import mx.com.teclo.siye.persistencia.vo.async.ArchivoLoteVO;
import mx.com.teclo.siye.persistencia.vo.async.ColumnaArchivoVO;
import mx.com.teclo.siye.persistencia.vo.async.ColumnaVO;
import mx.com.teclo.siye.persistencia.vo.async.ConfigCargaMasivaVO;
import mx.com.teclo.siye.persistencia.vo.async.ConfigLayoutVO;
import mx.com.teclo.siye.persistencia.vo.async.InsercionTablaVO;
import mx.com.teclo.siye.persistencia.vo.async.TablaDestinoVO;
import mx.com.teclo.siye.util.enumerados.ArchivoSeguimientoEnum;
import mx.com.teclo.siye.util.enumerados.SeccionLayoutEnum;
import mx.com.teclo.siye.util.enumerados.TipoDirectorioStorageEnum;

@Service
public class CargaMasivaServiceImpl implements CargaMasivaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CargaMasivaServiceImpl.class);
	private static final String MSG_BLOQUEANDO_ARCHIVO = "Bloqueando archivo ID {0} para iniciar su carga masiva ";
	private static final String MSG_LEYENDO_ARCHIVO_LOTE = "Leyendo el archivo {0} para procesar lineas";
	private static final String MSG_ACCEDIENDO_A_LA_RUTA = "Accediendo a la ruta de {0} {1} del archivo ID {2}";
	private static final String MSG_ERROR_LECTURA_ARCHIVO = "Hubo un problema al validar el contenido del archivo {0}";
	private static final String MSG_ERROR_ARCHIVO_NO_ENCONTRADO = "El archivo del lote ID {0} no fue encontrado";
	private static final String MSG_ERROR_SQL = "Error al ejecutar comando SQL {0}";
	private static final String MSG_ERROR_TAMANIO_EXCEDE = "Err: Excede +{0} caracteres ";
	private static final String MSG_ERROR_LINEA_DESAJUSTADA = "Err: Excede +{0} columnas";
	private static final String MSG_ERROR_INSUFICIENTES_COLUMNAS = "Err: Columnas incompletas ({0} vs {1})";
	private static final String MSG_ERROR_FORMATO_INVALIDO = "Err: No es {0}";
	private static final String MSG_ERROR_DATO_REQUERIDO = "Err: Requerido ";
	private static final String MSG_LINEA_NOK = "Err: No cumple";
	private static final String TIE025D_IE_LOTE_ODS = ":TIE025D_IE_LOTE_ODS";
	private static final String MSG_ERROR_INDETERMINADO = "Err: Error inesperado ";
	private static final String MSG_ERROR_CARGA_PARCIAL = "Se detectaron errores en una o varias l\u00EDneas del CSV ID {0} y no se permite la carga parcial";
	private static final String MSG_LINEA_INVALIDA = "Err:";
	private static final String MSG_ERROR_CONEXION_BD = "Hubo un error al intentar contectar a la base de datos";
	private static final String MSG_LINEA_OK = "OK";
	
	@Autowired
	private TipoLayoutDAO tipoLayoutDAO;
	@Autowired
	private LoteOrdenServicioDAO loteDAO;
	@Autowired
	private StSeguimientoDAO seguimientoDAO;
	@Autowired
	private DataSource ds;

	@Override
	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void procesarLineas(ConfigCargaMasivaVO config) throws BusinessException {

		LOGGER.debug(MessageFormat.format(MSG_LEYENDO_ARCHIVO_LOTE, config.getConfigLote().getIdLoteOds()));

		String rutaRecibido = config.getConfigLote().getNbLoteOds();
		String rutaEntregado = rutaRecibido.replace(TipoDirectorioStorageEnum.INPUT.getCdTipo(),
				TipoDirectorioStorageEnum.OUTPUT.getCdTipo());
		String rutaValidado = rutaRecibido.replace(TipoDirectorioStorageEnum.INPUT.getCdTipo(),
				TipoDirectorioStorageEnum.STAGE.getCdTipo());

		// valida datos del archivo
		int lineasConError = validarLineas(config, rutaRecibido, rutaValidado);

		// actualizar lineas ignoradas
		guardarLineasIgnoradas(config, lineasConError);

		// determinar si finaliza el proceso
		if (!config.getConfigLayout().getStCargaParcial() && lineasConError > BigDecimal.ZERO.intValue()) {
			return;
		}
		// crear las ordenes de servicio
		int lineasInsertadas = insertarLineas(config, rutaValidado, rutaEntregado);

		// finaliza
		guardarLineasCargadas(config, lineasInsertadas);

	}

	private int insertarLineas(ConfigCargaMasivaVO config, String input, String output) throws BusinessException {
		int totalOrdenesInsertadas = 0;
		try (LineNumberReader reader = new LineNumberReader(new FileReader(input));
				BufferedWriter bw = new BufferedWriter(new FileWriter(output));) {
			try (Connection connection = ds.getConnection()) {
				String linea;
				boolean isConHeader = isConSeccionTipo(config.getConfigSecciones(), SeccionLayoutEnum.HEADER);
				boolean isConFooter = isConSeccionTipo(config.getConfigSecciones(), SeccionLayoutEnum.FOOTER);

				while ((linea = reader.readLine()) != null) {

					if (isConHeader && reader.getLineNumber() == 1) {
						bw.write("ID orden de servicio" + LayoutServiceImpl.CARACTER_COMA + linea);
						bw.newLine();
						continue;
					}
					if (isConFooter && reader.getLineNumber() == config.getConfigLote().getNuOdsReportados()) {
						bw.write(LayoutServiceImpl.CARACTER_COMA + linea);
						bw.newLine();
						continue;
					}
					if (linea.startsWith(MSG_LINEA_INVALIDA)) {
						bw.write(BigDecimal.ZERO.longValue()+LayoutServiceImpl.CARACTER_COMA + linea);
						bw.newLine();
						continue;
					}
					Long idODS = 0L;
					try {
						idODS = insertarEnTablas(config, linea, LayoutServiceImpl.CARACTER_COMA, connection);
						if (idODS.longValue() > BigDecimal.ZERO.longValue()) {
							totalOrdenesInsertadas++;
						}
						bw.write(idODS.toString() + LayoutServiceImpl.CARACTER_COMA + linea);
						bw.newLine();
					} catch (Exception e) {
						bw.write(0L + LayoutServiceImpl.CARACTER_COMA + e.getMessage());
						bw.newLine();
					}
				}
			} catch (SQLException e2) {
				LOGGER.error(e2.getMessage());
				throw new BusinessException(MSG_ERROR_CONEXION_BD);
			}

		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			throw new BusinessException(
					MessageFormat.format(MSG_ERROR_LECTURA_ARCHIVO, config.getConfigLote().getIdLoteOds()));
		}
		return totalOrdenesInsertadas;
	}

	private Integer validarLineas(ConfigCargaMasivaVO config, String input, String output) throws BusinessException {
		LOGGER.debug(MessageFormat.format(MSG_ACCEDIENDO_A_LA_RUTA, TipoDirectorioStorageEnum.INPUT.getCdTipo(), input,
				config.getConfigLote().getIdLoteOds()));
		LOGGER.debug(MessageFormat.format(MSG_ACCEDIENDO_A_LA_RUTA, TipoDirectorioStorageEnum.STAGE.getCdTipo(), output,
				config.getConfigLote().getIdLoteOds()));
		int totalLineasConError = 0;
		try (LineNumberReader reader = new LineNumberReader(new FileReader(input));
				BufferedWriter bw = new BufferedWriter(new FileWriter(output));) {

			String linea;
			boolean isConHeader = isConSeccionTipo(config.getConfigSecciones(), SeccionLayoutEnum.HEADER);
			boolean isConFooter = isConSeccionTipo(config.getConfigSecciones(), SeccionLayoutEnum.FOOTER);

			while ((linea = reader.readLine()) != null) {
				
				if (isConHeader && reader.getLineNumber() == 1) {
					bw.write("Resultado" + LayoutServiceImpl.CARACTER_COMA + linea);
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
					bw.write(MSG_LINEA_OK + LayoutServiceImpl.CARACTER_COMA + linea);
					bw.newLine();
				} catch (IllegalArgumentException | BusinessException e) {
					totalLineasConError++;
					bw.write(e.getMessage());
					bw.newLine();
				} catch (Exception e) {
					totalLineasConError++;
					bw.write(MSG_ERROR_INDETERMINADO + LayoutServiceImpl.CARACTER_COMA + linea);
					bw.newLine();
				}
			}
			return totalLineasConError;
		} catch (FileNotFoundException e2) {
			throw new BusinessException(
					MessageFormat.format(MSG_ERROR_ARCHIVO_NO_ENCONTRADO, config.getConfigLote().getIdLoteOds()));
		} catch (IOException e2) {
			throw new BusinessException(
					MessageFormat.format(MSG_ERROR_LECTURA_ARCHIVO, config.getConfigLote().getIdLoteOds()));
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
		List<TablaDestinoVO> nbTbls = config.getConfigInsercion();
		TablaDestinoVO ultimaTabla = nbTbls.get(nbTbls.size() - BigDecimal.ONE.intValue());
		try {
			mapaIds = obtenerIDsTblsRef(config, linea, separador, con);
			if (mapaIds != null)
				if (mapaIds.containsKey(ultimaTabla.getNbTabla()))
					if (mapaIds.get(ultimaTabla.getNbTabla()).longValue() > BigDecimal.ZERO.longValue())
						return mapaIds.get(ultimaTabla.getNbTabla());

			traducirErroresEnLinea(config, linea, separador, mapaIds);
		} catch (BusinessException e) {
			throw e;
		} catch (RuntimeException e1) {
			String[] arrVal = linea.split(separador);
			arrVal[0] = "Err: " + e1.getMessage();
			throw new BusinessException(Arrays.toString(arrVal));
		}
		return 0L;

	}

	/**
	 * Obtiene el mapa de identificadores de los registros a los que hace referencia
	 * la orden de servicio que ser&aacute; insertada y crea la orden
	 * 
	 * @param config
	 * @param linea
	 * @param separador
	 * @param con
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("resource")
	private HashMap<String, Long> obtenerIDsTblsRef(ConfigCargaMasivaVO config, String linea, String separador,
			Connection con) throws BusinessException {

		HashMap<String, Long> mapaIds = new HashMap<String, Long>();
		List<TablaDestinoVO> nbTbls = config.getConfigInsercion();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			for (int i = 0; i < nbTbls.size(); i++) {
				Long idGenerado = 0L;
				TablaDestinoVO nbTbl = nbTbls.get(i);
				InsercionTablaVO configSQL = config.getConfigMoldesSQL().get(nbTbl.getNbTabla());

				String querySelect = formatearSQL(configSQL.getSelectSQL(), linea, separador);
				LOGGER.debug(querySelect);

				stmt = con.prepareStatement(querySelect);
				resultSet = stmt.executeQuery();

				if (resultSet.next()) {
					idGenerado = resultSet.getLong(1);
				}
				
				if(!resultSet.isClosed()){
					resultSet.close();
				}
				if(!stmt.isClosed()) {
					stmt.close();
				}
				
				if (idGenerado != null && idGenerado.longValue() > BigDecimal.ZERO.longValue()) {
					mapaIds.put(nbTbl.getNbTabla(), idGenerado);
					continue;
				}

				if (nbTbl.getIsReadOnly()) {
					mapaIds.put(nbTbl.getNbTabla(), idGenerado == null ? 0L : idGenerado);
					continue;
				}

				idGenerado = 0L;

				String queryInsert = formatearSQL(configSQL.getInsertSQL(), linea, separador);
				queryInsert = queryInsert.replace("\\,", ",");
				if (nbTbl.getIsTblBase()) {
					for (Entry<String, Long> tblRef : mapaIds.entrySet()) {
						queryInsert = queryInsert.replace(LayoutServiceImpl.CARACTER_DOS_PUNTOS + tblRef.getKey(),
								tblRef.getValue().toString());
					}
					// Aplicamos en duro el id del lote
					if (nbTbl.getIsTblBaseFinal()) {
						queryInsert = queryInsert.replace(TIE025D_IE_LOTE_ODS,
								config.getConfigLote().getIdLoteOds().toString());

					}
				}
				LOGGER.debug(queryInsert);
				stmt = con.prepareStatement(queryInsert, new String[] { configSQL.getCampoID().getNbColumna() });
				stmt.executeUpdate();
				resultSet = stmt.getGeneratedKeys();				
				if (null != resultSet) {
					Map<Object, Object> m = resultSetToArrayMap(resultSet);
					BigDecimal id = (BigDecimal) m.get("value");
					idGenerado = id.longValue();

				}
				if(!resultSet.isClosed()){
					resultSet.close();
				}
				if(!stmt.isClosed()) {
					stmt.close();
				}

				mapaIds.put(nbTbl.getNbTabla(), idGenerado);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			if (stmt != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}

		return mapaIds;

	}

	/**
	 * Obtiene la posici&oacute;n del valor de b&uacute;squeda y es sustituido por
	 * el error
	 * 
	 * @param config
	 * @param linea
	 * @param separador
	 * @param mapaIds
	 * @throws BusinessException
	 */
	private void traducirErroresEnLinea(ConfigCargaMasivaVO config, String linea, String separador,
			Map<String, Long> mapaIds) throws BusinessException {
		String[] arrVal = linea.split(separador);
		if (mapaIds == null) {
			arrVal[0] = "Err: Sin resultados";
			throw new BusinessException(Arrays.toString(arrVal));
		}
		boolean isExitoso = true;
		for (Entry<String, Long> refTbl : mapaIds.entrySet()) {
			ColumnaVO campoFiltro = config.getConfigMoldesSQL().get(refTbl.getKey()).getColumnaFiltro();
			int ordenEnArray = campoFiltro.getNuOrden();
			
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
	private boolean isConSeccionTipo(Map<String, List<ColumnaVO>> config, SeccionLayoutEnum seccionTipo) {
		List<ColumnaVO> cols = config.get(seccionTipo.getCdIndicadorReg());
		return (cols != null && !cols.isEmpty());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void iniciarCargaMasiva(Long idArchivoLote) throws BusinessException {

		LOGGER.debug(MessageFormat.format(MSG_BLOQUEANDO_ARCHIVO, idArchivoLote));
		LoteOrdenServicioDTO loteDTO = loteDAO.findOne(idArchivoLote);
		Long lineas = BigDecimal.ZERO.longValue();
		try {
			lineas = (long) countLines(new File(loteDTO.getNbLoteOds()));
		} catch (IOException e) {
			lineas--;
		}
		ConfigLayoutVO layoutVigente = tipoLayoutDAO.getLayoutVigente();
		if (layoutVigente == null) {
			throw new BusinessException(LayoutServiceImpl.MSG_LAYOUT_VIGENTE_NULO);
		}

		TipoLayoutDTO layoutVigenteDTO = tipoLayoutDAO.findOne(layoutVigente.getIdTipoLayout());

		StSeguimientoDTO seguimientoDTO = seguimientoDAO.obtenerStSeguimientoByCodigo(
				ArchivoSeguimientoEnum.CARGANDO.getCdArchivoSeg(), ArchivoSeguimientoEnum.CARGANDO.getCdTipoSeg(),
				ArchivoSeguimientoEnum.CARGANDO.getNbTipoSeg());
		loteDTO.setStSeguimiento(seguimientoDTO);
		loteDTO.setTipoLayout(layoutVigenteDTO);
		loteDTO.setFhModificacion(new Date());
		loteDTO.setNuOdsReportados(lineas);
		loteDTO.setNuOdsCargados(BigDecimal.ZERO.longValue());
		loteDTO.setNuOdsIgnorados(BigDecimal.ZERO.longValue());
		loteDTO.setNuOdsAtendidos(BigDecimal.ZERO.longValue());
		loteDTO.setNuOdsPendientes(BigDecimal.ZERO.longValue());
		loteDTO.setNuOdsIncidencia(BigDecimal.ZERO.longValue());
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
		if (colsRecibidas != colsEsperadas) {
			if (colsRecibidas > colsEsperadas) {
				throw new IllegalArgumentException(
						MessageFormat.format(MSG_ERROR_LINEA_DESAJUSTADA + separador + linea, colsEsperadas));
			}
			throw new IllegalArgumentException(MessageFormat
					.format(MSG_ERROR_INSUFICIENTES_COLUMNAS + separador + linea, colsRecibidas, colsEsperadas));
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
	 * Reemplaza el tipo dato java por uno reconocido por un usuario no
	 * t&eacute;cnico
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
		// String nvaLineaValores = separador + linea;
		String[] arrayValores = linea.split(separador);
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

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void actualizarCargaMasiva(ArchivoLoteVO archivoLoteVO) throws BusinessException {
		if (archivoLoteVO == null || archivoLoteVO.getIdLoteOds() == null
				|| StringUtils.isBlank(archivoLoteVO.getCdStSeguimiento())) {
			throw new BusinessException("Falta estatus");

		}
		LoteOrdenServicioDTO loteDTO = loteDAO.findOne(archivoLoteVO.getIdLoteOds());
		ArchivoSeguimientoEnum estado = ArchivoSeguimientoEnum.valueOf(ArchivoSeguimientoEnum.class,
				archivoLoteVO.getCdStSeguimiento());

		StSeguimientoDTO seguimientoDTO = seguimientoDAO.obtenerStSeguimientoByCodigo(estado.getCdArchivoSeg(),
				estado.getCdTipoSeg(), estado.getNbTipoSeg());
		loteDTO.setStSeguimiento(seguimientoDTO);
		loteDTO.setFhModificacion(new Date());
		if (archivoLoteVO.getNuOdsIgnoradas() != null) {
			loteDTO.setNuOdsIgnorados(archivoLoteVO.getNuOdsIgnoradas());
		}
		if (archivoLoteVO.getNuOdsCargados() != null) {
			loteDTO.setNuOdsCargados(archivoLoteVO.getNuOdsCargados());
		}
		if (StringUtils.isNotBlank(archivoLoteVO.getTxLoteOds())) {
			loteDTO.setTxLoteOds(archivoLoteVO.getTxLoteOds());
		}
		loteDAO.update(loteDTO);
	}

	private void guardarLineasIgnoradas(ConfigCargaMasivaVO config, int lineasConError) throws BusinessException {
		ArchivoLoteVO archivoLoteVO = new ArchivoLoteVO();
		archivoLoteVO.setIdLoteOds(config.getConfigLote().getIdLoteOds());
		archivoLoteVO.setNuOdsIgnoradas((long) lineasConError);
		archivoLoteVO.setCdStSeguimiento(config.getConfigLote().getCdStSeguimiento());
		if (!config.getConfigLayout().getStCargaParcial() && lineasConError > BigDecimal.ZERO.intValue()) {
			String errorParcialidad = MessageFormat.format(MSG_ERROR_CARGA_PARCIAL,
					config.getConfigLote().getIdLoteOds());
			archivoLoteVO.setTxLoteOds(errorParcialidad);
			archivoLoteVO.setCdStSeguimiento(ArchivoSeguimientoEnum.CARGADO.getCdArchivoSeg());
		}
		actualizarCargaMasiva(archivoLoteVO);
	}

	private void guardarLineasCargadas(ConfigCargaMasivaVO config, int lineasInsertadas) throws BusinessException {
		ArchivoLoteVO archivoProcesadoVO = new ArchivoLoteVO();
		archivoProcesadoVO.setIdLoteOds(config.getConfigLote().getIdLoteOds());
		archivoProcesadoVO.setNuOdsCargados((long) lineasInsertadas);
		archivoProcesadoVO.setCdStSeguimiento(ArchivoSeguimientoEnum.CARGADO.getCdArchivoSeg());
		actualizarCargaMasiva(archivoProcesadoVO);
	}
}
