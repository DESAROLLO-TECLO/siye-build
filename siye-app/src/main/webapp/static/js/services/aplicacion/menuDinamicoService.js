angular.module(appTeclo)
.service('menuDinamicoService',
function($http,jwtService,storageService,config) {
	
	this.buscarMenuUsuario = function () {
		
		var token = storageService.getToken();
		var placa = jwtService.getPlacaUsuario(token);
		
		return $http.get(config.baseUrl + "/login/menus");
	
	};
	
});