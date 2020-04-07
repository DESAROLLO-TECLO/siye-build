angular.module(appTeclo).controller('seguimientoOsController', function ($rootScope, $scope, $location, $document, showAlert,  growl) {

    $scope.catTipoBusqueda = new Array(
        { idTipoBusqueda: "1", cdTipoBusqueda: "PLACA", nbTipoBusqueda: "PLACA", txTipoBusqueda: "PLACA" },
        { idTipoBusqueda: "2", cdTipoBusqueda: "ORDEN_SERVICIO", nbTipoBusqueda: "ORDEN DE SERVICIO", txTipoBusqueda: "ORDEN DE SERVICIO" },
        { idTipoBusqueda: "3", cdTipoBusqueda: "VIN", nbTipoBusqueda: "VIN", txTipoBusqueda: "VIN" }
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

});