angular.module(appTeclo).controller('expedienteRedirectController',
    function($rootScope,$scope,$timeout,$filter,showAlert,growl,expedienteService,$routeParams)
    {
	
	//Objeto para la configuracion de la directiva de carga de imagenes
	var json=JSON.stringify($routeParams);
	var paramsImgResp=angular.fromJson(json);
	$scope.listImages=new Array();
	$scope.titleLinkBack=paramsImgResp.locatinPrev;

	$scope.paramConfSav=paramsImgResp.optionSave;
	
	$scope.paramConfiguracion=paramsImgResp.optionComponent;
	$scope.paramConfiguracion.maxNuImage=paramsImgResp.maxNuImage;
	$scope.paramConfiguracion.isIncidencia=paramsImgResp.isIncidencia == undefined ? false : paramsImgResp.isIncidencia;
	
	$scope.returnPreView=function(){
		 $location.path(paramsImgResp.locatinPrev);
	}
	  
    });
