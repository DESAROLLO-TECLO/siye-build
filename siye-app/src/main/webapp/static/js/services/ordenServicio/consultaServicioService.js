angular.module(appTeclo).service('consultaServicioService',function($http,config) {
	
	this.buscarMenuUsuario = function () {
		return $http.get(config.baseUrl + "/");
	};
	
});