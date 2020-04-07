angular.module(appTeclo)
.controller("encuestaController",
function($rootScope,$scope,$window,$translate,$timeout,ModalService,encuestaInfo,encuestaService,growl,showAlert,$location,$interval,idpro) {
    
	$scope.idProcesoActual=idpro;
    $scope.nombEncuesta = $rootScope.nomSeguimiento + " - Encuesta " + encuestaInfo.data.encuesta.nbEncuesta;
    $scope.nombSeccion = encuestaInfo.data.encuesta.secciones[0].nbSeccion;
    $scope.seccEncuesta = encuestaInfo.data.encuesta.secciones[0];
    $scope.objOpciones = new Object(
        {val:1,nom:'Opción 1'},
        {val:2,nom:'Opción 2'},
        {val:3,nom:'Opción 3'},
        {val:4,nom:'Opción 4'},
        {val:5,nom:'Opción 5'},
        {val:6,nom:'Opción 6'},
        {val:7,nom:'Opción 7'},
        {val:8,nom:'Opción 8'},
        {val:9,nom:'Opción 9'},
        {val:10,nom:'Opción 10'}
    );
	
	
	$scope.paramConfigPage = {
            bigCurrentPage: 1,
            bigTotalItems: 0,
            itemsPerPage: 0,
            maxSize: 0,
            tiempoEncuesta: 0,
            tiempoReanuda: false,
            tiempoCorriendo: false,
            flagTimer: true,
            segundo: 0,
            minuto: 0,
            hora: 0,
            flagFinalizaEncueta: false
        };
	
    $scope.detalleFinalEncuesta = {
            idUsuEncuIntento:encuestaInfo.data.intentoDetalleVO.idUsuEncuIntento,
            usuario: null,
            fhInicio: null,
            fhFin: null,
            StEncuestaVO: null,
            nuCalificacion: null,
            stCalificacion: null,
            stMostrar: null,
            nuPreguntas: null,
            nuPreguntasCorrectas: null,
            nuPreguntasIncorr: null,
            nuPreguntasNoRespondidas: null
        };
	

    $scope.preguntasContestadasEncuesta = 0;

	
 
	$scope.getNumPreguntasPorSeccion=function(cdParametro){
		encuestaService.getNumPreguntasPorSeccion(cdParametro).success(function(data) {
		 var a=parseInt(data.cdValorPConfig);
		 $scope.paramConfigPage.itemsPerPage=a;
		}).error(function(data) {
		  growl.warning(data.message);
		});
	};

	$scope.getNumMaxPaginacion=function(cdParametro){
		encuestaService.getNumPreguntasPorSeccion(cdParametro).success(function(data) {
		 var b=parseInt(data.cdValorPConfig);
		 $scope.paramConfigPage.maxSize=b;
		}).error(function(data) {
		  growl.warning(data.message);
		});
	};
     
     $scope.getEncuestaOrden = function(data) {
         if (data != null) {
             var detalle = data.data;
             $scope.encuestaDetalle;
             for (let i in detalle.encuesta.secciones) {
                 for (let j in detalle.encuesta.secciones[i].preguntas) {
                     for (const k in detalle.encuesta.secciones[i].preguntas[j].opciones) {
                         if (detalle.encuesta.secciones[i].preguntas[j].opciones[k].stMarcado == 1) {
                        	 cargar=false;
                             $scope.preguntasContestadasEncuesta++;
                             detalle.encuesta.secciones[i].nuPreguntasContestadas++;
                             detalle.encuesta.secciones[i].preguntas[j].stMarcado = 1;
                         }
                     }
                 }
             }

             $scope.encuestaDetalle = detalle;
             /*$scope.paramConfigPage.tiempoEncuesta = detalle.encuesta.nuTiempo;
             var estatusEncuesta = $scope.encuestaDetalle.intentoDetalleVO.stEncuesta.cdStEncuesta;
             if (estatusEncuesta == 'EC') {
                 $scope.paramConfigPage.tiempoCorriendo = true;
             }*/
             if(!$scope.encuestaDetalle.intentoDetalleVO.resumenRespuesta)
        	 {
            	 $scope.cargar($scope.encuestaDetalle.encuesta.idEncuesta,$scope.encuestaDetalle.usuario.idOrdenServicio);
        	 }
             else
            	 {
 				$scope.cambiarPregunta(null,$scope.encuestaDetalle.encuesta.secciones[0]);
				//$scope.iniciarConteo();
            	 }
             
             

         } else {
             growl.warning("Sin evaluaciones por asignar", { ttl: 5000 });
         }

     };
     
 	$scope.cargar = function(idEncuesta,idOrdenServicio) {	
		encuestaService.cargarEncuesta(idEncuesta,idOrdenServicio).success(function(data) {
			if (data) {
				$scope.cambiarPregunta(null,$scope.encuestaDetalle.encuesta.secciones[0]);
				//$scope.iniciarConteo();
			}
		}).error(function(data) {
			growl.warning(data.message);
		});
	};
	
	
	
    $scope.cambiarPregunta = function(nuPagina, seccionVO, instruccion) {
        var guardarSeccion = false;
        $scope.paramConfigPage.flagTimer = true;
        if (nuPagina != null) {
            guardarSeccion = $scope.guardaAvancePorPagina(nuPagina);
        }
        if (seccionVO != undefined || seccionVO != null) {
            $scope.posicionActual = $scope.encuestaDetalle.encuesta.secciones.indexOf(seccionVO);
            var evaluaContestadas = $scope.encuestaDetalle.encuesta.secciones[$scope.posicionActual].nuPreguntasContestadas != undefined ?
                $scope.encuestaDetalle.encuesta.secciones[$scope.posicionActual].nuPreguntasContestadas : 0;
            $scope.seccionSeleccion = seccionVO.cdSeccion;
            $scope.seccionVO = seccionVO;
            $scope.encuestaDetalle.encuesta.secciones[$scope.posicionActual].nuPreguntasContestadas = evaluaContestadas;
            $scope.paramConfigPage.bigTotalItems = $scope.seccionVO.preguntas.length;
            if (instruccion != 1) {
                $scope.paramConfigPage.bigCurrentPage = 1;
               // document.getElementById("myTable").scrollTop = 0;
                //$('html, body').animate({ scrollTop: 0 }, 'slow');
            }
        } else {
            growl.warning("No se pudo cargar seccion", { ttl: 5000 });
        }
    };
    
    //
    $scope.guardaAvancePorPagina = function(numPagina) {
        if($scope.redireccionar){
            $interval.cancel(iniciarConteo);
        };
        if (document.getElementById("myTable"))
            document.getElementById("myTable").scrollTop = 0;
        $('html, body').animate({ scrollTop: 0 }, 'slow');
        var guardaSeccionPorPaginaVO = new Object();
        var preguntasPorPagina = new Array();
        var seccionVO = new Object();
        var seccionVO = angular.copy($scope.encuestaDetalle.encuesta.secciones[$scope.posicionActual]);
        var preguntasPorPagina = seccionVO.preguntas.slice(((numPagina - 1) * $scope.paramConfigPage.itemsPerPage), ((numPagina) * $scope.paramConfigPage.itemsPerPage));
        seccionVO.preguntas = preguntasPorPagina;
        $scope.saveEncuesta(seccionVO);
    };
    
    $scope.saveEncuesta = function(seccionVO) {
        var guardarSeccion = false;
        var seccionContestada = new Array();
        var listPreguntaSeccion = angular.copy(seccionVO.preguntas);
        for (let i in listPreguntaSeccion) {
            let guardar = false;
            let objectEncuesta = new Object({
                idEncuesta: angular.copy($scope.encuestaDetalle.encuesta.idEncuesta),
                idSeccion: angular.copy(seccionVO.idSeccion),
                idPregunta: undefined,
                idOpcion: undefined,
                idIntento: $scope.encuestaDetalle.intentoDetalleVO.idUsuEncuIntento,
                causas: undefined
            });

            objectEncuesta.idPregunta = listPreguntaSeccion[i].idPregunta;
            for (let j in listPreguntaSeccion[i].opciones) {
                if (listPreguntaSeccion[i].opciones[j].stMarcado === 1) {
                    guardar = true
                    objectEncuesta.idOpcion = listPreguntaSeccion[i].opciones[j].idOpcion != undefined ? listPreguntaSeccion[i].opciones[j].idOpcion : 0;
                    objectEncuesta.causas=listPreguntaSeccion[i].opciones[j].causas;
                }
            }
            if (guardar) {
                seccionContestada.push(objectEncuesta);
            }
        }
        encuestaService.saveRespuestaEncuesta(seccionContestada).success(function(data) {
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
                $scope.paramConfigPage.tiempoReanuda = true;
                $scope.paramConfigPage.flagFinalizaEncueta = true;
                showAlert.aviso(data.message, $scope.regresarEncuestas2);
                // growl.error(data.message, { ttl: 3000 });
                /// $scope.consultaEvaEvaluados();
            } else {
                growl.error('Ha ocurrido un error al tratar de activar la evaluación, favor de validar.', { ttl: 4000 });
            }

            guardarSeccion = false;
        });
        return guardarSeccion;
    };
    
	$scope.guardaFinalizaEncuesta=function(numPagina){
		$scope.guardaAvancePorPagina(numPagina);
		$timeout(() => {
			$scope.finalizaEncuesta();
		}, 500);
	};


$scope.checkPregunta =function(opcion,respuesta){
	var evaluaContestadas=$scope.encuestaDetalle.encuesta.secciones[$scope.posicionActual].nuPreguntasContestadas;
	var preguntasCont=evaluaContestadas!=undefined?evaluaContestadas:0;
	var cambio=false;

	if(respuesta.stMarcado==undefined || respuesta.stMarcado==0  || respuesta.stMarcado==null){
		$scope.encuestaDetalle.encuesta.secciones[$scope.posicionActual].nuPreguntasContestadas=preguntasCont;
		$scope.encuestaDetalle.encuesta.secciones[$scope.posicionActual].nuPreguntasContestadas++
		$scope.preguntasContestadasEncuesta++;
	}
	for (let i in respuesta.opciones) {
		respuesta.opciones[i].stMarcado=0;
	}


	for (let i in respuesta.opciones) {
		if (angular.equals(respuesta.opciones[i],opcion)) {
		respuesta.opciones[i].stMarcado=1;;
		respuesta.stMarcado=1;
	}
      }
	if(opcion.cdMostrarCausas)
	{
	filtroCausas(opcion,respuesta,false);
	}
	if(!opcion.cdMostrarCausas)
		{
		for (let i in respuesta.opciones) {
			respuesta.opciones[i].causas=null;
	      }
		}

	
};

//Escuchar  la variable de paginador
$scope.$watch("paramConfigPage.bigCurrentPage", function(newValue, oldValue) {
    if (newValue === oldValue) {
        return;
    }
    $scope.guardaAvancePorPagina(oldValue);
});

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

$scope.finalizaEncuesta = function(tiempo) {
    var lisSeccionesValid = new Array();
    var secciones = $scope.encuestaDetalle.encuesta.secciones;
    var preguntasSinContestar = false;
    for (let i in secciones) {
        if (secciones[i].nuPreguntasContestadas < secciones[i].preguntas.length) {
            preguntasSinContestar = true;
            let comprobacion = new Object({
                nbSeccion: secciones[i].nbSeccion,
                preguntasSinContestar: secciones[i].preguntas.length - secciones[i].nuPreguntasContestadas,
                preguntas: new Array()
            });
            for (let j in secciones[i].preguntas) {
                var contador = 0;
                for (let k in secciones[i].preguntas[j].opciones) {
                    if (contador == 0 && secciones[i].preguntas[j].opciones[k].stMarcado != 1) {
                        comprobacion.preguntas.push(secciones[i].preguntas[j]);
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
        showAlert.confirmacion("¿Finalizar Evaluación?", $scope.testConfirmacion, $scope.object);
    }
};

$scope.testConfirmacion = function(object) {
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
};

//    Asignar Valores
$scope.asignarValores = function(datos) {
    $scope.paramConfigPage.flagFinalizaEncueta = datos != null;
    //$interval.cancel(iniciarConteo);
    $scope.paramConfigPage.tiempoReanuda = false;
    $scope.estatusFinalizaEncuesta();
    $scope.detalleFinalEncuesta = angular.copy(datos);
    $scope.encuestaDetalleFinal = angular.copy($scope.encuestaDetalle);
    growl.success("La evaluación se finalizó", { ttl: 5000 });
};

$scope.estatusFinalizaEncuesta = function() {
	$location.path('/etapas/proceso/'+$scope.idProcesoActual+'/'+$scope.encuestaDetalle.usuario.idOrdenServicio);
   /* $timeout(() => {
        let wizardExampled = WizardHandler.wizard('wizardExample');
        if ($scope.paramConfigPage.flagFinalizaEncueta) {
            if (wizardExampled)
                wizardExampled.goTo(2); // se envia al paso incompleto
        } else {
            if (wizardExampled)
                wizardExampled.goTo(0);
        }
    }, 300);*/
};

filtroCausas = function(opcion,respuestas,cargarPreviamente){
	$scope.opcionElejida=opcion;
	$scope.respuestaActual=respuestas;
	$scope.comboCausasList=[];
	$scope.opcionMarcadaRespuesta=opcion;
	encuestaService.comboCausas($scope.opcionMarcadaRespuesta.idOpcion).success(function(datos) {
	 for (let i in datos) {
			$scope.comboCausasList.push(datos[i].causas);
		}	
	 if(cargarPreviamente)
		 {
	 $scope.causas=opcion.causas.split(",").map(function(item) {
		    return parseInt(item, 10);
	
	 })
	 $scope.changeComboCausa();
		 }
				
}).error(function(datos) {
        $scope.error = datos;
    $scope.datos = {};
    });
}

$scope.guardarCausa=function()
{
    if ($scope.evaluacion.$invalid) {
        
        angular.forEach($scope.evaluacion.$error, function (field) {
          angular.forEach(field, function(errorField){
              errorField.$setDirty();
          })
        });
    }else
    	{
    	for(let a in $scope.seccionVO.preguntas)
    		{
    		if ($scope.seccionVO.preguntas[a].idPregunta==$scope.respuestaActual.idPregunta)
    			{
    	    	for (let i in $scope.seccionVO.preguntas[a].opciones) {
    	    		if ($scope.seccionVO.preguntas[a].opciones[i].idOpcion==$scope.opcionElejida.idOpcion) {
    	    			$scope.seccionVO.preguntas[a].opciones[i].causas=$scope.causas.toString();
    	    	}
    	          }

    			
    			}
    		}
    	$("#myModal").modal('hide');//ocultamos el modal
    	}

};

$scope.changeComboCausa=function()
{
	var listCausas =  $scope.causas;
	$scope.nbCausa= [];
	for(var x in listCausas )
	{
	if(!isNaN(x)){
	
	for(var y in $scope.comboCausasList )
		{
		if(listCausas[x]==$scope.comboCausasList[y].idCausa)
			{
			$scope.nbCausa[x]=$scope.comboCausasList[y].nbCausa;
			}
		}
	}
	}
	
};

$scope.cargarCausas=function(opciones,respuesta)
{
	if(opciones.cdMostrarCausas)
	{
	filtroCausas(opciones,respuesta,true);
	
	}


}



    
    $scope.getNumPreguntasPorSeccion('TIE019P_NU_PAGINACION');
    $scope.getNumMaxPaginacion('TIE019P_NU_MAX_PAG');
    $scope.getEncuestaOrden(encuestaInfo);
    
    
});

