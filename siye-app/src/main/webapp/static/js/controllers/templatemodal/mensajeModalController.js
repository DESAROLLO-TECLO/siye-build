angular.module(appTeclo).controller('mensajeModalController', function($scope, $element, message, close) {
	
	$scope.message = message;
	
	$scope.close = function(result){
		$('.modal-backdrop').remove();
		close(result, 100);
	};
});