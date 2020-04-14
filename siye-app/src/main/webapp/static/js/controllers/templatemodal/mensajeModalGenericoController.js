angular.module(appTeclo).controller('mensajeModalGenericoController', function($scope, $element,nameModal, idTipo,saveService,consultService, nameSaveEmpServ,nameConsultEmpServ, close,growl,showAlert,catalogoGenericoService) {
	$scope.flagBusqueda=idTipo==null?false:true;
	$scope.flagCdUsuario=false;
	$scope.nameModal=nameModal;
	$scope.flagBtnGuardar=false;
	$scope.flagDisableInput=false;
	$scope.objeto=new Object();
	$scope.operacion = function(result,objeto,formObject){
		if (idTipo!=null && objeto!=null) {objeto.idTipoPersona=idTipo};
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
				consultService[nameConsultEmpServ](idTipo).success(function (data) {
					newData.newList=data;
					newData.existe=false;
					if ($scope.flagBusqueda) {
						growl.success("Se guardo registro con Folio: "+newData.newObject.cdPersona,{ ttl: 8000 });
					}else{
						growl.success("Se guardo registro",{ ttl: 1000 });

					}
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
			close(null,100);}
			}
		}
	//Buscar Usuario
	$scope.buscarPersona=function(formBusq,cdPersona){
		if (formBusq.$invalid) {
			showAlert.requiredFields(formBusq);
			growl.error('Codigo Requerido');
			}
			else
			consultService['buscarPersona'](cdPersona,idTipo).success(function (data) {
				$scope.flagDisableInput=true;
				$scope.objeto=data;
				$scope.flagCdUsuario=$scope.objeto.existia;
				$scope.flagBtnGuardar=true;
				if ($scope.objeto.existia) 
					growl.success("El Registro ya existe con este perfil",{ ttl: 5000 });		
				else
					growl.warning("El Registro ya existe con otro perfil",{ ttl: 5000 });
				
				}).error(function (data) {
				growl.error(data.message);
				$scope.objeto=new Object();
				$scope.flagDisableInput=false;
				$scope.flagBtnGuardar=false;
			});	
	};
	$scope.aceptar=function(object){
		var newData=new Object({
		    existe:true,
			newObject:object,
			newList:new Array()
		});
		$('.modal-backdrop').remove();
		close(newData,100);
	};
	
});