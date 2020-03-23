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
});