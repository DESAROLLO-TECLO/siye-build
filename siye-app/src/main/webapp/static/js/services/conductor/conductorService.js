angular.module(appTeclo).service("conductorService", function($http, config) {
	
const END_POINT="/conductor";
	
this.nuevoConductor=function(objetoVO){
	return $http.post(config.baseUrl + END_POINT+"/nuevoConductor",objetoVO);
};

});