angular.module(appTeclo).controller('editarOrdenServicioController', function($scope, showAlert,
    growl, consultaServicioService, $timeout, $location, $routeParams) {
    $scope.banderas = {
        modal: false
    };

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
                petitionVO.fhCita=moment(vo.fhCita).format('DD/MM/YYYY HH:mm');
                consultaServicioService.actualizarServicio(petitionVO).success(function(data) {
                    if (data) {
                        // Cuando toda la petición sea correcta, debemos actualizar el objeto del array aztualizado
                        angular.copy(data, $scope.general.voModal);
                        angular.copy(data, $scope.general.voModalBackup);
                        growl.success('Orden de servicio actualizado correctamente', { ttl: 4000 });
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
        $scope.cleanComponentDateTimePiker();
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

    catalogos();
});