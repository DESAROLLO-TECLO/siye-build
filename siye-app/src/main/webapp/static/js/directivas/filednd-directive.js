/*
 * Author: César Gómez
 * Directive: filednd-directive
 * Versión: 1.0.0
 */

angular.module(appTeclo).directive('fileDnd', function (growl) {
    return {
        restrict: 'A',
        scope: {
            file: '=',
            fileName: '='
        },
        link: function (scope, element, attrs) {

            let validMimeTypes = `[${attrs.fileDnd}]`;
            const S = scope.$root.$$childTail;

            processDragOverOrEnter = function (event) {
                
                let dataTransfer = event.originalEvent.dataTransfer;

                if (event != null) {
                    event.preventDefault();
                }

                dataTransfer.effectAllowed = 'copy';

                return false;
            };

            checkSize = function (size) {
                
                let _ref;
                
                if (((_ref = attrs.maxFileSize) === (void 0) || _ref === '') || (size / 1024) / 1024 < attrs.maxFileSize) {
                    return true;
                } else {
                    growl.error('', {
                        title: S.mensajeModal('El archivo seleccionado excede el tamaño permitido de')+` ${attrs.maxFileSize}MB`,
                        ttl: 3000}
                    );

                    return false;
                }
            };

            isTypeValid = function (type) {

                if ((validMimeTypes === (void 0) || validMimeTypes === '') || validMimeTypes.indexOf(type) > 0) {
                    return true;
                } else {
                    growl.error('', {
                        title: S.mensajeModal('El archivo seleccionado no cumple con ninguno de los formatos')+`: ${attrs.fileDnd}`,
                        ttl: 5000}
                    );

                    return false;
                }
            };

            element.bind('dragover', processDragOverOrEnter);
            element.bind('dragenter', processDragOverOrEnter);

            return element.bind('drop', function (event) {

                let name, type, size;
                let dataTransfer = event.originalEvent.dataTransfer;

                const READER = new FileReader();
                const FILE = dataTransfer.files[0];
                
                if (event != null) {
                    event.preventDefault();
                }

                READER.onload = function (evt) {
                    if (checkSize(size) && isTypeValid(type)) {
                        return scope.$evalAsync(function () {
                            scope.file = evt.target.result;
                            if (angular.isString(scope.fileName)) {
                                return scope.fileName = name;
                            }
                            if(angular.isDefined(scope.$root.isChargedFile)) {
                                scope.$root.isChargedFile = true;
                            }
                        });
                    }
                };

                name = FILE.name;
                type = FILE.type;
                size = FILE.size;
                READER.readAsDataURL(FILE);
                
                return false;
            });
        }
    };
}).directive("fileread", [function () {
    return {
        scope: {
            fileread: '='
        },
        link: function (scope, element, attributes) {

            element.bind("change", function (changeEvent) {

                const READER = new FileReader();

                READER.onload = function (loadEvent) {
                    scope.$evalAsync(function () {
                        if(angular.isDefined(scope.$root.isChargedFile)) {
                            scope.$root.isChargedFile = true;
                        }
                        scope.fileread = loadEvent.target.result;
                    });
                }

                READER.readAsDataURL(changeEvent.target.files[0]);
            });
        }
    }
}]);