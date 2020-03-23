angular.module(appTeclo).service('cambioContraseniaService', function($http, config) {

	var myPath = "/usuarios";
	   
	this.cambioContrasenia = function ( oldPassword, newPassword, repeatPassword) {
        var cambioContrasenia = {"password":oldPassword , "newPassword": newPassword , "newPasswordRepeat": repeatPassword};
		return $http.put(config.baseUrl + myPath + "/password",cambioContrasenia);
    }
	
});