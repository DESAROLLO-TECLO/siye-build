angular.module(appTeclo)
.controller("encuestaSatisfaccionController",
function($rootScope,$scope,$window,$translate,$timeout,ModalService,showAlert,growl) {

	$scope.parametroBusqueda =new Object();
	
	//Se declaran tipos de busqueda
    $scope.listTipoBusqueda ={
        tipoBusqueda: [
            {idTipo:1,cdTipo:"PLACA",nbTipo:"Placa"},
            {idTipo:2,cdTipo:"ORDEN",nbTipo:"Orden"},
            {idTipo:2,cdTipo:"VIN",nbTipo:"Vin"}
        ]
    };
	
    $scope.buscarOrden=function(param,form){
    	if (form.$invalid) {
    		showAlert.requiredFields(form);
			growl.error('Formulario Incompleto');
			
		}
    	
    }
    
    
//	Detectar el navegador para ajustar el contenido
	detectarNavegador = function(){

		var contFormulario = $('.cont-formuarioCliente');
		var bfCaret = $('.contSelectPhone');
		
		switch(deviceDetector.browser){
			case 'firefox':
				$scope.navegador = "firefox";
				contFormulario.addClass('comodinHeight');
				break;
			case 'chrome':
				$scope.navegador = "chrome";
				bfCaret.addClass('contSelectPhoneCr');
				bfCaret.removeClass('contSelectPhone');
				break;
			case 'ms-edge':
				$scope.navegador = "ms-edge";
				break;
			case 'ie':
				$scope.navegador = "ie";
				break;
			case 'safari':
				$scope.navegador = "safari";
				break;
			default:
				$scope.navegador = "Unvalued browser";
		}
		return $scope.navegador;
	}

});