angular.module(appTeclo)
.controller("procesoController",
function($rootScope,$scope,$window,$translate,$timeout,growl,procesoService,procesoInfo,idord,idpro) {
    
	$scope.idOrdenServicio=idord;
	$scope.idProcesoActual=idpro;
    $scope.stActivarEncuesta = procesoInfo.data[3].stSatisfaccion;
    $scope.tiempoTranscurrido = new Date();
    $scope.numOrden = $rootScope.numOS;

    if(procesoInfo != null){
        $scope.nombreEtapa = "Orden de Servicio: " + $rootScope.nomOrdenServicio + " - Proceso: " + procesoInfo.data[0].idProceso.nbProceso;
        $scope.dataEtapa = procesoInfo.data;
        console.log($scope.dataEtapa);
        $rootScope.nomSeguimiento = $scope.nombreEtapa;
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
    
});