package mx.com.teclo.siye.negocio.service.catalogo;


import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.CentroInstalacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.CentroInstalacionDTO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;

@Service
public class TblCatalogosServiceImpl implements TblCatalogosService{
	
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private CentroInstalacionDAO centroInstalacionDAO;

 
	
	@Override
	@Transactional
	public String altaCentrodeInstalacion(@RequestBody CentroInstalacionVO centroInstalacionVO) throws BusinessException {
		String respuesta = "";	
		Long serial = centroInstalacionDAO.getUltimoId() + 1;
		String serie = "";
		if  (serial < 10) {
			serie = "00000" + serial;
		}
		if  (serial < 100 && serial > 9) {
			serie = "0000" + serial;
		}
		if  (serial < 1000 && serial > 99) {
			serie = "000" + serial;
		}
		if  (serial < 1000 && serial > 999) {
			serie = "00" + serial;
		}
		if  (serial < 10000 && serial > 9999) {
			serie = "0" + serial;
		}
		if  (serial < 100000&& serial > 99999) {
			serie = "" + serial;
		}
		CentroInstalacionDTO centroInstalacionDTO = new CentroInstalacionDTO();
		centroInstalacionDTO.setStCentroInstalcion(1L);
		centroInstalacionDTO.setFhCreacion(new Date());
		centroInstalacionDTO.setFhModificacion(new Date());
		centroInstalacionDTO.setIdUsrCreacion(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		centroInstalacionDTO.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		centroInstalacionDTO.setStActivo(true);
		centroInstalacionDTO.setStCentroInstalcion(serial);
		centroInstalacionDTO.setCdCentroInstalacion("CYCN-" + serie);
		centroInstalacionDTO.setNbCentroInstalacion(centroInstalacionVO.getNbCentroInstalacion());
		centroInstalacionDTO.setNbCalle(centroInstalacionVO.getNbCalle());
		centroInstalacionDTO.setNuExterior(centroInstalacionVO.getNuExterior());
		centroInstalacionDTO.setNbEntreCalle(centroInstalacionVO.getNbEntreCalle());
		centroInstalacionDTO.setNbYCalle(centroInstalacionVO.getNbYCalle());
		centroInstalacionDTO.setNbColonia(centroInstalacionVO.getNbColonia());
		centroInstalacionDTO.setNbAlcaldia(centroInstalacionVO.getNbAlcaldia());
		centroInstalacionDTO.setNbDiasAtencion(centroInstalacionVO.getNbDiasAtencion());
		centroInstalacionDTO.setHrAtencionIni(centroInstalacionVO.getHrAtencionIni());
		centroInstalacionDTO.setHrAtencionFin(centroInstalacionVO.getHrAtencionFin());
		try {
			centroInstalacionDAO.save(centroInstalacionDTO);
			respuesta = "";
		} catch (Exception e) {
			respuesta = "Error al guardar el centro de instalacion. ";
		}
		if (respuesta == "") {
			respuesta = "Se guardo el centro de instalacion correctamente con folio: " +  centroInstalacionDTO.getCdCentroInstalacion();
		}  
		return respuesta;
	}
	

}
