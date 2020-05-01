
angular.module(appTeclo).config(function (LightboxProvider) {
	LightboxProvider.fullScreenMode = true;
});

angular.module(appTeclo).controller('expedienteController',
    function($scope,$timeout,$filter,showAlert,growl,expedienteService,Lightbox){

	const constnteCDClaisfic={
			TODOS:"TD",
			ORDEN_SERVICIO:"OS",
			PROCESO:"PRC",
			ENCUESTA:"ENC",
			PREGUNTA:"PREG"
	};
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
			filter:undefined
	});
	
	$scope.ordenServicio=null;
	var listImagesVOBackup=new Array();
	
	//Variables para manejo de datos de MODAL
	const MENSAJE_OPTION='Seleccione una opción';
	$scope.listImages=new Array();
	$scope.nuMaxImg=5;
	var optionBacupSelected=null;
	
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
        listTypeExtencion: ['jpg','png','jpeg','pdf','doc','docx'],
        listTpDocuemnt: null
	});
	
	//Variables para manejo de datos de MODAL
	
	urltoFile=function(dataurl, filename, mimeType){
    	 var arr = dataurl.split(','),
         bstr = atob(dataurl), 
         n = bstr.length, 
         u8arr = new Uint8Array(n);
         
	     while(n--){
	         u8arr[n] = bstr.charCodeAt(n);
	     }
	     
	     return new File([u8arr], filename, {type:'image/'+mimeType});
     };
	
	$scope.getImagesOrderServices=function(tpBusqueda,valor,form){
		
		 if (form != undefined && form.$invalid) {
             showAlert.requiredFields(form);
             growl.error('Formulario incompleto');
             return;
         }
		
		expedienteService.getInfoOs(tpBusqueda.cdTipoBusqueda,valor)
			.success(function(response) {
				
				let lastIndex=(response.length-1);
				
				$scope.ordenServicio=response[lastIndex];
				$scope.paramConfiguracion.maxNuImage=$scope.ordenServicio.nuMaxImg;
				$scope.paramConfSav.idOrdenServ=$scope.ordenServicio.idOrdenServicio;
				if($scope.ordenServicio.imagenes != undefined){
					let i;
					for(i=0; i<$scope.ordenServicio.imagenes.length; i++){
						let file= urltoFile($scope.ordenServicio.imagenes[i].lbExpedienteODS, $scope.ordenServicio.imagenes[i].nbExpedienteODS, $scope.ordenServicio.imagenes[i].cdTipoArchivo);
						$scope.ordenServicio.imagenes[i].size=file.size;
						let imgBase64='data:'+file.type+';base64,'+$scope.ordenServicio.imagenes[i].lbExpedienteODS;
						$scope.ordenServicio.imagenes[i].url=imgBase64;
						$scope.ordenServicio.imagenes[i].thumbUrl=imgBase64;
						//$scope.ordenServicio.imagenes[i].caption=file.name;
					}
				}
				
				listImagesVOBackup=angular.copy($scope.ordenServicio.imagenes);
			}).error(function(error){
				$scope.ordenServicio=null;
				listImagesVOBackup=new Array();
				showErrorMessage(error);
			});
	};
	
	$scope.clasificImage=function(tpClasific){
		
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
		showAlert.confirmacion('Se borrarán todas las imagenes, ¿Desea continuar?',
	              confirm = () => {
	            	 
	    			  expedienteService.deleteExpediente(listImagesVOBackup)
	          		  .success(function(reponse){
	          			$scope.ordenServicio.imagenes=new Array();
	          			listImagesVOBackup=new Array();
	          			
	          			growl.success('Las imagenes se borraron correctamente', { ttl: 4000 });
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
		$scope.listImages=getListByFilter("cdNivel",constnteCDClaisfic.ORDEN_SERVICIO,$scope.ordenServicio.imagenes);
		$timeout(function() {
			  $('#modalIploadImage').modal('show');
			  $scope.updateViewDirective($scope.listImages);// metodo se encuntra en la directoa update-image
		 },100);
	};
	
	$scope.hideModalImages=function(){
		
		let listImagesNoTSaved=$scope.getValueListImageDirective();
		
		  if(listImagesNoTSaved != undefined && listImagesNoTSaved.length > 0){// si existen imagenes sin guardar se pregunta si desea cerrar el modal
			  showAlert.confirmacion('Existen imagenes sin guardar, ¿Desea continuar?',
		              confirm = () => {
		            	  resetModal();
		            	  $scope.getImagesOrderServices($scope.busqueda.tpBusqueda,$scope.busqueda.valorBusqueda,undefined);
		              }, cancelaNotificar = () => {
		                  return;
		              });
		  }else{
			  resetModal();
			  $scope.getImagesOrderServices($scope.busqueda.tpBusqueda,$scope.busqueda.valorBusqueda,undefined);
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
		$("#select2-encuesta-container").text(MENSAJE_OPTION);
		$("#select2-pregunta-container").text(MENSAJE_OPTION);
		$scope.paramConfSav.idEncuesta=null;
		$scope.paramConfSav.idPregunta=null;
		if(optionSelected == undefined){
			//imagenes nivel OS
			$scope.paramConfSav.idProceso=null;
			$scope.paramConfiguracion.maxNuImage=$scope.ordenServicio.nuMaxImg;
			$scope.listImages=getListByFilter("cdNivel",constnteCDClaisfic.ORDEN_SERVICIO,$scope.ordenServicio.imagenes);
		}else{
			//imagenes nivel proceso
			$scope.paramConfSav.idProceso=optionSelected.idProceso;
			$scope.paramConfiguracion.maxNuImage=optionSelected.nuMaxImg;
			$scope.listImages=getListByFilterImagesLevel("cdNivel",constnteCDClaisfic.PROCESO,"idProceso",optionSelected.idProceso,$scope.ordenServicio.imagenes);
		}
		$scope.updateViewDirective($scope.listImages);// metodo se encuntra en la directoa update-image
	};
	
	$scope.filterImagesByEnecuesta=function(optionSelected){
		$("#select2-pregunta-container").text(MENSAJE_OPTION);
		$scope.paramConfSav.idPregunta=null;
		if(optionSelected == undefined){
			//imagenes nivel proceso
			$scope.paramConfSav.idEncuesta=null;
			$scope.paramConfiguracion.maxNuImage=$scope.optionSelected.optionProcess.nuMaxImg;
			$scope.listImages=getListByFilterImagesLevel("cdNivel",constnteCDClaisfic.PROCESO,"idProceso",$scope.optionSelected.optionProcess.idProceso,$scope.ordenServicio.imagenes);
		}else{
			//imagenes nivel encuesta
			$scope.paramConfSav.idEncuesta=optionSelected.idEncuesta;
			$scope.paramConfiguracion.maxNuImage=optionSelected.nuMaxImg;
			$scope.listImages=getListByFilterImagesLevel("cdNivel",constnteCDClaisfic.ENCUESTA,"idOdsEncuesta",optionSelected.idEncuesta,$scope.ordenServicio.imagenes);
		}
		
		$scope.updateViewDirective($scope.listImages);// metodo se encuntra en la directoa update-image
		
	};
	
	$scope.filterImageByPregunta=function(optionSelected){
		
		if(optionSelected == undefined){
			//imagenes nivel encuesta
			$scope.paramConfSav.idPregunta=null;
			$scope.paramConfiguracion.maxNuImage=$scope.optionSelected.optionEncuesta.nuMaxImg;
			$scope.listImages=getListByFilterImagesLevel("cdNivel",constnteCDClaisfic.ENCUESTA,"idOdsEncuesta",$scope.optionSelected.optionEncuesta.idEncuesta,$scope.ordenServicio.imagenes);
		}else{
			//imagenes nivel pregunta
			$scope.paramConfSav.idPregunta=optionSelected.idPregunta;
			$scope.paramConfiguracion.maxNuImage=$scope.optionSelected.optionPregunta.nuMaxImg;
			$scope.listImages=getListByFilterImagesLevel("cdNivel",constnteCDClaisfic.PREGUNTA,"idPregunta",optionSelected.idPregunta,$scope.ordenServicio.imagenes);
		}
		$scope.updateViewDirective($scope.listImages);// metodo se encuntra en la directoa update-image
	};
	
	//METODOS PARA MODAL DE CARGA DE IMAGENES POR CLASIFICACION
	
	
	//Procesa mensaje de Error
	showErrorMessage=function(e){
		if(e.status != undefined && Number.isInteger(e.status)){
			
			if(e.descripcion != undefined){
	           	growl.error(e.descripcion,{ ttl: 4000 });
	        }else if(e.message != undefined) {
	           	growl.error(e.status.message,{ ttl: 4000 });
	        }else if(typeof e.status === 'string'){
	           	growl.error(status,{ ttl: 4000 });
	        }else {
	            showAlert.error('Falló la petición, por favor intente de nuevo');
	        }
			
		}else if(e.status != undefined && typeof e.status === 'object'){
			if(e.status.descripcion != undefined){
            	growl.error(e.status.descripcion,{ ttl: 4000 });
            }else if(e.status.message != undefined) {
            	growl.error(e.status.message,{ ttl: 4000 });
            }else if(typeof status === 'string'){
            	growl.error(status,{ ttl: 4000 });
            }else {showAlert.error('Falló la petición');} 
		}else{
			growl.error(e,{ ttl: 4000 });
		 }
	};
	
});