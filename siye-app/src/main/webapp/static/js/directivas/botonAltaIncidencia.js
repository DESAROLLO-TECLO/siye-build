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
			idPregunta: '='
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
		        showAlert.confirmacion("Se redireccionará a otra pantalla para dar de alta la incidencia. ¿Desea continuar?",
		            //Aceptar
		            function() {
		                $location.path("/altaIncidencia/"+scope.idOrden+"/"+scope.idProceso+"/"+scope.idEncuesta+"/"+scope.idPregunta);
		                $('.modal-backdrop').remove();
		            },
		            //Cancelar
		            function() {
		                return;
		            }
		        );
    		}
		}
	};
});
