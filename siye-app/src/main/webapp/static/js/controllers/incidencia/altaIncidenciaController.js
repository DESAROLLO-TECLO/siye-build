angular.module(appTeclo).controller('altaIncidenciaController', function($scope, showAlert, $location, growl, altaIncidenciaService, dataInfo, $timeout, catalogoGenericoService, $interval) {

	tick = function() { 
		$scope.fechaAnio = Date.now(); 
	} 
	tick(); 
	$interval(tick, 1000);
	$scope.totalLength = 1200;
	$scope.contador = $scope.totalLength;
	$scope.longitud = function (){ 
		$scope.contador = $scope.totalLength - ($scope.registroIncidencia.descripcion == undefined ? 0 : $scope.registroIncidencia.descripcion.length); 
	} 
	$scope.paramConfSav = new Object({
		idOrdenServ: null, 			 
		idProceso: null,   			
		idEncuesta:null,			
		idPregunta:null,			
		idIncidencia: null
	});
	$scope.paramConfiguracion = new Object({
		maxSizeMb: 2,			
        maxNuImage: 1,
        listTypeExtencion: ['jpg','png','jpeg','pdf','doc','docx'],
        listTpDocuemnt: new Array()
	});
	$scope.listImages = [];
	$scope.registroIncidencia = {};
	
	getParamIncidencia = function (){
		altaIncidenciaService.getNuMaxImgIncidencia().success(function(data) {
			let param = filtroBuscar(data, "cdLlavePConfig", "TIE051D_NU_MAX_IMAGENES");
			$scope.paramConfiguracion.maxNuImage = param.cdValorPConfig; 
			param = filtroBuscar(data, "cdLlavePConfig", "TIE051D_IMG_REQ");
			$scope.requiredImage = param.cdValorPConfig == "Si" ? true : false;
			ejecutarDespues(1);
		}).error(function(e) {
            growl.warning(e.message, { ttl: 5000 });
        });
		catalogoGenericoService.getOrdenServicio().success(function(data) {
			$scope.listOrden = data;
			$scope.listOrden.push({idOrdenServicio: 0, cdOrdenServicio: "SIN ORDEN DE SERVICIO"});
			orderByAsc($scope.listOrden, "idOrdenServicio");
			if(dataInfo.idOrden){
				let orden = filtroBuscar($scope.listOrden, "idOrdenServicio", dataInfo.idOrden)
				if(orden){
					$scope.registroIncidencia.orden = orden.cdOrdenServicio; 
				}
			}else{
				$scope.registroIncidencia.orden = $scope.listOrden[0].cdOrdenServicio;
			}
			$scope.addTxDescripcion();
		}).error(function(e) {
            $scope.registroIncidencia.orden = "SIN ORDEN DE SERVICIO";
        });
		catalogoGenericoService.getModAten().success(function(data) {
			$scope.listModAten = data;
			$scope.registroIncidencia.modAten = $scope.listModAten[0].nbCentroInstalacion;
			$scope.addTxDescripcion();
		}).error(function(e) {
            growl.warning("No se encontró el módulo de atención", { ttl: 5000 });
        });
	}	
	ejecutarDespues = function (opc){
		if(opc == 1){
			$timeout(() => {
			$scope.listTecnico.push({idPersona: 0, nbPersona: "SIN TÉCNICO", nbPatPersona: "", nbMatPersona: ""})
			orderByAsc($scope.listTecnico, "idPersona");
			$scope.listTransportista.push({idConductor: 0, nbConductor: "SIN TRANSPORTISTA", nbApepatConductor: "", nbApematConductor: ""})
			orderByAsc($scope.listTransportista, "idConductor");
			},1500);
		}else{
			if(dataInfo.idOrden && $scope.listOrden.length != 0){
				let orden = filtroBuscar($scope.listOrden, "idOrdenServicio", dataInfo.idOrden)
				if(orden){
					$scope.registroIncidencia.orden = orden.cdOrdenServicio; 
				}
			}else{
				$scope.registroIncidencia.orden = "SIN ORDEN DE SERVICIO";
			}
			$scope.registroIncidencia.modAten = $scope.listModAten[0].nbCentroInstalacion; 
			orderByAsc($scope.listTecnico, "idPersona");
			orderByAsc($scope.listTransportista, "idConductor");
		}
		$scope.addTxDescripcion();
	}
	function filtroBuscar(list, attr, val) {
        for (let x = 0; x < list.length; x++) {
            if (list[x][attr] == val) {
                return list[x];
            }
        }
        return null;
    };
//    Ordenar ascendentemente 
    function orderByAsc(items, attr) {
        items.sort(function (a, b) {
            return (a[attr] - b[attr])
        })
    }
	$scope.addTxDescripcion = function() {
		let tpIncidencia = $scope.registroIncidencia.tpIncidencia ? "- TIPO DE INCIDENCIA: "+$scope.registroIncidencia.tpIncidencia.nbStSeguimiento+"\n" : "";
		let prioridad = $scope.registroIncidencia.prioridad ? "- PRIORIDAD: "+$scope.registroIncidencia.prioridad.nbStSeguimiento+"\n" : "";
		let orden = $scope.registroIncidencia.orden ? "- ORDEN DE SERVICIO: "+$scope.registroIncidencia.orden+"\n" : "";		
		let tecnico = $scope.registroIncidencia.tecnico ? "- TÉCNICO: "+$scope.registroIncidencia.tecnico.nbPersona+' '+$scope.registroIncidencia.tecnico.nbPatPersona+' '+$scope.registroIncidencia.tecnico.nbMatPersona+"\n" : "";
		let transportista = $scope.registroIncidencia.transportista ? "- TRANSPORTISTA: "+$scope.registroIncidencia.transportista.nbConductor+' '+$scope.registroIncidencia.transportista.nbApepatConductor+' '+$scope.registroIncidencia.transportista.nbApematConductor+"\n" : "";
		let modulo = $scope.registroIncidencia.modAten ? "- MÓDULO: "+$scope.registroIncidencia.modAten+"\n" : "";
		let descripcion = $scope.registroIncidencia.descripcion ? "- DESCRIPCIÓN: "+$scope.registroIncidencia.descripcion+"\n" : "";
		$scope.registroIncidencia.txDescripcion = tpIncidencia + prioridad + orden + tecnico + transportista + modulo + descripcion;
	}
    $scope.guardar = function(ban) {
    	$scope.listImages=$scope.getValueListImageDirective();
        
    	if (ban && $scope.formAltaIncidencia.$invalid && !$scope.isValidFormImages()) {
            showAlert.requiredFields($scope.formAltaIncidencia);
            growl.warning("Formulario incompleto.", { ttl: 5000 });
            return;
        }
        if($scope.requiredImage && $scope.listImages.length == 0){
        	growl.warning("Falta agregar imagenes.", { ttl: 5000 });
            return;
        }        	
        let altaIncidencia = {
        		ordenServicio: $scope.registroIncidencia.orden ? $scope.registroIncidencia.orden.cdOrdenServicio : "",
				tpIncidencia: $scope.registroIncidencia.tpIncidencia ? $scope.registroIncidencia.tpIncidencia : null,
				prioridad: $scope.registroIncidencia.prioridad ? $scope.registroIncidencia.prioridad : null,
				descripcion: $scope.registroIncidencia.txDescripcion,
				listImagen: $scope.listImages,
				idOrdenServicio: dataInfo.idOrden,
				idProceso: dataInfo.idProceso,
				idEncuesta: dataInfo.idEncuesta,
				idPregunta: dataInfo.idPregunta
        }

        altaIncidenciaService.altaIncidencia(altaIncidencia).success(function(data) {
            if (data) {
            	showAlert.aviso(data);
            	$scope.contador = $scope.totalLength;
            	$scope.formAltaIncidencia.$setPristine();
        		$scope.registroIncidencia = {};
        		$("#select2-tipoIncidencia-container").text('Seleccione una opción');
        		$("#select2-prioridad-container").text('Seleccione una opción');
        		$("#select2-tecnico-container").text('Seleccione una opción');
        		$("#select2-transportista-container").text('Seleccione una opción');
        		$scope.listImages = [];
        		$scope.updateViewDirective($scope.listImages);//  actualiza la vista de la directiva
        		ejecutarDespues(2);
            }else
            	growl.warning("No se pudo realizar el alta de la incidencia", { ttl: 5000 });
        }).error(function(e) {
            $scope.listServicio = [];
            growl.warning(e, { ttl: 5000 });
        });
    }

    if(dataInfo.urlActual)
    	$scope.btnRegrear = true;
    
    $scope.regresar = function (){
//    	console.log(p.replace('-', '/'));
    	$location.path(dataInfo.urlActual.replace(/-/g, '/'));
    }
//    $scope.guardar(false);
    getParamIncidencia();
});
