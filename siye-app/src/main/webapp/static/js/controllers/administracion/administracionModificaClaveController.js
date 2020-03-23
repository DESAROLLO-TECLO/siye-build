angular.module(appTeclo).controller('administracionModificaClaveController', function($scope, $filter, cambioContraseniaService, ModalService) {
	$scope.objeto = {
			currentPassword: "",
			newPassword : "",
			repeatPassword : ""	
	}
	
	
	/* NOTIFICACIONES MODAL */
	$scope.showAviso = function(messageTo, action) {
        ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalAviso.html',
          controller: 'mensajeModalController',
          inputs:{ message: messageTo}
        }).then(function(modal) {
          modal.element.modal();
          modal.close.then(function(result) {
	        	if(result){
	        		action();
	        	}
	        }); 
        });
	};
	
	$scope.showError = function(messageTo) {
        ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalError.html',
          controller: 'mensajeModalController',
          	  inputs:{ message: messageTo}
        }).then(function(modal) {
          modal.element.modal();
        });
	};
	
	$scope.showConfirmacion = function(messageTo, action){
		ModalService.showModal({
	    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	        controller: 'mensajeModalController',
	        	inputs:{ message: messageTo}
	    }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		action();
	        	}
	        }); 
	    });
	};
 
	$scope.guardar = function()
	{
 
		if($scope.objeto.currentPassword == "" || $scope.objeto.newPassword == "" || $scope.objeto.repeatPassword == "")
		{
			if ($scope.formChgPass.$invalid) {
		           angular.forEach($scope.formChgPass.$error, function (field) {
		             angular.forEach(field, function(errorField){
		           	 errorField.$setDirty();
		             })
		           });
		           $scope.showAviso("Formulario Incompleto", function(){});
		           return;
		     }
			
		}
		else
		{
			if($scope.objeto.newPassword != $scope.objeto.repeatPassword)
			{
				$scope.showAviso("La contraseña nueva no coincide con la confirmación", function(){});
				$scope.objeto.currentPassword = "";  
				$scope.objeto.newPassword = "";  
				$scope.objeto.repeatPassword = "";
				$scope.formChgPass.$setPristine();
			}
			else
			{
				if($scope.objeto.currentPassword == $scope.objeto.newPassword)
				{
					$scope.showAviso("La contraseña nueva no puede ser igual a la actual", function(){});
					$scope.objeto.currentPassword = "";  $scope.objeto.newPassword = "";  $scope.objeto.repeatPassword = "";
					$scope.formChgPass.$setPristine();
				}
				else
				{
					$scope.cambiarClave();
				}
			}
		}
	};
	
	$scope.cambiarClave = function(){
		cambioContraseniaService.cambioContrasenia($scope.objeto.currentPassword, $scope.objeto.newPassword,  $scope.objeto.repeatPassword).success(function(data) {
			if(data.code == 200){
				$scope.showAviso(data.message, function(){});
			}
			else
			{
				$scope.showAviso(data[1]);
			}
			$scope.objeto.currentPassword = "";  
			$scope.objeto.newPassword = "";  
			$scope.objeto.repeatPassword = "";
			$scope.formChgPass.$setPristine();
		}).error(function(data) {
			if(data.code==409){
				$scope.showAviso(data.message);
			}else{
				$scope.showAviso(data.message);
			}
			$scope.objeto.currentPassword = "";  
			$scope.objeto.newPassword = "";  
			$scope.objeto.repeatPassword = "";
			$scope.formChgPass.$setPristine();
		});	
	};
	
	
	$scope.tituloSug="<b>Sugerencia de contraseña</b></br>";
	$scope.tamanio="<div style='width: 70%; min-width:310px; text-align: justify;'> - Al menos 8 caracteres </br>";
	$scope.mayusculas="- Al menos una letra mayúscula </br>";
	$scope.minuscula="- Al menos una letra minúscula </br>";
	$scope.digitos="- Al menos un dígito </br>";
	$scope.caracEpecials="- Al menos un caracter del conjunto:<br/>" +
	"<center>!&nbsp#&nbsp$&nbsp%&nbsp&&nbsp(&nbsp)&nbsp*&nbsp+&nbsp,&nbsp-&nbsp.&nbsp/&nbsp:&nbsp;&nbsp" +
	"<&nbsp=&nbsp>&nbsp?&nbsp@&nbsp[&nbsp\\&nbsp]&nbsp_&nbsp|</center>";
	$scope.tituloEjemplo="<br/><center><b>Ejemplo de contraseñas:</b></center>";
	$scope.pasInvalid="Contraseña no valida: </br><center> teclo1+- </center>";
	$scope.passValid="Contraseña valida: </br> <center>Teclo3Logistica@</center> </div>";
});