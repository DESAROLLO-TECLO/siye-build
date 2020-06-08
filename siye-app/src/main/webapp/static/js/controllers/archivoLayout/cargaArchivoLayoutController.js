angular.module(appTeclo).controller("cargaArchivoLayoutController",
function($scope,$interval,$timeout,ModalService,showAlert,growl, $location, cargaArchivoLayoutService) {
	
	const MESSAGES=new Object({
		NOT_ADD_FILE:'No se ha seleccionado ningun archivo',
		UPLOAD_FILE_SUCCESS:'Se cargo el archivo correctamente',
		UPLOAD_FILE_ERRROR:'No se pudo cargar el archivo seleccionado, vuelva a intetar',
		FILE_IN_PROCESS:'Actual mente existe un archivo en proceso, por favor espere',
		TXT_SELECT_DEFAULT:'Sin Refrescar'
	});
	
	const CD_STATUS=new Object({
		RECIBIDO:'RECIBIDO',
		CARGANDO:'CARGANDO',
		CARGADO:'CARGADO',
		RECHAZADO:'RECHAZADO'
	});
	var catOptionRefresh=new Array(
			  { "idTimeRefresh": 2, "nbTimeRefresh": ' 15 Seg ', "timeRefresh": .25 },
			  { "idTimeRefresh": 3, "nbTimeRefresh": ' 30 Seg ', "timeRefresh": .5 }, 
			  { "idTimeRefresh": 4, "nbTimeRefresh": '  1 Min ', "timeRefresh": 1 }, 
			  { "idTimeRefresh": 5, "nbTimeRefresh": '  3 Min ', "timeRefresh": 3 }, 
			  { "idTimeRefresh": 6, "nbTimeRefresh": '  5 Min ', "timeRefresh": 5 });
	$scope.filesVO=new Object({
		fileUploaded : undefined,
		fileUploadedBackup : undefined,
		fileUpload: undefined,
		selectListTimeRefresh : catOptionRefresh,
		selectTimeRefresh : catOptionRefresh[0]
	});
	
	$scope.isUseTooltip=true;
	
	$scope.mostrarTimeRefresh=false;
	
	$scope.view =new Object({
		rowsPerPage:5,
		filter:undefined
	});
	
	// Iniciar el contador
    $scope.mostrarTimeRefresh = false; // bandera para mostrar minutero o icono
    var _TIEMPO_CONSULTA = 0; // intervalo de busqueda en min...
    var _TIEMPO_COUNT = 0; // contador actual...
    var _INTERVAL_CONSULTA; // variable de interval
	
	
	//Metodo que recibe los files desde el input file
	$scope.getFilesFromInput=function(files){
		$scope.filesVO.fileUploadedBackup = angular.copy($scope.filesVO.fileUploaded);
		$scope.filesVO.fileUploaded = undefined;
		$scope.filesVO.fileUpload=files[0];
	};
	
	$scope.resetFile=function(){
		$scope.filesVO.fileUploaded = angular.copy($scope.filesVO.fileUploadedBackup);
		$scope.filesVO.fileUploadedBackup = undefined;
		$scope.filesVO.fileUpload = undefined;
	};
	
	$scope.uploadFileService=function(form){
		
		if($scope.filesVO.fileUploaded != undefined && 
				($scope.filesVO.fileUploaded.cdStSeguimiento == CD_STATUS.RECIBIDO ||  
						$scope.filesVO.fileUploaded.cdStSeguimiento == CD_STATUS.CARGANDO)){
			growl.error(MESSAGES.FILE_IN_PROCESS,{ ttl: 4000 });
            return;
		}
		
		if ($scope.filesVO.fileUpload == undefined || (form != undefined && form.$invalid)) {
            
			if(form != undefined && form.$invalid)
				showAlert.requiredFields(form);
            
            growl.error(MESSAGES.NOT_ADD_FILE,{ ttl: 4000 });
            return;
        }
		
		cargaArchivoLayoutService.uploadFile($scope.filesVO.fileUpload)
		.success(function(response){
			
			if(response.cdStSeguimiento == CD_STATUS.RECIBIDO ||  response.cdStSeguimiento == CD_STATUS.CARGANDO){
				$scope.filesVO.fileUploaded=response;
				$scope.filesVO.fileUploadedBackup = angular.copy(response);
			}
			
			if(response.txLoteOds != null){
				let msj='Estatus del archivo: '+response.nbStSeguimiento;
				growl.error(response.txLoteOds, { title:msj,ttl: 4000 });	
			}else{
				growl.success(MESSAGES.UPLOAD_FILE_SUCCESS, { ttl: 4000 });	
			}
			
			$scope.resetFile();
			
   	 	}).error(function (error){
   	 		$scope.filesVO.fileUploaded=undefined;
   	 		showErrorMessage(error);
   	 	});
		
	};
	
	//Retorna una lista de los archivos cargados del dia, y regresa su estatus actual
	getListFilesUploadStatus=function(){
		//$("#select2-selectTimeRefresh-container").text(MESSAGES.TXT_SELECT_DEFAULT);
			cargaArchivoLayoutService.getFilesUploadToDay()
			.success(function(response){
				let lote=response[0];
				$scope.filesVO.fileUploaded=lote;
				$scope.filesVO.fileUploadedBackup = undefined;
	   	 	}).error(function (error){
	   	 		$scope.filesVO.fileUploadedBackup=undefined;
	   	 		$scope.filesVO.fileUploaded=undefined;
	   	 	});
	};
	
	//Procesa mensaje de Error
	showErrorMessage=function(e){
		if(e.status != undefined && Number.isInteger(e.status)){
			
			if(e.descripcion != undefined){
	           	growl.error(e.descripcion,{ ttl: 4000 });
	        }else if(e.message != undefined) {
	           	growl.error(e.message,{ ttl: 4000 });
	        }else if(typeof e.status === 'string'){
	           	growl.error(e.status,{ ttl: 4000 });
	        }else {
	            showAlert.error('Falló la petición, por favor intente de nuevo');
	        }
			
		}else if(e.status != undefined && typeof e.status === 'object'){
			if(e.status.descripcion != undefined){
            	growl.error(e.status.descripcion,{ ttl: 4000 });
            }else if(e.status.message != undefined) {
            	growl.error(e.status.message,{ ttl: 4000 });
            }else if(typeof e.status === 'string'){
            	growl.error(e.status,{ ttl: 4000 });
            }else {showAlert.error('Falló la petición');} 
		}else{
			growl.error(e,{ ttl: 4000 });
		 }
	};
	
	// Cambios en el tiempo de refrescado
    $scope.cambiaTiempo = function() {
        cancelarBuscarPorIntervalo();
        if ($scope.filesVO.selectTimeRefresh != null) {
            _TIEMPO_CONSULTA = $scope.filesVO.selectTimeRefresh.timeRefresh;
            iniciarConsultaPorIntervalo();
            $timeout(function() { $scope.mostrarTimeRefresh = true; }, 1000);
        } else {
            _TIEMPO_CONSULTA = 0;
            $scope.mostrarTimeRefresh = false;
        }
    };
    
    function cancelarBuscarPorIntervalo() {
        if (angular.isDefined(_INTERVAL_CONSULTA)) {
            $interval.cancel(_INTERVAL_CONSULTA);
            _INTERVAL_CONSULTA = undefined;
            $scope.mostrarTimeRefresh = false;
        }
    };
    
    function iniciarConsultaPorIntervalo() {
        _TIEMPO_COUNT = _TIEMPO_CONSULTA * 60;
        _INTERVAL_CONSULTA = $interval(function() {
            var min = Math.floor(_TIEMPO_COUNT / 60);
            var sec = Math.floor(_TIEMPO_COUNT % 60);
            document.getElementById("select2-selectTimeRefresh-container").innerHTML = (min < 10 ? '0' + min : min) + " : " + (sec < 10 ? '0' + sec : sec);
            if (--_TIEMPO_COUNT < 0) {
                cancelarBuscarPorIntervalo();
                document.getElementById("select2-selectTimeRefresh-container").innerHTML = "00:00";
                getListFilesUploadStatus();
                $scope.cambiaTiempo();
            }
        }, 1000);
    };
    
    $scope.refrescar = function() {
        cancelarBuscarPorIntervalo();
        getListFilesUploadStatus();
        $scope.cambiaTiempo();
    };
    
    $scope.descargarArchivo = function(idLote) {
        
    	if($scope.filesVO.fileUploaded != undefined && 
				($scope.filesVO.fileUploaded.cdStSeguimiento == CD_STATUS.RECIBIDO ||  
						$scope.filesVO.fileUploaded.cdStSeguimiento == CD_STATUS.CARGANDO)){
			growl.error(MESSAGES.FILE_IN_PROCESS,{ ttl: 4000 });
            return;
		}
    	
    	cargaArchivoLayoutService.dowloadFileByIdLote(idLote).success(function(data, status, headers) {
            let filename = headers('filename');
            let file = new Blob([data], { type: 'application/vnd.ms-excel;base64,' });
            cargaArchivoLayoutService.downloadfile(file, filename);
        }).error(function(data) {
        	showErrorMessage(data);
        });
    };
    
    
    isMobile=function(){
		  var isMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);
			if (isMobile) {
				$scope.isUseTooltip=false;
				return true;
			} else {
				$scope.isUseTooltip=true;
				return false;
			}
	  };
	  
	  $scope.$on('$locationChangeSuccess',function(event, current, previous) {
			
			cancelarBuscarPorIntervalo();			
			
		});
	
	getListFilesUploadStatus();
	$scope.cambiaTiempo();
	isMobile();
	
});