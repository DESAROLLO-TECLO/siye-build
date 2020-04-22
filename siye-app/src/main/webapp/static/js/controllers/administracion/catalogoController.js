angular.module(appTeclo).controller('catalogoController', function($scope, showAlert, growl, catalogoService) {
	
	$scope.catalogosActivos = function(){
		
		catalogoService.getCatalogosActivos().success(function(data){
			$scope.listCatalogosActivos = data;
			$scope.error=false;
		}).error(function(data){
			$scope.listCatalogosActivos = {};
			$scope.error = true;
			growl.error("No se encontraron catalogos activos", { ttl: 5000 });
		})
	};
	
	$scope.catalogosActivos();
	
});