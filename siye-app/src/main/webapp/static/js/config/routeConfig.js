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

    //	Orden servicio
    $routeProvider.when("/alta", {
        templateUrl: "views/ordenServicio/altaServicio.html",
        controller: "altaServicioController"
    });

    $routeProvider.when("/consulta", {
        templateUrl: "views/ordenServicio/consultaServicio.html",
        controller: "consultaServicioController",
        resolve: {
            opciones: function(){
            	return {opt: null, val: null};
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
    /*___________________________________________________________
    ________** FIN -> ADMINISTRACIÓN CONTROLLERS ** ___________*/

    /*___________________________________________________________
    ________** FIN -> ADMINISTRACIÓN CONTROLLERS ** ___________*/

    /*________** INICIO -> ETAPA ** ________*/
    $routeProvider.when("/etapas/:id", {
        templateUrl: "views/etapa/etapa.html",
        controller: "etapaController",
        resolve: {
            etapaInfo : function(etapaService, $route){
                return etapaService.getInfoEtapa($route.current.params.id);
            }
        }
    });

    $routeProvider.when("/etapas/proceso", {
        templateUrl: "views/etapa/proceso/proceso.html",
        controller: "procesoController",
        resolve: {
            procesoInfo : function(procesoService, $route){
                return procesoService.getInfoProceso($route.current.params.id);
            }
        }
    });

    $routeProvider.when("/etapas/proceso/encuesta", {
        templateUrl: "views/etapa/proceso/encuesta/encuesta.html",
        controller: "encuestaController",
        resolve: {
            encuestaInfo : function(encuestaService, $route){
                return encuestaService.getInfoEncuesta($route.current.params.id);
            }
        }
    });

    $routeProvider.when("/editar/:id/:opt/:val", {
        templateUrl: "views/ordenServicio/editar/editarOrdenServicio.html",
        controller: "editarOrdenServicioController",
        resolve: {
            ordenServicio: function(consultaServicioService, $route) {
                return consultaServicioService.obtenerOrden($route.current.params.id);
            }
        }
    });

    $routeProvider.when("/consulta/:opt/:val", {
        templateUrl: "views/ordenServicio/consultaServicio.html",
        controller: "consultaServicioController",
        resolve: {
            opciones: function($route) {
                return {opt: $route.current.params.opt, val: $route.current.params.val};
            }
        }
    });

});