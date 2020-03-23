angular.module(appTeclo).constant("constante", {
    //	 urlWs: "/dummyApp_v000r0",
    //	urlWs: "/sspcdmxsai_api_v600r4",
    //	urlWs: "/teclocdmxca_api_v220r1",
    //	urlWs: "/teclocdmxgc_api_v220r1",
    urlWs: "/sie_api_v100r1",
    //	urlWs: "/sicnapp_api_v100r1",
    appletClass: 'application.App',
});

angular.module(appTeclo)
    .factory('config', ['$http', '$location', 'constante', '$rootScope',
        function($http, $location, constante, $rootScope) {

            var protocol = $location.protocol() + "://";
            var host = location.host;
            var url = protocol + host + constante.urlWs;
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