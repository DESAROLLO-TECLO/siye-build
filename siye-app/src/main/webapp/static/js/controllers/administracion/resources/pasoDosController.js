angular.module(appTeclo).controller('pasoDosController', function($controller, $scope, $rootScope, $routeParams, $timeout, $location, $localStorage, WizardHandler, showAlert, growl, configAppService, ModalService) {

	$scope.informationUserVO = {};
	
	$scope.saveStepTwo = function() {
		
		var wizardExample = WizardHandler.wizard('wizardExample');
		
		if($scope.formStepTwo.$invalid) {
			showAlert.requiredFields($scope.formStepTwo);
			growl.error('Formulario incompleto');
		} else {
			wizardExample.next();
		}
	}
	
});