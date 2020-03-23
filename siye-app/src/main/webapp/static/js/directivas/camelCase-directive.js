/*
 * Author: César Gómez
 * Directive: camelCase-directive
 * Versión: 1.0.0
 */

angular.module(appTeclo).directive('camelCase', function() {
	 return {
	     require: 'ngModel',
	     link: function(scope, element, attrs, modelCtrl) {
	    	 var camelcase = function(input){
	    		 var convert = '';
	    		 if (input == undefined) input = '';
	    		 
		    	 if(input.indexOf(' ') !== -1){
		    	      var inputPieces, i;
	
		    	      input = input.toLowerCase();
		    	      inputPieces = input.split(' ');
	
		    	      for(i = 0; i < inputPieces.length; i++){
		    	        inputPieces[i] = capitalizeString(inputPieces[i]);
		    	      }
		    	      
		    	      convert = inputPieces.toString().replace(/,/g, ' ');
	    	 		}
		    	    else {
		    	      input = input.toLowerCase();
		    	      convert = capitalizeString(input);
		    	    }
		    	 
		    	 	if(convert !== input){
		    	 		modelCtrl.$setViewValue(convert);
	          			modelCtrl.$render();
		    	 	}
		    	 	
		    	 	return convert;
	
		    	    function capitalizeString(inputString){
		    	      return inputString.substring(0,1).toUpperCase() + inputString.substring(1);
		    	    }  
	    	 	}
	    	 
	    	 	modelCtrl.$parsers.push(camelcase);
	    	    camelcase(scope[attrs.ngModel]);
	     	}
	   };
});