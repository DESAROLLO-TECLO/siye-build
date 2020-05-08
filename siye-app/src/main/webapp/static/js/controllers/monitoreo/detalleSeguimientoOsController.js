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
        var disY = 550;
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
                debugger
                $scope.ordenServicioVO.proceso = detalleSeguimientoOsService.getconsultaGeneral();
                $scope.ordenServicioVO.detalle = lineaTiempoVO.data;
                if(lineaTiempoVO.data.procesos!=null){
                    initController();
                }else{
                    growl.info("No tiene Procesos Asignados ", {ttl: 5000});
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

        crearNodo = function (proceso, padre) {
            if (typeof proceso == "undefined" || proceso == null)
                return;

            nodes.add({
                id: proceso.idProceso,
                shape: 'circularImage',
                image: 'data:image/png;base64,' + proceso.lbImagen,
                x: coordX,
                y: 0,
                label: proceso.nbProceso,
                color: { border: proceso.cdRgb, highlight: { border: proceso.cdRgb } },
                font: { vadjust: 10 }
            });
            coordY =0;

            if(proceso.encuestas !=null){
                let nodoshijos =  proceso.idProceso;
                for(let x=0; x<proceso.encuestas.length; x++){
                    // crear nodo encuesta
                    nodes.add({
                        id: proceso.encuestas[x].cdEncuesta,
                        shape: 'circularImage',
                        image: 'data:image/png;base64,' + proceso.lbImagen,
                        x: coordX,
                        y: coordY+=95,
                        label: proceso.encuestas[x].nbEncuesta,
                        color: { border: proceso.encuestas[x].nbColor, highlight: { border: proceso.encuestas[x].nbColor } },
                        font: { vadjust: 5 }
                    });
                    // agregar nodo encuesta
                    edges.add({ from:nodoshijos, to: proceso.encuestas[x].cdEncuesta, arrows: 'to' });
                    nodoshijos = proceso.encuestas[x].cdEncuesta;
                }
            }

            if (from != null && padre>1)
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
            let padre = 1;
            from = null;
            angular.forEach(procesos, function (value, key) {
                crearNodo(value, padre);
                padre+=1;
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
        $scope.downLoadTimeLine=function(){
            $scope.flagDownload=true;
            scrollDetailDestroy()
             $timeout(function() {
                html2canvas(document.querySelector("#imgLineTiempo")).then(canvas => {
                     var dataURL = canvas.toDataURL();
                     $scope.imgCapture=dataURL;
                     var file=dataURL.split(",");
                     $scope.archivo=$scope.b64toBlob(file[1],"image/png");
                     save( $scope.archivo,"image/png");
                });
             },15);
        };

        save=function(file, fileName) {
            $scope.flagDownload=false;
            scrollDetail()
            var url = window.URL || window.webkitURL;
            var blobUrl = url.createObjectURL(file);
            var a = document.createElement('a');
            a.href = blobUrl;
            a.target = '_blank';
            a.download = fileName;
            document.body.appendChild(a);
            a.click();
        }

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

        $scope.verModalEvidencias = function(nivel,opciones){
            debugger
            let verModal= false;
            let parametros = new Object({
                idOrdenServicio: $scope.ordenServicioVO.detalle.idOrdenServicio,
                valor: null,
                nivel: nivel,
                clase: "sin clase",
                data:[]
            });

            if(nivel==='pregunta'){
                parametros.valor = opciones.idEncuesta;
                parametros.nbEncuesta = opciones.nbEncuesta;
            }else if(nivel==='encuesta'){
                parametros.valor = opciones.idEncuesta;
                parametros.infoEncuesta = opciones;
            }else if(nivel==='ordenservicio'){
                parametros.valor = $scope.ordenServicioVO.detalle.idOrdenServicio;
                parametros.infoOS = opciones;
            }

            detalleSeguimientoOsService.getSeguimientoImagen(parametros).success(function(data){
                let mensaje = "",tipo='info';
                switch(nivel){
                    case 'pregunta':
                        if(data.nivelPreguntas!=null){
                           verModal=true;
                        }else{
                            mensaje="No tiene información para mostrar";
                            tipo='error';
                        }
                    break;

                    case 'encuesta':
                        if(data.nivelEncuesta.length>0){
                            verModal=true;
                        }else{
                            mensaje="No tiene Imagenes para mostrar";
                        }
                    break;

                    case 'ordenservicio':
                        if(data.nivelOrdenServicio.length>0){
                            verModal=true;
                        }else{
                            mensaje="No tiene Imagenes que mostrar a nivel Orden de Servicio";
                        }
                    break;

                    default:
                        growl.error("Nivel invalido");
                }

                if(verModal){
                    parametros.data=data;
                    verModalDetalle(parametros);
                }else{
                    if(tipo==='error'){
                        growl.error(mensaje);
                    }else{
                        growl.info(mensaje);
                    }
                }
            }).error(function(data){
                growl.error(data.message);
            });
        };

        verModalDetalle = function(elementos){
            ModalService.showModal({
                templateUrl: 'views/templatemodal/templateModalEvidenciasPorNivel.html',
                controller: 'modalSeguimientoNivelController',
                scope: $scope,
                inputs: {modalVO :elementos}
            }).then(function(modal) {
                modal.element.modal();
            });
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
