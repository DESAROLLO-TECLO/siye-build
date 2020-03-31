angular.module(appTeclo).controller('altaServicioController', function($scope,showAlert,growl, altaServicioService) {

	$scope.parametroBusqueda = {};
	$scope.orden = {};
	$scope.banderaVehiculo = false;
	$scope.kitInstalacionVO = {};
	$scope.vehiculoVO = {};
	
	
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
	
	$scope.buscarVehculoPorPlaca = function(placa){
//		  if ($scope.formAltaServicio.$invalid) {
//	            showAlert.requiredFields($scope.formAltaServicio);
//	            growl.warning("Formulario incompleto.", { ttl: 5000 });
//	            return;
//	        }
		
		altaServicioService.buscarVehiculo(placa)
		.success(function(data){
			$scope.vehiculoResult  = data;
			
			$scope.orden.placa = $scope.vehiculoResult.cdPlacaVehiculo;
			$scope.orden.cdVIN=$scope.vehiculoResult.cdVin;
			$scope.orden.tjtCirculacion = $scope.vehiculoResult.cdTarjetaDeCirculacion;
			$scope.orden.tpVehiculo = $scope.vehiculoResult.tipoVehiculo.nbTipoVehiculo;
			$scope.orden.marca = $scope.vehiculoResult.nbMarca;
			$scope.orden.subMarca=$scope.vehiculoResult.nbSubMarca;
			
			$scope.banderaVehiculo = true;
			$scope.banderadeshabilitar = true;
			$scope.error = false;
		})
		.error(function(data){
			$scope.vehiculoResult = {};
			$scope.orden = {};
			$scope.error = true;
			growl.warning("se encontro vehiculo con esa placa", { ttl: 5000 });
			$scope.banderaVehiculo = true;
			$scope.banderadeshabilitar = false;
		})
	}
	
	$scope.buscarTipoKit = function(idTpKit){
		altaServicioService.buscarTipoKit(idTpKit)
		.success(function(data){
			$scope.tipoKitsDisp = data;
		
			$scope.range = [] ;
//			$scope.kitInstalacionVO.idDisp=[];
			angular.forEach($scope.tipoKitsDisp, function(value, key){
//				$scope.range.push(value.dispositivo.nbDispositivo);
				$scope.range.push(value.dispositivo);
//				$scope.kitInstalacionVO[key].idDisp.push(value.dispositivo[key].idDispositivo);
				
			});
				
			
			
			$scope.error = true;
		})
		.error(function(data){
			$scope.tipoKitsDisp = [];
			$scope.error = true;
			growl.warning("No Existe", { ttl: 5000 });
		})
	}
	
	$scope.consultTipoVehiculos();
	$scope.consultaCentroInstalacion();
});