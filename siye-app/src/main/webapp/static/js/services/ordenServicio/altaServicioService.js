angular.module(appTeclo).service('altaServicioService',function($http,config) {
	
	this.buscarMenuUsuario = function () {
		return $http.get(config.baseUrl + "/");
	};
	
	
	this.buscarTipoVehiculos = function(){
		return $http.get(config.baseUrl + "/catalogo/tipoVehiculo");
	};
	
	this.buscarCentroInstalacion = function(){
		return $http.get(config.baseUrl + "/catalogo/getCatOrdenProceso")
	}
	
	this.buscarVehiculo = function (valor) {
		return $http.get(config.baseUrl + "/falta", 
				{params:{
					"valor": valor}});
	};
	
});