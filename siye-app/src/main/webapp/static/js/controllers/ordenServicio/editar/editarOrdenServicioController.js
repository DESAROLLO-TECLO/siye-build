angular.module(appTeclo).controller('editarOrdenServicioController', function($scope, showAlert,
    growl, consultaServicioService, ordenServicio, $timeout, $location, $routeParams) {
    $scope.banderas = {
        modal: false
    };

    $scope.general = {
        voModal: {},
        voModalBackup: {}
    };

    $scope.listas = {
        centroInstalacion: [],
        kitInstalacion: [],
        planInstalacion: [],
        tipoVehiculo: []
    };

    $scope.guardar = function(vo, form) {
        if (form.$invalid) {
            showAlert.requiredFields(form);
            growl.warning('Formulario incompleto', { ttl: 5000 });
            return;
            T
        } else {
            if (angular.equals(vo, $scope.general.voModalBackup)) {
                growl.warning('No se han detectado cambios por guardar, favor de validar', { ttl: 4000 });
                return;
            } else {
                consultaServicioService.actualizarServicio(vo).success(function(data) {
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


    function catalogos() {
        consultaServicioService.catalogosOrdenServicio().success(function(data) {
            angular.copy(data.centrosInstalacion, $scope.listas.centroInstalacion);
            angular.copy(data.kitInstalacion, $scope.listas.kitInstalacion);
            angular.copy(data.plan, $scope.listas.planInstalacion);
            angular.copy(data.tipoVehiculo, $scope.listas.tipoVehiculo);

            $timeout(function() {
                angular.copy(ordenServicio.data, $scope.general.voModal);
                angular.copy(ordenServicio.data, $scope.general.voModalBackup);
                $("#select2-nbCentroInstalacion-container").text($scope.general.voModal.centroInstalacion.nbCentroInstalacion);
                $("#select2-kit-container").text($scope.general.voModal.kitInstalacion.cdKitInstalacion);
                $("#select2-plan-container").text($scope.general.voModal.plan.nbPlan);
                $("#select2-idTipoVehiculo-container").text($scope.general.voModal.vehiculo.tipoVehiculo.nbTipoVehiculo);
            }, 200);

        }).error(function(data) {
            growl.error('Ocurrió un error al consultar los catálogos', { ttl: 4000 });
        });
    };

    $scope.regresar = function() {
        $location.path("/consulta/" + $routeParams.opt + "/" + $routeParams.val);
    };

    catalogos();
});