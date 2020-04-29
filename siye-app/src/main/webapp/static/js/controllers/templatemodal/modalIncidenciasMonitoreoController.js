angular.module(appTeclo).controller('modalIncidenciasMonitoreoController', 
	function ($rootScope, $scope, $location, $document, showAlert, growl, catalogoGenericoService, monIncidenciaService, ModalService, OrdenServicio) {
	$scope.OrdenServicioModalIncidencias =  angular.copy(OrdenServicio);
	
	$scope.paramConfigPage = {
			bigCurrentPage : 1,
			bigTotalItems : 0,
			itemsPerPage : 1,
			maxSize : 2
		};
		
	// Escuchar la variable de paginador
    $scope.$watch("paramConfigPage.bigCurrentPage", function(newValue, oldValue) {
        if (newValue === oldValue) {
            return;
        }
        $scope.recuperIncidencia(newValue);
	});
	
    $scope.recuperIncidencia=function(numPagina){
        var incidenciaVO = $scope.incidenciasMonVO.slice(((numPagina - 1) * $scope.paramConfigPage.itemsPerPage), ((numPagina) * $scope.paramConfigPage.itemsPerPage));
        var idIncidencia=incidenciaVO[0].idIncidencia;
        $scope.consultaExpedienteIncidencia(idIncidencia);
    }
	
	$scope.paramsModalIncidencias = {};
	
	$scope.defaultValuesModal = function(){
		$scope.paramsModalIncidencias.fechaInicio = $scope.paramsRespaldoModal.fechaInicio;
		$scope.paramsModalIncidencias.fechaFin = $scope.paramsRespaldoModal.fechaFin;
		$scope.paramsModalIncidencias.idCentroInstalacion = $scope.paramsRespaldoModal.idCentroInstalacion;
		
		if($scope.OrdenServicioModalIncidencias.idOrdenServicio != null){
			$scope.paramsModalIncidencias.tipoBusqueda = 1;
			$scope.paramsModalIncidencias.valor = $scope.OrdenServicioModalIncidencias.idOrdenServicio; 
		}else if($scope.OrdenServicioModalIncidencias.idIncidencia != null){
			$scope.paramsModalIncidencias.tipoBusqueda = 2;
			$scope.paramsModalIncidencias.valor = $scope.OrdenServicioModalIncidencias.idIncidencia;
		}else{
			$scope.paramsModalIncidencias.tipoBusqueda = 3;
			$scope.paramsModalIncidencias.valor = "";
		}
		$scope.consultaMonIncidencias();
	}
	
	$scope.consultaMonIncidencias = function () {
		monIncidenciaService.getIncidenciasByTipobusqueda(
			$scope.paramsModalIncidencias
		).success(function (data) {
			if(data.length > 0){
				$scope.paramConfigPage.bigTotalItems=data.length;
				$scope.incidenciasMonVO = data;
				for (var i = 0; i < $scope.incidenciasMonVO.length; i++) {
					$scope.incidenciasMonVO[i].listaImagenes = [];
				}
				$scope.consultaExpedienteIncidencia($scope.incidenciasMonVO[0].idIncidencia);
				
			}
		}).error(function (data) {
			growl.error(data.message);
		});
	};
	
	$scope.consultaExpedienteIncidencia = function (idIncidencia) {
		monIncidenciaService.getExpedienteIncidencia(
			idIncidencia
		).success(function (data) {
			if(data.length > 0){
				var incidenciasExpedienteVO = data;
//				for (var i = 0; i < array.length; i++) {
//					var array_element = array[i];
//					
//				}
//				var img = {
//						imagen: "data:image/JPEG;base64," + data.image,
//						nombreArchivo: fotoNombre,
//					};
				for (var i = 0; i < $scope.incidenciasMonVO.length; i++) {
					if($scope.incidenciasMonVO[i].idIncidencia == idIncidencia){
						$scope.incidenciasMonVO[i].listaImagenes = incidenciasExpedienteVO;
					}
				}
			}
		}).error(function (data) {
			growl.error(data.message);
		});
	};
	
	$scope.cerrarModal = function(){
		$('.modal-backdrop').remove();
		close(null,null);
	}
	
	$scope.defaultValuesModal();
});