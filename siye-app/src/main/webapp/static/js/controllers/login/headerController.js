angular.module(appTeclo)
    .controller("headerController",
        function($rootScope, $scope, $localStorage, $location, $window, $document, $translate, $timeout,
            loginService, storageService, logOutService, jwtService, menuDinamicoService, growl) {

            $scope.listAccess = [];
            $scope.filterWish = '';

            $scope.b_detalleUsuario = false;
            $scope.b_configUsuario = false;
            $scope._wishFunctions = false;
            $scope._wishList = false;
            $scope.changePwd = false;

            $scope.logout = function() {
                loginService.logout();
                logOutService.StopTimer();
                $location.path('/login');
            };

            nombreUsuario = function() {
                if (storageService.getToken()) {
                    return jwtService.getNombreUsuario(storageService.getToken());
                }

                return null;
            };

            perfilUsuario = function() {
                if (storageService.getToken()) {
                    return jwtService.getPerfilUsuario(storageService.getToken());
                }

                return null;
            };

            $rootScope.currentDate = function(lang) {

                var date = new Date();

                $scope.fechaHoy = "";

                switch (lang) {
                    case "es_ES":
                        $scope.fechaActual = moment(date).locale('es').format('dddd DD [de]  MMMM [de] YYYY');
                        break;
                    case "en_US":
                        $scope.fechaActual = moment(date).locale('en').format('dddd DD [of]  MMMM [of] YYYY');
                        break;
                    default:

                }
            }

            //	MENUS DETALLE DE USUARIO | CAMBIO TAMAÑOS | CERRAR SESIÓN
            $('.user-menu, .menuopc').click(function(e) {
                e.stopPropagation();
            });

            $document.on('click', function(e) {

                $rootScope.$evalAsync(function() {
                    $scope.b_detalleUsuario = false;
                    $scope.b_configUsuario = false;
                });

                let wishList = $('#wishList');
                let wishIcon = $('#wishIcon');
                let wishFilter = $('#wishFilter');

                if (!wishList.is(e.target) && !wishIcon.is(e.target) && !wishFilter.is(e.target) &&
                    wishFilter.has(e.target).length === 0 && wishList.has(e.target).length === 0 &&
                    wishIcon.has(e.target).length === 0) {

                    $rootScope.$evalAsync(function() {
                        $scope._wishList = false;
                    });

                }

            });

            $scope.mostrarDetalleUsuario = function(b) {
                if (b == true)
                    $scope.b_detalleUsuario = false;
                else
                    $scope.b_detalleUsuario = true;

                $scope.b_configUsuario = false;
            }

            $scope.mostrarConfigUsuario = function(b) {
                // DEBEMOS COMPROBAR SI EL MENÚ PARA CONFIGURAR CLAVE ESTÁ DENTRO DEL TOKEN
                var m = $rootScope.menuDinamico;
                if (m != undefined && m.length > 0) {
                    for (let i = 0; i < m.length; i++) {
                        for (let j = 0; j < m[i].subMenus.length; j++) {
                            if (m[i].subMenus[j].menuUri != undefined && m[i].subMenus[j].menuUri == '/administracionModificaClave') {
                                $scope.changePwd = true;
                                break;
                            }
                        }
                    }
                }
                if (b == true)
                    $scope.b_configUsuario = false;
                else
                    $scope.b_configUsuario = true;
                $scope.b_detalleUsuario = false;

            }

            // BOTON IR ARRIBA
            $scope.btnUp = function() {

                var window = angular.element($window);
                var scrollUp = $('.scrollup');
                var page = $('html, body');

                window.bind("scroll", function() {

                    if ($(this).scrollTop() > 300) {

                        scrollUp.fadeIn();

                    } else {

                        scrollUp.fadeOut();

                    }
                });

                scrollUp.click(function() {

                    page.animate({ scrollTop: 0 }, 600);

                    return false;
                });
            }

            $scope.showWishList = function() {
                $scope._wishList = !$scope._wishList;
                scrollWish();
            }

            $rootScope.validWish = function(menu) {
                if (storageService.getWishList()) {
                    $scope.listAccess = storageService.getWishList();
                    $scope.listAccess.map(access => {
                        menu.map(item => {
                            item.subMenus.map(subItem => {
                                if (subItem.id === access.idAccess) {
                                    subItem.active = true;
                                }
                            });
                        });
                    });
                } else {
                    storageService.setWishList($scope.listAccess);
                }
            };

            $scope.toggleWishItem = function(item, subItem) {

                const objectAccess = {
                    idAccess: subItem.id,
                    nbAccess: item.menuTexto + ' - ' + subItem.menuTexto,
                    iconAccess: item.menuIcono,
                    uriAccess: subItem.menuUri,
                }

                if ($scope.listAccess.length > 0) {
                    const id = subItem.id;
                    let objectTmp = { isShow: false, key: 0 };

                    $scope.listAccess.some(function(access, key) {
                        if (id === access.idAccess) {
                            objectTmp.isShow = true;
                            objectTmp.key = key;
                            return objectTmp.isShow;
                        } else {
                            objectTmp.isShow = false;
                        }
                    });

                    if (!objectTmp.isShow) {
                        if ($scope.listAccess.length >= 10) {
                            growl.info('', { title: 'Solo se permite un máximo de 10 favoritos' });
                            return;
                        } else {
                            $scope.listAccess.push(objectAccess);
                        }
                    } else {
                        $scope.listAccess.splice(objectTmp.key, 1);
                    }

                } else {
                    $scope.listAccess.push(objectAccess);
                }

                subItem.active = !subItem.active;
                storageService.setWishList($scope.listAccess);
            }

            $scope.deselectWish = function(items) {
                items.map(item => {
                    item.subMenus.map(subItem => {
                        subItem.active = false;
                    });
                });
                $scope.listAccess = [];
                storageService.setWishList($scope.listAccess);
            }

            scrollWish = function() {
                $(function() {
                    $('#wishList').slimScroll({
                        height: '100%',
                        color: '#55565a',
                        opacity: .5,
                        size: "5px",
                        alwaysVisible: true
                    });
                });
            }

            pluginsController = function() {
                // MENU HAMBUERGUESA
                $('.main-nav').on('click', function() {
                    $('#menu').toggleClass('mostrar');
                });

                if ($(window).width() < 768) {
                    $('body').on('click', '.treeview-menu', function() {
                        $('#menu').removeClass('mostrar');
                    });
                } else {
                    $('body').on('click', '.menuItem', function() {
                        $('#menu').removeClass('mostrar');
                    });
                }
            }

            initController = function() {
                $scope.pluginsController = pluginsController();
                $scope.btnUp();
                $timeout(function() {
                    $rootScope.currentDate($rootScope.currentLanguage);
                });
                $scope.perfilUsuario = perfilUsuario();
                $scope.nombreUsuario = nombreUsuario();
            }

            initController();
        });