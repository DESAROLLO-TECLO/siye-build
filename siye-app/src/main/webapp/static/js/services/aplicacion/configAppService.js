angular.module(appTeclo)
.service('configAppService',
function($http,config) {
	
	var app = "/aplicacion";
	var urlConfig = config.baseUrl+app;
	
	/*
	 * @author: Cesar Gomez
	 * @return: Array Objects
	*/
	this.configuracionAplicacion = function(data) {
		return $http.get(urlConfig + "/configuraciones");	
	};
	
	/*
	 * @author: Cesar Gomez
	 * @return: Array
	*/
	this.obtenerResoluciones = function(data) {
		return $http.get(urlConfig + "/resoluciones");
	}
	
	/*
	 * @author: Cesar Gomez
	 * @return: Array
	*/
	this.obtenerTemas = function(data) {
		return $http.get(urlConfig + "/temas");
	}
	
	/*
	 * @author: Cesar Gomez
	 * @param: configuracionVO
	 * @send: Array Objects
	*/
	this.guardarConfiguración = function(configuracionVO) {
		return $http.post(urlConfig + "/guardarConfiguracion", configuracionVO);
	}
 
});