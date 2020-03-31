angular.module(appTeclo)
.controller("encuestaSatisfaccionController",
function($rootScope,$scope,$window,$translate,$interval,$timeout,ModalService,showAlert,growl, $location) {
	$scope.banderaPantalla=false;
	$scope.formato = '0';
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
            {idTipo:1,cdTipo:"PLACA",nbTipo:"Placa"},
            {idTipo:2,cdTipo:"ORDEN",nbTipo:"Orden"},
            {idTipo:2,cdTipo:"VIN",nbTipo:"Vin"}
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
			growl.error('Formulario Incompleto');
			
		}
    	
    }
		
		$scope.empezarEncuesta=function(accion){
			$scope.banderaPantalla=accion;
			   $timeout(() => {
			$scope.cambiarPregunta(null,$scope.encuesta.secciones[0]);
			$scope.iniciarConteo();
			  $scope.paramConfigPage.tiempoEncuesta = $scope.encuesta.nuTiempo;
			$scope.encuesta=$scope.encuesta},300);
			//alert($scope.encuesta.secciones[0]);
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
	
	
	
	$scope.encuesta=new Object({idEncuesta:1,
		cdEncuesta:"AA",
		nbEncuesta:"Instalacion",
		nuSecciones:3,
		nuTiempo:3,
		nuTiempo:5700,
		nuPreguntas:24,
		  secciones:new Array(
				  {idSeccion:2,
		    nuPreguntasContestadas:null,
		    cdSeccion:"SECCION INSTALACION",
		    nbSeccion:" Instalacion Camaras",
		    preguntas:new Array(

		      {txPregunta:"Pregunta 1",stMarcado:0,
		        opciones:new Array(       
		       { txOpcion:"Opcion A",stMarcado:0},
		       { txOpcion:"Opcion B",stMarcado:0},
		       { txOpcion:"Opcion C",stMarcado:0})},

		      {txPregunta:"Pregunta 2",stMarcado:0,
		      opciones:new Array(
		      {txOpcion:"Opcion A",stMarcado:0},
		      {txOpcion:"Opcion B",stMarcado:0},
		      {txOpcion:"Opcion C",stMarcado:0})},

		     {txPregunta:"Pregunta 3",stMarcado:0,
		      opciones:new Array(
		        {txOpcion:"Opcion A",stMarcado:0},
		        {txOpcion:"Opcion B",stMarcado:0},
		        {txOpcion:"Opcion C",stMarcado:0})},

		      {txPregunta:"Pregunta 4",stMarcado:0,
		        opciones:new Array(       
		       { txOpcion:"Opcion A",stMarcado:0},
		       { txOpcion:"Opcion B",stMarcado:0},
		       { txOpcion:"Opcion C",stMarcado:0})},

		      {txPregunta:"Pregunta 5",stMarcado:0,
		      opciones:new Array(
		      {txOpcion:"Opcion A",stMarcado:0},
		      {txOpcion:"Opcion B",stMarcado:0},
		      {txOpcion:"Opcion C",stMarcado:0})},

		     {txPregunta:"Pregunta 6",stMarcado:0,
		      opciones:new Array(
		        {txOpcion:"Opcion A",stMarcado:0},
		        {txOpcion:"Opcion B",stMarcado:0},
		        {txOpcion:"Opcion C",stMarcado:0})},

		    
		      {txPregunta:"Pregunta 7",stMarcado:0,
		        opciones:new Array(       
		       { txOpcion:"Opcion A",stMarcado:0},
		       { txOpcion:"Opcion B",stMarcado:0},
		       { txOpcion:"Opcion C",stMarcado:0})},

		      {txPregunta:"Pregunta 8",
		      opciones:new Array(
		      {txOpcion:"Opcion A",stMarcado:0},
		      {txOpcion:"Opcion B",stMarcado:0},
		      {txOpcion:"Opcion C",stMarcado:0})},

		     {txPregunta:"Pregunta 9",stMarcado:0,
		      opciones:new Array(
		        {txOpcion:"Opcion A",stMarcado:0},
		        {txOpcion:"Opcion B",stMarcado:0},
		        {txOpcion:"Opcion C",stMarcado:0})},

		      {txPregunta:"Pregunta 10",stMarcado:0,
		        opciones:new Array(       
		       { txOpcion:"Opcion A",stMarcado:0},
		       { txOpcion:"Opcion B",stMarcado:0},
		       { txOpcion:"Opcion C",stMarcado:0})},

		      {txPregunta:"Pregunta 11",stMarcado:0,
		      opciones:new Array(
		      {txOpcion:"Opcion A",stMarcado:0},
		      {txOpcion:"Opcion B",stMarcado:0},
		      {txOpcion:"Opcion C",stMarcado:0})},

		     {txPregunta:"Pregunta 12",stMarcado:0,
		      opciones:new Array(
		        {txOpcion:"Opcion A",stMarcado:0},
		        {txOpcion:"Opcion B",stMarcado:0},
		        {txOpcion:"Opcion C",stMarcado:0})})
		  },
		 //SEGUNDA SECCION
		  {idSeccion:2,
			    nuPreguntasContestadas:null,
			    cdSeccion:"SECCION CORROBORACION",
			    nbSeccion:" Corroboracion Camaras",
			    preguntas:new Array(

			      {txPregunta:"Pregunta 13",stMarcado:0,
			        opciones:new Array(       
			       { txOpcion:"Opcion A",stMarcado:0},
			       { txOpcion:"Opcion B",stMarcado:0},
			       { txOpcion:"Opcion C",stMarcado:0})},

			      {txPregunta:"Pregunta 14",stMarcado:0,
			      opciones:new Array(
			      {txOpcion:"Opcion A",stMarcado:0},
			      {txOpcion:"Opcion B",stMarcado:0},
			      {txOpcion:"Opcion C",stMarcado:0})},

			     {txPregunta:"Pregunta 15",stMarcado:0,
			      opciones:new Array(
			        {txOpcion:"Opcion A",stMarcado:0},
			        {txOpcion:"Opcion B",stMarcado:0},
			        {txOpcion:"Opcion C",stMarcado:0})},

			      {txPregunta:"Pregunta 16",stMarcado:0,
			        opciones:new Array(       
			       { txOpcion:"Opcion A",stMarcado:0},
			       { txOpcion:"Opcion B",stMarcado:0},
			       { txOpcion:"Opcion C",stMarcado:0})},

			      {txPregunta:"Pregunta 17",stMarcado:0,
			      opciones:new Array(
			      {txOpcion:"Opcion A",stMarcado:0},
			      {txOpcion:"Opcion B",stMarcado:0},
			      {txOpcion:"Opcion C",stMarcado:0})},

			     {txPregunta:"Pregunta 18",stMarcado:0,
			      opciones:new Array(
			        {txOpcion:"Opcion A",stMarcado:0},
			        {txOpcion:"Opcion B",stMarcado:0},
			        {txOpcion:"Opcion C",stMarcado:0})},

			    
			      {txPregunta:"Pregunta 19",stMarcado:0,
			        opciones:new Array(       
			       { txOpcion:"Opcion A",stMarcado:0},
			       { txOpcion:"Opcion B",stMarcado:0},
			       { txOpcion:"Opcion C",stMarcado:0})},

			      {txPregunta:"Pregunta 20",
			      opciones:new Array(
			      {txOpcion:"Opcion A",stMarcado:0},
			      {txOpcion:"Opcion B",stMarcado:0},
			      {txOpcion:"Opcion C",stMarcado:0})},

			     {txPregunta:"Pregunta 21",stMarcado:0,
			      opciones:new Array(
			        {txOpcion:"Opcion A",stMarcado:0},
			        {txOpcion:"Opcion B",stMarcado:0},
			        {txOpcion:"Opcion C",stMarcado:0})},

			      {txPregunta:"Pregunta 22",stMarcado:0,
			        opciones:new Array(       
			       { txOpcion:"Opcion A",stMarcado:0},
			       { txOpcion:"Opcion B",stMarcado:0},
			       { txOpcion:"Opcion C",stMarcado:0})},

			      {txPregunta:"Pregunta 23",stMarcado:0,
			      opciones:new Array(
			      {txOpcion:"Opcion A",stMarcado:0},
			      {txOpcion:"Opcion B",stMarcado:0},
			      {txOpcion:"Opcion C",stMarcado:0})},

			     {txPregunta:"Pregunta 24",stMarcado:0,
			      opciones:new Array(
			        {txOpcion:"Opcion A",stMarcado:0},
			        {txOpcion:"Opcion B",stMarcado:0},
			        {txOpcion:"Opcion C",stMarcado:0})})
			  }
		  //SEGUNDA SECCION
		  
	 )

});
	
	
	$scope.checkPregunta =function(opcion,respuesta){
		var evaluaContestadas=$scope.encuesta.secciones[$scope.posicionActual].nuPreguntasContestadas;
		var preguntasCont=evaluaContestadas!=undefined?evaluaContestadas:0;
		var cambio=false;

		if(respuesta.stMarcado==undefined || respuesta.stMarcado==0  || respuesta.stMarcado==null){
			$scope.encuesta.secciones[$scope.posicionActual].nuPreguntasContestadas=preguntasCont;
			$scope.encuesta.secciones[$scope.posicionActual].nuPreguntasContestadas++
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
            $scope.posicionActual = $scope.encuesta.secciones.indexOf(seccionVO);
            var evaluaContestadas = $scope.encuesta.secciones[$scope.posicionActual].nuPreguntasContestadas != undefined ?
                $scope.encuesta.secciones[$scope.posicionActual].nuPreguntasContestadas : 0;
            $scope.seccionSeleccion = seccionVO.cdSeccion;
            $scope.seccionVO = seccionVO;
            $scope.encuesta.secciones[$scope.posicionActual].nuPreguntasContestadas = evaluaContestadas;
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
        var seccionVO = angular.copy($scope.encuesta.secciones[$scope.posicionActual]);
        var preguntasPorPagina = seccionVO.preguntas.slice(((numPagina - 1) * $scope.paramConfigPage.itemsPerPage), ((numPagina) * $scope.paramConfigPage.itemsPerPage));
        seccionVO.preguntas = preguntasPorPagina;
       // $scope.saveEncuesta(seccionVO);
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
        var lisSeccionesValid = new Array();
        var secciones = $scope.encuesta.secciones;
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
    
    

});