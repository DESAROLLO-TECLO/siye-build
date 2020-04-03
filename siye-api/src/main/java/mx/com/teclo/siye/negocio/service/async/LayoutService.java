package mx.com.teclo.siye.negocio.service.async;

import java.util.List;

import mx.com.teclo.siye.persistencia.vo.async.ColumnaVO;
import mx.com.teclo.siye.persistencia.vo.async.InsercionTablaVO;
import mx.com.teclo.siye.persistencia.vo.async.TipoLayoutVO;

/**
 * Administra la consulta del layout contra el que se validar&aacute; el archivo
 * lote
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public interface LayoutService {

	/**
	 * Obtiene el conjunto de columnas esperadas en el archivo
	 * 
	 * @return
	 */
	TipoLayoutVO getLayoutVigente();

	List<ColumnaVO> getLayout(Long idTipoLayout, String cdSeccion);

	/**
	 * Regresa los nombres de columnas que deber&aacute;n ser afectadas en la carga
	 * masiva
	 * 
	 * @param idTipoLayout
	 * @param tabla
	 * @return
	 */
	String getNbsColumnas(String tabla);

	/**
	 * Regresa el patr&oacute;n para formar el query de valores a insertar
	 * 
	 * @param tabla
	 * @return
	 */
	InsercionTablaVO getPatronValues(String tabla);
}
