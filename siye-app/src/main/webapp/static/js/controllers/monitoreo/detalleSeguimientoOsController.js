angular.module(appTeclo).config(function (LightboxProvider) {
    LightboxProvider.fullScreenMode = true;
});

angular.module(appTeclo).controller('detalleSeguimientoOsController', function ($rootScope, $scope, $location, $document, showAlert, growl, Lightbox) {



    $scope.regresarDesdeLineaTiempo = function () {

    };

    // Funciones genericas 
    $scope.cambioVista = function (vista) {
        $scope.viewMode = vista;
        if (vista == "c") {
            obtenerProcesos2($scope.procesos[0].idComparendo);
            $scope.b_seguimiento = true;
            $scope.b_detalle = false;
        }
        if (vista == "d") {
            consultaDetalle($scope.procesos[0].idComparendo);
            $scope.b_seguimiento = false;
            $scope.b_detalle = true;
            scrollDetail();
        }
    }

    crearNodo = function (datos) {
        if (typeof datos == "undefined" || datos == null)
            return;

        nodes.add({
            id: datos.procesos.idProceso,
            shape: 'circularImage',
            // image: DIR + datos.procesos.nbImagen, 
            image: 'data:image/png;base64,' + datos.procesos.lbImagen,
            x: coordX,
            y: coordY,
            label: datos.procesos.nbProceso,
            color: { border: datos.cdRgb, highlight: { border: datos.cdRgb } },
            font: { vadjust: 10 }
        });

        if (from != null)
            edges.add({ from: from, to: datos.procesos.idProceso, arrows: 'to' });
        from = datos.procesos.idProceso;

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

    initController = function() {
        $scope.comparendo=jasonComparendo;
        /* Inicializamos con valores generales para el diagrama principal */
        nodes = new vis.DataSet();
        edges = new vis.DataSet();
    
        var pxDf  = parseInt($('html').css('font-size'));
        var fontFamily  = $('html').css('font-family');
        
        obtenerProcesos();	
        
        data = {
                nodes: nodes,
                  edges: edges
          };
        
        
        options = {
                physics:{enabled: false},
                nodes: {borderWidth:5, borderWidthSelected: 5, size:30,
                  color:{background: '#FFFFFF', hover:{border:'#cc4865'}},
                  font:{color:'#2c3e50', size: pxDf, face: fontFamily, background: 'none', strokeWidth: 0, strokeColor: '#cc4865', align: 'center'}
                },
                edges: {color: '#cc4865', width: 1.5},
                  interaction:{dragNodes:false,  hover:true, tooltipDelay:100}
        };
        $scope.net = new vis.Network(container, data, options);
        fitAnimated();
    }


    // Guardar imagen 
    saveDoc = function(isURL, file, fileName){
        var url = window.URL || window.webkitURL;
        var a = document.createElement('a');
        if(isURL)
            a.href = file;
        else{ 
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
            inputs: {message: messageTo}
        }).then(function (modal) {
            modal.element.modal();
        });
    };
    
    initController();
    

});