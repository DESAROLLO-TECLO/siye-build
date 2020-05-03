angular.module(appTeclo).service('consultaGeneralService', 
		function($http, config, $timeout) {
	
	parametrosBusquedaAnterior={};
	
	
	this.busquedaTramitesParametros = function(parametrosBusqueda){
		return $http.get(config.baseUrl + "/ordenServicio/consultaAvanzada",
		{
			params:{
				"busquedaAvanzada": parametrosBusqueda.busquedaAvanzada,
				"cdTipoBusqueda": parametrosBusqueda.cdTipoBusqueda,
				"valor": parametrosBusqueda.valor,
				"fhInicio": parametrosBusqueda.fhInicio,
				"fhFin": parametrosBusqueda.fhFin,
				"centroInstalacion":parametrosBusqueda.centroInstalacion,
				"estatusSeguimiento": parametrosBusqueda.estatusSeguimiento,
				"isLote": parametrosBusqueda.isLote,
				"isIncidencia":parametrosBusqueda.isIncidencia,
				"valorLoteIncidencia":parametrosBusqueda.valorLoteIncidencia,
				"tipoKit":parametrosBusqueda.tipoKit,
				"tipoPlan":parametrosBusqueda.tipoPlan

			}
		});
	};
	
	this.cargarTramitesDia = function(){
		return $http.get(config.baseUrl + "/ordenServicio/consultaOrdenDia");
	};
	
	this.getCentroInstalacion = function (cdTipo,val) {
		return $http.get(config.baseUrl + "/catalogo/consultaCentroIntalacion",
				{params:{"cdTipoBusqueda":cdTipo,"valor":val}});
	};
	
	this.getStSeguimiento = function (val) {
		return $http.get(config.baseUrl + "/catalogo/consultaStSegumientoByTipo",
				{params:{"tipoSeguimiento":val}});
	};
	
	this.getTipoKit = function(){
		return $http.get(config.baseUrl + "/catalogo/consultaTipoKit");
	};
	
	this.getPlan = function(){
		return $http.get(config.baseUrl + "/catalogo/consultaPlan");
	};
	
});
