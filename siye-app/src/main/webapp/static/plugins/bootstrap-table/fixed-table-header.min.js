/**
 * AngularJS fixed header scrollable table directive
 * @author Jason Watmore <jason@pointblankdevelopment.com.au> (http://jasonwatmore.com)
 * @version 1.1.0
 */
angular.module('siidfApp').directive('fixedHeader', ['$timeout', function ($timeout) {
        return {
            restrict: 'A',
            scope: {
                //tableHeight: '@',
               result: '=',
               filter2: '='
            },
            link: function ($scope, $elem, $attrs) {
                
            	function isVisible(el) {
                    var style = window.getComputedStyle(el);
                    return (style.display != 'none' && el.offsetWidth !=0 );
                }

                function isTableReady() {
                    return isVisible(table) && angular.element(table.querySelector('tbody tr:first-child')) != null;
                }

                var table = $elem[0];
                // wait for content to load into table and to have at least one row, tdElems could be empty at the time of execution if td are created asynchronously (eg ng-repeat with promise)
                var unbindWatch = $scope.$watch('filter2', function (newValues, oldValues) {
                        if (newValues) { //=== true
                            // reset display styles so column widths are correct when measured below
                        	angular.element(table.querySelectorAll('thead, tbody, tfoot')).css('display', '')

                            // wrap in $timeout to give table a chance to finish rendering
                            $timeout(function () {
                                // set widths of columns
                               
                            	angular.forEach(table.querySelectorAll('tr:first-child th'), function (thElem, i) {

                            		var tdElems = table.querySelector('tbody tr:first-child td:nth-child(' + (i + 1) + ')');
                                    //var tfElems = elem.querySelector('tfoot tr:first-child td:nth-child(' + (i + 1) + ')');


                                    var columnWidth = tdElems ? tdElems.offsetWidth : thElem.offsetWidth;
                                    if(tdElems) {
                                        tdElems.style.width = columnWidth + 'px';
                                    }
                                    if(thElem) {
                                        thElem.style.width = columnWidth + 'px';
                                    }
                                    
                                  //if (tfElems) {
                                       //tfElems.style.width = columnWidth + 'px';
                                  //}
                                });

                                // set css styles on thead and tbody
                                angular.element(table.querySelectorAll('thead, tfoot')).css('display', 'block');
                                angular.element(table.querySelectorAll('tbody')).css({
                                	'display': 'block',
                                	'height': $scope.tableHeight || 'inherit',
                                	'overflow': 'auto',
                                });
                                
                                // reduce width of last column by width of scrollbar
                                var tbody = table.querySelector('tbody');
                                var scrollBarWidth = tbody.offsetWidth - tbody.clientWidth;
                                if (scrollBarWidth > 0) {
                                    // for some reason trimming the width by 2px lines everything up better
                                    scrollBarWidth -= 2;
                                    var lastColumn = table.querySelector('tbody tr:first-child td:last-child');
                                    lastColumn.style.width = (lastColumn.offsetWidth - scrollBarWidth) + 'px';
                                }
                            });

                            //we only need to watch once
                            unbindWatch();
                        }
                    });
            }
        };
    }]);
