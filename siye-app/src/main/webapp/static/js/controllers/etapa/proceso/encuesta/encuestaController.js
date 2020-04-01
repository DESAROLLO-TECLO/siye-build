angular.module(appTeclo)
.controller("encuestaController",
function($rootScope,$scope,$window,$translate,$timeout,ModalService,encuestaInfo) {

    console.log("::::::::::::::::::::::::::::::::::");
    console.log(encuestaInfo.data.encuesta.nbEncuesta);
    console.log("::::::::::::::::::::::::::::::::::");
    console.log(encuestaInfo.data.encuesta.secciones);
    console.log("::::::::::::::::::::::::::::::::::");
    
    $scope.nombEncuesta = encuestaInfo.data.encuesta.nbEncuesta;
    $scope.nombSeccion = encuestaInfo.data.encuesta.secciones[0].nbSeccion;
    $scope.seccEncuesta = encuestaInfo.data.encuesta.secciones[0];
});