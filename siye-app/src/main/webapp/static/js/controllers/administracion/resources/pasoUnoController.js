angular.module(appTeclo).controller('pasoUnoController', function($controller, $scope, $rootScope, $routeParams, $timeout, $location, $localStorage, WizardHandler, showAlert, growl, configAppService, ModalService) {
		
	$scope.createAccountVO = {};
	
	$scope.saveStepOne = function() {
		
		var wizardExample = WizardHandler.wizard('wizardExample');
		
		if($scope.formStepOne.$invalid) {
			showAlert.requiredFields($scope.formStepOne);
			growl.error('Formulario incompleto');
		} else {
			wizardExample.next();
		}
	}
	
});