angular.module(appTeclo).controller('modalController', function($scope, ModalService) {

	/* Modal Aviso */
	$scope.showAviso = function(messageTo) {
      ModalService.showModal({
        templateUrl: 'views/templatemodal/templateModalAviso.html',
        controller: 'mensajeModalController',
        inputs:{ message: messageTo}
      }).then(function(modal) {
        modal.element.modal();
      });
	};
	
	/* Modal Error */
	$scope.showError = function(messageTo) {
      ModalService.showModal({
        templateUrl: 'views/templatemodal/templateModalError.html',
        controller: 'mensajeModalController',
        	  inputs:{ message: messageTo}
      }).then(function(modal) {
        modal.element.modal();
      });
	};
	
	/* Modal Confirmacion */
	$scope.showConfirmacion = function(messageTo){
		ModalService.showModal({
	    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	        controller: 'mensajeModalController',
	        	inputs:{ message: messageTo}
	    }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		alert("Exito!");
	        	}
	        }); 
	    });
	};
});