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
	        title: ,			String
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
	        nameParamFile:'=',
	        maxNuImage:'@',
	        isIncidencia:'=',
	        filterViewImg:'=?'
	      },
	       replace: true,
	       terminal: true,
	       transclude: true,
	      template:'<div id="containerDirective"></div>',
	      link: function(scope, $element, attrs) {
	    	  
	    	   var listImg = scope.$eval(attrs.listImages);
	    	   
	    	   scope.listImages=listImg == undefined ? [] : listImg;
	    	   scope.listFiles=new Array();
	    	  scope.imagePreview=new Object();	    	  
	    	  
	    	//Variable con la injeccion por defecto del servicio para expedientes
	    	  var expedienteService=$injector.get('expedienteService')
	    	  //variable de paginador
	    	  scope.paramConfigPage=new Object({
	    		  itemsPerPage:2,
	    		  maxSize:5,
	    		  bigCurrentPage:1,
	    		  bigTotalItems:scope.listImages.length
	    	  });
	    	  
	    	  scope.viewPag={
	    			  currentPage 	: 0,
	    		      pageSize		: 2,
	    		      pages 		: []
	    	  };
	    	  
	    	  scope.view={
	    			  rowsPerPage:2,
	    			  filter:null
	    	  };
	    	  
	    	  scope.showCombo=false;
	    	  
	    	  //Constantes generales
	    	  const constants=new Object({
		    		 VAL_ONE_MB_BY_BYTES:'1048576'//Equivalente de 1MB en Bytes
		      });
		     //lista para clasificar por tipo de documento
		     scope.tpDocumentList=scope.paramConfComponent.listTpDocuemnt ==undefined ? new Array() : scope.paramConfComponent.listTpDocuemnt;
	    	  //Variables para el diseño incial del template
	    	  let includeDeseingModal=constTemplateExpediente.templateModalExp;
	    	  let incudeModalCarousel=constTemplateExpediente.templateCarouseModal;
	    	  var idButton=scope.idElementUp+"idButtonShowModal";
	    	  var btnModal="";
	    	  let includeDeseing=constTemplateExpediente.templateTableExp;
	    	    
			  //se valida como se mostraran el componente componentes
				let div ='';
				let buttonModal='<button class="btn btn-danger"> '+                                          
									'<i class="fa fa-picture-o" aria-hidden="true"></i></i> Cargar Imagenes' +                         
								'</button>';
				
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
			  async function intDirective(){
	    		  
				  await scope.valdComboTpDocuemnt();
				  
				  $timeout(function() {
					  scope.complementsDataImage();
				  },350);
	    	  };
	    	  
	    	  scope.complementsDataImage=function(){
	    		//se asigna un estatus a las imagenes que se obtienen de base de datos, 
		    	  //sirve de manera local para invocar o no el servicio de eliminacion en back, y se asigna un parametro para identificacion local
		    	  angular.forEach(scope.listImages, function(item, key) {
		    		  let file= scope.urltoFile(item.lbExpedienteODS, item.nbExpedienteODS, item.cdTipoArchivo);
		    		  file.unic=(key+1);
		    		  file.strBase64=item.lbExpedienteODS;
		    		  file.tpDocumentList=angular.copy(scope.tpDocumentList);
		    		  let isImg=('|jpg|png|jpeg|bmp|gif|'.indexOf(item.cdTipoArchivo) !== -1);
		    		  file.isImage=isImg;
		    		  file.isSuccess = item.idExpedienteODS != undefined;

		    		// SE VALIDA SI TIENE UN TP DE DOCUMENTO PREBIAMENTE ASIGNADO
		    		  if(item.idTipoExpediente != undefined){
	    				  let i;
	    				  let a=file.tpDocumentList;
	    				  for(i=0; i<a.length; i++){
	    					  
	    					  if(item.idTipoExpediente == a[i].idTipoExpediente){
	    						  file.tipoExpediente = a[i];
	    						  $timeout(function() {
	    							  $("#select2-tpDoc"+item.unic+''+scope.idElementUp+"-container").text(item.tipoExpediente.nbTipoExpediente);
	    						  },350);
	    						  break;
	    					  }
	    				  }
	    			  }
		    		  //hasta aqui control Z
		    		  scope.listFiles.push(file);
		    	  });
	    	  };
	    	  
	    	  //SE VALIDA SI SE OBTIENE TIPO DE DOCUMENTO DESDE SERVICIO
	    	  scope.valdComboTpDocuemnt=function(){
	    		  scope.showCombo=false;
					  if(scope.paramConfComponent.listTpDocuemnt != undefined 
							  && scope.paramConfComponent.listTpDocuemnt.length > 0){
						  scope.showCombo=true;
						  scope.tpDocumentList=scope.paramConfComponent.listTpDocuemnt;
					  }else{
						  scope.getCatalogoTipoDocumento();
					  }
	    	  }
	    	  
	    	  //Se reciben las imagenes injectadas desde el directiva dragAndropFile
	    	  scope.fileDropped=function(scopeDragDrop){
	    		  let filesList = [];
	    		  
	    		  angular.forEach(scopeDragDrop.uploadedFile, function(item, key) {
    				  filesList.push(item);
 		    	  });
	    			  
	    		  // Si exsisten restricciones se omiten los archivos que no las cumplan
	    		  let files=scope.validRestrcctionsFiles(filesList);
	    		  
	    		// los files obtenidos se asignan a la lista enviada como parametro
	    		  scope.reinitListView(files);
	    	  };
	    	  
	    	//Se obtienen los archivos en onjetoFiles se ejecuta cuando el componente input file detecta cambios, este componenete esta en el template
	    	  scope.getFilesFromInput=function(inputFiles){
	    			  
	    		  // Si exsisten restricciones se omiten los archivos que no las cumplan
	    		  let files=scope.validRestrcctionsFiles(inputFiles);
	    		  
	    		// los files obtenidos se asignan a la lista enviada como parametro
	    		  scope.reinitListView(files);
	    	  };
	    	  
	    	  scope.reinitListView=function(filesList){
	    		// los files obtenidos se asignan a la lista enviada como parametro
	    		  scope.addImgesToLisViewBiding(filesList);
	    	  }
	    	  
	    	  //se vaidan las restrciones en caso de existir
	    	  scope.validRestrcctionsFiles=function(lisFiles){
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
	    					msj+=' el tamaño maxímo ('+scope.paramConfComponent.maxSizeMb+' MB)';
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
	    	  scope.addImgesToLisViewBiding=function(files){
	    		  
	    		  if(files.length > 0){
	    			  let i;
	        		  for(i=0; i<files.length; i++){
	        			  
	        			  if(scope.maxNuImage != undefined && scope.listFiles.length >= scope.maxNuImage){
	        				  growl.warning('Algunas archivos no se adjuntaron se llego al limite de archivos', {ttl: 4000});
	        				  break;
	        			  }
	        			  
	        			  let file=files[i];
	           			  
	           			let unic=(scope.listFiles.length+1);
	           			file.unic=unic;
	           			file.isSuccess=false;
	           			let type = '|' + file.type.slice(file.type.lastIndexOf('/') + 1) + '|';
          				let isImg=('|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1);
          				file.isImage=isImg;
          				file.showProgress=true;
		          		
		          		if(scope.listFiles==undefined){
          					scope.listFiles=new Array();
          				}
          				
          				//se agrega a la lista envida siempre al inicio
          				scope.listFiles.unshift(file);
	        		  }
	    		  }
	    	  };
	    	  
	    	//crackstillo
				scope.logobs64=function(file,imagenVO) {
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
		     scope.urltoFile=function(dataurl, filename, mimeType){
		    	 var arr = dataurl.split(','),
		         bstr = atob(dataurl), 
		         n = bstr.length, 
		         u8arr = new Uint8Array(n);
		         
			     while(n--){
			         u8arr[n] = bstr.charCodeAt(n);
			     }
			     
			     return new File([u8arr], filename, {type:'image/'+mimeType});
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
		     
		     scope.getImgVOParamInitial=function(imgVO){
		    	 
		    	 imgVO.idOrdenServicio=scope.paramConfSav.idOrdenServ;
		    	 imgVO.idProceso=scope.paramConfSav.idProceso;
		    	 imgVO.idOdsEncuesta=scope.paramConfSav.idEncuesta;
		    	 imgVO.idPregunta=scope.paramConfSav.idPregunta;
		    	 imgVO.idIncidencia=scope.paramConfSav.idIncidencia;
		    	 
		    	 return imgVO;
		     };
		     
		     scope.getCatalogoTipoDocumento=function(){
		    	 
		    	 expedienteService.getCatTpDocumento().success(function(response){
		    		 
		    		 scope.tpDocumentList=response;
		    		 scope.showCombo=true;
		    		 if(response == undefined || response.length == 0)
		    			 scope.showCombo=false;
		    		 
		    	 }).error(function (error){
		    		 if(scope.paramConfComponent.listTpDocuemnt != undefined 
		    				 && scope.paramConfComponent.listTpDocuemnt.length > 0){
		    			 scope.tpDocumentList=scope.paramConfComponent.listTpDocuemnt;
		    			 scope.showCombo=true;
		    		 }else{
		    			 scope.showCombo=false;
		    			 scope.tpDocumentList=[];
		    		 }
		    			 
		    	 });
		     };
		     
		     //Guarda una imagen consumiendo el servicio
		     scope.saveImageItem=function(item,form,nameComponent){
		    	 
		    	 if (form[nameComponent].$invalid) {
		    		 form[nameComponent].$dirty=true;
		              growl.error('Formulario incompleto');
		              return;
		          }
		    	 
		    	 let listImages=new Array();
		    	 listImages.push(item);
		    	 scope.serviceSave(listImages);
		     };
		     
		     //metodo que se crea con alcance de controller padre, se puede invocar desde el controller
		     scope.$parent.$parent.isValidFormImages=function(message){
		    	 
		    	 if(scope.listImages == undefined || scope.listImages.length == 0 || scope.formTpDocument.$invalid){
		    		 	showAlert.requiredFields(scope.formTpDocument);
		    		 	
		    		 	if(message != undefined && message != '')
		    		 		growl.error(message);
		              return false; 
		    	 }
		    	 
		    	 return true;
		     };
		     
		     //METODO QUE CON ALCANCE DESDE EL CONTROLADOR PADRE, PERMITE COMPLEMENTAR LA LISTA DE IMAGENES DESDE EL CONTROLER
		     scope.$parent.$parent.updateViewDirective = function(inListImages){
		    	 let listImages=angular.copy(inListImages);
		    	  angular.forEach(listImages, function(item, key) {
		    		  item.unic=(key+1);
		    		  item.strBase64=item.lbExpedienteODS;
		    		  
		    		  if(item.idExpedienteODS != undefined){
		    			  item.isSuccess=true;
		    			  let file= scope.urltoFile(item.lbExpedienteODS, item.nbExpedienteODS, item.cdTipoArchivo);
		    			  item.name=file.name;
		    			  item.size=file.size;
		    			  item.tpDocumentList=angular.copy(scope.tpDocumentList);
		    			  let isImg=('|jpg|png|jpeg|bmp|gif|'.indexOf(item.cdTipoArchivo) !== -1);
		    			  item.isImage=isImg;
		    			  
		    			  // SE VALIDA SI TIENE UN TP DE DOCUMENTO PREBIAMENTE ASIGNADO
		    			  if(item.idTipoExpediente != undefined){
		    				  let i;
		    				  let a=item.tpDocumentList;
		    				  for(i=0; i<a.length; i++){
		    					  
		    					  if(item.idTipoExpediente == a[i].idTipoExpediente){
		    						  item.tipoExpediente = a[i];
		    						  $timeout(function() {
		    							  $("#select2-tpDoc"+item.unic+''+scope.idElementUp+"-container").text(item.tipoExpediente.nbTipoExpediente);
		    						  },300);
		    						  break;
		    					  }
		    				  }
		    			  }
		    			  
		    		  }else{
		    			  item.isSuccess=false;
		    		  }
		    	  });
		    	  
		    	  scope.listImages=listImages;
		     };
		     
		     scope.$parent.$parent.getValueListImageDirective=function(){
		    	 let resultImages= angular.copy(scope.listImages);
		    	return resultImages; 
		     };
		     
		   //Funcion para guardar las imagenes, se valida si se tiene una funcion en especifico
		     scope.saveImagesAll=function(form){
		    	 
		    	 if (form.$invalid) {
		              showAlert.requiredFields(form);
		              growl.error('Formulario incompleto');
		              return;
		          }
		    	 
		    	 let listImages=scope.getListImageToSaved();
		    	 
		    	 scope.serviceSave(listImages);
		    	 
		     };
		     
		     scope.serviceSave=function(listImages){
		    	 angular.forEach(listImages, function(item, key) {
		    		 if(item.tipoExpediente != undefined)
		    			 item.idTipoExpediente=item.tipoExpediente.idTipoExpediente;
		    		 	item= scope.getImgVOParamInitial(item);
		    	 });
		    	 
		    	 let service=expedienteService;
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
		    		 
		    		 let i;
		    		 let j;
		    		 for(i=0; i<scope.listImages.length; i++){
		    			 let itemUno=scope.listImages[i];
		    			 for(j=0; j<response.length; j++){
		    				 let itemDos=response[j];
		    				 if(itemUno.nbExpedienteODS == itemDos.nbExpedienteODS){
		    					 itemUno.isSuccess=true;
		    					 itemUno.idExpedienteODS=itemDos.idExpedienteODS;
		    					 break;
		    				 }
		    			 }
		    		 }
		    		 
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
		    	 });
		     };
		     
		   //Retorna la lista de images que se deberá de persistir
		     scope.getListImageToSaved=function(){
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
		       			     imgDelete.push(item);
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
			                	let i;
			                	for(i=0; i<scope.listImages.length; i++){
			                		let imagenVO=scope.listImages[i];
			                		if(!imagenVO.isSuccess){
			                			scope.listImages.splice(i,1);
			                			i--;
				          			}
			                	}
			                	
			                	if(scope.listImages.length > 0){
			          			 
			          			  expedienteService.deleteExpediente(scope.listImages)
				          		  .success(function(reponse){
				          			  scope.listImages=[];
				          			growl.success('Imagenes eliminadas correctamente', { ttl: 4000 });
				          		  }).error(function(error){
				          			growl.error('Las siguientes imagenes no se pudieron eliminar, favor de intentar nuevamente', { ttl: 4000 });
				          		  });
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
					 if(scope.listImages == undefined || scope.listImages.length == 0)
						  scope.getImagesByLevel();
					 else{
						 $timeout(function() {
							 $('#'+scope.idElementUp+'modalUpdateImage').modal('show');
			     		 },100);
					 }
				 }else if(scope.redirec){
					 let paramConfSav=scope.defineConsultaImagenesNivel();
					 paramConfSav.locatinPrev=$location.path();
					 paramConfSav.optionComponent=scope.paramConfComponent;
					 paramConfSav.optionComponent.listTpDocuemnt=scope.tpDocumentList;
					 paramConfSav.optionSave=scope.paramConfSav;
					 paramConfSav.maxNuImage=scope.maxNuImage;
					 paramConfSav.isIncidencia=scope.isIncidencia;
					 
					 expedienteService.setParams(paramConfSav);
					 
					 $location.path("/cargaMasiva/cargaNivel");
		                $('.modal-backdrop').remove();
		                
				 }
				 
	    	 });
			 
			 scope.defineConsultaImagenesNivel=function(){
				 let paramConfSav={};
				 paramConfSav.cdOs=scope.paramConfSav.idOrdenServ;
				 paramConfSav.cdNivel='ORDEN_SERVICIO';
				 paramConfSav.valor=null;
				 if(scope.paramConfSav.idPregunta != undefined){//nivel pregunta
					 paramConfSav.cdNivel='PREGUNTA';
					 paramConfSav.valor=scope.paramConfSav.idPregunta;
				 }else if(scope.paramConfSav.idEncuesta != undefined){//nivel encuesta
					 paramConfSav.cdNivel='ENCUESTA';
					 paramConfSav.valor=scope.paramConfSav.idEncuesta;
				 }else if(scope.paramConfSav.idProceso != undefined){// nivel prceso
					 paramConfSav.cdNivel='PROCESO';
					 paramConfSav.valor=scope.paramConfSav.idProceso;
				 }
				 
				 return paramConfSav;
			 }
			 
			 scope.getImagesByLevel=function(){
				 let paramSearch=scope.defineConsultaImagenesNivel();
				 
				 expedienteService.getInfoOsNivel(paramSearch.cdOs,paramSearch.cdNivel,paramSearch.valor)
					.success(function(reponse){
						scope.listImages=reponse;
						scope.complementsDataImage();
						$timeout(function() {
							 $('#'+scope.idElementUp+'modalUpdateImage').modal('show');
			     		 },500);
				  }).error(function(e){
					  scope.listImages=[];
					  $('#'+scope.idElementUp+'modalUpdateImage').modal('show');
				  });
			 }
			 
			 scope.cerrarModal=function(){
				  //preguntar si hay nuevas images cargadas, que no se hayan guardado, 
				  //si se confirma cerrar modal y descartar imagenes en lista enviada
				 let isPendient=false;
				 let i;
				 for(i=0; i<scope.listImages.length; i++){
					 let item=scope.listImages[i];
					 if(item.isSuccess == false){
						 isPendient=true;
						 break; 
					 } 
				 }
				 
				 if(!isPendient){// si no hay imagenes pendientes de guardar
					 $('#'+scope.idElementUp+'modalUpdateImage').modal('hide');
					  $('.modal-backdrop').remove();
					  scope.showModalBuild=false;
					 
				 }else{
					 showAlert.confirmacion('Hay imagenes sin guardar, ¿Desea continuar?',
				                confirm = () => {
				                	$('#'+scope.idElementUp+'modalUpdateImage').modal('hide');
				   				  	$('.modal-backdrop').remove();
				   				  	scope.showModalBuild=false;
				   				  	i=0;
				   				 for(i=0; i<scope.listImages.length; i++){
									 let item=scope.listImages[i];
									 if(item.isSuccess == false){
										 scope.listImages.splice(i,1);
										 i--;
									 } 
								 }
				                }, cancelaNotificar = () => {
				                    return;
				                });  
				 }
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
			  
			  scope.prevImg=function(){
				  let idCarousel='#carousel-'+scope.idElementUp;
				  $(idCarousel).carousel('prev');
			  };
			  
			  scope.nextImg=function(){
				  let idCarousel='#carousel-'+scope.idElementUp;
				  $(idCarousel).carousel('next'); 
			  };
			  
			  //Metodo para mostrar el modal con el carrusel
			  scope.showModalImg=function(itemImgVO){
				  scope.showModalCarousel=true;
				  scope.imagePreview=angular.copy(itemImgVO);
				  $timeout(function() {
					  let idCarousel='#carousel-'+scope.idElementUp;
					  $(idCarousel).carousel();
					  $('#'+scope.idElementUp+'modalCarousel').modal('show');
				  },100);
			  };
			  
			  scope.hideModal=function(srcB64,nemaImg){
				  $('#'+scope.idElementUp+'modalCarousel').modal('hide');
				  $('.modal-backdrop').remove();
				  scope.showModalCarousel=false;
				  scope.imagePreview=new Object();
			  };
			  
			  intDirective();
	   }
	};    
});

appt.directive('customOnChange', [function() {
    'use strict';

    return {
        restrict: "A",

        scope: {
            handler: '&'
        },
        link: function(scope, element){

            element.change(function(event){
                scope.$apply(function(){
                    let files=element[0].files;
                    let filesResult=[];
                    let i;
                    for(i=0; i<files.length; i++){
                    	filesResult.push(files[i]);
                    }
                    let onject={params: filesResult};
                    scope.handler(onject);
                });
            });
        }

    };
}]);

appt.filter('startFromGrid', function() {
		var lastInput=null;
		var lastStart=null;
	  return function(input, start) {
		  
		start = +start;
	    lastStart=start;
	    lastInput=input;
	    return input.slice(start);
	  }
	});
