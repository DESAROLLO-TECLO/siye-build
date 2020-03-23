package mx.com.teclo.siye.negocio.service.usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.persistencia.password.vo.ResponseVO;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.perfil.PerfilUsuarioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.usraplicacion.UsrAplicacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.usuario.UsuarioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.perfil.PerfilDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.perfil.PerfilUsuarioDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.AplicacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.UsrAplicacionDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.usuario.UsuarioDTO;
import mx.com.teclo.siye.persistencia.vo.perfil.PerfilVO;
import mx.com.teclo.siye.persistencia.vo.usuario.UsuarioVO;
import mx.com.teclo.siye.util.comun.FilterValuesVO;
import mx.com.teclo.siye.util.enumerados.PerfilesEnum;


/**
 *  Copyright (c) 2018, Teclo Mexicana. 
 * 
 *  Descripcion					: UsuarioServiceImpl
 *  Historial de Modificaciones	: 
 *  Descripcion del Cambio 		: Creacion
 *  @author 					: fjmb
 *  @version 					: 1.0 
 *  Fecha 						: 05/Diciembre/2018
 */

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	@Qualifier("usuariosDAO")
	private UsuarioDAO usuarioDAO;

	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private PerfilUsuarioDAO perfilUsuarioDAO;

	@Autowired
	private UsrAplicacionDAO usrAplicacionDAO;

	@Value("${app.config.codigo}")
	private String codeApplication;
	
	@Override
	@Transactional(readOnly = true)
	public UsuarioVO findUserById(Long id, String cdApplication) {
		UsuarioVO usuarioVO = null;
		UsuarioDTO usuarioDTO = null;
		usuarioDTO = usuarioDAO.findUserById(id, cdApplication);
	    usuarioVO = ResponseConverter.copiarPropiedadesFull(usuarioDTO, UsuarioVO.class);

	    return usuarioVO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioVO> findUsersByParams(String parameter, String value) {
		List<UsuarioDTO> usuariosDTO = null;
		List<UsuarioVO> usuariosVO = new ArrayList<UsuarioVO>();
		PerfilVO withoutProfile = new PerfilVO();
		PerfilVO profile = new PerfilVO();

		withoutProfile.setIdPerfil(new Long(0));
		withoutProfile.setAplicacion(null);
		withoutProfile.setCdPerfil(PerfilesEnum.SIN_PERFIL.getCdPerfil());
		withoutProfile.setNbPerfil(PerfilesEnum.SIN_PERFIL.getNbPerfil());
		withoutProfile.setTxPerfil(PerfilesEnum.SIN_PERFIL.getTxPerfil());
		withoutProfile.setStActivo(1);
		usuariosDTO = usuarioDAO.findUsersByParams(parameter, value, codeApplication, true);
		for (UsuarioDTO usuarioDTO : usuariosDTO) {
			UsuarioVO usuarioVO = ResponseConverter.copiarPropiedadesFull(usuarioDTO, UsuarioVO.class);
			if(usuarioDTO.getPerfilUsuario().isEmpty()) {
				profile = withoutProfile;
				}else {
					for(PerfilUsuarioDTO puDTO : usuarioDTO.getPerfilUsuario()) {
						if(puDTO.getPerfil().getAplicacion().getCdAplicacion().equals(codeApplication)) {
							profile = ResponseConverter.copiarPropiedadesFull(puDTO.getPerfil(), PerfilVO.class);
							break;
							}
						}
					}
			usuarioVO.setPerfil(profile);
			usuariosVO.add(usuarioVO);
			}
		return usuariosVO;
		}

	@Override
	@Transactional
	public Boolean saveOrUpdateUser(UsuarioVO userVO, String action) throws NotFoundException {

		if(action.equals("new")) {

			boolean isExistUser = false;
			isExistUser = usuarioDAO.isUserExist(userVO, action);
			if(isExistUser) {
				throw new NotFoundException("Ya hay un registro existente con los mismo datos proporcionados");
			}
		}

		UsuarioDTO userDTO = null;
		PerfilUsuarioDTO profileUserDTO = null;
		UsrAplicacionDTO userAplicationDTO = null;
		PerfilDTO profileDTO = new PerfilDTO();
		AplicacionDTO applicationDTO = new AplicacionDTO();

		List<PerfilUsuarioDTO> profileUserList = new ArrayList<PerfilUsuarioDTO>();
		List<UsrAplicacionDTO> userAplicationList = new ArrayList<UsrAplicacionDTO>();

		userAplicationDTO = usrAplicacionDAO.findUserAplication(codeApplication, userVO.getIdUsuario());

		if(userAplicationDTO != null) {

			if(!userAplicationDTO.getUsuario().getPerfilUsuario().isEmpty()) {
				Long idProfileOld = 0L;
				for(PerfilUsuarioDTO puDTO : userAplicationDTO.getUsuario().getPerfilUsuario()) {
					if(puDTO.getPerfil().getAplicacion().getCdAplicacion().equals(codeApplication)) {
						idProfileOld = puDTO.getPerfil().getIdPerfil();
						break;
					}
				}
				profileUserDTO = perfilUsuarioDAO.getPerfilUsuario(userVO.getIdUsuario(), idProfileOld);
			} else {
				profileUserDTO = new PerfilUsuarioDTO();
			}

		}

		if(!userVO.getPerfil().getNbPerfil().equals(PerfilesEnum.SIN_PERFIL.getNbPerfil())) {
			ResponseConverter.copiarPropriedades(profileDTO, userVO.getPerfil());
			ResponseConverter.copiarPropriedades(applicationDTO, userVO.getPerfil().getAplicacion());
		} else {
			applicationDTO = usrAplicacionDAO.getApplicationByCode(codeApplication);
			profileDTO = null;

			if(action.equals("edit") ) {
				profileUserDTO.setStActivo(0);
			}
		}

		if(profileUserDTO == null && userAplicationDTO == null) {

			// NEW
			profileUserDTO = new PerfilUsuarioDTO();
			userAplicationDTO = new UsrAplicacionDTO();

			userDTO = ResponseConverter.copiarPropiedadesFull(userVO, UsuarioDTO.class);

			if(profileDTO != null) {
				profileUserDTO.setPerfil(profileDTO);
			}

			profileUserDTO.setUsuario(userDTO);
			profileUserDTO.setIdUsrCreacion(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			profileUserDTO.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			profileUserDTO.setFhCreacion(new Date());
			profileUserDTO.setFhModificacion(new Date());
	 		profileUserDTO.setStActivo(1);

	 		userAplicationDTO.setAplicacion(applicationDTO);
	 		userAplicationDTO.setUsuario(userDTO);
			userAplicationDTO.setIdUsrCreacion(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			userAplicationDTO.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			userAplicationDTO.setStActivo(true);
			userAplicationDTO.setFhCreacion(new Date());
			userAplicationDTO.setFhModificacion(new Date());

			userDTO.setIdUsrCreacion(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			userDTO.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			userDTO.setFhCreacion(new Date());
			userDTO.setFhModificacion(new Date());
			userDTO.setNbContrasenia(usuarioDAO.encriptarCampo(userVO.getNbContrasenia()));
			userDTO.setFhModifContrasenia(new Date());
			userDTO.setStContrasenia(1);

		} else {

			ResponseVO response = new ResponseVO();

			boolean isExistUser = false;
			boolean isDifferentUser = (userAplicationDTO.getUsuario().getNbUsuario().equals(userVO.getNbUsuario())
					&& userAplicationDTO.getUsuario().getNbApaterno().equals(userVO.getNbApaterno())
					&& userAplicationDTO.getUsuario().getNbAmaterno().equals(userVO.getNbAmaterno()))
					? false
					: true;

			if(isDifferentUser) {

				isExistUser = usuarioDAO.isUserExist(userVO, action);

				if(isExistUser) {
					response.setMessage("El usuario ya existe");
					throw new NotFoundException(response.getMessage());
				}
			}

			// EDIT
			userDTO = userAplicationDTO.getUsuario();

			profileUserDTO.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			profileUserDTO.setFhModificacion(new Date());

			if(profileUserDTO.getUsuario() == null) {
				profileUserDTO.setUsuario(userDTO);
				profileUserDTO.setIdUsrCreacion(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
				profileUserDTO.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
				profileUserDTO.setFhCreacion(new Date());
				profileUserDTO.setFhModificacion(new Date());
		 		profileUserDTO.setStActivo(1);
			}

			if(profileDTO != null) {
				profileUserDTO.setPerfil(profileDTO);
				profileUserDTO.setStActivo(1);
			}

			userAplicationDTO.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			userAplicationDTO.setFhModificacion(new Date());

			userDTO.setFhModificacion(new Date());
			userDTO.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			userDTO.setLbFoto(userVO.getLbFoto());
			userDTO.setNbUsuario(userVO.getNbUsuario());
			userDTO.setNbAmaterno(userVO.getNbAmaterno());
			userDTO.setNbApaterno(userVO.getNbApaterno());
			userDTO.setNbContrasenia(userVO.getNbContrasenia());
			userDTO.setNbEmail(userVO.getNbEmail());
			userDTO.setNuTelefono(userVO.getNuTelefono());
			userDTO.setStActivo(userVO.getStActivo());

		}
		if(profileDTO != null) {
			profileUserList.add(profileUserDTO);
			userDTO.setPerfilUsuario(profileUserList);
		}
		userAplicationList.add(userAplicationDTO);
		userDTO.setUsuarioAplicacion(userAplicationList);
		usuarioDAO.saveOrUpdate(userDTO);
		return true;
	}
	
	@Override
	public List<FilterValuesVO> getQueryParamsUser() {

		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();

		filterValues.add(new FilterValuesVO(1, "TODOS", "TODOS"));
		filterValues.add(new FilterValuesVO(2, "USERNAME", "USUARIO"));
		filterValues.add(new FilterValuesVO(3, "NOMBRE", "NOMBRE(S)"));
		filterValues.add(new FilterValuesVO(4, "APELLIDO", "APELLIDO(S)"));
		filterValues.add(new FilterValuesVO(5, "PERFIL", "PERFIL"));

		return filterValues;
	}
	
	@Override
	public List<FilterValuesVO> getQueryParamsAll() {

		List<FilterValuesVO> filterValuesForAll = new ArrayList<FilterValuesVO>();

		filterValuesForAll.add(new FilterValuesVO(1, "ACTIVES", "ACTIVOS"));
		filterValuesForAll.add(new FilterValuesVO(2, "INACTIVES", "INACTIVOS"));
		filterValuesForAll.add(new FilterValuesVO(3, "ALLSTATUS", "ACTIVOS/INACTIVOS"));

		return filterValuesForAll;
	}

	@Override
	@Transactional
	public boolean changeStatusUser(UsuarioVO usuarioVO ) {
		boolean isSaveUser = false;
		UsuarioDTO usuarioDTO = null;
		usuarioDTO = usuarioDAO.findUserByUserName(usuarioVO.getCdUsuario(), codeApplication, true, false);
		
		int status = usuarioDTO.getStActivo() != 0 ? 0 : 1;
		
		usuarioDTO.setStActivo(status);
		usuarioDTO.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		usuarioDTO.setFhModificacion(new Date());
		
		if (usuarioDTO != null) {
			isSaveUser = true;
			usuarioDAO.saveOrUpdate(usuarioDTO);
			usuarioVO.setFhModificacion(usuarioDTO.getFhModificacion());
		}
		return isSaveUser;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean findExistentUser(Map<String, Object> parameters) {
		
		boolean isExistUser = false;
		List<UsuarioDTO> usuarios = null;
		usuarios = usuarioDAO.findExistentUser(parameters);
		if (usuarios != null)
			isExistUser = true;

		return isExistUser;
	}

	@Override
	@Transactional()
	public boolean resetPassword(String username) {
		boolean isExistUser = false;
		UsuarioDTO usuarioDTO = null;
		usuarioDTO = usuarioDAO.findUserByUserName(username, codeApplication, true, true);
		usuarioDTO.setNbContrasenia(usuarioDAO.encriptarCampo(usuarioDTO.getCdUsuario()));
 		usuarioDAO.saveOrUpdate(usuarioDTO);
 		if (usuarioDTO.getIdUsuario() != null)
 			isExistUser = true;

		return isExistUser;
	}

	@Override
	@Transactional
	public List<UsuarioVO> findUsersByProfile(String cdProfile) {

		List<UsuarioDTO> usuariosDTO = null;
		List<UsuarioVO> usuariosVO = new ArrayList<UsuarioVO>();
		PerfilVO withoutProfile = new PerfilVO();
		PerfilVO profile = new PerfilVO();

		withoutProfile.setIdPerfil(new Long(0));
		withoutProfile.setAplicacion(null);
		withoutProfile.setCdPerfil(PerfilesEnum.SIN_PERFIL.getCdPerfil());
		withoutProfile.setNbPerfil(PerfilesEnum.SIN_PERFIL.getNbPerfil());
		withoutProfile.setTxPerfil(PerfilesEnum.SIN_PERFIL.getTxPerfil());
		withoutProfile.setStActivo(1);

		usuariosDTO = usuarioDAO.findUsersByProfile(cdProfile, codeApplication, true);

		for (UsuarioDTO usuarioDTO : usuariosDTO) {

			UsuarioVO usuarioVO = ResponseConverter.copiarPropiedadesFull(usuarioDTO, UsuarioVO.class);

			if(cdProfile.endsWith(PerfilesEnum.SIN_PERFIL.getCdPerfil())) {
				if(usuarioDTO.getPerfilUsuario().isEmpty()
					|| usuarioDTO.getPerfilUsuario().get(0).getStActivo().equals(0)) {

					usuarioVO.setPerfil(withoutProfile);
					usuariosVO.add(usuarioVO);

				}
			} else {
				profile = ResponseConverter.copiarPropiedadesFull(usuarioDTO.getPerfilUsuario().get(0).getPerfil(), PerfilVO.class);

				usuarioVO.setPerfil(profile);
				usuariosVO.add(usuarioVO);
			}
		}

		return usuariosVO;
	}
	
	@Override
	@Transactional
	public String toggleEncryption(String password, String action) {
		
		switch(action) {
			case "encrypt":
				password = usuarioDAO.encriptarCampo(password);
				break;
			case "decrypt":
				password = usuarioDAO.desencriptarCampo(password);
				break;
			default:
				break;
		}

		return password;
	}

	@Transactional
	@Override
	public UsuarioVO getUserByCd(String cdPlaca) {
		UsuarioDTO usr = usuarioDAO.getUserByCd(cdPlaca);
		UsuarioVO usuarioVO = null;
	    usuarioVO = ResponseConverter.copiarPropiedadesFull(usr, UsuarioVO.class);
	    if(usuarioVO.getPerfil() == null) {
	    	PerfilVO withoutProfile = new PerfilVO();
			withoutProfile.setIdPerfil(new Long(0));
			withoutProfile.setAplicacion(null);
			withoutProfile.setCdPerfil(PerfilesEnum.SIN_PERFIL.getCdPerfil());
			withoutProfile.setNbPerfil(PerfilesEnum.SIN_PERFIL.getNbPerfil());
			withoutProfile.setTxPerfil(PerfilesEnum.SIN_PERFIL.getTxPerfil());
			withoutProfile.setStActivo(1);
			usuarioVO.setPerfil(withoutProfile);
	    }
	    return usuarioVO;
	}
}
