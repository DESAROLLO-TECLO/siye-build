angular.module(appTeclo)
.service("etapaService", 
function($http, config) {

    this.getInfoEtapa = function(idSolicitud){
        return $http.get(config.baseUrl + "/proceso/ordenServicioProceso", {
            params:{
                "idSolicitud": idSolicitud
            }
        });
    }

    this.getPlan = function(idPlan, idOrden){
        return $http.get(config.baseUrl + "/proceso/plan", {
            params:{
                "idPlan": idPlan, 
                "idOrden": idOrden
            }
        });
    }

    this.getTransportistasVehiculo = function(id){
        return $http.get(config.baseUrl + "/catalogo/getTransportistasVehiculo", {
            params:{
                "idVehiculo": id
            }
        });
    }

});