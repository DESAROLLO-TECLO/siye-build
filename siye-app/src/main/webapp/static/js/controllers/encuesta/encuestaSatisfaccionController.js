angular.module(appTeclo)
.controller("encuestaSatisfaccionController",
function($rootScope,$scope,$window,$translate,$timeout,ModalService,showAlert,growl) {
	$scope.banderaPantalla=false;
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
		nuPregntas:60,
		  secciones:new Array({
		    idSeccion:2,
		    nuPreguntasContestadas:null,
		    cdSeccion:"SECCION INSTALACION",
		    nbSeccion:" Instalacion 1",
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
		        {txOpcion:"Opcion C",stMarcado:0})}

		    )
		  })

		});
	
	
	$scope.checkPregunta =function(opcion,respuesta){
		var evaluaContestadas=$scope.encuestaDetalle.encuesta.secciones[$scope.posicionActual].nuPreguntasContestadas;
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

});