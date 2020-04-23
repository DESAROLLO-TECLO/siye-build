angular.module(appTeclo).config(function (LightboxProvider) {
	LightboxProvider.fullScreenMode = false;
});
angular.module(appTeclo).controller('detalleIncidenciaController', function($scope, $filter,idOrden,idPlan,altaIncidenciaService, $timeout, $window, growl, ModalService, showAlert, $location, Lightbox,monIncidenciaService) {

	$scope.procesoActivo = false;
	$scope.flagDownload=false;
	$scope.multimedia=[];
//Consulta arbol de incidencia de orden<Proceso<Encuesta>>>
$scope.getDetalleIncidenciasOS=function(idOrden,idPlan){
	monIncidenciaService.getDetalleIncidenciasOS(idOrden,idPlan).success(function(data) {
    if (data!=null) {
    	$scope.orden=data;
		scrollDetail();
    	}else
    	growl.warning("No se encontro informacion")
		}).error(function(e) {
	$scope.orden = [];
    growl.warning(e.message, { ttl: 5000 });
});
}
//Consulta Evidencias por incidencia
$scope.consultarEvidenciaIncide = function(idIncidencia){
	monIncidenciaService.getExpedienteIncidencia(idIncidencia).success(function(data){
		$scope.multimedia=[];
		$scope.evidencias = data;
		filtrarMultimedios();
		}).error(function(data) {
		growl.warning(data.message)
	});
}
//Funcion para exportar la linea de tiempo 
$scope.downLoadTimeLine=function(){
	$scope.flagDownload=true;
	scrollDetailDestroy()
	 $timeout(function() {
		html2canvas(document.querySelector("#scape1")).then(canvas => {
			 var dataURL = canvas.toDataURL();
			 $scope.imgCapture=dataURL;
			 var file=dataURL.split(",");
			 $scope.archivo=$scope.b64toBlob(file[1],"image/png"); 
			 save( $scope.archivo,"image/png");
		});
	 },15); 
};

$scope.getDetalleIncidenciasOS(idOrden,idPlan);

//Filra expedientes cargados a incidencia para cargar a la directiva lightbox
filtrarMultimedios = function(){
	$scope.multimedia.length = 0;
	var idx = 0;
	angular.forEach($scope.evidencias, function(arch, key) {
	var imagen;
		if(arch.cdTipoArchivo.includes("jpg")|| arch.cdTipoArchivo.includes("png")){
			imagen  = ("data:" +"image/"+arch.cdTipoArchivo + ";base64,").concat(arch.lbExpedienteODS);				
		}
		else if(arch.cdTipoArchivo.includes("video") || arch.cdTipoArchivo.includes("audio")){
			contentType = arch.cdTipoArchivo;
			sliceSize = 512;
			var byteCharacters = atob(arch.lbImagen);
			var byteArrays = [];
			for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
				var slice = byteCharacters.slice(offset, offset + sliceSize);
				var byteNumbers = new Array(slice.length);
				for (var i = 0; i < slice.length; i++) {
					byteNumbers[i] = slice.charCodeAt(i);
				}
				var byteArray = new Uint8Array(byteNumbers);
				byteArrays.push(byteArray);
			}
			var blob = new Blob(byteArrays, {type: contentType});
			var blobUrl = URL.createObjectURL(blob);
			imagen = $sce.trustAsResourceUrl(blobUrl);
		}else
				return;
		
		var _multimedia = {
				idArchivoDenu: arch.idExpedienteODS,
				image: imagen,
				nombreArchivo: arch.nbExpedienteODS,
				tipo: (arch.cdTipoArchivo.includes("video")) ? 2 : (arch.cdTipoArchivo.includes("audio")) ? 3 : 1,
				id: idx++,
				url: imagen,
			    thumbUrl: imagen,
			    type: (arch.cdTipoArchivo.includes("video")) ? 'video' : (arch.cdTipoArchivo.includes("audio")) ? 'audio' : ''
			};
			$scope.multimedia.push(_multimedia);	
	});
	$scope.openLightboxModal(0); 
}


$scope.openLightboxModal = function (index) {
//Directiva que permite mostrar lista de imagenes y avanzar entre ellas(<previus  next>)
//Parametros(lista de imagenes,posicion de la imagen de la lista primera a mostrar))
	Lightbox.openModal($scope.multimedia, index);
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
//Convierte archivos en base64 a blob
$scope.b64toBlob = function(b64Data, contentType, sliceSize) {
	  contentType = contentType || '';
	  sliceSize = sliceSize || 512;
	  var byteCharacters = atob(b64Data);
	  var byteArrays = [];
	  for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
	    var slice = byteCharacters.slice(offset, offset + sliceSize);
	    var byteNumbers = new Array(slice.length);
	    for (var i = 0; i < slice.length; i++) {
	      byteNumbers[i] = slice.charCodeAt(i);
	    }
	    var byteArray = new Uint8Array(byteNumbers);
	    byteArrays.push(byteArray);
	  }
	  var blob = new Blob(byteArrays, {type: contentType});
	  return blob;
};
//Funcion para descargar archivos
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
		
});
