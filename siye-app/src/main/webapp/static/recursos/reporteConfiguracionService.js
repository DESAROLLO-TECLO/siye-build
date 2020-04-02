angular.module(appTeclo).service("reporteConfiguracionService", 
function($http,storageService, config, $timeout, $location) {

    this.checkParams = function(cadena, scriptSelect) {
        return $http.get(config.baseUrl + "/reporte/checkParams", { params: { 'cadena': cadena } });
    };

    this.crearTestQuery = function(arrayParamValue, cadena, app) {
        //return $http.get(app.txUrl + "/validacion/crearTestQuery", { params: { 'arrayParamValue': arrayParamValue,'cadena': cadena } });
        return $http({
            method: 'GET',
            url: app.txUrl + "/validacion/crearTestQuery",
            dataType: "json",
            headers: {
                'Content-Type': 'application/json'
            },
            params: { 
				'arrayParamValue': arrayParamValue,
                'cadena': cadena ,
                'validacion': 'validacion'
			} 
        });
        
    };

    this.ejecutarQueryCatalogo = function(query, app) {
        /*return $http.get(app.txUrl + "/validacion/ejecutarQueryCat", {
            params: {
                'query': query
            }
        });*/
        return $http({
            method: 'GET',
            url: app.txUrl + "/validacion/ejecutarQueryCat",
            dataType: "json",
            headers: {
                'Content-Type': 'application/json'
            },
            params: { 
                'query': query,
                'validacion': 'validacion'
            } 
        });


    };

    this.guadaReporte = function(voObject) {
        return $http.post(config.baseUrl + "/reporte/guadaReporte", voObject);
    };

    this.getAllCatalogos = function() {
        return $http.get(config.baseUrl + "/reporte/getAllCatalogos");
    };

    this.listTiposReportes = function() {
        return $http.get(config.baseUrl + "/reporte/listaTipoReportes");
    };

    this.guadaEdicionReporte = function(voObject) {
        return $http.put(config.baseUrl + "/reporte/guadaEdicionReporte", voObject);
    };

    this.deleteReporteDinamico = function(idReporte) {
        return $http.delete(config.baseUrl + "/reporte/deleteReporteDinamico", {
            params: { 'idReporte': idReporte }
        });
    };

    /*obtener lista de reportes para la consulta de reportes dinamicos*/
    this.getReporteListaDinamic = function(objectPeticion) {
        return $http.post(config.baseUrl + "/reporte/getReporteListaDinamic", objectPeticion);
    };

    this.changeEstatusRep = function(objectVO) {
        return $http.put(config.baseUrl + "/reporte/changeEstatusRep", objectVO);
    };

    this.getReporteEdit = function(idReporte) {
        return $http.get(config.baseUrl + "/reporte/getReporteEdit", {
            params: { 'idReporte': idReporte }
        });
    };

    this.consultaPrevia = function(idParametro, valuesParams) {
        return $http.get(config.baseUrl + "/reporte/consultaPrevia", {
            params: { 'idParametro': idParametro, 'valores': valuesParams }
        });
    };

    this.methodDescargaExcel = function(parametros) {
        return $http({
            method: 'POST',
            url: config.baseUrl + "/validacion/excel",
            dataType: "json",
            data: parametros,
            header: {
                "Content-type": "application/json",
                "Accept": "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            },
            responseType: 'arraybuffer'
        });
    };

    this.methodConsultaDinamica = function(parametros) {
        //return $http.post(config.baseUrl + "/reporte/reporte", parametros);
        return $http({
            method: 'POST',
            url: config.baseUrl + "/validacion/reporte",
            dataType: "json",
            headers: {
                'Content-Type': 'application/json'
            },
            params: {
                'validacion': 'validacion'
            },
            data: parametros
        });

    };

    /*Método para normalizar las cabeceras
     * esto se hizo para que el ordenamiento
     * dinamico funcione sin problemas
     * */
    this.normalizeHeaders = function(listaHeaders) {
        var obj = {};
        var headReturn = [];
        var strHead = '';
        if (listaHeaders.length > 0) {
            for (var i = 0; i < listaHeaders.length; i++) {
                strHead = this.getCleanedString(listaHeaders[i]);
                obj = { id: strHead, name: listaHeaders[i] };
                headReturn.push(obj);
            }
            //console.log(headReturn);
        }
        return headReturn;
    };

    /**Método que reemplaza carcateres espaciales
     */
    this.getCleanedString = function(cadena) {
        //Lo queremos devolver limpio en minusculas
        cadena = cadena.toLowerCase();
        //Definimos los caracteres que queremos eliminar
        var specialChars = "!@#$^&%*()+=-[]\/{}|:<>?,.";
        // Los eliminamos todos
        for (var i = 0; i < specialChars.length; i++) {
            cadena = cadena.replace(new RegExp("\\" + specialChars[i], 'gi'), '');
        }
        // Quitamos espacios y los sustituimos por _ porque nos gusta mas asi
        cadena = cadena.replace(/ /g, ""); //reemplazar espacios
        cadena = cadena.replace(/_/g, ""); //reemplazar guiones bajos y medios
        cadena = cadena.replace(/-/g, ""); //reemplazar guiones bajos y medios
        // Quitamos acentos y "ñ". Fijate en que va sin comillas el primer parametro
        cadena = cadena.replace(/á/gi, "a");
        cadena = cadena.replace(/é/gi, "e");
        cadena = cadena.replace(/í/gi, "i");
        cadena = cadena.replace(/ó/gi, "o");
        cadena = cadena.replace(/ú/gi, "u");
        cadena = cadena.replace(/ñ/gi, "n");
        return cadena; //retornar cadena limpia
    };

    this.convertMapKeyAndValue = function(values, keys) {
        listReturn = [];
        var object = null;
        for (var x = 0; x < values.length; x++) {
            object = {};
            for (var y = 0; y < values[x].length; y++) {
                object[keys[y].id] = values[x][y];
            }
            listReturn.push(object);
        }
        return listReturn;
    };

    this.save = function(file, fileName) {
        var url = window.URL || window.webkitURL;
        var blobUrl = url.createObjectURL(file);
        var a = document.createElement('a');
        a.href = blobUrl;
        a.target = '_blank';
        a.download = fileName;
        document.body.appendChild(a); 
        //a.click();
        $timeout(function() {
            a.click();
        }, 100);
    };

    this.getDataPefilesReportes = function(data) {
        return $http.get(config.baseUrl + "/reporte/getDataPefilesReportes", {
            params: { 'idAplicacion': data }
        });
    };

    this.persisteConfigReportePerf = function(data) {
        var voObject = {
            perfiles: data.perfiles,
            reportes: data.reportes,
            interseccion: data.interseccion,
            aplicacionRepVO: data.aplicacionRepVO
        };
        return $http.post(config.baseUrl + "/reporte/persisteConfigReportePerf", voObject);
    };

    this.getAplicaciones = function() {
        return $http.get(config.baseUrl + "/reporte/getAplicaciones");
    };

    this.sincronizaPerfil = () => {
        let cdApp = storageService.getConfiguration().aplicacion.codigo;
        return $http({
            method: 'GET',
            url: config.baseUrl + "/validacion/sincronizaPerfil",
            dataType: "json",
            headers: {
                'Content-Type': 'application/json'
            },
            params: { 
                'cdApp': cdApp,
                'validacion': 'validacion'
            } 
        });


    };


    this.listaReportes = function(data) {
        let URIWS = storageService.getParameterValApp('URLAPIREP');
        let idPerfil = storageService.getUsrAplication().idPerfil;
        let objPeticion = new Object({
            'idAplicacion': storageService.getConfiguration().aplicacion.idAplicacion,
            'idPerfil': idPerfil,
            'perfilSincronizacionVO': data
        });
        return $http({
            method: 'POST',
            url: URIWS + "/reporte/listaReportes",
            dataType: "json",
            headers: {
                'Content-Type': 'application/json'
            },
            data: objPeticion,
            params: { 
                'validacion': 'validacion'
            } 
        });
    };

    // Servicio de actualización de recursos
    this.descargarActualizacion = function(cdApp, uriReporte) {
        var protocol = $location.protocol() + "://";
        var host = $location.host()+":"+$location.$$port;
		var url = protocol + host + "/" + config.contextApp;
        return $http.get(url + "/parametro/getParametro", {
            params: {
                'cdApp': cdApp,
                'url': uriReporte
            }
        });
    };



});