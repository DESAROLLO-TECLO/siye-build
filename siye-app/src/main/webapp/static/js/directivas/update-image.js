/**
 * Directiva para carga de archivos / Image
 * Permite la carga de imagenes mostrando dos opciones mediante el parametro showInModal que mostrará el diseño sobre un modal, 
 * o se incluira dentro del diseño de la pantalla donde se integre. 
 * 			
 * 
 * 
 $scope.paramConfiguracion=new Object({
			idOrdenServ: , 			numerico 
			idProceso: ,   			numerico
			idEncuesta: ,			numerico
			idPregunta: ,			numerico
			idIncidencia: ,			numerico
			listImages: ,			Array
			nameParamFile: ,		String
	        maxSizeMb: ,			numerico Megabites
	        maxNuImage: ,			numerico
	        listTypeExtencion: ,	Array ['jpg','png','pdf'...]
	        listTpDocuemnt: ,		Array
	        nameService:    ,   	String
	        nameFunctionService: ,	String 
	        titleModal: ,			String
	        templateButonModal: ,   String
	        fileUploader: ,			Object //{FileUploader}, parametro de tipoFileUploader se inicializa en el controller de la patalla
	        //este fileUploader permite manipular el objeto inicializado en el controller de manera personalizada
  });
 * 
 */
