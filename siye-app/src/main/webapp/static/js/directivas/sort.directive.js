/*
 * Author: Ángel Oropeza
 * Directive: sort.directive.js
 * Versión: 1.0.0
 */

(function () {
    'use strict';

    angular
        .module(appTeclo)
        .directive('sort', sort);

    function sort() {
        var sortAsc = 'fa-sort-amount-asc',
            sortDesc = 'fa-sort-amount-desc';
        var directive = {
            restrict: 'A',
            transclude: true,
            template: '<a ng-click="onClick()" class="ordenCampos">' +
            '<span ng-transclude></span> ' +
            '<i class="fa" ng-class="{\'' + sortAsc + '\' : order === by && !reverse,  \'' + sortDesc + '\' : order===by && reverse}"></i>' +
            '</a>',
            scope: {
                order: '=',
                by: '=',
                reverse: '='
            },
            link: linkFunc
        };

        function linkFunc(scope, element, attrs) {
            scope.onClick = function () {
                if (scope.order === scope.by) {
                    scope.reverse = !scope.reverse
                } else {
                    scope.by = scope.order;
                    scope.reverse = false;
                }
            }
        }

        return directive;
    }
})();
