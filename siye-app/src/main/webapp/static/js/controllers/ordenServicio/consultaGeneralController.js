angular.module(appTeclo).controller('consultaGeneralController', 
function($scope, showAlert, $location, growl, consultaGeneralService,encuestaService,catalogoGenericoService,deDetalle) {
	
	$scope.parametrosBusqueda = 
	{busquedaAvanzada:false,cdTipoBusqueda:null,valor:null,
	fhInicio:null,fhFin:null,centroInstalacion:null,
	estatusSeguimiento:null,isLote:false,isIncidencia:false,
	valorLoteIncidencia:null,tipoKit:null,tipoPlan:null};
	$scope.listOrden=[];
	
    $scope.tabla = new Object({
        order: '',
        reverse: false,
        paginacion: [5, 10, 20, 50],
        cantidadPaginacion: 5,
        cantidadPaginacionVal: 5,
        view: new Object({
            filterSearchVal: '',
            rowsPerPageVal: 0
        })
    });
	
	
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
		$scope.isIncidencia=false;
		$scope.isLote=false;
		getRangoFechas();
		getCentroIstalacion();
		getStSeguimiento();
		getTipoKit();
		getPlan();
		if(!deDetalle)
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
		if(!$scope.busquedaAvanzada)
			{
			$scope.paramBusqueda="TODO";
			$scope.cambioComboParametro($scope.paramBusqueda);
			$scope.checkTipoBusqudaFecha=true;
			$scope.valorBusqueda="";
			$scope.tipoFecha=$scope.rangoFechas[0];
			$scope.centroInst=[];
			$scope.statusSegumiento=[]
			$scope.tipoKit=[];
			$scope.tipoPlan=[];
			$scope.isLote=false;
			$scope.isIncidencia=false;
			$scope.valorOrigen="";
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
			$scope.listOrden = [];
			$scope.mostrarTabla=false;
			validarParametros();	
			consultaGeneralService.busquedaTramitesParametros($scope.parametrosBusqueda).success(function(data) {
						if(data.length >= $scope.cantidadRegistrosMostrar){
							growl.info("Su consulta supera los "+$scope.cantidadRegistrosMostrar+" registros. En caso de requerir el reporte completo, solicítelo a soporte.", 'templateModalAviso');
						}
						consultaGeneralService.parametrosBusquedaAnterior=$scope.parametrosBusqueda;
						$scope.mostrarTabla=true;
						if($scope.parametrosBusqueda.tipoKit && $scope.parametrosBusqueda.tipoKit.length>0)
						{ 
                           var nuevoArreglo=[];
                           var tamaño=0;
                           nuevoArreglo.push(data[0]);
				            for (let x = 1; x < data.length; x++) {
				            	tamaño=nuevoArreglo.length;
				                if (nuevoArreglo[tamaño-1].cdOrdenServicio!=data[x].cdOrdenServicio) {
				                nuevoArreglo.push(data[x])
				                }
				            }
			                $scope.listOrden = nuevoArreglo;
						}
						else
						$scope.listOrden = data;

						
				}).error(function(data){
					$scope.listOrden = [];
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
		  $scope.parametrosBusqueda.centroInstalacion=$scope.centroInst?$scope.centroInst:null;
		  $scope.parametrosBusqueda.estatusSeguimiento=$scope.statusSegumiento?$scope.statusSegumiento:null;
		  $scope.parametrosBusqueda.isLote=$scope.isLote?$scope.isLote:false;
		  $scope.parametrosBusqueda.isIncidencia=$scope.isIncidencia?$scope.isIncidencia:false;
		  $scope.parametrosBusqueda.valorLoteIncidencia=$scope.valorOrigen?$scope.valorOrigen:null;
		  $scope.parametrosBusqueda.tipoKit=$scope.tipoKit?$scope.tipoKit:null;
		  $scope.parametrosBusqueda.tipoPlan=$scope.tipoPlan?$scope.tipoPlan:null;
		
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
		    $scope.listOrden = [];
		    $scope.mostrarTabla=false;
			consultaGeneralService.cargarTramitesDia().success(function(data) {
						if(data.length >= $scope.cantidadRegistrosMostrar){
							growl.info("Su consulta supera los "+$scope.cantidadRegistrosMostrar+" registros. En caso de requerir el reporte completo, solicítelo a soporte.", 'templateModalAviso');
						}
						$scope.listOrden = data;
						$scope.mostrarTabla=true;
						
				}).error(function(data){
					$scope.listOrden = [];
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
            $scope.tipoFecha=$scope.rangoFechas[0];

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
		$scope.consultaOrden.$setPristine();
	}
	
	$scope.changeRangoFechas=function()
	{
		if(!$scope.checkTipoBusqudaFecha)
			{
			$scope.tipoFecha=$scope.rangoFechas[0];
			$scope.FHInicio="";
			$scope.FHFin="";
			}
		else
			{
			$scope.tipoFecha=$scope.rangoFechas[0];
			$scope.FHInicio="";
			$scope.FHFin="";
			}
	}
	
	$scope.changeOrigen=function()
	{
		$scope.valorOrigen="";
		if($scope.isLote)
			$scope.isIncidencia=false;

	
	}
	
	$scope.changeOrigen2=function()
	{
		$scope.valorOrigen="";
		if($scope.isIncidencia)
			$scope.isLote=false;
	}
	
    //  metodo para generarReporteExcel
    $scope.descargarExcel = function() {
        let peticionReporteVO = new Object();
        peticionReporteVO.header = getCabeceras();
        peticionReporteVO.values = getContenido($scope.listOrden);
        peticionReporteVO.titulo = "Reporte Orden de Servicio";
        consultaGeneralService.descargarReporteExcel(peticionReporteVO).success(function(data, status, headers) {
            let filename = headers('filename');
            let file = new Blob([data], { type: 'application/vnd.ms-excel;base64,' });
            consultaGeneralService.downloadfile(file, filename);
        }).error(function(data) {
            mostrarAviso(data);
        });
    };
    
    getCabeceras = () => {
        let headers = new Array();
        headers.push("ESTATUS");
        headers.push("ORDEN DE SERVICIO");
        headers.push("FECHA DE CITA");
        headers.push("PLACA");
        headers.push("TIPO DE VEHÍCULO");
        headers.push("TIPO DE KIT");
        headers.push("CENTRO DE INSTALACIÓN");
        headers.push("TIPO DE PLAN");
        headers.push("ORIGEN");
        headers.push("PROCESO ACTUAL");
        return headers;
    };
    
    getContenido = (list) => {
        let array = new Array();
        for (let i = 0; i < list.length; i++) {
            let elemento = new Array();
            elemento.push(list[i].stSeguimiento.nbStSeguimiento);
            elemento.push(list[i].cdOrdenServicio);
            elemento.push(list[i].fhCita);
            elemento.push(list[i].vehiculo.cdPlacaVehiculo);
            elemento.push(list[i].vehiculo.tipoVehiculo.nbTipoVehiculo);
            elemento.push(list[i].kitInstalacion!=null && 
            		
            		list[i].kitInstalacion.kitInstalacionDispDTO.length>=1?list[i].kitInstalacion.kitInstalacionDispDTO[0].kitDispositivo.tipoKit.nbTipoKit:"SIN KIT ASIGNADO");
            elemento.push(list[i].centroInstalacion.nbCentroInstalacion);
            elemento.push(list[i].plan.nbPlan);
            if(list[i].idOrigenOds==1 || list[i].idOrigenOds==null)
            elemento.push(list[i].loteOrdenServicio!=null?"Lote: "+list[i].loteOrdenServicio.nbArchivoFinal:"Lote: ");	
            if(list[i].idOrigenOds==2)
            elemento.push("Incidencia: "+list[i].cdOrdenServicio);	
            elemento.push(list[i].proceso == null ? "SIN PROCESO" : list[i].proceso.nbProceso);
            array.push(elemento);
        }
        return array;
    };
    
    mostrarAviso = (data) => {
        if (data != undefined && data.status === 404) {
        	growl.error(data.message, { ttl: 5000 });
        } else if (data != undefined && data.status === 400) {
        	growl.error(data.message, { ttl: 5000 });
        }
         else {
            growl.error('Ocurrió un error en la operación', { ttl: 5000 });
        }
    };
    
	$scope.redirigir = function(id_orden){
		$location.path('/detalleConsulta/'+id_orden);
			
	
	};
	
	if(deDetalle)
	{
		deDetalle=false;
		$scope.listOrden=[];
		consultaGeneralService.busquedaTramitesParametros(consultaGeneralService.parametrosBusquedaAnterior).success(function(data) {
		if(data.length >= $scope.cantidadRegistrosMostrar){
			growl.info("Su consulta supera los "+$scope.cantidadRegistrosMostrar+" registros. En caso de requerir el reporte completo, solicítelo a soporte.", 'templateModalAviso');
		}
		$scope.mostrarTabla=true;
		if(consultaGeneralService.parametrosBusquedaAnterior.tipoKit && consultaGeneralService.parametrosBusquedaAnterior.tipoKit.length>0)
		{ 
			
           var nuevoArreglo=[];
           var tamaño=0;
           nuevoArreglo.push(data[0]);
            for (let x = 1; x < data.length; x++) {
            	tamaño=nuevoArreglo.length;
                if (nuevoArreglo[tamaño-1].cdOrdenServicio!=data[x].cdOrdenServicio) {
                nuevoArreglo.push(data[x])
                }
            }
            $scope.listOrden = nuevoArreglo;
		}
		else
		$scope.listOrden = data;
	}).error(function(data){
		$scope.listOrden = [];
		growl.warning(data.message);
		$scope.mostrarTabla=false;
	});
	}



	$scope.getNumRegistrosMaximos('NUM_MAX_REGISTROS_MOSTRAR');
	iniciarValores();


});
