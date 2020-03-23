/*
 * Author: Francisco Martínez
 * Directive: progressBar
 * Versión: 1.0.0
 */

angular.module(appTeclo).directive('progressBar', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var watchFor = attrs.progressBarWatch;

			// update now
			var val = scope[watchFor];
			element.attr('aria-valuenow', val).css('width', val + "%");

			// watch for the value
			scope.$watch(watchFor, function(val) {
				element.attr('aria-valuenow', val).css('width', val + "%");
			})
		}
	}
})

