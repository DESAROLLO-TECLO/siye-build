/**
 * @author: César Gómez
 * @version: 1.0.0
 * @name: Notificaciones TOAST
 */

const APP = angular.module(appTeclo);

APP.directive('toast', () => {

    return new Object({
//style="z-index:10022 !important;"
        restrict: 'E',
        template: '<div class="content-toast ngOnlyOut" ng-if="isToast">'
                +   '<div class="toast" ng-click="$event.stopPropagation()" ng-style="{\'max-width\':widthToast}">'
                +       '<div class="toast-header">'
                +           '<div class="icon-title">'
                +               '<div class="icon fadeInRight a-10ms"><i class="fa {{ iconToast }}"></i></div>'
                +               '<div class="title fadeIn a-10ms" ng-style="{\'text-transform\':uppercaseTitle ? \'uppercase\':\'none\'}">'
                +                   '{{ titleToast }}'
                +               '</div>'
                +           '</div>'
                +           '<div class="closetoast fadeInRight a-10ms" ng-if="!hiddenClose" ng-click="closeToast(\'time\')">'
                +               '<i class="fa fa-times"></i>'
                +           '</div>'
                +       '</div>'
                +       '<ng-transclude>'
                //+         '<!-- Contenido del toast -->'
                +       '</ng-transclude>'
                +   '</div>'
                +'</div>',
        transclude: true,
        replace: false,
        scope: {
            isToast: '=',
            iconToast: '@',
            titleToast:'@',
            widthToast: '@',
            hiddenClose: '=?',
            backdropToast: '=?',
            uppercaseTitle: '=?',
            onShow: '&', 
            onHide: '&'
        },

        /**
         * @scopes :
         * * isToast: Bandera para mostrar/ocultar el toast, por defecto se deja en false
         * * iconToast: Icono que se muestra en la cabecera del toast, por defecto es fa-info
         * * titleToast: Titulo del toast, si no se define por defecto se muestra el texto "titulo del toast"
         * * widthToast: Tamaño del toast, por defecto está en 36rem, se puede definir en pixeles npx, nrem, o porcentaje n%
         * * hiddenClose: Bandera para mostrar/ocultar el botón de cerrar en la cabecera del toast, por defecto es false para mostrarlo
         * * backdropToast: Bandera para habilitar/deshabilitar el cierre del toast al dar clic fuera de él, por defecto es false
         * * uppercaseTitle: Bandera para habilitar/deshabilitar el texto del título en mayúsculas, por defecto está habilitado
         * * onShow: Función que se ejecuta cuando se abre el toast (No es obligatorio definirlo)
         * * onHide: Función que se ejecuta cuando se cierra el toast (No es obligatorio definirlo)
         */
        link: postLink = (scope) => {

            if ( scope.isToast === undefined ) { scope.isToast = false; }         
            if ( scope.iconToast === undefined ) { scope.iconToast = 'fa-info'; }
            if ( scope.titleToast === undefined ) { scope.titleToast = '<título del toast>'; }
            if ( scope.widthToast === undefined ) { scope.widthToast = '36rem'; }
            if ( scope.hiddenClose === undefined ) { scope.hiddenClose = false; }
            if ( scope.backdropToast === undefined ) { scope.backdropToast = false; }
            if ( scope.uppercaseTitle === undefined ) { scope.uppercaseTitle = true; }

            scope.closeToast = ( type ) => {

                if ( scope.backdropToast && type === 'content') { return; }
                else if ( !scope.backdropToast || type === 'time' ) { scope.isToast = false; }

            };

            scope.$watch('isToast', (newValue, oldValue) => {

                if ( newValue === oldValue ) { return; }
                else if ( newValue && scope.onShow !== undefined ) { scope.onShow(); }
                else if ( !newValue && scope.onHide !== undefined ) { scope.onHide(); }

            });

        }
    });

});

APP.directive('toastContent', () => {

    return new Object({

        restrict: 'E',
        replace: true,
        transclude: true,
        template: '<div class="toast-body" ng-transclude></div>'

    });

});

APP.directive('toastFoot', () => {

    return new Object({

        restrict: 'E',
        replace: true,
        transclude: true,
        template: '<div class="toast-footer">'
                +    '<div class="centerRowAround" ng-if="loadToastFootDefault">'
                +        '<a class="btn fadeInDown a-10ms"'
                +            'ng-click="onCancel();closeToast()">'
                +            '{{ labelCancel }}'
                +        '</a>'
                +        '<button class="btn btn-danger fadeInDown a-10ms"'
                +            'ng-click="onSuccess();">'
                +            '{{ labelSuccess }}'
                +        '</button>'
                +    '</div>'
                +    '<ng-transclude ng-if="!loadToastFootDefault"></ng-transclude>'
                +  '</div>',
        scope: {
            loadToastFootDefault: '=?',
            labelCancel: '@',
            labelSuccess: '@',
            onCancel: '&',
            onSuccess: '&'
        },
        /**
         * @scopes :
         * * loadToastFootDefault: Cuando esta bandera se define en true se muestran los botones por defaul de Cerrar y Aceptar, por defecto es false
         * * labelCancel: Con esta propiedad es posible cambiar el texto del botón Cerrar
         * * labelSuccess: Con esta propiedad es posible cambiar el texto del botón Aceptar
         * * onCancel: En esta propiedad se define una función a ejectutar cuando se de click en Cerrar
         * * onSuccess: En esta propiedad se define una función a ejectutar cuando se de click en Aceptar
         */
        link: (scope) => {

            if ( scope.loadToastFootDefault === undefined ) { scope.loadToastFootDefault = false; }
            if ( scope.labelCancel === undefined ) { scope.labelCancel = 'Cerrar'; }
            if ( scope.labelSuccess === undefined ) { scope.labelSuccess = 'Aceptar'; }

            scope.closeToast = () => {
                scope.$parent.$parent.$parent.isToast = false;
            };
        }

    });

});
