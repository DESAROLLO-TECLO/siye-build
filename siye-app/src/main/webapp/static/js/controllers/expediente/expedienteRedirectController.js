angular.module(appTeclo).controller('expedienteRedirectController',
    function($rootScope,$scope,$timeout,$filter,showAlert,growl,expedienteService,$routeParams)
    {
	
	//Objeto para la configuracion de la directiva de carga de imagenes
	var paramsImgResp=$routeParams;
	$scope.listImages=new Array();
	$scope.fileUploader=new FileUploader();
	$scope.fileUploaderDos=new FileUploader();
	
	$scope.lisTpDocuments=[{idTipoExpediente:1,nbTipoExpediente:'tipo uno'},{idTipoExpediente:2,nbTipoExpediente:'tipo dos'}];
	
	$scope.paramConfSav= new Object();
	
	$scope.paramConfSavDos= new Object({
		idOrdenServ: 2, 			 
		idProceso: 2,   			
		idEncuesta: 2,			
		idPregunta: 2,			
		idIncidencia: null
	});
	
	$scope.paramConfiguracion=new Object({
		maxSizeMb: 8,			
        maxNuImage: 5,
        listTypeExtencion: ['jpg','png','jpeg'],
        listTpDocuemnt: $scope.lisTpDocuments,
        nameService:  null,
        nameFunctionService: null,
        showComponentCopy:false,
        titleModal:'Carga nivel Pregunta',
        nameParamFile:'Buscar',
        templateButonModal:'<button class="btn btn-danger"> '+                                          
								'<i class="fa fa-clipboard" aria-hidden="true"></i></i> Personal' +                         
							'</button>'
	});
	
	$scope.returnPreView=function(){
		
	}
	  
    });