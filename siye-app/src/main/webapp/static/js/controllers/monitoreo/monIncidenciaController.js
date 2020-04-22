angular.module(appTeclo).controller('monIncidenciaController', 
	function ($rootScope, $scope, $location, $document, showAlert, growl, catalogoGenericoService, monIncidenciaService) {
	//monIncidenciaService
	$scope.flags = {
		showParametrosOS				: false,
		campoValorRequerido 			: false,
		labelValorRequerido 			: "Valor",
		mostrarTablaResultados			: false,
		mostrartablaResultadosCentros	: false,
		mostrartablaResultadosOS		: false
	}
	
	// Se declaran tipos de busqueda
    $scope.listTipoBusqueda ={
        tipoBusqueda: [
            {idTipo:1, cdTipo:"ORDEN",	nbTipo:"Orden de Servicio"},
            {idTipo:2, cd:"PLACA", 		nbTipo:"Placa Vehicular"},
            {idTipo:3, cdTipo:"VIN",	nbTipo:"VIN"} 
        ]
	};
    
	$scope.rangoFechas = {
        date: {
            startDate: moment().endOf('day'),
            endDate: moment().endOf('day')
        },
        options: {
            maxDate: moment().endOf("day"),
            locale: {
                applyLabel: "Aceptar",
                cancelLabel: 'Cancelar',
                customRangeLabel: 'PERSONALIZADO',
                format: 'D/MM/YYYY',
                separator: " - "
            },
            ranges: {}
        }
    };
	
	$scope.monIncidenciasVO = {
        datosTablaCentros	: [],
        respaldoConsulta	: [],
        nbCentroInstalacion	: "",
        idCentroInstalacion	: -1
    };
	
	$scope.params = {
        fechaInicio			: moment().endOf('day').format('DD/MM/YYYY'),
        fechaFin			: moment().endOf('day').format('DD/MM/YYYY'),
        tipoBusqueda		: -1,
        valor				: "",
        opcion				: 1,
        idCentroInstalacion	: -1
    };
	
	getRangoFechas = function () {
		catalogoGenericoService.getCatRangoFechas().success(function (data) {
			let rango = {};
			for (let x = 0; x < data.length; x++) {
				if (data[x].nbTipoFecha!= 'PERSONALIZADO') {
					rango[data[x].nbTipoFecha] = [data[x].fechaInicio, data[x].fechaFin];
				}
			}
			$scope.rangoFechas.options.ranges = rango;
			$scope.consultaMonIncidencias(1);
		}).error(function (data) {
			growl.error(data.message);
		});
	};
	
	$scope.consultaMonIncidencias = function (opcion) {
		//$scope.params.opcion = opcion;
		$scope.params.fechaInicio = $scope.rangoFechas.date.startDate.format('DD/MM/YYYY');
        $scope.params.fechaFin = $scope.rangoFechas.date.endDate.format('DD/MM/YYYY');
        
		monIncidenciaService.getMonIncidencias(
			$scope.params
		).success(function (data) {
			if(data.length > 0){
				$scope.flags.mostrarTablaResultados = true;
				$scope.monIncidenciasVO.datosTabla = data;
				if(opcion == 1){
					$scope.flags.mostrartablaResultadosCentros = true;
					$scope.flags.mostrartablaResultadosOS = false;

					$scope.monIncidenciasVO.respaldoCentros = angular.copy(data);
				}else if(opcion == 2){
					$scope.flags.mostrartablaResultadosCentros = false;
					$scope.flags.mostrartablaResultadosOS = true;
					
					$scope.monIncidenciasVO.respaldoOS = angular.copy(data);
				}
			}
		}).error(function (data) {
			growl.error(data.message);
		});
	};
	
	getRangoFechas();
});