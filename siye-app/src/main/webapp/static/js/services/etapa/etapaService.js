angular.module(appTeclo)
.service("etapaService", 
function($http, config) {

    this.getInfoEtapa = function(id){
        return $http.get(config.baseUrl + "/proceso/consultaOrdenServicioProceso", {
            params:{"idSolicitud": id}
        });
    }

});