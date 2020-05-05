angular.module(appTeclo).controller('detalleConsultaController', 
function($scope, showAlert, $location, growl, ordenInfo, consultaServicioService) {
	$scope.ordenInfo = ordenInfo.data;

	calcularDuracion = function(){
//		$scope.ordenInfo.fhAtencionIni = "2020-05-05 07:30:00";
		if($scope.ordenInfo.fhAtencionIni!=null){
	//		var fecha1 = moment("2016-09-30 07:30:00", "YYYY-MM-DD HH:mm");
	//		var fecha2 = moment("2016-10-03 08:31:26", "YYYY-MM-DD HH:mm");
			
			var fecha1 = moment($scope.ordenInfo.fhAtencionIni, "YYYY-MM-DD HH:mm");
			var fecha2 = null
			if($scope.ordenInfo.fhAtencionFin!=null){
				fecha2 = moment($scope.ordenInfo.fhAtencionFin != null , "YYYY-MM-DD HH:mm");
			}else{
				fecha2 = moment();
			}
			
			$scope.ordenInfo
			
			var now = moment();
			
			var diff = fecha2.diff(fecha1, 's'); // Diff in hours
	//		console.log(diff);
			
			var horas = Math.trunc(diff / 3600);
		    var minutos = Math.trunc((diff-horas*3600)/60);
		    var segundos = diff-(horas*3600+minutos*60);
		    console.log(horas + ":" + minutos + ":" + segundos);
			
		    $scope.ordenInfo.tpDuracion = horas + ":" + minutos + ":" + segundos;
		}else{
			$scope.ordenInfo.tpDuracion = "00:00:00";
		}
	}
	
	formateaDatos = function(){
		if($scope.ordenInfo.idOrigenOds==1){
			$scope.ordenInfo.cdOrigenOds = "LOTE"
		}if($scope.ordenInfo.idOrigenOds==2){
			$scope.ordenInfo.cdOrigenOds = "INCIDENCIA"
		}
		$scope.ordenInfo.centroInstalacion.nbCentroInstalacion 
			= $scope.ordenInfo.centroInstalacion.nbCentroInstalacion.toUpperCase();  
	}
	
	calcularDuracion();
	formateaDatos();
	
});

//