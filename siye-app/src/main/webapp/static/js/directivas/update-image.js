/**
 * Directiva para carga de archivos / Image
 * Permite la carga de imagenes mostrando dos opciones mediante el parametro showInModal que mostrará el diseño sobre un modal, 
 * o se incluira dentro del diseño de la pantalla donde se integre. 
 * 			
 * 
 *
 $scope.paramConfSav= new Object({
 			idOrdenServ: , 			numerico **REQUERIDO (No aplica incidencia)
			cdOrdenServicio:,		String
			idProceso: ,   			numerico
			idEncuesta: ,			numerico
			idPregunta: ,			numerico
			idIncidencia:  			numerico
 });
 
 $scope.paramConfComponent=new Object({
	        maxSizeMb: ,			numerico Megabites
	        maxNuImage: ,			numerico
	        listTypeExtencion: ,	Array ['jpg','png','pdf'...]
	        listTpDocuemnt: ,		Array
	        nameService:    ,   	String
	        nameFunctionService: ,	String 
	        titleModal: ,			String
	        templateButonModal: ,   String
  });
  
 * 
 */
angular.module(appTeclo).directive('updateImage',
                                  function($compile,$timeout,FileUploader,$location,
                                		  $injector,growl,showAlert,constTemplateExpediente) {
	return {
	      restrict: 'E',
	      scope:{
	    	idElementUp:'=',
	    	redirec:'=',
	        showInModal:'=',
	        include:'=',
	        paramConfSav:'=',
	        paramConfComponent:'=',
	        listImages:'=?',
	        nameParamFile:'@'
	      },
	       replace: true,
	       terminal: true,
	       transclude: true,
	      template:'<div id="containerDirective"></div>',
	      link: function(scope, $element, attrs) {
	    	  
	    	  scope.fileUploaderDvt=new FileUploader();
	    	  
	    	  scope.showImageStrBase64='';
	    	  //Constantes generales
	    	  const constants=new Object({
		    		 VAL_ONE_MB_BY_BYTES:'1048576'//Equivalente de 1MB en Bytes
		      });
		     //lista para clasificar por tipo de documento
		     scope.tpDocumentList=scope.paramConfComponent.listTpDocuemnt ==undefined ? new Array() : scope.paramConfComponent.listTpDocuemnt;
	    	  //Variables para el diseño incial del template
	    	  let includeDeseingModal=constTemplateExpediente.templateModalExp;
	    	  let incudeModalCarousel=constTemplateExpediente.templateCarouseModal;
	    	  var idButton=scope.idElement+"idButtonShowModal";
	    	  var btnModal="";
	    	  scope.nameParamFile=null;
	    	  let includeDeseing=constTemplateExpediente.templateTableExp;
	    	    
			  //se valida como se mostraran el componente componentes
				let div ='';
				let buttonModal='<div id="contButton">'+
					'<button class="btn btn-danger"> '+                                          
						'<i class="fa fa-clipboard" aria-hidden="true"></i></i> Cargar Imagenes' +                         
					'</button>'+
				'</div>';
				
			  if(scope.include != undefined && scope.include){
				  div= angular.element(includeDeseing);
				  $element.append(div);
			  }else if(scope.showInModal != undefined && scope.showInModal){
				  
				  
				  if(scope.paramConfComponent != undefined &&  
						  scope.paramConfComponent.templateButonModal != undefined
						  && scope.paramConfComponent.templateButonModal != ''){
					  buttonModal=angular.copy(scope.paramConfComponent.templateButonModal);
				  }
				  
				  btnModal=angular.element(buttonModal);
				  $(btnModal).prop("id",idButton);
				  $element.append(btnModal);
				  div=angular.element(includeDeseingModal);
				  $element.append(div);
			  }else{
				  btnModal=angular.element(buttonModal);
				  $(btnModal).prop("id",idButton);
				  $element.append(btnModal);
			  }
			  
			  let crousel=  angular.element(incudeModalCarousel);
			  $element.append(crousel);
			  
			  
	    	  //Metodo principal que ejecuta toda la configuración inicial de la directiva
	    	  intDirective=function(){

	    		  
	    		  //SE INICIALIZA EL COMPONENTE DE FILEUPLOADER  
	    		  initConfigFileUploader();
	    		
				  //SE CARGAN LAS IMAGENES AL COMPONENTE UPLOADER
				 uploadImageToFileUploader(scope.listImages);
				  
				  // SE INICIAL LOS CALLBAK DEL COMPONENTE
				  initCallBacksByItem();
	    		  
	    	  };
	    	  
	    	  //Variable con la injeccion por defecto del servicio para expedientes
	    	  var expedienteService=$injector.get('expedienteService');
	    	  
	    	// SE DEFINE EL DISEÑO DEL TEMPLATE QUE SE MOSTRARA
	    	  
	    	//SE VALIDA SI SE OBTIENE TIPO DE DOCUMENTO DESDE SERVICIO
	    	  async function valdComboTpDocuemnt(){
		    		
					  if(scope.paramConfComponent.listTpDocuemnt != undefined 
							  && scope.paramConfComponent.listTpDocuemnt.length > 0){
						  scope.tpDocumentList=scope.paramConfComponent.listTpDocuemnt;
					  }else{
						  await getCatalogoTipoDocumento(); 
					  }
	    	  }
	    	  
    	  /**
    	   * Observador para la carga de imagenes
    	   */
    	  scope.$watch('lisImages', function(newValue,oldValue) {
    		  if(newValue != undefined && oldValue != undefined)
    			  if(!angular.equals(newValue, oldValue)){
    				  uploadImageToFileUploader(scope.listImages);	
    			  }
    	  },true);
	    	
    	  /**
    	   * Obsevador para las configuraciones del componente
    	   */
    	  scope.$watch('paramConfComponent', function(newValue,oldValue) {
    		  
    		  if(newValue != undefined && oldValue != undefined)
    			  if(!angular.equals(newValue, oldValue)){
    				  scope.paramConfComponent=newOptions;
    	    		  //SE INICIALIZA EL COMPONENTE DE FILEUPLOADER
    				  //initConfigFileUploader();
    				  
    				  //SE CARGAN LAS IMAGENES AL COMPONENTE UPLOADER
    				  //uploadImageToFileUploader(scope.listImages);
    				  
    				  // SE INICIAL LOS CALLBAK DEL COMPONENTE
    				  //initCallBacksByItem();  
    			  }
    	  });
    	  
    	  /**
    	   * FUNCIONES DE COMPONENTE FILEUPLOADER
    	   */
		  initConfigFileUploader=function(){
			  
			  // SE VALIDA EL NUMERO DE IMAGENES ACEPTADAS
			  if(scope.paramConfComponent.maxNuImage != null)
				  scope.fileUploaderDvt.queueLimit = scope.paramConfComponent.maxNuImage; 
			  
			  // SE VALIDA TIPO DE EXTENCIONES QUE SE ACEPTARAN
			  if(scope.paramConfComponent.listTypeExtencion != undefined &&
					  scope.paramConfComponent.listTypeExtencion.length > 0){
				  let a=scope.paramConfComponent.listTypeExtencion;
				  scope.fileUploaderDvt.filters.push({
	    	   	         name: 'tpExtencion',
	    	   	         fn: function(item) {
	    	   	             let type = item.type.slice(item.type.lastIndexOf('/') + 1);
	    	   	             return a.indexOf(type) !== -1;
	    	   	         }
	    	   	   });  
			  }
			// SE VALIDA EL TAMAÑO DE LA IMAGEN
			  if(scope.paramConfComponent.maxSizeMb != undefined){
				  scope.fileUploaderDvt.filters.push({
	    	   	         name: 'imageFilterMaxSize',
	    	   	         fn: function (item) {
	    	   	             return item.size < (constants.VAL_ONE_MB_BY_BYTES * scope.paramConfComponent.maxSizeMb); // 1 MiB to bytes
	    	   	         }
	    	   	     });
			  }
		  } 
		 
		//inicializa los callback de cada elemento de FileUploader
 	     initCallBacksByItem=function(){
 	    	 
 	    	scope.fileUploaderDvt.onWhenAddingFileFailed = function(item, filter, options) {
 	             console.info('onWhenAddingFileFailed', item, filter, options);
 	             let msj="El archivo no cumple con los parámetros permitidos"
 	             switch(filter.name){
 	             	case 'tpExtencion':
 	             		msj='La extención del archivo no es admitida';
 	             	break;
 	             	case 'imageFilterMaxSize':
 	             		msj='El documento excedio el tamaño maxímo ('+scope.paramConfComponent.maxSizeMb+' MB)';
 	 	            break;
 	             	case 'queueLimit':
 	             		msj='Ha llegado al número ('+ scope.paramConfComponent.maxNuImage+') máximo de imagenes permitidas';
 	 	            break;
 	             }
 	            
 	             growl.error(msj, { ttl: 4000 }); 
 	         };
 	         
 	        scope.fileUploaderDvt.onAfterAddingFile = function(fileItem) {
 	             console.info('onAfterAddingFile', fileItem);
 	             console.log('Numero total de imagenes', scope.fileUploaderDvt.queue.length);
 	         };
 	         
 	        scope.fileUploaderDvt.onAfterAddingAll = function(addedFileItems) {
 	        	 if(addedFileItems != undefined && addedFileItems.length > 0){
 	        		 angular.forEach(addedFileItems, function(fileItem, key) {
 	        			//se genera el objeto que se enviará a la peticion
 	          			  imagenVO=new Object({
 	          				  'idOrdenServicio' :  scope.paramConfSav.idOrdenServ,
 	          				  'idProceso' 		:  scope.paramConfSav.idProceso,
 	          				  'idOdsEncuesta' 	:  scope.paramConfSav.idEncuesta,
 	          				  'idOdsEncuesta' 	:  scope.paramConfSav.idPregunta,
 	          				  'idIncidencia' 	:  scope.paramConfSav.idIncidencia,
 	          				  'nbExpedienteODS'	:  fileItem.file.name,
 	          				  'cdTipoArchivo'	:   fileItem.file.type.slice(fileItem.file.type.lastIndexOf('/') + 1),
 	          				  'size'            :  fileItem.file.size,
 	          				  'lbExpedienteODS'	:null,
 	          				  'tpDocumentList'  :angular.copy(scope.tpDocumentList)
 	          			  });
 	          			
 	          			  getBase64(fileItem._file);
 	          			
 	          			async function getBase64(file) {
 	          				
 	          				let result_base64 = await new Promise((resolve) => {
 	          					let fileReader = new FileReader();
 	          					fileReader.onload = (e) => resolve(fileReader.result);
 	          					fileReader.readAsDataURL(file);
 	          					//fileReader.readAsArrayBuffer(file);
 	          				});
 	          				
 	          				result_base64=result_base64.replace('data:', '').replace(/^.+,/, '');
 	          				//se asigna un identificador
 	          				let unic=(scope.fileUploaderDvt.queue.length+1);
 	          				fileItem.idEleFile=scope.idElementUp;
 	          				imagenVO.iniuc=unic;
 	          				fileItem.imagenVO=imagenVO;
 	          				fileItem.unic = unic;
 	          				fileItem.nameImg=fileItem.file.name;
 	          				//se asigna el arreglo de bytes a los correspondientes parametros
 	          				imagenVO.lbExpedienteODS=result_base64;
 	          				imagenVO.strBase64=result_base64;
 	          				let nameParam=scope.nameParamFile;
 	          				if(nameParam != undefined)
 	          					imagenVO[nameParam]=result_base64;	
 	          				
 	          				if(scope.listImages==undefined){
 	          					scope.listImages=new Array();
 	          				}
 	          				
 	          				//se agrega a la lista envida
 	          				scope.listImages.push(imagenVO);
 	          			};
 	        			  
 	        		 });
 	        	 }
 	         };
 	     };
 	     
	     //FUNCION QUE VALIDA SI ES IMAGEN O NO
	     scope.isImage= function(item) {
	         var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
	         return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
	     };
	     
	   //CONVIERTE UNA CADENA BASE64 A OBJETO FILE PARA EL COMPONENTE FILE UPLOADER
	     urltoFile=function(dataurl, filename, mimeType){
	    	 var arr = dataurl.split(','),
	         mime = arr[0].match(/:(.*?);/)[1],
	         bstr = atob(dataurl), 
	         n = bstr.length, 
	         u8arr = new Uint8Array(n);
	         
		     while(n--){
		         u8arr[n] = bstr.charCodeAt(n);
		     }
		     
		     return new File([u8arr], filename, {type:mimeType});
	     };
	     
	   //METODO QUE RECORRE LA LISTA RECIBIDA Y LAS CARGA EN EL COMPONENTE FILEUPLOADER
	     uploadImageToFileUploader=function(listImages){
	    	 
	    	 if(listImages != undefined 
	    			 && listImages.length > 0){
	    		 let i;
	    		 let nameParam=scope.nameParamFile;
   				
	    		 for(i=0; i<listImages.length; i++){
	    			 
	    			 let itemVO=listImages[i];
	    			 
	    			 let listUploader = scope.fileUploaderDvt.queue.filter(function (itemUpload) {
	    	        	    return (itemUpload.imagenVO.unic == itemVO.unic);
	    	        	});
	    			 
	    			 if(listUploader.length == 0){// si no se ha cargado previamente al componente no se vuelve a cargar
	    				 let base64Image=itemVO.lbExpedienteODS;
		    			 
		    			 if(nameParam != undefined){
		    			  let base64Image=+itemVO[nameParam];
		    			 }
		    			 
		    			 let file=urltoFile(base64Image,itemVO.nbExpedienteODS,itemVO.cdTipoArchivo);
	   	   				 let fileItem =new FileUploader.FileItem(scope.fileUploaderDvt,file);
	   	   				 
	   	   				 if(itemVO.idExpedienteODS != undefined){
	   	   					 fileItem.progress = 100;
	   	   					 fileItem.isUploaded = true;
	   	   					 fileItem.isSuccess = true;
	   	   				 }else{
	   	   					 fileItem.progress = 0;
	   	   					 fileItem.isUploaded = false;
	   	   					 fileItem.isSuccess = false;
	   	   				 }
	   	   				 let unic=(scope.fileUploaderDvt.queue.length+1)
	   	   				 
	   	   				 if(itemVO.unic == undefined)
	   	   					 itemVO.unic=unic;
	   	   				 
	   	   				 fileItem.nameImg=itemVO.nbExpedienteODS;
	   	   				 fileItem.imagenVO=itemVO;
	   	   				 fileItem.unic=unic;
	   	   				 fileItem.idEleFile=scope.idElementUp;
	   	   			
	   	   			    //se asigna opcion seleccionada de tipo de documento
	   	 	    	    if(fileItem.imagenVO.tpDocumentList != undefined && fileItem.imagenVO.tpDocumentList.length > 0){
	   	 	    	    	if(fileItem.imagenVO.tipoExpediente != undefined){
	   	 	    	    		let i;
	   	 	    	    		for(i=0; i<fileItem.imagenVO.tpDocumentList.length; i++){
	   	 	    	    			if(fileItem.imagenVO.tpDocumentList[i].idTipoExpediente == fileItem.imagenVO.idTipoExpediente)
	   	 	    	    				fileItem.imagenVO.tipoExpediente=fileItem.imagenVO.tpDocumentList[i];
	   	 	    	    		}
	   	 	    	    	}
	   	 	    	    }
	   	 	    	    scope.fileUploaderDvt.queue.push(fileItem); 
	    			 }
	    		 }
	    	 }
	     };
	     /**
	      * FIN FUNCIONES DE COMPONENTE FILEUPLOADER
   	   	  */
	     
	     /**
	      * Funciones y parametros para manimpulacion de la vista del template
	      */
	     scope.panelAuxiliar=new Object({
	    	 isCheckAll:false,
	    	 showComboTpDocument:false
	     });
	     
	     getImgVOParamInitial=function(imgVO){
	    	 
	    	 imgVO.idOrdenServicio=scope.paramConfSav.idOrdenServ;
	    	 imgVO.idProceso=scope.paramConfSav.idProceso;
	    	 imgVO.idOdsEncuesta=scope.paramConfSav.idEncuesta;
	    	 imgVO.idPregunta=scope.paramConfSav.idPregunta;
	    	 imgVO.idIncidencia=scope.paramConfSav.idIncidencia;
	    	 
	    	 return imgVO;
	     };
	     
	     getCatalogoTipoDocumento=function(){
	    	 
	    	 expedienteService.getCatTpDocumento().success(function(response){
	    		 scope.tpDocumentList=response;
	    	 }).error(function (error){
	    		 if(scope.paramConfComponent.listTpDocuemnt != undefined 
	    				 && scope.paramConfComponent.listTpDocuemnt.length > 0)
	    			 scope.tpDocumentList=scope.paramConfComponent.listTpDocuemnt;
	    		 else
	    			 scope.tpDocumentList=[];
	    	 });
	     }
	     
	     //Guarda una imagen consumiendo el servicio
	     scope.saveImageItem=function(item){
	    	 let listImages=new Array();
	    	 listImages.push(item.imagenVO);
	    	 serviceSave(listImages);
	     };
	     
	   //Funcion para guardar las imagenes, se valida si se tiene una funcion en especifico
	     scope.saveImagesAll=function(){
	    	 
	    	 let listImages=getListImageToSaved();
	    	 
	    	 serviceSave(listImages);
	    	 
	     };

	     serviceSave=function(listImages){
	    	 angular.forEach(listImages, function(item, key) {
	    		 if(item.tipoExpediente != undefined)
	    			 item.idTipoExpediente=item.tipoExpediente.idTipoExpediente;
	    		 	item=getImgVOParamInitial(item);
	    	 });
	    	 
	    	 let service=angular.copy(expedienteService);
	    	 let nameServiceSave='saveOrUpdateExpediente';
	    	 
	    	 if(scope.paramConfComponent.nameService != undefined){
	    		 
	    		 service=$injector.get(scope.paramConfComponent.nameService);
	    		 
	    		 if(service != undefined)
	    			 if(scope.paramConfComponent.nameFunctionService != undefined){
	    				 
	    				 nameServiceSave=scope.paramConfComponent.nameFunctionService;
	    				 
	    			 }
	    	 }
	    	 
	    	 service[nameServiceSave](listImages).success(function(response){
	    		 growl.success('Imagenes guardadas correctamente', { ttl: 4000 });
	    		 uploadImageToFileUploader(response);
	    	 }).error(function(e){
	    		 
	    		 if(e.status != null){
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
	    		 
	    		   
	    		 
	    		 growl.error(error,{ ttl: 4000 });
	    	 });
	     };
	     
	     //Retorna la lista de images que se deberá de persistir
	     getListImageToSaved=function(){
	    	 let listImagesSaved=[];
	    	 angular.forEach(scope.fileUploaderDvt.queue, function(fileItem, key) {
	    		 //se valida que no se haya enviado a back prebiamente
	    		 if(fileItem.progress != 100 && !fileItem.isUploaded
	    				 && !fileItem.isSuccess && fileItem.imagenVO.idExpedienteODS == undefined){
	    			 listImagesSaved.push(fileItem.imagenVO);
	    		 }
	    	 });
	    	 
	    	 return listImagesSaved;
	     };
	     
	     //Metodo para eliminar una imagen del componente y de la lista enviada
	     scope.cancelUploadeImageItem=function(item){
	    	
 			// se muestra modal de confirmación
 			  showAlert.confirmacion('Se eliminará esta imagen, ¿Desea continuar?',
	                confirm = () => {
	                // solamente se asocian a esta lista cuando esta imagen probiene de base de datos
	          		  if(item.isSuccess){
	          			 let imgDelete=[];
	       			     imgDelete.push(item.imagenVO);
	          			  expedienteService.deleteExpediente(imgDelete)
		          		  .success(function(reponse){
		          			scope.fileUploaderDvt.removeFromQueue(item);
		          			growl.success('Imagenes eliminadas correctamente', { ttl: 4000 });
		          		  }).error(function(error){
		          			  growl.error(error, { ttl: 4000 });
		          		  });
	          			
	          		  }else{
	          			scope.fileUploaderDvt.removeFromQueue(item);
	          		  }        			  
	                }, cancelaNotificar = () => {
	                    return;
	                });
	    	 
	     };
	     
		 //FUNCIONES DEL MODAL
		 //Funcion que muestra el modal
		 $(btnModal).on('click', function() {
			 if(scope.redirec == undefined || scope.showInModal){
				 scope.showModalBuild=true;
				 $timeout(function() {
					 $('#'+scope.idElementUp+'modalUpdateImage').modal('show');
					//SE CARGAN LAS IMAGENES AL COMPONENTE UPLOADER
					 
				 	 uploadImageToFileUploader(scope.listImages);
	     		 },100);
			 }else if(scope.redirec){
				 let paramConfSav=defineConsultaImagenesNivel();
				 paramConfSav.locatinPrev=$location.path();
				 let jsonObj=angular.toJson(paramConfSav);
				 $location.path('/cargaMasiva/cargaNivel/').search(jsonObj);
	                $('.modal-backdrop').remove();
	                
			 }
			 
    	 });
		 
		 defineConsultaImagenesNivel=function(){
			 let paramConfSav={};
			 paramConfSav.cdOs='sdssd';
			 paramConfSav.cdNivel='CDOS';
			 
			 if(scope.paramConfSav.idPregunta != undefined){//nivel pregunta
				 paramConfSav.cdNivel='CDPREGUNTA';
				 paramConfSav.valor=scope.paramConfSav.idPregunta;
			 }else if(scope.paramConfSav.idEncuesta != undefined){//nivel encuesta
				 paramConfSav.cdNivel='CDENCUESTA';
				 paramConfSav.valor=scope.paramConfSav.idPregunta;
			 }else if(scope.paramConfSav.idProceso){// nivel prceso
				 paramConfSav.cdNivel='CDPROCESO';
				 paramConfSav.valor=scope.paramConfSav.idPregunta;
			 }
			 
			 return paramConfSav;
		 }
		  
		  scope.cerrarModal=function(){
			  //preguntar si hay nuevas images cargadas, que no se hayan guardado, 
			  //si se confirma cerrar modal y descartar imagenes en lista enviada
			  
			  if(scope.fileUploaderDvt.queue.length > 0){
	    		while(scope.fileUploaderDvt.queue.length > 0){
	    			scope.fileUploaderDvt.queue.pop();
	    		}
	    	  }
			  
			  $('#'+scope.idElementUp+'modalUpdateImage').modal('hide');
			  $('.modal-backdrop').remove();
			  scope.showModalBuild=false;
		  };
		  
		  //METODO QUE PERMITE IDENTIFICAR EL TIPO DE DISPOSITIVO DONDE SE ESTA CARGANDO LA PANTALLA Y DETERMINA QUE COMPONENTES MOSTRAR
		  defComponents=function(){
			  var isMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);
				if (isMobile) {
					//do things 
				} else {
					//do things
				}
		  }
		  //METODO QUE PERMITE IDENTIFICAR EL TIPO DE DISPOSITIVO DONDE SE ESTA CARGANDO LA PANTALLA Y DETERMINA QUE COMPONENTES MOSTRAR
		  
		 //Metodo para mostrar el modal con el carrusel
		  scope.showModalImg=function(srcB64,nemaImg){
			  scope.showModalCarousel=true;
			  
			  $timeout(function() {
					 $('#'+scope.idElementUp+'modalCarousel').modal('show');
			  },100);
		  };
		  
		  scope.hideModal=function(srcB64,nemaImg){
			  $('#'+scope.idElementUp+'modalCarousel').modal('hide');
			  $('.modal-backdrop').remove();
			  scope.showModalCarousel=false;
		  };
		  
		  
		//Metodo para mostrar el modal con el carrusel
		  
		  valdComboTpDocuemnt();
		  intDirective();
		  $timeout(function() {
			  $compile($element)(scope);
		  },100);
	   }
	};
	    
});