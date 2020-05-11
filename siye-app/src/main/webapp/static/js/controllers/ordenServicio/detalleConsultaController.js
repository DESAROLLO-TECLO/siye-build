angular.module(appTeclo).controller('detalleConsultaController', 
function($scope, showAlert, $location, growl, ordenInfo, consultaServicioService, expedienteService) {
	$scope.ordenInfo = ordenInfo.data;
	$scope.evidenciaMostrar = {};
	$scope.supervisores=[];

	calcularDuracion = function(fhIni, fhFin){
		if(fhIni!=null && fhIni!=""){
			
			var fecha1 = moment(fhIni, "DD/MM/YYYY HH:mm:ss");
			var fecha2 = null
			if(fhFin!=null && fhFin!="")
				fecha2 = moment(fhFin, "DD/MM/YYYY HH:mm:ss");
			else
				fecha2 = moment();
			
			var diff = fecha2.diff(fecha1, 's'); // Diff in hours
			
			var horas = Math.trunc(diff / 3600);
		    var minutos = Math.trunc((diff-horas*3600)/60);
		    var segundos = diff-(horas*3600+minutos*60);
		    var diferencia =  (horas<10 ? ("0"+horas): horas) + ":" + (minutos<10?("0"+minutos):minutos) + ":" + (segundos<10?("0"+segundos):segundos);
			
		    return diferencia;
		}else{
			return "00:00:00";
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
		
		$scope.ordenInfo.tpDuracion = calcularDuracion($scope.ordenInfo.fhAtencionIni, $scope.ordenInfo.fhAtencionFin);
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
	            $scope.transportistas = [];
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
	
	consultaEvidencia = function(){
//		expedienteService.getInfoOs("ORDEN_SERVICIO", $scope.ordenInfo.cdOrdenServicio).success(function(data){
		expedienteService.getInfoDetalleOSNivel("ORDEN_SERVICIO", $scope.ordenInfo.cdOrdenServicio).success(function(data){
			$scope.evidencia = data;
			$scope.error = false;
			
			$scope.evidencia.infoEvidencia.activo = false;
			$scope.supervisor = $scope.evidencia.infoEvidencia.nbSupervisor != null ? $scope.evidencia.infoEvidencia.nbSupervisor[0] : "Sin Supervisor";
			for(sup in $scope.evidencia.infoEvidencia.nbSupervisor){
				var sup = {"name":$scope.evidencia.infoEvidencia.nbSupervisor[sup]};
				$scope.supervisores.push(sup);
			}
			
			for(proceso in $scope.evidencia.procesos){
				$scope.evidencia.procesos[proceso].infoEvidencia.activo = false;
				for(encuesta in $scope.evidencia.procesos[proceso].listEncuestas){
					$scope.evidencia.procesos[proceso].listEncuestas[encuesta].infoEvidencia.activo = false;
					for(pregunta in $scope.evidencia.procesos[proceso].listEncuestas[encuesta].listPreguntas){
						$scope.evidencia.procesos[proceso].listEncuestas[encuesta].listPreguntas[pregunta].infoEvidencia.activo = false;
						$scope.evidencia.procesos[proceso].listEncuestas[encuesta].listPreguntas[pregunta].infoEvidencia.tieneImagen = 
							$scope.evidencia.procesos[proceso].listEncuestas[encuesta].infoEvidencia.activo
						$scope.evidencia.procesos[proceso].listEncuestas[encuesta].listPreguntas[pregunta].infoEvidencia.fechaIni = 
							$scope.evidencia.procesos[proceso].listEncuestas[encuesta].infoEvidencia.fechaIni;
						$scope.evidencia.procesos[proceso].listEncuestas[encuesta].listPreguntas[pregunta].infoEvidencia.fechaFin = 
							$scope.evidencia.procesos[proceso].listEncuestas[encuesta].infoEvidencia.fechaFin;
						$scope.evidencia.procesos[proceso].listEncuestas[encuesta].listPreguntas[pregunta].infoEvidencia.nbSupervisor = 
							$scope.evidencia.procesos[proceso].listEncuestas[encuesta].infoEvidencia.nbSupervisor;
						$scope.evidencia.procesos[proceso].listEncuestas[encuesta].listPreguntas[pregunta].infoEvidencia.nbInstalador = 
							$scope.evidencia.procesos[proceso].listEncuestas[encuesta].infoEvidencia.nbInstalador;
						$scope.evidencia.procesos[proceso].listEncuestas[encuesta].listPreguntas[pregunta].infoEvidencia.nbTrasportista = 
							$scope.evidencia.procesos[proceso].listEncuestas[encuesta].infoEvidencia.nbTrasportista;
						$scope.evidencia.procesos[proceso].listEncuestas[encuesta].listPreguntas[pregunta].infoEvidencia.imagenes = 
							$scope.evidencia.procesos[proceso].listEncuestas[encuesta].infoEvidencia.imagenes;
					}
				}
			}
			
			$scope.mostrarInfoEvidencia("Orden de Servicio", $scope.evidencia);
//	$scope.mostrarInfoEvidencia("Orden de Servicio", null, null, 
//			"Sin supervisor", "Sin instalador", "Sin Trasportista", $scope.listaImagenes, $scope.evidencia);
			
		}).error(function(data){
			$scope.evidencia = {};
			$scope.error = data;
		});
	}
	
	$scope.mostrarInfoEvidencia = function(nivelNombre, obj){
		$scope.evidenciaMostrar.nombre = nivelNombre;
		$scope.evidenciaMostrar.fhIni = obj.infoEvidencia.fechaIni;
		$scope.evidenciaMostrar.fhFin = obj.infoEvidencia.fechaFin;
		$scope.evidenciaMostrar.duracion = calcularDuracion(obj.infoEvidencia.fechaIni, obj.infoEvidencia.fechaFin); 
		$scope.evidenciaMostrar.supervisor = obj.infoEvidencia.nbSupervisor != null ? obj.infoEvidencia.nbSupervisor[0] : "Sin Supervisor";
		$scope.evidenciaMostrar.instalador = obj.infoEvidencia.nbInstalador != null ? obj.infoEvidencia.nbInstalador[0] : "Sin Instalador";
		$scope.evidenciaMostrar.transportista = obj.infoEvidencia.nbTrasportista != null ? obj.infoEvidencia.nbTrasportista[0] : "Sin Trasportista";
		
		$scope.evidenciaMostrar.supervisores = obj.infoEvidencia.nbSupervisor;
		$scope.evidenciaMostrar.instaladores = obj.infoEvidencia.nbInstalador;
		$scope.evidenciaMostrar.transportistas = obj.infoEvidencia.nbTrasportista;
		
		
		$scope.evidenciaMostrar.listImg = obj.infoEvidencia.imagenes;
		
		$scope.evidencia.infoEvidencia.activo = false;
		for(proceso in $scope.evidencia.procesos){
			$scope.evidencia.procesos[proceso].infoEvidencia.activo = false;
			for(encuesta in $scope.evidencia.procesos[proceso].listEncuestas){
				$scope.evidencia.procesos[proceso].listEncuestas[encuesta].infoEvidencia.activo = false;
				for(pregunta in $scope.evidencia.procesos[proceso].listEncuestas[encuesta].listPreguntas){
					$scope.evidencia.procesos[proceso].listEncuestas[encuesta].listPreguntas[pregunta].infoEvidencia.activo = false;
				}
			}
		}
		
		obj.infoEvidencia.activo = true;
	}

	consultarTransportistas();
	consultarSupervisores();
	formateaDatos();
//	calcularDuracion();
	consultaEvidencia();
});

//