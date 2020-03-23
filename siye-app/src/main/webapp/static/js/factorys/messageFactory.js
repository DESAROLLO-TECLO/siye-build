
angular.module(appTeclo).factory('messageFactory',[function(){
	var listaErrores = {};
	var listaErroresSeguridad = {};

	listaErrores[-1]      = {codigo: -1,  mensaje: 'Error en la petici\u00F3n.'};
	listaErrores[400]     = {codigo: 400, mensaje: 'Petici\u00F3n incorrecta.'};
	listaErrores[420]     = {codigo: 420, mensaje: 'No se pudo establecer conexión.'};

	listaErrores[500]     = {codigo: 500, mensaje: 'Error interno al resolver la operaci\u00F3n.'};


 
    var interfaz = {       
        getErrorMessage: function(cdMessage){
        	if(listaErrores[cdMessage])
        		return listaErrores[cdMessage].mensaje;
        	else
        		return '';
        } 
    }
    return interfaz;
}]);