angular.module(appTeclo).controller('seguimientoOsController', function ($rootScope, $scope, $location, $document, showAlert,  growl, seguimientoOsService) {

    $scope.catTipoBusqueda = new Array(
        { idTipoBusqueda: "1", cdTipoBusqueda: "ENCURSO",  txTipoBusqueda: "EN CURSO" },
        { idTipoBusqueda: "2", cdTipoBusqueda: "COMPLETADAS", txTipoBusqueda: "COMPLETADAS" },
        { idTipoBusqueda: "4", cdTipoBusqueda: "PROGRAMADAS", txTipoBusqueda: "PROGRAMADAS" },
        { idTipoBusqueda: "5", cdTipoBusqueda: "NO PROGRAMADAS", txTipoBusqueda: "NO PROGRAMADAS" },
        { idTipoBusqueda: "6", cdTipoBusqueda: "INCIDENCIAS", txTipoBusqueda: "INCIDENCIAS" }
    );	

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
                customRangeLabel: 'RANGO DE FECHAS',
                format: 'D/MM/YYYY',
                separator: " - "
            },
            ranges: {}
        }
    };

    $scope.seguimientoVO = new Object({
        busquedaOk:false,
        seguimientoTotales:[]
    });
    
    consultaInicial = function(){
        seguimientoOsService.getInfoOsByRangoFechas(params).success(function(data){

        }).error(function(data){

        });
    }

    $scope.consultaOS = function(form, paramBusqueda){
        if(validarFormulario(form)){

        }
    }


    //Genericas 
    validarFormulario = function(formulario){
        if (formulario.$invalid) {
			showAlert.requiredFields(formulario);
			growl.error('Campos requeridos');
			return false;
		} else {
			return true;
		}
    };

    consultaInicial();
});