angular.module(appTeclo).controller('pluginsWebController', function($controller, $scope, $rootScope, $routeParams, $timeout, $location, $localStorage, WizardHandler, growl, showAlert, configAppService, ModalService) {
	
	$scope.viewTools = false;
	$scope.viewPlugins = true;
	
//	DatetimePicker variables
	var dateCurrent = moment();
	var dateBefore  = moment().add(-10, 'd');
	var dateAfter   = moment().add(+10, 'd');
	
//	Select multiple
	$scope.selectList = 
		[
		 {"idOpcion" : 1, nbGroup : "Grupo 1", "nbOpcion" : 'Opción 1'},
		 {"idOpcion" : 2, nbGroup : "Grupo 1", "nbOpcion" : 'Opción 2'},
		 {"idOpcion" : 3, nbGroup : "Grupo 1", "nbOpcion" : 'Opción 3'},
		 {"idOpcion" : 4, nbGroup : "Grupo 2", "nbOpcion" : 'Opción 4'},
		 {"idOpcion" : 5, nbGroup : "Grupo 2", "nbOpcion" : 'Opción 5'},
		 {"idOpcion" : 6, nbGroup : "Grupo 2", "nbOpcion" : 'Opción 6'},
		 {"idOpcion" : 7, nbGroup : "Grupo 3", "nbOpcion" : 'Opción 7'},
		 {"idOpcion" : 8, nbGroup : "Grupo 3", "nbOpcion" : 'Opción 8'},
		 {"idOpcion" : 9, nbGroup : "Grupo 3", "nbOpcion" : 'Opción 9'}
		 ];
	
	$controller('pasoUnoController', {$scope : $scope});
	$controller('pasoDosController', {$scope : $scope});
	$controller('pasoTresController', {$scope : $scope});
	
//	Selectpicker
	$scope.selectMultiplePicker = [];	
	
//	Smart wizard
	$('.wizard_steps').on('click', function(e){
		e.preventDefault();
	});
	
//	Datetimepicker
	$scope.dateTimePickerOptions = {
		format: 'DD/MM/YYYY hh:mm:ss',
		minDate: dateBefore,
		maxDate: dateAfter
	};
	
//	Timepicker
	$scope.opcionesTimepicker = {
		secondStep: 5,
		minuteStep: 5
	}
	
//	Webuipopover
	$scope.opcionesWebuipopover = {
		closeable: true
	};
	
//	Popover
	$scope.opcionesPopover = {
		animation: false
	}
	
//	Switcher
	$scope.switchTest1			= 'activo';
	$scope.switchTest2			= false;
	$scope.switchTest3			= '✓';
	
	$scope.checkVal = function(nuevoValor, viejoValor) {
		console.log("Nuevo valor: "+nuevoValor, " -> ", "Viejo valor: "+viejoValor);
	};
	
//	Alertas y Modales
	$scope.templateModalVO = {};
	$scope.showModal = false;
	
	$scope.alertas = function(nbAlert) {
		
		$scope.object = {co:'Confirmación ',de:'de ',us:'usuario'};
		
		switch(nbAlert) {
			case 'info':
				showAlert.aviso("Mensaje de aviso", testAviso);
				break;
			case 'error':
				showAlert.error("Mensaje de error");
				break;
			case 'confirmation':
				showAlert.confirmacion("Confirmacion", testConfirmacion, $scope.object, testCancelConfirmacion);
				break;
			default:
				
		}
		
	}

	testAviso = function() {
		growl.info('',{title:'Alerta de aviso'});
	}
	
	testConfirmacion = function(o) {
		growl.success('',{title: o.co+o.de+o.us});
	}
	testCancelConfirmacion = function() {
		growl.info('',{title: 'Se canceló y se ejecutó una acción'});
	}
	
	$scope.iterModal = function() {
		$scope.showModal = true;
	}
	
	$scope.saveModal = function() {
		growl.success('', {title : 'Éxito'});
		ModalService.closeModal();
	}
	
	$scope.saveModalInculde = function() {
		
		if($scope.formTemplateModal.$invalid) {
			showAlert.requiredFields($scope.formTemplateModal);
			growl.error('Formulario incompleto');
		} else {
			growl.success(angular.toJson($scope.templateModalVO), {title : 'Éxito'});
		}
		
	}
	
//	Notificaciones
	$scope.msgInfo = "clic";
	
	$scope.notificacion = function(type) {
		switch(type) {
			case 'exito':
				growl.success($scope.mensajeModal('Notificación de éxito'),
						{title: $scope.mensajeModal('¡Éxito!')});
				break;
			case 'advertencia':
				growl.warning($scope.mensajeModal('Notificación de advertencia'), {ttl: 3000});
				break;
			case 'error':
				growl.error($scope.mensajeModal('Notificación de error'));
				break;
			case 'informacion':
				growl.info($scope.mensajeModal('Notificación de información'), {
					onclose: function() {
						$scope.msgInfo = 'oculto';
					},
					onopen: function() {
						$scope.msgInfo = 'visualizado';
						$timeout(function() {
							$scope.msgInfo = 'clic';
						}, 3000);
					}
				});
			break;
		}
	}
	
	successEject = function() {
		growl.info(
			$scope.mensajeModal('Notificación con 2 segundos de retraso'), 
			{title: $scope.mensajeModal('INFORMACIÓN')}
		);
	}
	
//	Angular wizard
	$scope.saveWizard = function() {
		
		var wizardExample = WizardHandler.wizard('wizardExample');
		wizardExample.setEditMode(true);
		
		growl.info("Fin del angular wizard");
		
	}
	
	$scope.cancelWizard = function() {
		alert('Cancel');
	}
	
	
//	Drag and drop list
	$scope.miLista = [];
	$scope.miReset = {};
	
	$timeout(function() {
		$scope.miLista =
			[
				{
					miNombreLista: "AAAAAAAAAA",
					miItem:
						[
							{idItem:1,nbItem: "Item A1",cdItem:'A1',txtItem:'Descripción del item',txtRandom:'Lorem ipsum dolor text lorem ipsum dolor'},
							{idItem:2,nbItem: "Item A2",cdItem:'A2',txtItem:'Descripción del item',txtRandom:'Lorem ipsum dolor text lorem ipsum dolor'},
							{idItem:3,nbItem: "Item A3",cdItem:'A3',txtItem:'Descripción del item',txtRandom:'Lorem ipsum dolor text lorem ipsum dolor'},
							{idItem:4,nbItem: "Item A4",cdItem:'A4',txtItem:'Descripción del item',txtRandom:'Lorem ipsum dolor text lorem ipsum dolor'},
							{idItem:5,nbItem: "Item A5",cdItem:'A5',txtItem:'Descripción del item',txtRandom:'Lorem ipsum dolor text lorem ipsum dolor'}
						]
				},
				
				{
					miNombreLista: "BBBBBBBBBB",
					miItem:
						[
							{idItem:1,nbItem: "Item B1",cdItem:'B1',txtItem:'Descripción del item',txtRandom:'Lorem ipsum dolor text lorem ipsum dolor'},
							{idItem:2,nbItem: "Item B2",cdItem:'B2',txtItem:'Descripción del item',txtRandom:'Lorem ipsum dolor text lorem ipsum dolor'},
							{idItem:3,nbItem: "Item B3",cdItem:'B3',txtItem:'Descripción del item',txtRandom:'Lorem ipsum dolor text lorem ipsum dolor'},
							{idItem:4,nbItem: "Item B4",cdItem:'B4',txtItem:'Descripción del item',txtRandom:'Lorem ipsum dolor text lorem ipsum dolor'}
						]
				}
		    ];
	
		$scope.flag = true;
	}, 2500);
	
	$scope.resetManual = function() {
		$scope.miReset.reset();
	}
	
});