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
	    	  
	    	  const imageCompressor = new ImageCompressor();
	    	  
	    	  var listImg = scope.$eval(attrs.listImages);
	    	  var isIncompletForm=false;
	    	  scope.listImages=listImg == undefined ? [] : listImg;
	    	  scope.listFiles=new Array();
	    	  scope.listFilesExcedeSize=new Array();
	    	  scope.imagePreview=new Object();
	    	  scope.showProgressBar=false;
	    	  scope.totalImagesPendig=0;
	    	  scope.closedFiles=0;
	    	  scope.listConfigCompress=undefined;
	    	  
	    	//Variable con la injeccion por defecto del servicio para expedientes
	    	  var expedienteService=$injector.get('expedienteService')
	    	  //variable de paginador
	    	  scope.paramConfigPage=new Object({
	    		  itemsPerPage:2,
	    		  maxSize:5,
	    		  bigCurrentPage:1,
	    		  bigTotalItems:scope.listImages.length
	    	  });
	    	  
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
				  
				  await scope.getConfigComponentBD();
				  
				  $timeout(function() {
					  scope.complementsDataImage();
				  },450);
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
		    		  file.idExpedienteODS=item.idExpedienteODS;

		    		// SE VALIDA SI TIENE UN TP DE DOCUMENTO PREBIAMENTE ASIGNADO
		    		  if(item.idTipoExpediente != undefined){
	    				  let i;
	    				  let a=file.tpDocumentList;
	    				  for(i=0; i<a.length; i++){
	    					  
	    					  if(item.idTipoExpediente == a[i].idTipoExpediente){
	    						  file.tipoExpediente = a[i];
	    						  $timeout(function() {
	    							  $("#select2-tpDoc"+file.unic+''+scope.idElementUp+"-container").text(file.tipoExpediente.nbTipoExpediente);
	    						  },500);
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
	    		  let filesList = scopeDragDrop.uploadedFile;
	    		  
	    		  let imagesTotal=((scope.listFiles != undefined && scope.listFiles.length != undefined) ? scope.listFiles.length : 0);
	    		  if(scope.maxNuImage != undefined && (filesList.length + imagesTotal) > parseInt(scope.maxNuImage, 10)){
	    			  
	    			  growl.warning('El número de imagenes seleccionado excede el número permitido ', {ttl: 4000});
    				  return;
    			  }
	    		  
	    		  filesList= scope.validRestrcctionsFiles(filesList);
	    		  
	    		// los files obtenidos se asignan a la lista enviada como parametro
	    		  scope.addImgesToLisViewBiding(filesList);
	    	  };
	    	  
	    	//Se obtienen los archivos en onjetoFiles se ejecuta cuando el componente input file detecta cambios, este componenete esta en el template
	    	  scope.getFilesFromInput=function(inputFiles){
	    		  let imagesTotal=((scope.listFiles != undefined && scope.listFiles.length != undefined) ? scope.listFiles.length : 0);
	    		  if(scope.maxNuImage != undefined && (inputFiles.length + imagesTotal) > parseInt(scope.maxNuImage, 10)){
	    			  
	    			  growl.warning('El número de imagenes seleccionado excede el número permitido ', {ttl: 4000});
    				  return;
    			  }
	    		  
	    		  inputFiles = scope.validRestrcctionsFiles(inputFiles);
	    		  
	    		  scope.addImgesToLisViewBiding(inputFiles);
	    	  };
	    	  
	    	//se vaidan las restrciones en caso de existir
	    	  scope.validRestrcctionsFiles=function(lisFiles){
	    		  if(scope.paramConfComponent != undefined){
	    			  let errorTypeFile=false;
	        		  let msj='Algunas archivos seleccionados no cumplieron con';
	        		  let a=scope.paramConfComponent.listTypeExtencion;
	        		  if(lisFiles != undefined && lisFiles.length > 0){
	        			let i;
	        			for(i=0; i<lisFiles.length; i++){
	        				let item=lisFiles[i];
	        				item.exedeSize=false;
	        				if(scope.paramConfComponent.maxSizeMb != undefined &&
	        						item.size > (constants.VAL_ONE_MB_BY_BYTES * scope.paramConfComponent.maxSizeMb)){
	        					item.exedeSize=true;
	        					scope.listFilesExcedeSize.push(lisFiles[i]);
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
	        		  
	        		  if(errorTypeFile){
	        			  errorTypeFile=true;
	        			  msj+=' la extención admitida';
	        			  growl.warning(msj, {ttl: 4000});
	        		  }
	    		  }
	    		  
	    		  return lisFiles;
	    	  };
	    	  
	    	  scope.getConfigComponentBD=function(){
	    		  expedienteService.getConfigCompressImg().success(function(response){
		    		 scope.listConfigCompress=response;
		    	 }).error(function (error){
		    		 scope.listConfigCompress=undefined;
		    	 });
	    		  
	    	  };
	    	  
	    	  scope.clasifiQualityByPorcent=function(nuPorcentCompress){
	    		  switch(nuPorcentCompress){
	    		  	case 90:
	    		  		return 0.79;
	    		  	case 80:
	    		  		return 0.89;
	    		  	case 70:
	    		  		return 0.99;
	    		  	case 60:
	    		  		return 1;
	    		  	default:
	    		  		return 1;
	    		  }
	    	  };
	    	  
	    	  scope.qualityCompressPorcent=function(sizeImage){
	    		  let quality=1;// por defecto comprime la imagen un 60%
	    		  let kb=1024;
	    		  if(scope.listConfigCompress != undefined && scope.listConfigCompress.length){
	    			  let i;
		    		  for(i=0; i<scope.listConfigCompress.length; i++){
		    			  let sizeInicalBytes=(scope.listConfigCompress[i].nuPesoImgInicial * kb);
		    			  let sizeFinalBytes=(scope.listConfigCompress[i].nuPesoImgFinal * kb);
		    			  if(sizeImage >= sizeInicalBytes && sizeImage <= sizeFinalBytes){
		    				  quality=scope.clasifiQualityByPorcent(scope.listConfigCompress[i].nuPorcentaje);
		    				  break;
		    			  }
		    		  }
	    		  }
	    		  return quality;
	    	  };
	    	  
	    	  //Metodo que permite comprimir la imagen
	    	  scope.getCompress=function(){
				  let fileItem=scope.listFilesExcedeSize.pop();
    			  let qualityConfig=scope.qualityCompressPorcent(fileItem.size);
    			 
    			  let compress = new Compress();
    			  let arrayFiles=new Array();
    			  arrayFiles.push(fileItem);
    			  compress.compress(arrayFiles, {
    				    size: 4, // the max size in MB, defaults to 2MB
    				    quality: qualityConfig, // the quality of the image, max is 1,
    				    maxWidth: 1920, // the max width of the output image, defaults to 1920px
    				    maxHeight: 1920, // the max height of the output image, defaults to 1920px
    				    resize: true, // defaults to true, set false if you do not want to resize the image width and height
    				  }).then(function (dataList){
    					  let dataResult=dataList[0];
    					  let typeUni=dataResult.ext.slice(dataResult.ext.lastIndexOf('/') + 1);
    					  result=scope.urltoFile(dataResult.data, dataResult.alt, typeUni);
	    			    	 let unic=(scope.listFiles.length+1);
	    			    	 result.unic=unic;
	    			    	 result.isSuccess=false;
		        		  	 let type = '|' + result.type.slice(result.type.lastIndexOf('/') + 1) + '|';
		        		  	 let isImg=('|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1);
		        		  	 result.isImage=isImg;
		        		  	 result.exedeSize=false;
		        		  	 result.tpDocumentList=angular.copy(scope.tpDocumentList);
		        		  	 scope.logobsResult(result);
		        		  	 scope.listFiles.unshift(result);
		        		  	 scope.closedFiles++;
		        		  	 scope.initCalculatePorcentajeProgressBar();
		        		  	 if(scope.listFilesExcedeSize.length > 0){	
		        		  		scope.getCompress();
		        		  		return;
		        		  	 }
    				  });
	    	 };
	    	 
	    	 scope.logobsResult=function(file) {
	        		if(file != undefined){
        				let reader = new FileReader();
        				  reader.onloadend = function () {
        				    var b64 = reader.result.replace(/^data:.+;base64,/, '');
        				    file.strBase64=b64;
        				  };
        				 reader.readAsDataURL(file);
	        		}
	    	}
	    	 
	    	 scope.initCalculatePorcentajeProgressBar=function(){
	    		 let percentage = Math.min(Math.max(Math.floor(scope.closedFiles / scope.totalImagesPendig * 100), 0), 100);
	    		 // calculate remaining percentage
	    		 let remaining = 100-percentage;
	    		 let progressBar = document.querySelector('.progress-bar-ex-per[role="progressbar"]');
				 let remainingBar = document.querySelector('.progress-bar-ex-per[role="remaining"]');
	    		 // apply percentage
	    		 progressBar.style.width = percentage + '%';
	    		 progressBar.innerText = isNaN(percentage) ? '100%' : (percentage  + '%');
	    		 remainingBar.style.width = remaining + '%';
	    		 //remainingBar.innerText = remaining + '%';
	    		 
	    		 if(scope.closedFiles == scope.totalImagesPendig){
	    			 $timeout(function() {
		    			 scope.closedFiles=0;
		    			 scope.totalImagesPendig=0;
		    			 scope.showProgressBar=false;
		    			 let nameDivUnblokig='div.block-'+scope.idElementUp;
		    			 $(nameDivUnblokig).unblock(); 
					  },800);
	    		 }
	    	 };
	    	  
	    	// se agrega a la lista que mostrará las imagenes en el IU del html
	    	  scope.addImgesToLisViewBiding = function(files){
	    		  let idProgressBarDiv="div-progress-"+scope.idElementUp;
	    		  let nameDivUnblokig='div.block-'+scope.idElementUp;
	    		  $(nameDivUnblokig).block({
	                  message: $('#'+idProgressBarDiv),
	                  css: {border: 'none',
	                      	padding: '15px',
	                      	width: '40%',
	                      	backgroundColor: '#000', 
	                      	'-webkit-border-radius': '10px', 
	                      	'-moz-border-radius': '10px', 
	                      	opacity: .8, 
	                      	color: '#fff'},
	                  overlayCSS: { 
	                    	backgroundColor: '#ECF0F3',
	                    	opacity: .5,
	                    	  }
	              }); 
	    		  
	    		  scope.totalImagesPendig=scope.listFilesExcedeSize.length + files.length;
    			  scope.initCalculatePorcentajeProgressBar();
	    		  scope.showProgressBar=true;
	    		  if(files.length > 0){
	    			  let i;
	        		  for(i=0; i<files.length; i++){
	        		  	 
	        		  	 let file=files[i];
	        		  	 
	        		  	 let unic=(scope.listFiles.length+1);
	        		  	 file.unic=unic;
	        		  	 file.isSuccess=false;
	        		  	 let type = '|' + file.type.slice(file.type.lastIndexOf('/') + 1) + '|';
	        		  	 let isImg=('|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1);
	        		  	 file.isImage=isImg;
	        		  	 file.tpDocumentList=angular.copy(scope.tpDocumentList);
	        		  	 
	        		  	 if(scope.listFiles==undefined){
	        		  		 scope.listFiles=new Array();
	        		  	 }
	        		  	 
	        		  	 //se agrega a la lista envida siempre al inicio
	        		  	 scope.listFiles.unshift(file);
	        		  	scope.closedFiles++;
	        		  	scope.initCalculatePorcentajeProgressBar();
	        		  }
	    		  }
	    		  if(scope.listFilesExcedeSize.length > 0){	    			  
	    			  scope.getCompress();  
	    		  }
	    	  };
	 	     
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
		     
		     scope.getImgVOParamInitial=function(file,imagenVO){
		    	 
		    	 imagenVO.idOrdenServicio = scope.paramConfSav.idOrdenServ;
		    	 imagenVO.idProceso = scope.paramConfSav.idProceso;
		    	 imagenVO.idOdsEncuesta	= scope.paramConfSav.idEncuesta;
		    	 imagenVO.idPregunta = scope.paramConfSav.idPregunta;
		    	 imagenVO.idIncidencia = scope.paramConfSav.idIncidencia;
		    	 imagenVO.nbExpedienteODS = file.name;
		    	 imagenVO.cdTipoArchivo	= file.type.slice(file.type.lastIndexOf('/') + 1);
		    	 imagenVO.lbExpedienteODS =	file.strBase64;
		    	 imagenVO.idTipoExpediente = file.tipoExpediente == undefined ? null : file.tipoExpediente.idTipoExpediente;
		    	 imagenVO.idExpedienteODS=file.idExpedienteODS;
		    	 
		    	 return imagenVO;
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
		     scope.saveImageItem= async function(item,form,nameComponent){
		    	 
		    	 if(item.exedeSize){
		    		 growl.warning('La imagen aun se esta cargando, por favor espere', {ttl: 4000});
		    		 return;
		    	 }
		    		
		    	 if (form[nameComponent].$invalid) {
		    		 form[nameComponent].$dirty=true;
		              growl.error('Formulario incompleto');
		              return;
		          }
		    	 let imageVO={};
		    	 await scope.getImgVOParamInitial(item,imageVO);
		    	 
		    	 let listImages=new Array();
		    	 listImages.push(imageVO);
		    	 scope.serviceSave(listImages);
		     };
		     
		     //metodo que se crea con alcance de controller padre, se puede invocar desde el controller
		     scope.$parent.$parent.isValidFormImages=function(message){
		    	 isIncompletForm=false;
		    	 let listNotAdd=scope.listFiles.filter(function(item){
		    		 return item.exedeSize;
		    	 });
		    	 
		    	 if(listNotAdd.length !=  undefined && listNotAdd.length > 0){
		    		 growl.warning('Las imagenes aun se estan cargando, por favor espere', {ttl: 4000});
		    		 return false;
		    	 }
		    	 
		    	 if(scope.showCombo){
		    		 let listNotTpExpedient=scope.listFiles.filter(function(item){
			    		 return item.tipoExpediente == undefined;
			    	 });
		    		 
		    		 if(listNotTpExpedient.length !=  undefined && listNotTpExpedient.length > 0){
		    			 showAlert.requiredFields(scope.formTpDocument);
		    			 if(message != undefined && message != '')
			    		 		growl.error(message,{ttl: 4000});
		    			 isIncompletForm=true;
			    		 return false;
			    	 }
		    	 }
		    	 
		    	 if(scope.listFiles == undefined || scope.listFiles.length == 0 || scope.formTpDocument.$invalid){
		    		 	showAlert.requiredFields(scope.formTpDocument);
		    		 	
		    		 	if(message != undefined && message != '')
		    		 		growl.error(message,{ttl: 4000});
		    		 	
		              return false; 
		    	 }
		    	 
		    	 return true;
		     };
		     
		     //METODO QUE CON ALCANCE DESDE EL CONTROLADOR PADRE, PERMITE COMPLEMENTAR LA LISTA DE IMAGENES DESDE EL CONTROLER
		     scope.$parent.$parent.updateViewDirective = function(inListImages){
		    	 let listImages=[];
		    	  angular.forEach(inListImages, function(item, key) {
		    		  let file= scope.urltoFile(item.lbExpedienteODS, item.nbExpedienteODS, item.cdTipoArchivo);
		    		  file.unic=(key+1);
		    		  file.strBase64=item.lbExpedienteODS;
		    		  file.tpDocumentList=angular.copy(scope.tpDocumentList);
		    		  let isImg=('|jpg|png|jpeg|bmp|gif|'.indexOf(item.cdTipoArchivo) !== -1);
		    		  file.isImage=isImg;
		    		  file.isSuccess = item.idExpedienteODS != undefined;
		    		  file.idExpedienteODS=item.idExpedienteODS;
		    		// SE VALIDA SI TIENE UN TP DE DOCUMENTO PREBIAMENTE ASIGNADO
		    		  if(item.idTipoExpediente != undefined){
	    				  let i;
	    				  let a=file.tpDocumentList;
	    				  for(i=0; i<a.length; i++){
	    					  
	    					  if(item.idTipoExpediente == a[i].idTipoExpediente){
	    						  file.tipoExpediente = a[i];
	    						  $timeout(function() {
	    							  $("#select2-tpDoc"+file.unic+''+scope.idElementUp+"-container").text(file.tipoExpediente.nbTipoExpediente);
	    						  },450);
	    						  break;
	    					  }
	    				  }
	    			  }
		    		  listImages.push(file);
		    	  });
		    	  
		    	  scope.listFiles=listImages;
		     };
		     
		     scope.$parent.$parent.getValueListImageDirective=function(){
		    	 let resultImages= scope.getListImageToSaved();
		    	return resultImages; 
		     };
		     
		   //Funcion para guardar las imagenes, se valida si se tiene una funcion en especifico
		     scope.saveImagesAll=function(form){
		    	 isIncompletForm=false;
		    	 let listNotAdd=scope.listFiles.filter(function(item){
		    		 return item.exedeSize;
		    	 });
		    	 
		    	 if(scope.showCombo){
		    		 
		    		 let listNotTpExpedient=scope.listFiles.filter(function(item){
			    		 return item.tipoExpediente == undefined;
			    	 });
		    		 
		    		 if(listNotTpExpedient.length !=  undefined && listNotTpExpedient.length > 0){
		    			 showAlert.requiredFields(form);
			    		 growl.error('Formulario incompleto', {ttl: 4000});
			    		 isIncompletForm=true;
			    		 return;
			    	 }
		    	 }
		    	 
		    	 if(listNotAdd.length !=  undefined && listNotAdd.length > 0){
		    		 growl.warning('Las imagenes aun se estan cargando, por favor espere', {ttl: 4000});
		    		 return;
		    	 }
		    	 
		    	 if (form.$invalid) {
		              showAlert.requiredFields(form);
		              growl.error('Formulario incompleto', {ttl: 4000});
		              return;
		          }
		    	 
		    	 let listImages=scope.getListImageToSaved();
		    	 
		    	 scope.serviceSave(listImages);
		    	 
		     };
		     
		     scope.pageChanged=function(listFiles){
		    	 if(isIncompletForm){
		    		 
		    		 $timeout(function() {
						  let i;
						  for(i=0; i<listFiles.length; i++){
							  let file=listFiles[i];
							  if(scope.formTpDocument['tpDoc'+file.unic+scope.idElementUp] != undefined){
								  scope.formTpDocument['tpDoc'+file.unic+scope.idElementUp].$invalid=true;
								  scope.formTpDocument['tpDoc'+file.unic+scope.idElementUp].$dirty=true;
							  }
						  }
					  },100);
		    		 
		    		 showAlert.requiredFields(scope.formTpDocument);
		    	 }
		     };
		     
		     scope.serviceSave=function(listImages){
		    	 
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
		    		 for(i=0; i<scope.listFiles.length; i++){
		    			 let itemUno=scope.listFiles[i];
		    			 for(j=0; j<response.length; j++){
		    				 let itemDos=response[j];
		    				 if(itemUno.name == itemDos.nbExpedienteODS){
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
		    	 angular.forEach(scope.listFiles, function(item, key) {
		    		 //se valida que no se haya enviado a back prebiamente
		    		 if(!item.isSuccess && item.idExpedienteODS == undefined){
		    			 let imagenVO={};
		    			 scope.getImgVOParamInitial(item,imagenVO);
		    			 listImagesSaved.push(imagenVO);
		    		 }
		    	 });
		    	 
		    	 return listImagesSaved;
		     };
		     
		   //Metodo para eliminar una imagen del componente y de la lista enviada
		     scope.cancelUploadeImageItem=function(item){
		    	
	 			// se muestra modal de confirmación
	 			  showAlert.confirmacion('Se eliminará esta imagen, ¿Desea continuar?',
		                confirm = () => {
		                	let indexImg=scope.listFiles.indexOf(item);
		                // solamente se asocian a esta lista cuando esta imagen probiene de base de datos
		          		  if(item.isSuccess){
		          			 let imgDelete=[];
		       			     imgDelete.push(item);
		          			  expedienteService.deleteExpediente(imgDelete)
			          		  .success(function(reponse){
			          			scope.listFiles.splice(indexImg,1);
			          			growl.success('Imagen eliminada correctamente', { ttl: 4000 });
			          		  }).error(function(error){
			          			  growl.error(error, { ttl: 4000 });
			          		  });
		          			
		          		  }else{
		          			scope.listFiles.splice(indexImg,1);
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
			                	let imgDeleList=[];
			                	let j;
			                	for(j=0; j<scope.listFiles.length; j++){// de base pero en view
			                		let file=scope.listFiles[j];
			                		if(!file.isSuccess){
			                			scope.listFiles.splice(j,1);
			                			j--;
				          			}else{
				          				let imagenVO={};
			                			scope.getImgVOParamInitial(file,imagenVO);
			                			imgDeleList.push(imagenVO);
				          			}
			                	}
			                	
			                	if(imgDeleList.length > 0){
			          			 
			          			  expedienteService.deleteExpediente(imgDeleList)
				          		  .success(function(reponse){
				          			  scope.listImages=[];
				          			  scope.listFiles=[];
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
				 for(i=0; i<scope.listFiles.length; i++){
					 let item=scope.listFiles[i];
					 if(item.isSuccess == false){
						 isPendient=true;
						 break; 
					 } 
				 }
				 
				 if(!isPendient){// si no hay imagenes pendientes de guardar
					 $('#'+scope.idElementUp+'modalUpdateImage').modal('hide');
					  $('.modal-backdrop').remove();
					  scope.showModalBuild=false;
					  scope.listImages=[];
		   				 scope.listFiles=[];
				 }else{
					 showAlert.confirmacion('Hay imagenes sin guardar, ¿Desea continuar?',
				                confirm = () => {
				                	
				                	$('#'+scope.idElementUp+'modalUpdateImage').modal('hide');
				   				  	$('.modal-backdrop').remove();
				   				  	scope.showModalBuild=false;
				   				 scope.listImages=[];
				   				 scope.listFiles=[];
				   				 
				                }, cancelaNotificar = () => {
				                    return;
				                });  
				 }
			  };
			  
			//METODO QUE PERMITE IDENTIFICAR EL TIPO DE DISPOSITIVO DONDE SE ESTA CARGANDO LA PANTALLA Y DETERMINA QUE COMPONENTES MOSTRAR
			  scope.isMobile=function(){
				  var isMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);
					if (isMobile) {
//						 scope.paramConfigPage.itemsPerPage=2;
						return true;
					} else {
//						scope.paramConfigPage.itemsPerPage=6;
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
				  scope.imagePreview=itemImgVO;
				  $timeout(function() {
					  let idCarousel='#carousel-'+scope.idElementUp;
					  $(idCarousel).carousel();
					  $('#'+scope.idElementUp+'modalCarousel').modal('show');
				  },200);
			  };
			  
			  scope.hideModal=function(){
				  $('#'+scope.idElementUp+'modalCarousel').modal('hide');
				  $('.modal-backdrop').remove();
				  scope.showModalCarousel=false;
				  scope.imagePreview=new Object();
				  scope.showProgressBar=false;
				  let nameDivUnblokig='div.block-'+scope.idElementUp;
	    		  $(nameDivUnblokig).unblock(); 
			  };
			  
			  scope.isMobile();
			  intDirective();
			  
	   }
	};    
});

appt.directive('customOnChange', function() {
    'use strict';

    return {
        restrict: "A",

        scope: {
            handler: '&'
        },
        link: function(scope, element){
        	
        	scope.logobsResult=function(files) {
        		if(files != undefined){
        			let i;
        			for(i=0; i<files.length; i++){
        				let file=files[i];
        				let reader = new FileReader();
        				  reader.onloadend = function () {
        				    var b64 = reader.result.replace(/^data:.+;base64,/, '');
        				    file.strBase64=b64;
        				  };
        				 reader.readAsDataURL(file);
        			}
        		}
    		}
        	
            element.change(function(event){
            	
            	let files=element[0].files;
                let filesResult=[];
                let i;
                for(i=0; i<files.length; i++){
                	filesResult.push(files[i]);
                }
                
                scope.$apply(function(){
                    scope.logobsResult(filesResult);
                    let onject={params: filesResult};
                    scope.handler(onject);
                    element[0].value="";
                });
            });
        }

    };
});

appt.filter('prettySize',function(){
	return function(size) {
		var kilobyte = 1024;
	    var megabyte = kilobyte * kilobyte;

	    if (size > megabyte) {
	      return (size / megabyte).toFixed(2) + ' MB';
	    } else if (size > kilobyte) {
	      return (size / kilobyte).toFixed(2) + ' KB';
	    } else if (size >= 0) {
	      return size + ' B';
	    }

	    return 'N/A';
	}
});

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
