angular.module(appTeclo).controller('altaIncidenciaController', function($scope, showAlert, $location, growl, altaIncidenciaService, dataInfo, $timeout) {

	var fecha = new Date();
	$scope.fechaAnio = (fecha.getDate().toString().length==1?"0"+fecha.getDate():fecha.getDate()) + "/" + ((fecha.getMonth()+1)<10?"0"+(fecha.getMonth()+1):(fecha.getMonth()+1)) + "/" + fecha.getFullYear();
	$scope.totalLength = 1200;
	$scope.contador = $scope.totalLength;
	$scope.longitud = function (){ 
		$scope.contador = $scope.totalLength - ($scope.registroIncidencia.descripcion == undefined ? 0 : $scope.registroIncidencia.descripcion.length); 
	} 
	$scope.paramConfSav= new Object({
		idOrdenServ: null, 			 
		idProceso: null,   			
		idEncuesta:null,			
		idPregunta:null,			
		idIncidencia: null
	});
	
	$scope.paramConfiguracion=new Object({
		maxSizeMb: 5,			
        maxNuImage: 10,
        listTypeExtencion: ['jpg','png','jpeg','pdf','doc','docx'],
        listTpDocuemnt: null
	});
	
	$scope.listImages = [];
	$scope.registroIncidencia = {};
	$scope.idOrden = dataInfo.idOrden;
	$timeout(() => {
		$scope.listOrden.push({idOrdenServicio: 0, cdOrdenServicio: "SIN ORDEN SERVICIO"});
		if(dataInfo.idOrden){
			let orden = filtroBuscar($scope.listOrden, "idOrdenServicio", dataInfo.idOrden)
			if(orden){
				$scope.registroIncidencia.orden = orden; 
				$("#select2-orden-container").text(orden.cdOrdenServicio);
			}
		}else{
			$scope.registroIncidencia.orden = {idOrdenServicio: 0, cdOrdenServicio: "SIN ORDEN SERVICIO"}; 
			$("#select2-orden-container").text("SIN ORDEN SERVICIO");
		}
		$scope.registroIncidencia.modAten = $scope.listModAten[0]; 
		$("#select2-modAten-container").text($scope.registroIncidencia.modAten.nbCentroInstalacion);
		$scope.addTxDescripcion();
		$scope.listTecnico.push({idPersona: 0, nbPersona: "SIN TÉCNICO", nbPatPersona: "", nbMatPersona: ""})
		orderByAsc($scope.listTecnico, "idPersona");
		$scope.listTransportista.push({idConductor: 0, nbConductor: "SIN TRANSPORTISTA", nbApepatConductor: "", nbApematConductor: ""})
		orderByAsc($scope.listTransportista, "idConductor");
	},2000);
	function filtroBuscar(list, attr, val) {
        for (let x = 0; x < list.length; x++) {
            if (list[x][attr] == val) {
                return list[x];
            }
        }
        return null;
    };
//  Ordenar descendentemente 
    function orderByDes(items, attr) {
        items.sort(function (a, b, c){
            return (b[attr] - a[attr])
        })
    }
//    Ordenar ascendentemente 
    function orderByAsc(items, attr) {
        items.sort(function (a, b) {
            return (a[attr] - b[attr])
        })
    }
	$scope.addTxDescripcion = function() {
		let tpIncidencia = $scope.registroIncidencia.tpIncidencia ? "- TIPO DE INCIDENCIA: "+$scope.registroIncidencia.tpIncidencia.nbStSeguimiento+"\n" : "";
		let prioridad = $scope.registroIncidencia.prioridad ? "- PRIORIDAD: "+$scope.registroIncidencia.prioridad.nbStSeguimiento+"\n" : "";
		let orden = $scope.registroIncidencia.orden ? "- ORDEN SERVICIO: "+$scope.registroIncidencia.orden.cdOrdenServicio+"\n" : "";		
		let tecnico = $scope.registroIncidencia.tecnico ? "- TÉCNICO: "+$scope.registroIncidencia.tecnico.nbPersona+' '+$scope.registroIncidencia.tecnico.nbPatPersona+' '+$scope.registroIncidencia.tecnico.nbMatPersona+"\n" : "";
		let transportista = $scope.registroIncidencia.transportista ? "- TRANSPORTISTA: "+$scope.registroIncidencia.transportista.nbConductor+' '+$scope.registroIncidencia.transportista.nbApepatConductor+' '+$scope.registroIncidencia.transportista.nbApematConductor+"\n" : "";
		let modulo = $scope.registroIncidencia.modAten ? "- MÓDULO: "+$scope.registroIncidencia.modAten.nbCentroInstalacion+"\n" : "";
		let descripcion = $scope.registroIncidencia.descripcion ? "- DESCRIPCIÓN: "+$scope.registroIncidencia.descripcion+"\n" : "";
		$scope.registroIncidencia.txDescripcion = tpIncidencia + prioridad + orden + tecnico + transportista + modulo + descripcion;
//		$scope.registroIncidencia.txDescripcion = + $scope.registroIncidencia.prioridad.nbStSeguimiento+"\nHola"
		
	}
    $scope.guardar = function(ban) {
        if (ban && $scope.formAltaIncidencia.$invalid) {
            showAlert.requiredFields($scope.formAltaIncidencia);
            growl.warning("Formulario incompleto.", { ttl: 5000 });
            return;
        }
        let altaIncidencia = {
        		ordenServicio: $scope.registroIncidencia.orden ? $scope.registroIncidencia.orden.cdOrdenServicio : "",
				tpIncidencia: $scope.registroIncidencia.tpIncidencia ? $scope.registroIncidencia.tpIncidencia : null,
				prioridad: $scope.registroIncidencia.prioridad ? $scope.registroIncidencia.prioridad : null,
				descripcion: $scope.registroIncidencia.txDescripcion,
				listImagen: $scope.listImages
        }

        altaIncidenciaService.altaIncidencia(altaIncidencia).success(function(data) {
            if (data) {
            	growl.success("La incidencia ha sido guardado correctamente", { ttl: 5000 });
            }else
            	growl.warning("No se pudo realizar el alta de la incidencia", { ttl: 5000 });
        }).error(function(e) {
            $scope.listServicio = [];
            growl.warning(e.message, { ttl: 5000 });
        });
    }

    $scope.validarSelect = function () {
    	if($scope.parametroBusqueda.tipoBusqueda.idTipoBusqueda == 0){
    		$scope.parametroBusqueda.valor = null;
    		$scope.formConsultaServicio.valorBusqueda.$setPristine();
    	}
    }

    if(dataInfo.urlActual)
    	$scope.btnRegrear = true;
    
    $scope.regresar = function (){
//    	console.log(p.replace('-', '/'));
    	$location.path(dataInfo.urlActual.replace('-', '/'));
    }
//    $scope.guardar(false);
});
