angular.module(appTeclo).controller('expedienteController',
    function($scope,$timeout,$filter,showAlert,growl,expedienteService)
    {

	const MENSAJE='Seleccione una opción';
	//Objeto para la configuracion de la directiva de carga de imagenes
	$scope.listImages=new Array();
	$scope.nuMaxImg=10;
	var optionBacupSelected={itemSelected:null, nivel:null};

	$scope.paramConfSav= new Object({
		idOrdenServ: null, 			 
		idProceso: null,   			
		idEncuesta:null,			
		idPregunta:null,			
		idIncidencia: null
	});
	
	$scope.paramConfiguracion=new Object({
		maxSizeMb: 1,			
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
			  resetFom();
			  
			  $scope.listOrdenExpediente = response;
				$scope.expedienteImg=$scope.listOrdenExpediente[0];
				$scope.catalogosRelacoinados.catProceso = response[0].procesos;  
				$scope.nuMaxImg=response[0].nuMaxImg;
				
				if(response[0].imagenes == undefined)
					response[0].imagenes=[];
				
				if($scope.updateViewDirective != undefined) 
					$scope.updateViewDirective(response[0].imagenes);// metodo se encuntra en la directoa update-image
					 
				$scope.listImages = response[0].imagenes;				
				
				$scope.paramConfSav.idOrdenServ=response[0].idOrdenServicio;
				$scope.paramConfSav.cdOrdenServicio=response[0].cdOrdenServicio;
          }).error(function(e) {
        	  resetFom();
              if(e !== null) {showAlert.error(`Ocurrió un error en la petición ${e.message}`);}
              else {showAlert.error('Falló la petición');}
          });
		  
	  };
	  
	  resetFom=function(){
		  	$scope.nuMaxImg=null;
			$scope.listImages = [];
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
			$scope.paramConfSav.idOrdenServ=null;
			$scope.paramConfSav.cdOrdenServicio=null;
			$scope.listOrdenExpediente=[];
      	  	$scope.expedienteImg=new Object();
	  };
	  
	  $scope.confirmChanged=function(){//metodo que muestra una alerta indicando que  hay imagenes sin guardar
	    	 showAlert.confirmacion('Se ha detectado imagenes sin guardar, ¿Desea continuar?',
             confirm = function(){
	    		 $scope.listImages=[];
	    		 $scope.updateViewDirective($scope.listImages);
             }, cancelaNotificar =function (){
            	 aplicarCambio(itemSelected, nivel);
                 return;
             }); 
	     };
	  
	  /**
	   * METODOS PARA LA NUEVA FUNCONALIDAD COMBOS
	   */
	     
	  $scope.changedComboDependiente=function(itemSelected, nivel){
		  /*optionBacupSelected.itemSelected=itemSelected;
		  optionBacupSelected.nivel=angular.copy(nivel);
		  
		  let i;
		  let existImgNotSaved=false;
		  for(i=0; i<$scope.listImages.length; i++){
			if($scope.listImages[i].isSuccess == false){
				existImgNotSaved=true;
				break;
			}
		  }
		  
		  if(existImgNotSaved){
			  $scope.expedienteImg.pregunta.listImageClasif=agular.copyoptionBacupSelected.itemSelected;
			  $scope.confirmChanged();
		  }else{
			  aplicarCambio(itemSelected, nivel);
		  }*/
		  
		  
		  aplicarCambio(itemSelected, nivel);
		  
	  };
	  
	  aplicarCambio=function(itemSelected, nivel){
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
						  
						  $scope.listImages= angular.copy(itemSelected.imagenes);
						  
						  $scope.updateViewDirective($scope.listImages);// metodo se encuntra en la directoa update-image
						  
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
						  
						  $scope.listImages= angular.copy(itemSelected.imagenes);
						  
						  $scope.updateViewDirective($scope.listImages);// metodo se encuntra en la directoa update-image
						  
						  if(itemSelected.listImageClasif.length>0){
							$scope.catalogosRelacoinados.catPregunta = itemSelected.listImageClasif;
						  }	 
						  
						  break;

					  case 'pregunta':
						  
						  $scope.nuMaxImg=itemSelected.nuMaxImg;
						  $scope.paramConfSav.idPregunta=itemSelected.idPregunta;
						  
						  if(itemSelected.imagenes == undefined)
							  itemSelected.imagenes=[];
						  
						  $scope.listImages= angular.copy(itemSelected.imagenes);
						  
						  $scope.updateViewDirective($scope.listImages);// metodo se encuntra en la directoa update-image
						  break;
				  }
			  }else{
				  switch(nivel){
					case 'proceso':
						$scope.nuMaxImg=$scope.expedienteImg.nuMaxImg;
						
						if($scope.expedienteImg.imagenes == undefined)
							$scope.expedienteImg.imagenes=[];
						
						$scope.listImages = angular.copy($scope.expedienteImg.imagenes);
						
						$scope.updateViewDirective($scope.expedienteImg.imagenes);
						
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
						
						if($scope.expedienteImg.proceso.imagenes == undefined)
							$scope.expedienteImg.proceso.imagenes=[];
						
						$scope.listImages = angular.copy($scope.expedienteImg.proceso.imagenes);
						
						$scope.updateViewDirective($scope.listImages);
						
						$scope.catalogosRelacoinados.catPregunta=null;
						$scope.expedienteImg.encuesta=undefined;
						$scope.expedienteImg.pregunta=undefined;
						$("#select2-encuesta-container").text(MENSAJE);
						$("#select2-pregunta-container").text(MENSAJE);
						$scope.paramConfSav.idEncuesta=null;
						$scope.paramConfSav.idPregunta=null;
						break;

					case 'pregunta':
						if($scope.expedienteImg.encuesta.listImageClasif.imagenes == undefined)
							$scope.expedienteImg.encuesta.listImageClasif.imagenes=[];
						
						$scope.listImages = angular.copy($scope.expedienteImg.encuesta.listImageClasif.imagenes);
						
						$scope.updateViewDirective($scope.listImages);
						
						$scope.expedienteImg.pregunta=undefined;
						$("#select2-pregunta-container").text(MENSAJE);
						$scope.paramConfSav.idPregunta=null;
						break;
				  }
			  }
	  }

    });