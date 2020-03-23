/*
 * Author: César Gómez
 * Directive: selectpicker-directive
 * Versión: 1.0.0
 */

angular.module(appTeclo).directive('selectPicker', function($parse,$timeout) {
	return {
	      restrict: 'A',
	      require: '?ngModel',
	      priority: 10,
	      compile: function (tElement, tAttrs, transclude) {
	    	  return function (scope, element, attrs, ngModel) {
		          
	    		  if(attrs.idiomaSp == undefined) {
	    			  element.selectpicker({
	    				  noneSelectedText: 'Seleccione opciones',
		    			  noneResultsText: 'Sin coincidencias',
		    			  countSelectedText: '{0} Opciones seleccionadas',
		    			  maxOptionsText: 'Límite alcanzado ({n} opciones max)',
		    			  selectAllText: 'Todos',
		    			  deselectAllText: 'Deseleccionar',
		    			  doneButtonText: 'Cerrar'
	    			  });
	    		  }
	    		  
	    		  attrs.$observe('idiomaSp', function(lang) {
	    			  
	    			  element.selectpicker('destroy');
	    			  
	    			  $timeout(function() {
	    				  element.selectpicker('render');
	    			  }, 100);
	    			  
	    			  var noneSelected,
	    			  	  noneResults,
	    			  	  countSelected,
	    			  	  maxOptions,
	    			  	  selectAll,
	    			  	  deselectAll,
	    			  	  doneButton;
	    			  
	    			  switch(lang) {
	    			  	case 'es_ES':
	    			  		noneSelected  = 'Seleccione opciones',
		    			  	noneResults   = 'Sin coincidencias',
		    			  	countSelected = '{0} Opciones seleccionadas',
		    			  	maxOptions 	  = 'Límite alcanzado ({n} opciones max)',
		    			  	selectAll 	  = 'Todos',
		    			  	deselectAll   = 'Deseleccionar',
		    			  	doneButton 	  = 'Cerrar';
	    			  		break;
	    			  	case 'en_US':
	    			  		noneSelected  = 'Select options',
		    			  	noneResults   = 'Without coincidences',
		    			  	countSelected = '{0} Selected options',
		    			  	maxOptions 	  = 'Limit reached ({n} options max)',
		    			  	selectAll 	  = 'All',
		    			  	deselectAll   = 'Deselect',
		    			  	doneButton 	  = 'Close';
	    			  		break;
	    			  }
	    			  
	    			  element.selectpicker({
	    				  noneSelectedText: noneSelected,
		    			  noneResultsText: noneResults,
		    			  countSelectedText: countSelected,
		    			  maxOptionsText: maxOptions,
		    			  selectAllText: selectAll,
		    			  deselectAllText: deselectAll,
		    			  doneButtonText: doneButton
	    			  });
	    		  });
	    		  
	    		  if (!ngModel) return;
		          
		          if (attrs.ngOptions && / in /.test(attrs.ngOptions)) {
		        	  scope.$watch(attrs.ngOptions.split(' in ')[1], function() {
		        		  scope.$applyAsync(function () {
		        			  tElement.selectpicker('refresh');
		        		  });
		        	  }, true);
		          }
		          
		          scope.$watch(attrs.ngModel, function(nuevosVal, viejosVal) {
                    if (nuevosVal !== viejosVal) {
                        $timeout(function() {
                            element.selectpicker('val', element.val());
                        });
                    }
                });
		          
		          
	    	  };
	      }
	};
});