/*
 * Author: Jesús Gutierrez
 * Directive: datepicker-directive
 * Versión: 1.1.0
 */

angular.module(appTeclo).directive('datePicker', function() {
    return {
        require: 'ngModel',
        scope: {
        	mindate: '=',
        	maxdate: '=',
        	idiomaDp: '@',
        	elemento: '='
         },
        link: function(scope, el, attr, ngModel) {
        	
        	scope.$watch('idiomaDp', function(lang){
        		
        		$(el).datepicker("remove");
        		
        		switch(lang) {
					case "es_ES" :
						lang = 'es';
						break;
					case "en_US" :
						lang = 'en';
						break;
					default:
						lang = 'es';
		    	}
        	
	            $(el).datepicker({
	                format: 'dd/mm/yyyy',
	                autoclose: true,
	                language: lang,
	                todayHighlight: true,
	                todayBtn: "linked"
	            }).on('changeDate', function (selected) {
	            	
	            	if(scope.maxdate != undefined){
	    	        	$('#'+scope.elemento).datepicker('setEndDate', scope.maxdate);
	            	}
	            	
	            	if(scope.mindate != undefined){
	    	        	$('#'+scope.elemento).datepicker('setStartDate', scope.mindate);
	            	}
	            	
		    	});
        		
        	});
        }
    }
});