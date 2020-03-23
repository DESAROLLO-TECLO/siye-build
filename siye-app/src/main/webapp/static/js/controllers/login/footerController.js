angular.module(appTeclo)
.controller("footerController",
function($rootScope,$scope,$window,$translate,$timeout,ModalService) {
	
	$scope.catIdiomas = 
	[
   		{"idIdioma" : 1, "codIdioma" : "es_ES", "txIdiomaEs" : "Español", "txIdiomaEn" : "Spanish"},
   		{"idIdioma" : 2, "codIdioma" : "en_US", "txIdiomaEs" : "Inglés", "txIdiomaEn" : "English"}
   	]
	
	comprobarIdioma = function() {
		
		var idSelectTraslate = $('#select2-selectTranslate-container');
		
		switch($rootScope.currentLanguage) {
			case "es_ES" :
				$scope.idiomaLang = $scope.catIdiomas[0];
				idSelectTraslate.text($scope.catIdiomas[0].txIdiomaEs);
				break;
			case "en_US" :
				$scope.idiomaLang = $scope.catIdiomas[1];
				idSelectTraslate.text($scope.catIdiomas[1].txIdiomaEn);
				break;
			default :
				$window.location.reload();
		}
	}
	
	$scope.changeLanguage = function (key) {
		$translate.use(key);
		$rootScope.currentLanguage = key;
		$rootScope.menuToRefresh(key, true);
		$rootScope.currentDate(key);
		comprobarIdioma();
	};
	
	$timeout(comprobarIdioma, 300);
});