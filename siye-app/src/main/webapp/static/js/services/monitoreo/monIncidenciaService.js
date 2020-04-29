angular.module(appTeclo).service("monIncidenciaService", function($http, config, $timeout) {
	
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
	
	this.descargarReporteExcel = function (peticionReporteVO){
		return $http({
			method: 'POST',
			url: config.baseUrl + "/descargaExcel",
			data: peticionReporteVO,
			dataType: "json",
			header: {
				"Content-type": "application/json",
				"Accept": "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType: 'arraybuffer'
		});
	};
	
	this.downloadfile = function(file, fileName) {
		var url = window.URL || window.webkitURL;
		var blobUrl = url.createObjectURL(file);
		var a = document.createElement('a');
		a.href = blobUrl;
		a.target = '_blank';
		a.download = fileName;
		document.body.appendChild(a);
		$timeout(function() {
			a.click();
		}, 100);
	}
});