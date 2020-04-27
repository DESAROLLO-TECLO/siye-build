angular.module(appTeclo).service("monIncidenciaService", function($http, config) {
	
	const END_POINT="/monitoreo";
	
	this.getMonIncidencias = function(parametros){
		return $http.get(config.baseUrl + END_POINT + "/getMonIncidencias",{
			params:{
				"fechaInicio"			: parametros.fechaInicio,
				"fechaFin"				: parametros.fechaFin,
				"tipoBusqueda"			: parametros.tipoBusqueda,
				"valor"					: parametros.valor,
				"opcion"				: parametros.opcion,
				"idCentroInstalacion"	: parametros.idCentroInstalacion
			}
		});
	};
	
	this.getDetalleIncidenciasOS = function(idOrden, idPlan){
		return $http.get(config.baseUrl + END_POINT + "/getDetalleIncidenciasOS",{
			params:{
				"idOrden":idOrden,
				"idPlan":idPlan,
			}
		});
	};
	this.getExpedienteIncidencia = function(idIncidencia){
		return $http.get(config.baseUrl + END_POINT + "/getExpedienteIncidencia",{
			params:{
				"idIncidencia":idIncidencia
			}
		});
	};
	
	this.getIncidenciasByTipobusqueda = function(parametros){
		return $http.get(config.baseUrl + END_POINT + "/getIncidenciasByTipobusqueda",{
			params:{
				"fechaInicio"			: parametros.fechaInicio,
				"fechaFin"				: parametros.fechaFin,
				"tipoBusqueda"			: parametros.tipoBusqueda,
				"valor"					: parametros.valor,
				"idCentroInstalacion"	: parametros.idCentroInstalacion
			}
		});
	};
});