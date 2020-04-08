package mx.com.teclo.siye.negocio.service.async;

import org.hibernate.HibernateException;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.vo.async.ConfigCargaMasivaVO;

public interface CargaMasivaService {
	
	/**
	 * Registra al lote el tipo de layout que le ser&aacute; aplicado
	 * @throws BusinessException
	 */
	void iniciarCargaMasiva(Long idArchivoLote) throws BusinessException;
	
	/**
	 * 
	 * @param config
	 */
	void procesarLineas(ConfigCargaMasivaVO config);

	/**
	 * Recibe un query insert para ejecutarlo
	 * 
	 * @param insertQuery
	 * @param linea       Valores tomada del archivo lote
	 * @return
	 * @throws BusinessException
	 */
	Long insertarEnTablas(ConfigCargaMasivaVO config, String linea) throws BusinessException, HibernateException;
}
