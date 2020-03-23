package mx.com.teclo.siye.negocio.service.menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.siye.persistencia.hibernate.dao.menu.MenuDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.perfil.PerfilDAO;
import mx.com.teclo.siye.persistencia.hibernate.dao.perfil.PerfilMenuDAO;
import mx.com.teclo.siye.persistencia.hibernate.dto.menu.MenuDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.perfil.PerfilDTO;
import mx.com.teclo.siye.persistencia.hibernate.dto.perfil.PerfilMenuDTO;
import mx.com.teclo.siye.persistencia.vo.menu.AccesosVO;
import mx.com.teclo.siye.persistencia.vo.menu.ConsultaMenuVO;
import mx.com.teclo.siye.persistencia.vo.menu.MenuVO;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDAO menuDAO;
	
	@Autowired
	private PerfilDAO perfilDAO;
	
	@Autowired
	private PerfilMenuDAO perfilMenuDAO;
	
	@Autowired
	UsuarioFirmadoService usuarioFirmadoService;

	@Value("${app.config.codigo}")
	private String codeApplication;

	@Override
	@Transactional(readOnly = true)
	public List<MenuVO> findMenusByProfile(Long profile, String cdApplication) {

		List<MenuDTO> menusDTO = null;
		List<MenuVO> menusVO = null;
		menusDTO = menuDAO.findMenusByProfile(profile, cdApplication);
		menusVO = ResponseConverter.converterLista(new ArrayList<MenuVO>(), menusDTO, MenuVO.class);

		return menusVO;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly = true)
	public Map consultarMenusPorPerfil(Long profile, String cdApplication) {

		List<MenuDTO> accesoActual = null;
		List<MenuDTO> listaAcceso = null;
		List<ConsultaMenuVO> menusConAcceso = null;
		List<ConsultaMenuVO> menusSinAcceso = null;
		Map<String, Object> mapa = new HashMap<String, Object>();
		accesoActual = menuDAO.findMenusByProfile(profile, cdApplication);
		listaAcceso = menuDAO.findMenusByProfileNotIn(profile, cdApplication);
		menusConAcceso = ResponseConverter.converterLista(new ArrayList<ConsultaMenuVO>(), accesoActual, ConsultaMenuVO.class);
		menusSinAcceso = ResponseConverter.converterLista(new ArrayList<ConsultaMenuVO>(), listaAcceso, ConsultaMenuVO.class);
		mapa.put("stActivo", perfilDAO.findUniqueProfileById(profile, codeApplication, true).getStActivo());
		mapa.put("menuConAcceso", menusConAcceso);
		mapa.put("menuSinAcceso", menusSinAcceso);
		return mapa;
	}
	
	@Override
	@Transactional
	public boolean autorizaTodoModulo(Long idPerfil){
		List<MenuDTO> listaMenus = menuDAO.findMenusByProfileNotIn(idPerfil, codeApplication);
		
		for(MenuDTO mDTO: listaMenus){
			autorizaModulo(idPerfil, mDTO.getIdMenu());
		}
		
		return true;
	}
	
	@Override
	@Transactional
	public boolean autorizaModulo(Long idPerfil, Long idMenu){
		MenuDTO acceso = menuDAO.findMenuByPerfil(idPerfil, idMenu, codeApplication);
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		if(acceso != null){
			//Si existe su registro, unicamente actualizar
			PerfilMenuDTO pmDTO = perfilMenuDAO.findProfilesByIds(idPerfil, idMenu);
			pmDTO.setStActivo(1);
			pmDTO.setIdUsrModifica(idUsuario);
			pmDTO.setFhModificacion(new Date());
			perfilMenuDAO.saveOrUpdate(pmDTO);
		}else{
			MenuDTO menuAsignar = menuDAO.findMenusById(idMenu, codeApplication);
			PerfilDTO perfilAsignar = perfilDAO.findProfileById(idPerfil, codeApplication, true);
			//Si no existe su registro, crearlo
			PerfilMenuDTO pmDTO = new PerfilMenuDTO();
			pmDTO.setIdPerfilMenu(perfilMenuDAO.findNextVal());
			pmDTO.setMenu(menuAsignar);
			pmDTO.setPerfil(perfilAsignar);
			pmDTO.setStActivo(1);
			pmDTO.setFhCreacion(new Date());
			pmDTO.setIdUsrCreacion(idUsuario);
			pmDTO.setFhModificacion(new Date());
			pmDTO.setIdUsrModifica(idUsuario);
			perfilMenuDAO.save(pmDTO);
		}
		return Boolean.TRUE;
	}
	
	@Override
	@Transactional
	public boolean revocaTodoModulo(Long idPerfil){
		List<MenuDTO> listaMenus = menuDAO.findMenusByProfile(idPerfil, codeApplication);
		
		for(MenuDTO mDTO: listaMenus){
			revocaModulo(idPerfil, mDTO.getIdMenu());
		}
		
		return true;
	}
	
	@Override
	@Transactional
	public boolean revocaModulo(Long idPerfil, Long idMenu){
		MenuDTO acceso = menuDAO.findMenuByPerfil(idPerfil, idMenu, codeApplication);
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		if(acceso != null){
			//Si existe su registro, unicamente actualizar
			PerfilMenuDTO pmDTO = perfilMenuDAO.findProfilesByIds(idPerfil, idMenu);
			pmDTO.setStActivo(0);
			pmDTO.setIdUsrModifica(idUsuario);
			pmDTO.setFhModificacion(new Date());
			perfilMenuDAO.saveOrUpdate(pmDTO);
		}
		return Boolean.TRUE;
	}
	
	@Override
	@Transactional
	public Boolean restablecerModulos(AccesosVO accesosVO){
		Boolean auto = false, revo = false;
		
		//Autoriza a los de la lista de autorizados
		for(MenuVO menuVO : accesosVO.getAutorizados()){
			auto = autorizaModulo(accesosVO.getIdPerfil(), menuVO.getIdMenu());
		}
		
		//Revoca a los de la lista de noAutorizados
		for(MenuVO menuVO : accesosVO.getNoAutorizados()){
			revo = revocaModulo(accesosVO.getIdPerfil(), menuVO.getIdMenu());
		}
		return auto & revo;
	}

	@Override
	@Transactional(readOnly = true)
	public MenuVO findMenusById(Long id, String cdApplication) {
		// TODO Auto-generated method stub
		return null;
	}

}
