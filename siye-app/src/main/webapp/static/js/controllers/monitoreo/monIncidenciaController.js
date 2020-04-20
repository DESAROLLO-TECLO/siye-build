angular.module(appTeclo).controller('monIncidenciaController', 
	function ($rootScope, $scope, $location, $document, showAlert, growl, catalogoGenericoService, seguimientoOsService, detalleSeguimientoOsService) {
	//monIncidenciaService
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
	
	$scope.params = {
        colSeleccionada: [],
        columnas: [],
        colOmitidas: [],
        fechaInicio: moment().endOf('day').format('DD/MM/YYYY'),
        fechaFin: moment().endOf('day').format('DD/MM/YYYY')
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
            //consultaInicial();
        }).error(function (data) {

        });
    };
	
	getRangoFechas();
});