angular.module(appTeclo).directive('integerNumber', function(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {

            modelCtrl.$parsers.push(function (inputValue) {
                var transformedInput = inputValue ? inputValue.replace(/[^0-9]/g, '') : "";

                if (transformedInput!=inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});

angular.module(appTeclo).directive('integerDecimal', function() {
    return {
        require: 'ngModel',
        restrict: 'A',
        link: function(scope, element, attr, ctrl) {
            function inputValue(val) {
                if (val) {
                    var digits = val.replace(/[^0-9.]/g, '');

                    if (digits.split('.').length > 2) {
                        digits = digits.substring(0, digits.length - 1);
                    }

                    if (digits !== val) {
                        ctrl.$setViewValue(digits);
                        ctrl.$render();
                    }
                    return parseFloat(digits);
                }
                return "";
            }
            ctrl.$parsers.push(inputValue);
        }
    };
});

   angular.module(appTeclo).directive("limitToMax", function() {
        return {
          link: function(scope, element, attributes) {
            element.on("keydown keyup", function(e) {
          if (Number(element.val()) > Number(attributes.max) &&
                e.keyCode != 46 // delete
                &&
                e.keyCode != 8 // backspace
              ) {
                e.preventDefault();
                //element.val(attributes.max);
              }
            });
          }
        };
      });
      