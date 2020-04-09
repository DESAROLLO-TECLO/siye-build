package mx.com.teclo.siye.negocio.service.async;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.siye.persistencia.hibernate.dao.configuracion.ConfiguracionOSDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.configuracion.ConfiguracionOSDTO;
import mx.com.teclo.siye.persistencia.vo.async.TipoLayoutVO;

@Service
public class UploadServiceImpl implements UploadService {
	private static final String MSG_ARCHIVO_VACIO = "El archivo lote esta vac\u00EDo";
	private static final String MSG_ARCHIVO_CONTENT_TYPE_INVALIDO = "La extensi\u00F3n del archivo lote es inv\u00E1lida";

	private static final Long ID_CONTENT_TYPE = 6L;
	private static final String MSG_PARAM_CONTENT_TYPE_NULO = "El tipo de archivo esperado no esta definido";
	public static final String MSG_ERROR_LOTE_INEXISTENTE = "El lote {0} no existe";
	
	@Autowired
	private LayoutService layoutService;
	@Autowired
	private ConfiguracionOSDAO configuracionDAO;

	@Override
	@Transactional
	public void validarEstructuraBasica(MultipartFile archivoLote) throws BusinessException {
		if (archivoLote.isEmpty()) {
			throw new BusinessException(MSG_ARCHIVO_VACIO);
		}

		TipoLayoutVO layoutVigenteVO = layoutService.getLayoutVigente();

		if (layoutVigenteVO == null) {
			throw new BusinessException(LayoutServiceImpl.MSG_LAYOUT_VIGENTE_NULO);
		}

		ConfiguracionOSDTO contentTypeDTO = configuracionDAO.findOne(ID_CONTENT_TYPE);

		if (contentTypeDTO == null || StringUtils.isBlank(contentTypeDTO.getCdValorConfig())) {
			throw new BusinessException(MSG_PARAM_CONTENT_TYPE_NULO);
		}

		if (!archivoLote.getContentType().equalsIgnoreCase(contentTypeDTO.getCdValorConfig())) {
			throw new BusinessException(MSG_ARCHIVO_CONTENT_TYPE_INVALIDO);
		}

		if (!StringUtils.isBlank(layoutVigenteVO.getTxMascara())) {
			// TODO: validar formato del nombre del archivo
			// throw new BusinessException(MSG_ARCHIVO_REGEX_NAME_NULO);
		}

		if (layoutVigenteVO.getCdTamanioMax() != null
				&& (archivoLote.getSize() / (1024 * 1024)) > layoutVigenteVO.getCdTamanioMax()) {
			throw new BusinessException(LayoutServiceImpl.MSG_ARCHIVO_TAMANIO_REBASADO);
		}
	}

}
