/*
 * Author: César Gómez
 * Directive: popoverBootstrap-directive
 * Versión: 1.0.0
 */

angular.module(appTeclo).directive('popover', function($timeout) {
    return {
    	restrict: 'EA',
        scope: {
        	title: '@',
        	options: '=?',
        	placement: '@',
        	content: '@',
        	idiomaPb: '@'
         },
        link: function(scope, el, attr) {
        	
        	scope.$watch('idiomaPb', function(lang) {

        		$(el).popover('destroy');
        		
        		$timeout(function() {
        			
        			var optionsPb = {
        				container: 'body',
        				title: scope.title,
    	            	placement: scope.placement,
    	            	animation: true,
    	            	trigger: 'hover',
    	            	content: scope.content
            		};
            		
            		$.extend(optionsPb, scope.options);
            		
            		$(el).popover(optionsPb);
        		}, 300);
        	});
        }
    }
});