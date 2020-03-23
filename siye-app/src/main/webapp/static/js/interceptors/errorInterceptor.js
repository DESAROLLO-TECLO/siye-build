angular.module(appTeclo)
.factory("errorInterceptor",
function ($q,$location,$timeout) {
	return {
		responseError: function (rejection) {
			
			removeModal = function() {
				$timeout(function() {
					$('body').removeClass('modal-open');
					$('.modal-backdrop').remove();
				});
			}
			
			if (rejection.status === 500) {
				$location.path("/error");
				removeModal();
			}
			
			if (rejection.status === 401) {
				$location.path("/accesoNegado");
				removeModal();
			}
			
			return $q.reject(rejection);
		}
	};
});