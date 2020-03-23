/*
 * Author: César Gómez
 * Directive: timepicker-directive
 * Versión: 1.0.0
 */

angular.module(appTeclo).directive('timePicker', function($timeout) {
    return {
    	restrict: 'A',
    	require: 'ngModel',
        scope: {
        	tpOptions: '=?'
        },
        link: function(scope, el, attr) {
        	
        	scope.$watch('tpOptions', function(newOptions) {
        		
        		var inputGroup = el.parent().hasClass('input-group') ? el.parent() : el;
        		
        		inputGroup.addClass('bootstrap-timepicker');
        		
        		$timeout(function() {
        			
        			var optionsTp = {
    					minuteStep: 1,
    					showSeconds: true,
    					secondStep: 1,
    					showMeridian: false,
    					maxHours: 24
            		};
            		
            		$.extend(optionsTp, newOptions);
            		
            		$(el).timepicker(optionsTp);
        		});
        	});
        }
    }
});