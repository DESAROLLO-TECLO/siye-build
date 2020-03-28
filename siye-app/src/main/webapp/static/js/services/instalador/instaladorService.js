angular.module(appTeclo).service("instaladorService", function($http, config) {
	
const END_POINT="/instalador";
	
this.nuevoInstalador=function(objetoVO){
	return $http.post(config.baseUrl + END_POINT+"/nuevoInstalador",objetoVO);
};	
});