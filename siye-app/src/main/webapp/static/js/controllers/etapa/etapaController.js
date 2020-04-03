angular.module(appTeclo)
.controller("etapaController",
function($rootScope,$scope,$window,$translate,$timeout, growl, etapaService, etapaInfo) {

    $scope.fechaHoy = new Date();
    $scope.stValidarCheck = false;
    
    if(etapaInfo != null){
        $rootScope.idOrSer = parseInt(etapaInfo.data[0].idOrdenServicio);
        $rootScope.nomOrdenServicio = etapaInfo.data[0].cdOrdenServicio;
        $scope.dataEtapa = new Object({
            idOrdenServicio:etapaInfo.data[0].idOrdenServicio,
            cdOrdenServicio:etapaInfo.data[0].cdOrdenServicio,
            transportista:etapaInfo.data[0].transportista,
            vehiculo:{
                idVehiculo:etapaInfo.data[0].vehiculo.idVehiculo,
                cdPlacaVehiculo:etapaInfo.data[0].vehiculo.cdPlacaVehiculo,
                cdVin:etapaInfo.data[0].vehiculo.cdVin,
                cdTarjetaDeCirculacion:etapaInfo.data[0].vehiculo.cdTarjetaDeCirculacion,
                tipoVehiculo:etapaInfo.data[0].vehiculo.tipoVehiculo,
                consecionario:etapaInfo.data[0].vehiculo.consecionario
            },
            centroInstalacion:{
                idVehiculo:etapaInfo.data[0].centroInstalacion.idCentroInstalacion,
                cdCentroInstalacion:etapaInfo.data[0].centroInstalacion.cdCentroInstalacion,
                nbCentroInstalacion:etapaInfo.data[0].centroInstalacion.nbCentroInstalacion
            },
            kitInstalacion:{
                idKitInstalacion:etapaInfo.data[0].kitInstalacion.idKitInstalacion,
                cdKitInstalacion:etapaInfo.data[0].kitInstalacion.cdKitInstalacion
            },
            plan:{
                idPlan:etapaInfo.data[0].plan.idPlan,
                cdPlan:etapaInfo.data[0].plan.cdPlan,
                nbPlan:etapaInfo.data[0].plan.nbPlan,
                txPlan:etapaInfo.data[0].plan.txPlan,
                stActivo:etapaInfo.data[0].plan.stActivo
            }
        });
    }else{
        growl({ title: "-ERROR-", message: "No se logró recuperar el  registro solicitado" });
    }

    consultaPlan = function(){
        var idPlan = $scope.dataEtapa.plan.idPlan;
        var idOrden = $scope.dataEtapa.idOrdenServicio;
        etapaService.getPlan(idPlan, idOrden).success(function(data){
            $scope.dataPlan = data;
            var dplength = $scope.dataPlan.length;
            for(var i = 0; i < dplength; i++){
                switch($scope.dataPlan[i].proceso.cdProceso){
                    case 'INS':
                        $scope.dataPlan[i].urlImg = "static/dist/img/etapas/003-mechanic.png";
                        break;
                    case 'PLAT':
                        $scope.dataPlan[i].urlImg = "static/dist/img/etapas/002-null.png";
                        break;
                    case 'HOLO':
                        $scope.dataPlan[i].urlImg = "static/dist/img/etapas/001-qr-code.png";
                        break;
                    default:
                        $scope.dataPlan[i].urlImg = "static/dist/img/etapas/004-registration.png";
                        break;
                }
            }
        }).error(function(error){
            console.log(error);
        });
    }

    consultaPlan();

});