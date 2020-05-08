angular.module(appTeclo).config(function (LightboxProvider) {
    LightboxProvider.fullScreenMode = true;
});
angular.module(appTeclo).controller('seguimientoOsController', function ($rootScope,  $route,$scope, $location, $document, showAlert, growl, catalogoGenericoService,
    seguimientoOsService,detalleSeguimientoOsService, lineaTiempoVO, ModalService) {

    $scope.view = new Object({
        rowsPerPage:'5'
    });

    $scope.catTipoBusqueda = new Array(
        { idTipoBusqueda: 1, cdTipoBusqueda: "EN_CURSO", txTipoBusqueda: "EN CURSO" },
        { idTipoBusqueda: 2, cdTipoBusqueda: "COMPLETADAS", txTipoBusqueda: "COMPLETADAS" },
        { idTipoBusqueda: 3, cdTipoBusqueda: "PROGRAMADA", txTipoBusqueda: "PROGRAMADAS" },
        { idTipoBusqueda: 4, cdTipoBusqueda: "NO_PROGRAMADA", txTipoBusqueda: "NO PROGRAMADAS" },
        { idTipoBusqueda: 5, cdTipoBusqueda: "INCIDENCIAS", txTipoBusqueda: "INCIDENCIAS" }
    );

    $scope.verColumna = {
        col1: true,
        col2: true,
        col3: true,
        col4: true,
        col5: true
    };

    $scope.tamColumna = 'col-md-2';

    $scope.evidenciaVO = new Object({
        data:{},
        verEvidencia:false,
        folioOS:""
    });

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

    $scope.seguimientoVO = new Object({
        busquedaOk: false,
        verDetalleOS:false,
        tablaResultados:false,
        datosTabla:[],
        nbCentroInstalacion:""
    });

    $scope.params = {
        colSeleccionada: [],
        columnas: [],
        colOmitidas: [],
        opcMarcadas:"",
        fechaInicio: moment().endOf('day').format('DD/MM/YYYY'),
        fechaFin: moment().endOf('day').format('DD/MM/YYYY'),
        fechaI:null,
        fechaF:null
    };

    starController = function(){
        if(lineaTiempoVO!=null){
            $scope.rangoFechas.options.ranges = lineaTiempoVO.rangoFechas; 
            verColumnas(lineaTiempoVO.formBusuqeda.colSeleccionada);
            $scope.rangoFechas.date.startDate = lineaTiempoVO.formBusuqeda.fechaI;
            $scope.rangoFechas.date.endDate = lineaTiempoVO.formBusuqeda.fechaF;  
            $scope.seguimientoVO.tablaResultados = false;
            $scope.seguimientoVO.verDetalleOS = true;
            $scope.seguimientoVO.busquedaOk = true;
            $scope.view.rowsPerPage='5';
            $scope.view.searchSomething='';
            $scope.seguimientoVO.datosTabla=[];
            $scope.seguimientoVO.datosTabla = lineaTiempoVO.listaOs;
            $scope.seguimientoVO.nbCentroInstalacion = lineaTiempoVO.centroInstalacion;
            $scope.seguimientoVO.respaldo= angular.copy(lineaTiempoVO.tablaTotales);          
        }else{
            getRangoFechas();
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
            consultaInicial();
        }).error(function (data) {
            growl.error(data.message);
        });
    };

    consultaInicial = function () {
        verColumnas($scope.catTipoBusqueda)
        seguimientoOsService.getInfoOsByRangoFechas($scope.params).success(function (data) {
            $scope.seguimientoVO.tablaResultados = true;
            $scope.seguimientoVO.busquedaOk = true;
            $scope.seguimientoVO.respaldo = angular.copy(data);
            $scope.seguimientoVO.datosTabla = data;
        }).error(function (data) {
            growl.error(data.message);
        });
    };

    $scope.consultaOS = function (form, params) {
        if (validarFormulario(form)) {
            $scope.seguimientoVO.busquedaOk = false;
            $scope.seguimientoVO.verDetalleOS = false;
            $scope.evidenciaVO.verEvidencia = false;
            $scope.view.searchSomething='';
            $scope.params.opcMarcadas='';
            verColumnas(params.colSeleccionada);
            seguimientoOsService.getInfoOsByRangoFechas($scope.params).success(function (data) {
                $scope.seguimientoVO.tablaResultados = true;
                $scope.seguimientoVO.busquedaOk = true;
                $scope.seguimientoVO.respaldo = angular.copy(data);
                $scope.seguimientoVO.datosTabla = data;
            }).error(function (data) {
                growl.error(data.message);
            });
        }
    };

    $scope.verDetalle = function(centroInstalacion){
        if(centroInstalacion.detalleOrdenServicio!=null){
            if(centroInstalacion.detalleOrdenServicio.length>0){
                $scope.seguimientoVO.tablaResultados = false;
                $scope.seguimientoVO.verDetalleOS = true;
                $scope.view.rowsPerPage='5';
                $scope.view.searchSomething='';
                $scope.seguimientoVO.datosTabla=[];
                $scope.seguimientoVO.datosTabla = centroInstalacion.detalleOrdenServicio;
                $scope.seguimientoVO.nbCentroInstalacion = centroInstalacion.nbModulo;
                $scope.seguimientoVO.idCentroInstalacion = centroInstalacion.idCentroInstalacion;
            }
        }
    };

    $scope.infoGral = function(tipo){
        if(tipo==='incidencia'){
            $scope.evidenciaVO.verEvidencia = false;
            $scope.seguimientoVO.busquedaOk= true;
            $scope.seguimientoVO.verDetalleOS = true;
        }else{
            if($scope.seguimientoVO.respaldo!=undefined){
            $scope.seguimientoVO.verDetalleOS = false;
            $scope.seguimientoVO.tablaResultados = true;
            $scope.view.rowsPerPage='5';
            $scope.view.searchSomething='';
            $scope.seguimientoVO.datosTabla=$scope.seguimientoVO.respaldo;
        }
     }
    };

    $scope.verDetalleStatus = function(ordenservicio){
        if(ordenservicio!=undefined){
            let pasoVentana = new Object({
                formBusuqeda:$scope.params,
                tablaTotales:$scope.seguimientoVO.respaldo,
                listaOs:$scope.seguimientoVO.datosTabla,
                dtOs:ordenservicio,
                centroInstalacion:$scope.seguimientoVO.respaldo[0].nbModulo,
                rangoFechas:$scope.rangoFechas.options.ranges,
                idCentroInstalacion:$scope.seguimientoVO.idCentroInstalacion
            });
            detalleSeguimientoOsService.saveconsultaGeneral(pasoVentana);
            $location.path('/detSegimientoOS');
        }
    };

    $scope.verDetalleIncidencia = function(os){
        if(os!=undefined){
            seguimientoOsService.getDetalleIncidencias(os.idOrdenServicio).success(function(data){
                $scope.evidenciaVO.data = data;
                $scope.evidenciaVO.folioOS = os.nuOrdenServicio;
                $scope.evidenciaVO.verEvidencia = true;
                $scope.seguimientoVO.busquedaOk = false;
                $scope.seguimientoVO.verDetalleOS = false;
            }).error(function(data){
                growl.error(data.message)
            })
        }
    };

    $scope.verImagen = function(incidencia){
        if(incidencia!= undefined){
            $scope.paramsRespaldoModal = new Object({
                fechaInicio: $scope.params.fechaInicio,
                fechaFin: $scope.params.fechaFin,
                idCentroInstalacion: $scope.seguimientoVO.idCentroInstalacion
            });
            ModalService.showModal({
                templateUrl: 'views/templatemodal/templateModalIncidenciasMonitoreo.html',
                controller: 'modalIncidenciasMonitoreoController',
                scope: $scope,
                inputs: {OrdenServicio :incidencia}
            }).then(function(modal) {
                modal.element.modal();
            });
        }
    };

    $scope.corteDiario = function(){
        let fecha =  moment().endOf('day').format('DD/MM/YYYY');
        $scope.showConfirmacion("¿Desea Realizar el Corte del dia "+ fecha +" ?", function () {             
            seguimientoOsService.hacerCorteDiario(fecha).success(function(data){
                growl.success(data.message);
            }).error(function(data){
                growl.info(data.message);
            });
        });
    };

    //Genericas 
    validarFormulario = function (formulario) {
        if (formulario.$invalid) {
            showAlert.requiredFields(formulario);
            growl.error('Campos requeridos');
            return false;
        } else {
            return true;
        }
    };

    verColumnas = function (columnas) {
        const CATAMANO = columnas.length;
        const catColumnas = $scope.catTipoBusqueda.length;
        $scope.params.columnas = [];
        $scope.params.colSeleccionada = [];
        $scope.params.colOmitidas = [];
        $scope.tamColumna = 'col-md-' + 12 / (CATAMANO + 1);
        $scope.tamColumna = $scope.tamColumna +" col-sm-"+ 12 / (CATAMANO + 1);
        $scope.tamColumna = $scope.tamColumna +" col-xs-"+ 12 / (CATAMANO + 1);

        // Obtener las fechas del componente 
        $scope.params.fechaInicio = $scope.rangoFechas.date.startDate.format('DD/MM/YYYY');
        $scope.params.fechaFin = $scope.rangoFechas.date.endDate.format('DD/MM/YYYY');
        $scope.params.fechaI =  $scope.rangoFechas.date.startDate;
        $scope.params.fechaF =  $scope.rangoFechas.date.endDate;

        for (let x = 0; x < CATAMANO; x++) {
            $scope.params.columnas.push($scope.catTipoBusqueda[x].cdTipoBusqueda);
            $scope.params.colSeleccionada.push($scope.catTipoBusqueda[x]);
            $scope.params.opcMarcadas += $scope.catTipoBusqueda[x].txTipoBusqueda;
            if(x+1 < CATAMANO){
                $scope.params.opcMarcadas =  $scope.params.opcMarcadas +", ";
            }
        }

        if (CATAMANO < catColumnas) {
            for (let x = 0; x < catColumnas; x++) {
                let noExiste = true;
                for (let y = 0; y < CATAMANO; y++) {
                    if ($scope.catTipoBusqueda[x].cdTipoBusqueda === $scope.params.colSeleccionada[y].cdTipoBusqueda) {
                        if ($scope.catTipoBusqueda[x].idTipoBusqueda === 1) {
                            $scope.verColumna.col1 = true;
                        } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 2) {
                            $scope.verColumna.col2 = true;
                        } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 3) {
                            $scope.verColumna.col3 = true;
                        } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 4) {
                            $scope.verColumna.col4 = true;
                        } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 5) {
                            $scope.verColumna.col5 = true;
                        }
                        noExiste = false;
                        break;
                    }
                }
                if (noExiste) {
                    $scope.params.colOmitidas.push($scope.catTipoBusqueda[x].cdTipoBusqueda);
                    if ($scope.catTipoBusqueda[x].idTipoBusqueda === 1) {
                        $scope.verColumna.col1 = false;
                    } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 2) {
                        $scope.verColumna.col2 = false;
                    } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 3) {
                        $scope.verColumna.col3 = false;
                    } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 4) {
                        $scope.verColumna.col4 = false;
                    } else if ($scope.catTipoBusqueda[x].idTipoBusqueda === 5) {
                        $scope.verColumna.col5 = false;
                    }
                }
            }
        }else{
            $scope.verColumna = {
                col1: true,
                col2: true,
                col3: true,
                col4: true,
                col5: true
            }; 
        }
    };

    $scope.descargaExcel = function(){
        let parametros = new Object({
            nivel:"",
            nivelGeneral:null,
            nivelDetalle:null,
            nivelIncidencia:null,
            columnas:$scope.params.opcMarcadas,
            fechaInicio: $scope.params.fechaInicio,
            fechaFin: $scope.params.fechaFin,
            centroInstalacion:$scope.seguimientoVO.nbCentroInstalacion
        });
        
        if($scope.seguimientoVO.tablaResultados){
            parametros.nivelGeneral = $scope.seguimientoVO.datosTabla;
            parametros.nivel = "general";           
        }else if($scope.seguimientoVO.verDetalleOS){
            parametros.nivelDetalle = $scope.seguimientoVO.datosTabla;
            parametros.nivel = "detalle";
        }else if($scope.evidenciaVO.verEvidencia){
            parametros.nivelIncidencia = $scope.evidenciaVO.data;
            parametros.nivel = "incidencia";
        }

        seguimientoOsService.getReporteExcel(parametros).success(function (data, status, headers) {
            var filename = headers('filename');
            var contentType = headers('content-type');
            var file = new Blob([data], { type: 'application/vnd.ms-excel;base64,' });
            save(file, filename);
        }).error(function(data){
            growl.error(data.message);
        })
    };

    function save(file, fileName) {
        var url = window.URL || window.webkitURL;
        var blobUrl = url.createObjectURL(file);
        var a = document.createElement('a');
        a.href = blobUrl;
        a.target = '_blank';
        a.download = fileName;
        document.body.appendChild(a);
        a.click();
    };

	$scope.showConfirmacion = function (messageTo, action) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
			controller: 'mensajeModalController',
			inputs: { message: messageTo }
		}).then(function (modal) {
			modal.element.modal();
			modal.close.then(function (result) {
				if (result) {
					action();
				}
			});
		});
	};

    $scope.$on('$locationChangeSuccess', function (event, current, previous) {
        $scope.proximoController = $route.current.$$route.controller;
        if ($scope.proximoController != "detalleSeguimientoOsController" && $scope.proximoController != "seguimientoOsController") {
            detalleSeguimientoOsService.saveconsultaGeneral(null);
        }
    });

    starController();
});