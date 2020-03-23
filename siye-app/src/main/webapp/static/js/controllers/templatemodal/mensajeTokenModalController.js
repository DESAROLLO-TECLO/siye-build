angular.module(appTeclo)
.controller('mensajeTokenModalController',
function($rootScope,$scope,$location,$filter,$element,$window,
		jwtService,storageService,loginService,logOutService,close) {
	
	$scope.progress = 0;
	$scope.contador = 30;
	var interval; 
 	
	$scope.close = function(result) {
	 	close(result, 900);
	 };
	 
	 $scope.salir = function() {
		loginService.logout();
		logOutService.StopTimer();
//		$location.path('/login');
		$window.location.reload();
		$('.modal').modal('hide');
 		$('.modal-backdrop').hide();
			
	};
 
	interval = window.setInterval(function() {
	 	$scope.$apply(function() {
	 		if ($scope.progress <= 100){
	 			$scope.progress += 3.3;
	 			if($scope.contador > 0)
	 				$scope.contador -= 1;
	 		}
	 	});
	 }, 1000);
	  
	 $scope.$watch("progress", function() {
		if(typeof(storageService.getToken()) != "undefined"  ){
			var elapsedTime = $filter('timeMinutes')(storageService.getLastRequest());
			var inactivity = jwtService.getInactivity(storageService.getToken());
			var statusAlert= storageService.getStatusAlert();

			if ($scope.progress > 100) {		 		
				logOutService.StopTimer();
				loginService.logout();
//				$location.path('/login');
				$window.location.reload();
				$('.modal').modal('hide');
				$('.modal-backdrop').hide();
			}
		 	
			if(!jwtService.isTokenExpired(storageService.getToken(),0) &&  elapsedTime < inactivity && statusAlert == 'true' ){
				$('.modal').modal('hide');
				$('.modal-backdrop').hide();
				clearInterval(interval);
				storageService.setStatusAlert(false);
				logOutService.StopTimer();
			}
		}
	}, true);
});

