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
	
	this.altaOrdenServicio = function(ordenVO){
	return $http.post(config.baseUrl + "/ordenServicio/guardarReporteBd", ordenVO);	
	}
	
	this.buscarIncidencia = function(cdIncidenc){
		return $http.get(config.baseUrl + "/incidencia/incidenciaByCdIncidencia",{
			params:{
				"cdIncidenc" : cdIncidenc
			}
		});
	};
	
});