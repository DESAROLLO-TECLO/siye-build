angular.module(appTeclo).controller('detalleConsultaController', 
function($scope, showAlert, $location, growl, ordenInfo, consultaServicioService) {
	$scope.ordenInfo = ordenInfo.data;

	calcularDuracion = function(){
		let fdiferencia ="00:00:00";

		if($scope.ordenInfo.fhAtencionIni!=null){		
			const DATE_FORMAT = "YYYY-MM-DD HH:mm:ss";	
			let fecha1 = moment($scope.ordenInfo.fhAtencionIni, "YYYY-MM-DD HH:mm");
			let fecha2 = null;
			const SegDia=86400, SegHora= 3600, SegMinuto = 60;
			let dias, horas, minutos,segundos, diff;

			if($scope.ordenInfo.fhAtencionFin!=null){
				fecha2 = moment($scope.ordenInfo.fhAtencionFin, "YYYY-MM-DD HH:mm");
			}else{
				fecha2 = moment();
			}

			diff = fecha2.diff(fecha1,'s');

			if(diff >0){
				if(diff>=SegDia){
					dias = Math.trunc(diff / SegDia);
					diff = (diff % SegDia);
					fdiferencia = dias+" Dias ";
				}
				if(diff>=SegHora){
					horas = Math.trunc(diff / SegHora);
					diff = (diff % SegHora);
					fdiferencia = fdiferencia + horas+" Horas ";
				}
				if(diff>= SegMinuto){
					minutos = Math.trunc(diff / SegMinuto);
					diff = (diff % SegMinuto)	
					fdiferencia = fdiferencia + minutos +" Minutos";
				}
			}else{
				// fechas iguales 
				fdiferencia = "SIN TIEMPO";
			}
		}
		$scope.ordenInfo.tpDuracion = fdiferencia;

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
	
	consultarTransportistas = function(){
		$scope.transportista=[];
		$scope.transportista="";
		$scope.sintransportista = "";
	    var idVeh = ordenInfo.data.vehiculo.idVehiculo;
	    if(idVeh != null ){
	    	consultaServicioService.getTransportistasVehiculo(idVeh).success(function(data){
	            $scope.transportistas = data;
	            $scope.transportista=$scope.transportistas[0].nbConductor+" "+$scope.transportistas[0].nbApepatConductor+" "+$scope.transportistas[0].nbApematConductor
	            $scope.sintransportista = "";
	        }).error(function(error){
	            console.log(error);
	            $scope.transportista=[];
	            $scope.transportista="";
	            $scope.sintransportista = "Sin Transportistas";
	        });
	    }
	};
	
	consultarSupervisores = function(){
		$scope.supervisores=[];
		$scope.supervisor="";
		$scope.sinSupervisor = "Sin Supervisores";
	   
	};

	consultarTransportistas();
	consultarSupervisores();
	formateaDatos();
	calcularDuracion();
	
});

//