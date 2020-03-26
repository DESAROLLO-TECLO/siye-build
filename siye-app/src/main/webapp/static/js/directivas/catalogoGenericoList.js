/*
 * Author: Mannuel Dirsio
 * Directive: catalogoGenericoList
 * Descripcion:Obtiene una lista de un servicio y enpoint dado desde html y la muestra 
   en el select asi mismo detecta un click del boton y lanza un modal para un nuevo registro
 * Versión: 1.1.0
 */

angular.module(appTeclo).directive('catalogoGenericoList', function($injector) {
    return {
        /* Se Obtienen los parametros al poner la directiva 
         * catalogoGenericoList(catalogo-generico-list ) en el componente
         *-------------------Ejemplo Con el Componente select2-----------------------*
         *<select  class="form-control" select2
							ng-model="selectWithSearch" 
							catalogo-generico-list
							name-service="'catalogoGenericoService'" 
							name-end-point-service="'cargarCatalogo'" 
							model-result="selectList"
							opcion-selec="selectWithSearch"
							ng-options="x as x.nbConductor for x in selectList track by x.idConductor">
							<option value="">{{'APP.Base.mensaje.seleccioneOpcion' | translate}}</option>
			</select>*/
        scope: {
        	opcionSelec:'=',
        	modeloAtributos:'=',
        	nameService:'=',
        	modelResult:'=?',
        	nameSaveEndPointService:'=',
        	nameConsultEndPointService:'='
         },
         /*Se construye componente y se inyecta directiva se reciben paranetros*/
        link: function(scope, el, attr, ngModel) {
        	var model=scope.modeloAtributos;
        	let nameEmpServ=scope.nameConsultEndPointService;
        	/*Se inyecta servicio con el nombre dado desde el html*/
        	var service=$injector.get(scope.nameService);
        	/*Se consume el enpoint del servicio dado desde html*/
        	service[nameEmpServ](model).success(function(response){
        	/*Se obtiene la lista y se iguala  ala variable mandada desde html*/
        		scope.modelResult=response;
        	}).error(function(error){
        		scope.modelResult=[];
        	}); 	
        	
    		$(el).on("change",function(){
    			if (scope.opcionSelec!=null) {
    				if (scope.opcionSelec.nbConductor=="NUEVO") {
    					let serviceModal=$injector.get('ModalService');
    					let endPointService=scope.nameSaveEndPointService;
        				abrirModal("Guardar Objeto",serviceModal,service, endPointService)
    				}
				}
    			 					
    		});
    		
    		$(document).on('click', '#btnPersona', function() {
				let serviceModal=$injector.get('ModalService');
				let endPointService= scope.nameSaveEndPointService;
				abrirModal("Guardar Objeto", serviceModal,service,endPointService) 	
    	    });
    		
        } 
    }
 
    function abrirModal(messageTo, serviceModal,nameService,endPointService){
    	serviceModal['showModal']({
 			   templateUrl: 'views/templatemodal/templateModalGenerico.html',
 			   controller: 'mensajeModalGenericoController',
 				   inputs:{ message: messageTo,
 					  nameService:nameService,
 					  endPointService:endPointService
 				   }
 		   }).then(function(modal){
 			  modal.element.modal();
		 }); 
    	
    	};
});