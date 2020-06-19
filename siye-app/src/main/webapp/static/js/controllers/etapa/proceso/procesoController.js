angular.module(appTeclo)
.controller("procesoController",
function($rootScope,$scope,$window,$translate,$timeout,growl,procesoService,procesoInfo,idord,idpro,encuestaService,nbOrden) {
    

    $scope.idOrdenServicio=idord;
	$scope.idProcesoActual=idpro;
	$scope.nombreOrden=nbOrden;
    $scope.numOrden = idord;
    $scope.encuestas=procesoInfo.data;
    $scope.formato = '0';
    $scope.tiempoTranscurridoText = "Sin Iniciar";
	$scope.mostraClave = false;
	
	

    $scope.numMaxImgPro = procesoInfo.data[0].idProceso.nuMaxImagenes;    
    $scope.listImagesPro = [];
    $scope.paramProcImg = new Object({
        idOrdenServ: idord,
        cdOrdenServicio: nbOrden,
        idProceso: procesoInfo.data[0].idProceso.idProceso
    });
    $scope.paramConfigImgPro = new Object({
        maxSizeMb: 1,
        title: "Agregar Evidencia por Proceso"
    });

    if(procesoInfo != null){
        $scope.nombreEtapa = "Orden de Servicio: " + nbOrden + " - Proceso: " + procesoInfo.data[0].idProceso.nbProceso;
        $scope.nombreProceso=procesoInfo.data[0].idProceso.nbProceso;
        $scope.dataEtapa = procesoInfo.data;
        $rootScope.nomSeguimiento = $scope.nombreEtapa;
        $scope.tiempoTranscurrido = 0;
        
        	let parcial=new Date();
            let finit = procesoInfo.data[0].fechaInicioProceso;
            let ffin = procesoInfo.data[0].fechaFinProceso;
            if(finit != null && ffin != null){
                $scope.tiempoTranscurrido += ffin - finit;
            }else if(finit != null)
            {
            	$scope.tiempoTranscurrido += parcial-finit;
            }
            for(let i = 0; i < procesoInfo.data.length; i++){
            if(procesoInfo.data[i].idEncuesta.cdEncuesta == "SAT02"|| procesoInfo.data[i].idEncuesta.cdEncuesta == "SAT01"){
                $scope.stActivarEncuesta = procesoInfo.data[i].stSatisfaccion;
            }

        }
    }else{
        growl.error('No se logró recuperar el  registro solicitado', {title: '-ERROR-'});
    }
    
      cambiaTiempo = function(tiempoTranscurridoFormato) {
    	    if(tiempoTranscurridoFormato)
    		{
    	  	  var ms = tiempoTranscurridoFormato % 1000;
    	  	tiempoTranscurridoFormato = (tiempoTranscurridoFormato - ms) / 1000;
    		  var secs = tiempoTranscurridoFormato % 60;
    		  tiempoTranscurridoFormato = (tiempoTranscurridoFormato - secs) / 60;
    		  var mins = tiempoTranscurridoFormato % 60;
    		  var hrs = (tiempoTranscurridoFormato - mins) / 60

        $scope.segundo = secs;
        $scope.minuto = mins;
        $scope.hora = hrs;
    		}
    };

    $scope.activarEncuesta = function(idEncuesta){
    	var mensajeSatsifaccion="";
        $scope.stActivarEncuesta = !$scope.stActivarEncuesta;
        if($scope.stActivarEncuesta)
        	mensajeSatsifaccion="La encuesta fue activada"
        	else
            mensajeSatsifaccion="La encuesta fue desactivada"
        	
        procesoService.activarEncuesta(parseInt(idEncuesta), idord, $scope.stActivarEncuesta).success(function(data){
            if(data){
                growl.success(mensajeSatsifaccion, {title: 'Encuesta'});
            }
        }).error(function(error){
            growl.error(error.message, {title: '- ERROR -'});
        });
    }
    
    obtenerPrimeraEncuestaPrimerProceso=function(EncuestasInfo)
    {
    	if(EncuestasInfo[0].idProceso.idProceso==encuestaService.primerProceso)
    		{
    		encuestaService.primerEncuestaPrimerProceso=EncuestasInfo[0].idEncuesta.idEncuesta;
    		}
    	encuestaService.primerEncuesta=EncuestasInfo[0].idEncuesta.idEncuesta;
    	
    }
    
    $scope.abrirModal = function(){
    	encuestaService.getClaveDiaria().success(function(data){
    		$scope.claveDiaria = data;
    		$scope.mostraClave = true;
    		$scope.error = false;
    	}).error(function(data){
    		$scope.claveDiaria = {}
    		$scope.error = data;
    	});
    };
    
    $scope.cerrarModal = function(){
    	$scope.mostraClave = false;
    };
    
    obtenerPrimeraEncuestaPrimerProceso(procesoInfo.data);
    cambiaTiempo($scope.tiempoTranscurrido);
    
});