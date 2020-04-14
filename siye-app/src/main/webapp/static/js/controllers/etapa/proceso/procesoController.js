angular.module(appTeclo)
.controller("procesoController",
function($rootScope,$scope,$window,$translate,$timeout,growl,procesoService,procesoInfo,idord,idpro,encuestaService) {
    
	$scope.idOrdenServicio=idord;
	$scope.idProcesoActual=idpro;
    $scope.stActivarEncuesta = procesoInfo.data[3].stSatisfaccion;
    $scope.numOrden = $rootScope.numOS;

    if(procesoInfo != null){
        $scope.nombreEtapa = "Orden de Servicio: " + $rootScope.nomOrdenServicio + " - Proceso: " + procesoInfo.data[0].idProceso.nbProceso;
        $scope.dataEtapa = procesoInfo.data;
        $rootScope.nomSeguimiento = $scope.nombreEtapa;
        $scope.tiempoTranscurrido = 0;
        for(let i = 0; i < procesoInfo.data.length; i++){
            let finit = procesoInfo.data[i].idEncuesta.fechaInicioEncuesta;
            let ffin = procesoInfo.data[i].idEncuesta.fechaFinEncuesta;
            if(finit != null && ffin != null){
                $scope.tiempoTranscurrido += ffin - finit;
            }else{
                $scope.tiempoTranscurridoText = "Sin validar";
            }
        }
    }else{
        growl.error('No se logró recuperar el  registro solicitado', {title: '-ERROR-'});
    }

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
    	
    }
    
    obtenerPrimeraEncuestaPrimerProceso(procesoInfo.data);
    
});