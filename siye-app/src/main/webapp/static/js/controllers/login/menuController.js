angular.module(appTeclo)
.controller("menuController",
function($rootScope,$scope,$location,$translate,$timeout,$injector,
		storageService,menuDinamicoService) {
	
	$rootScope.currentLanguage = $translate.use();
	
	$scope.loadedMenu = true;
	
//	Leer función al entrar al sistema
	$scope.menuActive = function(idModulo) {
		
		if($('body').hasClass('sidebar-collapse')){
			$('body').removeClass('sidebar-collapse');
		}
		
		angular.forEach($('.desplegar'), function(element, key){
			if(idModulo == $(element).attr('id')) {
				$('#'+idModulo).toggleClass('active-m');
				return false;
			}else{
				$(element).removeClass('active-m');
			}
		});
	}
	
//	Consumir servicio para obtener los módulos y servicios de la aplicación
	cargarMenuDinamico = function() {
		
		$.AdminLTE.layout.fix();
		
		menuDinamicoService.buscarMenuUsuario().success(function(data) {

			$rootScope.menuDinamico = configurarMenu(data);
			$scope.loadedMenu = false;
			
			$rootScope.menuToRefresh($rootScope.currentLanguage, false);
			$rootScope.validWish($rootScope.menuDinamico);
		
		}).error(function(data) {
			
		});
	}	

//	Configurar los módulo y servicios para pintarlos en el menu
	configurarMenu = function(menuUsuario) {
		
		var menus = []
		
		angular.forEach(menuUsuario, function(menu, key){
			if (menu.menuSuperior === 0) {
				
				menu.subMenus = [];
				
				angular.forEach(menuUsuario, function(subMenu, key){
					if (menu.id === subMenu.menuSuperior) {
						menu.subMenus.push(subMenu);
					}
			    });
				menus.push(menu);
			}
	    });
		return menus;
	};

//	Limpiar todo el rastro del menú al cerrar sesión
	$scope.limpiarCache = function() {
		angular.module(appTeclo)._invokeQueue.filter(function(comp){return comp[0] === '$provide'}).forEach(function(ctrl){
			$injector.invoke([ctrl[2][0], function(service){
				if (service.hasOwnProperty("destroy")) {
					service.destroy();
				}
			}]);			
		})		
	};

//	Obtener el submenu actual por medio del path para pintar el breadcrumbs
	$rootScope.menuToRefresh = function(lang, status) {
		
		var path 	  = $location.path();
		var son 	  = "/"+path.split("/")[1];
		var grandSon  = "/"+path.split("/")[2];
		var parameter = "/"+path.split("/")[3];
		
		angular.forEach($rootScope.menuDinamico, function(menu, key){
			
			menu.menuNombre = languageMenu(lang, menu);
			
			angular.forEach(menu.subMenus, function(subMenu, key){
								
				subMenu.subMenuNombre = languageMenu(lang, subMenu);
				
				switch(path) {
					case subMenu.menuUri :
					case subMenu.menuUri+grandSon :
					case subMenu.menuUri+parameter :
					case subMenu.menuUri+grandSon+parameter :
						$rootScope.breadCrumbs.icono = menu.menuIcono;
						$rootScope.breadCrumbs.modulo = menu.menuNombre;
						$rootScope.breadCrumbs.servicio = subMenu.subMenuNombre;
						
						if(!status) {
							$timeout(function(){
								
								$('#'+menu.id).trigger("click");
								
								if(grandSon != undefined){
									$('#'+subMenu.id).addClass("active-subm");
								}else{
									$('#'+subMenu.id).trigger("click");
								}
								
							}, 300);
							
							$scope.menuActive(menu.id);
						}
					break;
				}
			});
		});
		
		menuScroll();
		scrollWish();
	}

	languageMenu = function(lang, obj) {
		
		var name = "";
		
		switch (lang) {
			case "es_ES" :
				name = obj.menuTexto;
				break;
			case "en_US" :
				name = obj.menuTextoEn;
				break;
		}
		
		return name;
	}

//	Inicializar el scroll del menu
	menuScroll = function() {
		$(function(){
			$('#menuScroll').slimScroll({
				height: '100%',
		        color: '#00243c',
		        opacity: .3,
		        size: "4px",
		        alwaysVisible: false
		    });
		});
	}

//	Inicializar el scroll del wish
	scrollWish = function() {
		$(function(){
			$('#wishList').slimScroll({
				height: '100%',
		        color: '#55565a',
		        opacity: .5,
		        size: "5px",
		        alwaysVisible: true
		    });
		});
	}
	
	initController = function() {
	//	Validar si hay token
		if (storageService.getToken()) {
			cargarMenuDinamico();
		}
	}
	
	initController();
});