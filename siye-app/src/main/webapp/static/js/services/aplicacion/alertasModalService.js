angular.module(appTeclo).service("showAlert", function(ModalService) {
	
	/* Modal aviso */
	this.aviso = function(messageTo,success,paramSuccess) {
		
		var properties = setProperties(messageTo,success,paramSuccess,null,null,"aviso");
		
		ModalService.showModal({
			templateUrl : 'views/templatemodal/templateShowAlertas.html',
			controller : 'modalMensajeController',
			inputs : {
				pa: properties
			}
		}).then(function(modal) {
			modal.element.modal();
		});

		return ModalService.showModal;
	}

	/* Modal error */
	this.error = function(messageTo,success,paramSuccess) {
		
		var properties = setProperties(messageTo,success,paramSuccess,null,null,"error");
		
		ModalService.showModal({
			templateUrl : 'views/templatemodal/templateShowAlertas.html',
			controller : 'modalMensajeController',
			inputs : {
				pa: properties
			}
		}).then(function(modal) {
			modal.element.modal();
		});
		
		return ModalService.showModal;
	}

	/* Modal confirmacion */
	this.confirmacion = function(messageTo,success,paramSuccess,cancel,paramCancel) {
		
		var properties = setProperties(messageTo,success,paramSuccess,cancel,paramCancel,"confirmacion");
		var arg = arguments.length;
		
		if(properties.message !== undefined) {
			
			if(arg === 3) {
				if(typeof properties.paramSuccess === 'function') {
					properties.cancel = properties.paramSuccess;
				}
			}else if(arg === 4) {
				if(typeof properties.cancel !== 'function') {
					properties.paramCancel = angular.copy(properties.cancel);
					properties.cancel = properties.paramSuccess;
				}
			}else{
				if(arg > 5) {
					return;
				}
			}
			
		} else {
			return;
		}
		
		ModalService.showModal({
			templateUrl : 'views/templatemodal/templateShowAlertas.html',
			controller : 'modalMensajeController',
			inputs : {
				pa: properties
			}
		}).then(function(modal) {
			modal.element.modal();
		});
		
		return ModalService.showModal;
	}
	
	this.requiredFields = function(formName) {
		angular.forEach(formName.$error, function (field) {
			angular.forEach(field, function(errorField){
				errorField.$setDirty();
			})
		});
	}
	
	setProperties = function(m,a,pa,c,ca,tm) {
		return properties = {
			message:m,
			success:a,
			paramSuccess:pa,
			cancel:c,
			paramCancel:ca,
			typeModal:tm
		};
	}

});