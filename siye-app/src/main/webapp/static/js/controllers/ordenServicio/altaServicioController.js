angular.module(appTeclo).controller('altaServicioController', function($scope,showAlert,growl, altaServicioService) {

	
	
	$scope.consultTipoVehiculos = function(){
		altaServicioService.buscarTipoVehiculos()
		.success(function(data){
			$scope.catalogoTipoVehiculo = data;
			$scope.error = false;
		})
		.error(function(data){
			$scope.catalogoTipoVehiculo = {};
			error = true;
		})
	}
	
	$scope.consultTipoVehiculos();
});