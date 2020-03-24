angular.module(appTeclo).service('altaServicioService',function($http,config) {
	
	this.buscarMenuUsuario = function () {
		return $http.get(config.baseUrl + "/");
	};
	
});