angular.module(appTeclo).controller('monIncidenciaController', 
	function ($rootScope, $timeout, $scope, $location, $document, showAlert, growl, catalogoGenericoService, monIncidenciaService, ModalService) {
	//monIncidenciaService
	$scope.defaultValues = function(){
		$scope.flags = {
			showParametrosOS				: false,
			campoValorRequerido 			: false,
			labelValorRequerido 			: "Valor",
			mostrarTablaResultados			: false,
			mostrartablaResultadosCentros	: false,
			mostrartablaResultadosOS		: false,
			pantallaLTiempo					:false
		}
		$scope.orden=[];

		
		$scope.rangoFechas = {
			date: {
				startDate: moment().endOf('day'),
				endDate: moment().endOf('day')
			},
			options: {
				maxDate: moment().endOf("day"),
				locale: {
					applyLabel: "Aceptar",
					cancelLabel: 'Cancelar',
					customRangeLabel: 'PERSONALIZADO',
					format: 'D/MM/YYYY',
					separator: " - "
				},
				ranges: {}
			}
		};
		
		$scope.params = {
			fechaInicio			: moment().endOf('day').format('DD/MM/YYYY'),
			fechaFin			: moment().endOf('day').format('DD/MM/YYYY'),
			tipoBusqueda		: -1,
			valor				: "",
			opcion				: 1,
			idCentroInstalacion	: -1,
			nbCentroInstalacion : ""
		};
		
		$scope.paramsRespaldo = angular.copy($scope.params);
		$scope.paramsRespaldoModal = {};
		
		$scope.monIncidenciasVO = {
			datosTablaCentros	: [],
			respaldoConsulta	: [],
			nbCentroInstalacion	: "",
			idCentroInstalacion	: -1
		};
	}
	
	$scope.showAviso = function(messageTo, template, action) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/'+ template +'.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
			modal.close.then(function(){
				action();
			});
		});
	};
	
	//
	$scope.showDialogIncidencias = function(OrdenServicio){
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalIncidenciasMonitoreo.html',
			controller: 'modalIncidenciasMonitoreoController',
			scope: $scope,
			inputs: {OrdenServicio : OrdenServicio}
		}).then(function(modal) {
			modal.element.modal();
		});
	}
	
	$scope.limpiarCampos = function(){
		if($scope.params.opcion == 1){
			$scope.defaultValues();
		}else if($scope.params.opcion == 2){
			$scope.params = angular.copy($scope.paramsRespaldo);
			$scope.formBoxBusqueda.valor.$setPristine();
			$scope.rangoFechas.date.startDate = moment($scope.params.fechaInicio, "DD/MM/YYYY");
			$scope.rangoFechas.date.endDate = moment($scope.params.fechaFin, "DD/MM/YYYY");
		}
		
		//$scope.rangoFechas.date.startDate = moment("12/04/2020", "DD/MM/YYYY");
		//$scope.rangoFechas.date.endDate = moment("24/04/2020", "DD/MM/YYYY");
		
		$scope.consultaBtn();
	}
	
	// Se declaran tipos de busqueda
	$scope.listTipoBusqueda ={
		tipoBusqueda: [
			{idTipo:1, cdTipo:"ORDEN",	nbTipo:"Orden de Servicio"},
			{idTipo:2, cd:"PLACA", 		nbTipo:"Placa Vehicular"},
			{idTipo:3, cdTipo:"VIN",	nbTipo:"VIN"},
			{idTipo:4, cdTipo:"INCIDENCIA",	nbTipo:"INCIDENCIA"}
		]
	};
	
	$scope.cambioCombo = function(){
		if($scope.params.tipoBusqueda > 0){
			$scope.flags.campoValorRequerido = true;
			$scope.flags.labelValorRequerido = "*Valor";
		}else if($scope.params.tipoBusqueda == undefined || $scope.params.tipoBusqueda == null || $scope.params.tipoBusqueda < 1){
			$scope.params.tipoBusqueda = -1;
			$scope.flags.campoValorRequerido = false;
			$scope.flags.labelValorRequerido = "Valor";
			$scope.params.valor = "";
			$scope.formBoxBusqueda.valor.$setPristine();
		}
	}
	
	$scope.realizaValidaciones = function (){
		var respuesta = true;
		if($scope.rangoFechas.date.startDate == null || $scope.rangoFechas.date.endDate == null){
			$scope.rangoFechas.date.startDate = moment().endOf('day');
			$scope.rangoFechas.date.endDate = moment().endOf('day');
			$scope.params.fechaInicio = $scope.rangoFechas.date.startDate.format('DD/MM/YYYY');
			$scope.params.fechaFin = $scope.rangoFechas.date.endDate.format('DD/MM/YYYY');
			$scope.showAviso("El campo de rango de fechas no puede estar vacío.", "templateModalAviso");
			respuesta = false;
		}
		
		if($scope.params.tipoBusqueda > 0 && ($scope.params.valor == undefined || $scope.params.valor == "")){
			respuesta = false;
		}
		
		return respuesta;
	}
	
	requiredFields = function(){
		angular.forEach($scope.formBoxBusqueda.$error, function (field) {
			angular.forEach(field, function(errorField){
				errorField.$setDirty();
			})
		});
	}
	
	$scope.consultaMonIncidencias = function () {
		if($scope.realizaValidaciones() == false){
			requiredFields();
		}else{
			monIncidenciaService.getMonIncidencias(
				$scope.params
			).success(function (data) {
				//if(data.length > 0){
				$scope.flags.mostrarTablaResultados = true;
				$scope.monIncidenciasVO.datosTabla = data;
				if($scope.params.opcion == 1){
					$scope.flags.mostrartablaResultadosCentros = true;
					$scope.flags.mostrartablaResultadosOS = false;
					
					$scope.monIncidenciasVO.respaldoCentros = angular.copy(data);
				}else if($scope.params.opcion == 2){
					$scope.flags.mostrartablaResultadosCentros = false;
					$scope.flags.mostrartablaResultadosOS = true;
					
					$scope.monIncidenciasVO.respaldoOS = angular.copy(data);
					$scope.paramsRespaldoModal = angular.copy($scope.params);
				}
				$scope.paramsRespaldo = angular.copy($scope.params);
				//}
			}).error(function (data) {
				growl.error(data.message);
			});
		}
	};
	
	getRangoFechas = function () {
		catalogoGenericoService.getCatRangoFechas().success(function (data) {
			let rango = {};
			for (let x = 0; x < data.length; x++) {
				if (data[x].nbTipoFecha!= 'PERSONALIZADO') {
					rango[data[x].nbTipoFecha] = [data[x].fechaInicio, data[x].fechaFin];
				}
			}
			$scope.rangoFechas.options.ranges = rango;
			$scope.consultaMonIncidencias();
		}).error(function (data) {
			growl.error(data.message);
		});
	};
	
	$scope.consultaBtn = function(){
		$scope.params.fechaInicio = $scope.rangoFechas.date.startDate.format('DD/MM/YYYY');
		$scope.params.fechaFin = $scope.rangoFechas.date.endDate.format('DD/MM/YYYY');
		$scope.consultaMonIncidencias();
	}
	
	$scope.infoGral = function(){
		$scope.flags.showParametrosOS = false;
		$scope.flags.campoValorRequerido = false,
		$scope.flags.labelValorRequerido = "Valor",
		$scope.params.tipoBusqueda = -1;
		$scope.params.valor = "";
		$scope.formBoxBusqueda.valor.$setPristine();
		$scope.params.opcion = 1;
		$scope.params.nbCentroInstalacion = "";
		$scope.params.idCentroInstalacion = -1;
		$scope.params.fechaInicio = $scope.rangoFechas.date.startDate.format('DD/MM/YYYY');
		$scope.params.fechaFin = $scope.rangoFechas.date.endDate.format('DD/MM/YYYY');
		$scope.consultaMonIncidencias();
	}
	
	$scope.consultaDetalle = function(modulo){
		$scope.flags.showParametrosOS = true;
		$scope.params.tipoBusqueda = -1;
		$scope.params.valor = "";
		$scope.params.opcion = 2;
		$scope.params.nbCentroInstalacion = modulo.nbModulo;
		$scope.params.idCentroInstalacion = modulo.idCentroInstalacion;
		$scope.params.fechaInicio = $scope.rangoFechas.date.startDate.format('DD/MM/YYYY');
		$scope.params.fechaFin = $scope.rangoFechas.date.endDate.format('DD/MM/YYYY');
		$scope.consultaMonIncidencias();
	}
	//Consulta arbol de incidencia de orden<Proceso<Encuesta>>>
	$scope.showLineaTiempo=function(orden){
		let idOrden = orden.idOrdenServicio;
		let idPlan = orden.idPlan;
		if(idOrden == undefined){
			growl.warning("La incidencia no cuenta con una orden de servicio asociada");
			return;
		}
		monIncidenciaService.getDetalleIncidenciasOS(idOrden,idPlan).success(function(data) {
			if (data != null) {
				$scope.flags.pantallaLTiempo = true;
				$scope.orden = data;
				scrollDetail();
			}else{
				growl.warning("No se encontro informacion");
			}
		}).error(function(e) {
			$scope.flags.pantallaLTiempo=false;
			$scope.orden = [];
			growl.warning(e.message, { ttl: 5000 });
		});
	};
	
	$scope.regresar=function(){
		//$scope.consultaDetalle();
		$scope.flags.pantallaLTiempo = false;
		$scope.orden = [];
		scrollDetailDestroy();
	};
	
	//Funcion para destruir el slimscrol de la linea de tiempo(Muestra toda la informacion en columna)
	scrollDetailDestroy = function() {
		$('#scrollDetail').slimscroll("destroy");
		$('#scrollDetail').attr('style', '');
		$("#scrollDetail").slimScroll({destroy: true});
	};
	
	//Funcion para asignar scrol a la linea de tiempocon el plugin slim scroll
	scrollDetail = function() {
		$('#scrollDetail').slimScroll({
			height : '100%',
			color : '#00243c',
			opacity : .3,
			size : "4px",
			alwaysVisible : false
		});
	};
	
	$scope.descargarExcel = function() {
		let peticionReporteVO = new Object();
		peticionReporteVO.header = getCabeceras();
		peticionReporteVO.values = getContenido($scope.monIncidenciasVO.datosTabla);
		if($scope.paramsRespaldo.opcion == 1){
			peticionReporteVO.titulo = "Reporte de Monitoreo de Incidencias - Por Centros de Instalacion";
		}else if($scope.paramsRespaldo.opcion == 2){
			peticionReporteVO.titulo = "Reporte de Monitoreo de Incidencias - Por Ordenes de Servicio";
		}
		monIncidenciaService.descargarReporteExcel(peticionReporteVO).success(function(data, status, headers) {
			let filename = headers('filename');
			let file = new Blob([data], { type: 'application/vnd.ms-excel;base64,' });
			monIncidenciaService.downloadfile(file, filename);
		}).error(function(e) {
			if(e.descripcion != undefined){
				growl.error(e.descripcion,{ ttl: 5000 });
			}else if(e.message != undefined) {
				growl.error(e.message,{ ttl: 5000 });
			}else {showAlert.error('Falló la petición');}
		});
	};
	
	getCabeceras = () => {
		let headers = new Array();
		if($scope.paramsRespaldo.opcion == 1){
			headers.push("CENTRO DE INSTALACIÓN");
			headers.push("TOTAL ORDENES DE SERVICIO");
			headers.push("ORDENES DE SERVICIO PROGRAMADAS");
			headers.push("ORDENES DE SERVICIO NO PROGRAMADAS");
			headers.push("INCIDENCIAS");
		}else if($scope.paramsRespaldo.opcion == 2){
			headers.push("ORDEN SERVICIO");
			headers.push("CONCESION");
			headers.push("PLACA");
			headers.push("VIN");
			headers.push("PROCESO");
			headers.push("ETAPA");
			headers.push("INCIDENCIAS");
		}
		return headers;
	};
	
	getContenido = (list) => {
		let array = new Array();
		for (let i = 0; i < list.length; i++) {
			let elemento = new Array();
			if($scope.paramsRespaldo.opcion == 1){
				elemento.push(list[i].nbModulo);
				elemento.push(list[i].totalOrdenes);
				elemento.push(list[i].nuOrdenesProgramadas);
				elemento.push(list[i].nuOrdenesNoProgramadas);
				elemento.push(list[i].nuIncidencias);
			}else if($scope.paramsRespaldo.opcion == 2){
				elemento.push(list[i].cdOrdenServicio);
				elemento.push(list[i].nbConcesion);
				elemento.push(list[i].cdPlacaVehiculo);
				elemento.push(list[i].cdVIN);
				elemento.push(list[i].nbProceso);
				elemento.push(list[i].nbEncuesta);
				elemento.push(list[i].nuIncidenciasOS);
			}
			array.push(elemento);
		}
		return array;
	};
	
	$scope.defaultValues();
	getRangoFechas();
});