angular.module(appTeclo).controller("dashboardController", function($scope, $filter, $timeout, catalogoGenericoService) {

    $scope.optionsTimer = [{
        label:"15 Seg."
    },{
        label:"30 Seg."
    },{
        label:"60 Seg."
    }]

    $scope.rangoFechas = {
        date: {
            startDate: moment().endOf('day'),
            endDate: moment().endOf('day')
        },
        options: {
            maxDate: moment().endOf("day"),
            locale: {
                applyLabel: "Aceptar",
                cancelLabel: 'Cancelar',
                customRangeLabel: 'PERSONALIZADO',
                format: 'D/MM/YYYY',
                separator: " - "
            },
            ranges: {}
        }
    };

    getRangoFechas = function () {
        catalogoGenericoService.getCatRangoFechas().success(function (data) {
            let rango = {};
            for (let x = 0; x < data.length; x++) {
                if (data[x].nbTipoFecha!= 'PERSONALIZADO') {
                    rango[data[x].nbTipoFecha] = [data[x].fechaInicio, data[x].fechaFin];
                }
            }
            $scope.rangoFechas.options.ranges = rango;
           // consultaInicial();
        }).error(function (data) {
            growl.error(data.message);
        });
    };


    consultaInicial = function () {
        verColumnas($scope.catTipoBusqueda)
        seguimientoOsService.getInfoOsByRangoFechas($scope.params).success(function (data) {
            $scope.seguimientoVO.tablaResultados = true;
            $scope.seguimientoVO.busquedaOk = true;
            $scope.seguimientoVO.respaldo = angular.copy(data);
            $scope.seguimientoVO.datosTabla = data;
        }).error(function (data) {
            growl.error(data.message);
        });
    };

    getRangoFechas();















    $scope.indicadores = [];
    /**************************************INITIALIZERS***************************************************/
    $scope.cambia = function(valor) {
        $scope.OrdenesServiciosShow = false;
        $scope.PlanesShow = false;
        $scope.ModulosShow = false;
        $scope.incidenciasShow = false;
        $scope.generalShow = false;


        switch (valor) {

            case 'General':
                $scope.titulos = {
                    general: "Grafica General"
                }
                $scope.generalShow = true;

              //  graficaGeneral($scope.generalData, "graficaGeneral");

              graficaGeneral2($scope.generalData, "graficaGeneral");
                break;
            case 'OrdenesServicios':
                $scope.titulos = {
                    pastel: 'Tipos de Vehiculos con Instalacion',
                    barras3D: 'Multas Pagadas vs No Pagadas',
                    barras: 'Total de Comparendos',
                    barras2: 'Ordenes de Servicion con Inicdencias',
                    barras3D_2: 'Validacion de Componentes',
                    pie: 'Ordenes de Servicios Planeadas y no Planeadas',
                    osbyPlan: 'Ordenes de Servicios por Plan',
                    AvanceGeneral: 'Avance General Ordenes de Servicios',
                    topTenicosMayNumInstall: 'Top técnicos con mayor número de instalaciones',
                    topTenicosMayNumPruebasPlat: 'Top técnicos con mayor número de pruebas en plataforma'
                };

                $scope.OrdenesServiciosShow = true;

                // initGraphCG($scope.graphBarrasVerticalesInfracciones);
                //graphBarras3D($scope.datGraph3DInfracciones);
                graphBarras3D_2($scope.datGraph3D_2,);
                //graphBarras3D_3($scope.datGraph3D_3);
                //grafica1($scope.dataGraphPastel1OS, "");
                grafPie($scope.OSPlaneadasAndnotPlaneadas);
                initGraphCGeneral($scope.dataGraphPastel1OS, "grafica1");
                initGraphCGeneral($scope.OSconincidencias, "graficaBarras");
              /// grafica1();
                graficaPie($scope.ordenesServicioByPlan, "graficaPieOSPlan");
                grafica1($scope.dataOSByAvanceGeneral, "graficaAvanceGeneral");
                
                // dataBarrasHorizontalTrabajo
                graficaBarrasHorizontal($scope.dataBarrasHorizontalTrabajo, "topTecnicosMayNumInstall");
                graficaBarrasHorizontal($scope.dataPruebasPlat, "topTecnicosMayNumPruebasPlat");

                grafica1($scope.dataGraphPastel1IN, "ordenesDeServPorMod");


                $scope.indicadores = [];
                $scope.indicadores = [
                    { myValue: 1, nbPanel: "Nuevo", myTarget: 15985, myDuration: 1500, bgPanel: 'bg-aqua' /*, icon: "fa-beer"*/ , myEffect: 'swing', id: 1 },
                    { myValue: 2, nbPanel: "En Curso", myTarget: 1382, myDuration: 1500, bgPanel: 'bg-yellow' /*, icon: "fa-beer"*/ , myEffect: 'swing', id: 2 },
                    { myValue: 3, nbPanel: "Finalizadas", myTarget: 8723, myDuration: 1500, bgPanel: 'bg-green' /*, icon: "fa-beer"*/ , myEffect: 'swing', id: 3 },
                    { myValue: 4, nbPanel: "Finalizadas con Incidencia", myTarget: 8723, myDuration: 1500, bgPanel: 'bg-aqua' /*, icon: "fa-beer"*/ , myEffect: 'swing', id: 4 },
                    { myValue: 5, nbPanel: "Finalizado Incompleto", myTarget: 8723, myDuration: 1500, bgPanel: 'bg-red' /*, icon: "fa-beer"*/ , myEffect: 'swing', id: 5 },

                ];


                break;

            case 'Planes':
                $scope.titulos = {
                    pastel: 'Avance Plan Completo',
                    barras3D_2: 'Avance Plan Basico',
                    barras: 'Avance de Procesos'
                };
                $scope.PlanesShow = true;
                initGraphCG($scope.graphBarrasVerticalesPuntos);
                initGraphCG2($scope.graphBarrasVerticalesPuntos);
                //grafica1($scope.dataGraphPastel1OS, "grafica1");
                graficaGeneral($scope.avancePlan, "grafica1");
                graficaGeneral($scope.avancePlan, "grafica2");
               // grafica1($scope.dataGraphAvancePlan, "grafica2");
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

                graficaBarrasHorizontal($scope.dataPruebasModulo, "incidenciaTopSupervisor");
                grafica1($scope.dataGraphPastel1IN, "ordenesDeServPorMod");
              //  graficaIncidenciaTop($scope.dataGraphPastel1IN, "incidenciaTopSupervisor");

               // initGraphCG($scope.graphBarrasVerticalesPuntos);
              //  graficaBarrasHorizontal($scope.dataBarrasHorizontalTrabajo, "topTecnicosMayNumInstall");

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
              //  graficaIncidenciaTop($scope.incidenciasPorSuper, "incidenciaTopSupervisor");
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
        $scope.active5 = false;

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
            case 5:
                $scope.active5 = true;
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
    $scope.dataGraphPastel1OS = {
    title: '',
    titleRed: '',
    titleBlue: '',
    data: [{
        "proceso": "AUDI 1",
        "avance": 1150
    }, {
        "proceso": "AUDI 2",
        "avance": 950
    }, {
        "proceso": "AUDI 3",
        "avance": 1050
    }, {
        "proceso": "AUDI 4",
        "avance": 550
    }, {
        "proceso": "AUDI 5",
        "avance": 650
    }],
    };


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

   
    $scope.dataGraphAvancePlan = [{
        "title": "En Curso",
        "value": 46385
    }, {
        "title": "Nuevas",
        "value": 11345
    }, {
        "title": "Finalizadas",
        "value": 9863
    }, {
        "title": "Finalizadas s/Incidencias",
        "value": 9863
    }, {
        "title": "Finalizadas Incompletas",
        "value": 9863
    }];



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


    $scope.ordenesServicioByPlan = [{
              "label": "Plan Basico",
              "data": 501.9,
              "color": "#67b7dc"
            }, {
              "label": "Plan Completo",
              "data": 301.9,
              "color": "#fdd400"
            }];

// Modulos

$scope.dataPruebasModulo = [{
    "year": "Modulo 1",
    "income": 10,
    "expenses": 10000
},
{
    "year": "Modulo 2",
    "income": 8,
    "expenses": 9000
},
{
    "year": "Modulo 3",
    "income": 13,
    "expenses": 8000
}
];

    // Graficas 

   
    graficaPie = function(jsonData, div){
        var grafPie = AmCharts.makeChart(div, {
            "type": "pie",
          /*  "legend": {
                "useGraphSettings": true,
                "colorField": "color",
              },*/
            "dataProvider": jsonData,
            "valueField": "data",
            "titleField": "label",
            "colorField": "color",
            "labelColorField": "color",
            "balloon": {
              "fixedPosition": true
            }
          });
    };

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

    // General


   $scope.generalData = [{
        "date": "Modulo  1",
        "market1": 71,
        "market2": 75,
        "sales1": 75,
        "sales2": 78
      }, {
        "date": "Modulo 2",
        "market1": 74,
        "market2": 78,
        "sales1": 84,
        "sales2": 96
      }];

      
   $scope.avancePlan = [{
    "date": "Semana  1",
    "market1": 71,
    "market2": 75,
    "sales1": 75,
    "sales2": 78
  }, {
    "date": "Semana 2",
    "market1": 74,
    "market2": 78,
    "sales1": 84,
    "sales2": 96
  }];

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

    graficaGeneral2 = function(jsonData, div){
        var graficaG = AmCharts.makeChart(div, {
    "type": "serial",
    "theme": "none",
    "legend": {
        "useGraphSettings": true
    },
    "dataProvider": [{
        "year": "Modulo 1",
        "en curso": 1,
        "Nuevo": 5,
        "Finalizadas": 3,
        "Finalizadas con Incidencia": 6,
        "Finalizadas Incompletas": 8
    }, {
        "year": "Modulo 2",
        "en curso": 1,
        "Nuevo": 2,
        "Finalizadas": 6,
        "Finalizadas con Incidencia": 6,
        "Finalizadas Incompletas": 8
    }],
    "valueAxes": [{
        "integersOnly": true,
        "maximum": 6,
        "minimum": 1,
        "reversed": true,
        "axisAlpha": 0,
        "dashLength": 5,
        "gridCount": 10,
        "position": "left",
        "title": "Place taken"
    }],
    "startDuration": 0.5,
    "graphs": [{
        "balloonText": "[[category]]: [[value]]",
        "bullet": "round",
        "hidden": false,
        "title": "en curso",
        "valueField": "en curso",
    "fillAlphas": 0
    }, {
        "balloonText": "[[category]]: [[value]]",
        "bullet": "round",
        "title": "Nuevo",
        "valueField": "Nuevo",
    "fillAlphas": 0
    }, {
        "balloonText": "place taken by UK in [[category]]: [[value]]",
        "bullet": "round",
        "title": "Finalizadas",
        "valueField": "Finalizadas",
    "fillAlphas": 0
    },{
        "balloonText": "place taken by UK in [[category]]: [[value]]",
        "bullet": "round",
        "title": "Finalizadas con Incidencia",
        "valueField": "Finalizadas con Incidencia",
    "fillAlphas": 0
    },{
        "balloonText": "place taken by UK in [[category]]: [[value]]",
        "bullet": "round",
        "title": "Finalizadas Incompletas",
        "valueField": "Finalizadas Incompletas",
    "fillAlphas": 0
    },




],
    "chartCursor": {
        "cursorAlpha": 0,
        "zoomable": false
    },
    "categoryField": "year",
    "categoryAxis": {
        "gridPosition": "start",
        "axisAlpha": 0,
        "fillAlpha": 0.05,
        "fillColor": "#000000",
        "gridAlpha": 0,
        "position": "top"
    },
    "export": {
    	"enabled": true,
        "position": "bottom-right"
     }
});
    };

    graficaGeneral = function(jsonData, div){
        var graficaGeneral =AmCharts.makeChart(div, {
            "type": "serial",
            "theme": "light",
           
            "precision": 2,
            "valueAxes": [{
              "id": "v2",
              "title": "",
              "gridAlpha": 0,
              "position": "left",
              "autoGridCount": false
            }],
            "graphs": [{
              "id": "g3",
              "valueAxis": "v1",
              "lineColor": "#e1ede9",
              "fillColors": "#e1ede9",
              "fillAlphas": 1,
              "type": "column",
              "title": "Total",
              "valueField": "sales2",
              "clustered": false,
              "columnWidth": 0.5,
              "legendValueText": "[[value]]",
              "balloonText": "[[title]]<br/><b style='font-size: 130%'>[[value]]</b>"
            }, {
              "id": "g4",
              "valueAxis": "v1",
              "lineColor": "#62cf73",
              "fillColors": "#62cf73",
              "fillAlphas": 1,
              "type": "column",
              "title": "Finalizadas",
              "valueField": "sales1",
              "clustered": false,
              "columnWidth": 0.3,
              "legendValueText": "[[value]]",
              "balloonText": "[[title]]<br/><b style='font-size: 130%'>[[value]]</b>"
            }, {
              "id": "g1",
              "valueAxis": "v2",
              "bullet": "round",
              "bulletBorderAlpha": 1,
              "bulletColor": "#FFFFFF",
              "bulletSize": 5,
              "hideBulletsCount": 50,
              "lineThickness": 2,
              "lineColor": "#20acd4",
              "type": "smoothedLine",
              "title": "En Curso",
              "useLineColorForBulletBorder": true,
              "valueField": "market1",
              "balloonText": "[[title]]<br/><b style='font-size: 130%'>[[value]]</b>"
            }, {
              "id": "g2",
              "valueAxis": "v2",
              "bullet": "round",
              "bulletBorderAlpha": 1,
              "bulletColor": "#FFFFFF",
              "bulletSize": 5,
              "hideBulletsCount": 50,
              "lineThickness": 2,
              "lineColor": "#930F0F",
              "type": "smoothedLine",
              "dashLength": 5,
              "title": "Finalizadas con Incidencia",
              "useLineColorForBulletBorder": true,
              "valueField": "market2",
              "balloonText": "[[title]]<br/><b style='font-size: 130%'>[[value]]</b>"
            }],
            "chartScrollbar": {
              "graph": "g1",
              "oppositeAxis": false,
              "offset": 30,
              "scrollbarHeight": 50,
              "backgroundAlpha": 0,
              "selectedBackgroundAlpha": 0.1,
              "selectedBackgroundColor": "#888888",
              "graphFillAlpha": 0,
              "graphLineAlpha": 0.5,
              "selectedGraphFillAlpha": 0,
              "selectedGraphLineAlpha": 1,
              "autoGridCount": true,
              "color": "#AAAAAA"
            },
            "chartCursor": {
              "pan": true,
              "valueLineEnabled": true,
              "valueLineBalloonEnabled": true,
              "cursorAlpha": 0,
              "valueLineAlpha": 0.2
            },
            "categoryField": "date",
            "categoryAxis": {
            
              "dashLength": 1,
              "minorGridEnabled": true
            },
            "legend": {
              "useGraphSettings": true,
              "position": "top"
            },
            "balloon": {
              "borderThickness": 1,
              "shadowAlpha": 0
            },
            "export": {
              enabled: true
            },
            "dataProvider": jsonData
          });
    }




    $scope.showDates = false;
    $scope.showDatesFunc = function() {
        $scope.showDates = !$scope.showDates;
    }


    // init
    $scope.actives();
    $scope.cambia('General');

});