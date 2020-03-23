/*
 * Author: César Gómez
 * Directive: tooltip-directive.js
 * Versión: 1.0.0
 */

angular.module(appTeclo).directive('tooltip', function($rootScope, $timeout) {
	return{
		restrict: 'A',
		scope: {
			title: '@tooltip',
			tooltipPlacement: '@',
			tooltipOptions: '='
		},
		link: function($scope, el, attr) {
			
			var $rs = $rootScope,
				$sc = $scope,
				$tm = $timeout,
				$ex = $.extend,
				optionsTooltip = {};
			
			$rs.$watch('currentLanguage', function(newLang) {
				
				el.tooltip('destroy');
				
				$tm(function() {
					optionsTooltip = {
						title: $sc.title,
						placement: $sc.tooltipPlacement,
						container: false,
						trigger: 'hover',
						html: false	
					};
					
					$ex(optionsTooltip, $sc.tooltipOptions);
					
					el.tooltip(optionsTooltip);
					
				}, 200);
			});
			
		}
	}
});