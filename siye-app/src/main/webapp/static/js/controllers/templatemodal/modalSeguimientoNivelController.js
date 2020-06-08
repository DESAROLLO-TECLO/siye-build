angular.module(appTeclo).controller('modalSeguimientoNivelController',
    function ($rootScope, $scope, $location, $document, showAlert, growl, ModalService, modalVO) {

        let informacionVO = angular.copy(modalVO);

        $scope.paramConfigPage = {
            bigCurrentPage: 1,
            bigTotalItems: 0,
            itemsPerPage: 1,
            maxSize: 2
        };

        $scope.infoOS = new Object({
            cdOrdenServicio: null,
            nivel: null,
            nbEncuesta: null,
            fechaIncio: null,
            fechaFin: null,
            transcurrido: null
        });

        $scope.propsIncidencia = {};
        $scope.elemento = new Object({
            listaImagen:[]
        });

        // Metodo para el funcionamiento del modal
        inicializarModal = function () {
            debugger
            $scope.infoOS.nivel = informacionVO.nivel;
            $scope.infoOS.cdOrdenServicio = informacionVO.data.cdOrdenServicio;
            $scope.infoOS.listIncidenciasNivel = informacionVO.data.listIncidenciasNivel;
            switch (informacionVO.nivel) {
                case 'pregunta':
                    $scope.infoOS.nbEncuesta = informacionVO.nbEncuesta;
                    $scope.ListaEvidencias = informacionVO.data.nivelPreguntas;
                    $scope.paramConfigPage.bigTotalItems = $scope.ListaEvidencias.length;
                    break;

                case 'encuesta':
                    $scope.infoOS.nbEncuesta = informacionVO.infoEncuesta.nbEncuesta;
                    $scope.infoOS.fechaIncio = informacionVO.infoEncuesta.fhInicio;
                    $scope.infoOS.fechaFin = informacionVO.infoEncuesta.fhFin;
                    $scope.infoOS.transcurrido = informacionVO.infoEncuesta.fhDiferencia;
                    $scope.infoOS.status = informacionVO.infoEncuesta.estatus;
                    $scope.infoOS.colorStatus = informacionVO.infoEncuesta.nbColor;
                    $scope.ListaEvidencias = informacionVO.data.nivelEncuesta;
                    $scope.elemento.listaImagen = informacionVO.data.nivelEncuesta;
                    break;

                case 'ordenservicio':
                    $scope.elemento = new Object({
                        listaImagen: []
                    });
                    $scope.infoOS.nivelOS = informacionVO.infoOS;
                    $scope.infoOS.centroInstalacion = informacionVO.data.centroInstalacion;
                    $scope.ListaEvidencias = informacionVO.data.nivelOrdenServicio;
                    $scope.elemento.listaImagen = informacionVO.data.nivelOrdenServicio;
                    break;
            }
        };

        /// Genericas  colorPrioridad
        $scope.cerrarModal = function () {
            $('.modal-backdrop').remove();
            close(null, null);
        }

        $scope.$watch('active', function (newIndex, oldIndex) {
            if (Number.isFinite(newIndex) && newIndex !== oldIndex) {
                if($scope.ListaEvidencias!=undefined){
                    $scope.propsIncidencia = $scope.ListaEvidencias[newIndex];
                }
            }
        });

        // Inicio del Modal
        inicializarModal();

    });