angular.module(appTeclo).controller('consultaServicioController', function($scope, showAlert, growl, consultaServicioService) {

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

    $scope.tabla = new Object({
        order: 'folio',
        reverse: false,
        paginacion: [5, 10, 20, 50],
        cantidadPaginacion: 5,
        cantidadPaginacionVal: 5,
        view: new Object({
            filterSearchVal: '',
            rowsPerPageVal: 0
        })
    });

    buscarTipoBusqueda = function() {
        $scope.listTipoBusqueda = [
            { idTipoBusqueda: "1", cdTipoBusqueda: "PLACA", nbTipoBusqueda: "PLACA", txTipoBusqueda: "PLACA" },
            { idTipoBusqueda: "2", cdTipoBusqueda: "ORDEN_SERVICIO", nbTipoBusqueda: "ORDEN DE SERVICIO", txTipoBusqueda: "ORDEN DE SERVICIO" },
            { idTipoBusqueda: "3", cdTipoBusqueda: "VIN", nbTipoBusqueda: "VIN", txTipoBusqueda: "VIN" }
        ];
    }
    $scope.buscarOrdenServicio = function() {
        if ($scope.formConsultaServicio.$invalid) {
            showAlert.requiredFields($scope.formConsultaServicio);
            growl.warning("Formulario incompleto.", { ttl: 5000 });
            return;
        }
        consultaServicioService.buscarOrdenServicio($scope.parametroBusqueda).success(function(data) {
            if (data != null) {
                $scope.listServicio = data;
            }
        }).error(function(e) {
            $scope.listServicio = [];
            growl.warning("No se encontraron orden de servicios.", { ttl: 5000 });
        });
    }


    $scope.mostrarModal = function(vo) {
        $scope.banderas.modal = true;
        angular.copy(vo, $scope.general.voModal);
        angular.copy(vo, $scope.general.voModalBackup);
    };

    $scope.cerrar = function() {
        $scope.banderas.modal = false;
        $scope.general.voModal = {};
        $scope.general.voModalBackup = {};
    };

    $scope.guardar = function(vo, form) {
        if (form.$invalid) {
            showAlert.requiredFields(form);
            growl.warning("Formulario incompleto.", { ttl: 5000 });
            return;
        } else {
            if (angular.equals(vo, $scope.general.voModalBackup)) {
                growl.warning('No se han detectado cambios por guardar, favor de validar', { ttl: 4000 });
                return;
            } else {
                consultaServicioService.actualizarServicio(vo).success(function(data) {
                    if (data) {
                        growl.success('Servicio actualizado correctamente', { ttl: 4000 });
                        $scope.cerrar();
                    }
                }).error(function(data) {
                    growl.error('Ocurrió un error al tratar de actualizar datos del servicio', { ttl: 4000 });
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
        }).error(function(data) {
            growl.error('Ocurrió un error al consultar los catálogos', { ttl: 4000 });
        });
    };

    catalogos();
    buscarTipoBusqueda();
});