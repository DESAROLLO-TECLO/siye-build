angular.module(appTeclo).service("catalogoGenericoService", function($http, config) {
	
const END_POINT="/catalogo";
	
this.getTransportistas = function () {
	return $http.get(config.baseUrl + END_POINT + "/getTransportistas");
};

this.getTecnicos = function (param) {
	return $http.get(config.baseUrl + END_POINT + "/getTecnicos",
			{params:{"idTipoPersona":param}});
};
		
});