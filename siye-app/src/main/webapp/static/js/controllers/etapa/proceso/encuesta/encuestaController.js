angular.module(appTeclo)
.controller("encuestaController",
function($rootScope,$scope,$window,$translate,$timeout,ModalService,encuestaInfo) {
    $scope.nombEncuesta = encuestaInfo.data.encuesta.nbEncuesta;
    $scope.nombSeccion = encuestaInfo.data.encuesta.secciones[0].nbSeccion;
    $scope.seccEncuesta = encuestaInfo.data.encuesta.secciones[0];
});