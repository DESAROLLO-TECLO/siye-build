angular.module(appTeclo)
.controller("procesoController",
function($rootScope,$scope,$window,$translate,$timeout,growl,procesoService,procesoInfo) {

    $scope.stActivarEncuesta = false;
    $scope.tiempoTranscurrido = new Date();

    if(procesoInfo != null){
        $scope.nombreEtapa = procesoInfo.data[0].idProceso.nbProceso;
        $scope.dataEtapa = procesoInfo.data;
    }else{
        growl({ title: "-ERROR-", message: "No se logró recuperar el  registro solicitado" });
    }
    
});