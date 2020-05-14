angular.module(appTeclo).controller('altaServicioController', function($scope,showAlert,growl, altaServicioService) {

	$scope.parametroBusqueda = {};
	$scope.orden = {fhCita: ""};
	$scope.incidencia = {};
	$scope.orden.vehiculoVO = {};
	$scope.banderaVehiculo = false;
	$scope.kitInstalacionVO = {};
	$scope.range = [] ;
	$scope.rango =[];
	$scope.ordenVO={};
	$scope.mostrarAlta = false;
	$scope.vehiculoResult = {};
	$scope.filKit=false;
	$scope.incidendiaRequerida = false;
	$scope.parametroBusqueda.incidencia="";
	$scope.cdPlacaVehiculo="";
	$scope.vehicPlaca=false;
	
	
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
			 $scope.vehicPlaca=true;
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
			$scope.orden.vehiculoVO.tpVehiculo = $scope.vehiculoResult.tipoVehiculo;
			$("#select2-tipopVehiculo-container").text($scope.vehiculoResult.tipoVehiculo.nbTipoVehiculo);
			$scope.orden.vehiculoVO.concesionaria = $scope.vehiculoResult.consecionario;
			$("#select2-catConcesionario-container").text($scope.vehiculoResult.consecionario.nbConsecion);
			
			$scope.banderaVehiculo = true;
			$scope.banderadeshabilitar = true;
			$scope.error = false;
		})
		.error(function(data){
			
			$scope.error = true;
			$scope.orden.vehiculoVO={};
			$scope.orden.vehiculoVO.placa = placa;
			growl.warning("No se encontro vehículo con esa placa", { ttl: 5000 });
			$scope.banderaVehiculo = true;
			$scope.banderadeshabilitar = false;
			$(".select2").select2();
			$("#select2-tipopVehiculo-container").text('Seleccione');
			$("#select2-catConcesionario-container").text('Seleccione');
			$scope.formAltaServicio.$setPristine(); 
			
		})
	};
	
	$scope.buscarTipoKit = function(idTpKit){
		if(idTpKit == undefined){
			$scope.rango.nbDispositivo="";
			$scope.rango.nbDispositivo="";
			$scope.filKit=false;
			return;
			
		}
		altaServicioService.buscarTipoKit(idTpKit)
		.success(function(data){
			$scope.tipoKitsDisp = data;
			$scope.range=[];
			$scope.filKit=true;
			$scope.rango.nbDispositivo="";
			$scope.rango.nbDispositivo="";
			
			for ( var i = 0; i < $scope.tipoKitsDisp.length ; i++ ) {				
				$("#select2-proveedor-"+i+"-container").text('Seleccione');
			}
			
			angular.forEach($scope.tipoKitsDisp, function(value, key){
				$scope.range.push(value.dispositivo);
			});
			$scope.formAltaServicio.$setPristine();
			$scope.error = false;
		})
		.error(function(data){
			$scope.tipoKitsDisp = [];
			$scope.error = true;
			$scope.filKit=false;
			growl.warning("No existen dispósitivos asociados al kit", { ttl: 5000 });
		})
	};
	
	var fhCurrent=moment().format("DD-MM-YYYY" );
	var fhCurrenDate=moment(fhCurrent, "DD-MM-YYYY");

	$('#fhCita').datetimepicker({ 
		
		 minDate: fhCurrenDate
	});
	    
	requiredFields = function(){
		angular.forEach($scope.formAltaServicio.$error, function (field) {
		      	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            
            })

		});
	};
	
	$scope.guardarOrden = function(valor, valorDos){
		
			if($scope.banderaVehiculo==false){
				
				growl.error("Es necesario que consultes la placa vehicular ", { ttl: 5000 });
				
			}
		
		  if($scope.formAltaServicio.$invalid){
				requiredFields();
			}else{
		
		var temp = valor.map(item =>{
			return {
				"idDispositivo" : item.idDispositivo, "serie" : item.serie, "proveedor" : item.proveedor
			}
		});
		console.log(temp);
		$scope.orden.kitInstalacionVO= temp;
		$scope.ordenVO = $scope.orden;
		$scope.ordenVO.idIncidencia = $scope.incidenciaVO.idIncidencia;
		console.log($scope.ordenVO);
		
		altaServicioService.altaOrdenServicio(valorDos).success(function(data){
			$scope.error = false;
			growl.success("Se ha generado una nueva orden de servicio con folio " + data.cdOrden , { ttl: 5000 });
			$scope.parametroBusqueda.incidencia="";
			$scope.cdPlacaVehiculo="";
			$scope.orden.fhCita = null;
			$scope.orden={};
			$scope.ordenVO={};
			$scope.mostrarAlta=false;
			$scope.formAltaServicio.$setPristine(); 
			$scope.banderaVehiculo=false;
			$scope.filKit=false;
			$scope.kitInstalacionVO = {};
			$(".select2").select2();
			$("#select2-tpKit-container").text('Seleccione');
			$("#select2-centroI-container").text('Seleccione');
			$("#select2-tipopVehiculo-container").text('Seleccione');
			$("#select2-catConcesionario-container").text('Seleccione');
			$("#select2-plan-container").text('Seleccione');
			$scope.rango.nbDispositivo="";
			$scope.rango.nbDispositivo="";
			
			for ( var i = 0; i < temp.length ; i++ ) {				
				$("#select2-proveedor-"+i+"-container").text('Seleccione');
			}
			$('#fhCita').val('');
			

			$scope.formAltaServicio.$setUntouched();
			
		}).error(function(data){
			$scope.error = true;
			growl.error("No se registraron los datos", { ttl: 5000 });
		});

	}
		
	};
	
	var date = new Date();
	$scope.minDate = date.setDate((new Date()).getDate());
	
	$scope.buscarIncidencia = function(cdIncidenc){
		
		  if ($scope.formAltaServicio.incidencia.$invalid) {
	            $scope.incidendiaRequerida=true;
	            return;
	        }
		altaServicioService.buscarIncidencia(cdIncidenc).success(function(data){
			
			$scope.incidenciaVO = data;
			$scope.mostrarAlta= true;
			$scope.incidencia.nbIncidencia = $scope.incidenciaVO.nbIncidencia;
			$scope.incidencia.txIncidencia = $scope.incidenciaVO.txIncidencia;
			console.log($scope.incidencia);
			
			
		}).error(function(data){
			growl.warning("No existe la incidencia", { ttl: 5000 });
			$scope.mostrarAlta=false
			
		})
		
		
	};
	
	$scope.getCatalogos = function(){
		altaServicioService.getCatalogos()
		.success(function(data){
			$scope.catalogoProcesos = data;
			$scope.error = false;
			
		})
		.error(function(data){
			$scope.catalogoProcesos ={};
			$scope.error=true;
			
		})
	};
	
	$scope.getCatalogos();
});