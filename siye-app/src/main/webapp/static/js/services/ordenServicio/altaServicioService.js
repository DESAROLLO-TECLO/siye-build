angular.module(appTeclo).service('altaServicioService',function($http,config) {
	
	this.buscarMenuUsuario = function () {
		return $http.get(config.baseUrl + "/");
	};
	
	
	this.buscarTipoVehiculos = function(){
		return $http.get(config.baseUrl + "/catalogo/tipoVehiculo");
	};
	
	this.buscarCentroInstalacion = function(){
		return $http.get(config.baseUrl + "/catalogo/getCatOrdenProceso")
	};
	
	this.buscarVehiculo = function (placa) {
		return $http.get(config.baseUrl + "/proceso/buscaPlacaVehiculo", 
				{params:{
					"placa": placa}});
	};
	this.buscarTipoKit = function(idTipoKit){
		return $http.get(config.baseUrl + "/proceso/dipositivosPorKit",
				{params:{
					"idTipoKit" : idTipoKit}});
	};
	
});