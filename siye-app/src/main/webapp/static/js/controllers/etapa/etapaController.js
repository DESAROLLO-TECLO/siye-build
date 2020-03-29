angular.module(appTeclo)
.controller("etapaController",
function($rootScope,$scope,$window,$translate,$timeout,ModalService, etapaService, etapaInfo) {

    $scope.fechaHoy = new Date();

    $scope.etapaInfo = new Object({
        id:etapaInfo[0]
    });
    console.log(etapaInfo);
    /*
    consultaPlan = function(){
        etapaService.getConsultaPlan(1).success(function(data){
            $scope.dataPlan = data;
            console.log(data);
        }).error(function(data){
			console.log(data);
		});
    }

    consultaPlan();
    */
});