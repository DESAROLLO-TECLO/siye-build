angular.module(appTeclo)
.service('storageService',
function($rootScope,$localStorage,$http,config) {
	
	var $rs = $rootScope;
	var cdApp = "App";
	var app = "/aplicacion";
	var urlConfig = config.baseUrl+app;
	var contexto = config.contextApp;
	
	this.getToken = function () {
		if($rs.stg !== undefined) {
			return $localStorage[$rs.stg.tokenName];
		} else {
			return;
		}
	};
	
	this.setToken = function(token) {
		$localStorage[$rs.stg.tokenName] = token;
	};
	
	this.getStatusAlert = function() {
		if($rs.stg !== undefined) {
			return $localStorage[$rs.stg.statusAlertName];
		} else {
			return;
		}
	}
	
	this.setStatusAlert = function(flag) {
		$localStorage[$rs.stg.statusAlertName] = flag;
	}
	
	this.getLastRequest = function() {
		if($rs.stg !== undefined) {
			return $localStorage[$rs.stg.lastRequestName];
		} else {
			return;
		}
	}
	
	this.setLastRequest = function(date) {
		$localStorage[$rs.stg.lastRequestName] = date;
	}
	
	this.getConfiguration = function() {
		if($rs.stg !== undefined) {
			return $localStorage[$rs.stg.configApp];
		} else {
			return
		}
	}
	
	this.setConfiguration = function(data) {
		$localStorage[$rs.stg.configApp] = data;
	}
	
	this.getConfigurationTmp = function() {
		if($rs.stg !== undefined) {
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
			D_O = $localStorage["D_O_"+contexto] !== undefined ? 'D_O' : undefined,
			Q_O = $localStorage["Q_O_"+contexto] !== undefined ? 'Q_O' : undefined,
			P_O = $localStorage["P_O_"+contexto] !== undefined ? 'P_O' : undefined,
			D_S = $localStorage["D_S_"+contexto] !== undefined ? 'D_S' : undefined,
			Q_S = $localStorage["Q_S_"+contexto] !== undefined ? 'Q_S' : undefined,
			P_S = $localStorage["P_S_"+contexto] !== undefined ? 'P_S' : undefined;
		
		if(D_O !== undefined) {
			ambiente = D_O;
		} else if (Q_O !== undefined) {
			ambiente = Q_O;
		} else if (P_O !== undefined) {
			ambiente = P_O;
		} else if(D_S !== undefined) {
			ambiente = D_S;
		} else if (Q_S !== undefined) {
			ambiente = Q_S;
		} else if (P_S !== undefined) {
			ambiente = P_S;
		} else {
			if($rs.stg !== undefined) {
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
		nameStorages.abtBs		 	 = abtBs+"_"+contexto;
		nameStorages.ambiente 		 = ambiente;
		nameStorages.base 			 = base;
		nameStorages.tokenName 		 = ambiente+"_Token"+cdApp+"_"+contexto;
		nameStorages.statusAlertName = ambiente+"_StatusAlert"+cdApp+"_"+contexto;
		nameStorages.lastRequestName = ambiente+"_LastRequest"+cdApp+"_"+contexto;
		nameStorages.configApp 		 = ambiente+"_Config"+cdApp+"_"+contexto;
		nameStorages.configAppTmp 	 = ambiente+"_Config"+cdApp+"Tmp"+"_"+contexto;
		nameStorages.wishList		 = ambiente+"_wishList"+cdApp+"_"+contexto;
		
		return nameStorages;
	}
	
	this.getWishList = function () {
		if($rs.stg !== undefined) {
			return $localStorage[$rs.stg.wishList];
		} else {
			return;
		}
	};
	
	this.setWishList = function(arrayWish) {
		$localStorage[$rs.stg.wishList] = arrayWish;
	};
	
	this.deleteStorage = function(storage) {
		delete $localStorage[storage];
	}
	
	this.resetStorage = function() {
		$localStorage.$reset();
	}
});