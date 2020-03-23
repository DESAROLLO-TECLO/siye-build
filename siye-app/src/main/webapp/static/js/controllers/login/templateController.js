angular.module(appTeclo)
    .controller("templateController",
        function($rootScope, $scope, $location, $window, $document, $timeout,
            config, storageService, msjModalFactory, configAppService, ModalService, growl) {

            //	Bloquear advertencia de moments
            moment.suppressDeprecationWarnings = true;

            $scope.opacityBlink = 1;

            $rootScope.mostrarConfigResolucion = true; //Boolean: Bandera para mostrar la resolución estática o no dependiendo del $location.path()
            $rootScope.configuracion = true; //Boolean: Bandera para iterar entre la configuración temporal y la real
            $rootScope.breadCrumbs = {}; //Object: Objeto con las propiedades para mostrar los breadCrumbs en las pantallas
            $scope.fullScreenActive = false; //Boolean: Bandera para activar o desactivar el modo pantalla completa
            $scope.b_malla = false; //Boolean: Bandera para mostrar el efecto gaussiano al abrir el popover en configuración
            $scope.listeningTrick = false; //Boolean: Bandera, truco oculto :D

            $scope.mensajeModal = function(mensaje) {
                return msjModalFactory.getMensaje(mensaje, $rootScope.currentLanguage);
            }

            $scope.currentPath = function() {
                return $location.path();
            };

            yearCurrent = function() {
                var date = new Date();
                year = date.getFullYear();

                return year;
            }

            versionApp = function() {
                return version = "1.0.0";
            }

            // 	Pantalla completa
            $scope.goFullscreen = function() {
                $scope.fullScreenActive = !$scope.fullScreenActive;
            }

            $scope.yearCurrent = yearCurrent();
            $scope.versionApp = versionApp();

            $scope.textoTmp = [];

            desabilitarF5 = function(e) {

                $scope.textoTmp.push(e.key);

                angular.forEach($scope.textoTmp, function(letra, key) {
                    $scope.textIn = $scope.textIn != undefined ? $scope.textIn + letra : "" + letra;
                });

                if (/teclodark/.test($scope.textIn)) {

                    var temaActualTrick = "";
                    var bodyTrick = $('#bodyGlobal');
                    var bodyClasesTrick = (bodyTrick[0].className).split(" ");
                    var indicioTemaTrick = "tema";

                    angular.forEach(bodyClasesTrick, function(clase, key) {
                        if (clase.split("-")[0] == indicioTemaTrick) {
                            temaActualTrick = clase;
                            return false;
                        }
                    });

                    if ($('#bodyGlobal').hasClass('tema-tecloDark')) {
                        $('#bodyGlobal').removeClass('tema-tecloDark');
                        $('#bodyGlobal').addClass($scope.temaActual);
                    } else {
                        $('#bodyGlobal').removeClass(temaActualTrick);
                        $('#bodyGlobal').addClass('tema-tecloDark');
                    }

                    $scope.listeningTrick = true;
                    $scope.textoTmp = [];
                    $scope.textIn = "";
                }

                if (/viewplugins/.test($scope.textIn)) {

                    $scope.$apply(function() {
                        $location.path('/componentesWeb');
                    });

                    $scope.textoTmp = [];
                    $scope.textIn = "";
                }

                if (((e.which || e.keyCode) == 116 || (e.ctrlKey == true && (e.which || e.keyCode) == 82)) && $scope.fullScreenActive == true) {

                    growl.warning($scope.mensajeModal(
                        "La función de actualizar o recargar la página está deshabilitada en el modo pantalla completa"
                    ), { title: $scope.mensajeModal('¡ATENCIÓN!'), ttl: 6000 });

                    e.preventDefault();
                }
            }

            $document.on("keydown", desabilitarF5);

            //  CONFIGURACION APLICACION

            // 	ESTILOS BASE PARA MODO ESTÁTICO
            $rootScope.resolucionesApp = [
                { "idResolucion": 1, "cdResolucion": 'PQ', "txResolucionEs": "Tamaño pequeño", "txResolucionEn": "Small size", "nuPixelsBase": 10 },
                { "idResolucion": 2, "cdResolucion": 'NM', "txResolucionEs": "Tamaño normal", "txResolucionEn": "Normal size", "nuPixelsBase": 12 },
                { "idResolucion": 3, "cdResolucion": 'GR', "txResolucionEs": "Tamaño grande", "txResolucionEn": "Big size", "nuPixelsBase": 14 }
            ];

            //	OBTENER CONFIGURACIÓN EN CASO DE QUE NO EXISTE EL LOCALSTORAGE
            $rootScope.obtenerConfiguracion = function() {

                configAppService.configuracionAplicacion().success(function(data) {

                    //    		storageService.resetStorage();

                    let configuration = data,
                        ambienteBase = configuration.aplicacion.ambiente,
                        cdResolucion = configuration.resolucion.cdResolucion,
                        nuPixelesBase = configuration.resolucion.nuPixelesBase;

                    //    		localStorage.clear();

                    $rootScope.stg = storageService.initNameStorages(ambienteBase);
                    $scope.showAmbient($rootScope.stg);

                    storageService.setAmbiente(ambienteBase);
                    storageService.setConfiguration(configuration);
                    $rootScope.configuracionApp = storageService.getConfiguration();

                    storageService.setStatusAlert(false);
                    storageService.setLastRequest(new Date());

                    $rootScope.liveResolucion(cdResolucion, nuPixelesBase);
                    $rootScope.comprobarConfiguracion();

                });
            }

            $scope.refreshAll = function() {

                storageService.getCdAmbiente().success(function(data) {

                    let abtBs = data.ambiente + "_" + config.contextApp;

                    if (abtBs !== $rootScope.stg.abtBs) {

                        //	Se obtienen los valores del $rootScopes.stg actual
                        let stgTmp = angular.copy($rootScope.stg);

                        //	Se obtienen los valores de los $localStorages actuales
                        let sAbtBs = angular.copy(storageService.getAmbiente());
                        let sToken = angular.copy(storageService.getToken());
                        let sStatusAlert = angular.copy(storageService.getStatusAlert());
                        let sLastRequest = angular.copy(storageService.getLastRequest());
                        let sConfigApp = angular.copy(storageService.getConfiguration());
                        let sConfigAppTmp = angular.copy(storageService.getConfigurationTmp());

                        $rootScope.stg = storageService.initNameStorages(data.ambiente);

                        if (sToken !== undefined) {
                            storageService.setToken(sToken);
                        }

                        storageService.setConfiguration(sConfigApp);

                        if (sConfigAppTmp !== undefined) {
                            storageService.setConfigurationTmp(sConfigAppTmp);
                        }

                        storageService.deleteStorage(stgTmp.tokenName);
                        storageService.deleteStorage(stgTmp.statusAlertName);
                        storageService.deleteStorage(stgTmp.lastRequestName);
                        storageService.deleteStorage(stgTmp.configApp);
                        storageService.deleteStorage(stgTmp.configAppTmp);
                        storageService.deleteStorage(stgTmp.abtBs);

                        $rootScope.comprobarStorage();

                    } else {
                        return;
                    }

                }).error(function(data) {
                    var error = data;
                });

            }

            $scope.showAmbient = function(stg) {

                $scope.amb = {};

                var showBase = function(base) {
                    if (base == "O") {
                        $scope.amb.base = "Oracle";
                    } else if (base == "S") {
                        $scope.amb.base = "SQL Server";
                    } else {
                        $scope.amb.base = "Base no definida";
                    }
                }

                if (stg.ambiente == "D") {
                    $scope.amb.ambiente = "Desarrollo";
                    $scope.amb.color = "#FF9100";
                    $scope.amb.border = "#E65100";
                    $scope._viewIndicator = true;
                    showBase(stg.base);
                } else if (stg.ambiente == "Q") {
                    $scope.amb.ambiente = "QA";
                    $scope.amb.color = "#00B8D4";
                    $scope.amb.border = "#006064";
                    $scope._viewIndicator = true;
                    showBase(stg.base);
                } else if (stg.ambiente == "P") {
                    $scope.amb.ambiente = "Producción";
                    $scope.amb.color = "#00C853";
                    $scope.amb.border = "#1B5E20";
                    $scope._viewIndicator = false;
                    showBase(stg.base);
                } else {
                    $scope.amb.ambiente = "No hay ambiente definido";
                    $scope.amb.color = "#969597";
                    $scope.amb.border = "#55565a";
                    showBase(stg.base);
                }

            }

            //  COMPROBAR LA CONFIGURACIÓN PARA MOSTRAR LOS AJUSTES EN VIVO
            $rootScope.comprobarConfiguracion = function(menu, subMenu) {

                $rootScope.temaTMP = $rootScope.configuracionApp.tema;

                $timeout(function() {
                    if ($location.path() == '/index') {
                        $('.desplegar').removeClass('active-m');
                        $('.desplegarsub').removeClass('active-subm');
                        $('.treeview').removeClass('active');
                        $('.treeview-menu').removeClass('menu-open');
                        $('.treeview-menu').css('display', 'none');
                    }
                });

                if (menu != undefined || menu != null) {
                    $rootScope.breadCrumbs.icono = menu.menuIcono;
                    $rootScope.breadCrumbs.modulo = menu.menuNombre;
                    $rootScope.breadCrumbs.servicio = subMenu.subMenuNombre;
                }

                if (subMenu != undefined || subMenu != null) {
                    angular.forEach($('.desplegarsub'), function(element, key) {
                        if (subMenu.id == $(element).attr('id')) {
                            $('.desplegarsub').removeClass('active-subm');
                            $('#' + subMenu.id).addClass('active-subm');
                            return false;
                        } else {
                            $(element).removeClass('active-subm');
                        }
                    });
                }

                //    	Destruir el popover al salir de la pantalla de configuracion
                WebuiPopovers.hideAll();

                var temaVO = $rootScope.configuracionApp.tema;
                var cdResolucion = $rootScope.configuracionApp.resolucion.cdResolucion;
                var nuPixelesBase = $rootScope.configuracionApp.resolucion.nuPixelesBase;
                var menuFijo = $rootScope.configuracionApp.stMenuFijo;
                var menuDesplegable = $rootScope.configuracionApp.stMenuDesplegable;
                var headerFijo = $rootScope.configuracionApp.stHeaderFijo;

                $timeout(function() {
                    if ($scope.listeningTrick == false || $location.path() == '/configuracion')
                        $rootScope.liveTema(temaVO, 'init', 'template');
                });

                $rootScope.liveResolucion(cdResolucion, nuPixelesBase);
                $rootScope.liveHeaderFijo(headerFijo, menuFijo);
                $rootScope.liveMenuFijo(menuFijo, headerFijo);
                $rootScope.liveMenuDesplegado(menuDesplegable);

            }

            //  MOSTRAR EL CAMBIO DE TEMA DE LA APLICACIÓN EN VIVO
            $rootScope.liveTema = function(temaVO, event, exe) {

                $scope.temaActual = temaVO.cdTema;
                var body = $('#bodyGlobal');
                var bodyClases = (body[0].className).split(" ");
                var indicioTema = "tema";

                angular.forEach(bodyClases, function(clase, key) {
                    if (clase.split("-")[0] == indicioTema) {
                        body.removeClass(clase);
                        return false;
                    }
                });

                if (exe == 'config' && event == 'mouseover') {
                    $rootScope.temaTMP = $scope.configuracionTMP.tema;
                }

                if (exe == 'config' && event == 'mouseleave') {
                    temaVO = $rootScope.temaTMP;
                    $('#select2-temasApp-container').text(temaVO.nbTema);
                }

                if (exe == 'config' && event == 'click') {
                    $scope.configuracionTMP.tema = temaVO;
                    $rootScope.temaTMP = temaVO;
                    $('#select2-temasApp-container').text(temaVO.nbTema);
                    $timeout(function() {
                        $('.popoverTemas').trigger('click');
                    });
                    $scope.b_balla = false;
                }

                body.addClass($scope.temaActual);
            }

            //  MOSTRAR EL CAMBIO DE RESOLUCIÓN DE LA APLICACIÓN EN VIVO
            $rootScope.liveResolucion = function(cdResolucion, nuPixelesBase) {

                var html = $('html');
                var fsDefault = $('html').css('font-size');
                var nuPixel = nuPixelesBase;
                var fsApp = nuPixel + 'px';

                if (nuPixel == null || nuPixel == undefined) {

                    for (r = 0; r < $rootScope.resolucionesApp.length; r++) {

                        if ($rootScope.resolucionesApp[r].cdResolucion == cdResolucion) {

                            nuPixel = $rootScope.resolucionesApp[r].nuPixelsBase;
                            fsApp = nuPixel + 'px';
                            break;

                        }
                    }
                }

                switch (cdResolucion) {

                    case 'GR':
                        html.css('font-size', fsApp);
                        if ($.AdminLTE.layout != undefined) {
                            $.AdminLTE.layout.fix();
                        }
                        break;

                    case 'NM':
                        html.css('font-size', fsApp);
                        if ($.AdminLTE.layout != undefined) {
                            $.AdminLTE.layout.fix();
                        }
                        break;

                    case 'PQ':
                        html.css('font-size', fsApp);
                        if ($.AdminLTE.layout != undefined) {
                            $.AdminLTE.layout.fix();
                        }
                        break;

                        //Se mete un default, en caso que no exista ningún registro en el catálogo de resoluciones
                    default:
                        html.css('font-size', fsDefault);
                        if ($.AdminLTE.layout != undefined) {
                            $.AdminLTE.layout.fix();
                        }
                }
            }

            //  MOSTRAR EL CAMBIO DE LA CONFIGURACIÓN DEL HEADER FIJO EN VIVO
            $rootScope.liveHeaderFijo = function(headerFijo, menuFijo) {

                var mainHeader = $('.main-header');
                var contentWrapper = $('.content-wrapper');

                if (headerFijo != 1) {

                    mainHeader.removeClass("fixed");
                    contentWrapper.removeClass("headerFijo");
                    contentWrapper.addClass("header-noFijo");

                    if (menuFijo == 1) {

                        $('.main-sidebar').css("padding-top", "0");
                        $('.sidebar-menu').addClass("sidebar-menuHeaderFijo");

                    }

                } else {

                    mainHeader.addClass("fixed");
                    contentWrapper.removeClass("header-noFijo");
                    contentWrapper.addClass("headerFijo");

                    if (menuFijo == 1) {

                        $('.main-sidebar').css("padding-top", "3.571rem");
                        $('.sidebar-menu').removeClass("sidebar-menuHeaderFijo");

                    }

                }

            }

            //  MOSTRAR EL CAMBIO DE LA CONFIGURACIÓN DEL MENU FIJO EN VIVO
            $rootScope.liveMenuFijo = function(menuFijo, headerFijo) {

                var mainSidebar = $('.main-sidebar'); //Objeto: Se guarda el objeto mediante el id del elemento
                var titleMenuNavegacion = $('#titleMenuNavegacion'); //Objeto: Se guarda el objeto mediante el id del elemento
                var contEmpty = $('.cont-empty'); //Objeto: Se guarda el objeto mediante la clase del elemento
                var sidebarMenu = $('.sidebar-menu'); //Objeto: Se guarda el objeto mediante la clase del elemento

                if (menuFijo != 1) {

                    //	Menu
                    mainSidebar.removeClass('menufixed');
                    //	Titulo Menu de Navegación
                    titleMenuNavegacion.removeClass("title-menu");
                    titleMenuNavegacion.addClass("title-menu-noFijo");
                    //	Contenedor invisible que crea una separación
                    contEmpty.css("display", "none");
                    //	Quitar scroll al menu
                    sidebarMenu.addClass("sidebar-menu-noFijo");

                    if (headerFijo != 1) {

                        $('.main-sidebar').css("padding-top", "3.571rem");
                        $('.sidebar-menu').removeClass("sidebar-menuHeaderFijo");

                    }

                } else {

                    //	Menu
                    mainSidebar.addClass('menufixed');
                    //	Titulo Menu de Navegación
                    titleMenuNavegacion.removeClass("title-menu-noFijo");
                    titleMenuNavegacion.addClass("title-menu");
                    //	Contenedor invisible que crea una separación
                    contEmpty.css("display", "block");
                    //	Quitar scroll al menu
                    sidebarMenu.removeClass("sidebar-menu-noFijo");

                    if (headerFijo != 1) {

                        $('.main-sidebar').css("padding-top", "0");
                        $('.sidebar-menu').addClass("sidebar-menuHeaderFijo");

                    }

                }

                if ($.AdminLTE.layout != undefined) {
                    $.AdminLTE.layout.fix();
                }
            }

            $rootScope.changeStatusMenu = function() {

                if ($rootScope.configuracionTMP) {

                    var menDesplegableCambio = $rootScope.configuracionTMP.stMenuDesplegable;

                    if (menDesplegableCambio != undefined || menDesplegableCambio != null) {

                        if ($rootScope.configuracionTMP.stMenuDesplegable == 1) {
                            $rootScope.configuracionTMP.stMenuDesplegable = 0;
                        } else {
                            $rootScope.configuracionTMP.stMenuDesplegable = 1;
                        }
                    }
                }

            }

            //  MOSTRAR EL CAMBIO DE LA CONFIGURACIÓN DEL MENU DESPLEGADO EN VIVO
            $rootScope.liveMenuDesplegado = function(menuDesplegable) {

                var bodyGlobal = $('#bodyGlobal'); //Objeto: Se guarda el objeto mediante el id del elemento
                var menu = $('#menu'); //Objeto: Se guarda el objeto mediante el id del elemento
                var sidebarCollapse = "sidebar-collapse"; //String: Se guarda el nombre de la clase
                var mostrar = "mostrar"; //String: Se guarda el nombre de la clase

                if (menuDesplegable != 1) { //Inactivo -> 0 Replegado (solo iconos)

                    bodyGlobal.addClass(sidebarCollapse);
                    menu.addClass(mostrar);

                } else { //Activo -> 1 Desplegado (iconos y menuTexto)

                    bodyGlobal.removeClass(sidebarCollapse);
                    menu.removeClass(mostrar);

                }

                $timeout($scope.cancelarCambiosConfig, 500);
            }

            //  CANCELAR LA CONFIGURACIÓN AL SALIRSE DEL MÓDULO
            $scope.cancelarCambiosConfig = function() {

                if ($location.path() !== '/configuracion') {

                    $rootScope.mostrarConfigResolucion = true;
                    $rootScope.configuracion = true;

                    //Borrar el localStorage temporal de la configuración
                    storageService.deleteStorage($rootScope.stg.configAppTmp);
                }
            }

            $rootScope.comprobarStorage = function() {

                $scope.abtBs = storageService.getAmbiente();

                let _config = false;

                if ($scope.abtBs !== undefined) {
                    _config = true;
                }

                //  DESPUÉS DE LEER TODOS LOS RECURSOS, VALIDAR SI EXISTE EL LOCALSTORAGE DE LA CONFIGURACIÓN
                if (_config) {

                    let ambientBase = $scope.abtBs.split('_')[0] + "_" + $scope.abtBs.split("_")[1];

                    $rootScope.stg = storageService.initNameStorages(ambientBase);
                    $scope.showAmbient($rootScope.stg);

                    let configuration = storageService.getConfiguration();

                    if (configuration !== undefined) {
                        let ambiente = configuration.aplicacion.ambiente,
                            cdResolucion = configuration.resolucion.cdResolucion,
                            nuPixelesBase = configuration.resolucion.nuPixelesBase;

                        storageService.setConfiguration(configuration);
                        $rootScope.configuracionApp = storageService.getConfiguration();

                        storageService.setStatusAlert(false);
                        storageService.setLastRequest(new Date());
                        storageService.setAmbiente(ambientBase);

                        $timeout($rootScope.comprobarConfiguracion, 300);
                    } else {
                        $rootScope.obtenerConfiguracion();
                    }


                } else {
                    $rootScope.obtenerConfiguracion();
                }
            }

            $rootScope.comprobarStorage();

        });