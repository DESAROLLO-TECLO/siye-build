angular.module(appTeclo).controller('expedienteController',
    function($rootScope,$scope,$timeout,$filter,showAlert,growl,expedienteService)
    {
	
	//Objeto para la configuracion de la directiva de carga de imagenes
	$scope.paramConfiguracion=new Object({
		idOrdenServ: 1, 			 
		idProceso: 1,   			
		idEncuesta: 1,			
		idPregunta: 1,			
		idIncidencia: null,			
		listImages: $scope.listImages,			
		nameParamFile: null,		
        maxSizeMb: 8,			
        maxNuImage: 5,
        listTypeExtencion: ['jpg','png','jpeg'],
        listTpDocuemnt: null,
        nameService:  null,
        nameFunctionService: null,
        fileUploader: null,
        showComponentCopy:false,
        titleModal:'Carga Masiva de Imagenes'
    });
	
	$scope.listImages=new Array();

	//Variables de TipoBusqueda
	$scope.busqueda=new Object({
		tpBusqueda:undefined,
		valorBusqueda:"",
		catalogo:new Array(
	            { idTipoBusqueda: "1", cdTipoBusqueda: "PLACA", nbTipoBusqueda: "PLACA", txTipoBusqueda: "PLACA" },
	            { idTipoBusqueda: "2", cdTipoBusqueda: "ORDEN_SERVICIO", nbTipoBusqueda: "ORDEN DE SERVICIO", txTipoBusqueda: "ORDEN DE SERVICIO" },
	            { idTipoBusqueda: "3", cdTipoBusqueda: "VIN", nbTipoBusqueda: "VIN", txTipoBusqueda: "VIN" }		
		)
	});
	
	  //Objeto de prueba
	  $scope.expedienteImg=new Object();
	  
	  //lista de Ordenes de servicio
	  $scope.listOrdenExpediente=new Array();
	 
	  //liata para modificar y manipular la vista padre sin afectar la lista orginal
	  $scope.listImagesClasifiView=new Array();
     
	//metodo que regresa una lista mediante un nombre de atributo (nameAtributo) especificado y un filtro (value) 
	  getListByFilter=function(nameAtributo,value,listItems){
		  
		  let list=new Array();
		  
		  if(listItems != undefined && nameAtributo != undefined && value!= undefined){
			  list = listItems.filter(function (item) {
		     	    return (item[nameAtributo] == value);
		     	});  
		  }
	    	 
		  return list;
	  }
	 
/**
 * Metodos Para consulta y procesamiento de datos de componentes de imagenes
 *
 */
	  //Se otiene los datos obtenidos de base de datos por tipo de busqueda
	  $scope.getDataOrdenServicio= function(tpBusqueda,valor,form){
		  
		  if (form.$invalid) {
              showAlert.requiredFields(form);
              growl.error('Formulario incompleto');
              return;
          }
		  
		  expedienteService.getInfoOs(tpBusqueda.cdTipoBusqueda,valor)
		  	.success(function(response) {

		  		$scope.listOrdenExpediente = response;
		  		$scope.expedienteImg=$scope.listOrdenExpediente[0];
          }).error(function(e) {
        	  $scope.listOrdenExpediente=[];
        	  $scope.expedienteImg=new Object();
              if(e !== null) {showAlert.error(`Ocurrió un error en la petición ${e.message}`);}
              else {showAlert.error('Falló la petición');}
          });
		  
	  };
	  
	  /**
	   * METODOS PARA LA NUEVA FUNCONALIDAD COMBOS
	   */
	  $scope.changedComboDependiente=function(itemSelected){
		  
	  };
	  
    });