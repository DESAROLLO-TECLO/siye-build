angular.module(appTeclo).service('cargaArchivoLayoutService',function($http,config,$timeout) {
	
	const END_POINT=config.baseUrl + "/async/lote/";
	const UPLOAD_FILE=END_POINT+"upload";
	const GET_FILES_UPLOAD_TO_DAY=END_POINT+"getFilesUploadToDay";
	const DOWLOAD_FILE=END_POINT+"dowloadFileByIdLote";
	
	this.uploadFile = function(file) {
		delete file.strBase64;
		var fd = new FormData();
		fd.append('file', file);
		return $http({
		    url: UPLOAD_FILE,
		    headers: {"Content-Type": undefined},
		    transformRequest: angular.identity,
		    data: fd,
		    method: "POST"
		});
	};
	
	this.getFilesUploadToDay=function(){
		return $http.get(GET_FILES_UPLOAD_TO_DAY);
	};
	
	/*UTILITIES DOWLOAD FILE*/
	this.dowloadFileByIdLote = function(idLote) {
		
        return $http({
            method: 'GET',
            url: DOWLOAD_FILE,
            params: {'idLote':idLote},
            dataType: "json",
            header: {
                "Content-type": "application/json",
                "Accept": "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            },
            responseType: 'arraybuffer'
        });
    };
    
    this.downloadfile = function(file, fileName) {
        var url = window.URL || window.webkitURL;
        var blobUrl = url.createObjectURL(file);
        var a = document.createElement('a');
        a.href = blobUrl;
        a.target = '_blank';
        a.download = fileName;
        document.body.appendChild(a);
        $timeout(function() {
            a.click();
        }, 100);
    };
    /*UTILITIES DOWLOAD FILE*/
});