angular.module(appTeclo).controller('modalIncidenciasMonitoreoController', 
	function ($rootScope, $scope, $location, $document, showAlert, growl, catalogoGenericoService, monIncidenciaService, ModalService, OrdenServicio) {
	$scope.OrdenServicioModalIncidencias =  angular.copy(OrdenServicio);
	
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
				$scope.incidenciasMonVO = data;
			}
		}).error(function (data) {
			growl.error(data.message);
		});
	};
	
	$scope.consultaMonIncidencias = function () {
		monIncidenciaService.getIncidenciasByTipobusqueda(
			$scope.paramsModalIncidencias
		).success(function (data) {
			if(data.length > 0){
				$scope.incidenciasMonVO = data;
			}
		}).error(function (data) {
			growl.error(data.message);
		});
	};
	
	$scope.cambioDeIncidencia = function(){
		alert("hola newPageNumber: oldPageNumber: ");
	}
	
	$scope.defaultValuesModal();
});