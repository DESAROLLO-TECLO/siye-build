angular.module(appTeclo)
.service("procesoService", 
function($http, config) {

    this.getInfoProceso = function(id){
        return $http.get(config.baseUrl + "plan/...", {
            params:{"id": id}
        });
    }

});