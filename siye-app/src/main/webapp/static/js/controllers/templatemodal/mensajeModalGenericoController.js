angular.module(appTeclo).controller('mensajeModalGenericoController', function($scope, $element, message,saveService,consultService, nameSaveEmpServ,nameConsultEmpServ, close,growl,showAlert,catalogoGenericoService) {
	
	$scope.message = message;
	$scope.operacion = function(result,objeto,formObject){
		$scope.message=$scope.message;
		if (formObject.$invalid && result) {
			showAlert.requiredFields(formObject);
			growl.error('Formulario Incompleto');
		}else{
			var newData=new Object({
			    existe:null,
				newObject:new Object(),
				newList:new Array()
			});
		if (result) {
			saveService[nameSaveEmpServ](objeto).success(function(data){
				newData.newObject=data;
				if (data.existia) {
					newData.existe=true;
					growl.warning("El registro ya existe");
					$('.modal-backdrop').remove();
					close(newData,100);
				}else{
					consultService[nameConsultEmpServ]().success(function (data) {
						newData.newList=data;
						newData.existe=false;
						growl.success("Se guardo registro");
						$('.modal-backdrop').remove();
						close(newData,100);
						    }).error(function (data) {
						    	growl.error(data.message);
						    });
				}
			
			}).error(function(data){
				growl.error(data.message);
			});
		}else{
			$('.modal-backdrop').remove();
			close(newData,100);
			}
			}
		}
});