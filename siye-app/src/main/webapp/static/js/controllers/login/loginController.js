angular.module(appTeclo)
    .controller("loginController",
        function($rootScope, $scope, $location, $window, $timeout,
            loginService, storageService, logOutService, messageFactory) {

            var btnLogin = $('#login-btn');

            $scope.inputType = 'password';
            $scope.b_hidePassword = true;

            $scope.showClave = function() {
                $scope.$evalAsync(function() {
                    $scope.inputType = 'text';
                    $scope.b_hidePassword = false;
                });
            }

            $scope.hideClave = function() {
                $scope.$evalAsync(function() {
                    $scope.inputType = 'password';
                    $scope.b_hidePassword = true;
                });
            }

            $scope.login = function() {

                loginService.login($scope.user).success(function(data) {

                    btnLogin.attr('disabled', 'true');
                    var usrAplication = {
                            "idPerfil": data.idPerfil
                        }
                        //storageService.setParamsValApp(data.restrictionCollection);
                        //storageService.setUsrAplication(usrAplication);
                    storageService.setToken(data.token);
                    logOutService.StartTimer();
                    $location.path('/index');
                    $timeout($rootScope.comprobarConfiguracion, 500);

                }).error(function(data) {

                    $scope.error = data;

                    if (data.status == undefined || data == null) {
                        $scope.error = {

                            message: messageFactory.getErrorMessage(420),

                        }
                    }
                });
            };
        });