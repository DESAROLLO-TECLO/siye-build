angular.module(appTeclo).service('consultaServicioService', function($http, config) {

    this.buscarTipoBusqueda = function() {
        return $http.get(config.baseUrl + "/");
    }

    this.buscarOrdenServicio = function(params) {
        return $http.get(config.baseUrl + "/ordenServicio/consultaOrden", {
            params: {
                "cdTipoBusqueda": params.tipoBusqueda.cdTipoBusqueda,
                "valor": params.valor == null ? " " : params.valor
            }
        });
    };

    this.catalogosOrdenServicio = function() {
        return $http.get(config.baseUrl + '/catalogo/getCatOrdenProceso');
    };

    this.actualizarServicio = function(data) {
        return $http.put(config.baseUrl + '/ordenServicio/updateOrden', data);
    };

    this.obtenerOrden = function(id) {
        return $http.get(config.baseUrl + '/ordenServicio/getOrdenServicio', {
            params: {
                idOrdenservicio: id
            }
        });
    };
});