angular.module(appTeclo).service("jwtService", function($window) {

	this.decodeToken = function(token) {
		if(token !== undefined) {
			var parts = token.split('.');
			
			if (parts.length !== 3) {
				throw new Error('JWT must have 3 parts');
			}
			
			var decoded = this.urlBase64Decode(parts[1]);
			if (!decoded) {
				throw new Error('Cannot decode the token');
			}
			
			return angular.fromJson(decoded);
		} else {
			return;
		}
	};

	this.getNombreUsuario = function(token) {
		var decoded = this.decodeToken(token);

		if (typeof decoded.name === "undefined") {
			return null;
		}

		return decoded.name;
	};

	this.getPerfilUsuario = function(token) {
		var decoded = this.decodeToken(token);

		if (typeof decoded.perfil === "undefined") {
			return null;
		}
		return decoded.perfil;
	};

	this.getCaja = function(token) {
		var decoded = this.decodeToken(token);

		if (typeof decoded.caja === "undefined") {
			return null;
		}
		return decoded.caja;
	};

	this.getDeposito = function(token) {
		var decoded = this.decodeToken(token);

		if (typeof decoded.deposito === "undefined") {
			return null;
		}
		return decoded.deposito;
	};

	this.getPlacaUsuario = function(token) {
		var decoded = this.decodeToken(token);
		if (typeof decoded.sub === "undefined") {
			return null;
		}

		return decoded.sub;
	};

	this.getInactivity = function(token) {
		if(token !== undefined) {			
			var decoded = this.decodeToken(token);
			if (typeof decoded.inactivity === "undefined") {
				return null;
			}
			
			return decoded.inactivity;
		} else {
			return;
		}
	};

	this.getTokenExpirationDate = function(token) {
		if(token !== undefined) {			
			var decoded = this.decodeToken(token);
			
			if (typeof decoded.exp === "undefined") {
				return null;
			}
			
			var d = new Date(0); // The 0 here is the key, which sets the date to the epoch
			d.setUTCSeconds(decoded.exp);
			
			return d;
		} else {
			return;
		}
	};

	this.isTokenExpired = function(token, offsetSeconds) {
		if(token !== undefined) {			
			var d = this.getTokenExpirationDate(token);
			offsetSeconds = offsetSeconds || 0;
			if (d === null) {
				return false;
			}
			
			// Token expired?
			return !(d.valueOf() > (new Date().valueOf() + (offsetSeconds * 1000)));
		} else {
			return;
		}
	};

	this.urlBase64Decode = function(str) {
		var output = str.replace(/-/g, '+').replace(/_/g, '/');
		switch (output.length % 4) {
		case 0: {
			break;
		}
		case 2: {
			output += '==';
			break;
		}
		case 3: {
			output += '=';
			break;
		}
		default: {
			throw 'Illegal base64url string!';
		}
		}
		return $window.decodeURIComponent(escape($window.atob(output)));
	};

	this.hasAccess = function(page, token) {

		var pageAccess = "/" + page.split("/")[1];
		if (pageAccess === "/index" || pageAccess === "/" || pageAccess === "/error" || pageAccess === "/undefined") {
			return true;
		}
		var decoded = this.decodeToken(token);

		//Agregar página de componentes
		//		decoded.pages = decoded.pages+" /componentesWeb";

		if (typeof decoded.pages === "undefined") {
			return false;
		}
		var pages = decoded.pages.split(" ");
		for (var i = 0; i < pages.length; i++) {
			if (pages[i] === pageAccess) {
				return true;
			}
		}
		return false;
	};
});