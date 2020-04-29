angular.module(appTeclo).controller("cargaArchivoLayoutController",
function($rootScope,$scope,$window,$translate,$interval,$timeout,ModalService,showAlert,growl, $location, FileUploader, cargaArchivoLayoutService) {

	
	$scope.archivoVO = new Object({
		listaArchivos: new Array()
	});
	
	
	$scope.procesaArchivo = function (voObject){
		$scope.showTabla = true;
		$scope.mostrarBtnPlan = true;
		growl.info('La información se validó correctamente.', {title : 'Éxito', ttl:5000});
	};	
	
	$scope.consultaFileVO = [];
	var uploader = $scope.uploader = new FileUploader({
		url: this
	});
	
	$scope.$watch("uploader.queue.length", function (newVal, olvVal) {
		if (uploader.queue != undefined && uploader.queue.length > 0) {
			let index = newVal - 1;
			let adjuntar = {
				nbArchivo: uploader.queue[index].file.name,
				tamano: uploader.queue[index].file.size,
				file: uploader.queue[index],
				isNew: true
			};
			$scope.consultaFileVO.push(adjuntar);
		}
	});

	// an async filter
	uploader.filters.push({
		name: 'asyncFilter',
		fn: function (item /*{File|FileLikeObject}*/, options, deferred) {

			setTimeout(deferred.resolve, 1);
		}
	});
	
	
	// cargar archivos
	$scope.makeListPicture = (data, nombreArchivo, form, tipo) => {

	

			if (nombreArchivo != undefined) {
				//	if (validaFormulario(form)) {
				readFileAsDataURL(data.file._file);
				$scope.cargarArchivo();
				
				//$scope.showConfirmacionCancelarArchivo("¿Desea guardar el archivo?", function () { $scope.cargarArchivo() });
				//	}
			} else {

				for (let i = 0; i < data.length; i++) {
					if (data[i].tipoDocumento != null) {
						readFileAsDataURL(data[i].file._file);
					}
				}
				 $scope.cargarArchivo()
			  // $scope.showConfirmacionCancelarArchivo("¿Desea guardar los archivo?", function () { $scope.cargarArchivo() });
			}

			async function readFileAsDataURL(file) {
				let result_base64 = await new Promise((resolve) => {
					let fileReader = new FileReader();
					fileReader.onload = (e) => resolve(fileReader.result);
					fileReader.readAsArrayBuffer(file);
				});
				var byteArray = new Uint8Array(result_base64);
				var array = Array.from(byteArray);

				$scope.fileVO = {};
			//	$scope.fileVO = new Object({ 'nbArchivo': file.name, 'cdExtension': file.type, 'lbArchivo': array });
				$scope.fileVO = new Object({ 'file': array });
				$scope.archivoVO.listaArchivos.push($scope.fileVO);
			}
	
		

	};
	
	$scope.cargarArchivo = function () {
		cargaArchivoLayoutService.cargarArchivo($scope.archivoVO)
			.success(function (data) {
				$scope.error = false;
				if (data) {
					showAlert.aviso("Archivo correctamente cargado");
					$scope.archivoVO = { listaArchivos: [] };
					listaArchivoCargar =[];
				}
			}).error(function (data) {
				$scope.showError("No se pudo cargar archivo");

			});
	};
	
	/* Modal Confirmacion */
	$scope.showConfirmacionCancelarArchivo = function (messageTo, action) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
			controller: 'mensajeModalController',
			inputs: { message: messageTo }
		}).then(function (modal) {
			modal.element.modal();
			modal.close.then(function (result) {
				if (result) {
					action();
				} else {
					$scope.archivoVO = { listaArchivos: [] };
				}
			});
		});
	};
	
	
});