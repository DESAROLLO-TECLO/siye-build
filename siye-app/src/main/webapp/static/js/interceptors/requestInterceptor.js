angular.module(appTeclo)
.factory("requestInterceptor",
function ($rootScope,$localStorage,$q,$location) {
	
	return {
		request : function(config) {
			config.headers = config.headers || {};

			if ($rootScope.stg) {
				config.headers['X-Auth-Token'] = $localStorage[$rootScope.stg.tokenName];
			}
			
			if($rootScope.stg !== undefined) {
				if(config.url != "views/templatemodal/templateModalToken.html") {
					$localStorage[$rootScope.stg.lastRequestName] = new Date();
				}
			}
			
			return config;
		},

		responseError : function(response) {
			if (response.status === 401 || response.status === 403) {
				//$location.path('/login');
			}
			return $q.reject(response);
		}
	}
});