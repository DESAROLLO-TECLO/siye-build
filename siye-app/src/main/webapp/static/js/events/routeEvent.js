angular.module(appTeclo)
.run(function($rootScope,$location,$localStorage,$timeout,
		storageService,loginService,logOutService,$route,jwtService) {
	
	$timeout(function() {		
		logOutService.StartTimer();
	}, true);
	
	$rootScope.$on("$routeChangeStart", function(event, next, current) {

		var token = storageService.getToken();
		
		if ( !token ) {
			$rootScope.$evalAsync(function() {
				//	Destruir el popover al salir de la pantalla de configuracion
				WebuiPopovers.hideAll();
				
				$location.path('/');
			})
		}
		else if(next.$$route.originalPath === "/" && token) {
			$location.path('/index');
		}
		else if(next.$$route.originalPath === "/componentesWeb" && token) {
			$location.path('/componentesWeb');
		}
		else if (jwtService.isTokenExpired(token, 0)) {
			loginService.logout();
			logOutService.StopTimer();
			$rootScope.$evalAsync(function() {
				$location.path('/');
			})
		}
		else if (token && !jwtService.hasAccess(next.$$route.originalPath, token)) {
			$location.path('/accesoNegado');
		}
	});
});