angular.module(appTeclo).controller('consultaServicioController', function($scope, showAlert, $location, growl, consultaServicioService, opciones) {
    $scope.tabla = new Object({
        order: '',
        reverse: false,
        paginacion: [5, 10, 20, 50],
        cantidadPaginacion: 5,
        cantidadPaginacionVal: 5,
        view: new Object({
            filterSearchVal: '',
            rowsPerPageVal: 0
        })
    });

    $scope.parametroBusqueda = {
        tipoBusqueda: {},
        valor: null
    };



    buscarTipoBusqueda = function() {
        $scope.listTipoBusqueda = [
            { idTipoBusqueda: "0", cdTipoBusqueda: "TODO", nbTipoBusqueda: "TODO", txTipoBusqueda: "TODO" },
            { idTipoBusqueda: "1", cdTipoBusqueda: "PLACA", nbTipoBusqueda: "PLACA", txTipoBusqueda: "PLACA" },
            { idTipoBusqueda: "2", cdTipoBusqueda: "ORDEN_SERVICIO", nbTipoBusqueda: "ORDEN DE SERVICIO", txTipoBusqueda: "ORDEN DE SERVICIO" },
            { idTipoBusqueda: "3", cdTipoBusqueda: "VIN", nbTipoBusqueda: "VIN", txTipoBusqueda: "VIN" }
        ];

        // CUANDO CARGUE TODO EL CONTROLLER VALIDAMOS EL OBJETO
        if (opciones.opt != null && opciones.val != "null") {
            var opt = filtroBuscarTipoBusqueda(opciones.opt);
            if (filtroBuscarTipoBusqueda != null) {
                $scope.parametroBusqueda.tipoBusqueda = opt;
                $scope.parametroBusqueda.valor = opciones.val;
                $scope.buscarOrdenServicio(false);
            }
        }else{
        	$scope.parametroBusqueda.tipoBusqueda = $scope.listTipoBusqueda[0];
	}
    };

    function filtroBuscarTipoBusqueda(cd) {
        for (let i = 0; i < $scope.listTipoBusqueda.length; i++) {
            if (cd === $scope.listTipoBusqueda[i].cdTipoBusqueda) {
                return $scope.listTipoBusqueda[i];
            }
        }
        return null;
    };



    $scope.buscarOrdenServicio = function(ban) {
        if (ban && $scope.formConsultaServicio.$invalid) {
            showAlert.requiredFields($scope.formConsultaServicio);
            growl.warning("Formulario incompleto.", { ttl: 5000 });
            return;
        }
        consultaServicioService.buscarOrdenServicio($scope.parametroBusqueda).success(function(data) {
            if (data != null) {
                $scope.listServicio = data;
            }
        }).error(function(e) {
            $scope.listServicio = [];
            growl.warning(e.message, { ttl: 5000 });
        });
    }

    $scope.validarSelect = function () {
    	if($scope.parametroBusqueda.tipoBusqueda.idTipoBusqueda == 0){
    		$scope.parametroBusqueda.valor = null;
    		$scope.formConsultaServicio.valorBusqueda.$setPristine();
    	}
    }

    $scope.direccionar = function(idOrdenServicio) {
        showAlert.confirmacion("Se redireccionará a otra pantalla para realizar el servicio. ¿Desea continuar?",
            //Aceptar
            function() {
                $location.path("/etapas/" + idOrdenServicio);
                $('.modal-backdrop').remove();
            },
            //Cancelar
            function() {
                return;
            }
        );
    };

    buscarTipoBusqueda();
    $scope.buscarOrdenServicio(false);
    
    //  metodo para generarReporteExcel
    $scope.descargarExcel = function() {
        let peticionReporteVO = new Object();
        peticionReporteVO.header = getCabeceras();
        peticionReporteVO.values = getContenido($scope.listServicio);
        peticionReporteVO.titulo = "Reporte de Incidencias";
        consultaServicioService.descargarReporteExcel(peticionReporteVO).success(function(data, status, headers) {
            let filename = headers('filename');
            let file = new Blob([data], { type: 'application/vnd.ms-excel;base64,' });
            consultaServicioService.downloadfile(file, filename);
        }).error(function(data) {
            mostrarAviso(data);
        });
    };

    getCabeceras = () => {
        let headers = new Array();
        headers.push("ESTATUS");
        headers.push("FOLIO");
        headers.push("PLACA");
        headers.push("VIN");
        headers.push("CENTROINSTALACION");
        headers.push("FECHA CITA");
        headers.push("PLAN DE INSTALACION");
        headers.push("PROCESO ACTUAL");
        return headers;
    };
    getContenido = (list) => {
        let array = new Array();
        for (let i = 0; i < list.length; i++) {
            let elemento = new Array();
            elemento.push(list[i].stSeguimiento.nbStSeguimiento);
            elemento.push(list[i].cdOrdenServicio);
            elemento.push(list[i].vehiculo.cdPlacaVehiculo);
            elemento.push(list[i].vehiculo.cdVin);
            elemento.push(list[i].centroInstalacion.nbCentroInstalacion);
            elemento.push(list[i].fhCita);
            elemento.push(list[i].plan.nbPlan);
            elemento.push(list[i].proceso.nbProceso == null ? "SIN PROCESO" : list[i].proceso.nbProceso);
            array.push(elemento);
        }
        return array;
    };
    mostrarAviso = (data) => {
        if (data != undefined && data.status === 404) {
        	growl.error(data.message, { ttl: 5000 });
        	functionMostrarDatosGenerales();
        } else if (data != undefined && data.status === 400) {
        	growl.error(data.message, { ttl: 5000 });
        }
         else {
            growl.error('Ocurrió un error en la operación', { ttl: 5000 });
        }
    };
});