angular.module(appTeclo).directive('updateImage', function() {
	return {
	      restrict: 'AE',
	      scope:{
	    	idElementUp:'=?',
	        showInModal:'=',
	        paramConfiguracion:'=?'
	      },transclude:false,
	      controller: function($element,$scope,$timeout,FileUploader,$injector,growl,$transclude) {
	    	  
	    	  var expedienteService=$injector.get('expedienteService');
	    	  var idElementUp=$scope.idElementUp;
	    	  //Templates para mostrar en pantalla
	    	  var desingButtonModal="views/templatemodal/templateModalUploadImage.html";
			  var desingTemplate="views/templatemodal/templateTableImagesUpload.html";
			  $scope.templateUI='';
	    	  
			  const constants=new Object({
		    		 VAL_ONE_MB_BY_BYTES:'1048576'//Equivalente de 1MB en Bytes
		    	  });
	    	  
	    	  $scope.fileUploader = null;
	    	  $scope.tpDocumentList=new Array();
	    	  
	    	  async function valdComboTpDocuemnt(){
	    		//SE VALIDA SI SE OBTIENE TIPO DE DOCUMENTO DESDE SERVICIO
				  if($scope.paramConfiguracion.listTpDocuemnt != undefined 
						  && $scope.paramConfiguracion.listTpDocuemnt.length > 0){
					  $scope.tpDocumentList=anular.copy($scope.paramConfiguracion.listTpDocuemnt);
				  }else{
					  await getCatalogoTipoDocumento(); 
				  }
	    	  }
			  
			  /**
			   * Funcion para validar configuración de directiva
			   */
	    	  initConstructor=function(){
				  
				  //se valida como se mostraran el componente componentes
				  if($scope.showInModal){
					  $scope.paramConfiguracion.idOrdenServ=9;
					  $scope.templateUI=desingButtonModal;
				  }else{
					  $scope.templateUI=desingTemplate;
			      }
				  
				  
				  //SE INICIALIZA EL COMPONENTE DE FILEUPLOADER
				  initConfigFileUploader();
				  
				  //SE CARGAN LAS IMAGENES AL COMPONENTE UPLOADER
				  uploadImageToFileUploader($scope.paramConfiguracion.listImages);
				  
				  // SE INICIAL LOS CALLBAK DEL COMPONENTE
				  initCallBacksByItem();
			  }
			  
			  /**
	    	   * FUNCIONES DE COMPONENTE FILEUPLOADER
	    	   */
			  initConfigFileUploader=function(){
				  
				  if($scope.paramConfiguracion.fileUploader == undefined){			  
					  $scope.fileUploader = new FileUploader();
					  $scope.paramConfiguracion.fileUploader=$scope.fileUploader 
				  }else{
					  $scope.fileUploader =$scope.paramConfiguracion.fileUploader;
				  }
				  
				  // SE VALIDA EL NUMERO DE IMAGENES ACEPTADAS
				  if($scope.paramConfiguracion.maxNuImage != null)
					  $scope.fileUploader.queueLimit =$scope.paramConfiguracion.maxNuImage;  
				  
				  // SE VALIDA TIPO DE EXTENCIONES QUE SE ACEPTARAN
				  if($scope.paramConfiguracion.listTypeExtencion != undefined &&
						  $scope.paramConfiguracion.listTypeExtencion.length > 0){
					  let a=$scope.paramConfiguracion.listTypeExtencion;
					  $scope.fileUploader.filters.push({
		    	   	         name: 'tpExtencion',
		    	   	         fn: function(item) {
		    	   	             var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
		    	   	             let i;
		    	   	             return a.indexOf(type) !== -1;
		    	   	         }
		    	   	   });  
				  }
				// SE VALIDA EL TAMAÑO DE LA IMAGEN
				  if($scope.paramConfiguracion.maxSizeMb != undefined){
					  $scope.fileUploader.filters.push({
		    	   	         name: 'imageFilterMaxSize',
		    	   	         fn: function (item) {
		    	   	             return item.size < (constants.VAL_ONE_MB_BY_BYTES * $scope.paramConfiguracion.maxSizeMb); // 1 MiB to bytes
		    	   	         }
		    	   	     });
				  }
			  }
	    	  
	    	
    	     //inicializa los callback de cada elemento de FileUploader
    	     initCallBacksByItem=function(){
    	    	 
    	    	 $scope.fileUploader.onWhenAddingFileFailed = function(item, filter, options) {
    	             console.info('onWhenAddingFileFailed', item, filter, options);
    	         };
    	         
    	         $scope.fileUploader.onAfterAddingFile = function(fileItem) {
    	             console.info('onAfterAddingFile', fileItem);
    	         };
    	         
    	         $scope.fileUploader.onAfterAddingAll = function(addedFileItems) {
    	        	 console.log($scope.paramConfiguracion.titleModal);
    	        	 if(addedFileItems != undefined && addedFileItems.length > 0){
    	        		 angular.forEach(addedFileItems, function(fileItem, key) {
    	        			//se genera el objeto que se enviará a la peticion
    	          			  imagenVO=new Object({
    	          				  'idOrdenServicio' :  $scope.paramConfiguracion.idOrdenServ,
    	          				  'idProceso' 		:  $scope.paramConfiguracion.idProceso,
    	          				  'idOdsEncuesta' 	:  $scope.paramConfiguracion.idEncuesta,
    	          				  'idOdsEncuesta' 	:  $scope.paramConfiguracion.idPregunta,
    	          				  'idIncidencia' 	:  $scope.paramConfiguracion.idIncidencia,
    	          				  'nbExpedienteODS'	:fileItem.file.name,
    	          				  'cdTipoArchivo'	:fileItem.file.type,
    	          				  'lbExpedienteODS'	:null
    	          			  });
    	          			
    	          			  getBase64(fileItem._file);
    	          			
    	          			async function getBase64(file) {
    	          				let result_base64 = await new Promise((resolve) => {
    	          					let fileReader = new FileReader();
    	          					fileReader.onload = (e) => resolve(fileReader.result);
    	          					fileReader.readAsArrayBuffer(file);
    	          				});
    	          				
    	          				let byteArray = new Uint8Array(result_base64);
    	          				let array = Array.from(byteArray);
    	          				
    	          				//se asigna un identificador
    	          				let unic=($scope.fileUploader.queue.length+1);
    	          				fileItem.imagenVO=imagenVO;
    	          				fileItem.unic=unic;
    	          				//se asigna el arreglo de bytes a los correspondientes parametros
    	          				imagenVO.lbExpedienteODS=array;
    	          				
    	          				let nameParam=$scope.paramConfiguracion.nameParamFile;
    	          				if(nameParam != undefined)
    	          					imagenVO[nameParam]=array;	
    	          				
    	          				if($scope.paramConfiguracion.listImages==undefined){
    	          					$scope.paramConfiguracion.listImages=new Array();
    	          				}
    	          				
    	          				//se agrega a la lista envida
    	          				$scope.paramConfiguracion.listImages.push(imagenVO);
    	          				
    	          			};
    	        			  
    	        		 });
    	        	 }
    	         };
    	     };
    	     
    	     //CONVIERTE UNA CADENA BASE64 A OBJETO FILE PARA EL COMPONENTE FILE UPLOADER
    	     urltoFile=function(dataurl, filename, mimeType){
    	    	 var arr = dataurl.split(','),
    	         mime = arr[0].match(/:(.*?);/)[1],
    	         bstr = atob(arr[1]), 
    	         n = bstr.length, 
    	         u8arr = new Uint8Array(n);
    	         
    		     while(n--){
    		         u8arr[n] = bstr.charCodeAt(n);
    		     }
    		     
    		     return new File([u8arr], filename, {type:mimeType});
    	     }
    	     
    	     //METODO QUE RECORRE LA LISTA RECIBIDA Y LAS CARGA EN EL COMPONENTE FILEUPLOADER
    	     uploadImageToFileUploader=function(listImages){
    	    	 let listFile=new Array();
    	    	 if(listImages != undefined 
    	    			 && listImages.length > 0){
    	    		 let i;
    	    		 let nameParam=$scope.paramConfiguracion.nameParamFile;
       				
    	    		 for(i=0; i<listImages.length; i++){
    	    			 
    	    			 let itemVO=listImages[i];
    	    			 
    	    			 let base64Image='data:'+itemVO.cdTipoArchivo+';base64,'+itemVO.lbExpedienteODS;
    	    			 
    	    			 if(nameParam != undefined){
    	    			  let base64Image='data:'+itemVO.cdTipoArchivo+';base64,'+itemVO[nameParam];
    	    			 }
    	    			 
    	    			 let file=urltoFile(base64Image,itemVO.nbExpedienteODS,itemVO.cdTipoArchivo);
	   	   				 let fileItem =new FileUploader.FileItem(fileUploader,file);
	   	   				 fileItem.progress = 100;
	   	   				 fileItem.isUploaded = true;
	   	   				 fileItem.isSuccess = true;
	   	   				 fileItem.nameImg=itemVO.nbExpedienteODS;
	   	   				 fileItem.imagenVO=itemVO;
	   	   				 fileItem.unic=($scope.fileUploader.queue.length+1);
	   	   				 listFile.push(fileItem);
    	    		 }
    	    		 $scope.fileUploader.queue=listFile;
    	    	 }
    	     };
	    	  
	    	  /**
	    	   * FIN FUNCIONES DE COMPONENTE FILEUPLOADER
	    	   */
    	     
    	     
    	     /**
    	      * Funciones y parametros para manimpulacion de la vista del template
    	      */
    	     $scope.panelAuxiliar=new Object({
    	    	 isCheckAll:false,
    	    	 showComboTpDocument:false
    	     });
    	     
    	     getCatalogoTipoDocumento=function(){
    	    	 
    	    	 expedienteService.getCatTpDocumento().success(function(response){
    	    		 $scope.tpDocumentList=response;
    	    	 }).error(function (error){
    	    		 $scope.tpDocumentList=$scope.paramConfiguracion.listTpDocuemnt;
    	    	 });
    	     }
    	     
    	   //marca todas la images de la configuración actual
    	     $scope.selectAllForCopy=function(){
    	    	 
    	     };
    	     
    	   //clasifica las imagenes de acuerdo a la configuracion seleccionada de los ids
    	     $scope.cutImages=function(){
    	    	 
    	     };
    	     
    	     //clasifica las imagenes de acuerdo a la configuracion seleccionada de los ids
    	     $scope.pasteImages=function(){
    	    	 
    	     }
    	     
    	     $scope.saveImageItem=function(item){
    	    	 let listImages=new Array();
    	    	 listImages.push(item);
    	    	 serviceSave(listImages);
    	     };
    	     
    	     //Funcion para guardar las imagenes, se valida si se tiene una funcio en especifico
    	     $scope.saveImagesAll=function(){
    	    	 
    	    	 let listImages=getListImageToSaved();
    	    	 
    	    	 serviceSave(listImages);
    	    	 
    	     };
    	     
    	     serviceSave=function(listImages){
    	    	 
    	    	 let service=angular.copy(expedienteService);
    	    	 let nameServiceSave='saveOrUpdateExpediente';
    	    	 
    	    	 if($scope.paramConfiguracion.nameService != undefined){
    	    		 
    	    		 service=$injector.get($scope.paramConfiguracion.nameService);
    	    		 
    	    		 if(service != undefined)
    	    			 if($scope.paramConfiguracion.nameFunctionService != undefined){
    	    				 
    	    				 nameServiceSave=$scope.paramConfiguracion.nameFunctionService;
    	    				 
    	    			 }
    	    	 }
    	    	 
    	    	 service[nameServiceSave](listImages).success(function(response){
    	    		 growl.success('Imagenes guardadas correctamente', { ttl: 4000 });
    	    		 uploadImageToFileUploader(response);
    	    	 }).error(function(error){
    	    		 growl.error(error,{ ttl: 4000 });
    	    	 });
    	     };
    	     
    	     //Retorna la lista de images que se deberá de persistir
    	     getListImageToSaved=function(){
    	    	 let listImagesSaved=[];
    	    	 angular.forEach($scope.fileUploader.queue, function(fileItem, key) {
    	    		 //se valida que no se haya enviado a back prebiamente
    	    		 if(fileItem.progress != 100 && fileItem.isUploaded
    	    				 && fileItem.isSuccess && fileItem.imagenVO.idExpedienteODS != undefined){
    	    			 listImagesSaved.push(fileItem.imagenVO);
    	    		 }
    	    	 });
    	    	 
    	    	 return listImagesSaved;
    	     };
    	     
    	      //Metodo para eliminar una imagen del componente y de la lista enviada
    	     $scope.cancelUploadeImageItem=function(){
    	    	 
    	     };
    	     
    	     // Metodo para eliminar todoas las imagenes que se cargaron y se eliminan de la lista de imagenes enviada
    	     $scope.cancelUploadImages=function(){
    	    	 
    	     };
    	     
    	     /**
    	      * FIN Funciones y parametros para manimpulacion de la vista del template
    	      */
    	     
    	     /**
			   * FUNCIONES DE MODAL
			   */
			  $scope.cerrarModal=function(){
				  //preguntar si hay nuevas images cargadas, que no se hayan guardado, 
				  //si se confirma cerrar modal y descartar imagenes en lista enviada
				  $('#modalUpdateImage').modal('hide');
				  $scope.fileUploader = null;
			  };
				
	    	  $scope.showModal=function(){
				  $('#modalUpdateImage').modal('show');
				  initConstructor();
				 
			  };
			  /**
			   * FUNCIONES DE MODAL
			   */
			  
			  
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
			  
	    	  
	    	initConstructor();
	    	valdComboTpDocuemnt();
	    	 
	      },template:'<div ng-include="templateUI"></div>'
	};
	    
});
 