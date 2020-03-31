angular.module(appTeclo).controller('altaIncidenciaController', function($scope, showAlert, $location, growl, altaIncidenciaService) {

	var fecha = new Date();
	var fechaAnio = fecha.getFullYear();
	
    buscarTipoBusqueda = function() {
        $scope.listTipoBusqueda = [
            { idTipoBusqueda: "0", cdTipoBusqueda: "TODO", nbTipoBusqueda: "TODO", txTipoBusqueda: "TODO" },
            { idTipoBusqueda: "1", cdTipoBusqueda: "PLACA", nbTipoBusqueda: "PLACA", txTipoBusqueda: "PLACA" },
            { idTipoBusqueda: "2", cdTipoBusqueda: "ORDEN_SERVICIO", nbTipoBusqueda: "ORDEN DE SERVICIO", txTipoBusqueda: "ORDEN DE SERVICIO" },
            { idTipoBusqueda: "3", cdTipoBusqueda: "VIN", nbTipoBusqueda: "VIN", txTipoBusqueda: "VIN" }
        ];
    };

    $scope.buscarOrdenServicio = function(ban) {
        if (ban && $scope.formConsultaServicio.$invalid) {
            showAlert.requiredFields($scope.formConsultaServicio);
            growl.warning("Formulario incompleto.", { ttl: 5000 });
            return;
        }
        altaIncidenciaService.buscarOrdenServicio($scope.parametroBusqueda).success(function(data) {
            if (data != null) {
                $scope.listServicio = data;
            }
        }).error(function(e) {
            $scope.listServicio = [];
            growl.warning(e.message, { ttl: 5000 });
        });
    }

    $scope.validarSelect = function () {
    	if($scope.parametroBusqueda.tipoBusqueda.idTipoBusqueda == 0){
    		$scope.parametroBusqueda.valor = null;
    		$scope.formConsultaServicio.valorBusqueda.$setPristine();
    	}
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
//    $scope.buscarOrdenServicio(false);
});
