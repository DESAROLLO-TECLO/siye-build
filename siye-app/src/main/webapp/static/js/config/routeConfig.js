angular.module(appTeclo).config(function($routeProvider, $locationProvider) {

    $routeProvider.when("/", {
        templateUrl: "login.html",
        controller: "loginController"
    });

    $routeProvider.when("/login", {
        templateUrl: "login.html",
        controller: "loginController"
    });

    $routeProvider.when("/error", {
        templateUrl: "views/error.html",
    });

    $routeProvider.when("/index", {
        templateUrl: "views/index.html",
    });

    $routeProvider.when("/accesoNegado", {
        templateUrl: "views/accesoNegado.html",
    });

    $routeProvider.otherwise({ redirectTo: "/index" });

    /*________** INICIO -> ADMINISTRACIÓN CONTROLLERS ** ________*/
    $routeProvider.when("/administracionModificaClave", {
        templateUrl: "views/administracion/administracionModificaClave.html",
        controller: "administracionModificaClaveController"
    });

    //	Configurar Aplicación
    $routeProvider.when("/configuracion", {
        templateUrl: "views/administracion/configuracionApp.html",
        controller: "configuracionAppController"
    });

    //	Componentes Web
    $routeProvider.when("/componentesWeb", {
        templateUrl: "views/administracion/resources/pluginsWeb.html",
        controller: "pluginsWebController"
    });

 // Carga de Archivo OTG
    $routeProvider.when("/upload", {
        templateUrl: "views/archivoLayout/cargarArchivoRuta.html",
        controller: "cargaArchivoLayoutController"
    });
    //	Orden servicio
    $routeProvider.when("/alta", {
        templateUrl: "views/ordenServicio/altaServicio.html",
        controller: "altaServicioController"
    });

    $routeProvider.when("/consulta", {
        templateUrl: "views/ordenServicio/consultaServicio.html",
        controller: "consultaServicioController",
        resolve: {
            opciones: function() {
                return { opt: null, val: null };
            }
        }
    });

    //	Usuarios
    $routeProvider.when("/users", {
        templateUrl: "views/administracion/users.html",
        controller: "usersController",
        resolve: {
            predata: async function(userService) {

                let response = new Object();

                try {
                    let typeSearch = await userService.getTypeSearch();
                    let searchForAll = await userService.getTypeSearchForAll();
                    let profiles = await userService.getProfiles();

                    response.typeSearch = typeSearch.data;
                    response.searchForAll = searchForAll.data;
                    response.profiles = profiles.data;

                    return response;
                } catch (e) {
                    return console.log("Ocurrió un error");
                }
            }
        }
    });

    $routeProvider.when("/catalogos", {
        templateUrl: "views/administracion/catalogos.html",
        controller: "catalogoController"

    });
    /*___________________________________________________________
    ________** FIN -> ADMINISTRACIÓN CONTROLLERS ** ___________*/

    /*___________________________________________________________
    ________** FIN -> ADMINISTRACIÓN CONTROLLERS ** ___________*/

    /*________** INICIO -> ETAPA ** ________*/
    $routeProvider.when("/etapas/:id", {
        templateUrl: "views/etapa/etapa.html",
        controller: "etapaController",
        resolve: {
            etapaInfo: function(etapaService, $route) {
                return etapaService.getInfoEtapa($route.current.params.id);
            }
        }
    });

    $routeProvider.when("/etapas/proceso/:idpro/:idord", {
        templateUrl: "views/etapa/proceso/proceso.html",
        controller: "procesoController",
        resolve: {
            procesoInfo: function(procesoService, $route) {
                return procesoService.getInfoProceso($route.current.params.idpro, $route.current.params.idord);
            },
            idord: function($route) {
                return $route.current.params.idord;
            },
            idpro: function($route) {
                return $route.current.params.idpro;
            }
        }
    });

    $routeProvider.when("/etapas/proceso/encuesta/:idencuesta/:idorden/:idproceso", {
        templateUrl: "views/etapa/proceso/encuesta/encuesta.html",
        controller: "encuestaController",
        resolve: {
            encuestaInfo: function(encuestaService, $route) {
                return encuestaService.getInfoEncuesta($route.current.params.idencuesta, $route.current.params.idorden);
            },
            idpro: function($route) {
                return $route.current.params.idproceso;
            }
        }
    });

    $routeProvider.when("/encuesta", {
        templateUrl: "views/encuesta/encuesta.html",
        controller: "encuestaSatisfaccionController"
    });

    $routeProvider.when("/modificar", {
        templateUrl: "views/ordenServicio/editar/editarOrdenServicio.html",
        controller: "editarOrdenServicioController"
    });

    $routeProvider.when("/informe", {
        templateUrl: "static/recursos/dinamicReporte.html",
        controller: "dinamicReporteController"
    });

    $routeProvider.when("/reporteDinamicoConsulta/:idReporte", {
        templateUrl: "static/recursos/formBusqueda.html",
        controller: "formBusquedaController",
        resolve: {
            reporte: function($route, $http, $q, config, storageService) {
                var URIWS = storageService.getParameterValApp('URLAPIREP');
                var deferred = $q.defer();
                $http({
                    method: 'GET',
                    url: URIWS + "/reporte/getReporte",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    params: {
                        'idReporte': $route.current.params.idReporte,
                        'validacion': 'validacion'
                    }
                }).then(function(reporteVO) {
                    let objectReporteVO = reporteVO.data;
                    let array = new Array();
                    let objetc = undefined;
                    if (objectReporteVO.parametrosAux) {
                        for (let i = 0; i < objectReporteVO.parametrosAux.length; i++) {
                            if (objectReporteVO.parametrosAux[i].txtQueryTipoCat != null) {
                                objetc = new Object({ 'idParamtro': objectReporteVO.parametrosAux[i].idParamtro, 'txQuery': objectReporteVO.parametrosAux[i].txtQueryTipoCat });
                                array.push(objetc);
                            }
                        }
                        if (array.length < 1) {
                            return deferred.resolve(objectReporteVO);
                        }
                    }

                    $http({
                        method: 'POST',
                        url: config.baseUrl + "/validacion/validacionParametroTipoCatalogoVO",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: array
                    }).then(function(data) {
                        let lisResult = data.data;
                        for (let j = 0; j < lisResult.length; j++) {
                            for (let k = 0; k < objectReporteVO.parametrosAux.length; k++) {
                                if (lisResult[j].idParamtro == objectReporteVO.parametrosAux[k].idParamtro) {
                                    objectReporteVO.parametrosAux[k].catValues = lisResult[j].catValues;
                                }
                            }
                        }
                        deferred.resolve(objectReporteVO);
                    });
                });
                return deferred.promise;
            }
        }
    });
    //	Indicendias
    $routeProvider.when("/altaIncidencia", {
        templateUrl: "views/incidencia/altaIncidencia.html",
        controller: "altaIncidenciaController",
        resolve: {
            dataInfo: function($route) {
                let info = {}
                return info;
            }
        }
    });
    $routeProvider.when("/altaIncidencia/:idOrden/:idProceso/:idEncuesta/:idPregunta/:urlActual", {
        templateUrl: "views/incidencia/altaIncidencia.html",
        controller: "altaIncidenciaController",
        resolve: {
            dataInfo: function($route) {
                let info = {
                    idOrden: $route.current.params.idOrden,
                    idProceso: $route.current.params.idProceso,
                    idEncuesta: $route.current.params.idEncuesta,
                    idPregunta: $route.current.params.idPregunta,
                    urlActual: $route.current.params.urlActual
                }
                return info;
            }
        }
    });
    //Expedinete
    $routeProvider.when("/cargaMasiva", {
        templateUrl: "views/expediente/expediente.html",
        controller: "expedienteController"
    });

    //Expedinete por nivel
    $routeProvider.when("/cargaMasiva/cargaNivel", {
        templateUrl: "views/expediente/expedineteRedirec.html",
        controller: "expedienteRedirectController",
        resolve: {
            params: function(expedienteService) {
                let params = expedienteService.getParams();
                return params;
            }
        }
    });

    // Seguimiento 
    $routeProvider.when("/seguimientoOS", {
        templateUrl: "views/monitoreo/seguimiento.html",
        controller: "seguimientoOsController",
        resolve:{
            lineaTiempoVO: function(detalleSeguimientoOsService){
                return detalleSeguimientoOsService.getconsultaGeneral();
            }
        }
    });

    // Linea de tiempo 
    $routeProvider.when("/detSegimientoOS", {
        templateUrl: "views/monitoreo/detalleSeguimientoOS.html",
        controller: "detalleSeguimientoOsController",
        resolve:{
            lineaTiempoVO: function(detalleSeguimientoOsService){
                return detalleSeguimientoOsService.getProcesosByOrdenServicio();
            }
        }
    });

    // Monitoreo Incidencias 
    $routeProvider.when("/monIncidencia", {
        templateUrl: "views/monitoreo/monIncidencia.html",
        controller: "monIncidenciaController"
    });
    
    $routeProvider.when("/seguimientoIncidencia/:idOrden/:idPlan", {
        templateUrl: "views/monitoreo/detalleIncidencia.html",
        controller: "detalleIncidenciaController",
        resolve: {
        	idOrden: function($route) { return $route.current.params.idOrden; },
        	idPlan: function($route) { return $route.current.params.idPlan; }
        }
    });

    /* Dashboard */
    $routeProvider.when("/tablero", {
        templateUrl: "views/dashboard/dashboard.html",
        controller: "dashboardController"
    });
    
    //consulta General
    $routeProvider.when("/consultaGeneral", {
        templateUrl: "views/ordenServicio/consultaGeneral.html",
        controller: "consultaGeneralController"
    });
    
    //consulta General
    $routeProvider.when("/detalleConsulta/:id", {
        templateUrl: "views/ordenServicio/detalleConsulta.html",
        controller: "detalleConsultaController"
    });

});