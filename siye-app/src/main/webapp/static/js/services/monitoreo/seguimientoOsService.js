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

	this.getReporteExcel = function(params){
		return $http({
	        method: 'POST',
			url: config.baseUrl + END_POINT +  "/getReporGral",
			data:params,
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
		});
	};

	this.hacerCorteDiario = function(fecha){
		return $http.post(config.baseUrl + END_POINT +"/corteDiario",nuevoInsumo);
	};

	this.saveInsumo = function(nuevoInsumo){
		return $http.post(config.baseUrl + "/almacen/nuevoInsumo",nuevoInsumo);
	}

});