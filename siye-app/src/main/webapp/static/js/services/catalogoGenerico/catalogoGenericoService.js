angular.module(appTeclo).service("catalogoGenericoService", function($http, config) {
	
const END_POINT="/catalogo";
	
this.getTransportistas = function () {
	return $http.get(config.baseUrl + END_POINT + "/getTransportistas");
};

this.getTecnicos = function (param) {
	return $http.get(config.baseUrl + END_POINT + "/getTecnicos",
			{params:{"idTipoPersona":param==null?1:param}});
};

this.getTpIncidencia = function () {
	return $http.get(config.baseUrl + END_POINT + "/getTpIncidencia");
};

this.getPrioridad = function () {
	return $http.get(config.baseUrl + END_POINT + "/getPrioridad");
};

this.getOrdenServicio = function () {
	return $http.get(config.baseUrl + END_POINT + "/getOrdenServicio");
};

this.getModAten = function () {
	return $http.get(config.baseUrl + END_POINT + "/getModAten");
};

this.buscarPersona = function (cdPersona,idTipoPersona) {
	return $http.get(config.baseUrl + END_POINT + "/buscarPersona",
			{params:{"cdPersona":cdPersona,"idTipoPersona":idTipoPersona}});
};

this.getCatRangoFechas = function () {
	return $http.get(config.baseUrl + END_POINT + "/getCatTipoFechas");
};

});