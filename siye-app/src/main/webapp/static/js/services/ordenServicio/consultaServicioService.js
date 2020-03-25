angular.module(appTeclo).service('consultaServicioService',function($http,config) {
	
	this.buscarTipoBusqueda = function (){
		return $http.get(config.baseUrl + "/");
	}
	
	this.buscarOrdenServicio = function (params) {
		return $http.get(config.baseUrl + "/", {
			params : {
				"cdTipoBusqueda": params.cdTipoBusqueda,
				"valor": params.valor
			}
		});
	};
	
});