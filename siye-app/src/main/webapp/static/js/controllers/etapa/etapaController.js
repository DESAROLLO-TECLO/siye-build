angular.module(appTeclo)
.controller("etapaController",
function($rootScope,$scope,$window,$translate,$timeout, growl, etapaService, etapaInfo,encuestaService) {

    $rootScope.idOrdenServ = etapaInfo.data[0].idOrdenServicio;
    $rootScope.cdOrdenServicio = etapaInfo.data[0].cdOrdenServicio;
    $scope.finicio = etapaInfo.data[0].fhAtencionIni;
    $scope.ffin = etapaInfo.data[0].fhAtencionFin;
    $scope.fcita = etapaInfo.data[0].fhCita;
    $scope.idOS = $rootScope.idOrdenServ;
    $scope.formato = '0';
    $scope.tiempoTranscurridoText = "Sin Iniciar";

    
    	let parcial=new Date();
    	$scope.tiempoTranscurridoOrden=0;
        let finit =  etapaInfo.data[0].fhAtencionIni;
        let ffin =   etapaInfo.data[0].fhAtencionFin;
        if(finit != null && ffin != null){
            $scope.tiempoTranscurridoOrden += ffin - finit;
        }else if(finit != null)
        {
        	$scope.tiempoTranscurridoOrden += parcial-finit;
        }
    
    


    $scope.stValidarCheck = false;

    $scope.numMaxImg = etapaInfo.data[0].numMaxImagenes;
    $scope.listImages = [];
    $scope.paramEtapaImg = new Object({
        idOrdenServ: $rootScope.idOrdenServ,
        cdOrdenServicio: $rootScope.cdOrdenServicio
    });
    $scope.paramConfigImg = new Object({
        maxSizeMb: 1,
        title: "Agregar Evidencia por Orden de Servicio"
    });
    
    if(etapaInfo != null){
        $rootScope.idOrSer = parseInt(etapaInfo.data[0].idOrdenServicio);
        $rootScope.nomOrdenServicio = etapaInfo.data[0].cdOrdenServicio;
        $rootScope.numOS = etapaInfo.data[0].idOrdenServicio;
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
            encuestaService.primerProceso=$scope.dataPlan[0].proceso.idProceso;
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
    };
    
    cambiaTiempo = function(tiempoTranscurridoFormato) {
	    if(tiempoTranscurridoFormato)
		{
	  	  var ms = tiempoTranscurridoFormato % 1000;
	  	tiempoTranscurridoFormato = (tiempoTranscurridoFormato - ms) / 1000;
		  var secs = tiempoTranscurridoFormato % 60;
		  tiempoTranscurridoFormato = (tiempoTranscurridoFormato - secs) / 60;
		  var mins = tiempoTranscurridoFormato % 60;
		  var hrs = (tiempoTranscurridoFormato - mins) / 60

    $scope.segundo = secs;
    $scope.minuto = mins;
    $scope.hora = hrs;
		}
};

consultarTransportistas = function(){
	$scope.transportista=[];
    var idVeh = $scope.dataEtapa.vehiculo.idVehiculo;
    if(idVeh != null ){
        etapaService.getTransportistasVehiculo(idVeh).success(function(data){
            $scope.transportista = data;
            console.log(data);
        }).error(function(error){
            console.log(error);
            $scope.sintransportista = [];
        });
    }
};

consultarTransportistas();

    consultaPlan();
    cambiaTiempo($scope.tiempoTranscurridoOrden);

});