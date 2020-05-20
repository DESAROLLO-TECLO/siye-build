angular.module(appTeclo).controller('detalleConsultaController', 
function($scope, showAlert, $location, growl, ordenInfo, consultaServicioService, expedienteService) {
	$scope.ordenInfo = ordenInfo.data;
	$scope.evidenciaMostrar = {};
	$scope.supervisores=[];

	calcularDuracion = function(fhIni, fhFin){
		let fdiferencia ="00:00:00";

		if(fhIni!=null){		
			const DATE_FORMAT = "YYYY-MM-DD HH:mm:ss";	
			let fecha1 = moment(fhIni, "YYYY-MM-DD HH:mm");
			let fecha2 = null;
			const SegDia=86400, SegHora= 3600, SegMinuto = 60;
			let dias, horas, minutos,segundos, diff;

			if(fhFin!=null){
				fecha2 = moment(fhFin, "YYYY-MM-DD HH:mm");
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
		return fdiferencia;

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
						/*$scope.evidencia.procesos[proceso].listEncuestas[encuesta].listPreguntas[pregunta].infoEvidencia.imagenes = 
							$scope.evidencia.procesos[proceso].listEncuestas[encuesta].infoEvidencia.imagenes;*/
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

	descargarDetalleOS = function(conImagenes){
		consultaServicioService.descargarReporteDetalle($scope.ordenInfo.idOrdenServicio, conImagenes)
			.success(function(archivo) {
				$scope.error = false;
//				var filename = data.nombreArchivo;
				var filename = "Detalle Orden de Servicio Folio: " + $scope.ordenInfo.cdOrdenServicio;
//	    		let file = new Blob([data.bdPath], { type: 'application/pdf' });
	    		let file = new Blob([archivo], { type: 'application/pdf' });
//				var file = $scope.b64toBlob(data.bdPath, "application/pdf");
				$scope.error = false;
				consultaServicioService.downloadfile(file, filename);
			}).error(function(data) {
				$scope.error=data;
			});
	};
	
	$scope.confirmaImagenesReporte = function() {
        showAlert.confirmacion("¿Incluir imagenes en el reporte?",
            //Aceptar
            function() {
        	descargarDetalleOS(true);
            },
            //Cancelar
            function() {
            	descargarDetalleOS(false);
            }
        );
    };
	
	consultarTransportistas();
	consultarSupervisores();
	formateaDatos();
//	calcularDuracion();
	consultaEvidencia();
});

//