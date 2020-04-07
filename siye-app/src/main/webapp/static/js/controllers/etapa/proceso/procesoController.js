angular.module(appTeclo)
.controller("procesoController",
function($rootScope,$scope,$window,$translate,$timeout,growl,procesoService,procesoInfo) {

    $scope.stActivarEncuesta = procesoInfo.data[3].stSatisfaccion;
    $scope.tiempoTranscurrido = new Date();
    $scope.numOrden = $rootScope.numOS;
    console.log(procesoInfo.data);

    if(procesoInfo != null){
        $scope.nombreEtapa = "Orden de Servicio: " + $rootScope.nomOrdenServicio + " - Proceso: " + procesoInfo.data[0].idProceso.nbProceso;
        $scope.dataEtapa = procesoInfo.data;
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