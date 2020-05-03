angular.module(appTeclo).controller('consultaGeneralController', 
function($scope, showAlert, $location, growl, consultaGeneralService,encuestaService,catalogoGenericoService) {
	
	$scope.parametrosBusqueda = 
	{busquedaAvanzada:false,cdTipoBusqueda:null,valor:null,
	fhInicio:null,fhFin:null,centroInstalacion:null,
	estatusSeguimiento:null,isLote:false,isIncidencia:false,
	valorLoteIncidencia:null,tipoKit:null,tipoPlan:null};
	
	
	$scope.catalogoParamBusqueda = 
		[
		 {"idOpcion" : 1, "codigo" : 'TODO', "nbOpcion" : 'Todo'},
		 {"idOpcion" : 2, "codigo" : 'ORDEN_SERVICIO', "nbOpcion" : 'Orden de Servicio'},
		 {"idOpcion" : 3, "codigo" : 'PLACA', "nbOpcion" : 'Placa'},
		 {"idOpcion" : 4, "codigo" : 'VIN', "nbOpcion" : 'VIN'},
		 {"idOpcion" : 5, "codigo" : 'LOTE', "nbOpcion" : 'Lote'},
		 {"idOpcion" : 6, "codigo" : 'INCIDENCIA', "nbOpcion" : 'Incidencia'}
		 ];
	
	$scope.catalogoParamBusqueda2 = 
		[
		 {"idOpcion" : 1, "codigo" : 'TODO', "nbOpcion" : 'Todo'},
		 {"idOpcion" : 2, "codigo" : 'ORDEN_SERVICIO', "nbOpcion" : 'Orden de Servicio'},
		 {"idOpcion" : 3, "codigo" : 'PLACA', "nbOpcion" : 'Placa'},
		 {"idOpcion" : 4, "codigo" : 'VIN', "nbOpcion" : 'VIN'}
		 ];
	
	iniciarValores=function()
	{
		$scope.paramBusqueda="TODO";
		$scope.valorBusqueda="";
		$scope.busquedaAvanzada=false;	
		getRangoFechas();
		getCentroIstalacion();
		getStSeguimiento();
		getTipoKit();
		getPlan();
		$scope.cargarTramitesDia();
		$scope.FHInicio="";
		$scope.FHFin="";
		$scope.checkTipoBusqudaFecha=true;
	}
	
	$scope.cambioComboParametro = function(op){
		if(op!= "TODO")
			{
			$scope.valorRequerido = true;
		    $scope.bloquearValorBusquedaTodo=false;
			}
		else
			{
			$scope.valorRequerido = false;
		    $scope.valorBusqueda="";
		    $scope.bloquearValorBusquedaTodo=true;
			}
	};
	
	$scope.cambioBusquedaAvanzada=function()
	{
		if($scope.busquedaAvanzada)
			{
			$scope.paramBusqueda="TODO";
			$scope.valorBusqueda="";
			$scope.tipoFecha=1;
			$scope.centroInst=[];
			$scope.statusSegumiento=[]
			$scope.tipoKit=[];
			$scope.tipoPlan=[];
			$scope.FHInicio="";
			$scope.FHFin="";
			$scope.checkTipoBusqudaFecha
			}
		else
		{
	    $scope.paramBusqueda="TODO";
	    $scope.cambioComboParametro($scope.paramBusqueda);
       
		}
	};
	
	
	
	$scope.busquedaTramitesParametros = function(){
		if($scope.consultaOrden.$invalid){
			requiredFields()
		}else{
			validarParametros();	
			consultaGeneralService.busquedaTramitesParametros($scope.parametrosBusqueda).success(function(data) {
						if(data.length >= $scope.cantidadRegistrosMostrar){
							growl.info("Su consulta supera los "+$scope.cantidadRegistrosMostrar+" registros. En caso de requerir el reporte completo, solicítelo a soporte.", 'templateModalAviso');
						}
						$scope.listOrden = data;
						consultaGeneralService.parametrosBusquedaAnterior=$scope.parametrosBusqueda;
						$scope.mostrarTabla=true;
						
				}).error(function(data){
					$scope.listOrden = {};
					growl.warning(data.message);
					$scope.mostrarTabla=false;
				});
			
			 
	
			
		}
		
	};
	
	validarParametros=function()
	{
	$scope.parametrosBusqueda.cdTipoBusqueda=$scope.paramBusqueda;
	$scope.parametrosBusqueda.valor=$scope.valorBusqueda?$scope.valorBusqueda:"";
	$scope.parametrosBusqueda.busquedaAvanzada=$scope.busquedaAvanzada;	
	if(!$scope.busquedaAvanzada)
	{
		$scope.parametrosBusqueda.fhInicio=null;
		  $scope.parametrosBusqueda.fhFin=null;
		  $scope.parametrosBusqueda.centroInstalacion=null;
		  $scope.parametrosBusqueda.estatusSeguimiento=null;
		  $scope.parametrosBusqueda.isLote=false;
		  $scope.parametrosBusqueda.isIncidencia=false;
		  $scope.parametrosBusqueda.valorLoteIncidencia=null;
		  $scope.parametrosBusqueda.tipoKit=null;
		  $scope.parametrosBusqueda.tipoPlan=null;
		  
	}else
		{
		if($scope.checkTipoBusqudaFecha==true)
		{
		$scope.parametrosBusqueda.fhInicio=$scope.tipoFecha.fechaInicio; 
    	$scope.parametrosBusqueda.fhFin=$scope.tipoFecha.fechaFin;
		}
	else
		{
		$scope.parametrosBusqueda.fhInicio=$scope.FHInicio;
		$scope.parametrosBusqueda.fhFin=$scope.FHFin;
		}
		}
	};
	
	requiredFields = function(){
		angular.forEach($scope.consultaOrden.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		})
	};
	
	$scope.getNumRegistrosMaximos=function(cdParametro){
		encuestaService.getNumPreguntasPorSeccion(cdParametro).success(function(data) {
		$scope.cantidadRegistrosMostrar=parseInt(data.cdValorPConfig);
		}).error(function(data) {
		  growl.warning(data.message);
		});
	};
	
	$scope.cargarTramitesDia = function(){	
			consultaGeneralService.cargarTramitesDia().success(function(data) {
						if(data.length >= $scope.cantidadRegistrosMostrar){
							growl.info("Su consulta supera los "+$scope.cantidadRegistrosMostrar+" registros. En caso de requerir el reporte completo, solicítelo a soporte.", 'templateModalAviso');
						}
						$scope.listOrden = data;
						$scope.mostrarTabla=true;
						
				}).error(function(data){
					$scope.listOrden = {};
					growl.warning(data.message);
					$scope.mostrarTabla=false;
				});
			
	};
	
    getRangoFechas = function () {
    	var arrayNuevo=[];
        catalogoGenericoService.getCatRangoFechas().success(function (data) {
            for (let x = 0; x < data.length; x++) {
                if (data[x].nbTipoFecha!= 'PERSONALIZADO') {
                arrayNuevo.push(data[x])
                }
            }
            $scope.rangoFechas=arrayNuevo
            $scope.tipoFecha=1;

        }).error(function (data) {
            growl.error(data.message);
        });
    };
    
    getCentroIstalacion = function () {
    	$scope.centroInst=[];
    	consultaGeneralService.getCentroInstalacion("TODO","").success(function (data) {
            $scope.catCentroInstalacion=data
        }).error(function (data) {
            growl.error(data.message);
        });
    };
    
    getStSeguimiento = function () {
    	$scope.statusSegumiento=[]
    	consultaGeneralService.getStSeguimiento(1).success(function (data) {
            $scope.catStSeguimiento=data
        }).error(function (data) {
            growl.error(data.message);
        });
    };
    
    getTipoKit = function () {
    	$scope.tipoKit=[];
    	consultaGeneralService.getTipoKit().success(function (data) {
            $scope.catTipoKit=data
        }).error(function (data) {
            growl.error(data.message);
        });
    };
    
    getPlan = function () {
    	$scope.tipoPlan=[];
    	consultaGeneralService.getPlan().success(function (data) {
            $scope.catPlan=data
        }).error(function (data) {
            growl.error(data.message);
        });
    };
    
	$scope.limpiarCampos=function()
	{
		iniciarValores();
	}


	$scope.getNumRegistrosMaximos('NUM_MAX_REGISTROS_MOSTRAR');
	iniciarValores();


});
