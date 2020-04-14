angular.module(appTeclo).controller('formBusquedaController',
    function($scope, reporteConfiguracionService, growl, $timeout, $filter, showAlert, reporte, $location, $rootScope) {

        $scope.b = false;
        $scope.reporte = {};
        var reporteTmp = reporte;
        $scope.listaParametros = [];
        $scope.listaParametrosTMP = [];
        $scope.avisoResultados = undefined;
        $scope.objectVO = { parametros: {} };
        $scope.dataTmp = [];
        $scope.cabeceras = [];
        $scope.valores = [];
        $scope.nbReporteGeneric = '';
        $scope.rowCollection = [];

        if (reporteTmp != null || reporteTmp != undefined) {
            angular.copy(reporteTmp, $scope.reporte);
            $scope.nbReporteGeneric = $scope.reporte.nbReporte;
            if ($scope.reporte.parametrosAux == null) {
                $scope.listaParametros = [];
                $scope.listaParametrosTMP = [];
            } else if ($scope.reporte.parametrosAux != null && $scope.reporte.parametrosAux.length > 0) {
                angular.copy($scope.reporte.parametrosAux, $scope.listaParametros);
                $scope.listaParametrosTMP = $scope.listaParametros;
            }
        }
        $scope.buscar = function(parametros, formDinamico) {
            var mapPram = []; //lista de parametros con su valor
            var objectMap = {}; //objeto individual de parametro
            $scope.avisoResultados = undefined;
            if ($scope.fName.$invalid) {
                showAlert.requiredFields($scope.fName);
                growl.error('Formulario incompleto');
                return;
            } else {
                if ($rootScope.listaValido.isListaDoble && !$rootScope.listaValido.listaValidado) {
                    growl.warning('Se deben validar la búsqueda previa', { ttl: 4000 });
                    return;
                }
                var mapReturn = makeReduceMapParams(parametros);
                if (mapReturn != undefined && mapReturn != null) {
                    angular.copy(mapReturn, $scope.objectVO.parametros);
                    //console.log($scope.objectVO);
                    $scope.objectVO.reportesTaqVO = reporteTmp;
                    $scope.methodConsultaDinamica($scope.objectVO);
                } else {
                    return;
                }
            }
        };

        $scope.exportExcel = function(voObject, formDinamico) {
            if ($rootScope.listaValido.isListaDoble && $rootScope.listaValido.listaValidado) {
                var strValoresBus = formDinamico['busquePrevia' + $rootScope.idParametro];
                var txSeparador = $rootScope.listaValido.separador;
                var listaValoredOri = '';
                if (strValoresBus != undefined && txSeparador != undefined) {
                    var separado = strValoresBus.$modelValue.split(txSeparador);
                    for (var i = 0; i < separado.length; i++) {
                        if (i > 0)
                            listaValoredOri += ',';
                        listaValoredOri += separado[i];
                    }
                    voObject.strValoresListDoble = listaValoredOri;
                }
            }
            voObject.reportesTaqVO = reporteTmp;
            reporteConfiguracionService.methodDescargaExcel(voObject).success(function(data, status, headers, config) {
                if (data != null || data != '') {
                    var filename = headers('filename');
                    //var contentType = headers('content-type');
                    var file = new Blob([data], { type: 'application/vnd.ms-excel;base64,' });
                    reporteConfiguracionService.save(file, filename);

                } else {
                    growl.error($scope.mensajeModal('Algo salió mal al procesar la solicitud') + ': ' + data.message, { ttl: 3000 });
                }
            }).error(function(data, status, headers, config) {
                growl.error($scope.mensajeModal('Algo salió mal al procesar la solicitud') + ': ' + data.message, { ttl: 3000 });
            });
        };

        function makeReduceMapParams(parametros) {
            var mapPram = []; //lista de parametros con su valor
            var objectMap = {}; //objeto individual de parametro
            //asignar objeto de cajón que pertene al reporte
            if (parametros.length > 0)
                objectMap = { nbParam: 'idReporte', valor: parametros[0].idReporte.toString() };
            else
                objectMap = { nbParam: 'idReporte', valor: reporteTmp.idReporte.toString() };
            mapPram.push(objectMap); //agregsr objeto de cajon a la lista
            //console.log($rootScope.nuevoModelo);
            angular.forEach(parametros, function(value, key) {
                var t = $scope.fName[value.cdParametro];
                if (t != undefined) {
                    value.txValor = t.$modelValue;
                    let v = (value.txValor == null || value.txValor == undefined) ? null : value.txValor.toString();
                    objectMap = { nbParam: value.cdParametro, valor: v }; //asignar objeto individual de paramtros
                    mapPram.push(objectMap); //agregar lista de paramtros
                }
            });
            var nuevoModelo = $rootScope.nuevoModelo[1].listado;
            var idPListaDoble = $rootScope.idParametro;
            var idPeticion = '';
            if (nuevoModelo != null && nuevoModelo != undefined && nuevoModelo.length > 0) {
                for (var i = 0; i < nuevoModelo.length; i++) {
                    if (i > 0)
                        idPeticion += ',';
                    idPeticion += nuevoModelo[i].LD_KEY;
                }
                for (var i = 0; i < parametros.length; i++) {
                    if (parametros[i].idParamtro === idPListaDoble) {
                        var objectMapParamListaDoble = {};
                        objectMapParamListaDoble = { nbParam: parametros[i].cdParametro, valor: idPeticion.toString() };
                        mapPram.push(objectMapParamListaDoble);
                        break;
                    }
                }
            } else if ((idPListaDoble != undefined || idPListaDoble != null) && nuevoModelo.length <= 0 && $rootScope.listaValido.isListaDoble && $rootScope.listaValido.listaValidado) {
                growl.error('Agrega el menos un valor', { ttl: 3000 });
                return;
            }
            var mapParams = _.reduce(mapPram, function(hash, value) {
                var key = value['nbParam'];
                hash[key] = value['valor'];
                return hash;
            }, {});
            return mapParams;
        };

        $scope.methodConsultaDinamica = function(map) {
            $scope.cabeceras = [];
            $scope.valores = [];
            $scope.rowCollection = [];
            reporteConfiguracionService.methodConsultaDinamica(map).success(function(data, status, headers, config) {
                var json = '';
                if (data != null || data != '') {
                    angular.copy(data.cabeceras, $scope.cabeceras);
                    $scope.cabeceras = reporteConfiguracionService.normalizeHeaders($scope.cabeceras);
                    angular.copy(data.values, $scope.valores);
                    $scope.valores = reporteConfiguracionService.convertMapKeyAndValue($scope.valores, $scope.cabeceras);
                    $scope.gridOptions.data = $scope.valores;
                    $scope.dataTmp = $scope.valores;
                    $scope.rowCollection = $scope.valores;
                    //console.log($scope.reporte);
                    if (data.msjAvisoMax != null) {
                        $scope.avisoResultados = data.msjAvisoMax;
                        growl.warning($scope.avisoResultados, { ttl: 5000 });
                    }
                } else {
                    growl.error($scope.mensajeModal('Error de formato en el json'), { ttl: 3000 });
                }
            }).error(function(data, status, headers, config) {
                if (status === 404) {
                    growl.error('No se encontraron registros', { ttl: 5000 });
                } else {
                    growl.error($scope.mensajeModal('Algo salió mal al procesar la solicitud') + ': ' + data.message, { ttl: 5000 });
                }
            });
        };


        $scope.gridOptions = {
            data: [],
            urlSync: false,
            pagination: {
                itemsPerPage: '10'
            },
            enableHorizontalScrollbar: true,
            enableVerticalScrollbar: true
        };

        $scope.refreshData = function() {
            $scope.gridOptions.data = $filter('filter')($scope.dataTmp, $scope.filtroBusqueda);
            $scope.rowCollection = $scope.gridOptions.data;
        };

        $scope.regresar = function() {
            $rootScope.nuevoModelo = [];
            $rootScope.idParametro = undefined;
            $rootScope.listaValido.isListaDoble = false;
            $rootScope.listaValido.listaValidado = false;
            $rootScope.listaValido.separador = undefined;
            $location.path("/informe");
        };
    });