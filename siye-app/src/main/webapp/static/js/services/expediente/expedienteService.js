angular.module(appTeclo).service("expedienteService",
function($http, config) {
	
	const SAVE_UPDATE=config.baseUrl+"/saveOrUpdateExpedientes";
	const SAVE=config.baseUrl+"/saveExpedientes";
	const UPDATE=config.baseUrl+"/updateExpedientes";
	const DELETE=config.baseUrl+"/deleteExpedientes";
	
	this.saveOrUpdateExpediente = function(listExpedinetes) {
        return $http.post(SAVE_UPDATE);
    };
    
    this.saveExpediente = function(listExpedinetes) {
        return $http.post(SAVE);
    };
    
    this.updateExpediente = function(listExpedinetes) {
        return $http.put(UPDATE);
    };
    
    this.deleteExpediente = function(listExpedinetes) {
        return $http.put(DELETE);
    };
	
});