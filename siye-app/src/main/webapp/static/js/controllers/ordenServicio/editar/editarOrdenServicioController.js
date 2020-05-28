angular.module(appTeclo).controller('editarOrdenServicioController', function($scope, showAlert,
    growl, consultaServicioService, $timeout, $location, $routeParams,catalogoGenericoService,$injector) {
    $scope.banderas = {
        modal: false
    };
    
    $scope.listTransportista=new Array();
    
    $scope.general = {
        voModal: {},
        voModalBackup: {},
        voIncidencia: {},
        cdIncidencia: undefined
    };

    $scope.banderas = {
        formEditar: false,
        folioDisable: false
    };

    $scope.listas = {
        centroInstalacion: [],
        kitInstalacion: [],
        planInstalacion: [],
        tipoVehiculo: []
    };

    $scope.dateTimePickerOptions=new Object({
    	useCurrent:false
    });
    
    $scope.guardar = function(vo, form) {
        if (form.$invalid) {
            showAlert.requiredFields(form);
            growl.warning('Formulario incompleto', { ttl: 5000 });
            return;
        } else {
            if (angular.equals(vo, $scope.general.voModalBackup)) {
                growl.warning('No se han detectado cambios por guardar, favor de validar', { ttl: 4000 });
                return;
            } else {
                vo.incidencia = $scope.general.voIncidencia; // agregamos la incidencia que estará asociada
                let petitionVO=angular.copy(vo);
                if(typeof petitionVO.fhCita != 'string'){
                	petitionVO.fhCita=moment(vo.fhCita).format('DD/MM/YYYY HH:mm');
                }
                consultaServicioService.actualizarServicio(petitionVO).success(function(data) {
                    if (data) {
                        // Cuando toda la petición sea correcta, debemos actualizar el objeto del array aztualizado
                        angular.copy(data, $scope.general.voModal);
                        angular.copy(data, $scope.general.voModalBackup);
                        growl.success('Orden de servicio actualizado correctamente', { ttl: 4000 });
                        getTransportistas();
                        if($scope.general.voModal.vehiculo != undefined && $scope.general.voModal.vehiculo.listConductores != undefined && $scope.general.voModal.vehiculo.listConductores.length > 0)
            				        $scope.listTransportista=getOptions($scope.listTransportista);
                    }
                }).error(function(data) {
                    growl.error('Ocurrió un error al tratar de actualizar datos del servicio', { ttl: 4000 });
                    return;
                });
            }
        }
    };

    $scope.buscarIncidencia = function(cdIncidencia, form) {
        $scope.banderas.formEditar = false;
        if (form.$invalid) {
            showAlert.requiredFields(form);
            growl.warning('Formulario incompleto', { ttl: 5000 });
            return;
        } else {
            consultaServicioService.buscaIncidencia(cdIncidencia).success(function(data) {
                    angular.copy(data, $scope.general.voIncidencia);
                    $scope.banderas.formEditar = true;
                    getTransportistas();
            }).error(function(data) {
                if (data != null && data.status != null) {
                    growl.info(data.message, { ttl: 4000 });
                } else {
                    growl.error('Ocurrió un error al tratar de actualizar datos del servicio', { ttl: 4000 });
                }
                return;
            });
        };
    };

    $scope.buscaServicio = function(cdOrdenServicio, form) {
        if (cdOrdenServicio == undefined) {
            growl.warning('Es necesario especificar el folio del servicio', { ttl: 4000 });
            form['cdOrdenServicio'].$pristine = true;
            form['cdOrdenServicio'].$setDirty();
            return;
        } else {
            consultaServicioService.buscaServicioByCd(cdOrdenServicio).success(function(data) {
                if (data != null) {
                    $scope.banderas.folioDisable = true;
                    angular.copy(data, $scope.general.voModal);
                    angular.copy(data, $scope.general.voModalBackup);
                    $("#select2-nbCentroInstalacion-container").text($scope.general.voModal.centroInstalacion.nbCentroInstalacion);
                    $("#select2-kit-container").text($scope.general.voModal.kitInstalacion.cdKitInstalacion);
                    $("#select2-plan-container").text($scope.general.voModal.plan.nbPlan);
                    $("#select2-idTipoVehiculo-container").text($scope.general.voModal.vehiculo.tipoVehiculo.nbTipoVehiculo);
                    
                    
                    getTransportistas();
                    if($scope.general.voModal.vehiculo != undefined && $scope.general.voModal.vehiculo.listConductores != undefined && $scope.general.voModal.vehiculo.listConductores.length > 0)
        				        $scope.listTransportista=getOptions($scope.listTransportista);
                }
            }).error(function(data) {
                if (data != null && data.status != null) {
                    growl.info(data.message, { ttl: 4000 });
                } else {
                    growl.error('Ocurrió un error al tratar de consultar el servicio', { ttl: 4000 });
                }
                return;
            });
        }
    };

    function catalogos() {
        consultaServicioService.getCatalogos().success(function(data) {
            angular.copy(data.centrosInstalacion, $scope.listas.centroInstalacion);
            angular.copy(data.kitInstalacion, $scope.listas.kitInstalacion);
            angular.copy(data.plan, $scope.listas.planInstalacion);
            angular.copy(data.tipoVehiculo, $scope.listas.tipoVehiculo);
        }).error(function(data) {
            growl.error('Ocurrió un error al consultar los catálogos', { ttl: 4000 });
        });
    };


    $scope.resetActualData = function() {
        $scope.banderas.folioDisable = false;
        $scope.general.voModal = {};
        $scope.general.voModal.fhCita=null;
        $scope.general.voModalBackup = {};
        $scope.cleanComponentDateTimePiker();//METODO DE LA DIRECTIVA DE DATETIMEPIKER
        $("#select2-nbCentroInstalacion-container").text('Seleccione una opción');
        $("#select2-kit-container").text('Seleccione una opción');
        $("#select2-plan-container").text('Seleccione una opción');
        $("#select2-idTipoVehiculo-container").text('Seleccione una opción');
    };

    $scope.regresar = function() {
        $location.path("/consulta/" + $routeParams.opt + "/" + $routeParams.val);
    };
    
    $scope.setMinDateToStartDate=function(minDate,newdate){
    	
    	if(minDate != undefined){
    		let momenStr=moment(minDate).format('DD/MM/YYYY');
        	let momentDateInit=moment(momenStr,'DD/MM/YYYY').format('DD/MM/YYYY');
        	$('#fhAtencionIni').datepicker('setStartDate', momenStr);
        	$('#fhAtencionFin').datepicker('setStartDate', momenStr);
    	}
    	
    };

  //FUNCIONES PARA COMPONENTE DE TRANSPORTISTA
	$scope.openModalTransportista=function(){
		let serviceModal=$injector.get('ModalService');
		let nameModal="Transportista";
		let saveService=$injector.get('conductorService');
		let consultService=$injector.get('catalogoGenericoService');
		abrirModal(nameModal,undefined, serviceModal,saveService,consultService, 'nuevoConductor','getTransportistas');
	};
	
     abrirModal = function(nameModal,paramBusqueda, serviceModal,saveService,consultService, nameSaveEmpServ,nameConsultEmpServ){
    	serviceModal['showModal']({
 			   templateUrl: 'views/templatemodal/templateModalGenerico.html',
 			   controller: 'mensajeModalGenericoController',
 				   inputs:{
 					  nameModal:nameModal,
 					  idTipo: paramBusqueda,
 					  saveService:saveService,
 					  consultService:consultService,
 					  nameSaveEmpServ:nameSaveEmpServ,
 					  nameConsultEmpServ:nameConsultEmpServ
 				   }
 		   }).then(function(modal){
 			  modal.element.modal();
 			  modal.close.then(function(datos) {
 				  if (datos!=null) {
				   if(datos.existe===false){
					   if($scope.general.voModal.vehiculo.listConductores == undefined)
						   $scope.listTransportista=datos.newList;
		        		else
		        			$scope.listTransportista=getOptions(datos.newList);
				   }
				   
				   angular.forEach($scope.listTransportista,function(item,index){
					   if (item.idConductor==datos.newObject.idPersona) {
						   
						   if($scope.general.voModal.conductores == undefined)
							   $scope.general.voModal.conductores=new Array();
						   
						   $scope.general.voModal.conductores.push(item);
					   }
					   let i;
					   let size=$scope.general.voModal.conductores.length;
					   for(i=0; i<size; i++){
						   let itemSe=$scope.general.voModal.conductores[i];
						   if(itemSe.idConductor == item.idConductor){
							   $scope.general.voModal.conductores[i]=item;
							   break;
						   }
					   }
				   });
				   
				   $('#transportista').selectpicker('val', $scope.general.voModal.conductores);
				   $('#transportista').selectpicker('render');
				   $('#transportista').selectpicker('refresh');
				   
 				  }
			   }); 
		 });
    };
    
    function getTransportistas(){
		catalogoGenericoService.getTransportistas().success(function(response){
        	/*Se obtiene la lista y se iguala  ala variable mandada desde html*/
        	$scope.listTransportista=getOptions(response);
        	}).error(function(error){
        		$scope.listTransportista=[];
        	});
	};
	
	 function getOptions(listOption){
		if(listOption != undefined && listOption.length > 0
				&& $scope.general.voModal.vehiculo != undefined && $scope.general.voModal.vehiculo.listConductores != undefined && $scope.general.voModal.vehiculo.listConductores.length > 0){
			let i;
			let size=listOption.length;
			let j;
			let tamanio=$scope.general.voModal.vehiculo.listConductores.length;
			for(i=0; i<size; i++){
				let itemT=listOption[i];
				for(j=0; j<tamanio; j++){
					let itemC=$scope.general.voModal.vehiculo.listConductores[j];
					if(itemT.idConductor == itemC.idConductor){
						listOption.splice(i,1);
						i--;
						size=listOption.length;
						break;
					}
				}
			}
			 $timeout(function() {
				 $('#transportista').selectpicker();
				 $('#transportista').selectpicker('render');
				 $('#transportista').selectpicker('refresh');
			  }, 100);
		}
		return listOption;
	};
	//FUNCIONES PARA COMPONENTE DE TRANSPORTISTA
	getTransportistas();
    catalogos();
});