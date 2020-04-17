angular.module(appTeclo)
.service("encuestaService", 
function($http, config, $rootScope) {
	
    primerProceso=undefined;
    primerEncuestaPrimerProceso=undefined;
	primerEncuesta=undefined;
    



    this.getInfoEncuesta = function(idEncuesta,idOrden){
        return $http.get(config.baseUrl + "/encuesta/detalle", {
            params:{
                "idEncuesta": idEncuesta,
                "idOrdenServicio": idOrden
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
	
	//Combo Causas
	this.comboCausas = function(idOpcion){
		return $http.get(config.baseUrl + "/catalogo/catCuasas",		
		{
            params:{
                "idOpcion": idOpcion
            }	
		});	
	};
	
    this.iniciarProceso = function(idOrden){
        return $http.get(config.baseUrl + "/proceso/iniciarProceso", {
            params:{
                "idOrdenServicio": idOrden
            }
        });
    };
    
    this.avanzarProceso = function(idOrden){
        return $http.get(config.baseUrl + "/proceso/avanzarEncuestaProceso", {
            params:{
                "idOrdenServicio": idOrden
            }
        });
    };
    
    this.iniciarTiempoProceso = function(idOrden,idProceso){
        return $http.get(config.baseUrl + "/proceso/iniciarTiempoProceso", {
            params:{
                "idOrdenServicio": idOrden,
                "idProceso": idProceso
            }
        });
    };

});