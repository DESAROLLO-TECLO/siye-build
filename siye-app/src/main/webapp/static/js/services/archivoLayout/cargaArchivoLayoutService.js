angular.module(appTeclo).service('cargaArchivoLayoutService',function($http,config) {
	
	const END_POINT=config.baseUrl + "/async/lote/";
	const UPLOAD_FILE=END_POINT+"upload";
	const GET_FILES_UPLOAD_TO_DAY=END_POINT+"getFilesUploadToDay";
	
	this.uploadFile = function(file) {
		delete file.strBase64;
		var fd = new FormData();
		fd.append('file', file);
		return $http({
		    url: UPLOAD_FILE,
		    headers: {"Content-Type": undefined },
		    data: fd,
		    method: "POST"
		});
		
		//return $http.post(UPLOAD_FILE, file);
	};
	
	this.getFilesUploadToDay=function(){
		return $http.get(GET_FILES_UPLOAD_TO_DAY);
	};
});