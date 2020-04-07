angular.module(appTeclo).controller('altaServicioController', function($scope,showAlert,growl, altaServicioService) {

	$scope.parametroBusqueda = {};
	$scope.orden = {};
	$scope.orden.vehiculoVO = {};
	$scope.banderaVehiculo = false;
	$scope.kitInstalacionVO = {};
	$scope.range = [] ;
	$scope.rango =[];
	$scope.ordenVO={};
	$scope.mostrarAlta = false;
	$scope.vehiculoResult = {};
	$scope.filKit=false;
	
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
	};
	
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
	};
	
	$scope.buscarVehculoPorPlaca = function(placa){
		  if ($scope.formAltaServicio.valorBusqueda.$invalid) {
	            showAlert.requiredFields($scope.formAltaServicio);
	            return;
	        }
		
		altaServicioService.buscarVehiculo(placa)
		.success(function(data){
			$scope.vehiculoResult  = data;
			
			$scope.orden.vehiculoVO.placa = $scope.vehiculoResult.cdPlacaVehiculo;
			$scope.orden.vehiculoVO.cdVIN=$scope.vehiculoResult.cdVin;
			$scope.orden.vehiculoVO.tjtCirculacion = $scope.vehiculoResult.cdTarjetaDeCirculacion;
			$scope.orden.vehiculoVO.marca = $scope.vehiculoResult.nbMarca;
			$scope.orden.vehiculoVO.subMarca=$scope.vehiculoResult.nbSubMarca;
			$scope.orden.vehiculoVO.cdModelo = $scope.vehiculoResult.cdModelo;
			$scope.orden.vehiculoVO.concesionario = $scope.vehiculoResult.consecionario;
//			$("#select2-catConcesionario-container").text($)
			$scope.orden.vehiculoVO.tpVehiculo = $scope.vehiculoResult.tipoVehiculo;
			$("#select2-tipopVehiculo-container").text($scope.vehiculoResult.tipoVehiculo.nbTipoVehiculo);
			
			$scope.banderaVehiculo = true;
			$scope.banderadeshabilitar = true;
			$scope.error = false;
		})
		.error(function(data){
//			$scope.vehiculoResult = {};
//			$scope.orden = {};
			$scope.error = true;
			growl.warning("se encontro vehiculo con esa placa", { ttl: 5000 });
			$scope.banderaVehiculo = true;
			$scope.banderadeshabilitar = false;
		})
	};
	
	$scope.buscarTipoKit = function(idTpKit){
		altaServicioService.buscarTipoKit(idTpKit)
		.success(function(data){
			$scope.tipoKitsDisp = data;
			$scope.range=[];
			$scope.filKit=true;
		
	
			angular.forEach($scope.tipoKitsDisp, function(value, key){
				$scope.range.push(value.dispositivo);
				
			});
				
			
			
			$scope.error = true;
		})
		.error(function(data){
			$scope.tipoKitsDisp = [];
			$scope.error = true;
			$scope.filKit=false;
			growl.warning("No Existe", { ttl: 5000 });
		})
	};
	
	
	$scope.guardarOrden = function(valor, valorDos){
		
		var temp = valor.map(item =>{
			return {
				"idDispositivo" : item.idDispositivo, "serie" : item.serie, "proveedor" : item.proveedor
			}
		});
		console.log(temp);
		$scope.orden.kitInstalacionVO= temp;
		$scope.ordenVO = $scope.orden;
		console.log($scope.ordenVO);
			
//		altaServicioService.altaOrdenServicio(valorDos).success(function(data){
//			$scope.error = false;
//			growl.success("Datos Guardado Correctamente", { ttl: 5000 });
//			$scope.parametroBusqueda.incidencia="";
//			$scope.orden={};
//			$scope.ordenVO={};
//			$scope.mostrarAlta=false
//			
//		}).error(function(data){
//			$scope.error = true;
//			growl.error("No se registraron los datos", { ttl: 5000 });
//		});

		
		
	};
	
	$scope.buscarIncidencia = function(cdIncidenc){
		
		  if ($scope.formAltaServicio.incidencia.$invalid) {
	            showAlert.requiredFields($scope.formAltaServicio);
	            return;
	        }
		altaServicioService.buscarIncidencia(cdIncidenc).success(function(data){
			
			
			$scope.incidenciaVO = data;
			$scope.mostrarAlta= true;
			
		}).error(function(data){
			growl.warning("No Existe incidencia", { ttl: 5000 });
			$scope.mostrarAlta=false
			
		})
		
		
	};
	
	$scope.consultTipoVehiculos();
	$scope.consultaCentroInstalacion();
});