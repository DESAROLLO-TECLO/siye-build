angular.module(appTeclo)
.controller("procesoController",
function($rootScope,$scope,$window,$translate,$timeout,growl,procesoService,procesoInfo,idord,idpro,encuestaService) {
    
    $rootScope.idProceso = procesoInfo.data[0].idProceso.idProceso;
    $scope.idOrdenServicio=idord;
	$scope.idProcesoActual=idpro;
    $scope.numOrden = $rootScope.numOS;
    $scope.encuestas=procesoInfo.data;
    $scope.formato = '0';
	
	
	

    $scope.numMaxImgPro = procesoInfo.data[0].idProceso.nuMaxImagenes;    
    $scope.listImagesPro = [];
    $scope.paramProcImg = new Object({
        idOrdenServ: $rootScope.idOrdenServ,
        cdOrdenServicio: $rootScope.cdOrdenServicio,
        idProceso: $rootScope.idProceso
    });
    $scope.paramConfigImgPro = new Object({
        maxSizeMb: 1,
        title: "Agregar Evidencia por Proceso"
    });

    if(procesoInfo != null){
        $scope.nombreEtapa = "Orden de Servicio: " + $rootScope.nomOrdenServicio + " - Proceso: " + procesoInfo.data[0].idProceso.nbProceso;
        $scope.dataEtapa = procesoInfo.data;
        $rootScope.nomSeguimiento = $scope.nombreEtapa;
        $scope.tiempoTranscurrido = 0;
        for(let i = 0; i < procesoInfo.data.length; i++){
            let finit = procesoInfo.data[i].idEncuesta.fechaInicioEncuesta;
            let ffin = procesoInfo.data[i].idEncuesta.fechaFinEncuesta;
            if(finit != null && ffin != null){
                $scope.tiempoTranscurrido += ffinzz - finit;
            }else{
                $scope.tiempoTranscurridoText = "Sin validar";
            }
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
        $scope.stActivarEncuesta = !$scope.stActivarEncuesta;
        procesoService.activarEncuesta(parseInt(idEncuesta), $rootScope.idOrSer, $scope.stActivarEncuesta).success(function(data){
            if(data){
                growl.success('La encuesta fue activada', {title: 'Encuesta Activada'});
            }else{
                growl.warning('La encuesta fue desactivada', {title: 'Encuesta Inactiva'});
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
    
    obtenerPrimeraEncuestaPrimerProceso(procesoInfo.data);
    cambiaTiempo($scope.tiempoTranscurrido);
    
});