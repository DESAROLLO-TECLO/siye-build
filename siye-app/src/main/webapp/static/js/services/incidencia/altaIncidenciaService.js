angular.module(appTeclo).service('altaIncidenciaService',function($http,config) {
	
	this.buscarMenuUsuario = function () {
		return $http.get(config.baseUrl + "/");
	};
	
	this.buscarTipoVehiculos = function(){
		return $http.get(config.baseUrl + "/catalogo/tipoVehiculo");
	};
	
	this.buscarVehiculo = function (placa) {
		return $http.get(config.baseUrl + "/proceso/buscaPlacaVehiculo", {
			params:{
					"placa": placa
					}
		});
	};	
});