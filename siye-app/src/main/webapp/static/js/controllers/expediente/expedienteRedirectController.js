angular.module(appTeclo).controller('expedienteRedirectController',
    function($rootScope,$scope,$timeout,$filter,showAlert,growl,expedienteService,params,$location)
    {
	
	//Objeto para la configuracion de la directiva de carga de imagenes
	var paramsImgResp=params;
	$scope.listImages=new Array();
	var linPrev=paramsImgResp.locatinPrev;
	$scope.titleLinkBack=linPrev;

	$scope.paramConfSav=paramsImgResp.optionSave;
	
	$scope.paramConfiguracion=paramsImgResp.optionComponent;
	$scope.paramConfiguracion.maxNuImage=paramsImgResp.maxNuImage;
	$scope.paramConfiguracion.isIncidencia=paramsImgResp.isIncidencia == undefined ? false : paramsImgResp.isIncidencia;
	
	$scope.returnPreView=function(){
		 $location.path(linPrev);
	};
	  
	function getImagesByLevel(){
		expedienteService.getInfoOsNivel(paramsImgResp.cdOs,paramsImgResp.cdNivel,paramsImgResp.valor)
			.success(function(reponse){
				$scope.listImages=reponse;
		  }).error(function(e){
			  if(e.status != undefined){
	    			 if(e.status.descripcion != undefined){
		                	growl.error(e.status.descripcion,{ ttl: 4000 });
		                }else if(e.status.message != undefined) {
		                	growl.error(e.status.message,{ ttl: 4000 });
		                }else if(typeof status === 'string'){
		                	growl.error(status,{ ttl: 4000 });
		                }else {showAlert.error('Falló la petición');} 
	    		 }else{
	    			 if(e.descripcion != undefined){
		                	growl.error(e.descripcion,{ ttl: 4000 });
		                }else if(e.message != undefined) {
		                	growl.error(e.message,{ ttl: 4000 });
		                }else if(typeof e === 'string'){
		                	growl.error(e,{ ttl: 4000 });
		                }else {showAlert.error('Falló la petición');}
	    		 }
		  });
	};
	
	getImagesByLevel();
	
});
