angular.module(appTeclo).service('consultaServicioService', function($http, config, $timeout) {

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

    this.buscaIncidencia = function(cdIncidencia) {
        return $http.get(config.baseUrl + '/incidencia/getIncidenciaBycdIncidencia', {
            params: {
                cdIncidencia: cdIncidencia
            }
        });
    };

    this.buscaServicioByCd = function(cdOrdenServicio) {
        return $http.get(config.baseUrl + '/ordenServicio/getOrdenServicioCDOS', {
            params: {
                cdOrdenServicio: cdOrdenServicio
            }
        });
    };
    this.descargarReporteExcel = function(rVO) {
        return $http({
            method: 'POST',
            url: config.baseUrl + "/reporte/reporteIncidencia",
            data: rVO,
            dataType: "json",
            header: {
                "Content-type": "application/json",
                "Accept": "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            },
            responseType: 'arraybuffer'
        });
    };

    /*UTILITIES*/
    this.downloadfile = function(file, fileName) {
        var url = window.URL || window.webkitURL;
        var blobUrl = url.createObjectURL(file);
        var a = document.createElement('a');
        a.href = blobUrl;
        a.target = '_blank';
        a.download = fileName;
        document.body.appendChild(a);
        $timeout(function() {
            a.click();
        }, 100);
    }
});