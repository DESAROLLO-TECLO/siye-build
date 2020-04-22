package mx.com.teclo.siye.persistencia.hibernate.dao.async;

import java.util.List;

import mx.com.teclo.siye.persistencia.vo.async.ColumnaArchivoVO;
import mx.com.teclo.siye.persistencia.vo.async.ColumnaVO;

/**
 * Recupera las columnas esperadas en el archivo de carga masiva
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public interface LayoutDAO {

	/**
	 * Obtiene las cabeceras, el detalle o el footer configurado del layout
	 * 
	 * @param idTipoLayout
	 * @param cdIndicadorReg indica la seccion a recuperar<br>
	 *                       (H=header, D=Detalle, F=footer)
	 * @return
	 */
	List<ColumnaVO> getLayout(Long idTipoLayout, String cdIndicadorReg);

	/**
	 * Obtiene ordenadamente los nombres de las columnas a afectar en el insert *
	 * @param idTipoLayout
	 * @param tabla
	 * @return
	 */
	List<ColumnaVO> getNbsColumnas(Long idTipoLayout, String tabla);

	/**
	 * Obtiene ordenadamente los nombres de las columnas a recibir en el archivo*
	 * 
	 * 
	 * @return List<ColumnaArchivoVO>
	 */
	List<ColumnaArchivoVO> getColumnasEnArchivo();

}
