angular.module(appTeclo).config(function (LightboxProvider) {
    LightboxProvider.fullScreenMode = true;
});
angular.module(appTeclo).controller('detalleSeguimientoOsController',
    function ($rootScope, $scope, $route, $location, $document, showAlert, growl, Lightbox, detalleSeguimientoOsService, lineaTiempoVO, ModalService) {
        $scope.procesoActual = {};
        $scope.b_seguimiento = true;
        $scope.b_detalle = false;
        $scope.procesoActivo = false;
        $scope.ordenServicio = {};
        $scope.net;
        $scope.infoNet;
        var nodosPorFila = 4;
        var coordX = 0;
        var coordY = 0;
        var disX = 250;
        var disY = 150;
        //se obtiene una referencio del elemento del dom con id  mynetwork
        var container = document.getElementById('mynetwork');
        var nodes, edges;
        var nodeInfo, edgeInfo;

        // Var datos desde el back
        $scope.ordenServicioVO = new Object({
            verProcesos:true,
            verDetalle:false,
            detalle:[],
            proceso:[],
            etapasVO:[]
        });

        $scope.opciones = new Object({
            verNodos:true,
            verLinea:false
        });

        getDetalleOrdenServicio = function () {
            if (lineaTiempoVO != undefined) {
                $scope.ordenServicioVO.proceso = detalleSeguimientoOsService.getconsultaGeneral();
                $scope.ordenServicioVO.detalle = lineaTiempoVO.data;
                if(lineaTiempoVO.data.procesos!=null){
                    initController();
                }else{
                    growl.info("No tiene Procesos Asignados ");
                    $location.path('/seguimientoOS');
                }
               
            } else {
                $location.path('/seguimientoOS');
                // growl.error('No hay detalle para esta Orden de Servicio');
            }
        };

        $scope.regresar = function(){
            if($scope.ordenServicioVO.verProcesos){
                //Regresar consulta gral
                $location.path('/seguimientoOS');
            }else{
                $scope.ordenServicioVO.verProcesos = true;
                $scope.ordenServicioVO.verDetalle = false;
                $scope.opciones.verNodos = true;
                $scope.opciones.verLinea= false;
            }
        };

        $scope.showViewTimeList = function (itmeProceso) {
            $scope.flags.showViewNodes = false;
            $scope.flags.showViewTimeList = true;
            $scope.procesoActual = itmeProceso;
            $scope.b_seguimiento = false;
            $scope.b_detalle = true;
            scrollDetail();
        };

        $scope.showViewNodeData = function (idOrdenServicio) {
            $scope.flags.showViewNodes = false;
            $scope.flags.showViewTimeList = true;
            $scope.procesoActual = {};
            $scope.b_seguimiento = true;
            $scope.b_detalle = false;
        };

        crearNodo = function (proceso) {
            if (typeof proceso == "undefined" || proceso == null)
                return;

            nodes.add({
                id: proceso.idProceso,
                shape: 'circularImage',
                image: 'data:image/png;base64,' + proceso.lbImagen,
                x: coordX,
                y: coordY,
                label: proceso.nbProceso,
                color: { border: proceso.cdRgb, highlight: { border: proceso.cdRgb } },
                font: { vadjust: 10 }
            });

            if (from != null)
                edges.add({ from: from, to: proceso.idProceso, arrows: 'to' });
            from = proceso.idProceso;

            coordX += disX;
            if (nodes.length % nodosPorFila == 0) {
                disX *= (-1);
                coordY += disY;
                coordX += disX;
            }
        };

        scrollDetail = function () {
            $('#scrollDetail').slimScroll({
                height: '100%',
                color: '#00243c',
                opacity: .3,
                size: "4px",
                alwaysVisible: false
            });
        };

        obtenerProcesos = function (procesos) {
            from = null;
            angular.forEach(procesos, function (value, key) {
                crearNodo(value);
            });
        };

        fitAnimated = function () {
            var options = {
                duration: 500,
                easingFunction: 'linear'
            };
            $scope.net.fit({ animation: options });
        };

        initController = function () {            /* Inicializamos con valores generales para el diagrama principal */
            nodes = new vis.DataSet();
            edges = new vis.DataSet();

            obtenerProcesos($scope.ordenServicioVO.detalle.procesos);

            var pxDf = parseInt($('html').css('font-size'));
            var fontFamily = $('html').css('font-family');


            data = {
                nodes: nodes,
                edges: edges
            };

            options = {
                physics: { enabled: false },
                nodes: {
                    borderWidth: 5, borderWidthSelected: 5, size: 30,
                    color: { background: '#FFFFFF', hover: { border: '#cc4865' } },
                    font: { color: '#2c3e50', size: pxDf, face: fontFamily, background: 'none', strokeWidth: 0, strokeColor: '#cc4865', align: 'center' }
                },
                edges: { color: '#cc4865', width: 1.5 },
                interaction: { dragNodes: false, hover: true, tooltipDelay: 100 }
            };
            $scope.net = new vis.Network(container, data, options);
            fitAnimated();
        }
        // Guardar imagen 
        saveDoc = function (isURL, file, fileName) {
            var url = window.URL || window.webkitURL;
            var a = document.createElement('a');
            if (isURL)
                a.href = file;
            else {
                var blobUrl = url.createObjectURL(file);
                showPdf(blobUrl);
                a.href = blobUrl;
            }
            a.target = '_blank';
            a.download = fileName;
            document.body.appendChild(a);
        }

        showPdf = function (messageTo) {
            ModalService.showModal({
                templateUrl: 'views/templatemodal/templateModalPdf.html',
                controller: 'mensajeModalController',
                inputs: { message: messageTo }
            }).then(function (modal) {
                modal.element.modal();
            });
        };

        getLineaTiempo = function(os, procesos){
            detalleSeguimientoOsService.getDetalleProcesoEspecifico(os, procesos).success(function(data){
                $scope.ordenServicioVO.etapasVO = data;
                $scope.ordenServicioVO.verProcesos = false;
                $scope.ordenServicioVO.verDetalle = true;
                $scope.opciones.verNodos = false;
                $scope.opciones.verLinea= true;
            }).error(function(data){
                growl.error(data.message);
            })
        };

        $scope.verImagenesCarrusel = function(params, claseBus, nivel){
            if(params!=undefined){
                $scope.paramsRespaldoModal = new Object({
                    fechaInicio:  $scope.ordenServicioVO.proceso.formBusuqeda.fechaInicio,
                    fechaFin: $scope.ordenServicioVO.proceso.formBusuqeda.fechaInicio,
                    idCentroInstalacion:$scope.ordenServicioVO.proceso.idCentroInstalacion
                });    
                ModalService.showModal({
                    templateUrl: 'views/templatemodal/templateModalIncidenciasMonitoreo.html',
                    controller: 'modalIncidenciasMonitoreoController',
                    scope: $scope,
                    inputs: {OrdenServicio :params}
                }).then(function(modal) {
                    modal.element.modal();
                });
            }
        };

        $scope.changeViewTab = function(tipo){
            switch(tipo){
                case 'nodo':
                    $scope.opciones.verNodos = true;
                    $scope.opciones.verLinea= false;
                    $scope.ordenServicioVO.verProcesos = true;
                    $scope.ordenServicioVO.verDetalle = false;
                    break;

                case 'linea':
                    let opciones = $scope.ordenServicioVO.detalle.procesos.length;
                    let proceso = [];
                    for(let x=0; x<opciones; x++){
                        proceso.push($scope.ordenServicioVO.detalle.procesos[x].idProceso);
                    }
                    getLineaTiempo($scope.ordenServicioVO.proceso.dtOs.idOrdenServicio, proceso)
                    break;
            }
        };

        $scope.$on('$locationChangeSuccess', function (event, current, previous) {
            $scope.proximoController = $route.current.$$route.controller;
            if ($scope.proximoController != "detalleSeguimientoOsController" && $scope.proximoController != "seguimientoOsController") {
                detalleSeguimientoOsService.saveconsultaGeneral(null);
            }
        });


        getDetalleOrdenServicio();

    });