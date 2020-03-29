angular.module(appTeclo).service("encuestaSatisfaccionService", function($http, config) {
	
const END_POINT="/encuesta";
	
this.servicio = function () {

	return $http.get(config.baseUrl + "/encuestaSatisfaccion/prueba"); 
};
});
