angular.module(appTeclo)
    .service('storageService',
        function($rootScope, $localStorage, $http, config) {

            var $rs = $rootScope;
            var cdApp = "App";
            var app = "/aplicacion";
            var urlConfig = config.baseUrl + app;
            var contexto = config.contextApp;
            var secretKey = 'parameter';

            this.getEnCrypto = function(encrypto) {
                return CryptoJS.AES.encrypt(angular.toJson(encrypto), secretKey).toString();
            }
            this.getDesCrypto = function(desEncryptedDate) {
                return angular.fromJson(CryptoJS.AES.decrypt(desEncryptedDate, secretKey).toString(CryptoJS.enc.Utf8));
            }
            this.getParameterValApp = function(cdParametro) {
                if ($rs.stg !== undefined) {
                    let parametro = this.getParamsValApp();
                    if (parametro) {
                        let valor = null;
                        $.each(parametro, function(i, val) {
                            if (val.cdParametro == cdParametro) {
                                valor = val.nbValor;
                            }
                        });
                        return valor;
                    }
                }
                return;
            };
            this.getParamsValApp = function() {
                if ($rs.stg !== undefined) {
                    var encryptedData = $localStorage[$rs.stg.paramsValApp];
                    if (encryptedData)
                        return this.getDesCrypto(encryptedData);
                }
                return;
            };
            this.setParamsValApp = function(paramsValApp) {
                var encryptedData = this.getEnCrypto(paramsValApp);
                $localStorage[$rs.stg.paramsValApp] = encryptedData;
            };
            this.getUsrAplication = function() {
                if ($rs.stg !== undefined) {
                    var encryptedData = $localStorage[$rs.stg.usrAplication];
                    if (encryptedData)
                        return this.getDesCrypto(encryptedData);
                }
                return;
            };
            this.setUsrAplication = function(usrAplication) {
                var encryptedData = this.getEnCrypto(usrAplication);
                $localStorage[$rs.stg.usrAplication] = encryptedData;
            };
            this.getToken = function() {
                if ($rs.stg !== undefined) {
                    return $localStorage[$rs.stg.tokenName];
                } else {
                    return;
                }
            };

            this.setToken = function(token) {
                $localStorage[$rs.stg.tokenName] = token;
            };

            this.getStatusAlert = function() {
                if ($rs.stg !== undefined) {
                    var encryptedData = $localStorage[$rs.stg.statusAlertName];
                    if (encryptedData)
                        return this.getDesCrypto(encryptedData);
                }
                return;
            }

            this.setStatusAlert = function(flag) {
                var encryptedData = this.getEnCrypto(flag);
                $localStorage[$rs.stg.statusAlertName] = encryptedData;
            }

            this.getLastRequest = function() {
                if ($rs.stg !== undefined) {
                    var encryptedData = $localStorage[$rs.stg.lastRequestName];
                    if (encryptedData)
                        return this.getDesCrypto(encryptedData);
                }
                return;
            }

            this.setLastRequest = function(date) {
                var encryptedData = this.getEnCrypto(date.toString());
                $localStorage[$rs.stg.lastRequestName] = encryptedData;
            }

            this.getConfiguration = function() {
                if ($rs.stg !== undefined) {
                    var encryptedData = $localStorage[$rs.stg.configApp];
                    if (encryptedData)
                        return this.getDesCrypto(encryptedData);
                }
                return
            }

            this.setConfiguration = function(data) {
                var encryptedData = this.getEnCrypto(data);
                $localStorage[$rs.stg.configApp] = encryptedData;
            }

            this.getConfigurationTmp = function() {
                if ($rs.stg !== undefined) {
                    return $localStorage[$rs.stg.configAppTmp];
                } else {
                    return;
                }
            }

            this.setConfigurationTmp = function(data) {
                $localStorage[$rs.stg.configAppTmp] = data;
            }

            this.getAmbiente = function() {

                let ambiente = "",
                    D_O = $localStorage["D_O_" + contexto] !== undefined ? 'D_O' : undefined,
                    Q_O = $localStorage["Q_O_" + contexto] !== undefined ? 'Q_O' : undefined,
                    P_O = $localStorage["P_O_" + contexto] !== undefined ? 'P_O' : undefined,
                    D_S = $localStorage["D_S_" + contexto] !== undefined ? 'D_S' : undefined,
                    Q_S = $localStorage["Q_S_" + contexto] !== undefined ? 'Q_S' : undefined,
                    P_S = $localStorage["P_S_" + contexto] !== undefined ? 'P_S' : undefined;

                if (D_O !== undefined) {
                    ambiente = D_O;
                } else if (Q_O !== undefined) {
                    ambiente = Q_O;
                } else if (P_O !== undefined) {
                    ambiente = P_O;
                } else if (D_S !== undefined) {
                    ambiente = D_S;
                } else if (Q_S !== undefined) {
                    ambiente = Q_S;
                } else if (P_S !== undefined) {
                    ambiente = P_S;
                } else {
                    if ($rs.stg !== undefined) {
                        $localStorage[$rs.stg.abtBs];
                        ambiente = $rs.stg.abtBs;
                    } else {
                        ambiente = undefined;
                    }
                }

                return ambiente;
            }

            this.setAmbiente = function(abtBs) {
                $localStorage[$rs.stg.abtBs] = abtBs;
            }

            this.getCdAmbiente = function() {
                return $http.get(urlConfig + "/ambiente");
            }

            this.initNameStorages = function(abtBs) {

                let nameStorages = {};
                let ambiente = abtBs.split('_')[0];
                let base = abtBs.split("_")[1];
                //Revisar esta parte-
                nameStorages.abtBs = abtBs + "_" + contexto;
                nameStorages.ambiente = ambiente;
                nameStorages.base = base;
                nameStorages.paramsValApp = ambiente + "_ParamsValApp" + cdApp + "_" + contexto;
                nameStorages.usrAplication = ambiente + "_UsrAplication" + cdApp + "_" + contexto;
                nameStorages.tokenName = ambiente + "_Token" + cdApp + "_" + contexto;
                nameStorages.statusAlertName = ambiente + "_StatusAlert" + cdApp + "_" + contexto;
                nameStorages.lastRequestName = ambiente + "_LastRequest" + cdApp + "_" + contexto;
                nameStorages.configApp = ambiente + "_Config" + cdApp + "_" + contexto;
                nameStorages.configAppTmp = ambiente + "_Config" + cdApp + "Tmp" + "_" + contexto;

                return nameStorages;
            }

            this.deleteStorage = function(storage) {
                delete $localStorage[storage];
            }

            this.resetStorage = function() {
                $localStorage.$reset();
            }

            this.getWishList = function() {
                if ($rs.stg !== undefined) {
                    return $localStorage[$rs.stg.wishList];
                } else {
                    return;
                }
            };

            this.setWishList = function(arrayWish) {
                $localStorage[$rs.stg.wishList] = arrayWish;
            };
        });