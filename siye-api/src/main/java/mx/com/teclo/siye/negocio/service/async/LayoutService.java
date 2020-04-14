package mx.com.teclo.siye.negocio.service.async;

import java.util.List;
import java.util.Map;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.vo.async.ColumnaVO;
import mx.com.teclo.siye.persistencia.vo.async.ConfigCargaMasivaVO;
import mx.com.teclo.siye.persistencia.vo.async.InsercionTablaVO;
import mx.com.teclo.siye.persistencia.vo.async.TipoLayoutVO;

/**
 * Administra la alta, actualizaci&oacute;n y consulta de la estructura (layout)
 * y caracter&iacute;sticas (configuraci&oacute;n) que debe tener el archivo
 * enviado por la ORT para la carga masiva
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public interface LayoutService {

	/**
	 * Obtiene la configuraci&oacute;n vigente del archivo
	 * 
	 * @return TipoLayoutVO
	 */
	TipoLayoutVO getLayoutVigente();

	/**
	 * Obtiene todas las configuraciones requeridas para generar queries
	 * din&aacute;micos
	 * 
	 * @param idArchivoLote
	 * @return
	 * @throws BusinessException
	 */
	ConfigCargaMasivaVO getConfigCargaMasiva(Long idArchivoLote) throws BusinessException;

	/**
	 * Obtiene las columnas a nivel header, footer o detalle
	 * 
	 * @param idTipoLayout
	 * @param cdSeccion
	 * @return
	 */
	List<ColumnaVO> getSeccion(Long idTipoLayout, String cdSeccion);

	/**
	 * Regresa el conjunto de nombres de columnas en base de datos que ser&aacute;n
	 * afectadas por la carga masiva
	 * 
	 * @param idTipoLayout
	 * @param tabla
	 * @return
	 */
	InsercionTablaVO getNbsColumnas(String tabla);

	/**
	 * Obtiene los conjuntos de titulos de las columnas del header y footer y el
	 * conjunto de tipo de dato a recibir en las columnas del detalle
	 * 
	 * @param idTipoLayout
	 * @return
	 * @throws BusinessException
	 */
	Map<String, List<ColumnaVO>> getColumnasPorSeccion(Long idTipoLayout) throws BusinessException;

	/**
	 * Obtiene ordenadamente el arreglo de nombres de tablas a ser afectadas por la
	 * carga masiva
	 * 
	 * @return
	 * @throws BusinessException
	 */
	List<String> getOrdenInsercionTablas() throws BusinessException;

	/**
	 * Obtiene la bandera configurada para saber si se deben registrar archivos
	 * inv&aacute;lidos; por ejemplo archivos de diferente content type o
	 * vac&iacute;os
	 * 
	 * @return
	 */
	boolean getIsProcesoConRechazo();

	/**
	 * Genera de forma din&aacute;mica  los comandos SQL para insertar la informacion del archivo
	 * @return
	 * @throws BusinessException
	 */
	Map<String, InsercionTablaVO> getMoldesSQLPorTbl() throws BusinessException;

}
