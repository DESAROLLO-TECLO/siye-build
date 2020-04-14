angular.module(appTeclo).controller('dinamicReporteController',
    function($window, $scope, growl, showAlert, reporteConfiguracionService, storageService) {

        console.log("en el controller dinamicReporteController");

        $scope.mostrarBotonMius = true;
        $scope.nombreReporte = "";
        $scope.classCollapsedBoxPrincipal = "box box-danger";
        $scope.styleCollapse = {};
        $scope.helpers = {};


        $scope.cambiaIcono = function(fafIconReportes) {
            if (fafIconReportes == "fa fa-chevron-down")
                fafIconReportes = "fa fa-chevron-up";
            else
                fafIconReportes = "fa fa-chevron-down";
            return fafIconReportes;
        }

        /*Funciones para Agrupar */
        function agruparLista(l) {
            var many = 3,
                object;
            $scope.listaAgrupada = [];
            for (var i = 0; i < l.length; i += many) {
                object = {
                    reporte1: l[i]
                };

                if (l[i + 1] && (many == 2 || many == 3)) {
                    object.reporte2 = l[i + 1];
                }
                if (l[i + (many - 1)] && many == 3) {
                    object.reporte3 = l[i + 2];
                }
                $scope.listaAgrupada.push(object);
            }
            //console.log($scope.listaAgrupada);
        };

        configReporte = function(allReportes) {
            var listReportes = allReportes.reportes;
            var listTipos = allReportes.tipoReporte;

            angular.forEach(listTipos, function(tipo, key) {
                tipo.subReportes = [];
                angular.forEach(listReportes, function(reporte, key) {
                    if (tipo.idTipoReporte === reporte.idTipoReporte) {
                        tipo.subReportes.push(reporte);
                    }
                });
            });

            if (listTipos.length > 0) {
                $scope.lAux = [];
                for (var i = 0; i < listTipos.length; i++) {
                    if (listTipos[i].subReportes.length >= 1) {
                        $scope.lAux.push(listTipos[i]);
                    }
                }
            }
            angular.copy($scope.lAux, listTipos);
            agruparLista(listTipos);
        };

        /*Funciones para cargar los reportes que se puede accesar ,segun el nivel de usuario y permisso */
        //$scope.direccion = function (urlReporte,IdReporte){
        //	$scope.direccionCatalogos =urlReporte;
        //}
        getlistaReportes = function() {
            reporteConfiguracionService.sincronizaPerfil().success(function(data) {
                let perfiles = data.listaPerfiles;
                // Actualizar las uris de reportes en localStorage
                storageService.setParamsValApp(data.listaParametros);
                // Reescribir los archivos requerdidos de reportes
                let uriReporte = storageService.getParameterValApp('URLAPPREP');
                var configuracion = storageService.getConfiguration();
                reporteConfiguracionService.descargarActualizacion(configuracion.aplicacion.codigo, uriReporte).success(function(data) {
                    if (data) {
                        console.log('Recursos actualizados');
                    }
                }).error(function(data) {
                    console.log(data);
                });
                reporteConfiguracionService.listaReportes(perfiles).success(function(data) {
                    $scope.listCatReportes = [];
                    $scope.listCatReportes = configReporte(data);
                    $scope.error = false;
                }).error(function(data) {
                    $scope.error = data;
                    $scope.TiposReportes = {};
                    //growl.error(data.status.descripcion, { ttl: 5000 });
                    growl.error("No se encontraron registros", { ttl: 5000 });
                });
            }).error(function(data) {
                growl.error('Ocurrió un error la sincronizar perfiles', { ttl: 5000 });
            });
        };

        $scope.mostrarPanelTipoReporte = function(urlReporte, IdReporte) {
            $scope.minimPanelBusqueda = true;
            $scope.direccionCatalogos = urlReporte;
            $scope.classCollapsedBoxPrincipal = "box box-danger collapsed-box";
            $scope.classIconBoxPrincipal = "fa fa-plus";
            $scope.mostrarBotonMius = false;
            $scope.styleCollapse = "diaplay:none !important;";
        }
        getlistaReportes();

        /*$scope.buscarRegistros = function (idReporte){
        	console.log(idReporte);
        };*/
    });