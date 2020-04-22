angular.module('ui.filedrop', [])
.directive("fileDrop", function ($parse, $document) {
    return {
        restrict: "A",
        scope:{
        	onImageDrop:'&'	
        },
        link: function (scope, element, attrs) {

            //When an item is dragged over the document
            var onDragOver = function (e) {
                e.preventDefault();
                angular.element('body').addClass("dragOver");
            };

            //When the user leaves the window, cancels the drag or drops the item
            var onDragEnd = function (e) {
                e.preventDefault();
                angular.element('body').removeClass("dragOver");
            };
            
            scope.logobsResult=function(files) {
        		if(files != undefined){
        			let i;
        			for(i=0; i<files.length; i++){
        				let file=files[i];
        				let reader = new FileReader();
        				  reader.onloadend = function () {
        				    var b64 = reader.result.replace(/^data:.+;base64,/, '');
        				    file.strBase64=b64;
        				  };
        				 reader.readAsDataURL(file);
        			}
        		}
    		}

            //When a file is dropped
            var loadFile = function (files) {
            	
                let filesResult=[];
                let i;
                for(i=0; i<files.length; i++){
                	filesResult.push(files[i]);
                }
                
                scope.logobsResult(filesResult);
            	
                scope.uploadedFile = filesResult;
                scope.$apply(scope.onImageDrop(scope));
            };

            //Dragging begins on the document
            $document.bind("dragover", onDragOver);

            //Dragging ends on the overlay, which takes the whole window
            element.bind("dragleave", onDragEnd)
                   .bind("drop", function (e) {
                       onDragEnd(e);
                       loadFile(e.originalEvent.dataTransfer.files);
                   });
        }
    };
});