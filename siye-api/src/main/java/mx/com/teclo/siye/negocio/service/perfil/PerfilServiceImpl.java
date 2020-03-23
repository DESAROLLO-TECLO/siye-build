package mx.com.teclo.siye.negocio.service.perfil;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.perfil.PerfilDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.perfil.PerfilUsuarioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.usuario.AplicacionDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.usuario.UsuarioDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.perfil.PerfilDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.perfil.PerfilUsuarioDTO;
import mx.com.teclo.siye.persistencia.vo.perfil.ConsultaPerfilVO;
import mx.com.teclo.siye.persistencia.vo.perfil.PerfilVO;
import mx.com.teclo.siye.persistencia.vo.usuario.UsuarioVO;

@Service
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	private PerfilDAO perfilDAO;	
	@Autowired
	@Qualifier("aplicacionesDAOImpl")
	private AplicacionDAO aplicacionDAO;
	@Autowired
	private PerfilUsuarioDAO perfilUsuarioDAO;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	@Qualifier("usuariosDAO")
	private UsuarioDAO usuarioDAO;
	
	@Value("${app.config.codigo}")
    private String codeApplication;
    
	
	@Override
	@Transactional(readOnly = true)
	public List<PerfilVO> findProfilesByApp() {

		List<PerfilDTO> perfilesDTO = null;
		List<PerfilVO> profiles = new LinkedList<PerfilVO>();
		List<PerfilVO> resultQuery = new LinkedList<PerfilVO>();
		PerfilVO withoutProfile = new PerfilVO();

		withoutProfile.setIdPerfil(new Long(0));
		withoutProfile.setAplicacion(null);
		withoutProfile.setCdPerfil("SINPERFIL");
		withoutProfile.setNbPerfil("SIN PERFIL");
		withoutProfile.setTxPerfil("SIN PERFIL");
		withoutProfile.setStActivo(1);
		
		profiles.add(withoutProfile);

		perfilesDTO = perfilDAO.findProfilesByApp(codeApplication);
		resultQuery = ResponseConverter.converterLista(new ArrayList<>(), perfilesDTO, PerfilVO.class);

		profiles.addAll(resultQuery);
		return profiles;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ConsultaPerfilVO> consultaPerfiles(){
		List<PerfilDTO> perfilesDTO = perfilDAO.findAllProfilesByApp(codeApplication);
		List<PerfilVO> perfiles = ResponseConverter.converterLista(new ArrayList<UsuarioVO>(), perfilesDTO, PerfilVO.class);
		List<ConsultaPerfilVO> consultaPerfiles = ResponseConverter.converterLista(new ArrayList<UsuarioVO>(), perfiles, ConsultaPerfilVO.class);

		return consultaPerfiles;
	}

	@Override
	@Transactional
	public boolean savePerfil( PerfilVO perfilVO) {
		boolean isSaveProfile = false;
		//Implementacion
		return isSaveProfile;
		
	}
	
	@Override
	@Transactional
	public boolean habilitaDeshabilitaPerfil(Long idPerfil, Boolean activo){
		PerfilDTO pDTO = perfilDAO.findUniqueProfileById(idPerfil, codeApplication, true);
		pDTO.setStActivo(activo ? 1:0);
		perfilDAO.saveOrUpdate(pDTO);
		
		List<PerfilUsuarioDTO> usuarios = perfilUsuarioDAO.findAll();
		
		//Iteramos todos las relaciones de perfiles con usuarios, si dan positivo con el perfil
		//Se deshabilitan
		if(usuarios.size()>0){
			for(PerfilUsuarioDTO user: usuarios){
				if(user.getPerfil().getIdPerfil() == pDTO.getIdPerfil()){
					user.setStActivo(activo ? 1:0);
					//perfilUsuarioDAO.saveOrUpdate(user);
				}
			}
		}
		
		return pDTO.getStActivo() != (!activo ? 1:0) ? true:false;
	}
	
	
	@Override
	@Transactional
	public boolean crearPerfil(PerfilVO pVO){
		
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		Long idPerfil = perfilDAO.findNextValue();
		PerfilDTO pDTO = new PerfilDTO();
		pDTO.setIdPerfil(idPerfil);
		pDTO.setCdPerfil(pVO.getCdPerfil());
		pDTO.setNbPerfil(pVO.getNbPerfil());
		pDTO.setTxPerfil(pVO.getTxPerfil());
		pDTO.setAplicacion(aplicacionDAO.findAplicationById(codeApplication));
		pDTO.setStActivo(1);
		pDTO.setFhCreacion(new Date());
		pDTO.setIdUsrCreacion(idUsuario);
		pDTO.setFhModificacion(new Date());
		pDTO.setIdUsrModifica(idUsuario);
		
		perfilDAO.save(pDTO);
		
		//validamos que exista
		PerfilDTO check = perfilDAO.findProfileById(idPerfil, codeApplication, true);
		
		return check != null ? true:false;
	}
	
	
	@Override
	@Transactional
	public boolean updatePerfil(  PerfilVO perfilVO) {		
		boolean isUpdateProfile = false;
		//Implementacion
 		return isUpdateProfile;
		
	}

}