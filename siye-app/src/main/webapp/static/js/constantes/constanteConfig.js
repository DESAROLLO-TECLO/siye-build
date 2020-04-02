angular.module(appTeclo).constant("constante", {
    urlWs: "/siye_api_v100r1",
    //	urlWs: "/sicnapp_api_v100r1",
    appletClass: 'application.App',
});

angular.module(appTeclo)
    .factory('config', ['$http', '$location', 'constante', '$rootScope',
        function($http, $location, constante, $rootScope) {

            var protocol = $location.protocol() + "://";
            var host = location.host;
            //var url = protocol + host + constante.urlWs;
            var url = "http://192.168.0.13/siye_api_v100r1";
            var absUrl = $location.absUrl();

            let contextApp = absUrl.split("/")[3];

            $rootScope.applet_route = url;
            $rootScope.codebase = constante.appletClass;

            return {
                baseUrl: url,
                absUrl: absUrl,
                contextApp: contextApp
            }
        }
    ]);