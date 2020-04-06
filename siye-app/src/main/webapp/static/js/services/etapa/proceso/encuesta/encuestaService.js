angular.module(appTeclo)
.service("encuestaService", 
function($http, config, $rootScope) {

    this.getInfoEncuesta = function(idEncuesta){
        return $http.get(config.baseUrl + "/encuesta/detalle", {
            params:{
                "idEncuesta": idEncuesta,
                "idOrdenServicio": parseInt($rootScope.idOrSer)
            }
        });
    };
    
    this.cargarEncuesta = function(idEncuesta,idOrdenServicio){
        return $http.get(config.baseUrl + "/encuesta/cargar", {
            params:{
                "idEncuesta": idEncuesta,
                "idOrdenServicio": idOrdenServicio
            }
        });
    };
    
	this.saveRespuestaEncuesta=function(listRespuestasVO){
		return $http.post(config.baseUrl + "/encuesta/respuestas",listRespuestasVO);
	};
	
	this.getNumPreguntasPorSeccion = function (cdParametro) {
		return $http.get(config.baseUrl +"/catalogo/parametroCd", 
		{params:{"cdParametro": cdParametro}});
	};
	
	this.finalizaEncuesta=function(usuarioEncuestaVO){
		return $http.put(config.baseUrl + "/encuesta/finalizar",usuarioEncuestaVO);
	};

});