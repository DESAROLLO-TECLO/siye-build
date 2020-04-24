angular.module(appTeclo).controller('catalogoController', function($scope, $filter, showAlert, growl, catalogoService) {
	
	$scope.direccionCatalogos = undefined;
	$scope.listCatReportes = [];
	$scope.mostrarBotonMius=true;
	$scope.nombreReporte="";
	$scope.isCollapsed=false;
	
	$scope.catalogosActivos = function(){
		
		catalogoService.getCatalogosActivos().success(function(data){
			$scope.listCatReportes = [];
            $scope.listCatReportes = configReporte(data);
			$scope.error=false;
		}).error(function(data){
//			$scope.listCatalogosActivos = {};
			$scope.error = true;
			growl.error("No se encontraron catalogos activos", { ttl: 5000 });
		})
	};
	
	
	configReporte = function(allReportes) {
		var listReturn = [];
		angular.forEach(allReportes, function(reporte, key){
			if (reporte.idPadreTblCatalogo === 0) {
				reporte.subReportes = [];
				angular.forEach(allReportes, function(subReporte, key){
					if (reporte.idPadreTblCatalogo === subReporte.idPadreTblCatalogo) {
						reporte.subReportes.push(subReporte);
					}
			    });
				listReturn.push(reporte);
			}
	    });
		
		if(listReturn.length > 0){
			$scope.lAux = [];
			for(var i = 0; i< listReturn.length; i++){
				if(listReturn[i].subReportes.length >= 1){
					$scope.lAux.push(listReturn[i]);
				}
			}
		}
		angular.copy($scope.lAux,listReturn);
		listReturn=reloadListOrdenada(listReturn,'nombre');
		
		agruparLista(listReturn);
		
		return listReturn;
	};
	
	function reloadListOrdenada(lista,nameOrderProperty){
		return $filter('orderBy')(lista, nameOrderProperty,false);
	};
	
	function agruparLista (l){
		var many = 3, object;
		$scope.listaAgrupada = [];
		
		if(l.length > 0){
			for(var i = 0; i < l.length; i+= many){
				object = {
						reporte1: l[i]
				};
				
//				if (l[i + 1] && (many == 2 || many == 3)) {
//			    	  object.reporte2 = l[i + 1];
//
//			      }
//			      if (l[i + (many - 1)] && many == 3) {
//			    	  object.reporte3 = l[i + 2];
//			      }
			      $scope.listaAgrupada.push(object);
			}
		}	
	};
	
	
	
	$scope.catalogosActivos();
	
});