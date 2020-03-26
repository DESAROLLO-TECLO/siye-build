angular.module(appTeclo).controller('consultaServicioController', function($scope, showAlert, $location, growl, consultaServicioService, $routeParams) {
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

    $scope.parametroBusqueda = {
        tipoBusqueda: {},
        valor: null
    };




    buscarTipoBusqueda = function() {
        $scope.listTipoBusqueda = [
            { idTipoBusqueda: "1", cdTipoBusqueda: "PLACA", nbTipoBusqueda: "PLACA", txTipoBusqueda: "PLACA" },
            { idTipoBusqueda: "2", cdTipoBusqueda: "ORDEN_SERVICIO", nbTipoBusqueda: "ORDEN DE SERVICIO", txTipoBusqueda: "ORDEN DE SERVICIO" },
            { idTipoBusqueda: "3", cdTipoBusqueda: "VIN", nbTipoBusqueda: "VIN", txTipoBusqueda: "VIN" }
        ];

        // CUANDO CARGUE TODO EL CONTROLLER VALIDAMOS EL OBJETO
        if ($routeParams.opt != null && $routeParams.val != null) {
            var opt = filtroBuscarTipoBusqueda($routeParams.opt);
            if (filtroBuscarTipoBusqueda != null) {
                $scope.parametroBusqueda.tipoBusqueda = opt;
                $scope.parametroBusqueda.valor = $routeParams.val;
                $scope.buscarOrdenServicio(false);
            }
        }
    };

    function filtroBuscarTipoBusqueda(cd) {
        for (let i = 0; i < $scope.listTipoBusqueda.length; i++) {
            if (cd === $scope.listTipoBusqueda[i].cdTipoBusqueda) {
                return $scope.listTipoBusqueda[i];
            }
        }
        return null;
    };



    $scope.buscarOrdenServicio = function(ban) {
        if (ban)
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
        showAlert.confirmacion("Se redireccionará a otra pantalla para la edición del servicio. ¿Desea continuar?",
            //Aceptar
            function() {
                $location.path("/editar/" + vo.idOrdenServicio + "/" + $scope.parametroBusqueda.tipoBusqueda.cdTipoBusqueda + "/" + $scope.parametroBusqueda.valor);
                $('.modal-backdrop').remove();
            },
            //Cancelar
            function() {
                return;
            }
        );
    };

    buscarTipoBusqueda();
});