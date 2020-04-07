angular.module(appTeclo).controller('altaIncidenciaController', function($scope, showAlert, $location, growl, altaIncidenciaService, dataInfo) {

	var fecha = new Date();
	$scope.fechaAnio = (fecha.getDate().toString().length==1?"0"+fecha.getDate():fecha.getDate()) + "/" + ((fecha.getMonth()+1)<10?"0"+(fecha.getMonth()+1):(fecha.getMonth()+1)) + "/" + fecha.getFullYear();
	$scope.contador = 1000;
	$scope.longitud = function (){ 
		$scope.contador = 1000 - ($scope.registroIncidencia.descripcion == undefined ? 0 : $scope.registroIncidencia.descripcion.length); 
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
	
	$scope.listImages = {};
	$scope.registroIncidencia = {};
	$scope.idOrden = dataInfo.idOrden;
//	$scope.registroIncidencia.modAten = $scope.listModAten[0]; 
	$scope.addTxDescripcion = function() {
		let tpIncidencia = $scope.registroIncidencia.tpIncidencia ? "TIPO DE INCIDENCIA: "+$scope.registroIncidencia.tpIncidencia.nbStSeguimiento+"\n" : "";
		let prioridad = $scope.registroIncidencia.prioridad ? "PRIORIDAD: "+$scope.registroIncidencia.prioridad.nbStSeguimiento+"\n" : "";
		let orden = $scope.registroIncidencia.orden ? "ORDEN SERVICIO: "+$scope.registroIncidencia.orden.cdOrdenServicio+"\n" : "";		
		let tecnico = $scope.registroIncidencia.tecnico ? "TÉCNICO: "+$scope.registroIncidencia.tecnico.nbPersona+' '+$scope.registroIncidencia.tecnico.nbPatPersona+' '+$scope.registroIncidencia.tecnico.nbMatPersona+"\n" : "";
		let transportista = $scope.registroIncidencia.transportista ? "TRANSPORTISTA: "+$scope.registroIncidencia.transportista.nbConductor+' '+$scope.registroIncidencia.transportista.nbApepatConductor+' '+$scope.registroIncidencia.transportista.nbApematConductor+"\n" : "";
		let modulo = $scope.registroIncidencia.modAten ? "MÓDULO: "+$scope.registroIncidencia.modAten.nbCentroInstalacion+"\n" : "";
		let descripcion = $scope.registroIncidencia.descripcion ? "DESCRIPCIÓN: "+$scope.registroIncidencia.descripcion+"\n" : "";
		$scope.registroIncidencia.txDescripcion = tpIncidencia + prioridad + orden + tecnico + transportista + modulo + descripcion;
//		$scope.registroIncidencia.txDescripcion = + $scope.registroIncidencia.prioridad.nbStSeguimiento+"\nHola"
		
	}
    $scope.guardar = function(ban) {
        if (ban && $scope.formAltaIncidencia.$invalid) {
            showAlert.requiredFields($scope.formAltaIncidencia);
            growl.warning("Formulario incompleto.", { ttl: 5000 });
            return;
        }
        altaIncidenciaService.altaIncidencia($scope.parametroBusqueda).success(function(data) {
            if (data) {
            	growl.success("La incidencia ha sido guardado correctamente", { ttl: 5000 });
            }
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
