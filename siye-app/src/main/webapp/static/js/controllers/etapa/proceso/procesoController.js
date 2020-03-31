angular.module(appTeclo)
.controller("procesoController",
function($rootScope,$scope,$window,$translate,$timeout,ModalService,procesoService,procesoInfo) {

    console.log(procesoInfo);
    $scope.nombreEtapa = procesoInfo.data[0].idProceso.nbProceso;
    $scope.dataEtapa = procesoInfo.data;
    $scope.stActivarEncuesta = false;
    
});