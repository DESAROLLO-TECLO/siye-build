package mx.com.teclo.siye.persistencia.hibernate.dao.menu;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.siye.persistencia.hibernate.dto.menu.MenuDTO;

/**
 * Copyright (c) 2018, Teclo Mexicana.
 * 
 * Descripcion : UsuarioService Historial de Modificaciones : Descripcion del
 * Cambio : Creacion
 * 
 * @author : fjmb
 * @version : 1.0 Fecha : 05/Diciembre/2018
 */

@SuppressWarnings("unchecked")
@Repository
public class MenuDAOImpl extends BaseDaoHibernate<MenuDTO> implements MenuDAO {

	@Override
	public List<MenuDTO> findMenusByProfile(Long profile, String cdApplication) {
		List<MenuDTO> menus = null;
		Criteria query = getCurrentSession().createCriteria(MenuDTO.class);
		query.createAlias("perfiles", "perfiles");
		query.createAlias("perfiles.perfil", "perfil");
		query.createAlias("perfil.aplicacion", "aplicacionPerfil");
		query.createAlias("aplicacion", "aplicacionMenu");
 
		query.add(Restrictions.eq("perfiles.stActivo", 1));
		query.add(Restrictions.eq("perfil.idPerfil", profile));
		query.add(Restrictions.eq("aplicacionPerfil.cdAplicacion", cdApplication));
		query.add(Restrictions.eq("aplicacionMenu.cdAplicacion", cdApplication));

		menus = (List<MenuDTO>) query.list();

		return menus;

	}
	
	@Override
	public List<MenuDTO> findMenusByProfileNotIn(Long profile, String cdApplication) {
		List<MenuDTO> menus = findMenusByProfile(profile, cdApplication);
		
		Criteria query = getCurrentSession().createCriteria(MenuDTO.class);
		query.createAlias("aplicacion", "aplicacionMenu");
		
		query.add(Restrictions.eq("stActivo", Boolean.TRUE));
		query.add(Restrictions.eq("aplicacionMenu.cdAplicacion", cdApplication));
		
		if(menus.size()>0){
			query.add(Restrictions.not(Restrictions.in("idMenu", extraerIdMenus(menus)))); 
		}
		
		return (List<MenuDTO>) query.list();

	}

	

	@Override
	public MenuDTO findMenusById(Long menuId, String cdApplication) {
		Criteria query = getCurrentSession().createCriteria(MenuDTO.class);
		query.createAlias("aplicacion", "aplicacionMenu");
		
		query.add(Restrictions.eq("stActivo", Boolean.TRUE));
		query.add(Restrictions.eq("idMenu",menuId));		
		query.add(Restrictions.eq("aplicacionMenu.cdAplicacion", cdApplication));
		query.addOrder(Order.asc("nuMenuOrden"));

		return (MenuDTO) query.uniqueResult();
	}
	
	@Override
	public MenuDTO findMenusByName(String menu, String cdApplication) {
		Criteria query = getCurrentSession().createCriteria(MenuDTO.class);
		query.createAlias("aplicacion", "aplicacionMenu");
		
		query.add(Restrictions.eq("stActivo", new Integer(1)));
		query.add(Restrictions.eq("nbMenuUrl",menu));		
		query.add(Restrictions.eq("aplicacionMenu.cdAplicacion", cdApplication));
		query.addOrder(Order.asc("nuMenuOrden"));

		return (MenuDTO) query.uniqueResult();
	}
	
	@Override
	public MenuDTO findMenuByPerfil(Long idPerfil, Long idMenu, String cdApplication){
		Criteria query = getCurrentSession().createCriteria(MenuDTO.class);
		query.createAlias("perfiles", "perfiles");
		query.createAlias("perfiles.perfil", "perfil");
		query.createAlias("perfiles.menu", "menu");
		query.createAlias("perfil.aplicacion", "aplicacionPerfil");
		query.createAlias("aplicacion", "aplicacionMenu");
 
		query.add(Restrictions.eq("stActivo", Boolean.TRUE));
		query.add(Restrictions.eq("perfil.idPerfil", idPerfil));
		query.add(Restrictions.eq("menu.idMenu", idMenu));
		query.add(Restrictions.eq("aplicacionPerfil.cdAplicacion", cdApplication));
		query.add(Restrictions.eq("aplicacionMenu.cdAplicacion", cdApplication));

		return (MenuDTO) query.uniqueResult();
	}

	private Long[] extraerIdMenus(List<MenuDTO> menus){
		Long[] x = new Long[menus.size()];
		for(int i=0;i<menus.size();i++){
			x[i]=menus.get(i).getIdMenu();
		}
		return x;
	}
}
