package mx.com.teclo.siye.negocio.service.async;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.hibernate.dao.async.LayoutDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.async.TipoLayoutDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.configuracion.ConfiguracionOSDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.LoteOrdenServicioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.configuracion.ConfiguracionOSDTO;
import mx.com.teclo.siye.persistencia.vo.async.ColumnaVO;
import mx.com.teclo.siye.persistencia.vo.async.ConfigCargaMasivaVO;
import mx.com.teclo.siye.persistencia.vo.async.InsercionTablaVO;
import mx.com.teclo.siye.persistencia.vo.async.TablaDestinoVO;
import mx.com.teclo.siye.persistencia.vo.async.TipoLayoutVO;
import mx.com.teclo.siye.util.enumerados.SeccionLayoutEnum;

@Service
public class LayoutServiceImpl implements LayoutService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LayoutServiceImpl.class);
	private static final Long ID_ORDEN_INSERCION = 5L;
	private static final String MSG_LAYOUT_SIN_ORDEN_INSERCION = "El layout no tiene un orden de valores a insertar";
	private static final String MSG_INSERT_PATTERN = "INSERT INTO {0}({1}) VALUES({2})";
	private static final String MSG_SELECT_PATTERN = "SELECT {0} FROM {1} WHERE {2} = {3}";
	public static final String MSG_LAYOUT_VIGENTE_NULO = "No existe un layout vigente. Contacte al administrador.";
	public static final String MSG_ERROR_LAYOUT_INEXISTENTE = "El archivo lote {0} no tiene un layout asociado";
	public static final String NULO_SQL = "null";
	public static final String CARACTER_COMA = ",";
	public static final String CARACTER_DOS_PUNTOS = ":";
	public static final String CARACTER_INTERROGACION = "?";
	public static final String CARACTER_PIPE = "|";
	private static final String MSG_FORMATTER_DATE = "(TO_DATE('{}',";
	public static final String SEPARADOR_DIR = System.getProperty("file.separator");
	public static final String SALTO_LINEA = System.getProperty("line.separator");
	public static final String MSG_ERROR_LAYOUT_SIN_DETALLE = "El layout no tiene detalle";
	public static final String MSG_ERROR_LAYOUT_SECCION_INCOMPLETA = "El numero de columnas ''{0}'' no coinciden con las de tipo ''{1}''";
	private static final String MSG_LAYOUT_SIN_COLUMNAS_CONFIGURADAS = "El layout no tiene columnas configuradas";
	private static final String MSG_LAYOUT_INCONSISTENTE = "El layout no tiene igual cantidad de columnas en sus diferentes secciones";
	private static final String MSG_ARCHIVO_COLUMNAS_INVALIDAS = "El n\u00FAmero de columnas del archivo lote no coincide con el layout vigente";

	private static final String MSG_ARCHIVO_REGEX_NAME_NULO = "No hay una regla para validar el nombre del archivo.";
	private static final String MSG_ARCHIVO_REGEX_NAME_INVALIDO = "El nombre del archivo es inv\u00E1lido.";
	public static final String MSG_ARCHIVO_TAMANIO_REBASADO = "El tama\u00F1o del archivo excede el m\u00E1ximo de {0} MB";
	private static final String MSG_DIRECTORIO_ORT_INDEFINIDO = "El directorio ort no ha sido especificado";
	private static final String MSG_FALTA_FORMATO_PARA_FECHA = "Falta formato fecha para la columna exel n\u00FAmero {0}";
	public static final String MSG_RECUPERANDO_CONFIG_MASIVA = "Recuperando la configuraci\u00F3n previa a la carga masiva del archivo {0} ";

	private static final Long ID_PROCESO_CON_RECHAZO = 9L;

	@Autowired
	private TipoLayoutDAO tipoLayoutDAO;

	@Autowired
	private LayoutDAO layoutDAO;

	@Autowired
	private LoteOrdenServicioDAO loteDAO;

	@Autowired
	private ConfiguracionOSDAO configuracionDAO;

	@Override
	@Transactional(readOnly = true)
	public TipoLayoutVO getLayoutVigente() {
		return tipoLayoutDAO.getLayoutVigente();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ColumnaVO> getSeccion(Long idTipoLayout, String cdSeccion) {
		return layoutDAO.getLayout(idTipoLayout, cdSeccion);
	}

	@Override
	@Transactional(readOnly = true)
	public InsercionTablaVO getNbsColumnas(String tabla) throws BusinessException {

		List<ColumnaVO> cols = layoutDAO.getNbsColumnas(tabla);
		if (cols == null || cols.isEmpty()) {
			return null;
		}
		InsercionTablaVO insertVO = new InsercionTablaVO();

		StringBuilder sbCols = new StringBuilder();
		StringBuilder sbVals = new StringBuilder();
		StringBuilder sbPatt = new StringBuilder();
		StringBuilder sbMaxL = new StringBuilder();

		for (ColumnaVO col : cols) {
			sbCols.append(CARACTER_COMA).append(col.getNbColumna());
			sbVals.append(CARACTER_COMA).append(getValorSQL(col));
			sbPatt.append(CARACTER_COMA).append(col.getCdTipo());
			sbMaxL.append(CARACTER_COMA).append(col.getNuLongitudMax());
		}

		insertVO.setColumnas(sbCols.toString().replaceFirst(CARACTER_COMA, ""));
		insertVO.setValores(sbVals.toString().replaceFirst(CARACTER_COMA, ""));
		insertVO.setComodines(sbPatt.toString().replaceFirst(CARACTER_COMA, ""));
		insertVO.setMaxLengths(sbMaxL.toString().replaceFirst(CARACTER_COMA, ""));
		return insertVO;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, List<ColumnaVO>> getColumnasPorSeccion(Long idTipoLayout) throws BusinessException {
		Map<String, List<ColumnaVO>> seccionesMap = new HashMap<String, List<ColumnaVO>>();
		List<ColumnaVO> encabezado = getSeccion(idTipoLayout, SeccionLayoutEnum.HEADER.getCdIndicadorReg());
		List<ColumnaVO> pie = getSeccion(idTipoLayout, SeccionLayoutEnum.FOOTER.getCdIndicadorReg());
		List<ColumnaVO> detalle = getSeccion(idTipoLayout, SeccionLayoutEnum.DETALLE.getCdIndicadorReg());
		if (detalle == null || detalle.isEmpty()) {
			throw new BusinessException(MSG_ERROR_LAYOUT_SIN_DETALLE);
		}
		seccionesMap.put(SeccionLayoutEnum.HEADER.getCdIndicadorReg(), encabezado);
		seccionesMap.put(SeccionLayoutEnum.DETALLE.getCdIndicadorReg(), detalle);
		seccionesMap.put(SeccionLayoutEnum.FOOTER.getCdIndicadorReg(), pie);
		return seccionesMap;

	}

	@Override
	@Transactional
	public ConfigCargaMasivaVO getConfigCargaMasiva(Long idArchivoLote) throws BusinessException {
		LOGGER.info(MessageFormat.format(MSG_RECUPERANDO_CONFIG_MASIVA, idArchivoLote));
		ConfigCargaMasivaVO cargaMasivaVO = new ConfigCargaMasivaVO();

		// lote
		cargaMasivaVO.setConfigLote(loteDAO.obtenerLote(idArchivoLote));
		if (cargaMasivaVO.getConfigLote() == null) {
			throw new BusinessException(
					MessageFormat.format(UploadServiceImpl.MSG_ERROR_LOTE_INEXISTENTE, idArchivoLote));
		}

		// orden de inserción en tablas
		cargaMasivaVO.setConfigInsercion(getOrdenInsercionTablas());

		// layout
		TipoLayoutVO layoutAplicado = tipoLayoutDAO.getTipoLayoutById(cargaMasivaVO.getConfigLote().getIdTipoLayout());
		cargaMasivaVO.setConfigLayout(layoutAplicado);
		if (cargaMasivaVO.getConfigLayout() == null) {
			throw new BusinessException(MSG_ERROR_LAYOUT_INEXISTENTE);
		}

		// secciones
		cargaMasivaVO.setConfigSecciones(getColumnasPorSeccion(layoutAplicado.getIdTipoLayout()));
		validarCantidadColumnas(cargaMasivaVO.getConfigSecciones());

		// moldes SQL
		cargaMasivaVO.setConfigMoldesSQL(getMoldesSQLPorTbl(cargaMasivaVO.getConfigInsercion()));

		// columnas esperadas en el archivo
		cargaMasivaVO.setColumnasEnArchivo(layoutDAO.getColumnasEnArchivo());

		return cargaMasivaVO;
	}

	@Override
	@Transactional
	public List<TablaDestinoVO> getOrdenInsercionTablas() throws BusinessException {
		ConfiguracionOSDTO configInsercionTbls = configuracionDAO.findOne(ID_ORDEN_INSERCION);
		if (configInsercionTbls == null || StringUtils.isBlank(configInsercionTbls.getCdValorConfig())
				|| configInsercionTbls.getCdValorConfig().indexOf(CARACTER_COMA + CARACTER_COMA) > 0) {
			throw new BusinessException(MSG_LAYOUT_SIN_ORDEN_INSERCION);
		}
		List<TablaDestinoVO> tablasDestino = new ArrayList<TablaDestinoVO>();
		List<String> nbTablas = Arrays.asList(configInsercionTbls.getCdValorConfig().split(CARACTER_COMA));
		for (String nombreTbl : nbTablas) {
			String nombreOriginal = nombreTbl.trim();
			String nombreFinal = nombreOriginal;
			boolean isReadOnly = Boolean.TRUE.booleanValue();
			if (nombreOriginal.startsWith(CARACTER_DOS_PUNTOS)) {
				isReadOnly = Boolean.FALSE.booleanValue();
				nombreFinal = nombreOriginal.replace(CARACTER_DOS_PUNTOS, "");
			}

			TablaDestinoVO tblDestino = new TablaDestinoVO();
			tblDestino.setNbTabla(nombreFinal);
			tblDestino.setIsReadOnly(isReadOnly);
			tblDestino.setIsTblBase(Boolean.FALSE.booleanValue());
			tablasDestino.add(tblDestino);
		}
		tablasDestino.get(nbTablas.size() - 1).setIsTblBase(Boolean.TRUE.booleanValue());
		return tablasDestino;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, InsercionTablaVO> getMoldesSQLPorTbl(List<TablaDestinoVO> tbls) throws BusinessException {

		if (tbls == null || tbls.isEmpty()) {
			throw new BusinessException(MSG_LAYOUT_SIN_ORDEN_INSERCION);
		}

		Map<String, InsercionTablaVO> insertQueriesMap = new HashMap<String, InsercionTablaVO>();
		for (TablaDestinoVO nbTbl : tbls) {
			InsercionTablaVO valInsertVO = getConcatNbCols(nbTbl.getNbTabla());
			String insertSQL = MessageFormat.format(MSG_INSERT_PATTERN, nbTbl.getNbTabla(), valInsertVO.getColumnas(),
					valInsertVO.getComodines());
			String selectSQL = MessageFormat.format(MSG_SELECT_PATTERN, valInsertVO.getCampoID(), nbTbl.getNbTabla(),
					valInsertVO.getColumnaFiltro().getNbColumna(), valInsertVO.getColumnaFiltro().getTxValorDefecto());
			valInsertVO.setInsertSQL(insertSQL);
			valInsertVO.setSelectSQL(selectSQL);

			insertQueriesMap.put(nbTbl.getNbTabla(), valInsertVO);
		}
		return insertQueriesMap;
	}

	/**
	 * Indica si se rechaza o se registra un archivo inv&aacute;lido
	 * 
	 * @return
	 */
	@Override
	@Transactional
	public boolean getIsProcesoConRechazo() {
		boolean rechazarArchivoInvalido = true;
		ConfiguracionOSDTO configDTO = configuracionDAO.findOne(ID_PROCESO_CON_RECHAZO);
		if (configDTO == null || StringUtils.isBlank(configDTO.getCdValorConfig())) {
			return rechazarArchivoInvalido;
		}
		try {
			return new Boolean(configDTO.getCdValorConfig()).booleanValue();
		} catch (IllegalArgumentException e) {
			return rechazarArchivoInvalido;
		}
	}

	/**
	 * Verifica que sean las mismas cantidades de columnas en todas las secciones
	 * del archivo
	 * 
	 * @param mapSecciones
	 * @throws BusinessException
	 */

	private void validarCantidadColumnas(Map<String, List<ColumnaVO>> mapSecciones) throws BusinessException {
		List<ColumnaVO> mapHeader = mapSecciones.get(SeccionLayoutEnum.HEADER.getCdIndicadorReg());
		List<ColumnaVO> mapDetalle = mapSecciones.get(SeccionLayoutEnum.DETALLE.getCdIndicadorReg());
		List<ColumnaVO> mapPie = mapSecciones.get(SeccionLayoutEnum.FOOTER.getCdIndicadorReg());

		if (mapDetalle == null || mapDetalle.isEmpty()) {
			throw new BusinessException(MSG_ERROR_LAYOUT_SIN_DETALLE);
		}

		if ((mapHeader != null && !mapHeader.isEmpty()) && mapHeader.size() != mapDetalle.size()) {
			throw new BusinessException(MessageFormat.format(MSG_ERROR_LAYOUT_SECCION_INCOMPLETA,
					SeccionLayoutEnum.HEADER.getNbSeccion(), SeccionLayoutEnum.DETALLE.getNbSeccion()));
		}

		if ((mapPie != null && !mapPie.isEmpty()) && mapPie.size() != mapDetalle.size()) {
			throw new BusinessException(MessageFormat.format(MSG_ERROR_LAYOUT_SECCION_INCOMPLETA,
					SeccionLayoutEnum.FOOTER.getNbSeccion(), SeccionLayoutEnum.DETALLE.getNbSeccion()));
		}

	}

	private InsercionTablaVO getConcatNbCols(String tabla) throws BusinessException {

		List<ColumnaVO> cols = layoutDAO.getNbsColumnas(tabla);
		if (cols == null || cols.isEmpty()) {
			return null;
		}
		InsercionTablaVO insertVO = new InsercionTablaVO();

		StringBuilder sbCols = new StringBuilder();
		StringBuilder sbVals = new StringBuilder();
		StringBuilder sbMaxs = new StringBuilder();
		StringBuilder sbTipos = new StringBuilder();
		StringBuilder sbComodines = new StringBuilder();
		ColumnaVO columnaFiltro = new ColumnaVO();

		for (ColumnaVO col : cols) {
			sbCols.append(CARACTER_COMA).append(col.getNbColumna());
			sbComodines.append(CARACTER_COMA).append(getValorSQL(col));
			sbVals.append(CARACTER_PIPE).append(getValorSQL(col));
			sbTipos.append(CARACTER_PIPE).append(col.getCdTipo());
			sbMaxs.append(CARACTER_PIPE).append(col.getNuLongitudMax());
			if (col.getStCampoFiltro() != null && col.getStCampoFiltro()) {
				columnaFiltro.setNbColumna(col.getNbColumna());
				columnaFiltro.setNuOrden(col.getNuOrden());
				columnaFiltro.setCdTipo(col.getCdTipo());
				columnaFiltro.setTxValorDefecto(getValorSQL(col));
			}
		}
		insertVO.setColumnas(sbCols.toString().replaceFirst(CARACTER_COMA, ""));
		insertVO.setComodines(sbComodines.toString().replaceFirst(CARACTER_COMA, ""));
		insertVO.setValores(sbVals.toString().replaceFirst(CARACTER_PIPE, ""));
		insertVO.setMaxLengths(sbMaxs.toString().replaceFirst(CARACTER_PIPE, ""));
		insertVO.setTipos(sbTipos.toString().replaceFirst(CARACTER_PIPE, ""));
		insertVO.setColumnaFiltro(columnaFiltro);

		String nbColumnaID = insertVO.getColumnas().substring(0, insertVO.getColumnas().indexOf(CARACTER_COMA));
		insertVO.setCampoID(nbColumnaID);
		return insertVO;
	}

	/**
	 * Concatena los valores predeterminados a insertar o indica la columna del
	 * archivo lote a insertar
	 * 
	 * @param col
	 * @return
	 * @throws BusinessException
	 */
	private String getValorSQL(ColumnaVO col) throws BusinessException {
		String colValor = "";
		if (col.getNuOrden() == null || col.getNuOrden() == BigDecimal.ZERO.longValue()) {
			colValor = StringUtils.isBlank(col.getTxValorDefecto()) ? "null" : col.getTxValorDefecto();
		} else {
			if (col.getCdTipo().equals("String")) {
				colValor = "''{" + col.getNuOrden() + "}''";
			} else {
				colValor = "{" + col.getNuOrden() + "}";
			}
		}
		return colValor;

	}

	/**
	 * Concatena los valores predeterminados a insertar e indica la columna del
	 * archivo lote a insertar
	 * 
	 * @param col
	 * @return
	 * @throws BusinessException
	 */
	private String determinarPxatronSQL(ColumnaVO col) throws BusinessException {
		String colValor = "";
		if (col.getNuOrden() == null) {
			colValor = StringUtils.isBlank(col.getTxValorDefecto()) ? NULO_SQL : col.getTxValorDefecto();
		} else {
			if (col.getCdTipo().equals("String")) {
				colValor = "''{" + col.getNuOrden() + "}''";
			} else {
				colValor = "{" + col.getNuOrden() + "}";
			}
		}
		return colValor;

	}

}
