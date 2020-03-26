angular.module(appTeclo).controller('mensajeModalGenericoController', function($scope, $element, message,nameService,endPointService, close,growl,showAlert) {
	
	$scope.message = message;
	$scope.operacion = function(result,objeto,formObject){
		$scope.message=$scope.message;
		if (formObject.$invalid && result) {
			showAlert.requiredFields(formObject);
			growl.error('Formulario Incompleto');
		}else{
		if (result) {
			nameService[endPointService](objeto).success(function(data){
				growl.success("Se guardo registro");
				$('.modal-backdrop').remove();
				close(result,100);
			}).error(function(data){
				growl.error(data.message);
			});
		}else{
			$('.modal-backdrop').remove();
			close(result,100);
			}
			}
		}
});