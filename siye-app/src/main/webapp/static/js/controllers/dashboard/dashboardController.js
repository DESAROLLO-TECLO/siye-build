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
                    AvanceGeneral: 'Avance General Ordenes de Servicios'
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
                    //	barras3D:'Top 10 de Artículos más Infringidos con Multa',
                    barras: 'Avance de Procesos'
                };


                $scope.PlanesShow = true;
                initGraphCG($scope.graphBarrasVerticalesPuntos);
                initGraphCG2($scope.graphBarrasVerticalesPuntos);
                grafica1($scope.dataGraphPastel1OS, "grafica1");
                //graphBarras3D($scope.datGraph3DPuntos);
                //graficaUnidadesMantenimiento($scope.dataGraphPastelPuntos);



                //removeClass();
                break;

            case 'Modulos':
                $scope.titulos = {
                    pastel: 'Distribución de Trabajo Comunitario por Categoría',
                    barras3D: 'Media de Puntos Acumulados',
                    barras: 'Actividad de Trabajo Comunitario Durante el Periodo',
                    barrasHorizontal: 'Top 10 Trabajos más Solicitados'
                };
                $scope.ModulosShow = true;

                initGraphCG($scope.graphBarrasVerticalesPuntos);
                //graphBarras3D($scope.datGraph3DPuntos);
                //graficaUnidadesMantenimiento($scope.dataGraphPastelTrabajoComun);
                //graficaBarrasHorizontal($scope.dataBarrasHorizontalTrabajo);

                break;

            case 'Incidencias':
                $scope.incidenciasShow = true;
                $scope.titulos = {
                    pastel: 'Incidencias por modulo',
                    barras: 'Suspensión de Actividades Durante el Periodo',
                    barrasHorizontal: 'Top 10 de Actividades más Suspendidas',
                    barras3D_2: 'Incidencias Recurrentes',
                    incidenciasTop: 'Top Estatus de incidencia'
                };


                //	initGraphCG($scope.IncidenciasModulo);
                grafica1($scope.dataGraphPastel1IN, "grafica1");
                graficaIncidenciaTop($scope.incidenciasTopData);
                graphBarras3D_2($scope.IndicenciasRecurrentes);
                //	graficaUnidadesMantenimiento($scope.dataGraphPastelCursosLineaB);
                //	graficaBarrasHorizontal($scope.dataBarrasHorizontalCursosLineaB);

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

    graficaIncidenciaTop = function(jsonData) {
        AmCharts.makeChart("incidenciaTop", {
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





    $scope.showDates = false;
    $scope.showDatesFunc = function() {
        $scope.showDates = !$scope.showDates;
    }


    // init
    $scope.actives();
    $scope.cambia('OrdenesServicios');

});