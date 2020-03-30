angular.module(appTeclo)
.service("procesoService", 
function($http, config) {

    this.getInfoProceso = function(idProceso,idOrden){
        return $http.get(config.baseUrl + "/proceso/encuestasProceso", {
            params:{
                "idProceso": idProceso,
                "idOrden": idOrden
            }
        });
    }

});