angular.module(appTeclo).service("encuestaSatisfaccionService", function($http, config) {
	
const END_POINT="/encuesta";
	
this.servicio = function () {

	return $http.get(config.baseUrl + "/encuestaSatisfaccion/prueba"); 
};

this.getDetalleEncuesta = function (idEncuesta, idOrdenServicio) {
	return $http.get(config.baseUrl + END_POINT + "/detalle", 
	{params:{"idEncuesta": idEncuesta,"idOrdenServicio": idOrdenServicio}});
};

this.getEncuesta = function (tipoBusqueda,valor,password){
	var idTipobusqueda = parseInt(tipoBusqueda);
	return $http.get(config.baseUrl + END_POINT + "/consultaEncuestasSatisfaccion",
	{params:{"tipoBusqueda":idTipobusqueda,"valor":valor,"password":password}});
};


this.getNumPreguntasPorSeccion = function (cdParametro) {
	return $http.get(config.baseUrl +"/catalogo/parametroCd", 
	{params:{"cdParametro": cdParametro}});
};

this.saveRespuestaEncuesta=function(listRespuestasVO){
	return $http.post(config.baseUrl + END_POINT+"/respuestas",listRespuestasVO);
};


this.finalizaEncuesta=function(usuarioEncuestaVO){
	return $http.put(config.baseUrl + END_POINT+"/finalizarEncuestaS",usuarioEncuestaVO);
};

this.cargarEncuesta = function (idOrdenServicio,idEncuesta) {
		return $http.get(config.baseUrl + END_POINT + "/cargar", 
		{
			params:{"idOrdenServicio": idOrdenServicio,"idEncuesta": idEncuesta}
		});
		};
		
//Combo Causas
this.comboCausas = function(idOpcion){
			return $http.get(config.baseUrl + "/catalogo/catCuasas",		
			{params:{"idOpcion": idOpcion}	
			});	
		};	
});
