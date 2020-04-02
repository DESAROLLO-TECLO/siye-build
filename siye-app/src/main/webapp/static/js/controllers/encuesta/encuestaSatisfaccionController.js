angular.module(appTeclo)
.controller("encuestaSatisfaccionController",
function($rootScope,$scope,$window,$translate,$interval,$timeout,ModalService,showAlert,growl, $location,encuestaSatisfaccionService) {
	$scope.banderaPantalla=false;
	$scope.formato = '0';
    $scope.encuesta={};
    $scope.listOrden=new Array();
	$scope.parametroBusqueda =new Object();
    $scope.paramConfigPage = {
            bigCurrentPage: 1,
            bigTotalItems: 12,
            itemsPerPage: 4,
            maxSize: 12,
            tiempoEncuesta: 0,
            tiempoReanuda: false,
            tiempoCorriendo: false,
            flagTimer: true,
            segundo: 0,
            minuto: 0,
            hora: 0,
            flagFinalizaEncueta: false
        };
	
    $scope.posicionActual = -1;
    $scope.redireccionar = false;
    $scope.preguntasContestadasEncuesta = 0;

	
	//Se declaran tipos de busqueda
    $scope.listTipoBusqueda ={
        tipoBusqueda: [
            {idTipo:1,cdTipo:"ORDEN",nbTipo:"Orden"},
            {idTipo:2,cd:"PLACA",nbTipo:"Placa"},
            {idTipo:3,cdTipo:"VIN",nbTipo:"Vin"}
        ]
	};
	
		//Se enceustas de prueba
		$scope.listEncuesta=[{
			idEncuesta:1,estatus:"No Iniciado",cdColor:"#F81717",placa:"PLC001",ordenServicio:"OS001",fInicio:new Date("December 17, 1995 03:24:00"),cuestionario:"Instalacion"},
			{
				idEncuesta:1,estatus:"No Iniciado",cdColor:"#F81717",placa:"PLC001",ordenServicio:"OS001",fInicio:new Date("December 17, 1995 03:24:00"),cuestionario:"Plataforma"}
					]
			
	
    $scope.buscarOrden=function(param,form){
    	if (form.$invalid) {
    		showAlert.requiredFields(form);
			showAlert.error('Formulario Incompleto');
			
        }else
        encuestaSatisfaccionService.getEncuesta(param.tipoBusqueda.idTipo,param.valor,param.pass).success(function(data){
            if (data.length>0) 
                $scope.listOrden=data;
                else
                showAlert.aviso("No se encontraron concidencias con el valor ingresado");
          
        }).error(function(data){
            showAlert.error(data.message);
        });
    	
    };
		
		$scope.empezarEncuesta=function(idOrdenServicio,encuestaVO,accion){
            var idEncuesta= encuestaVO.idEncuesta;
            encuestaSatisfaccionService.cargarEncuesta(idOrdenServicio,idEncuesta).success(function(data) {
                if (data) {
                    $scope.banderaPantalla=accion;
                    $scope.encuesta=angular.copy(encuestaVO);
                    $scope.cambiarPregunta(null,$scope.encuesta.seccion[0]);
                    $scope.iniciarConteo();
                    $scope.paramConfigPage.tiempoEncuesta = 5700;
                    //reloj();
                }
            }).error(function(data) {
                growl.warning(data.message);
            });
			//alert($scope.encuesta.seccion[0]);
		};
    
//	Detectar el navegador para ajustar el contenido
	detectarNavegador = function(){

		var contFormulario = $('.cont-formuarioCliente');
		var bfCaret = $('.contSelectPhone');
		
		switch(deviceDetector.browser){
			case 'firefox':
				$scope.navegador = "firefox";
				contFormulario.addClass('comodinHeight');
				break;
			case 'chrome':
				$scope.navegador = "chrome";
				bfCaret.addClass('contSelectPhoneCr');
				bfCaret.removeClass('contSelectPhone');
				break;
			case 'ms-edge':
				$scope.navegador = "ms-edge";
				break;
			case 'ie':
				$scope.navegador = "ie";
				break;
			case 'safari':
				$scope.navegador = "safari";
				break;
			default:
				$scope.navegador = "Unvalued browser";
		}
		return $scope.navegador;
	}
	
	
	

	
	$scope.checkPregunta =function(opcion,respuesta){
		var evaluaContestadas=$scope.encuesta.seccion[$scope.posicionActual].nuPreguntasContestadas;
		var preguntasCont=evaluaContestadas!=undefined?evaluaContestadas:0;
		var cambio=false;

		if(respuesta.stMarcado==undefined || respuesta.stMarcado==0  || respuesta.stMarcado==null){
			$scope.encuesta.seccion[$scope.posicionActual].nuPreguntasContestadas=preguntasCont;
			$scope.encuesta.seccion[$scope.posicionActual].nuPreguntasContestadas++
			$scope.preguntasContestadasEncuesta++;
		}
		for (let i in respuesta.opciones) {
			respuesta.opciones[i].stMarcado=0;
		}

		for (let i in respuesta.opciones) {
			if (angular.equals(respuesta.opciones[i],opcion)) {
			respuesta.opciones[i].stMarcado=1;	
			respuesta.stMarcado=1;
		}
	  }	
	};
	
	//CAMBIAR PREGUNTA
    $scope.cambiarPregunta = function(nuPagina, seccionVO, instruccion) {
        var guardarSeccion = false;
        $scope.paramConfigPage.flagTimer = true;
        if (nuPagina != null) {
            guardarSeccion = $scope.guardaAvancePorPagina(nuPagina);
        }
        if (seccionVO != undefined || seccionVO != null) {
            $scope.posicionActual = $scope.encuesta.seccion.indexOf(seccionVO);
            var evaluaContestadas = $scope.encuesta.seccion[$scope.posicionActual].nuPreguntasContestadas != undefined ?
                $scope.encuesta.seccion[$scope.posicionActual].nuPreguntasContestadas : 0;
            $scope.seccionSeleccion = seccionVO.cdSeccion;
            $scope.seccionVO = seccionVO;
            $scope.encuesta.seccion[$scope.posicionActual].nuPreguntasContestadas = evaluaContestadas;
            $scope.paramConfigPage.bigTotalItems = $scope.seccionVO.preguntas.length;
            if (instruccion != 1) {
                $scope.paramConfigPage.bigCurrentPage = 1;
                document.getElementById("myTable2").scrollTop = 0;
                $('html, body').animate({ scrollTop: 0 }, 'slow');
            }
        } else {
            growl.warning("No se pudo cargar seccion", { ttl: 5000 });
        }
    };
    
    //INICIAR CONTEO
    $scope.iniciarConteo = function() {
        $scope.paramConfigPage.tiempoReanuda = true;
        iniciarConteo = $interval($scope.observaTiempo, 1000);

    };
    
    $scope.observaTiempo = function() {
        if ($scope.paramConfigPage.flagTimer) {
            if ($scope.paramConfigPage.tiempoEncuesta > 0) {
                $scope.paramConfigPage.tiempoEncuesta--;
                $scope.cambiaTiempo($scope.paramConfigPage.tiempoEncuesta);
            } else {
                $scope.paramConfigPage.segundo = 0;
                $scope.paramConfigPage.minuto = 0;
                $scope.paramConfigPage.hora = 0;
                $scope.paramConfigPage.flagTimer = false;
                $interval.cancel(iniciarConteo);
                $scope.guardaAvancePorPagina($scope.paramConfigPage.bigCurrentPage);
                $("#confirmacion").modal('hide'); //ocultamos el modal
                $('body').removeClass('modal-open'); //eliminamos la clase del body para poder hacer scroll
                $('.modal-backdrop').remove(); //eliminamos el backdrop del modal
                showAlert.aviso("Se ha terminado el tiempo de la evaluación", $scope.testConfirmacion);
            }

        }
    };

    $scope.cambiaTiempo = function(segundoRestante) {
        var segundoDB = segundoRestante;
        var segundos = segundoDB;
        var segundos_s = segundos % 60;
        var minutos = Math.floor(segundos / 60);
        var horas = Math.floor(minutos / 60);
        var minutos_s = minutos % 60;
        var dias = Math.floor(horas / 24);
        var horas_s = horas % 24;

        $scope.paramConfigPage.segundo = segundos_s;
        $scope.paramConfigPage.minuto = minutos_s;
        $scope.paramConfigPage.hora = horas_s;
    };

    //Guardar Avance por pagina
    $scope.guardaAvancePorPagina = function(numPagina) {
        if($scope.redireccionar){
            $interval.cancel(iniciarConteo);
        };
        if (document.getElementById("myTable2"))
            document.getElementById("myTable2").scrollTop = 0;
        $('html, body').animate({ scrollTop: 0 }, 'slow');
        var guardaSeccionPorPaginaVO = new Object();
        var preguntasPorPagina = new Array();
        var seccionVO = new Object();
        var seccionVO = angular.copy($scope.encuesta.seccion[$scope.posicionActual]);
        var preguntasPorPagina = seccionVO.preguntas.slice(((numPagina - 1) * $scope.paramConfigPage.itemsPerPage), ((numPagina) * $scope.paramConfigPage.itemsPerPage));
        seccionVO.preguntas = preguntasPorPagina;
        $scope.saveEncuesta(seccionVO);
    };
	//Escuchar  la variable de paginador
    $scope.$watch("paramConfigPage.bigCurrentPage", function(newValue, oldValue) {
        if (newValue === oldValue) {
            return;
        }
        $scope.guardaAvancePorPagina(oldValue);
    });
    
	$scope.guardaFinalizaEncuesta=function(numPagina){
		$scope.guardaAvancePorPagina(numPagina);
		$timeout(() => {
			$scope.finalizaEncuesta();
		}, 500);
	};
	
	////FUNCION PARA FINALIZAR ENCUESTA
	
	   //Funcion obtiene las respuestas por parametros de busqueda
    $scope.finalizaEncuesta = function(tiempo) {
        var lisseccionValid = new Array();
        var seccion = $scope.encuesta.seccion;
        var preguntasSinContestar = false;
        for (let i in seccion) {
            if (seccion[i].nuPreguntasContestadas < seccion[i].preguntas.length) {
                preguntasSinContestar = true;
                let comprobacion = new Object({
                    nbSeccion: seccion[i].nbSeccion,
                    preguntasSinContestar: seccion[i].preguntas.length - seccion[i].nuPreguntasContestadas,
                    preguntas: new Array()
                });
                for (let j in seccion[i].preguntas) {
                    var contador = 0;
                    for (let k in seccion[i].preguntas[j].opciones) {
                        if (contador == 0 && seccion[i].preguntas[j].opciones[k].stMarcado != 1) {
                            comprobacion.preguntas.push(seccion[i].preguntas[j]);
                            contador++
                        }
                    }

                }
                lisSeccionesValid.push(comprobacion);

            }
        }

        if (preguntasSinContestar) {
            growl.warning("Hay preguntas sin contestar en las secciones amarillas o rojas");
        } else {
            showAlert.confirmacion("¿Finalizar Evaluación?", $scope.testConfirmacion, $scope.object, $scope.testCancelConfirmacion);
        }
    };


    $scope.testCancelConfirmacion = function() {
       
    }

    $scope.testCancelConfirmacion2 = function() {
        // growl.info('',{title: ''});
        $scope.redireccionar = false;
       
    }

    $scope.testConfirmacion = function(object) {
    	$scope.banderaPantalla=false;
    	/*
        encuestaService.finalizaEncuesta($scope.detalleFinalEncuesta).success(function(data) {
            if (data != null) {
                $scope.paramConfigPage.segundo = 0;
                $scope.paramConfigPage.minuto = 0;
                $scope.paramConfigPage.hora = 0;
                $scope.paramConfigPage.flagTimer = false;
                ///$scope.saveStepTwo(); 
                $scope.asignarValores(data);
            } else {
                growl.success("No Se Pudo Finalizar la encuesta", { ttl: 5000 });
                guardarSeccion = false;
            }
        }).error(function(data) {
            growl.error(data.message);
        });
        */
    };



    //Guarda respuestas de encuesta
    $scope.saveEncuesta = function(seccionVO) {
        var guardarSeccion = false;
        var seccionContestada = new Array();
        var listPreguntaSeccion = angular.copy(seccionVO.preguntas);
        for (let i in listPreguntaSeccion) {
            let guardar = false;
            let objectEncuesta = new Object({
                idEncuesta: angular.copy($scope.encuesta.idEncuesta),
                idSeccion: angular.copy(seccionVO.idSeccion),
                idPregunta: undefined,
                idOpcion: undefined,
                idIntento: 7
            });

            objectEncuesta.idPregunta = listPreguntaSeccion[i].idPregunta;
            for (let j in listPreguntaSeccion[i].opciones) {
                if (listPreguntaSeccion[i].opciones[j].stMarcado === 1) {
                    guardar = true
                    objectEncuesta.idOpcion = listPreguntaSeccion[i].opciones[j].idOpcion != undefined ? listPreguntaSeccion[i].opciones[j].idOpcion : 0;
                }
            }
            if (guardar) {
                seccionContestada.push(objectEncuesta);
            }
        }
        encuestaSatisfaccionService.saveRespuestaEncuesta(seccionContestada).success(function(data) {
            if (data == true) {
                if ($scope.redireccionar) {
                    $timeout(() => {
                        $scope.regresarEncuestas()
                    }, 1000);
                }
                guardarSeccion = true;
                //  growl.success("Se guardaron respuestas ",{ ttl: 5000 });
            } else
                growl.success("No se pudieron guardar respuestas", { ttl: 5000 });
            guardarSeccion = false;

        }).error(function(data) {
            if (data != null && data.status != null && data.status == 400) {
                //$scope.paramConfigPage.tiempoReanuda = true;
                //$scope.paramConfigPage.flagFinalizaEncueta = true;
                //showAlert.aviso(data.message, $scope.regresarEncuestas2);
                // growl.error(data.message, { ttl: 3000 });
                /// $scope.consultaEvaEvaluados();
            } else {
                growl.error('Ha ocurrido un error al tratar de activar la evaluación, favor de validar.', { ttl: 4000 });
            }

            guardarSeccion = false;
        });
        return guardarSeccion;
    };

    
    

});