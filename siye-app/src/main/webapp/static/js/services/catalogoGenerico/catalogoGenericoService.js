angular.module(appTeclo).service("catalogoGenericoService", function($http, config) {
	
const END_POINT="/catalogo";
	
this.cargarCatalogo = function (param) {
	return $http.get(config.baseUrl + END_POINT + "/getTransportistas");
};

this.saveCatalogo = function (objectVO) {
			return $http.get("https://jsonplaceholder.typicode.com/posts");
			};
		
});