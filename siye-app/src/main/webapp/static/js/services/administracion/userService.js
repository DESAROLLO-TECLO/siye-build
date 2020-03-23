angular.module(appTeclo).service("userService",
function($http, config) {

    const APIUSER = `${config.baseUrl}/usuarios`;
    const APIPROFILE = `${config.baseUrl}/perfiles`;
    
    /**
     * 
     */
    this.getTypeSearch = function() {
        return $http.get(`${APIUSER}/tipoBusqueda`);
    }

    this.getTypeSearchForAll = function() {
        return $http.get(`${APIUSER}/searchForAll`);
    }

    this.getProfiles = function() {
        return $http.get(APIPROFILE);
    }

    this.getUsersByType = function(data) {

        let params = new Object({
            params: data
        });

        return $http.get(APIUSER, params);
    }

    this.getUsersByProfile = function(data) {
        let params = new Object({
            params: data
        });

        return $http.get(`${APIUSER}/perfil`, params);
    }

    this.getMenusByProfile = function(cdprofile) {
        let request = new Object({
            params: {
                "profile": cdprofile
            }
        });

        return $http.get(`${APIPROFILE}/menusbyperfil`, request);
    }

    this.updateStatusUser = function(user) {
        return $http.put(`${APIUSER}/status`, user);
    }

    this.restorePasswordbyUser = function(userVO) {
        return $http.put(`${APIUSER}/password/restablecer`, userVO);
    }

    this.saveOrUpdateUser = function(usuarioVO, action) {

        let request = undefined;
        
        switch(action) {
            case "new":
                request = $http.post(`${APIUSER}/saveOrUpdateUser`, usuarioVO);
                break;
            case "edit":
                request = $http.put(`${APIUSER}/saveOrUpdateUser`, usuarioVO);
                break;
        }

        return request;
    }

    this.toggleEncryption = function(obj) {

        let params = new Object({
            params: obj
        });

        return $http.get(`${APIUSER}/toggleEncryption`, params);
    }

});
