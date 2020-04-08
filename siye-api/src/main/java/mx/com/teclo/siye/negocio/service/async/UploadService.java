package mx.com.teclo.siye.negocio.service.async;

import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;

public interface UploadService {
	/**
	 * Valida nombre, content type, tama&ntilde;o
	 * 
	 * @param archivoLote
	 * @throws BusinessException
	 */
	void validarEstructuraBasica(MultipartFile archivoLote) throws BusinessException;
}
