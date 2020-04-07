/*
 * Author: F.Castillo
 * Directive: botonIncidencia
 * Descripcion: Pinta un boton y obtiene informacion necesaria para enviarlo al alta de Incidencias
 * Versión: 1.1.0
 */

angular.module(appTeclo).directive('botonIncidencia', function ($location, showAlert) {
	return {
		restrict :'AE',  
		scope: {
	    	idOrden: '=',
			idProceso: '=',
			idEncuesta: '=',
			idPregunta: '=',
			urlActual: '='
	    },
	    template:  
	    	' 	<div class="row">                                    							'+
	    	' 		<div class="col-md-12">                                						'+
	    	'	 	<button type="button" class="btn botonIncidencia" ng-click="redireccionar()">   '+
	    	'	 		<span class="glyphicon glyphicon-tags" aria-hidden="true"></span>   	'+
	    	'	 		<br>Incidencia                                    							'+
	    	'	 	</button>                                    								'+
	    	'	</div> ',
    	link: function(scope){
    		scope.redireccionar = function (){
    			let idOrden = scope.idOrden == undefined ? 0 : scope.idOrden;
        		let idProceso = scope.idProceso == undefined ? 0 : scope.idProceso;
        		let idEncuesta = scope.idEncuesta == undefined ? 0 : scope.idEncuesta;
        		let idPregunta = scope.idPregunta == undefined ? 0 : scope.idPregunta;
        		let urlActual = scope.urlActual == undefined ? 0 : scope.urlActual;
                $location.path("/altaIncidencia/"+idOrden+"/"+idProceso+"/"+idEncuesta+"/"+idPregunta+"/"+urlActual);
//		        showAlert.confirmacion("Se redireccionará a otra pantalla para dar de alta la incidencia. ¿Desea continuar?",
//		            //Aceptar
//		            function() {
//		                $('.modal-backdrop').remove();
//		            },
//		            //Cancelar
//		            function() {
//		                return;
//		            }
//		        );
    		}
		}
	};
});
