angular.module(appTeclo)
.service("encuestaService", 
function($http, config, $rootScope) {

    var idOrdenServicio = $rootScope.idOrSer;
    console.log("id Orden Servicio " + idOrdenServicio);

    this.getInfoEncuesta = function(idEncuesta){
        return $http.get(config.baseUrl + "/encuesta/detalle", {
            params:{
                "idEncuesta": idEncuesta,
                "idOrdenServicio": idOrdenServicio
            }
        });
    }

});