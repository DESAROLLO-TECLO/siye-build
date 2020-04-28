package mx.com.teclo.siye.negocio.service.catalogo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.catalogo.PersonaDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.proceso.CentroInstalacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.catalogo.PersonaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.IncidenciaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.incidencia.OdsIncidenciaDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.CentroInstalacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.KitInstalacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.LoteOrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OdsDetalleCambioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.OrdenServicioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.PlanDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.TipoVehiculoDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.proceso.VehiculoDTO;
import mx.com.teclo.siye.persistencia.vo.catalogo.PersonaVO;
import mx.com.teclo.siye.persistencia.vo.proceso.CentroInstalacionVO;
import mx.com.teclo.siye.persistencia.vo.proceso.OrdenServicioVO;

@Service
public class TblCatalogosServiceImpl implements TblCatalogosService{
	
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private CentroInstalacionDAO centroInstalacionDAO;
	
	@Autowired
	private PersonaDAO personaDAO;

	@Autowired
	private CatalogoService catalogoService;
 
	
	@Override
	@Transactional
	public String altaCentrodeInstalacion(CentroInstalacionVO centroInstalacionVO) throws BusinessException {
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
	
	
	@Transactional
	@Override
	public Boolean actualizaCentrodeInstalacion(CentroInstalacionVO centroInstalacionVO) throws NotFoundException, BusinessException{
		CentroInstalacionDTO centroInstalacionDTO = centroInstalacionDAO.centroIns(centroInstalacionVO.getIdCentroInstalacion());
		Boolean respuesta = false;	
		if(centroInstalacionDTO == null)
			throw new NotFoundException("El registro que intenta actualizar no existe");
		
		CentroInstalacionDTO centroInstalacionNuevo = new CentroInstalacionDTO();
		centroInstalacionNuevo.setIdCentroInstalacion(centroInstalacionDTO.getIdCentroInstalacion());
		centroInstalacionNuevo.setStCentroInstalcion(centroInstalacionDTO.getStCentroInstalcion());
		centroInstalacionNuevo.setFhCreacion(centroInstalacionDTO.getFhCreacion());
		centroInstalacionNuevo.setFhModificacion(new Date());
		centroInstalacionNuevo.setIdUsrCreacion(centroInstalacionDTO.getIdUsrCreacion());
		centroInstalacionNuevo.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		centroInstalacionNuevo.setStActivo(centroInstalacionVO.getStActivo());
		centroInstalacionNuevo.setStCentroInstalcion(centroInstalacionDTO.getStCentroInstalcion());
		centroInstalacionNuevo.setCdCentroInstalacion(centroInstalacionDTO.getCdCentroInstalacion());
		centroInstalacionNuevo.setNbCentroInstalacion(centroInstalacionVO.getNbCentroInstalacion());
		centroInstalacionNuevo.setNbCalle(centroInstalacionVO.getNbCalle());
		centroInstalacionNuevo.setNuExterior(centroInstalacionVO.getNuExterior());
		centroInstalacionNuevo.setNbEntreCalle(centroInstalacionVO.getNbEntreCalle());
		centroInstalacionNuevo.setNbYCalle(centroInstalacionVO.getNbYCalle());
		centroInstalacionNuevo.setNbColonia(centroInstalacionVO.getNbColonia());
		centroInstalacionNuevo.setNbAlcaldia(centroInstalacionVO.getNbAlcaldia());
		centroInstalacionNuevo.setNbDiasAtencion(centroInstalacionVO.getNbDiasAtencion());
		centroInstalacionNuevo.setHrAtencionIni(centroInstalacionVO.getHrAtencionIni());
		centroInstalacionNuevo.setHrAtencionFin(centroInstalacionVO.getHrAtencionFin());
		try {
			centroInstalacionDAO.update(centroInstalacionNuevo);
			respuesta = true;
		} catch (Exception e) {
			respuesta = false;
		}
		
	return respuesta;
	}
	
	@Transactional
	@Override
	public CentroInstalacionVO findCentroInstalacion(Long idCentroInstalacion) {
		CentroInstalacionVO centroInstalacionVO = new CentroInstalacionVO();
		CentroInstalacionDTO centroInstalacionDTO = centroInstalacionDAO.centroIns(idCentroInstalacion);
		ResponseConverter.copiarPropriedades(centroInstalacionVO, centroInstalacionDTO);
		return centroInstalacionVO;
	}
	
	
	@Override
	@Transactional
	public String altaPersona (PersonaVO personaVO) throws BusinessException {
		String respuesta = "";	
		Integer serial = personaDAO.getUltimoId() + 1;
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
		PersonaDTO personaDTO = new PersonaDTO();
		personaDTO.setNbPersona(personaVO.getNbPersona());
		personaDTO.setCdPersona("CDP"+serie);
		personaDTO.setNbPatPersona(personaVO.getNbPatPersona());
		personaDTO.setNbMatPersona(personaVO.getNbMatPersona());
		personaDTO.setNuOrden(serial);
		personaDTO.setFhCreacion(new Date());
		personaDTO.setFhModificacion(new Date());
		personaDTO.setIdUsrCreacion(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		personaDTO.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		personaDTO.setStActivo(true);
		personaDTO.setStPersona(1L);
		
		try {
			personaDAO.save(personaDTO);
			respuesta = "";
		} catch (Exception e) {
			respuesta = "Error al guardar el técnico. ";
		}
		if (respuesta == "") {
			respuesta = "Se guardo el técnico correctamente con folio: " +  personaDTO.getCdPersona();
		}  
		return respuesta;
	}
	
	
	@Transactional
	@Override
	public Boolean actualizaPersona (PersonaVO personaVO) throws NotFoundException, BusinessException{
		PersonaDTO personaDTO = personaDAO.findOne(personaVO.getIdPersona());
		Boolean respuesta = false;	
		if(personaDTO == null)
			throw new NotFoundException("El registro que intenta actualizar no existe");
		
		PersonaDTO personaNuevo = new PersonaDTO();
		
		personaNuevo.setNbPersona(personaVO.getNbPersona());
		personaNuevo.setCdPersona(personaDTO.getCdPersona());
		personaNuevo.setNbPatPersona(personaVO.getNbPatPersona());
		personaNuevo.setNbMatPersona(personaVO.getNbMatPersona());
		personaNuevo.setNuOrden(personaDTO.getNuOrden());
		personaNuevo.setFhCreacion(personaDTO.getFhCreacion());
		personaNuevo.setFhModificacion(new Date());
		personaNuevo.setIdUsrCreacion(personaDTO.getIdUsrCreacion());
		personaNuevo.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		personaNuevo.setStActivo(personaDTO.getStActivo());
		personaNuevo.setStPersona(personaDTO.getStPersona());
		
		try {
			personaDAO.update(personaNuevo);
			respuesta = true;
		} catch (Exception e) {
			respuesta = false;
		}
		
	return respuesta;
	}
	
	@Transactional
	@Override
	public PersonaVO findPersona(Integer idPersona) {
		PersonaVO personaVO = new PersonaVO();
		PersonaDTO personaDTO = personaDAO.obtenerPersonaId(idPersona);
		ResponseConverter.copiarPropriedades(personaVO, personaDTO);
		return personaVO;
	}
}
