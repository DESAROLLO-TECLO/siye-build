angular.module(appTeclo)
    .factory("requestInterceptor",
        function($rootScope, $localStorage, $q, $location, $injector) {

            return {
                request: function(config) {
                    config.headers = config.headers || {};

                    if ($rootScope.stg) {
                        config.headers['X-Auth-Token'] = $localStorage[$rootScope.stg.tokenName];
                        if (config.params != undefined && config.params['validacion'] != undefined) {
                            delete config.headers['X-Auth-Token'];
                            delete config.params['validacion'];
                        }
                    }

                    if ($rootScope.stg !== undefined) {
                        if (config.url != "views/templatemodal/templateModalToken.html") {
                            // $localStorage[$rootScope.stg.lastRequestName] = new Date();
                            var storageService = $injector.get("storageService");
                            storageService.setLastRequest(new Date());
                        }
                    }

                    return config;
                },

                responseError: function(response) {
                    if (response.status === 401 || response.status === 403) {
                        //$location.path('/login');
                    }
                    return $q.reject(response);
                }
            }
        });