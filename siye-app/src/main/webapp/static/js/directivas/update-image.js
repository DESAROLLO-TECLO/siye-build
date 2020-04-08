/**
 * Directiva para carga de archivos / Image
 * Permite la carga de imagenes mostrando dos opciones mediante el parametro showInModal que mostrará el diseño sobre un modal, 
 * o se incluira dentro del diseño de la pantalla donde se integre. 
 * 			
 * 
 *
 scope.paramConfSav= new Object({
 			idOrdenServ: , 			numerico **REQUERIDO (No aplica incidencia)
			cdOrdenServicio:,		String
			idProceso: ,   			numerico
			idEncuesta: ,			numerico
			idPregunta: ,			numerico
			idIncidencia:  			numerico
 });
 
 scope.paramConfComponent=new Object({
	        maxSizeMb: ,			numerico Megabites
	        listTypeExtencion: ,	Array ['jpg','png','pdf'...]
	        listTpDocuemnt: ,		Array
	        nameService:    ,   	String
	        nameFunctionService: ,	String 
	        titleModal: ,			String
	        templateButonModal: ,   String
  });
  
 * 
 */
var appt=angular.module(appTeclo);

appt.directive('updateImage',
                                  function($compile,$timeout,FileUploader,$location,$document,
                                		  $injector,growl,showAlert,constTemplateExpediente) {
	return {
	      restrict: 'E',
	      scope:{
	    	idElementUp:'@',
	    	redirec:'=',
	        showInModal:'=',
	        include:'=',
	        paramConfSav:'=',
	        paramConfComponent:'=',
	        listImages:'=?',
	        nameParamFile:'=',
	        maxNuImage:'@',
	        isIncidencia:'='
	      },
	       replace: true,
	       terminal: true,
	       transclude: true,
	      template:'<div id="containerDirective"></div>',
	      link: function(scope, $element, attrs) {
	    	  
	    	//Variable con la injeccion por defecto del servicio para expedientes
	    	  var expedienteService=$injector.get('expedienteService')
	    	  //variable de paginador
	    	  scope.viewPag={
	    			  currentPage 	: 0,
	    		      pageSize		: 3,
	    		      pages 		: []
	    	  };
	    	  
	    	  
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
			  $compile($element)(scope);
			  
			//Se obtiene el elemneto que donde se pueden arrastrar y pegar imagenes (pc)
			  let idDrop=('#zonaDrop'+scope.idElementUp);
			  var divDropable= $element.find(idDrop);
			  
			//Metodo principal que ejecuta toda la configuración inicial de la directiva
	    	  intDirective=function(){
	    		  
	    		//se asigna un estatus a las imagenes que se obtienen de base de datos, 
		    	  //sirve de manera local para invocar o no el servicio de eliminacion en back, y se asigna un parametro para identificacion local
		    	  angular.forEach(scope.listImages, function(item, key) {
		    		  item.unic=(key+1);
		    		  if(item.isSuccess == undefined || item.idExpedienteODS == undefined){
		    			  item.isSuccess=false;
		    			  item.lbExpedienteODS=result_base64;
		    			  item.strBase64=item.lbExpedienteODS;
		    		  }
		    	  });
		    	  
		    	  scope.configPages();
	    	  };
	    	  
	    	  //funcion para iniciar el paginador
	    	  scope.configPages = function() {
	    	        scope.viewPag.pages.length = 0;
	    	        var ini = scope.viewPag.currentPage - 4;
	    	        var fin = scope.viewPag.currentPage + 5;
	    	        if (ini < 1) {
	    	          ini = 1;
	    	          if (Math.ceil(scope.listImages.length / scope.viewPag.pageSize) > 10)
	    	            fin = 10;
	    	          else
	    	            fin = Math.ceil(scope.listImages.length / scope.viewPag.pageSize);
	    	        } else {
	    	          if (ini >= Math.ceil(scope.listImages.length / scope.viewPag.pageSize) - 10) {
	    	            ini = Math.ceil(scope.listImages.length / scope.viewPag.pageSize) - 10;
	    	            fin = Math.ceil(scope.listImages.length / scope.viewPag.pageSize);
	    	          }
	    	        }
	    	        if (ini < 1) ini = 1;
	    	        for (var i = ini; i <= fin; i++) {
	    	          scope.viewPag.pages.push({
	    	            no: i
	    	          });
	    	        }

	    	        if (scope.viewPag.currentPage >= scope.viewPag.pages.length)
	    	          scope.viewPag.currentPage = scope.viewPag.pages.length - 1;
	    	  };
	    	  
	    	  scope.setPage = function(index) {
	    	        scope.viewPag.currentPage = index - 1;
	    	      };
	    	  
	    	      //fin dunciones de paginador
	    	  
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
	    	  /*scope.$watch('lisImages', function(newValue,oldValue) {
	    		  if(newValue != undefined && oldValue != undefined)
	    			  if(!angular.equals(newValue, oldValue)){
	    				  //do things	
	    			  }
	    	  },true);*/
	    	  
	    	  //Se reciben las imagenes injectadas desde el directiva dragAndropFile
	    	  scope.fileDropped=function(scopeDragDrop){
	    		  let filesList = [];
	    		  
	    		  angular.forEach(scopeDragDrop.uploadedFile, function(item, key) {
    				  filesList.push(item);
 		    	  });
	    			  
	    		  // Si exsisten restricciones se omiten los archivos que no las cumplan
	    		  let files=validRestrcctionsFiles(filesList);
	    		  
	    		// los files obtenidos se asignan a la lista enviada como parametro
	    		  reinitListView(filesList);
	    		  
	    	  };
	    	  
	    	//Se obtienen los archivos en onjetoFiles se ejecuta cuando el componente input file detecta cambios, este componenete esta en el template
	    	  scope.getFilesFromInput=function(inputFiles){
	    		  let filesList=[];
    			  angular.forEach(inputFiles.files, function(item, key) {
    				  filesList.push(item);
 		    	  });
	    			  
	    		  // Si exsisten restricciones se omiten los archivos que no las cumplan
	    		  let files=validRestrcctionsFiles(filesList);
	    		  
	    		// los files obtenidos se asignan a la lista enviada como parametro
	    		  reinitListView(filesList);
	    	  };
	    	  
	    	  async function reinitListView(filesList){
	    		// los files obtenidos se asignan a la lista enviada como parametro
	    		  await addImgesToLisViewBiding(filesList);
	    		  scope.viewPag.currentPage=0;
	    		  scope.viewPag.pageSize=2;
	    		  scope.viewPag.pages=[];
	    		  scope.configPages();
	    	  }
	    	  
	    	  //se vaidan las restrciones en caso de existir
	    	  validRestrcctionsFiles=function(lisFiles){
	    		  if(scope.paramConfComponent != undefined){
	    			  let errorTypeFile=false;
	        		  let errorSizeImage=false;
	        		  let msj='Algunas archivos seleccionados no cumplieron con';
	        		  let a=scope.paramConfComponent.listTypeExtencion;
	        		  if(lisFiles != undefined && lisFiles.length > 0){
	        			let i;
	        			for(i=0; i<lisFiles.length; i++){
	        				let item=lisFiles[i];
	        				if(scope.paramConfComponent.maxSizeMb != undefined &&
	        						item.size > (constants.VAL_ONE_MB_BY_BYTES * scope.paramConfComponent.maxSizeMb)){
	        					errorSizeImage=true;
	        					lisFiles.splice(i,1);
	        					i--;
	        				}
	        				if(scope.paramConfComponent.listTypeExtencion != undefined){
	        					let type = item.type.slice(item.type.lastIndexOf('/') + 1);
	            				if(a.indexOf(type) == -1){
	            					errorTypeFile=true;
	            					lisFiles.splice(i,1);
	            					i--;
	            				}
	        				}
	        				
	        			}
	        		  }
	        		  
	        		  if(errorSizeImage){
	    					msj+=' el tamaño minimo ('+scope.paramConfComponent.maxSizeMb+' MB)';
	    			  }
	        		  if(errorTypeFile){
	        			  errorTypeFile=true;
	        			  let coma=errorSizeImage  ? ',':'';
	        			  msj+=coma+' la extención admitida';
	        		  }
	        		  if(errorSizeImage || errorTypeFile)
	        			  growl.warning(msj, {ttl: 4000});
	    		  }
	    		  
	    		  return lisFiles;
	    	  };
	    	  
	    	// se agrega a la lista que mostrará las imagenes en el IU del html
	    	 function addImgesToLisViewBiding(files){
	    		  
	    		  if(files.length > 0){
	    			  let i;
	        		  for(i=0; i<files.length; i++){
	        			  
	        			  if(scope.maxNuImage != undefined && scope.listImages.length >= scope.maxNuImage){
	        				  growl.warning('Algunas archivos no se adjuntaron se llego al limite de archivos', {ttl: 4000});
	        				  break;
	        			  }
	        			  
	        			  let file=files[i];
	        			  
	        			  let imagenVO=new Object({
	           				  'idOrdenServicio' :	null,
	           				  'idProceso' 		:	null,
	           				  'idOdsEncuesta' 	:	null,
	           				  'idPregunta' 		:	null,
	           				  'idIncidencia' 	:	null,
	           				  'nbExpedienteODS'	:	file.name,
	           				  'cdTipoArchivo'	:	file.type.slice(file.type.lastIndexOf('/') + 1),
	           				  'lbExpedienteODS'	:	null,
	           				  'tpDocumentList'  :	angular.copy(scope.tpDocumentList),
	           				  'idTipoExpediente':	null
	           			  });
	           			  
	           			  if(scope.paramConfSav != undefined){
	           				imagenVO=getImgVOParamInitial(imagenVO);
	           			  }
	           			  
		           			let unic=(scope.listImages.length+1);
	          				imagenVO.inic=unic;
	          				imagenVO.name=file.name;
	          				imagenVO.file=file;
	          				imagenVO.type=file.type;
	          				imagenVO.size=file.size;
	          				imagenVO.isSuccess=false;
	          				let isImg=('|jpg|png|jpeg|bmp|gif|'.indexOf(imagenVO.cdTipoArchivo) !== -1);
	          				imagenVO.isImage=isImg;
	          				imagenVO.showProgress=true;
	          				logobs64(file,imagenVO);
		          		
		          		if(scope.listImages==undefined){
          					scope.listImages=new Array();
          				}
          				
          				//se agrega a la lista envida siempre al inicio
          				scope.listImages.unshift(imagenVO);
	        		  }
	    		  }
	    	  };
	    	  
	    	//crackstillo
				function logobs64(file,imagenVO) {
	    			var reader = new FileReader();
	    			reader.onload = function(){
	    				
	    				let result_base64=reader.result.replace('data:', '').replace(/^.+,/, '');
	    				imagenVO.lbExpedienteODS=result_base64;
        				imagenVO.strBase64=result_base64;
        				let nameParam=scope.nameParamFile;
        				if(nameParam != undefined)
        					imagenVO[nameParam]=result_base64;	
	    			}
	    			reader.onerror = function(){
	    				growl.error('No se pudo leer los archivos seleccionados.', {ttl:5000});				
	    			}
	    			reader.readAsDataURL(file);
	    			imagenVO.showProgress=false;
	    		}
	 	     
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
		    	 
		    	 imgVO.idOrdenServicio=scope.paramConfSav.idOrdenServs;
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
		     };
		     
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
		    		 
		    		 angular.forEach(response, function(item, key) {
			    		  if(item.isSuccess == undefined || item.isSuccess==false){
			    			  item.isSuccess=true;
			    		  }
			    	  });
		    		 
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
		    	 angular.forEach(scope.listImages, function(item, key) {
		    		 //se valida que no se haya enviado a back prebiamente
		    		 if(!item.isSuccess && item.idExpedienteODS == undefined){
		    			 listImagesSaved.push(item);
		    		 }
		    	 });
		    	 
		    	 return listImagesSaved;
		     };
		     
		   //Metodo para eliminar una imagen del componente y de la lista enviada
		     scope.cancelUploadeImageItem=function(item){
		    	
	 			// se muestra modal de confirmación
	 			  showAlert.confirmacion('Se eliminará esta imagen, ¿Desea continuar?',
		                confirm = () => {
		                	let indexImg=scope.listImages.indexOf(item);
		                // solamente se asocian a esta lista cuando esta imagen probiene de base de datos
		          		  if(item.isSuccess){
		          			 let imgDelete=[];
		       			     imgDelete.push(item.imagenVO);
		          			  expedienteService.deleteExpediente(imgDelete)
			          		  .success(function(reponse){
			          			scope.listImages.splice(indexImg,1);
			          			growl.success('Imagenes eliminadas correctamente', { ttl: 4000 });
			          		  }).error(function(error){
			          			  growl.error(error, { ttl: 4000 });
			          		  });
		          			
		          		  }else{
		          			scope.listImages.splice(indexImg,1);
		          		  }
		                }, cancelaNotificar = () => {
		                    return;
		                });
		     };
		     
		  // se eliminan las todas las imagenes
		     scope.cancelAllImage=function(){
		    	 showAlert.confirmacion('Se eliminarán las imagenes, ¿Desea continuar?',
			                confirm = () => {
			                // solamente se asocian a esta lista cuando esta imagen probiene de base de datos
			                	let imgDelete=[];
			                	angular.forEach(scope.listImages, function(imagenVO, key) {
				          			if(imagenVO.isSuccess){
				          				imgDelete.push(imagenVO);
				          			}
				          		});
			                	
			                	if(imgDelete.length > 0){
			          			 
			          			  expedienteService.deleteExpediente(imgDelete)
				          		  .success(function(reponse){
				          			growl.success('Imagenes eliminadas correctamente', { ttl: 4000 });
				          			
				          		  }).error(function(error){
				          			  growl.error(error, { ttl: 4000 });
				          		  });
			          			
			          		  }
			                 scope.listImages=[];
			          		
			          		if(imgDelete.length > 0){
			          			scope.listImages=imgDelete;
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
				  
				  $('#'+scope.idElementUp+'modalUpdateImage').modal('hide');
				  $('.modal-backdrop').remove();
				  scope.showModalBuild=false;
			  };
			  
			//METODO QUE PERMITE IDENTIFICAR EL TIPO DE DISPOSITIVO DONDE SE ESTA CARGANDO LA PANTALLA Y DETERMINA QUE COMPONENTES MOSTRAR
			  isMobile=function(){
				  var isMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);
					if (isMobile) {
						return true;
					} else {
						return false;
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
	   }
	};    
});

appt.filter('startFromGrid', function() {
	  return function(input, start) {
	    start = +start;
	    return input.slice(start);
	  }
	});