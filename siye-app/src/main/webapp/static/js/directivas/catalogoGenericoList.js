/*
 * Author: Mannuel Dirsio
 * Directive: catalogoGenericoList
 * Descripcion:Obtiene una lista de un servicio y enpoint dado desde html y la muestra 
   en el select asi mismo detecta un click del boton dado y lanza un modal para un nuevo registro[3 parametros]
 * Versión: 1.1.0
 */
  /*
         *-------------------Ejemplo Con el Componente select2-----------------------*
      
		*/

angular.module(appTeclo).directive('catalogoGenericoList', function($injector) {
    return {
        scope: {
        	idbtn:'=?',
        	nameModal:'=?',
        	objectId:'=',
        	opcionSelec:'=?',
        	paramBusq:'=',
        	nameConsultService:'=',
        	nameSaveService:'=',
        	modelResult:'=?',
        	nameSaveEndPointService:'=',
        	nameConsultEndPointService:'=',
        	listOptionDisables:'='
         },
         /*Se construye componente y se inyecta directiva se reciben paranetros*/
        link: function(scope, el, attr, ngModel) {
        	var nameContex=el.context.name;
        	var paramBusqueda=scope.paramBusq;
        	var nameConsultEmpServ=scope.nameConsultEndPointService;
        	var nameSaveEmpServ=scope.nameSaveEndPointService;
        	/*Se inyecta servicio con el nombre dado desde el html*/
        	var consultService=$injector.get(scope.nameConsultService);
        	if (scope.nameSaveService==null || scope.nameSaveEndPointService==null) {
        		$('#'+scope.idbtn).css('visibility', 'hidden');
        		//$('#'+scope.idbtn).attr("disabled", true);
			}else{
				var saveService=$injector.get(scope.nameSaveService);
			}
        	/*Se consume el enpoint del servicio dado desde html*/
        	consultService[nameConsultEmpServ](paramBusqueda).success(function(response){
        	/*Se obtiene la lista y se iguala  ala variable mandada desde html*/
        		if(scope.listOptionDisables == undefined)
        			scope.modelResult=response;
        		else
        			scope.modelResult=getOptions(response);
        		
        	}).error(function(error){
        		scope.modelResult=[];
        	});
        	
        	function getOptions(listOption){
        		if(listOption != undefined && listOption.length > 0
						&& scope.listOptionDisables != undefined && scope.listOptionDisables.length > 0){
					let i;
					let size=listOption.length;
					let j;
					let tamanio=scope.listOptionDisables.length;
					for(i=0; i<size; i++){
						let itemT=listOption[i];
						for(j=0; j<tamanio; j++){
							let itemC=scope.listOptionDisables[j];
							if(scope.objectId != undefined && itemT[scope.objectId] == itemC[scope.objectId]){
								listOption.splice(i,1);
								i--;
								size=listOption.length;
								break;
							}
						}
					}
				}
        		return listOption;
        	};
        	
        	/* si se desea agregar un registro "NUEVO" ,que sirva para lanzar modal como el boton de + 
    		$(el).on("change",function(){
    			if (scope.opcionSelec!=null) {
    				if (scope.opcionSelec.nbConductor=="NUEVO") {
    					let serviceModal=$injector.get('ModalService');
        				abrirModal(paramBusqueda,serviceModal,saveService,consultService, nameSaveEmpServ,nameConsultEmpServ)
    				}
				}
    			 					
    		});
    		*/
    		$(document).on('click', '#'+scope.idbtn, function() {
				let serviceModal=$injector.get('ModalService');
				var nameModal=scope.nameModal==null?"":scope.nameModal;
				abrirModal(nameModal,paramBusqueda, serviceModal,saveService,consultService, nameSaveEmpServ,nameConsultEmpServ) 	
    	    });
    		
    	    function abrirModal(nameModal,paramBusqueda, serviceModal,saveService,consultService, nameSaveEmpServ,nameConsultEmpServ){
    	    	serviceModal['showModal']({
    	 			   templateUrl: 'views/templatemodal/templateModalGenerico.html',
    	 			   controller: 'mensajeModalGenericoController',
    	 				   inputs:{
    	 					  nameModal:nameModal,
    	 					  idTipo: paramBusqueda,
    	 					  saveService:saveService,
    	 					  consultService:consultService,
    	 					  nameSaveEmpServ:nameSaveEmpServ,
    	 					  nameConsultEmpServ:nameConsultEmpServ
    	 				   }
    	 		   }).then(function(modal){
    	 			  modal.element.modal();
    	 			  modal.close.then(function(datos) {
    	 				  if (datos!=null) {
    	 				  var flagIdObject=scope.objectId==null?false:true;
    					   if(datos.existe===false){
    						   if(scope.listOptionDisables == undefined)
    			        			scope.modelResult=datos.newList;
    			        		else
    			        			scope.modelResult=getOptions(datos.newList);
    					   }
    					   var folioPersona=datos.newObject.cdPersona==null?"":" ("+datos.newObject.cdPersona+")";
    					   let componenteCombo=$("#select2-"+nameContex+"-container");
    					   if(componenteCombo != undefined)
    						   componenteCombo.text(datos.newObject.nombre+" "+datos.newObject.aPaterno+" "+datos.newObject.aMaterno+folioPersona);
    					   
    			    		if (!flagIdObject) 
    			    			scope.opcionSelec=datos.newObject.idPersona;
								 else	
							     angular.forEach( scope.modelResult,function(item,index){
    			    			 if (item[scope.objectId]==datos.newObject.idPersona) {
    	    							 scope.opcionSelec=item;
    	    					   }
    			    		 });
    	 				  }
    				   }); 
    			 });
    	    };
    	    
    	    //Metdod que le da alcance al controlador anfitron de la directiva
    	    scope.$parent.$parent.getListViewCombo = function(){
    	    	return scope.modelResult;
    	    };
    	    
    	    scope.$parent.$parent.setListViewCombo = function(listOptions){
    	    	scope.modelResult=listOptions;
    	    };
        }           
    }
});