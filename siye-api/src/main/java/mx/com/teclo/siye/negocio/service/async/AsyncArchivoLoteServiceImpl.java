/**
 * 
 */
package mx.com.teclo.siye.negocio.service.async;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.vo.async.ArchivoLoteVO;
import mx.com.teclo.siye.persistencia.vo.async.LineaCsvOdsVO;

@Service
public class AsyncArchivoLoteServiceImpl implements AsyncArchivoLoteService {
	private static final String MSG_ARCHIVO_NULO = "Archivo nulo";
	private static final String MSG_ARCHIVO_VACIO = "Archivo vac\u00EDo";
	private static final String MSG_ARCHIVO_CONTENT_TYPE_INVALIDO = "Extensi\u00F3n de archivo inv\u00E1lida";
	private static final String MSG_ARCHIVO_COLUMNAS_INVALIDAS = "Contenido inv\u00E1lido";
	private static final List<String> contentTypes = Arrays.asList("text/csv");

	@Override
	public Integer registrarArchivoLote(MultipartFile archivoLote) throws BusinessException {
		if (archivoLote == null) {
			throw new BusinessException(MSG_ARCHIVO_NULO);
		}
		if (archivoLote.isEmpty()) {
			throw new BusinessException(MSG_ARCHIVO_VACIO);
		}

		String fileContentType = archivoLote.getContentType();
		if (!contentTypes.contains(fileContentType)) {
			throw new BusinessException(MSG_ARCHIVO_CONTENT_TYPE_INVALIDO);
		}

		try {
			List<LineaCsvOdsVO> lineas = CsvUtils.read(LineaCsvOdsVO.class, archivoLote.getInputStream());
			return lineas.size();
		} catch (IOException e) {
			throw new BusinessException(MSG_ARCHIVO_COLUMNAS_INVALIDAS);
		}

	}

	@Override
	public void validarArchivoLote() throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesarArchivoLote() throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarArchivoLote() throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArchivoLoteVO getArchivoLote(Long idArchivoLote) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
