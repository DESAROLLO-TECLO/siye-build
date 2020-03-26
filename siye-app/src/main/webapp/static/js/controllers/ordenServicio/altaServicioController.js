angular.module(appTeclo).controller('altaServicioController', function($scope,showAlert,growl, altaServicioService) {

	$scope.parametroBusqueda = {};
	$scope.orden = {};
	
	
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
	
	$scope.consultaCentroInstalacion = function(){
		altaServicioService.buscarCentroInstalacion()
		.success(function(data){
			$scope.catalogoProcesos = data;
			$scope.error = false;
			
		})
		.error(function(data){
			$scope.catalogoProcesos ={};
			$scope.error=true;
		})
	}
	
	$scope.buscarVehculoPorPlaca = function(){
		altaServicioService.buscarVehiculo(parametroBusqueda.placa)
		.success(function(data){
			$scope.vehiculoResult  = data;
			$scope.error = false;
		})
		.error(function(data){
			$scope.vehiculoResult = {};
			$scope.error = true;
		})
	}
	
	$scope.consultTipoVehiculos();
	$scope.consultaCentroInstalacion();
});