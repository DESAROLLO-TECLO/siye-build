angular.module(appTeclo)
.service('logOutService',
function($http,$rootScope,$localStorage,$q,$location,$interval,$filter,
		jwtService,config,loginService,storageService,ModalService) {
	
	var funciones = this;
	var Timer = undefined;

	refresh = function() {
		return $http.get(config.baseUrl + "/login/refresh");
	};

	refreshToken = function() {
		refresh().success(function(data) {
			storageService.setToken(data.token);
		}).error(function(data) {});
	};

	this.StartTimer = function() {
		if (storageService.getToken())
			Timer = $interval(function() {
				callAtInterval();
			}, 8000);
	};

	this.StopTimer = function() {
		if (angular.isDefined(Timer)) {
			$interval.cancel(Timer);
		}
	};
	
	function callAtInterval() {
		if (typeof (storageService.getToken()) != "undefined") {
			var elapsedTime = $filter('timeMinutes')(storageService.getLastRequest());
			var inactivity = jwtService.getInactivity(storageService.getToken());
			var statusAlert = storageService.getStatusAlert();
			if (!jwtService.isTokenExpired(storageService.getToken(), 0) && elapsedTime > inactivity && statusAlert != 'true') {
				
				storageService.setStatusAlert(true);
				funciones.StopTimer();
				showConfirmacion();
				
			}
     	}else{
     		loginService.logout();
     		funciones.StopTimer();
    		$location.path('/login');
     	}
    }

	function showConfirmacion() {

		//	Destruir el popover al salir de la pantalla de configuracion
		WebuiPopovers.hideAll();

		ModalService.showModal({
			templateUrl : 'views/templatemodal/templateModalToken.html',
			controller : 'mensajeTokenModalController',
		}).then(function(modal) {
			modal.element.modal();
			modal.close.then(function(result) {
				if (result) {
					refreshToken();
					funciones.StartTimer();
				}

			});
		});
	};
});