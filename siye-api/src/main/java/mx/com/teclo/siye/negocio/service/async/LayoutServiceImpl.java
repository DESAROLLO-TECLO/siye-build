package mx.com.teclo.siye.negocio.service.async;

import java.math.BigDecimal;
import java.text.MessageFormat;
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
import mx.com.teclo.siye.persistencia.vo.async.ConfigLayoutVO;
import mx.com.teclo.siye.util.enumerados.SeccionLayoutEnum;

@Service
public class LayoutServiceImpl implements LayoutService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LayoutServiceImpl.class);
	private static final String MSG_LAYOUT_SIN_ORDEN_INSERCION = "El layout no tiene un orden de valores a insertar";
	private static final String MSG_INSERT_PATTERN = "INSERT INTO {0}({1}) VALUES({2})";
	private static final String MSG_SELECT_PATTERN = "SELECT {0} FROM {1} WHERE {2} = {3}";
	public static final String MSG_LAYOUT_VIGENTE_NULO = "No es posible continuar el proceso por falta de definiciones sobre lo que se espera recibir en el archivo lote";
	public static final String MSG_ERROR_LAYOUT_INEXISTENTE = "El archivo lote {0} no tiene un layout asociado";
	public static final String MSG_ERROR_FALTAN_PARAMETROS = "No es posible continuar el proceso por falta de configuraciones en el sistema";
	public static final String NULO_SQL = "null";
	public static final String CARACTER_COMA = ",";
	public static final String CARACTER_DOS_PUNTOS = ":";
	public static final String CARACTER_INTERROGACION = "?";
	public static final String CARACTER_PIPE = "|";
	public static final String SEPARADOR_DIR = System.getProperty("file.separator");
	public static final String SALTO_LINEA = System.getProperty("line.separator");
	public static final String MSG_ERROR_LAYOUT_SIN_DETALLE = "El layout no tiene detalle";
	public static final String MSG_ERROR_LAYOUT_SECCION_INCOMPLETA = "El numero de columnas ''{0}'' no coinciden con las de tipo ''{1}''";
	public static final String MSG_ARCHIVO_TAMANIO_REBASADO = "El tama\u00F1o del archivo excede el m\u00E1ximo de {0} MB";
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
	public ConfigLayoutVO getLayoutVigente() {
		return tipoLayoutDAO.getLayoutVigente();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ColumnaVO> getSeccion(Long idTipoLayout, String cdSeccion) {
		return layoutDAO.getLayout(idTipoLayout, cdSeccion);
	}

	@Override
	@Transactional(readOnly = true)
	public InsercionTablaVO getNbsColumnas(Long idTipoLayout, String tabla) throws BusinessException {

		List<ColumnaVO> cols = layoutDAO.getNbsColumnas(idTipoLayout, tabla);
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
		cargaMasivaVO.setConfigInsercion(getOrdenInsercionTablas(cargaMasivaVO.getConfigLote().getIdTipoLayout()));

		// layout
		ConfigLayoutVO layoutAplicado = tipoLayoutDAO.getTipoLayoutById(cargaMasivaVO.getConfigLote().getIdTipoLayout());
		cargaMasivaVO.setConfigLayout(layoutAplicado);
		if (cargaMasivaVO.getConfigLayout() == null) {
			throw new BusinessException(
					MessageFormat.format(MSG_ERROR_LAYOUT_INEXISTENTE, cargaMasivaVO.getConfigLote().getCdLoteOds()));
		}

		// secciones
		cargaMasivaVO.setConfigSecciones(getColumnasPorSeccion(layoutAplicado.getIdTipoLayout()));
		validarCantidadColumnas(cargaMasivaVO.getConfigSecciones());

		// moldes SQL
		cargaMasivaVO.setConfigMoldesSQL(getMoldesSQLPorTbl(cargaMasivaVO.getConfigLayout().getIdTipoLayout(),
				cargaMasivaVO.getConfigInsercion()));

		// columnas esperadas en el archivo
		cargaMasivaVO.setColumnasEnArchivo(
				layoutDAO.getColumnasEnArchivo(cargaMasivaVO.getConfigLayout().getIdTipoLayout()));

		return cargaMasivaVO;
	}

	@Override
	@Transactional
	public List<TablaDestinoVO> getOrdenInsercionTablas(Long idTipoLayout) throws BusinessException {
		List<TablaDestinoVO> tablasDestino = layoutDAO.getOrdenInsercionTablas(idTipoLayout);
		int tblFinal = tablasDestino.size() - 1;
		for (int i = 0; i < tablasDestino.size(); i++) {
			tablasDestino.get(i).setIsTblBaseFinal(Boolean.FALSE.booleanValue());
		}
		tablasDestino.get(tblFinal).setIsTblBaseFinal(Boolean.TRUE.booleanValue());

		return tablasDestino;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, InsercionTablaVO> getMoldesSQLPorTbl(Long idTipoLayout, List<TablaDestinoVO> tbls)
			throws BusinessException {

		if (tbls == null || tbls.isEmpty()) {
			throw new BusinessException(MSG_LAYOUT_SIN_ORDEN_INSERCION);
		}

		Map<String, InsercionTablaVO> insertQueriesMap = new HashMap<String, InsercionTablaVO>();
		for (TablaDestinoVO nbTbl : tbls) {
			InsercionTablaVO valInsertVO = getConcatNbCols(idTipoLayout, nbTbl.getNbTabla());
			String insertSQL = MessageFormat.format(MSG_INSERT_PATTERN, nbTbl.getNbTabla(), valInsertVO.getColumnas(),
					valInsertVO.getComodines());
			String selectSQL = MessageFormat.format(MSG_SELECT_PATTERN, valInsertVO.getCampoID().getNbColumna(),
					nbTbl.getNbTabla(), valInsertVO.getColumnaFiltro().getNbColumna(),
					valInsertVO.getColumnaFiltro().getTxValorDefecto());
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
	 * @throws BusinessException
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

	/**
	 * Concatena los nombres de las columnas tal como se nombraron en BD para formar
	 * parte de SQL insert
	 * 
	 * @param tabla
	 * @return
	 * @throws BusinessException
	 */
	private InsercionTablaVO getConcatNbCols(Long idTipoLayout, String tabla) throws BusinessException {

		List<ColumnaVO> cols = layoutDAO.getNbsColumnas(idTipoLayout, tabla);
		if (cols == null || cols.isEmpty()) {
			return null;
		}
		int totColumnasEnCsv = cols.size();
		InsercionTablaVO insertVO = new InsercionTablaVO();
		StringBuilder sbCols = new StringBuilder();
		StringBuilder sbComodines = new StringBuilder();
		ColumnaVO columnaFiltro = new ColumnaVO();
		ColumnaVO columnaID = new ColumnaVO();

		for (int i = 0; i < totColumnasEnCsv; i++) {
			ColumnaVO col = cols.get(i);
			if (i == BigDecimal.ZERO.intValue()) {
				columnaID.setNbColumna(col.getNbColumna());
				columnaID.setNuOrden(col.getNuOrden());
				columnaID.setCdTipo(col.getCdTipo());
				columnaID.setTxValorDefecto(getValorSQL(col));
			}
			sbCols.append(CARACTER_COMA).append(col.getNbColumna());
			sbComodines.append(CARACTER_COMA).append(getValorSQL(col));

			if (col.getStCampoFiltro() != null && col.getStCampoFiltro()) {
				columnaFiltro.setNbColumna(col.getNbColumna());
				columnaFiltro.setNuOrden(col.getNuOrden());
				columnaFiltro.setCdTipo(col.getCdTipo());
				columnaFiltro.setTxValorDefecto(getValorSQL(col));
			}
		}
		insertVO.setColumnas(sbCols.toString().replaceFirst(CARACTER_COMA, ""));
		insertVO.setComodines(sbComodines.toString().replaceFirst(CARACTER_COMA, ""));
		insertVO.setColumnaFiltro(columnaFiltro);

		insertVO.setCampoID(columnaID);
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
			} else if (col.getCdTipo().equals("Date")) {
				colValor = "TO_DATE(''{" + col.getNuOrden() + "}'' \\, ''" + col.getTxValorDefecto() + "'')";
			} else {
				colValor = "{" + col.getNuOrden() + "}";
			}
		}
		return colValor;

	}

}
