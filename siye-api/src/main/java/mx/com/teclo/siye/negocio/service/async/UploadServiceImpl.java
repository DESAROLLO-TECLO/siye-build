package mx.com.teclo.siye.negocio.service.async;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.hibernate.dao.configuracion.ConfiguracionOSDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.configuracion.ConfiguracionOSDTO;
import mx.com.teclo.siye.persistencia.vo.async.ConfigLayoutVO;

@Service
public class UploadServiceImpl implements UploadService {
	private static final String MSG_ARCHIVO_VACIO = "El archivo lote esta vac\u00EDo";
	private static final String MSG_ARCHIVO_CONTENT_TYPE_INVALIDO = "La extensi\u00F3n del archivo lote es inv\u00E1lida";

	private static final Long ID_CONTENT_TYPE = 6L;
	private static final int MAX_LARGO_NOMBRE_ARCHIVO = 30;
	private static final String MSG_PARAM_CONTENT_TYPE_NULO = "El tipo de archivo esperado no esta definido";
	public static final String MSG_ERROR_LOTE_INEXISTENTE = "El lote {0} no existe";
	public static final String MSG_ERROR_NOMBRE_LARGO = "El nombre de archivo {0} excede los {1} caracteres";
	

	@Autowired
	private LayoutService layoutService;
	@Autowired
	private ConfiguracionOSDAO configuracionDAO;

	@Override
	@Transactional
	public void validarEstructuraBasica(MultipartFile archivoLote) throws BusinessException {

		// QUE NO ESTE VACIO
		if (archivoLote.isEmpty()) {
			throw new BusinessException(MSG_ARCHIVO_VACIO);
		}
		// QUE HAYA UNA CONFIGURACION VIGENTE CONTRA LA CUAL SE VALIDARA EL ARCHIVO
		ConfigLayoutVO layoutVigenteVO = layoutService.getLayoutVigente();

		if (layoutVigenteVO == null) {
			throw new BusinessException(LayoutServiceImpl.MSG_LAYOUT_VIGENTE_NULO);
		}

		// VALIDAR CONTENT TYPE
		validarContentType(archivoLote);

		// VALIDAR EL LARGO DEL NOMBRE
		if (archivoLote.getOriginalFilename().length() > MAX_LARGO_NOMBRE_ARCHIVO) {
			throw new BusinessException(MessageFormat.format(MSG_ERROR_NOMBRE_LARGO, archivoLote.getOriginalFilename(),
					MAX_LARGO_NOMBRE_ARCHIVO));
		}

		// VALIDAMOS QUE NO EXCEDA EN TAMANIO
		if (layoutVigenteVO.getCdTamanioMax() != null) {
			Long tamArchivoMB = archivoLote.getSize();
			Long tamMaxBD = layoutVigenteVO.getCdTamanioMax() * (1024L * 1024L);
			if (tamArchivoMB > tamMaxBD) {
				throw new BusinessException(MessageFormat.format(LayoutServiceImpl.MSG_ARCHIVO_TAMANIO_REBASADO,
						layoutVigenteVO.getCdTamanioMax()));
			}

		}
	}
	
	private void  validarContentType(MultipartFile archivoLote) throws BusinessException{
		List<String> tiposValidos = new ArrayList<>();
		
		ConfiguracionOSDTO contentTypeDTO = configuracionDAO.findOne(ID_CONTENT_TYPE);
		
		if (contentTypeDTO == null || StringUtils.isBlank(contentTypeDTO.getCdValorConfig())) {
			throw new BusinessException(MSG_PARAM_CONTENT_TYPE_NULO);
		}
		String contentValido = contentTypeDTO.getCdValorConfig();
		
		if(contentValido.indexOf(",")>=0) {
			String[] arrContentType = contentValido.split(",");
			for (String tipo : arrContentType) {
				tiposValidos.add(tipo.trim());
			}
		}
		
		if (tiposValidos.isEmpty() && !archivoLote.getContentType().equalsIgnoreCase(contentTypeDTO.getCdValorConfig())) {
			throw new BusinessException(MSG_ARCHIVO_CONTENT_TYPE_INVALIDO);
		}
		
		boolean isValido = false;
		
		if (!tiposValidos.isEmpty()) {			
			for (String tipo : tiposValidos) {
				if(archivoLote.getContentType().equalsIgnoreCase(tipo)) {
					isValido = true;
					break;				
				}
			}
			if(!isValido) {
				throw new BusinessException(MSG_ARCHIVO_CONTENT_TYPE_INVALIDO);	
			}
		}		
	}

}
