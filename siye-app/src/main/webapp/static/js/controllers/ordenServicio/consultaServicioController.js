angular.module(appTeclo).controller('consultaServicioController', function($scope,showAlert,growl,consultaServicioService) {

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
	
	buscarTipoBusqueda = function (){
		$scope.listTipoBusqueda = [
		                        {idTipoBusqueda: "1", cdTipoBusqueda: "PLACA", nbTipoBusqueda: "PLACA", txTipoBusqueda: "PLACA"},
		                        {idTipoBusqueda: "2", cdTipoBusqueda: "ORDEN_SERVICIO", nbTipoBusqueda: "ORDEN DE SERVICIO", txTipoBusqueda: "ORDEN DE SERVICIO"},
		                        {idTipoBusqueda: "3", cdTipoBusqueda: "VIN", nbTipoBusqueda: "VIN", txTipoBusqueda: "VIN"}
		                        ];
	}
	$scope.buscarOrdenServicio = function (){
		if($scope.formConsultaServicio.$invalid) {
			showAlert.requiredFields($scope.formConsultaServicio);
			growl.warning("Formulario incompleto.", {ttl: 5000});
		}
		consultaServicioService.buscarOrdenServicio($scope.parametroBusqueda).success(function(data) {
			if(data!=null){
				$scope.listServicio = data;
			}
        }).error(function(e) {
        	$scope.listServicio = [];
        	growl.warning("No se encontraron orden de servicios.", {ttl: 5000});
        });
	}
	
	buscarTipoBusqueda();
});