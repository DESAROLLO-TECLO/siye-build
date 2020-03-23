angular.module(appTeclo)
.service('loginService',
function($rootScope,$http,$localStorage,$window,$q,$route,
		storageService,config) {
	
	this.login = function(data) {
		return $http.post(config.baseUrl + "/login", data);
	};
	
	this.logout = function() {
		
		storageService.setStatusAlert(false);
		storageService.deleteStorage($rootScope.stg.tokenName);
		storageService.deleteStorage($rootScope.stg.statusAlertName);
		storageService.deleteStorage($rootScope.stg.lastRequestName);
		storageService.deleteStorage($rootScope.stg.configApp);
		storageService.deleteStorage($rootScope.stg.configAppTmp);
		storageService.deleteStorage($rootScope.stg.abtBs);
		
		delete $rootScope.stg;
		delete $rootScope.menuDinamico;
		
		$rootScope.comprobarStorage();
		$q.when();
	};
	
	this.safeApply = function(fn) {
		var $root = $rootScope,
			phase = $root.$$phase;
    	
    	if(phase == '$apply' || phase == '$digest') {
    		if(fn && (typeof(fn) === 'function')) {
    			fn();
    		}
    	} else {
    		$apply(fn);
    	}
	}
});