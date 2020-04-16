angular.module(appTeclo).controller('seguimientoOsController', function ($rootScope, $scope, $location, $document, showAlert, growl, catalogoGenericoService,
    seguimientoOsService,detalleSeguimientoOsService) {

    $scope.view = new Object({
        rowsPerPage:'5'
    });
    $scope.catTipoBusqueda = new Array(
        { idTipoBusqueda: 1, cdTipoBusqueda: "EN_CURSO", txTipoBusqueda: "EN CURSO" },
        { idTipoBusqueda: 2, cdTipoBusqueda: "COMPLETADAS", txTipoBusqueda: "COMPLETADAS" },
        { idTipoBusqueda: 3, cdTipoBusqueda: "PROGRAMADA", txTipoBusqueda: "PROGRAMADAS" },
        { idTipoBusqueda: 4, cdTipoBusqueda: "NO_PROGRAMADA", txTipoBusqueda: "NO PROGRAMADAS" },
        { idTipoBusqueda: 5, cdTipoBusqueda: "INCIDENCIAS", txTipoBusqueda: "INCIDENCIAS" }
    );
    $scope.verColumna = {
        col1: true,
        col2: true,
        col3: true,
        col4: true,
        col5: true
    };
    $scope.tamColumna = 'col-md-2';

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

    $scope.seguimientoVO = new Object({
        busquedaOk: false,
        verDetalleOS:false,
        tablaResultados:false,
        datosTabla:[],
        nbCentroInstalacion:""
    });

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
            consultaInicial();
        }).error(function (data) {

        });
    };

    consultaInicial = function () {
        verColumnas($scope.catTipoBusqueda)
        seguimientoOsService.getInfoOsByRangoFechas($scope.params).success(function (data) {
            $scope.seguimientoVO.tablaResultados = true;
            $scope.seguimientoVO.busquedaOk = true;
            $scope.seguimientoVO.respaldo = angular.copy(data);
            $scope.seguimientoVO.datosTabla = data;
        }).error(function (data) {
            growl.error(data.message);
        });
    };

    $scope.consultaOS = function (form, params) {
        if (validarFormulario(form)) {
            $scope.seguimientoVO.tablaResultados = false;
            $scope.seguimientoVO.verDetalleOS = false;
            $scope.view.searchSomething='';
            verColumnas(params.colSeleccionada);
            seguimientoOsService.getInfoOsByRangoFechas($scope.params).success(function (data) {
                $scope.seguimientoVO.tablaResultados = true;
                $scope.seguimientoVO.busquedaOk = true;
                $scope.seguimientoVO.respaldo = angular.copy(data);
                $scope.seguimientoVO.datosTabla = data;
            }).error(function (data) {
                growl.error(data.message);
            });
        }
    };

    $scope.verDetalle = function(centroInstalacion){
        if(centroInstalacion!=undefined){
            $scope.seguimientoVO.busquedaOk = false;
            $scope.seguimientoVO.verDetalleOS = true;
            $scope.view.rowsPerPage='5';
            $scope.view.searchSomething='';
            $scope.seguimientoVO.datosTabla=[];
            $scope.seguimientoVO.datosTabla = centroInstalacion.detalleOrdenServicio;
            $scope.seguimientoVO.nbCentroInstalacion = centroInstalacion.nbModulo;
        }
    };

    $scope.infoGral = function(){
        if($scope.seguimientoVO.respaldo!=undefined){
            $scope.seguimientoVO.verDetalleOS = false;
            $scope.seguimientoVO.busquedaOk = true;
            $scope.view.rowsPerPage='5';
            $scope.view.searchSomething='';
            $scope.seguimientoVO.datosTabla=$scope.seguimientoVO.respaldo;
        }
    };

    $scope.verDetalleStatus = function(ordenservicio){
        if(ordenservicio!=undefined){
            let pasoVentana = new Object({
                formBusuqeda:$scope.params,
                tablaTotales:$scope.seguimientoVO.respaldo,
                listaOs:$scope.seguimientoVO.datosTabla
            });
            debugger
            detalleSeguimientoOsService.saveconsultaGeneral(pasoVentana);
            $location.path('/detSegimientoOS');
        }
    };

    //Genericas 
    validarFormulario = function (formulario) {
        if (formulario.$invalid) {
            showAlert.requiredFields(formulario);
            growl.error('Campos requeridos');
            return false;
        } else {
            return true;
        }
    };

    verColumnas = function (columnas) {
        const CATAMANO = columnas.length;
        const catColumnas = $scope.catTipoBusqueda.length;
        $scope.params.columnas = [];
        $scope.params.colSeleccionada = [];
        $scope.params.colOmitidas = [];
        $scope.tamColumna = 'col-md-' + 12 / (CATAMANO + 1);

        // Obtener las fechas del componente 
        debugger
        $scope.params.fechaInicio = $scope.rangoFechas.date.startDate.format('DD/MM/YYYY');
        $scope.params.fechaFin = $scope.rangoFechas.date.endDate.format('DD/MM/YYYY');
        
        for (let x = 0; x < CATAMANO; x++) {
            $scope.params.columnas.push($scope.catTipoBusqueda[x].cdTipoBusqueda);
            $scope.params.colSeleccionada.push($scope.catTipoBusqueda[x]);
        }

        if (CATAMANO < catColumnas) {
            for (let x = 0; x < catColumnas; x++) {
                let noExiste = true;
                for (let y = 0; y < CATAMANO; y++) {
                    if ($scope.catTipoBusqueda[x].cdTipoBusqueda === $scope.params.colSeleccionada[y].cdTipoBusqueda) {
                        if ($scope.catTipoBusqueda[x].idTipoBusqueda === 1) {
                            $scope.verColumna.col1 = true;
                        } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 2) {
                            $scope.verColumna.col2 = true;
                        } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 3) {
                            $scope.verColumna.col3 = true;
                        } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 4) {
                            $scope.verColumna.col4 = true;
                        } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 5) {
                            $scope.verColumna.col5 = true;
                        }
                        noExiste = false;
                        break;
                    }
                }
                if (noExiste) {
                    $scope.params.colOmitidas.push($scope.catTipoBusqueda[x].cdTipoBusqueda);
                    if ($scope.catTipoBusqueda[x].idTipoBusqueda === 1) {
                        $scope.verColumna.col1 = false;
                    } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 2) {
                        $scope.verColumna.col2 = false;
                    } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 3) {
                        $scope.verColumna.col3 = false;
                    } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 4) {
                        $scope.verColumna.col4 = false;
                    } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 5) {
                        $scope.verColumna.col5 = false;
                    }
                }
            }
        }else{
            $scope.verColumna = {
                col1: true,
                col2: true,
                col3: true,
                col4: true,
                col5: true
            }; 
        }
    };

    getRangoFechas();
});