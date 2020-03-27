angular.module(appTeclo)
.service("encuestaService", 
function($http, config) {

    this.getInfoEncuesta = function(id){
        return $http.get(config.baseUrl + "plan/...", {
            params:{"id": id}
        });
    }

});