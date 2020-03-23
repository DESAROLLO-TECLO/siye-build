/*
 * Author: César Gómez
 * Directive: modalBootstrap-directive
 * Versión: 1.0.0
 */

var app = angular.module(appTeclo);

app.directive('modal', function() {
        return {
            template: '<div class="modal fade" tabindex="-1" id="{{open}}" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">'
            		+	'<div class="modal-dialog {{txtModal}}{{sizeModal}} ng-style:sizeModalWidth">'
            		+		'<div class="modal-content" ng-transclude>'
            		//+			<!-- Contenido del modal -->
            		+		'</div>'
            		+	'</div>'
            		+'</div>', 
            restrict: 'E',
            transclude: true,
            replace:true,
            scope: {
            	visible:'=',
            	sizeModal: '@',
            	open: '@',
            	onShown:'&',
            	onHide:'&'
            },
            link:function postLink(scope, element, attrs){
            	
            	scope.txtModal = "modal-";
            	
            	$(element).modal({
            		show: false,
                    keyboard: attrs.noescape != undefined ? false : true,
                    backdrop: attrs.modalstatic != undefined ? false : true
                });
                
            	sizeModalDefault = function() {
                	scope.$evalAsync(function() {	                				
        				scope.sizeModal = 'md';
        			});
                	return scope.sizeModal;
                }
            	
                if(scope.sizeModal != undefined) {
                	switch(scope.sizeModal) {
	                	case 'sm':
	                	case 'md':
	                	case 'lg':
	                		scope.sizeModal = scope.sizeModal;
	                		break;
	                	default:
	                		
	                		var isNumber = parseInt(scope.sizeModal);
	                	
	                		if(isNaN(isNumber)) {
	                			sizeModalDefault();
	                		} else {
	                			
	                			var isNumberString = scope.sizeModal.split(/(\d+[.0-9]*)/);
	                			
	                			if(isNumberString[2] != undefined) {
	                				var numberSize = parseFloat(isNumberString[1]);
			                		var unitySize = isNumberString[2];
		                			
			                		switch(unitySize) {
			                			case 'px':
			                			case 'rem':
			                			case '%':
			                				scope.txtModal = "";
			                				scope.$evalAsync(function() {
			                					scope.sizeModal = "";
			                				});
			                				
			                				if(unitySize == 'px'){
			                					numberSize = (numberSize/14).toFixed(4);
			                					unitySize = 'rem';
			                				}
			                				
			                				scope.sizeModalWidth = {
			    	                			'width' : numberSize+unitySize
			    	                		};
			                				break;
			                			default:
				                			scope.sizeModalWidth = {};
			                				sizeModalDefault();
			                		}
			                		
	                			} else {
	                				sizeModalDefault();
	                			}
	                		}
	                		
                	}
                } else {
                	sizeModalDefault();
                }
                
                scope.$watch(function(){return scope.visible;}, function(value){
                	
                	if(value == true){
                		$(element).modal('show');
                	}else{
                		$(element).modal('hide');
                	}
                });
                
                scope.safeApply = function(fn) {
                	var phase = this.$root.$$phase;
                	
                	if(phase == '$apply' || phase == '$digest') {
                		if(fn && (typeof(fn) === 'function')) {
                			fn();
                		}
                	} else {
                		this.$apply(fn);
                	}
                };
                
                $(element).on('shown.bs.modal', function(){
                	scope.safeApply(function(){
                		scope.$parent[attrs.visible] = true;
                	});
                });
                
                $(element).on('shown.bs.modal', function(){
                	scope.safeApply(function(){
                		scope.onShown({});
                	});
                });
                
                $(element).on('hidden.bs.modal', function(){
                	scope.safeApply(function(){
                		scope.$parent[attrs.visible] = false;
                	});
                });
                
                $(element).on('hidden.bs.modal', function(){
                	scope.safeApply(function(){
                		scope.onHide({});
                	});
                });
            }
        };
    }
);

app.directive('openModal', function() {
	return {
		replace: true,
		restrict: 'A',
		scope: {
			open: '@openModal'
		},
		link: function(s, e, a) {
			e.attr('data-toggle', 'modal');
			e.attr('data-target', '#'+s.open);
		}
	}
});

app.directive('modalHeader', function() {
    return {
        template:'<div class="modal-header">'
        		+	'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
        		+		'<span aria-hidden="true">&times;</span>'
        		+	'</button>'
        		+	'<h4 class="modal-title">{{title}}</h4>'
        		+'</div>',
        replace:true,
        restrict: 'E',
        scope: {
        	title:'@'
        }
    };
});

app.directive('modalBody', function() {
    return {
        template:'<div class="modal-body" ng-transclude></div>',
        replace:true,
        restrict: 'E',
        transclude: true
    };
});

app.directive('modalFooter', function() {
    return {
        template:'<div class="modal-footer">'
        		+	'<button class="btn btn-danger" data-dismiss="modal" ng-if="btnClose">'
        		+		'{{btnClose}}'
        		+	'</button>'
        		+	'<ng-transclude></ng-transclude>'
        		+'</div>',
        replace: true,
        restrict: 'E',
        transclude: true,
        scope: {
        	btnClose: '@'
        }
    };
});