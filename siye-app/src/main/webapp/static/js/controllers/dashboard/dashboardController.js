angular.module(appTeclo).controller("dashboardController", function($scope, $filter, $timeout) {


    $scope.ctrl={
		valueUnidadesActivas:1,
		unidadesActivas:90,
		valueUnidadesMantenimiento:1,
		unidadesMantenimiento:7,
		valueUnidadesDetenidos:1,
		unidadesDetenidos:3,
		duration:1000,
		effect:'swing'	
	};
	$scope.init = {
		zAnio1 : 2019,
		zMes1 : 3,
		zAnio2 : 2019,
		zMes2 : 3,
		zSuc2 : 1,
		zAnio3 : 2019,
		zMes3 : 3,
		zSuc3 : 1
	};
	$scope.comboSucursal = [
		{"idTipoSucursal": 1, "cdTipoSucursal": "Modulo 1"},
		{"idTipoSucursal": 2, "cdTipoSucursal": "Modulo 2"},
		{"idTipoSucursal": 3, "cdTipoSucursal": "Modulo 3"}
	];
	
	$scope.comboMes = [
		{"idMes":1,"descMes":"Enero"},
		{"idMes":2,"descMes":"Febrero"},
		{"idMes":3,"descMes":"Marzo"},
		{"idMes":4,"descMes":"Abril"},
		{"idMes":5,"descMes":"Mayo"},
		{"idMes":6,"descMes":"Junio"},
		{"idMes":7,"descMes":"Julio"},
		{"idMes":8,"descMes":"Agosto"},
		{"idMes":9,"descMes":"Septiembre"},
		{"idMes":10,"descMes":"Octubre"},
		{"idMes":11,"descMes":"Noviembre"},
		{"idMes":12,"descMes":"Diciembre"}
	];
	
	$scope.comboAnio = [
    	{"idAnio":2017,"descAnio":"2017"},
    	{"idAnio":2018,"descAnio":"2018"},
    	{"idAnio":2019,"descAnio":"2019"}
    ];
	
	$scope.ctrls = [
		/** fa-shopping-cart incono de carrito */
		{myValue:2, nbPanel: "Ordenes de servicio en curso",	myTarget:826,		myDuration:1500, bgPanel:'bg-yellow',	icon: "fa-line-chart",	myEffect:'swing'},
		{myValue:1, nbPanel: "ORdenes de servicio en espera",				myTarget:52,	myDuration:1500, bgPanel:'bg-blue',		icon: "fa-line-chart",		myEffect:'swing'},
		{myValue:3, nbPanel: "Orden de servicio finalizas correctamente",	myTarget:35,	myDuration:1500, bgPanel:'bg-green',	icon: "fa-line-chart",	myEffect:'swing'},
		{myValue:4, nbPanel: "Finalizadas con incidencia",	myTarget:10,	myDuration:1500, bgPanel:'bg-red',		icon: "fa-line-chart",	myEffect:'swing'}
	];
	
	$scope.Top10MasVen = [
	    {
	    	"orden": 1,
	        "idProducto": 1,
	        "idTipoProducto": 5,
	        "nbTipoProducto": "Santa Clara Entera",
	        "tpPresentacion": "1L" ,
	        "nbAlmacen": "Almacén 1","nbAntigueda":"Recientes",
	        "txDescripcion":"Descricion",
	        "nbStatus":"Agotado",
	        "flProducto":"FOL_2019112030",
	        "nuStock": 0,
	        "nuStockMax": 200,
	        "nuStockMin": 50,
	        "lbImg": "static/dist/img/imgProducto/lecheEntera1l.PNG",
	        "tpImg":"median",
	        "nuPrecio": "23.50",
	        "tpUnidadMedida":"Pieza",
	        "listimages": [
	            { "blImg": "static/dist/img/imgProducto/lecheEntera1l.PNG" },
	            { "blImg": "static/dist/img/imgProducto/lecheEntera1l.PNG" },
	            { "blImg": "static/dist/img/imgProducto/lecheEntera1l.PNG" }
	        ]
	    }
	];
	
	var dataGraficaSalidas = 
	[
		  {"tipoSalida": "Modulo 1", "value": 250},
		  {"tipoSalida": "Modulo 2","value": 50},
		  {"tipoSalida": "Modulo 3","value": 20}
	];
	
	
	var dataVentasXSuc = [
		{"mes":"Enero","ventas":254},
		{"mes":"Febrero","ventas":308},
		{"mes":"Marzo","ventas":100},
		{"mes":"Abril","ventas":250},
		{"mes":"Mayo","ventas":234},
		{"mes":"Junio","ventas":380},
		{"mes":"Julio","ventas":200},
		{"mes":"Agosto","ventas":328},
		{"mes":"Septiembre","ventas":105},
		{"mes":"Octubre","ventas":180},
		{"mes":"Noviembre","ventas":259},
		{"mes":"Diciembre","ventas":80}
	];
	
	iniciaTop10 = function(){
		$scope.arrayTop10MasVen = $scope.Top10MasVen;
		$scope.arrayTop10MenosVen = $scope.Top10MenosVen;
	}
	
	graficaSalidasProductos = function(){
		var graficaSalidas = AmCharts.makeChart( "graficaSalidas", {
			"type": "pie",
			"theme": "donaMantto",
			"dataProvider": dataGraficaSalidas,
			"titleField": "tipoSalida",
			"valueField": "value",
			"labelRadius": 5,
		    "marginTop": 3,
		    "marginRight": 0,
		    "marginLeft": 80,
		    "marginBottom": 0,
			"radius": "42%",
			"innerRadius": "60%",
			"labelText": "[[title]]",
			"export": {
				"enabled": true
			}
		});
	}
	
	graficaVentasXSucursal = function() {
		var graficaVentasXSucursal = AmCharts.makeChart("graficaVentasXSucursal", {
		    "theme": "light",
		    "type": "serial",
		    "dataProvider": dataVentasXSuc,
		    "valueAxes": [{
		        "title": ""
		    }],
		    "graphs": [{
		        "balloonText": "[[category]]: [[ventas]]",
		        "fillAlphas": 1,
		        "lineAlpha": 0.2,
		        "title": "Income",
		        "type": "column",
		        "valueField": "ventas"
		    }],
		    "depth3D": 8,
		    "angle": 20,
		    "rotate": true,
		    "categoryField": "mes",
		    "categoryAxis": {
		        "gridPosition": "start",
				"gridAlpha": 0.05,
		        "fillAlpha": 0.05,
		        "position": "left"
		    },
		    "export": {
		    	"enabled": true
		     }
		});
	}


	var dataSucursales = [
		{
			"idCentroAtencion": 1,
			"nbCentroAtencion": "Modulo 1",
			"color1": "#90EE90",
			"color2": "#d3d3d1",
			"Rechazados":50
		},
		{
			"idCentroAtencion": 1,
			"nbCentroAtencion": "Modulo 2",
			"color1": "#90EE90",
			"color2": "#d3d3d1",
			"Rechazados":100
		},
		{
			"idCentroAtencion": 1,
			"nbCentroAtencion": "Modulo 3",
			"color1": "#90EE90",
			"color2": "#d3d3d1",
			"Rechazados":110,
		}
	];
	
	graficaIngGanSucursal = function(){
		var graficaIngGanSucursal = AmCharts.makeChart("graficaIngGanSucursal", {
		    "theme": "none",
		    "type": "serial",
		    "dataProvider": dataSucursales,
		    "valueAxes": [{
		        "position": "left",
		        "title": "Ordenes de Servicios Rechazadas"
		    }],
		    "startDuration": 1,
		    "graphs": [{
		        "balloonText": "<span style='font-size:13px;'>[[nbCentroAtencion]]: <b>[[value]]</b></span>",
		        "fillAlphas": 0.9,
		        "lineAlpha": 0.2,
		        "title": "Rechazados",
		        "type": "column",
		        "valueField": "Rechazados",
		        "colorField": "color1",
		        "cornerRadiusTop": 8
		    }],
		    "legend": {
		        "horizontalGap": 10,
		        "maxColumns": 1,
		        "position": "right",
		        "useGraphSettings": true,
		        "useMarkerColorForLabels": true,
		        "markerSize": 10
		    },
		    "plotAreaFillAlphas": 0.1,
		    "categoryField": "nbCentroAtencion",
		    "categoryAxis": {
		        "gridPosition": "start",
		        "labelRotation": 20
		    },
		    "export": {
		    	"enabled": true
		     }
		});
	}
	
	graficaMetasXSucursal = function(){
		var graficaMetasXSucursal = AmCharts.makeChart("graficaMetasXSucursal", {
			"theme": "none",
			"type": "gauge",
			"axes": [{
				"topTextFontSize": 20,
				"topTextYOffset": 100,
				"axisColor": "#5b0218",
				"axisThickness": 1,
				"endValue": $scope.dataMetasSuc.metaSuc,
				"gridInside": true,
				"inside": true,
				"radius": "60%",
				"valueInterval": $scope.dataMetasSuc.intervalos,
				"tickColor": "#5b0218",
				"startAngle": -90,
				"endAngle": 90,
				//"unit": "%",
				"bandOutlineAlpha": 0,
				"topText": $scope.dataMetasSuc.textoCentral,
				"bands": [{
					"color": "#5b0218",
					"endValue": $scope.dataMetasSuc.metaSuc,
					"innerRadius": "105%",
					"radius": "170%",
					"gradientRatio": [0.5, 0, -0.5],
					"startValue": 0
				}, {
					"color": "#d3d3d1",
					"endValue": $scope.dataMetasSuc.ventas,
					"innerRadius": "105%",
					"radius": "170%",
					"gradientRatio": [0.5, 0, -0.5],
					"startValue": 0
				}]
			}],
			"arrows": [{
				"alpha": 1,
				"innerRadius": "35%",
				"nailRadius": 0,
				"radius": "170%",
				"value": $scope.dataMetasSuc.ventas
			}]
		});
	}
	
	$scope.tabMasV = true;
	$scope.tabMenosV = false;
	$scope.tabSucVen = true;
	$scope.tabSucIng = false;
	
	$scope.tabsTOP10 = [
   		{idTab: 1, nbTab: 'Más Vendidos', isActive: true},
   		{idTab: 2, nbTab: 'Menos Vendidos', isActive: false}
   	];
	
	$scope.tabsSucursales = [
   		{idTab: 1, nbTab: 'Atendidas', isActive: true},
   		{idTab: 2, nbTab: 'Rechazados', isActive: false}
   	];
	
	iniciaGrafMetas = function(){
		var metaSuc = 1800;
		var intervalos = metaSuc / 10;
		var ventas = 670;
		var textoCentral = "Ingresos por venta: $"+ ventas;
		
		$scope.dataMetasSuc = {
			metaSuc : 1800,
			intervalos : (metaSuc / 10),
			ventas : 670,
			textoCentral : ventas
		};
		$timeout(function() {
			graficaMetasXSucursal();
		});
	}
	
	$scope.changeGraph = function(tab) {
		switch (tab) {
			case 'Menos Vendidos':
				$scope.tabsTOP10[0].isActive = false;
				$scope.tabsTOP10[1].isActive = true;
				$scope.tabMenosV = true;
				$scope.tabMasV = false;
				break;
			case 'Más Vendidos':
				$scope.tabsTOP10[1].isActive = false;
				$scope.tabsTOP10[0].isActive = true;
				$scope.tabMenosV = false;
				$scope.tabMasV = true;
				break;
			case 'Rechazados':
				$scope.tabsSucursales[0].isActive = false;
				$scope.tabsSucursales[1].isActive = true;
				$scope.tabSucIng = true;
				$scope.tabSucVen = false;
				$timeout(function() {
					graficaIngGanSucursal();
				});
				break;
			case 'Atendidas':
				$scope.tabsSucursales[1].isActive = false;
				$scope.tabsSucursales[0].isActive = true;
				$scope.tabSucIng = false;
				$scope.tabSucVen = true;
				$timeout(function() {
					graficaVentasXSucursal();
				});
				$timeout(function() {
					graficaMetasXSucursal();
				});
				break;
			default:
				break;
		}
	}
	
	initController = function() {
		$scope.dataMetasSuc = {};
		iniciaTop10();
		$timeout(function() {
			graficaSalidasProductos();
		});
		$timeout(function() {
			graficaVentasXSucursal();
		});
		$timeout(function() {
			//graficaMetasXSucursal();
			iniciaGrafMetas();
			graficaAvancePlan();
		});
	}


	var AvancePlan = [
		{
			"idCentroInstalacion": 1,
			"nbCentroInstalacion": "Modulo 1",
			"color1": "#5b0218",
			"color2": "#d3d3d1",
			"numFoliosAtendidos":50,
			"Avance":120
		},
		{
			"idCentroInstalacion": 2,
			"nbCentroInstalacion": "Modulo 2",
			"color1": "#5b0218",
			"color2": "#d3d3d1",
			"numFoliosAtendidos":25,
			"Avance":80
		},
		{
			"idCentroInstalacion": 1,
			"nbCentroInstalacion": "Modulo 3",
			"color1": "#5b0218",
			"color2": "#d3d3d1",
			"numFoliosAtendidos":60,
			"Avance":140
		}
	];

	// nueva Graficas
	graficaAvancePlan = function() {
		var graficaVentasXSucursal = AmCharts.makeChart("graficaAvancePlan", {
			"theme": "none",
		    "type": "serial",
		    "dataProvider": AvancePlan,
		    "valueAxes": [{
		        "position": "left",
		        "title": "Ingresos y Ganancias"
		    }],
		    "startDuration": 1,
		    "graphs": [{
		        "balloonText": "<span style='font-size:13px;'>[[nbCentroInstalacion]]: <b>[[value]]</b></span>",
		        "fillAlphas": 0.9,
		        "lineAlpha": 0.2,
		        "title": "Atendidos",
		        "type": "column",
		        "valueField": "numFoliosAtendidos",
		        "colorField": "color1",
		        "cornerRadiusTop": 8
		    }, {
		        "balloonText": "<span style='font-size:13px;'>[[nbCentroInstalacion]]: <b>[[value]]</b></span>",
		        "fillAlphas": 0.9,
		        "lineAlpha": 0.2,
		        "title": "Avance",
		        "type": "column",
		        "clustered":false,
		        "columnWidth":0.5,
		        "valueField": "Avance",
		        "bulletOffset": 10,
		        "bulletSize": 52,
		        "colorField": "color2",
		        "cornerRadiusTop": 8,
		        "customBulletField": "lbImg"
		    }],
		    "legend": {
		        "horizontalGap": 10,
		        "maxColumns": 1,
		        "position": "right",
		        "useGraphSettings": true,
		        "useMarkerColorForLabels": true,
		        "markerSize": 10
		    },
		    "plotAreaFillAlphas": 0.1,
		    "categoryField": "nbCentroInstalacion",
		    "categoryAxis": {
		        "gridPosition": "start",
		        "labelRotation": 20
		    },
		    "export": {
		    	"enabled": true
		     }
		});
	}
	
	
	initController();






	qa2 =function() {

		var chart = AmCharts.makeChart("chartdiv2",{
			"type"    : "pie",
			"titleField"  : "category",
			"valueField"  : "column-1",
			"dataProvider"  : [
			  {
				"category": "Proceso 1",
				"column-1": 6
			  },
			  {
				"category": "Proceso 2 ",
				"column-1": 6
			  },
			  {
				"category": "Proceso 3",
				"column-1": 2
			  }
			]
		  });

		
		
		}; 







	
	qa =function() {

		var chart = AmCharts.makeChart("chartdiv",{
			"type"    : "pie",
			"titleField"  : "category",
			"valueField"  : "column-1",
			"dataProvider"  : [
			  {
				"category": "Proceso 1",
				"column-1": 6
			  },
			  {
				"category": "Proceso 2 ",
				"column-1": 6
			  },
			  {
				"category": "Proceso 3",
				"column-1": 2
			  }
			]
		  });

		
		
		}; 




		qa();
		qa2();
}); 