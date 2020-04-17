angular.module(appTeclo).service("monIncidenciaService", function($http, config) {
	
	const END_POINT="/monitoreo";
	
	this.getInfoOsByRangoFechas = function(parametros){
		return $http.get(config.baseUrl + END_POINT + "/getMonIncidencias",{
			params:{
				"fechaInicio"	: parametros.fechaInicio,
				"fechaFin"		: parametros.fechaFin,
				"tipoBusqueda"	: parametros.tipobusqueda,
				"valor"			: parametros.valor
			}
		});
	};
});