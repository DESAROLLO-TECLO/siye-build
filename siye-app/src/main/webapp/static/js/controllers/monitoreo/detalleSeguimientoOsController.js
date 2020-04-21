angular.module(appTeclo).config(function (LightboxProvider) {
    LightboxProvider.fullScreenMode = true;
});

angular.module(appTeclo).controller('detalleSeguimientoOsController', 
		function ($rootScope, $scope, $location, $document, showAlert, growl, Lightbox,
				detalleSeguimientoOsService) {
	
	var paramSaarchOrden={
			"idOrdenServicio":61,
			 "cdOrdenServicio":"FOL-001"
	};
	
	$scope.procesoActual={};
	$scope.b_seguimiento = true;
	$scope.b_detalle = false;
	$scope.procesoActivo = false;
	$scope.viewMode = "c";
	$scope.ordenServicio={};
	$scope.net;
	$scope.infoNet;
	var nodosPorFila = 4;
	var coordX = 0;
	var coordY = 0;
	var disX = 250;
	var disY = 150;
	//se obtiene una referencio del elemento del dom con id  mynetwork
	var container = document.getElementById('mynetwork');
	
	///var infoNetwork = document.getElementById('infoNetwork');
	
	var nodes, edges;
	var nodeInfo, edgeInfo;
	
	$scope.flags={
			showViewNodes:true,
			showViewTimeList:false
	};

    $scope.showViewTimeList = function (itmeProceso) {
    	$scope.flags.showViewNodes=false;
    	$scope.flags.showViewTimeList=true;
    	$scope.procesoActual=itmeProceso;
    	$scope.b_seguimiento = false;
        $scope.b_detalle = true;
        scrollDetail();
    };

    $scope.showViewNodeData = function(idOrdenServicio){
    	$scope.flags.showViewNodes=false;
    	$scope.flags.showViewTimeList=true;
    	$scope.procesoActual={};
        $scope.b_seguimiento = true;
        $scope.b_detalle = false;
    };
    
    getDataOrdenServicio=function(){
    	$scope.ordenServicio=detalleSeguimientoOsService.getDetalleOS(1);
    	obtenerProcesos($scope.ordenServicio.procesos);
    };
    
    function  showAlert(){
    	growl.error('Entro al evento');
    };
    
    crearNodo = function (proeceso) {
        if (typeof proeceso == "undefined" || proeceso == null)
            return;

        nodes.add({
            id: proeceso.idProceso,
            shape: 'circularImage',
            // image: DIR + datos.procesos.nbImagen, 
            image: 'data:image/png;base64,' + proeceso.lbImagen,
            x: coordX,
            y: coordY,
            label: proeceso.nbProceso,
            color: { border: proeceso.cdRgb, highlight: { border: proeceso.cdRgb } },
            font: { vadjust: 10 }
        });

        if (from != null)
            edges.add({ from: from, to: proeceso.idProceso, arrows: 'to' });
        from = proeceso.idProceso;

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
       
       obtenerProcesos2 = function(idComparendo){

   		$scope.procesos = [];
   		nodes.clear();
   		edges.clear();
   		coordX = 0;
   		coordY = 0;
   		disX = 250;
   		from = null;
   		angular.forEach($scope.procesos, function (value, key) {
   			crearNodo(value);
   			if(value.estatus.cdEstatus == 'c001')
   				$scope.procesoActivo = true;
   		});
   		fitAnimated();
   	
   };           
   obtenerProcesos = function(procesos){
   		from = null;
   		angular.forEach(procesos, function (value, key) {
   			crearNodo(value);
   			if(value.estatus.cdEstatus == 'c001')
   				$scope.procesoActivo = true;					
   		});

   };

   fitAnimated =function() {
	    var options = {
	      duration: 500,
	      easingFunction: 'linear'
	    };
	    $scope.net.fit({animation:options});
   };
           
    initController = function() {
        /* Inicializamos con valores generales para el diagrama principal */
        nodes = new vis.DataSet();
        edges = new vis.DataSet();
        
        getDataOrdenServicio();
    
        var pxDf  = parseInt($('html').css('font-size'));
        var fontFamily  = $('html').css('font-family');
        
        
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
                  interaction:{dragNodes:false,  hover:true, tooltipDelay:100},
                  event: showAlert()
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