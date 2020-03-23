/*
 * Author: César Gómez
 * Directive: webuiPopover-directive.js
 * Versión: 1.0.0
 */

angular.module(appTeclo).directive('webuipopover', function($timeout) {
    return {
    	restrict: 'EA',
        scope: {
        	webuiTitle: '@',
        	webuiOptions: '=?',
        	webuiPlacement: '@',
        	selectorContent: '@',
        	idiomaWup: '@'
         },
        link: function(scope, el, attr) {
        	
        	scope.$watch('idiomaWup', function(lang) {
        		
        		var selector = $('#'+scope.selectorContent);
        		
        		selector.css('display', 'none');
        		
        		$(el).webuiPopover('destroy');
        		
        		$timeout(function() {
        			var optionsWup = {
        				title: scope.webuiTitle,
    	            	placement: scope.webuiPlacement,
    	            	trigger: 'click',
    	            	backdrop: true,
    	            	animation: 'pop',
    	            	closeable: false,
    	            	dismissible: true,
    	            	autoHide: false,
    	            	url: selector
            		};
            		
            		$.extend(optionsWup, scope.webuiOptions);
            		
            		$(el).webuiPopover(optionsWup);
        		});
        	});
        }
    }
});