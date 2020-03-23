angular.module(appTeclo).controller('pasoTresController', function($controller, $scope, $rootScope, $routeParams, $timeout, $location, $localStorage, WizardHandler, showAlert, growl, configAppService, ModalService) {
	
	$scope.informationContactVO = {};
	
	$scope.saveStepThree = function() {
				
		var wizardExample = WizardHandler.wizard('wizardExample');
		
		if($scope.formStepThree.$invalid) {
			showAlert.requiredFields($scope.formStepThree);
			growl.error('Formulario incompleto');
		} else {
			wizardExample.next();
		}
	}
	
});