/*
 * Author: César Gómez
 * Directive: datetimepicker-directive
 * Versión: 1.0.0
 */

(function () {
    'use strict';

    var module = angular.module('ae-datetimepicker', []);

    module.directive('datetimePicker', [
        '$timeout',
        function ($timeout) {
            return {
                restrict: 'EA',
                require: 'ngModel',
                scope: {
                    options: '=?',
                    idiomaDtp: '@',
                    onChange: '&?',
                    onClick: '&?',
                    onHide: '&?'
                },
                link: function ($scope, $element, $attrs, ngModel) {
                	
                	$scope.$watch('idiomaDtp', function(lang) {

                		var dpElement = $element.parent().hasClass('input-group') ? $element.parent() : $element;
                		
                		var dtmtoday 		 = '',
	                		dtmclear 		 = '',
	                		dtmclose 		 = '',
	                		dtmselectTime	 = '',
	                		dtmselectMonth   = '',
	                		dtmprevMonth 	 = '',
	                		dtmnextMonth 	 = '',
	                		dtmselectYear 	 = '',
	                		dtmprevYear 	 = '',
	                		dtmnextYear 	 = '',
	                		dtmselectDecade  = '',
	                		dtmprevDecade 	 = '',
	                		dtmnextDecade 	 = '',
	                		dtmprevCentury   = '',
	                		dtmnextCentury   = '';
                		
	                    switch(lang) {
							case "es_ES" :
								lang = 'es';
								dtmtoday 		 = 'Ir a hoy',
								dtmclear 		 = 'Limpiar selección',
								dtmclose 		 = 'Cerrar el componente',
								dtmselectTime	 = 'Seleccionar hora',
								dtmselectMonth   = 'seleccione el mes',
								dtmprevMonth 	 = 'Mes anterior',
								dtmnextMonth 	 = 'Próximo mes',
								dtmselectYear 	 = 'Seleccione el año',
								dtmprevYear 	 = 'Mes anterior',
								dtmnextYear 	 = 'Próximo mes',
								dtmselectDecade  = 'Seleccione la década',
								dtmprevDecade 	 = 'Década anterior',
								dtmnextDecade 	 = 'Prómixa década',
								dtmprevCentury   = 'Siglo anterior',
								dtmnextCentury   = 'Próximo siglo';
								break;
							case "en_US" :
								lang = 'en';
								dtmtoday 		 = 'Go to today',
								dtmclear 		 = 'Clear selection',
								dtmclose 		 = 'Close the picker',
								dtmselectTime	 = 'Select Time',
								dtmselectMonth   = 'Select Month',
								dtmprevMonth 	 = 'Previous Month',
								dtmnextMonth 	 = 'Next Month',
								dtmselectYear 	 = 'Select Year',
								dtmprevYear 	 = 'Previous Year',
								dtmnextYear 	 = 'Next Year',
								dtmselectDecade  = 'Select Decade',
								dtmprevDecade 	 = 'Previous Decade',
								dtmnextDecade 	 = 'Next Decade',
								dtmprevCentury   = 'Previous Century',
								dtmnextCentury   = 'Next Century';
								break;
							default:
								lang = 'es';
								dtmtoday 		 = 'Ir a hoy',
								dtmclear 		 = 'Limpiar selección',
								dtmclose 		 = 'Cerrar el componente',
								dtmselectTime	 = 'Seleccionar hora',
								dtmselectMonth   = 'seleccione el mes',
								dtmprevMonth 	 = 'Mes anterior',
								dtmnextMonth 	 = 'Próximo mes',
								dtmselectYear 	 = 'Seleccione el año',
								dtmprevYear 	 = 'Mes anterior',
								dtmnextYear 	 = 'Próximo mes',
								dtmselectDecade  = 'Seleccione la década',
								dtmprevDecade 	 = 'Década anterior',
								dtmnextDecade 	 = 'Prómixa década',
								dtmprevCentury   = 'Siglo anterior',
								dtmnextCentury   = 'Próximo siglo';
				    	}
	                    
	                	$element.datetimepicker({
	                		toolbarPlacement: 'bottom',
	                		showTodayButton: true,
	                		showClear: true,
	                		showClose: true,
	                		ignoreReadonly: true,
	                		allowInputToggle: true,
	                	});
	                	
	                    $scope.$watch('options', function (newValue) {
	                        var dtp = dpElement.data('DateTimePicker');
	                        $.map(newValue, function (value, key) {
	                            dtp[key](value);
	                        });
	                    }, true);
	
	                    ngModel.$render = function () {
	                        // if value is undefined/null do not do anything, unless some date was set before
	                        var currentDate = dpElement.data('DateTimePicker').date();
	                        if (!ngModel.$viewValue && currentDate) {
	                            dpElement.data('DateTimePicker').clear();
	                        } else if (ngModel.$viewValue) {
	                            // otherwise make sure it is moment object
	                            if (!moment.isMoment(ngModel.$viewValue)) {
	                                ngModel.$setViewValue(moment(ngModel.$viewValue));
	                            }
	                            dpElement.data('DateTimePicker').date(ngModel.$viewValue);
	                        }
	                    };
	
	                    var isDateEqual = function (d1, d2) {
	                        return moment.isMoment(d1) && moment.isMoment(d2) && d1.valueOf() === d2.valueOf();
	                    };
	
	                    dpElement.on('dp.change', function (e) {
	                        if (!isDateEqual(e.date, ngModel.$viewValue)) {
	                            var newValue = e.date === false ? null : e.date;
	                            ngModel.$setViewValue(newValue);
	
	                            $timeout(function () {
	                                if (typeof $scope.onChange === 'function') {
	                                    $scope.onChange();
	                                }
	                            });
	                        }
	                    });
	                    
	                    dpElement.on('dp.hide', function (e) {
	                    	$timeout(function() {
	                    		if(typeof $scope.onHide === 'function') {
	                    			$scope.onHide();
	                    		}
	                    	});
	                    });
	
	                    dpElement.on('click', function () {
	                        $timeout(function () {
	                            if (typeof $scope.onClick === 'function') {
	                                $scope.onClick();
	                            }
	                        });
	                    });
	                    
	                    if($scope.options != undefined) {
	                    	$scope.options.locale = moment.locale(lang);
	                    	$scope.options.tooltips = 
	                    		{
	                    			today: dtmtoday,
		                            clear: dtmclear,
		                            close: dtmclose,
		                            selectTime: dtmselectTime,
		                            selectMonth: dtmselectMonth,
		                            prevMonth: dtmprevMonth,
		                            nextMonth: dtmnextMonth,
		                            selectYear: dtmselectYear,
		                            prevYear: dtmprevYear,
		                            nextYear: dtmnextYear,
		                            selectDecade: dtmselectDecade,
		                            prevDecade: dtmprevDecade,
		                            nextDecade: dtmnextDecade,
		                            prevCentury: dtmprevCentury,
		                            nextCentury: dtmnextCentury
	                    		};
	                    }else{
	                    	$scope.options = {locale : lang};
	                    }
	                    
	                    dpElement.datetimepicker($scope.options);
                    });
                }
            };
        }
    ]);
})();