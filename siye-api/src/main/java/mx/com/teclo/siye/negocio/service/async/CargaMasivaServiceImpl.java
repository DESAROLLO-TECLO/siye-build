package mx.com.teclo.siye.negocio.service.async;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
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

	private static final String MSG_ERROR_TAMANIO_EXCEDE = "Err: Excede +{0} caracteres ";
	private static final String MSG_ERROR_LINEA_DESAJUSTADA = "Err: Excede +{0} columnas";
	private static final String MSG_ERROR_INSUFICIENTES_COLUMNAS = "Err: Columnas incompletas ({0} vs {1})";
	private static final String MSG_ERROR_FORMATO_INVALIDO = "Err: No es {0}";
	private static final String MSG_ERROR_DATO_REQUERIDO = "Err: Requerido ";
	private static final String MSG_LINEA_NOK = "Err: No cumple";
	private static final String TIE025D_IE_LOTE_ODS = ":TIE025D_IE_LOTE_ODS";

	private static final String MSG_ERROR_CARGA_PARCIAL = "Se detectaron errores en una o varias l\u00EDneas del CSV ID {0} y no se permite la carga parcial";
	private static final String MSG_LINEA_INVALIDA = "Err:";
	private static final String MSG_ERROR_CONEXION_BD = "Hubo un error al intentar contectar a la base de datos";
	private static final String MSG_LINEA_OK = "OK";
	private static final String MSG_HEADER_NO_CORRESPONDE = "La cantidad de columnas no son las esperadas";
	private static final String MSG_HEADER_RESULTADO = "RESULTADO";
	private static final String MSG_HEADER_ID_ORDEN_SERVICIO = "ID ORDEN DE SERVICIO";
	private static final String MSG_ERROR_EN_BD = "Err: Registro inconcluso";
	private static final String MSG_ERROR_NO_ENCONTRADO = "Err: No encontrado";
	private static final String MSG_ERROR_MAP_IDS_NULO = "Err: Sin resultados";
	private static final char CHAR_COMA = ',';
	private static final String ST_RECIBIDO = "RECIBIDO";

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
		boolean isConHeader = isConSeccionTipo(config.getConfigSecciones(), SeccionLayoutEnum.HEADER);
		boolean isConFooter = isConSeccionTipo(config.getConfigSecciones(), SeccionLayoutEnum.FOOTER);
		char delimitador = null == config.getConfigLayout().getCharDelimitador() ? CHAR_COMA
				: config.getConfigLayout().getCharDelimitador().charAt(BigDecimal.ZERO.intValue());

		// despues de la validacion se agrego una columna "Resultado"
		int colsEsperadas = config.getColumnasEnArchivo().size() + BigDecimal.ONE.intValue();
		int colsRecibidas = colsEsperadas;
		CSVFormat csvFormat = CSVFormat.EXCEL.withDelimiter(delimitador);
		if (isConHeader) {
			csvFormat = csvFormat.withHeader().withSkipHeaderRecord();
		}
		try (Reader reader = Files.newBufferedReader(Paths.get(input), StandardCharsets.UTF_8);
				CSVParser csvParser = new CSVParser(reader, csvFormat);
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(output), StandardCharsets.UTF_8);
				CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {
			try (Connection connection = ds.getConnection()) {
				if (!csvParser.getHeaderMap().isEmpty()) {
					Set<String> nuCols = csvParser.getHeaderMap().keySet();
					colsRecibidas = nuCols.size();
					ArrayList<String> linea = new ArrayList<>();
					linea.add(MSG_HEADER_ID_ORDEN_SERVICIO);
					for (String string : nuCols) {
						linea.add(string);
					}
					csvPrinter.printRecord(linea);
				}

				for (CSVRecord csvRecord : csvParser) {
					LinkedList<String> linea = new LinkedList<>();
					for (int i = 0; i < colsRecibidas; i++) {
						linea.add(csvRecord.get(i));
					}
					if (linea.get(0).startsWith(MSG_LINEA_INVALIDA)) {
						linea.addFirst(BigDecimal.ZERO.toString());
						csvPrinter.printRecord(linea);
						continue;
					}

					try {
						LinkedList<String> resultadoInsercion = insertarEnTablas(config, linea, connection);
						Long idODS = Long.valueOf(resultadoInsercion.getFirst());
						if (idODS.longValue() > BigDecimal.ZERO.longValue()) {
							totalOrdenesInsertadas++;
						}
						csvPrinter.printRecord(resultadoInsercion);
					} catch (Exception e) {
						linea.addFirst(MSG_LINEA_INVALIDA + " " + e.getMessage());
						csvPrinter.printRecord(linea);
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
		int totalLineasConError = BigDecimal.ZERO.intValue();
		boolean isConHeader = isConSeccionTipo(config.getConfigSecciones(), SeccionLayoutEnum.HEADER);
		boolean isConFooter = isConSeccionTipo(config.getConfigSecciones(), SeccionLayoutEnum.FOOTER);
		char delimitador = null == config.getConfigLayout().getCharDelimitador() ? CHAR_COMA
				: config.getConfigLayout().getCharDelimitador().charAt(BigDecimal.ZERO.intValue());
		int colEsperadas = config.getColumnasEnArchivo().size();
		CSVFormat csvFormat = CSVFormat.EXCEL.withDelimiter(delimitador);
		if (isConHeader) {
			csvFormat = csvFormat.withHeader().withSkipHeaderRecord();
		}

		try (Reader reader = Files.newBufferedReader(Paths.get(input), StandardCharsets.UTF_8);
				CSVParser csvParser = new CSVParser(reader, csvFormat);
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(output), StandardCharsets.UTF_8);
				CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {
			int colRecibidas = 0;
			if (!csvParser.getHeaderMap().isEmpty()) {

				Set<String> nuCols = csvParser.getHeaderMap().keySet();
				colRecibidas = nuCols.size();
				if (colRecibidas != colEsperadas) {
					throw new BusinessException(MSG_HEADER_NO_CORRESPONDE);
				}

				LinkedList<String> linea = new LinkedList<>();

				for (String string : nuCols) {
					linea.add(string);
				}
				linea.addFirst(MSG_HEADER_RESULTADO);
				csvPrinter.printRecord(linea);

			}
			for (CSVRecord csvRecord : csvParser) {
				LinkedList<String> linea = new LinkedList<>();
				for (int i = 0; i < csvRecord.size(); i++) {
					linea.add(csvRecord.get(i));
				}
				try {
					LinkedList<String> resultado = validarLinea(config, linea, delimitador);
					csvPrinter.printRecord(resultado);
				} catch (Exception e) {
					totalLineasConError++;
					linea.addFirst(MSG_LINEA_NOK);
					csvPrinter.printRecord(linea);
				}
			}

		} catch (IOException e) {
			throw new BusinessException(e.getMessage());
		}

		return totalLineasConError;
	}

	/**
	 * Busca registros por un campo filtro y recupera el ID para despu&eacute;s
	 * reemplazarlo en el insert de la orden de servicio y ejecutar el comando SQL
	 * 
	 * @param ConfigCargaMasivaVO
	 * @param linea
	 * @param separador
	 * @param con
	 * @return
	 * @throws BusinessException
	 */
	private LinkedList<String> insertarEnTablas(ConfigCargaMasivaVO config, LinkedList<String> arrayValores,
			Connection con) throws BusinessException {
		String separador = config.getConfigLayout().getCharDelimitador();
		HashMap<String, Long> mapaIds = new HashMap<String, Long>();
		List<TablaDestinoVO> nbTbls = config.getConfigInsercion();
		TablaDestinoVO ultimaTabla = nbTbls.get(nbTbls.size() - BigDecimal.ONE.intValue());
		Long idOrdenServicio = BigDecimal.ZERO.longValue();

		mapaIds = obtenerIDsTblsRef(config, arrayValores, separador, con);
		if (mapaIds != null)
			if (mapaIds.containsKey(ultimaTabla.getNbTabla()))
				if (mapaIds.get(ultimaTabla.getNbTabla()) != null)
					if (mapaIds.get(ultimaTabla.getNbTabla()).longValue() > BigDecimal.ZERO.longValue())
						idOrdenServicio = mapaIds.get(ultimaTabla.getNbTabla());

		if (idOrdenServicio != null && idOrdenServicio.longValue() > BigDecimal.ZERO.longValue()) {
			arrayValores.addFirst(idOrdenServicio.toString());
			return arrayValores;
		}

		return traducirErroresEnLinea(config, arrayValores, separador, mapaIds);

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
	private HashMap<String, Long> obtenerIDsTblsRef(ConfigCargaMasivaVO config, List<String> arrayValores,
			String separador, Connection con) throws BusinessException {

		HashMap<String, Long> mapaIds = new HashMap<String, Long>();
		List<TablaDestinoVO> nbTbls = config.getConfigInsercion();
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		TablaDestinoVO nbTbl = null;
		Long idGenerado = BigDecimal.ZERO.longValue();

		try {
			for (int i = 0; i < nbTbls.size(); i++) {
				idGenerado = BigDecimal.ZERO.longValue();
				nbTbl = nbTbls.get(i);
				InsercionTablaVO configSQL = config.getConfigMoldesSQL().get(nbTbl.getNbTabla());

				String querySelect = formatearSQL(configSQL.getSelectSQL(), arrayValores, separador);
				LOGGER.debug(querySelect);

				stmt = con.prepareStatement(querySelect);
				resultSet = stmt.executeQuery();

				if (resultSet.next()) {
					idGenerado = resultSet.getLong(1);
				}

				if (!resultSet.isClosed()) {
					resultSet.close();
				}
				if (!stmt.isClosed()) {
					stmt.close();
				}

				idGenerado = idGenerado == null ? BigDecimal.ZERO.longValue() : idGenerado;
				mapaIds.put(nbTbl.getNbTabla(), idGenerado);

				if (nbTbl.getIsReadOnly() || idGenerado.longValue() > BigDecimal.ZERO.longValue()) {
					continue;
				}

				String queryInsert = formatearSQL(configSQL.getInsertSQL(), arrayValores, separador);
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
				if (!resultSet.isClosed()) {
					resultSet.close();
				}
				if (!stmt.isClosed()) {
					stmt.close();
				}

				mapaIds.put(nbTbl.getNbTabla(), idGenerado);

			}

		} catch (SQLException e) {
			mapaIds.put(nbTbl.getNbTabla(), null);
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
					stmt.close();
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
	private LinkedList<String> traducirErroresEnLinea(ConfigCargaMasivaVO config, LinkedList<String> linea,
			String separador, Map<String, Long> mapaIds) throws BusinessException {

		if (mapaIds == null) {
			linea.addFirst(MSG_ERROR_MAP_IDS_NULO);
			return linea;
		}

		List<TablaDestinoVO> nbTbls = config.getConfigInsercion();
		TablaDestinoVO ultimaTabla = nbTbls.get(nbTbls.size() - BigDecimal.ONE.intValue());

		for (Entry<String, Long> refTbl : mapaIds.entrySet()) {

			@SuppressWarnings("unlikely-arg-type")
			boolean isUltimaTbl = ultimaTabla.equals(refTbl.getKey());

			// recuperamos columnas utilizadas en los selects
			ColumnaVO campoFiltro = config.getConfigMoldesSQL().get(refTbl.getKey()).getColumnaFiltro();
			int ordenEnArray = campoFiltro.getNuOrden();

			if (isUltimaTbl) {
				continue;
			}
			if (refTbl.getValue() == null) {
				linea.set(ordenEnArray, MSG_ERROR_EN_BD);
			} else if (refTbl.getValue().longValue() == BigDecimal.ZERO.longValue()) {
				linea.set(ordenEnArray, MSG_ERROR_NO_ENCONTRADO);
			}
		}

		linea.addFirst(BigDecimal.ZERO.toString());
		return linea;
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
		
		if(!loteDTO.getStSeguimiento().getCdStSeguimiento().equalsIgnoreCase(ST_RECIBIDO))
			return;
		
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
	 * Valida tama&ntilde;o, longitud y formato de cada columna
	 * 
	 * @param config
	 * @param arrayValores
	 * @param delimitador
	 * @return LinkedList<String>
	 * @throws IllegalArgumentException
	 * @throws BusinessException
	 */
	private LinkedList<String> validarLinea(ConfigCargaMasivaVO config, LinkedList<String> arrayValores,
			char delimitador) throws IllegalArgumentException, BusinessException {
		int colsRecibidas = arrayValores.size();
		int colsEsperadas = config.getColumnasEnArchivo().size();

		if (colsRecibidas != colsEsperadas) {
			if (colsRecibidas > colsEsperadas) {
				arrayValores.addFirst(MessageFormat.format(MSG_ERROR_LINEA_DESAJUSTADA, colsEsperadas));
				return arrayValores;
			}
			arrayValores.addFirst(MessageFormat.format(MSG_ERROR_INSUFICIENTES_COLUMNAS, colsRecibidas, colsEsperadas));
			return arrayValores;

		}
		boolean isLineaValida = true;

		for (ColumnaArchivoVO col : config.getColumnasEnArchivo()) {
			String valor = arrayValores.get(col.getNuOrden() - 1);
			String msgErrorCol = validarColumna(col, valor);

			if (StringUtils.isNotBlank(msgErrorCol)) {
				isLineaValida = false;
				arrayValores.set(col.getNuOrden() - 1, msgErrorCol);
			}
		}
		if (!isLineaValida) {
			arrayValores.addFirst(MSG_LINEA_NOK);
			return arrayValores;
		}
		arrayValores.addFirst(MSG_LINEA_OK);
		return arrayValores;

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
		if (continuar && StringUtils.isNotBlank(col.getTipoDato()) && StringUtils.isNotBlank(valor)) {
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
	private String formatearSQL(String molde, final List<String> valores, String separador) {
		String[] arrayValores = valores.toArray(new String[0]);
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

	private void guardarLineasIgnoradas(ConfigCargaMasivaVO config, Integer lineasConError) throws BusinessException {
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
