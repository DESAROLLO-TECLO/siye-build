angular.module(appTeclo).controller('expedienteController',
    function($rootScope,$scope,$timeout,$filter,showAlert,growl,expedienteService)
    {

	const MENSAJE='Seleccione una opción';
	//Objeto para la configuracion de la directiva de carga de imagenes
	$scope.listImages=new Array();
	$scope.nuMaxImg=10;

	$scope.paramConfSav= new Object({
		idOrdenServ: null, 			 
		idProceso: null,   			
		idEncuesta:null,			
		idPregunta:null,			
		idIncidencia: null
	});
	
	$scope.paramConfiguracion=new Object({
		maxSizeMb: 5,			
        maxNuImage: 1,
        listTypeExtencion: ['jpg','png','jpeg','pdf','doc','docx'],
        listTpDocuemnt: null
	});
	
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

	  //Objeto para combos dependientes
	  $scope.catalogosRelacoinados = new Object({
		  catProceso:null,
		  catEncuesta:null,
		  catPregunta:null
	  });
	 
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
		  
		  expedienteService.getInfoOs(tpBusqueda.cdTipoBusqueda,valor).success(function(response) {
		  		$scope.listOrdenExpediente = response;
				$scope.expedienteImg=$scope.listOrdenExpediente[0];
				$scope.catalogosRelacoinados.catProceso = response[0].procesos;  
				$scope.nuMaxImg=response[0].nuMaxImg;
				if(response[0].imagenes != undefined){
					if($scope.updateViewDirective != undefined) 
						$scope.updateViewDirective(response[0].imagenes);// metodo se encuntra en la directoa update-image
					 
					 $scope.listImages = response[0].imagenes;
				}
				
				$scope.paramConfSav.idOrdenServ=response[0].idOrdenServicio;
				$scope.paramConfSav.cdOrdenServicio=response[0].cdOrdenServicio;
          }).error(function(e) {
        	  $scope.listOrdenExpediente=[];
        	  $scope.expedienteImg=new Object();
              if(e !== null) {showAlert.error(`Ocurrió un error en la petición ${e.message}`);}
              else {showAlert.error('Falló la petición');}
          });
		  
	  };
	  
	  $scope.confirmChanged=function(message){//metodo que muestra una alerta indicando que  hay imagenes sin guardar
	    	 showAlert.confirmacion(message,
             confirm = function(){
             	let i;
             	for(i=0; i<scope.listImages.length; i++){
             		let imagenVO=scope.listImages[i];
             		if(!imagenVO.isSuccess){
             			scope.listImages.splice(i,1);
             			i--;
	          			}
             	}	                	
             }, cancelaNotificar =function (){
                 return;
             }); 
	     };
	  
	  /**
	   * METODOS PARA LA NUEVA FUNCONALIDAD COMBOS
	   */
	  $scope.changedComboDependiente=function(itemSelected, nivel){
		  if(itemSelected!=undefined){
			let temp= [];
			  switch(nivel){
				  case 'proceso':
					  $scope.paramConfSav.idProceso=itemSelected.idProceso;
					  $scope.paramConfSav.idEncuesta=null;
					  $scope.paramConfSav.idPregunta=null;	
					  $scope.nuMaxImg=itemSelected.nuMaxImg;					  
					  
					  if(itemSelected.imagenes == undefined)
						  itemSelected.imagenes=[];
					  
					  $scope.updateViewDirective(itemSelected.imagenes);// metodo se encuntra en la directoa update-image
					  
					  $scope.listImages= itemSelected.imagenes;
					  
					  if(itemSelected.listImageClasif.length > 0){
						$scope.catalogosRelacoinados.catEncuesta = itemSelected.listImageClasif;
					  }
					  break;
				  case 'encuesta':
					  $scope.nuMaxImg=itemSelected.nuMaxImg;
					  $scope.paramConfSav.idEncuesta=itemSelected.idEncuesta;
					  $scope.paramConfSav.idPregunta=null;
					  
					  if(itemSelected.imagenes == undefined)
						  itemSelected.imagenes=[];
					  
					  $scope.updateViewDirective(itemSelected.imagenes);// metodo se encuntra en la directoa update-image
					  $scope.listImages = itemSelected.imagenes;
					  
					  if(itemSelected.listImageClasif.length>0){
						$scope.catalogosRelacoinados.catPregunta = itemSelected.listImageClasif;
					  }	 
					  
					  break;

				  case 'pregunta':
					  $scope.nuMaxImg=itemSelected.nuMaxImg;
					  $scope.paramConfSav.idPregunta=itemSelected.idPregunta;
					  
					  if(itemSelected.imagenes == undefined)
						  itemSelected.imagenes=[];
					  
					  $scope.updateViewDirective(itemSelected.imagenes);// metodo se encuntra en la directoa update-image
					  $scope.listImages = itemSelected.imagenes;					  
					  break;
			  }
		  }else{
			  switch(nivel){
				case 'proceso':
					$scope.nuMaxImg=$scope.expedienteImg.nuMaxImg;
					$scope.listImages = $scope.expedienteImg.imagenes;
					$scope.catalogosRelacoinados.catEncuesta=null;
					$scope.catalogosRelacoinados.catPregunta=null;
					$scope.expedienteImg.proceso=undefined;
					$scope.expedienteImg.encuesta=undefined;
					$scope.expedienteImg.pregunta=undefined;					
					$("#select2-proceso-container").text(MENSAJE);
					$("#select2-encuesta-container").text(MENSAJE);
					$("#select2-pregunta-container").text(MENSAJE);
					$scope.paramConfSav.idProceso=null;
					$scope.paramConfSav.idEncuesta=null;
					$scope.paramConfSav.idPregunta=null;	
					break;

				case 'encuesta':
					
					$scope.listImages=$scope.expedienteImg.proceso.imagenes;
					$scope.catalogosRelacoinados.catPregunta=null;
					$scope.expedienteImg.encuesta=undefined;
					$scope.expedienteImg.pregunta=undefined;
					$("#select2-encuesta-container").text(MENSAJE);
					$("#select2-pregunta-container").text(MENSAJE);
					$scope.paramConfSav.idEncuesta=null;
					$scope.paramConfSav.idPregunta=null;
					break;

				case 'pregunta':
					$scope.listImages = $scope.expedienteImg.encuesta.listImageClasif.imagenes;
					$scope.expedienteImg.pregunta=undefined;
					$("#select2-pregunta-container").text(MENSAJE);
					$scope.paramConfSav.idPregunta=null;
					break;
			  }
		  }
	  };

    });