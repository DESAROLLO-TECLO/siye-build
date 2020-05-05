angular.module(appTeclo).service('catalogoService',function($http,config) {
	
	this.buscarMenuUsuario = function () {
		return $http.get(config.baseUrl + "/");
	};
	
	this.getCatalogosActivos = function (){
		return $http.get(config.baseUrl + "/catalogo/buscaCatalogosActivos");
	};
	
});