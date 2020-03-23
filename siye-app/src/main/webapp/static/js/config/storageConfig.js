angular.module(appTeclo)
.config(['$localStorageProvider',
function ($localStorageProvider) {
	
	$localStorageProvider.setKeyPrefix('-');

}])