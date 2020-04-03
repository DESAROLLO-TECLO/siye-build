angular.module(appTeclo)
.controller("procesoController",
function($rootScope,$scope,$window,$translate,$timeout,growl,procesoService,procesoInfo) {

    $scope.stActivarEncuesta = true;
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
        procesoService.activarEncuesta(idEncuesta, $rootScope.idOrSer, $scope.stActivarEncuesta).success(function(data){
            console.log(data);
            growl.success('contenido', {title: 'titulo'});
        }).error(function(error){
            growl.error(error.message, {title: '-ERROR-'});
            console.log(error)
        });
    }
    
});