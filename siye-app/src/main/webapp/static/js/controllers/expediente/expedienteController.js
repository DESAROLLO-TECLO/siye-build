
angular.module(appTeclo).config(function (LightboxProvider) {
	LightboxProvider.fullScreenMode = true;
	LightboxProvider.alwaysShowNavOnTouchDevices = true;
	LightboxProvider.disableScrolling = true;
	LightboxProvider.showImageNumberLabel = true;
	LightboxProvider.templateUrl = "views/templatedirectivas/templateLightBox.html";
});

angular.module(appTeclo).controller('expedienteController',
    function($scope,$timeout,$filter,showAlert,growl,expedienteService,Lightbox,consultaGeneralService){

	$scope.isViewMobile=false;
	
	const constnteCDClaisfic={
			TODOS:"TD",
			ORDEN_SERVICIO:"OS",
			PROCESO:"PRC",
			ENCUESTA:"ENC",
			PREGUNTA:"PREG"
	};
	
	const mensajes={
		CONFIMR_IMAGES:'Existen imágenes sin guardar las cuales se descartaran, ¿Desea continuar?',
		SELECCIONE_OPTION:'Seleccione una opción',
		NOT_FOUND_IMAGES:'No se encontraron imágenes para la orden de servicio'
	};
	
	//Variables de TipoBusqueda
	$scope.busqueda=new Object({
		tpBusqueda:undefined,
		valorBusqueda:"",
		catalogo:new Array(
	            { idTipoBusqueda: "1", cdTipoBusqueda: "PLACA", nbTipoBusqueda: "PLACA", txTipoBusqueda: "PLACA" },
	            { idTipoBusqueda: "2", cdTipoBusqueda: "ORDEN_SERVICIO", nbTipoBusqueda: "ORDEN DE SERVICIO", txTipoBusqueda: "ORDEN DE SERVICIO" },
	            { idTipoBusqueda: "3", cdTipoBusqueda: "VIN", nbTipoBusqueda: "VIN", txTipoBusqueda: "VIN"}
		)
	});
	var optionAll={cdClasif:"TD",nbClasif:"TODOS"};
	$scope.clasificCatalogo=new Object({
		clasifPregunta:"PREG",
		showClasifPregunta:false,
		catalogo:new Array( optionAll,
							{cdClasif:"OS",nbClasif:"ORDEN DE SERVICIO"},
							{cdClasif:"PRC",nbClasif:"PROCESO"},
							{cdClasif:"ENC",nbClasif:"ENCUESTA"}),
		clasifSelected:optionAll,
	});
	
	$scope.view =new Object({
			rowsPerPage:5,
			filter:undefined,
			order: '',
			reverse: false,
			showTableResumeOrden:false,
			showTableImagesByOrden:false
			
	});
	
	$scope.paramConfigPage= new Object({
		bigCurrentPage:1,
		itemsPerPage:1,
		maxSize:5,
		bigCurrentPage:1
	});
	
	$scope.listOrden=new Array();
	$scope.ordenServicio=null;
	var listImagesVOBackup=new Array();
	
	//Variables para manejo de datos de MODAL
	const MENSAJE_OPTION='Seleccione una opción';
	$scope.listImages=new Array();
	$scope.nuMaxImg=5;
	
	$scope.optionSelectedOld=new Object({
		optionProcess:null,
		optionEncuesta:null,
		optionPregunta:null
	});
	
	$scope.optionSelected=new Object({
		optionProcess:null,
		optionEncuesta:null,
		optionPregunta:null
	});
	
	$scope.paramConfSav= new Object({
		idOrdenServ: null, 			 
		idProceso: null,   			
		idEncuesta:null,			
		idPregunta:null,			
		idIncidencia: null
	});
	
	$scope.paramConfiguracion=new Object({
		maxSizeMb: 1,			
        maxNuImage: 10,
        listTypeExtencion: ['jpg','png','jpeg'],
        listTpDocuemnt: null
	});
	
	//Variables para manejo de datos de MODAL
	
	urltoFile=function(dataurl, filename, mimeType){
    	 var bstr = atob(dataurl), 
         n = bstr.length, 
         u8arr = new Uint8Array(n);
         
	     while(n--){
	         u8arr[n] = bstr.charCodeAt(n);
	     }
	     
	     return new File([u8arr], filename, {type:'image/'+mimeType});
     };
	
     $scope.getListOrdenServicio=function(tpBusqueda,valor,form){
    	
    	 $scope.hideTablesResult();
    	 
    	if (form != undefined && form.$invalid) {
            showAlert.requiredFields(form);
            growl.error('Formulario incompleto');
            return;
        }
    	
    	let params= {
            "cdTipoBusqueda": tpBusqueda.cdTipoBusqueda,
            "valor": valor,
            "busquedaAvanzada":false
        };
    	
    	consultaGeneralService.busquedaTramitesParametros(params)
    		.success(function(response){
    			$scope.listOrden=response;
    			$scope.showTableResumenOrden();
    		}).error(function(error){
    			$scope.listOrden=new Array();
    			showErrorMessage(error);
    		});
    	
    };
     
	$scope.getImagesOrderServices=function(idOrden){
		
		expedienteService.getInfoOs("ID_OREDEN",idOrden)
			.success(function(response) {
				
				let lastIndex=(response.length-1);
				
				$scope.ordenServicio=response[lastIndex];
				$scope.paramConfiguracion.maxNuImage=$scope.ordenServicio.nuMaxImg;
				$scope.paramConfSav.idOrdenServ=$scope.ordenServicio.idOrdenServicio;
				if($scope.ordenServicio.imagenes != undefined && $scope.ordenServicio.imagenes.length > 0){
					let i;
					for(i=0; i<$scope.ordenServicio.imagenes.length; i++){
						let file= urltoFile($scope.ordenServicio.imagenes[i].lbExpedienteODS, $scope.ordenServicio.imagenes[i].nbExpedienteODS, $scope.ordenServicio.imagenes[i].cdTipoArchivo);
						$scope.ordenServicio.imagenes[i].size=file.size;
						let imgBase64='data:'+file.type+';base64,'+$scope.ordenServicio.imagenes[i].lbExpedienteODS;
						$scope.ordenServicio.imagenes[i].url=imgBase64;
						$scope.ordenServicio.imagenes[i].thumbUrl=imgBase64;
						$scope.ordenServicio.imagenes[i].caption=$scope.ordenServicio.imagenes[i].nbNivel;
					}
				}else{
					growl.warning(mensajes.NOT_FOUND_IMAGES);
				}
				
				listImagesVOBackup=angular.copy($scope.ordenServicio.imagenes);
				$scope.showTableImagesByOrden();
			}).error(function(error){
				$scope.ordenServicio=null;
				listImagesVOBackup=new Array();
				showErrorMessage(error);
				$scope.showTableImagesByOrden();
			});
	};
	
	$scope.clasificImage=function(tpClasific){
		
		if($scope.ordenServicio != undefined){
			$scope.clasificCatalogo.clasifPregunta=false;
			$scope.clasificCatalogo.showClasifPregunta=false;
			
			switch(tpClasific.cdClasif){
				case constnteCDClaisfic.TODOS:
					$scope.ordenServicio.imagenes=angular.copy(listImagesVOBackup);
					break;
				case constnteCDClaisfic.ORDEN_SERVICIO:
					$scope.ordenServicio.imagenes=getListByFilter("cdNivel",constnteCDClaisfic.ORDEN_SERVICIO,listImagesVOBackup);
					break;
				case constnteCDClaisfic.PROCESO:
					$scope.ordenServicio.imagenes=getListByFilter("cdNivel",constnteCDClaisfic.PROCESO,listImagesVOBackup);
					break;
				case constnteCDClaisfic.ENCUESTA:
					$scope.clasificCatalogo.showClasifPregunta=true;
					$scope.ordenServicio.imagenes=getListByFilter("cdNivel",constnteCDClaisfic.ENCUESTA,listImagesVOBackup);
					break;
			}	
		}
	};
	
	$scope.changedClasifLevelAsk=function(isCheck){
		if(isCheck){
			$scope.ordenServicio.imagenes=getListByFilter("cdNivel",constnteCDClaisfic.PREGUNTA,listImagesVOBackup);
		}else{
			$scope.ordenServicio.imagenes=getListByFilter("cdNivel",constnteCDClaisfic.ENCUESTA,listImagesVOBackup);
		}
	}
	
	//metodo que regresa una lista mediante un nombre de atributo (nameAtributo) especificado y un filtro (value) 
	getListByFilter=function(nameAtributo,value,listItems){
		  
		  let list=new Array();
		  
		  if(listItems != undefined && nameAtributo != undefined && value!= undefined){
			  list = listItems.filter(function (item) {
		     	    return (item[nameAtributo] == value);
		     	});  
		  }
	    	 
		  return list;
	 };
	 
	 getListByFilterImagesLevel=function(nameAtributo,value,nameAttribute2,value2,listItems){
		  
		  let list=new Array();
		  
		  if(listItems != undefined && nameAtributo != undefined && value!= undefined){
			  list = listItems.filter(function (item) {
		     	    return (item[nameAtributo] == value && item[nameAttribute2] == value2);
		     	});  
		  }
	    	 
		  return list;
	 };
	
	$scope.viewImage=function(imgVO){
		let index=0;
		let i;
		for(i=0; i<$scope.ordenServicio.imagenes.length; i++){
			if(imgVO.idExpedienteODS == $scope.ordenServicio.imagenes[i].idExpedienteODS){
				index=i;
				break;
			}
		}
		Lightbox.openModal($scope.ordenServicio.imagenes, index);
	};
	
	$scope.deleteImage=function(imgVO){
		  showAlert.confirmacion('Se borrará esta imagen, ¿Desea continuar?',
              confirm = () => {
            	  let listImgDel=new Array();
            	  listImgDel.push(imgVO);
    			  expedienteService.deleteExpediente(listImgDel)
          		  .success(function(reponse){
          			
          			let i;
          			let index;
          			for(i=0; i<$scope.ordenServicio.imagenes.length; i++){
          				if(imgVO.idExpedienteODS == $scope.ordenServicio.imagenes[i].idExpedienteODS){
          					index=i;
          					break;
          				}
          			}
          			$scope.ordenServicio.imagenes.splice(index,1);
          			
          			let j;
          			let indexj;
          			for(j=0; j<listImagesVOBackup.length; j++){
          				if(imgVO.idExpedienteODS == listImagesVOBackup[j].idExpedienteODS){
          					indexj=j;
          					break;
          				}
          			}
          			listImagesVOBackup.splice(indexj,1);
          			
          			growl.success('Imagen borrada correctamente', { ttl: 4000 });
          		  }).error(function(error){
          			showErrorMessage(error);
          		  });
              }, cancelaNotificar = () => {
                  return;
              });
	};
	
	$scope.deleteAllImage=function(){
		showAlert.confirmacion('Se borrarán todas las imágenes, ¿Desea continuar?',
	              confirm = () => {
	            	 
	    			  expedienteService.deleteExpediente(listImagesVOBackup)
	          		  .success(function(reponse){
	          			$scope.ordenServicio.imagenes=new Array();
	          			listImagesVOBackup=new Array();
	          			
	          			growl.success('Las imágenes se borraron correctamente', { ttl: 4000 });
	          		  }).error(function(error){
	          			showErrorMessage(error);
	          		  });
	              }, cancelaNotificar = () => {
	                  return;
	              });
	};
	
	
	//METODOS PARA MODAL DE CARGA DE IMAGENES POR CLASIFICACION
	
	$scope.showModalImages=function(){
		//imagenes nivel OS
		$scope.listImages=getListByFilter("cdNivel",constnteCDClaisfic.ORDEN_SERVICIO,listImagesVOBackup);
		$timeout(function() {
			  $('#modalIploadImage').modal('show');
			  $scope.updateViewDirective($scope.listImages);// metodo se encuntra en la directoa update-image
		 },100);
	};
	
	$scope.hideModalImages=function(idOrdenServicio){
		
		let listImagesNoTSaved=$scope.getValueListImageDirective();
		
		  if(listImagesNoTSaved != undefined && listImagesNoTSaved.length > 0){// si existen imagenes sin guardar se pregunta si desea cerrar el modal
			  showAlert.confirmacion('Existen imágenes sin guardar, ¿Desea continuar?',
		              confirm = () => {
		            	  resetModal();
		            	  $scope.getImagesOrderServices(idOrdenServicio);
		              }, cancelaNotificar = () => {
		                  return;
		              });
		  }else{
			  resetModal();
			  $scope.getImagesOrderServices(idOrdenServicio);
		  }
		  
	};
	
	resetModal=function(){
		$scope.listImages=new Array();
		$scope.updateViewDirective($scope.listImages);
		$('#modalIploadImage').modal('hide');
		  $('.modal-backdrop').remove();
		  $scope.paramConfSav.idProceso=null;
		  $scope.paramConfSav.idEncuesta=null;
		  $scope.paramConfSav.idPregunta=null;
		  $scope.paramConfiguracion.maxNuImage=$scope.ordenServicio.nuMaxImg;
		  
		  $scope.optionSelected.optionProcess=null;
		  $scope.optionSelected.optionEncuesta=null;
		  $scope.optionSelected.optionPregunta=null;
		  $("#select2-proceso-container").text(MENSAJE_OPTION);
		  $("#select2-encuesta-container").text(MENSAJE_OPTION);
		  $("#select2-pregunta-container").text(MENSAJE_OPTION);
	};
	
	$scope.filterImagesByProcess=function(optionSelected){
		let newOption=angular.copy(optionSelected);
		if(existenImagenesSinGurdar()){// se solicita confirmacion para cambiar la vista
			marckOptionTextCombo('proceso',constnteCDClaisfic.PROCESO,$scope.optionSelectedOld.optionProcess);
			genericComfirm(mensajes.CONFIMR_IMAGES, actionChangedFilterImageProcess,newOption);
		}else{
			actionChangedFilterImageProcess(optionSelected);
		}
		
	};
	
	actionChangedFilterImageProcess=function(optionSelected){
		$scope.optionSelectedOld.optionProcess = angular.copy(optionSelected);
		marckOptionTextCombo('proceso',constnteCDClaisfic.PROCESO,optionSelected);
		$("#select2-encuesta-container").text(MENSAJE_OPTION);
		$("#select2-pregunta-container").text(MENSAJE_OPTION);
		$scope.paramConfSav.idEncuesta=null;
		$scope.paramConfSav.idPregunta=null;
		$scope.optionSelectedOld.optionEncuesta=null;
		$scope.optionSelectedOld.optionPregunta=null;
		if(optionSelected == undefined){
			//imagenes nivel OS
			$scope.paramConfSav.idProceso=null;
			$scope.paramConfiguracion.maxNuImage=$scope.ordenServicio.nuMaxImg;
			$scope.listImages=getListByFilter("cdNivel",constnteCDClaisfic.ORDEN_SERVICIO,listImagesVOBackup);
		}else{
			//imagenes nivel proceso
			$scope.paramConfSav.idProceso=optionSelected.idProceso;
			$scope.paramConfiguracion.maxNuImage=optionSelected.nuMaxImg;
			$scope.listImages=getListByFilterImagesLevel("cdNivel",constnteCDClaisfic.PROCESO,"idProceso",optionSelected.idProceso,listImagesVOBackup);
		}
		$scope.updateViewDirective($scope.listImages);// metodo se encuntra en la directoa update-image
	};
	
	$scope.filterImagesByEnecuesta=function(optionSelected){
		let newOption=angular.copy(optionSelected);
		if(existenImagenesSinGurdar()){// se solicita confirmacion para cambiar la vista
			marckOptionTextCombo('encuesta',constnteCDClaisfic.ENCUESTA,$scope.optionSelectedOld.optionEncuesta);
			genericComfirm(mensajes.CONFIMR_IMAGES, actionChangedFilterImageEncuesta,newOption);
		}else{
			actionChangedFilterImageEncuesta(optionSelected);
		}
		
	};
	
	actionChangedFilterImageEncuesta=function(optionSelected){
		$scope.optionSelectedOld.optionEncuesta = angular.copy(optionSelected);
		marckOptionTextCombo('encuesta',constnteCDClaisfic.ENCUESTA,optionSelected);
		$scope.paramConfSav.idPregunta=null;
		$scope.optionSelectedOld.optionPregunta=null;
		if(optionSelected == undefined){
			//imagenes nivel proceso
			$scope.paramConfSav.idEncuesta=null;
			$scope.paramConfiguracion.maxNuImage=$scope.optionSelected.optionProcess.nuMaxImg;
			$scope.listImages=getListByFilterImagesLevel("cdNivel",constnteCDClaisfic.PROCESO,"idProceso",$scope.optionSelected.optionProcess.idProceso,listImagesVOBackup);
		}else{
			//imagenes nivel encuesta
			$scope.paramConfSav.idEncuesta=optionSelected.idEncuesta;
			$scope.paramConfiguracion.maxNuImage=optionSelected.nuMaxImg;
			$scope.listImages=getListByFilterImagesLevel("cdNivel",constnteCDClaisfic.ENCUESTA,"idEncuesta",optionSelected.idEncuesta,listImagesVOBackup);
		}
		
		$scope.updateViewDirective($scope.listImages);// metodo se encuntra en la directoa update-image
		
	};
	
	$scope.filterImageByPregunta=function(optionSelected){
		let newOption=angular.copy(optionSelected);
		if(existenImagenesSinGurdar()){// se solicita confirmacion para cambiar la vista
			marckOptionTextCombo('pregunta',constnteCDClaisfic.PREGUNTA,$scope.optionSelectedOld.optionPregunta);
			genericComfirm(mensajes.CONFIMR_IMAGES, actionChangedFilterImagePregunta,newOption);
		}else{
			actionChangedFilterImagePregunta(optionSelected);
		}
		
	};
	
	actionChangedFilterImagePregunta=function(optionSelected){
		$scope.optionSelectedOld.optionPregunta=angular.copy(optionSelected);
		marckOptionTextCombo('pregunta',constnteCDClaisfic.PREGUNTA,optionSelected);
		if(optionSelected == undefined){
			//imagenes nivel encuesta
			$scope.paramConfSav.idPregunta=null;
			$scope.paramConfiguracion.maxNuImage=$scope.optionSelected.optionEncuesta.nuMaxImg;
			$scope.listImages=getListByFilterImagesLevel("cdNivel",constnteCDClaisfic.ENCUESTA,"idEncuesta",$scope.optionSelected.optionEncuesta.idEncuesta,listImagesVOBackup);
		}else{
			//imagenes nivel pregunta
			$scope.paramConfSav.idPregunta=optionSelected.idPregunta;
			$scope.paramConfiguracion.maxNuImage=$scope.optionSelected.optionPregunta.nuMaxImg;
			$scope.listImages=getListByFilterImagesLevel("cdNivel",constnteCDClaisfic.PREGUNTA,"idPregunta",optionSelected.idPregunta,listImagesVOBackup);
		}
		$scope.updateViewDirective($scope.listImages);// metodo se encuntra en la directoa update-image
	};
	
	genericComfirm=function(message,functionActionConfirm,param){
		showAlert.confirmacion(message,
                confirm = () => {
                	functionActionConfirm(param);
                }, cancelaNotificar = () => {
                    return;
                });
	}
	
	//METODOS PARA MODAL DE CARGA DE IMAGENES POR CLASIFICACION
	
	existenImagenesSinGurdar=function(){
		let listImages=$scope.getValueListImageDirective();
		if(listImages != undefined && listImages.length > 0)
			return true;
		else
			return false;
	}
	
	//Procesa mensaje de Error
	showErrorMessage=function(e){
		if(e.status != undefined && Number.isInteger(e.status)){
			
			if(e.descripcion != undefined){
	           	growl.warning(e.descripcion,{ ttl: 4000 });
	        }else if(e.message != undefined) {
	           	growl.warning(e.message,{ ttl: 4000 });
	        }else if(typeof e.status === 'string'){
	           	growl.warning(e.status,{ ttl: 4000 });
	        }else {
	            showAlert.error('Falló la petición, por favor intente de nuevo');
	        }
			
		}else if(e.status != undefined && typeof e.status === 'object'){
			if(e.status.descripcion != undefined){
            	growl.warning(e.status.descripcion,{ ttl: 4000 });
            }else if(e.status.message != undefined) {
            	growl.warning(e.status.message,{ ttl: 4000 });
            }else if(typeof e.status === 'string'){
            	growl.error(e.status,{ ttl: 4000 });
            }else {showAlert.error('Falló la petición');} 
		}else{
			growl.error(e,{ ttl: 4000 });
		 }
	};
	
	showInMobile=function(){
		var isMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);
		if (isMobile) {
			$scope.isViewMobile=true;
			$scope.isUseTooltip=false;
			return true;
		} else {
			$scope.isViewMobile=false;
			$scope.isUseTooltip=true;
			return false;
		}
	};
	
	$scope.hideTablesResult=function(){
		$scope.view.showTableResumeOrden = false;
		$scope.view.showTableImagesByOrden = false;
	};
	
	$scope.showTableResumenOrden=function(){
		$scope.view.showTableResumeOrden = true;
		$scope.view.showTableImagesByOrden = false;
		$scope.view.filter=undefined;
		$scope.view.order='';
		$scope.view.reverse=false;
		
		if($scope.clasificCatalogo.clasifSelected.cdClasif != constnteCDClaisfic.TODOS){
		
			let i;
			let size=$scope.clasificCatalogo.catalogo.length;
			for(i=0; i<size; i++){
				if($scope.clasificCatalogo.catalogo[i].cdClasif == constnteCDClaisfic.TODOS){
					$scope.clasificCatalogo.clasifSelected=	$scope.clasificCatalogo.catalogo[i];
					$("#select2-clasifImg-container").text($scope.clasificCatalogo.catalogo[i].nbClasif);
					break;
				}
				
			}
			
		}
	};
	
	$scope.showTableImagesByOrden=function(){
		$scope.view.showTableResumeOrden = false;
		$scope.view.showTableImagesByOrden = true;
	};
	
	marckOptionTextCombo=function(nameCombo,codeCombo,optionMarck){
		let i;
		let size;
		let idOption=undefined;
		switch(codeCombo){
		case constnteCDClaisfic.PROCESO:
			
			if(optionMarck != undefined){
				idOption=optionMarck.idProceso;
				$("#select2-"+nameCombo+"-container").text(optionMarck.cdProceso);
				size=$scope.ordenServicio.procesos.length;
				for(i=0; i<size; i++){
					if(idOption == $scope.ordenServicio.procesos[i].idProceso){
						$scope.optionSelected.optionProcess=$scope.ordenServicio.procesos[i];		
						break;
					}
				}
			}else{
				$("#select2-"+nameCombo+"-container").text(mensajes.SELECCIONE_OPTION);
				$scope.optionSelected.optionProcess=undefined;
			}
			
			break;
		case constnteCDClaisfic.ENCUESTA:
			
			if(optionMarck != undefined){
				idOption=optionMarck.idEncuesta;
				$("#select2-"+nameCombo+"-container").text(optionMarck.cdEncuesta);
				size=$scope.optionSelected.optionProcess.listEncuestas.length;
				for(i=0; i<size; i++){
					if(idOption == $scope.optionSelected.optionProcess.listEncuestas[i].idEncuesta){
						$scope.optionSelected.optionEncuesta=$scope.optionSelected.optionProcess.listEncuestas[i];		
						break;
					}
				}
			}else{
				$("#select2-"+nameCombo+"-container").text(mensajes.SELECCIONE_OPTION);
				$scope.optionSelected.optionEncuesta=undefined;
			}
			
			break;
		case constnteCDClaisfic.PREGUNTA:

			if(optionMarck != undefined){
				idOption=optionMarck.idPregunta;
				$("#select2-"+nameCombo+"-container").text(optionMarck.cdPregunta);
				size=$scope.optionSelected.optionEncuesta.listPreguntas.length;
				for(i=0; i<size; i++){
					if(idOption == $scope.optionSelected.optionEncuesta.listPreguntas[i].idPregunta){
						$scope.optionSelected.optionPregunta=$scope.optionSelected.optionEncuesta.listPreguntas[i];		
						break;
					}
				}
				
			}else{
				$("#select2-"+nameCombo+"-container").text(mensajes.SELECCIONE_OPTION);
				$scope.optionSelected.optionPregunta=undefined;
			}
			
			break;
		}
	};
	
	showInMobile();
	
});