angular.module(appTeclo)
.controller('configuracionAppController',
function($rootScope,$scope,$location,$document,$timeout,
		storageService,configAppService,showAlert,ModalService) {
	
	var idSelect = {}; //
	
	$rootScope.configuracionTMP = {}; //objeto temporal donde se guarda la configuración obtenida de localStorage
	
	$scope.idResolucion			= 0; //Int que inicializa el id de la resolución
	$scope.idTema				= 0; //Int que inicializa el id de la resolución
	$scope.configuracionVO 		= {}; //Objeto donde se guardará la configuración a enviar
	$scope.imgLogos				= []; //Arreglo temporal para almacenar las imágenes cargadas
	$scope.tema					= []; //Arreglo donde se guardará el catálogo de temas
	$scope.resolucion			= []; //Arreglo donde se guardará el catpalogo de resoluciones
	
	$scope.b_logoMenuPrincipal	= false; //Boolean: Bandera que itera para mostrar la carga o visualización del logoPrincipal
	$scope.b_logoMenuSecundario = false; //Boolean: Bandera que itera para mostrar la carga o visualización del logoSecundario
	$scope.b_logoHeader			= false; //Boolean: Bandera que itera para mostrar la carga o visualización del logoHeader
	$scope.b_logoIndex			= false; //Boolean: Bandera que itera para mostrar la carga o visualización del logoIndex
	
	$scope.configuracionVO.logoMenuPrincipal  = null; //String donde se alamcenará el arreglo de bytes del logoPrincipal
	$scope.configuracionVO.logoMenuSecundario = null; //String donde se alamcenará el arreglo de bytes del logoSecundario
	$scope.configuracionVO.logoHeader		  = null; //String donde se alamcenará el arreglo de bytes del logoHeader
	$scope.configuracionVO.logoIndex		  = null; //String donde se alamcenará el arreglo de bytes del logoIndex
	
//	Opciones del webuiPopover de las imágenes
	$scope.opcionesImg = {
		backdrop: false
	}
	
//	INICIALIZAR AL ACCEDER A LA VISTA DEL CONTROLLER
	initController = function() {
		
		$('.popoverTemas').webuiPopover({
			trigger: 'click',
			delay: {
				show: null,
				hide: null
			},
			animation : 'pop'
		});
		
		$scope.consultarConfiguracion();
	}
	
//	CONSULTAR CONFIGURACION
	$scope.consultarConfiguracion = function() {
		
		$rootScope.mostrarConfigResolucion = false;
		$rootScope.configuracion 		   = false;
		
		angular.copy($rootScope.configuracionApp, $rootScope.configuracionTMP);
		
		storageService.setConfigurationTmp($rootScope.configuracionTMP);
		
		$scope.configuracionVO = $rootScope.configuracionTMP;
		$scope.idResolucion	   = $scope.configuracionVO.resolucion.idResolucion;
		$scope.idTema		   = $scope.configuracionVO.tema.idTema;

		$scope.logoMenuPrincipal  = $scope.configuracionVO.logoMenuPrincipal;
		$scope.logoMenuSecundario = $scope.configuracionVO.logoMenuSecundario;
		$scope.logoHeader 		  = $scope.configuracionVO.logoHeader;
		$scope.logoIndex		  = $scope.configuracionVO.logoIndex;
		
		comprobarLogosStock();
	}	
	
	// Combrobar la existencia de logos cargados para validar en la vista 
	comprobarLogosStock = function() {
		
		if($scope.logoMenuPrincipal != null) {
			$scope.b_logoMenuPrincipal = true;
		}else{
			$scope.b_logoMenuPrincipal = false;
		}
		
		if($scope.logoMenuSecundario != null) {
			$scope.b_logoMenuSecundario = true;
		}else{
			$scope.b_logoMenuSecundario = false;
		}
		
		if($scope.logoHeader != null) {
			$scope.b_logoHeader = true;
		}else{
			$scope.b_logoHeader = false;
		}
		
		if($scope.logoIndex != null) {
			$scope.b_logoIndex = true;
		}else{
			$scope.b_logoIndex = false;
		}
		
		obtenerTemas();
		obtenerResoluciones();
	}
	
//	OBTENER CATÁLOGO DE TEMAS
	obtenerTemas = function() {
		
		configAppService.obtenerTemas().success(function(data) {
			
			var temas = data;
			idSelect  = $('#select2-temasApp-container');
			
			$scope.tema = temas;
			
			for(var t = 0 ; t < temas.length ; t++) {
				
				if(temas[t].idTema === $scope.idTema) {
					
					$scope.configuracionVO.tema = temas[t];
					idSelect.text(temas[t].nbTema);
				
				}
			}
		}).error(function(data) {

			console.log("Ocurrió un error al tratar de obtener el catálogo de temas");
			
		});
	}
	
	$('.webui-popover-content, .webui-arrow').click(function(e) {
		e.stopPropagation();
	});
	
	$document.on('click', function() {
		$scope.b_malla = false;
		$('.content-wrapper').removeClass('desenfoqueApp');
	});
	
	$scope.mallaTemas = function() {
		
		var headerHeight = $('.main-header').height();
		var menuWidth 	 = $('.main-sidebar').width();
		var windowHeight = $(window).height();
		var windowWidth  = $(window).width();
		var px 			 = "px";
		
		$scope.b_malla = !$scope.b_malla;
		
		$('.content-wrapper').toggleClass('desenfoqueApp');
		
		$timeout(function() {
			$('.mallaTemas').css("top", "0");
			$('.mallaTemas').css("width", (windowWidth-menuWidth)+px);
			$('.mallaTemas').css("height", (windowHeight-headerHeight)+px);
		});
	}

//	OBTENER CATÁLOGO DE RESOLUCIONES
	obtenerResoluciones = function() {
		
		configAppService.obtenerResoluciones().success(function(data) {
			
			var resoluciones = data;
			idSelect	 	 = $('#select2-resolucionesApp-container');
			
			$scope.resolucion = resoluciones;
			
			for(var r = 0 ; r < resoluciones.length ; r++) {
				
				if(resoluciones[r].idResolucion === $scope.idResolucion) {
					
					var cdResolucion  = resoluciones[r].cdResolucion;
					var nuPixelesBase = resoluciones[r].nuPixelesBase;
					
					$scope.configuracionVO.resolucion = resoluciones[r];
					idSelect.text(resoluciones[r].txResolucion);
					
				}
			}
			
			$rootScope.liveResolucion(cdResolucion, nuPixelesBase); //Este método está en el templateController
			
		}).error(function(data) {

			console.log("Ocurrió un error al tratar de obtener el catálogo de resoluciones");
			
		});
	}
	
//	PRE-LIVES MENU-FIJO, MENU-DESPLEGADO, CABECERA-FIJA
	$scope.preLiveEstructureApp = function(newVal, oldVal, nameLive) {
		switch(nameLive) {
			case 'stMenuFijo':
				$rootScope.liveMenuFijo(newVal, $scope.configuracionVO.stHeaderFijo); //Esta función está en el templateController
				break;
			case 'stMenuDesplegable':
				$rootScope.liveMenuDesplegado(newVal); //Esta función está en el templateController
				break;
			case 'stHeaderFijo':
				$rootScope.liveHeaderFijo(newVal, $scope.configuracionVO.stMenuFijo); //Esta función está en el templateController
				break;
		}
	}
	
//	ONCHANGE QUE ACTUALIZA EL NOMBRE DE LA APLICACIÓN EN VIVO EN EL HEADER
	$scope.cambiarNombreApp = function(nombreApp) {
		$rootScope.configuracionTMP.aplicacion.nombre = nombreApp;
	}
	
//	VALIDAR Y SETEAR EL NOMBRE DEL ARCHIVO SELECCIONADO AL INPUT
	$scope.getFileLogos = function(e){
		
		$scope.nombreLogo = e.name;
		
		$scope.$apply(function () {
			
			switch(e.name) {
				case 'logoMenuPrincipal':
					if(!(/\.(png)$/i).test(e.files[0].name)) {
						showAlert.aviso(
							$scope.mensajeModal('La imagen seleccionada no tiene formato PNG')
						);
						$scope.imgLogos[0] = null;
					} else {						
						$scope.imgLogos[0] = e.files[0];
						logobs64($scope.imgLogos[0]);
					}
					break;
				case 'logoMenuSecundario':
					if(!(/\.(png)$/i).test(e.files[0].name)) {
						showAlert.aviso(
							$scope.mensajeModal('La imagen seleccionada no tiene formato PNG')
						);
						$scope.imgLogos[1] = null;
					} else {
						$scope.imgLogos[1] = e.files[0];
						logobs64($scope.imgLogos[1]);
					}
					break;
				case 'logoHeader':
					if(!(/\.(png)$/i).test(e.files[0].name)) {
						showAlert.aviso(
							$scope.mensajeModal('La imagen seleccionada no tiene formato PNG')
						);
						$scope.imgLogos[2] = null;
					} else {
						$scope.imgLogos[2] = e.files[0];
						logobs64($scope.imgLogos[2]);
					}
					break;
				case 'logoIndex':
					if(!(/\.(jpeg|jpg|png)$/i).test(e.files[0].name)) {
						showAlert.aviso(
							$scope.mensajeModal('La imagen seleccionada no tiene formato PNG o JPG|JPEG')
						);
						$scope.imgLogos[3] = null;
					} else {
						$scope.imgLogos[3] = e.files[0];
						logobs64($scope.imgLogos[3]);
					}
					break;
			}
        });
	}
	
//	CONVERTIR LOS FILES OBTENIDOS A ARREGLO DE BYTES
	logobs64 = function(file) {
		
		var reader = new FileReader();
		
		reader.onloadend = function() { //Entra después de cargar el file al DOM
			
			var splitLogo = reader.result.split(',')[1];
			
			$scope.$apply(function () {
				
				switch($scope.nombreLogo) {
				
					case 'logoMenuPrincipal':
						$scope.configuracionVO.logoMenuPrincipal = splitLogo;
						$scope.b_logoMenuPrincipal = true;
						$timeout(function() {
							$('#clickPopoverLP').trigger("click")
						}, 400);
						break;
						
					case 'logoMenuSecundario':
						$scope.configuracionVO.logoMenuSecundario = splitLogo;
						$scope.b_logoMenuSecundario = true;
						$timeout(function() {
							$('#clickPopoverLS').trigger("click")
						}, 400);
						break;
						
					case 'logoHeader':
						$scope.configuracionVO.logoHeader = splitLogo;
						$scope.b_logoHeader = true;
						$timeout(function() {
							$('#clickPopoverLH').trigger("click")
						}, 400);
						break;
						
					case 'logoIndex':
						$scope.configuracionVO.logoIndex = splitLogo;
						$scope.b_logoIndex = true;
						$timeout(function() {
							$('#clickPopoverLI').trigger("click")
						}, 400);
						break;
				}
			});
		}
		
		reader.readAsDataURL(file);
	}
	
//	ITERAR LA BANDERA PARA MOSTRAR FORULARIO DE CARGA O DE VISUALIZACIÓN
	$scope.actualizarImg = function(imgUpdate) {
		
		switch(imgUpdate.id) {
		
			case 'updLogoMenuPrincipal' :
				$scope.b_logoMenuPrincipal = false;
				$('.webui-popover').webuiPopover().remove();
				break;
				
			case 'updLogoMenuSecundario' :
				$scope.b_logoMenuSecundario = false;
				$('.webui-popover').webuiPopover().remove();
				break;
				
			case 'updLogoHeader' :
				$scope.b_logoHeader = false;
				$('.webui-popover').webuiPopover().remove();
				break;
				
			case 'updLogoIndex' :
				$scope.b_logoIndex = false;
				$('.webui-popover').webuiPopover().remove();
				break;
		}
	}
	
//	ITERAR BANDERA AL DAR CLIC SOBRE LA X DEL INPUT EN MODO CARGAR ARCHIVO
	$scope.resetFile = function(logoReset) {
		
		$scope.$apply(function() {
			
			switch(logoReset.name) {
			
				case 'logoMenuPrincipal':
					if($scope.logoMenuPrincipal != null){
						$scope.b_logoMenuPrincipal = true;
					}else{
						$scope.b_logoMenuPrincipal = false;
					}
					break;
					
				case 'logoMenuSecundario':
					if($scope.logoMenuSecundario != null) {
						$scope.b_logoMenuSecundario = true;
					}else{
						$scope.b_logoMenuSecundario = false;
					}
					break;
					
				case 'logoHeader':
					if($scope.logoHeader != null) {
						$scope.b_logoHeader = true;
					}else{
						$scope.b_logoHeader = false;
					}
					break;
					
				case 'logoIndex':
					if($scope.logoIndex != null) {
						$scope.b_logoIndex = true;
					}else{
						$scope.b_logoIndex = false;
					}
					break;
			}
			
		});
	}
	
//	RESTAURAR LA CONFIGURACIÓN COMO ESTABA ANTES DE REALIZAR CAMBIOS SIN GUARDAR
	$scope.restaurarConfiguracion = function() {
		
		$scope.consultarConfiguracion();
		$rootScope.comprobarConfiguracion();
		
	}	
	
//	GUARDAR LA CONFIGURACIÓN
	$scope.guardarConfiguracion = function() {
		
		if($scope.formConfigApp.$invalid){
			
			showAlert.requiredFields($scope.formConfigApp);
			showAlert.aviso(
				$scope.mensajeModal('Formulario incompleto')
			);
			
		} else {
	
			configAppService.guardarConfiguración($scope.configuracionVO)
			.success(function(data) {
				
				showAlert.aviso(
					$scope.mensajeModal("Configuración actualizada correctamente"),
					function() {
						$scope.obtenerConfiguracionBD()
					}
				);
				
			}).error(function(data) {

				showAlert.error(
					$scope.mensajeModal("Ocurrió un error al actualizar la configuración"), 
					function() {
						$scope.errorSave()
					}
				);
			});
		}
	}
	
//	DESPUÉS DE GUARDAR CORRECTAMENTE SE CONSULTA NUEVAMENTE LOS DATOS GUARDADOS
	$scope.obtenerConfiguracionBD = function() {
		
		angular.copy($rootScope.configuracionTMP, $rootScope.configuracionApp);
		
		storageService.setConfiguration($rootScope.configuracionApp);
		
		$scope.consultarConfiguracion();
		
	}
	
//	Cargar configuracion de nuevo después de mostrar el mensaje cuando hay error
	$scope.errorSave = function() {
		$location.path('/configuracion');
	}
		
//	DESPUÉS DE LEER TODOS LOS RECURSOS EJECUTAR LO CONTENIDO EN EL MÉTODO
	initController();
	
});