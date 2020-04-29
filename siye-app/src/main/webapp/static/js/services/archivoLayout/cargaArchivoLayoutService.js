angular.module(appTeclo).service('cargaArchivoLayoutService',function($http,config) {




//Descarga excel  GeneradorCsv
this.cargarArchivo = function(archivoVO){
	return $http({
		method: 'POST',
		url: config.baseUrl + "/async/lote/upload",

			params : {
				"file"		: archivoVO
			},

		dataType: "json",
		header :{ "Content-type": "application/json",
					"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
		},
		responseType: 'arraybuffer'
	});
}



	
	this.cargarArchivo1 = function(archivoVO) {
		return $http.post(config.baseUrl + "/async/lote/upload", archivoVO);
	};
  
	
	


});