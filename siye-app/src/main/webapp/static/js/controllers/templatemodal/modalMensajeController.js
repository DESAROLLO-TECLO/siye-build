angular.module(appTeclo)
.controller('modalMensajeController',
function($scope,$element,pa) {

	$scope.message = pa.message;
	$scope.typeModal = pa.typeModal;
	
	$scope.cancel = function() {
		if(pa.cancel) {
			if(pa.paramCancel) {
				pa.cancel(pa.paramCancel);
			}else{
				pa.cancel();
			}
		} else {
			return;
		}
	}
	
	$scope.success = function(result){
		if(pa.success) {
			if(pa.paramSuccess) {
				pa.success(pa.paramSuccess);
			}else{
				pa.success();
			}
		} else {
			return;
		}
	};
});