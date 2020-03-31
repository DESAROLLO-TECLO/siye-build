angular.module(appTeclo).controller('expedienteController',
    function($rootScope,$scope,$timeout,$filter,showAlert,growl,FileUploader)
    {
	
	//FUNCION QUE INICIALIZA LAS FUNCIONES POR DEFECTO
	
	
	//constantes con mensajes o parametros estaticos
	  const constants={
	    MSJ_CONFIRM_REMOVE:"",
	    MSJ_CONFIRM_CANCEL_UPLOAD:"",
	    MSJ_CONFIRM_CANCEL_ALL:"",
	    MAX_NUMBER_FILES:5,
	    MAX_SIZE_IMG_MB: 5242880,//1048576,// bytes a megas
	    TAB_VALID_ORDEN:"VOR",
	    TAB_KIT:"KIT",
	    TAB_INSTALACION:"INT"
	   };
	
	  //Objeto para manipulacion de banderas
	  $scope.flags=new Object({
		  showValidarOrden:true,
		  showKit:false,
		  showInstalacion: false,
		  showButtonMobileFileUploader:false,
		  showButtonBack:false
	  });
	  
	  //objeto con las diefrentes listas de imagenes
	  $scope.expedienteImg=new Object({
		  nuOrdenServicio:'0985263736',
		  nameConsesionario:'Juan Rodriguez',
		  placa:'DSER-789',
		  vin:'99827-DR45',
		  maxLimitUpdate:5,
		  tpNivel:'Orden de Servicio',
		  cdClasif:'OS',
		  backStackParent:[{cdClasif:'OS',tpNivel:'Orden de Servicio'}],
		  listProcesoImages:[{idProceso:1,cdClasif:'INS',name:'Instalacion',tpNivel:'Proceso',nuMaxImage:30,listImage:[],
			  				   listImageClasif:[{idEtapa:1,cdClasif:'VDO',name:'Validación de Orden',tpNivel:'Encuesta',nuMaxImage:20,
			  					   			listImage:[],
			  					   		listImageClasif:[{idPregunta:1,cdClasif:'1P01',nuMaxImage:3,tpNivel:'Pregunta',name:'¿Pregunta Uno?',listImage:[]},
			  					   			               {idPregunta:2,cdClasif:'2P02',nuMaxImage:3,tpNivel:'Pregunta',name:'¿Pregunta Dos?',listImage:[]}]},
					   			          {idEtapa:2,cdClasif:'KIT',tpNivel:'Encuesta',name:'KIT',nuMaxImage:30,
					  					   			listImage:[],
					  					   		listImageClasif:[{idPregunta:3,cdClasif:'3P01',nuMaxImage:3,tpNivel:'Pregunta',name:'¿Pregunta Uno?',listImage:[]},
					  					   			               {idPregunta:4,cdClasif:'4P02',nuMaxImage:3,tpNivel:'Pregunta',name:'¿Pregunta Dos?',listImage:[]}]},
  					   			          {idEtapa:3,cdClasif:'INT',tpNivel:'Encuesta',name:'Instalación',nuMaxImage:30,
					  					   			listImage:[],
					  					   		listImageClasif:[{idPregunta:5,cdClasif:'5P01',tpNivel:'Pregunta',nuMaxImage:3,name:'¿Pregunta Uno?',listImage:[]},
					  					   			               {idPregunta:6,cdClasif:'6P02',tpNivel:'Pregunta',nuMaxImage:3,name:'¿Pregunta Dos?',listImage:[]}]}]},
		   			          {idProceso:2,cdClasif:'PTF',name:'Plataforma',tpNivel:'Proceso',nuMaxImage:30,listImage:[],
					  					 listImageClasif:[{idEtapa:1,cdClasif:'VDF',tpNivel:'Encuesta',name:'Validación de Funcionalidad',nuMaxImage:20,
					  					   			listImage:[],
					  					   		listImageClasif:[{idPregunta:7,cdClasif:'7P01',tpNivel:'Pregunta',nuMaxImage:3,name:'¿Pregunta Uno?',listImage:[]},
					  					   			               {idPregunta:8,cdClasif:'8P02',tpNivel:'Pregunta',nuMaxImage:3,name:'¿Pregunta Dos?',listImage:[]}]}]}]
	  });
	  
	  //liata para modificar y manipular la vista padre sin afectar la lista orginal
	  $scope.listImagesClasifiView=angular.copy($scope.expedienteImg.listProcesoImages);
	  	  
	 //objeto con la instancia del elemento de la libreria FileUploader
	 $scope.fileUploader = new FileUploader();
	 
	  // Se preparan filtros de configuración para el componente
	 $scope.fileUploader.filters.push({
         name: 'imageFilter',
         fn: function(item, options) {
             var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
             return '|jpg|png|jpeg'.indexOf(type) !== -1;
         },
         name: 'imageFilterMaxSize',
         fn: function (item) {
             return item.size < constants.MAX_SIZE_IMG_MB; // 1 MiB to bytes
         }
     });
	 
	 $scope.isImage= function(item) {
         var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
         return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
     };
	 
	// CALLBACKS COMPONENT FileUploader
	 $scope.fileUploader.onWhenAddingFileFailed = function(item, filter, options) {
		 // si se cumple algun error por los filtros especificados
         console.info('onWhenAddingFileFailed', item, filter, options);
         showAlert.aviso("La imagen no cumple con los parametros indicados "+item.size+"tamaño maximo "+constants.MAX_SIZE_IMG_MB);
     };
     
     $scope.fileUploader.onAfterAddingFile = function(fileItem) {
         console.info('onAfterAddingFile', fileItem);
     };
     
     $scope.fileUploader.onAfterAddingAll = function(addedFileItems) {
    	 if(addedFileItems != undefined && addedFileItems.length > 0){
    		 angular.forEach(addedFileItems, function(fileItem, key) {
    			  fileItem.cdClasif=angular.copy($scope.expedienteImg.cdClasif);
    			  let arrayFile=fileItem.file.type.split("/");
    			  let name=fileItem.file.name;
    			  fileItem.file.nameImg=name.slice(0,-(arrayFile[1].length+1));
    		 });
    	 }
     };
     
     $scope.fileUploader.onBeforeUploadItem = function(item) {
         console.info('onBeforeUploadItem', item);
     };
     $scope.fileUploader.onProgressItem = function(fileItem, progress) {
         console.info('onProgressItem', fileItem, progress);
     };
     $scope.fileUploader.onProgressAll = function(progress) {
         console.info('onProgressAll', progress);
     };
     $scope.fileUploader.onSuccessItem = function(fileItem, response, status, headers) {
         console.info('onSuccessItem', fileItem, response, status, headers);
     };
     $scope.fileUploader.onErrorItem = function(fileItem, response, status, headers) {
         console.info('onErrorItem', fileItem, response, status, headers);
     };
     $scope.fileUploader.onCancelItem = function(fileItem, response, status, headers) {
         console.info('onCancelItem', fileItem, response, status, headers);
     };
     $scope.fileUploader.onCompleteItem = function(fileItem, response, status, headers) {
         console.info('onCompleteItem', fileItem, response, status, headers);
     };

     $scope.fileUploader.onTimeoutItem = function(fileItem) {
         console.info('onTimeoutItem', fileItem);
     };

     $scope.fileUploader.onCompleteAll = function() {
         console.info('onCompleteAll');
     };
  // CALLBACKS COMPONENT FileUploader
	
     //Metodo para cambiar de vista de carpetas imagenes
     $scope.changedView=function(item){
    	 $scope.expedienteImg.tpNivel=item.tpNivel+": "+item.name;
    	 $scope.expedienteImg.cdClasif=item.cdClasif;
    	 if(item.listImageClasif != undefined){
    		 
    		 $scope.flags.showButtonBack=true;
    		 let a=$scope.expedienteImg.backStackParent;
    		 let exist=false;
    		 let itemBackStak={};
			 for(let i=0; i<a.length; i++){
    			 if(a[i].cdClasif==item.cdClasif){
    				 exist=true;
    				 break;
    			 }
    		 }
        		 
    		if(!exist){
    			itemBackStak={cdClasif:item.cdClasif,tpNivel:item.tpNivel+": "+item.name};
           	 	$scope.expedienteImg.backStackParent.push(itemBackStak);
    		}
        	 $scope.listImagesClasifiView=item.listImageClasif;
    	 }else{
    		 $scope.listImagesClasifiView=[];
    	 }
     };
     
     //METODO PARA REGRESAR Y MOSTRAR LA LISTA DE CARPETAS EN FRONT CON EL BOTORN REGRESAR
     $scope.returnParent=function(listPrincipal){
    	 let lis=$scope.expedienteImg.backStackParent;
    	 let itemBackStak=lis[lis.length-1];
    	 let cdClasif=itemBackStak.cdClasif;
    	
    	 if(cdClasif == 'OS'){
    		 $scope.flags.showButtonBack=false;
			 $scope.expedienteImg.tpNivel=itemBackStak.tpNivel;
			 $scope.expedienteImg.cdClasif=itemBackStak.cdClasif;
    		 $scope.listImagesClasifiView=angular.copy($scope.expedienteImg.listProcesoImages);
    		 return;
		 }else if(listPrincipal==undefined){
    		 return false;
    	 }else{
    		 let isFund=false;
    		 for(let i=0; i<listPrincipal.length; i++){
    			 if(isFund)
    				 return;
    			 
    			 let item=listPrincipal[i];
    			 if(cdClasif == item.cdClasif){
      				  $scope.listImagesClasifiView=angular.copy(item.listImageClasif);
      				  $scope.expedienteImg.tpNivel=itemBackStak.tpNivel;
      				  
      				  if(cdClasif ==  $scope.expedienteImg.cdClasif){
      					cdClasif=$scope.expedienteImg.backStackParent.pop();
      					isFund=$scope.returnParent(listPrincipal);
      				  }
      				  $scope.expedienteImg.cdClasif=cdClasif;
      				  
      				  return true;
      			  }else{
      				isFund=$scope.returnParent(item.listImageClasif);
      			  }
    		 }
    	 }
     };
     
     
   //Metodo para tabs
	  $scope.changeViewTab=function(nameTab){
		  
		  switch(nameTab){
		  	case constants.TAB_VALID_ORDEN:
		  		$scope.flags.showValidarOrden=true;
		  		$scope.flags.showKit=false;
		  		$scope.flags.showInstalacion=false;
		  		$scope.expedienteImg.nameTabCurrent=constants.TAB_VALID_ORDEN;
		  		break;
		  	case constants.TAB_KIT:
		  		$scope.flags.showValidarOrden=false;
		  		$scope.flags.showKit=true;
		  		$scope.flags.showInstalacion=false;
		  		$scope.expedienteImg.nameTabCurrent=constants.TAB_KIT;
		  		break;
		  	case constants.TAB_INSTALACION:
		  		$scope.flags.showValidarOrden=false;
		  		$scope.flags.showKit=false;
		  		$scope.flags.showInstalacion=true;
		  		$scope.expedienteImg.nameTabCurrent=constants.TAB_INSTALACION;
		  		break;
		  }
	  };
	  
	//metodo que regresa una lista mediante un filtro y un nombre de atributo especificado
	  getListByFilter=function(nameAtributo,value,listItems){
		  
		  let list=new Array();
		  
		  if(listItems != undefined && nameAtributo != undefined && value!= undefined){
			  list = listItems.filter(function (item) {
		     	    return (item[nameAtributo] == value);
		     	});  
		  }
	    	 
		  return list;
	  }
	  
	  //METODO QUE PERMITE IDENTIFICAR EL TIPO DE DISPOSITIVO DONDE SE ESTA CARGANDO LA PANTALLA Y DETERMINA QUE COMPONENTES MOSTRAR
	  defComponents=function(){
		  var isMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);
			if (isMobile) {
				 $scope.flags.showButtonMobileFileUploader=true;
			} else {
				$scope.flags.showButtonMobileFileUploader=false;
			}
	  }
	  //METODO QUE PERMITE IDENTIFICAR EL TIPO DE DISPOSITIVO DONDE SE ESTA CARGANDO LA PANTALLA Y DETERMINA QUE COMPONENTES MOSTRAR
	  
     
     //clasifica las imagenes por tab
     $scope.clasificImageByTab=function(itemFile){
    	 
     };
     
	 //configuración de opciones para el componente de carga FileUploader
	 $scope.optsComponentUploadFile={
			 
	 };

	  //lusta de archivos con la configuración para enviar en la peticción, o recibir al consultar
	  $scope.uploadFilesListVO=new Array();

	  /**
	   * Metodo para cargar las imagenes o archivos
	   */
	  $scope.uploadeFiles=function(){

	  };

	  /**
	   * Metodo para remover los archivos uno a uno
	   */
	  $scope.removeItemFile=function(){

	  };
	  
	  /**
	   * Metodo para remover todos los archivos
	   */
	  $scope.removeAllItemsFile=function(){

	  };

	  /**
	   * Metodo que complementa los items determinando el tipo de documento 
	   */
	  $scope.complementTypeDocuemnt=function(){

	  };

	  /**
	   * Metodo que muestra un mensaje de confirmación, cuando se remueve un item 
	   */
	  confirmRemoveMessage=function(){

	  };

	  /**
	   * Metodo que muestra un mensaje de confirmación, cuando se remueven todos los items de la lista 
	   */
	  confirmCancelUploadMessage=function(){

	  };
	  
	  /**
	   * Metodo que convierte el objeto del componente FileUploader a el VO para enviar a la peticion
	   */
	  converterFileUploaderToVO=function(elementFileUpload,elemntVO){
		  
	  };
	  
	  /**
	   * Metodo que convierte el objeto VO el objeto del componente FileUploader 
	   */
	  converterVOToFileUploader=function(elementFileUpload,elemntVO){
		  
	  };
	
    });