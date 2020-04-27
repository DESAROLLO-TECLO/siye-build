angular.module(appTeclo).service("seguimientoOsService", function($http, config) {

    const END_POINT="/monitoreo";

    //return $http.post(config.baseUrl + END_POINT+"/nuevoConductor",objetoVO);
	

	this.getInfoOsByRangoFechas = function(parametros){
		return $http.get(config.baseUrl + END_POINT + "/getSeguimientoOS",{
			params:{"fechaInicio":parametros.fechaInicio,
				    "fechaFin":parametros.fechaFin,
					"columnas":parametros.columnas,
					"colOmitidas":parametros.colOmitidas
				}});
	};

	this.getDetalleIncidencias = function(os){
		return $http.get(config.baseUrl + END_POINT + "/getDetalleIncidencia",{
			params:{"idOrdenServicio":os}});
	};


});