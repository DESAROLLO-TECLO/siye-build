package mx.com.teclo.siye.negocio.service.async;

import mx.com.teclo.siye.persistencia.vo.async.TipoLayoutVO;

/**
 * Administra la consulta del layout contra el que se validar&aacute; el archivo
 * lote
 * 
 * @author beatriz.orosio@unitis.com.mx
 *
 */
public interface LayoutService {

	TipoLayoutVO getLayoutVigente();
}
