angular.module(appTeclo).controller('toolsWebController', function($scope, growl, ModalService) {
	
	$scope.CG = {};
	$scope.pixelsConvertVO = [];
	
	$scope.CG.basePixel = 14;
	$scope.CG.pixels = "2,4,6,8,10";
	
	$scope.validateField = function(e) {
		var eCopy = angular.copy(e);
		if(!(/^[0-9,]+$/).test($scope.CG.pixels)) {
			$scope.CG.pixels = eCopy;
		}
	}
	
	$scope.convertPixels = function() {
		
		$scope.pixelsConvertVO = [];
		
		var arrayPixels = $scope.CG.pixels.split(',');
		
		if(arrayPixels[0] != "") {
			angular.forEach(arrayPixels, function(pixel, key) {
				parseInt(pixel);
				var rem = (pixel/$scope.CG.basePixel).toFixed(4);
				$scope.pixelsConvertVO.push({pixels:pixel+'px', rems: rem+'rem', basePixel: $scope.CG.basePixel});
			});			
		}else{
			$scope.pixelsConvertVO = [];
		}
	}
	
	$scope.copyRem = function(c) {
		
		var $temp = $('<input />'),
			$body = $('body');
		
		$body.append($temp);
		$temp.val(c).select();
		
		document.execCommand('copy');
		
		$temp.remove();
		
		growl.success('¡Copiado!',{title: c})
	}
	
});