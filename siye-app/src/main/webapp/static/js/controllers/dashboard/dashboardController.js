angular.module(appTeclo).controller("dashboardController", function($scope, $filter, $timeout) {

    $scope.indicadores = [];
    /**************************************INITIALIZERS***************************************************/
    $scope.cambia = function(valor) {
        $scope.OrdenesServiciosShow = false;
        $scope.PlanesShow = false;
        $scope.ModulosShow = false;
        $scope.incidenciasShow = false;


        switch (valor) {
            case 'OrdenesServicios':
                $scope.titulos = {
                    pastel: 'Tipos de Vehiculos con Instalacion',
                    barras3D: 'Multas Pagadas vs No Pagadas',
                    barras: 'Total de Comparendos',
                    barras3D_2: 'Ordenes de Servicios',
                    pie: 'Ordenes de Servicios Planeadas y no Planeadas',
                    osbyPlan: 'Ordenes de Servicios po Plan',
                    AvanceGeneral: 'Avance General Ordenes de Servicios',
                    topTenicosMayNumInstall: 'Top técnicos con mayor número de instalaciones',
                    topTenicosMayNumPruebasPlat: 'Top técnicos con mayor número de pruebas en plataforma'
                };

                $scope.OrdenesServiciosShow = true;

                // initGraphCG($scope.graphBarrasVerticalesInfracciones);
                //graphBarras3D($scope.datGraph3DInfracciones);
                graphBarras3D_2($scope.datGraph3D_2);
                //graphBarras3D_3($scope.datGraph3D_3);
                grafica1($scope.dataGraphPastel1OS, "grafica1");
                grafPie($scope.OSPlaneadasAndnotPlaneadas);
                initGraphCGeneral($scope.OSconincidencias, "graficaBarras");
                grafica1($scope.dataOSByPlan, "graficaPieOSPlan");
                grafica1($scope.dataOSByAvanceGeneral, "graficaAvanceGeneral");
                graficaIncidenciaTop($scope.dataGraphPastel1IN, "incidenciaTopSupervisor");
                // dataBarrasHorizontalTrabajo
                graficaBarrasHorizontal($scope.dataBarrasHorizontalTrabajo, "topTecnicosMayNumInstall");
                graficaBarrasHorizontal($scope.dataPruebasPlat, "topTecnicosMayNumPruebasPlat");

                grafica1($scope.dataGraphPastel1IN, "ordenesDeServPorMod");


                $scope.indicadores = [];
                $scope.indicadores = [
                    { myValue: 1, nbPanel: "En Cusro", myTarget: 15985, myDuration: 1500, bgPanel: 'bg-aqua' /*, icon: "fa-beer"*/ , myEffect: 'swing', id: 1 },
                    { myValue: 2, nbPanel: "En Espera", myTarget: 1382, myDuration: 1500, bgPanel: 'bg-yellow' /*, icon: "fa-beer"*/ , myEffect: 'swing', id: 2 },
                    { myValue: 3, nbPanel: "Finalizadas", myTarget: 8723, myDuration: 1500, bgPanel: 'bg-green' /*, icon: "fa-beer"*/ , myEffect: 'swing', id: 3 },
                    { myValue: 4, nbPanel: "Finalizadas Incidencia", myTarget: 8723, myDuration: 1500, bgPanel: 'bg-aqua' /*, icon: "fa-beer"*/ , myEffect: 'swing', id: 3 },
                ];


                break;

            case 'Planes':
                $scope.titulos = {
                    pastel: 'Avance por Plan',
                    barras: 'Avance de Procesos'
                };
                $scope.PlanesShow = true;
                initGraphCG($scope.graphBarrasVerticalesPuntos);
                initGraphCG2($scope.graphBarrasVerticalesPuntos);
                grafica1($scope.dataGraphPastel1OS, "grafica1");
                break;

            case 'Modulos':
                $scope.titulos = {
                    pastel: 'Distribución de Trabajo Comunitario por Categoría',
                    barras3D: 'Media de Puntos Acumulados',
                    barras: 'Actividad de Trabajo Comunitario Durante el Periodo',
                    barrasHorizontal: 'Top 10 Trabajos más Solicitados',
                    tipoEstimadoAtencion: 'Tiempo promedio de atención de ordenes de servicio por módulo',
                    atencionPorModulo: 'Atencion de ordenes de servicio por módulo'
                };
                $scope.ModulosShow = true;

                initGraphCG($scope.graphBarrasVerticalesPuntos);
                break;

            case 'Incidencias':
                $scope.incidenciasShow = true;
                $scope.titulos = {
                    pastel: 'Incidencias por modulo',
                    barras: 'Suspensión de Actividades Durante el Periodo',
                    barrasHorizontal: 'Top 10 de Actividades más Suspendidas',
                    barras3D_2: 'Incidencias Recurrentes',
                    incidenciasTop: 'Top estatus de incidencia',
					topSupervisorLevantaIncidencia: 'Top supervisores que levantan incidencia',
					incidenciaProcesos: 'Incidencia por Procesos'
                };
                grafica1($scope.dataGraphPastel1IN, "grafica1");
                graficaIncidenciaTop($scope.incidenciasTopData, "incidenciaTop");
                graficaIncidenciaTop($scope.incidenciasPorSuper, "incidenciaTopSupervisor");
				graphBarras3D_2($scope.IndicenciasRecurrentes);
				initGraphCGeneral($scope.graphBarrasVerticalesPuntos,"graficaBarrasIncidencia");

				
                break;
            default:

                // code block
        }
    };

    // switch para el filtro de graficas 
    $scope.actives = function(input) {
        $scope.active1 = false;
        $scope.active2 = false;
        $scope.active3 = false;
        $scope.active4 = false;

        switch (input) {
            case 1:
                $scope.active1 = true;
                break;
            case 2:
                $scope.active2 = true;
                break;

            case 3:
                $scope.active3 = true;
                break;
            case 4:
                $scope.active4 = true;
                break;
            default:
                $scope.active1 = true;
                break;
        }
    };




    // JSON Data Graficas

    $scope.dataBarrasHorizontalTrabajo = [{
            "year": "Jesús Goméz",
            "income": 10,
            "expenses": 10000
        },
        {
            "year": "Nataly Rodriguez",
            "income": 8,
            "expenses": 9000
        },
        {
            "year": "Jose Toscano",
            "income": 13,
            "expenses": 8000
        },
        {
            "year": "Fernando Sanchez",
            "income": 17,
            "expenses": 7000
        },
        {
            "year": "Gabriel Soto",
            "income": 9,
            "expenses": 6000
        },
        {
            "year": "Graciela Hernandez",
            "income": 9,
            "expenses": 5000
        },
        {
            "year": "Jose Luis Lopez",
            "income": 9,
            "expenses": 4000
        },
        {
            "year": "Carlos Perez",
            "income": 9,
            "expenses": 3000
        },
        {
            "year": "Marisol Sanchez",
            "income": 9,
            "expenses": 2000
        }
    ];

    $scope.dataPruebasPlat = [{
            "year": "Antonio Perez",
            "income": 10,
            "expenses": 10000
        },
        {
            "year": "José Murillo",
            "income": 8,
            "expenses": 9000
        },
        {
            "year": "Manuel Sanchez",
            "income": 13,
            "expenses": 8000
        },
        {
            "year": "Francisco Lopez",
            "income": 17,
            "expenses": 7000
        },
        {
            "year": "David Guerra",
            "income": 9,
            "expenses": 6000
        },
        {
            "year": "Javier Dante",
            "income": 9,
            "expenses": 5000
        },
        {
            "year": "Sanuel Sanchez",
            "income": 9,
            "expenses": 4000
        },
        {
            "year": "Gabriel Martinez",
            "income": 9,
            "expenses": 3000
        },
        {
            "year": "Damian Gomez",
            "income": 9,
            "expenses": 2000
        }
    ];


    // Odenes de Servicio
    $scope.dataGraphPastel1OS = [{
        "title": "AUDI A1",
        "value": 46385
    }, {
        "title": "AUDI A2",
        "value": 11345
    }, {
        "title": "AUDI A3",
        "value": 9863
    }, {
        "title": "AUDI A4",
        "value": 30100
    }, {
        "title": "AUDI A5",
        "value": 3195
    }];

    $scope.datGraph3D_2 = [{
        "componente": "Camaras",
        "nInstalaciones": 10300,
        "color": "#607d8b"
    }, {
        "componente": "GPS",
        "nInstalaciones": 10300,
        "color": "#607d8b"
    }, {
        "componente": "Contador Personas",
        "nInstalaciones": 10300,
        "color": "#607d8b"
    }];

    $scope.OSPlaneadasAndnotPlaneadas = [{
        "label": "Planedas",
        "data": 501.9
    }, {
        "label": "No Planedas",
        "data": 501.9
    }];


    $scope.dataOSByPlan = [{
        "title": "Plan Basico",
        "value": 46385
    }, {
        "title": "PlanCompleto",
        "value": 11345
    }];


    $scope.dataOSByAvanceGeneral = [{
        "title": "Total de Ordenes de Servicios",
        "value": 46385
    }, {
        "title": "Odenes de Servicios Finalizadas",
        "value": 11345
    }];

    $scope.OSconincidencias = {
        title: 'jsjs',
        titleRed: '',
        titleBlue: '',
        data: [{
            "proceso": "Con Inicdencia",
            "avance": 1150
        }, {
            "proceso": "Sin Incidencias",
            "avance": 950
        }],
    };

    $scope.incidenciasTopData = [{
        "title": "Nuevo",
        "value": 46385
    }, {
        "title": "En curso",
        "value": 11345
    }, {
        "title": "Finalizado",
        "value": 9863
    }];





    // Plan Basico y completo

    $scope.graphBarrasVerticalesPuntos = {
        title: 'Avance por plan',
        titleRed: '',
        titleBlue: '',
        data: [{
                "proceso": "INSTALACION",
                "avance": 1150
            }, {
                "proceso": "PLATAFORMA",
                "avance": 1150
            }, {
                "proceso": "HOLOGRAMA",
                "avance": 1150
            }

        ],
    };

    // incidencias

    $scope.dataGraphPastel1IN = [{
        "title": "Modulo 1",
        "value": 46385
    }, {
        "title": "Modulo 2",
        "value": 11345
    }, {
        "title": "Modulo 3",
        "value": 9863
    }];



    $scope.IncidenciasModulo = {
        title: 'Avance por plan',
        titleRed: '',
        titleBlue: '',
        data: [{
                "proceso": "INSTALACION",
                "avance": 1150
            }, {
                "proceso": "PLATAFORMA",
                "avance": 1150
            }, {
                "proceso": "HOLOGRAMA",
                "avance": 1150
            }

        ],
    };


    $scope.IndicenciasRecurrentes = [{
        "componente": "Incidencia tipo 1",
        "nInstalaciones": 10300,
        "color": "#607d8b"
    }, {
        "componente": "Incidencia tipo 2",
        "nInstalaciones": 10300,
        "color": "#607d8b"
    }, {
        "componente": "Incidencia tipo 3",
        "nInstalaciones": 10300,
        "color": "#607d8b"
    }];

    $scope.incidenciasPorSuper = [{
        "title": "Jorge Pérez -<br> NO EXISTE ORDEN",
        "value": 133
    }, {
        "title": "Marisol Sanchez -<br> SIN PIEZAS <br> PARA INSTALACIÓN",
        "value": 245
    }, {
        "title": "Carlos Hernández -<br> VEHÍCULO NO APROBADO",
        "value": 158
    }, {
        "title": "Francisco Gutierrez -<br> DATOS ERRONEOS",
        "value": 382
    }];




    // Graficas 

    grafica1 = function(jsonData, div) {

        var grafica1 = AmCharts.makeChart(div, {
            "type": "pie",
            "theme": "donaMantto",
            "dataProvider": jsonData,
            "titleField": "title",
            "valueField": "value",
            "labelRadius": 5,

            "radius": "42%",
            "innerRadius": "60%",
            "labelText": "[[title]]",
            "export": {
                "enabled": true
            }
        });
    };

    graphBarras3D_2 = function(jsonDataxMes) {

        var cartTotalCitas = AmCharts.makeChart("grafica2", {
            "theme": "light",
            "type": "serial",
            "startDuration": 2,
            "dataProvider": jsonDataxMes,
            "graphs": [{
                "balloonText": "[[category]]: <b>[[value]]</b>",
                "fillColorsField": "color",
                "fillAlphas": 1,
                "lineAlpha": 0.1,
                "type": "column",
                "valueField": "nInstalaciones"
            }],
            "depth3D": 20,
            "angle": 30,
            "chartCursor": {
                "categoryBalloonEnabled": false,
                "cursorAlpha": 0,
                "zoomable": false
            },
            "categoryField": "componente",
            "categoryAxis": {
                "gridPosition": "start",
                "labelRotation": 0
            },
            "export": {
                "enabled": true
            }

        });


    };

    initGraphCG = function(jsonData) {
        var chartRendimientoVehiculosCombustible = AmCharts.makeChart("grafica3", {
            "type": "serial",
            "addClassNames": true,
            "theme": "chartVG",
            "autoMargins": false,
            "marginLeft": 35,
            "marginRight": 8,
            "marginTop": 10,
            "marginBottom": 26,
            "balloon": {
                "adjustBorderColor": false,
                "horizontalPadding": 20,
                "verticalPadding": 8,
                "color": "#ffffff"
            },

            "dataProvider": jsonData.data,
            "valueAxes": [{
                "axisAlpha": 10,
                "position": "left"
            }],
            "startDuration": 1,
            "graphs": [{
                "alphaField": "alpha",
                "balloonText": "<span style='font-size:12px;'>[[proceso]] :<br><span style='font-size:20px;'>[[value]]</span> [[additional]]</span>",
                "fillAlphas": 1,
                //	"title": jsonData.titleRed,
                "type": "column",
                "valueField": "avance",
                "dashLengthField": "dashLengthColumn"
            }],
            "categoryField": "proceso",
            "categoryAxis": {
                "gridPosition": "start",
                "axisAlpha": 0,
                "tickLength": 0
            },
            "export": {
                "enabled": true
            }
        });
    }

    initGraphCG2 = function(jsonData) {
        var chartRendimientoVehiculosCombustible = AmCharts.makeChart("grafica3PC", {
            "type": "serial",
            "addClassNames": true,
            "theme": "chartVG",
            "autoMargins": false,
            "marginLeft": 35,
            "marginRight": 8,
            "marginTop": 10,
            "marginBottom": 26,
            "balloon": {
                "adjustBorderColor": false,
                "horizontalPadding": 20,
                "verticalPadding": 8,
                "color": "#ffffff"
            },

            "dataProvider": jsonData.data,
            "valueAxes": [{
                "axisAlpha": 10,
                "position": "left"
            }],
            "startDuration": 1,
            "graphs": [{
                "alphaField": "alpha",
                "balloonText": "<span style='font-size:12px;'>[[proceso]] :<br><span style='font-size:20px;'>[[value]]</span> [[additional]]</span>",
                "fillAlphas": 1,
                //	"title": jsonData.titleRed,
                "type": "column",
                "valueField": "avance",
                "dashLengthField": "dashLengthColumn"
            }],
            "categoryField": "proceso",
            "categoryAxis": {
                "gridPosition": "start",
                "axisAlpha": 0,
                "tickLength": 0
            },
            "export": {
                "enabled": true
            }
        });
    }

    // new 
    grafPie = function(jsonData) {

        var grafinaDonut = AmCharts.makeChart("graficaPie", {
            "type": "pie",
            "theme": "light",
            "dataProvider": jsonData,
            "valueField": "data",
            "titleField": "label",
            "colorField": "color",
            "balloon": {
                "fixedPosition": true
            }
        });

    };


    initGraphCGeneral = function(jsonData, div) {
        var chart = AmCharts.makeChart(div, {
            "type": "serial",
            "addClassNames": true,
            "theme": "chartVG",
            "autoMargins": false,
            "marginLeft": 35,
            "marginRight": 8,
            "marginTop": 10,
            "marginBottom": 26,
            "balloon": {
                "adjustBorderColor": false,
                "horizontalPadding": 20,
                "verticalPadding": 8,
                "color": "#ffffff"
            },

            "dataProvider": jsonData.data,
            "valueAxes": [{
                "axisAlpha": 10,
                "position": "left"
            }],
            "startDuration": 1,
            "graphs": [{
                "alphaField": "alpha",
                "balloonText": "<span style='font-size:12px;'>[[proceso]] :<br><span style='font-size:20px;'>[[value]]</span> [[additional]]</span>",
                "fillAlphas": 1,
                //	"title": jsonData.titleRed,
                "type": "column",
                "valueField": "avance",
                "dashLengthField": "dashLengthColumn"
            }],
            "categoryField": "proceso",
            "categoryAxis": {
                "gridPosition": "start",
                "axisAlpha": 0,
                "tickLength": 0
            },
            "export": {
                "enabled": true
            }
        });
    };

    graficaIncidenciaTop = function(jsonData, idElemento) {
        AmCharts.makeChart(idElemento, {
            "type": "pie",
            "theme": "donaMantto",
            "dataProvider": jsonData,
            "titleField": "title",
            "valueField": "value",
            "labelRadius": 5,

            "radius": "42%",
            "innerRadius": "60%",
            "labelText": "[[title]]",
            "export": {
                "enabled": true
            }
        });
    };

    graficaBarrasHorizontal = function(jsonData, idElemento) {
        AmCharts.makeChart(idElemento, {
            "type": "serial",
            "theme": "chartMantto",
            "categoryField": "year",
            "rotate": true,
            "startDuration": 1,
            "categoryAxis": {
                "gridPosition": "start",
                "position": "left"
            },
            "trendLines": [],
            "graphs": [{
                "balloonText": "Cantidad: [[value]]",
                "fillAlphas": 0.8,
                "id": "AmGraph-2",
                "lineAlpha": 0.2,
                "title": "Cantidad",
                "type": "column",
                "valueField": "expenses"
            }],
            "guides": [],
            "valueAxes": [{
                "id": "ValueAxis-1",
                "position": "top",
                "axisAlpha": 0
            }],
            "allLabels": [],
            "balloon": {},
            "titles": [],
            "dataProvider": jsonData,
            "export": {
                "enabled": true
            }

        });
    };




    $scope.showDates = false;
    $scope.showDatesFunc = function() {
        $scope.showDates = !$scope.showDates;
    }


    // init
    $scope.actives();
    $scope.cambia('OrdenesServicios');

});