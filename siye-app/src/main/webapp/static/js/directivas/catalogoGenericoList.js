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
        	idComponent:'=?',
        	opcionSelec:'=',
        	modeloAtributos:'=',
        	nameConsultService:'=',
        	nameSaveService:'=',
        	modelResult:'=?',
        	nameSaveEndPointService:'=',
        	nameConsultEndPointService:'='
         },
         /*Se construye componente y se inyecta directiva se reciben paranetros*/
        link: function(scope, el, attr, ngModel) {
        	var model=scope.modeloAtributos;
        	var nameConsultEmpServ=scope.nameConsultEndPointService;
        	var nameSaveEmpServ=scope.nameSaveEndPointService;
        	/*Se inyecta servicio con el nombre dado desde el html*/
        	var consultService=$injector.get(scope.nameConsultService);
        	var saveService=$injector.get(scope.nameSaveService);
			
        	/*Se consume el enpoint del servicio dado desde html*/
        	consultService[nameConsultEmpServ](model).success(function(response){
        	/*Se obtiene la lista y se iguala  ala variable mandada desde html*/
        		scope.modelResult=response;
        	}).error(function(error){
        		scope.modelResult=[];
        	}); 	
        	
    		$(el).on("change",function(){
    			if (scope.opcionSelec!=null) {
    				if (scope.opcionSelec.nbConductor=="NUEVO") {
    					let serviceModal=$injector.get('ModalService');
        				abrirModal("Guardar Objeto",serviceModal,saveService,consultService, nameSaveEmpServ,nameConsultEmpServ)
    				}
				}
    			 					
    		});
    		
    		$(document).on('click', '#btnPersona', function() {
    			var a=scope;
    			var e=el;
    			var c= attr;
    			var d=ngModel;
				let serviceModal=$injector.get('ModalService');
				abrirModal("Guardar Objeto", serviceModal,saveService,consultService, nameSaveEmpServ,nameConsultEmpServ) 	
    	    });
    		
    	    function abrirModal(messageTo, serviceModal,saveService,consultService, nameSaveEmpServ,nameConsultEmpServ){
    	    	serviceModal['showModal']({
    	 			   templateUrl: 'views/templatemodal/templateModalGenerico.html',
    	 			   controller: 'mensajeModalGenericoController',
    	 				   inputs:{ message: messageTo,
    	 					  saveService:saveService,
    	 					  consultService:consultService,
    	 					  nameSaveEmpServ:nameSaveEmpServ,
    	 					  nameConsultEmpServ:nameConsultEmpServ
    	 				   }
    	 		   }).then(function(modal){
    	 			  modal.element.modal();
    	 			 modal.close.then(function(datos) {
    					   if(datos.existe===false){
    						   scope.modelResult=datos.newList;
    							$("#select2-selectName-container").text(datos.newObject.nombre+" "+datos.newObject.aPaterno+" "+datos.newObject.aMaterno);
    			    					scope.opcionSelec=datos.newObject.idPersona;

    					   }else if(datos.existe){
    						   $("#select2-selectName-container").text(datos.newObject.nombre+" "+datos.newObject.aPaterno+" "+datos.newObject.aMaterno);
    			    					scope.opcionSelec=datos.newObject.idPersona;
    					   }
    				   }); 
    			 }); 
    	    	
    	    };
    		
        } 
         
         
         
    }
 
//    function abrirModal(messageTo, serviceModal,nameService,service,endPointService,modelResult,ngModel){
//    	serviceModal['showModal']({
// 			   templateUrl: 'views/templatemodal/templateModalGenerico.html',
// 			   controller: 'mensajeModalGenericoController',
// 				   inputs:{ message: messageTo,
// 					  nameService:nameService,
// 					  endPointService:endPointService,
// 					  modelResult:modelResult,
// 					  ngModel:ngModel,
// 					  serviceConsult:service
// 				   }
// 		   }).then(function(modal){
// 			  modal.element.modal();
// 			 modal.close.then(function(datos) {
//				   if(!datos.existe){
//					   modelResult=datos.newList;
//						$("#select2-selectName-container").text(datos.newObject.nombre);
//		    			angular.forEach(modelResult,function(el,a){
//		    				if (angular.equals(el.idConductor,datos.newObject.idPersona)) {
//		    					ngModel=el.idConductor;
//		    				}
//		    			});
//				   }else if(datos.existe){
//					   $("#select2-selectName-container").text(datos.newObject.nombre);
//		    			angular.forEach(modelResult,function(el,a){
//		    				if (angular.equals(el.idConductor,datos.newObject.idPersona)) {
//		    					ngModel=el.idConductor;
//		    				}
//		    			}); 
//				   }
//			   }); 
//		 }); 
//    	
//    	};
});