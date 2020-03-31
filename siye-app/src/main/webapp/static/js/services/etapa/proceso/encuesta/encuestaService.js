angular.module(appTeclo)
.service("encuestaService", 
function($http, config, $rootScope) {

    this.getInfoEncuesta = function(idEncuesta){
        return $http.get(config.baseUrl + "/encuesta/detalle", {
            params:{
                "idEncuesta": idEncuesta,
                "idOrdenServicio": parseInt($rootScope.idOrSer)
            }
        });
    }

});