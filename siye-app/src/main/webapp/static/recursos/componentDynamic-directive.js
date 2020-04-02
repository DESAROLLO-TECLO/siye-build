var app = angular.module(appTeclo);

app.directive('componente', function($compile, $document, growl, $rootScope, $timeout, reporteConfiguracionService) {
    return {
        restrict: 'E',
        template: `<form role="form" name="formDinamico" novalidate></form>`,
        transclude: true,
        replace: true,
        scope: {
            //propiedades
            array: "=",
            arrayTmp: "=",
            nameForm: "=",
            modelChecklist: "="
        },
        link: function(scope, element, attrs, modelo) {
            scope.flag = false;
            scope.dateTimeOptions = { format: 'DD/MM/YYYY HH:mm:ss' };
            $rootScope.listaValido = {
                isListaDoble: false,
                listaValidado: false,
                separador: undefined
            };
            scope.cambioSelect = function(parentValue, contador) {
                scope.array = scope.arrayTmp;
                var paramParent = scope.array[contador];
                var paramsParandeSons = scope.array[contador].dependencias;

                if (parentValue == null || parentValue == undefined) {
                    if (paramParent.padres != null && paramParent.padres.length > 0) { //en caso de que el parametro dependa de otro
                        for (var n = 0; n < scope.array.length; n++) {
                            if (paramParent.padres[0].cdParam === scope.array[n].cdParametro) {
                                scope.cambioSelect(scope.nameForm[paramParent.padres[0].cdParam].$modelValue, n);
                                break;
                            }
                            break;
                        }
                    }
                } else {
                    for (var i = 0; i < paramsParandeSons.length; i++) { //iterar los parametros dependientes
                        for (var j = 0; j < scope.array.length; j++) { //iterar lista de parametros
                            if (paramsParandeSons[i].cdParam === scope.array[j].cdParametro) { //comparar el código por el que se va filtrar
                                var listCatValuesSon = scope.array[j].catValues; //Obtener los valores del catalogo del hijo
                                var nuevaListaValores = []; //inicializar la lista donde se van a filtrar los valores
                                for (var k = 0; k < listCatValuesSon.length; k++) { //iterar el catalogo del hijo
                                    var valoresSeparado = parentValue.toString(); //se convierte a string el valor
                                    var strValSplit = valoresSeparado.split(','); //se separan los valores por coma, esto para solventar select multiple        						
                                    for (var l = 0; l < strValSplit.length; l++) { //recorrero los valores separado
                                        if (paramParent.padres != null && paramParent.padres.length > 0) { //en caso de que el parametro dependa de otro
                                            for (var m = 0; m < paramParent.padres.length; m++) { //recorrer los padres del parametro filtro
                                                //comparar los valores de la lista
                                                if (listCatValuesSon[k][paramsParandeSons[i].nbColumnaFiltro] == Number(strValSplit[l]) &&
                                                    listCatValuesSon[k][paramParent.padres[m].nbColumnaFiltro] == scope.nameForm[paramParent.padres[m].cdParam].$modelValue
                                                ) { //si sale true quiere decir que el la acción del change afectará a mas de un select
                                                    nuevaListaValores.push(listCatValuesSon[k]); //agregar valores filtrados
                                                } else if (listCatValuesSon[k][paramsParandeSons[i].nbColumnaFiltro] == Number(strValSplit[l]) &&
                                                    listCatValuesSon[k][paramParent.padres[m].nbColumnaFiltro] == undefined) { //si sale false quieres decirque la dependencia es en cadena
                                                    nuevaListaValores.push(listCatValuesSon[k]); //agregar valores filtrados
                                                }
                                            }
                                        } else { //caso en que el parametro no depende de otro
                                            if (listCatValuesSon[k][paramsParandeSons[i].nbColumnaFiltro] == Number(strValSplit[l])) { //comparar los valors
                                                nuevaListaValores.push(listCatValuesSon[k]); //agregar los valores filtrados
                                            }
                                        }
                                    }
                                }
                                scope.nameForm[scope.array[j].cdParametro].$setViewValue(); //limpiar el contenido del parametro actual
                                $timeout(function() { //timeout para que angular le de tiempo limpiar el form
                                    scope.nameForm.$setPristine();
                                }, true);
                                scope["listaValores" + j] = nuevaListaValores; //setear los nuevo valores de la lista
                            }
                        }
                    }
                }
            };

            scope.$watch('modelo', function(newValue, oldValue) {
                $rootScope.nuevoModelo = newValue;
            });
            scope.modelo = [{ nombreLista: "Resultados", requerido: false }, { nombreLista: "*Seleccione", listado: [], requerido: true }];
            scope.busquedaPrevia = function(idParametro, valores) {
                scope.modelo = [{ nombreLista: "Resultados", requerido: false }, { nombreLista: "*Seleccione", listado: [], requerido: true }];
                if (idParametro != undefined && valores != null && valores != "") {
                    reporteConfiguracionService.consultaPrevia(idParametro, valores).success(function(data) {
                        scope.modelo[0].listado = data;
                        scope.flag = true;
                        $rootScope.idParametro = idParametro;
                        $rootScope.listaValido.listaValidado = true;
                    }).error(function(data) {
                        growl.error(data.message, { ttl: 3000 });
                        scope.modelo = angular.copy(scope.modeloOriginal);
                        $rootScope.listaValido.listaValidado = false;
                    });
                } else {
                    growl.error('Lo valores son requeridos', { ttl: 4000 });
                }
            };

            var a = scope.array;
            //var listElemenst = [];
            var listelementsObject = [];
            scope.nameForm = scope.formDinamico;
            scope.modelChecklist = scope.modeloDirectiveChecKList;

            scope.$watch('formDinamico', function(newValue, oldValue) {
                scope.nameForm = scope.formDinamico;
            });


            scope.$watch('modeloDirectiveChecKList', function(newValue, oldValue) {
                scope.modelChecklist = scope.modeloDirectiveChecKList;
            });




            if (a.length > 0) {
                for (var i = 0; i < a.length; i++) {
                    typeElementToCreate(a[i], i);
                }
            }


            if (listelementsObject.length > 0) {
                if (listelementsObject.length == 1) {
                    if (listelementsObject[0].cdComponente === 'LISTD') {
                        $("#boxPrincipal").removeClass("col-md-6 col-md-offset-3").addClass("col-md-10 col-md-offset-1");
                        var div = createdivRow();
                        div.addClass("col-md-12");
                        div.append(listelementsObject[0].elementAngular);
                    } else if (listelementsObject[0].cdComponente !== 'LISTD') {
                        var div = createdivRow();
                        div.addClass("col-md-12");
                        if (listelementsObject[0].cdComponente !== 'RADIO' && listelementsObject[0].cdComponente !== 'CHEKBOX') //a los componente de tipo radiobutton no se le pone
                            listelementsObject[0].elementAngular.addClass('col-md-offset-3');
                        div.append(listelementsObject[0].elementAngular);
                    }
                } else if (listelementsObject.length == 2) {
                    var div = createdivRow();
                    for (var i = 0; i < listelementsObject.length; i++) {
                        if (listelementsObject[i].cdComponente === 'LISTD') {
                            $("#boxPrincipal").removeClass("col-md-6 col-md-offset-3").addClass("col-md-10 col-md-offset-1");
                            var div = createdivRow();
                            div.addClass("col-md-12");
                            div.append(listelementsObject[i].elementAngular);
                        } else {
                            div.append(listelementsObject[i].elementAngular);
                        }
                    }
                } else if (listelementsObject.length == 3) {
                    var div = createdivRow();
                    for (var i = 0; i < listelementsObject.length; i++) {
                        if (listelementsObject[i].cdComponente === 'LISTD') {
                            $("#boxPrincipal").removeClass("col-md-6 col-md-offset-3").addClass("col-md-10 col-md-offset-1");
                            var div = createdivRow();
                            div.addClass("col-md-12");
                            div.append(listelementsObject[i].elementAngular);
                        } else {
                            div.append(listelementsObject[i].elementAngular);
                        }
                    }
                } else if (listelementsObject.length > 3) {
                    var div = createdivRow();
                    for (var i = 0; i < listelementsObject.length; i++) {
                        if (listelementsObject[i].cdComponente === 'LISTD') {
                            $("#boxPrincipal").removeClass("col-md-6 col-md-offset-3").addClass("col-md-10 col-md-offset-1");
                            var divListdoble = createdivRow();
                            divListdoble.addClass("col-md-12");
                            divListdoble.append(listelementsObject[i].elementAngular);
                        } else if (listelementsObject[i].cdComponente !== 'LISTD') {
                            if (i % 2 == 0)
                                div = createdivRow();
                            div.append(listelementsObject[i].elementAngular);
                        }
                    }
                }
                $compile(element)(scope);
            }

            function createdivRow() {
                var divRow = angular.element("<div class='row'></div>");
                element.append(divRow);
                return divRow;
            }

            function typeElementToCreate(parametro, count) {
                var objElement = undefined;
                var elementDOM = undefined;
                var elementAngular = undefined;
                switch (parametro.componente.cdComponente) {
                    case "INP":
                        elementDOM = input(parametro);
                        elementAngular = angular.element(elementDOM);
                        objElement = { 'cdComponente': parametro.componente.cdComponente, 'elementAngular': elementAngular };
                        listelementsObject.push(objElement);
                        break;
                    case "FH":
                        elementDOM = dateShort(parametro);
                        elementAngular = angular.element(elementDOM);
                        objElement = { 'cdComponente': parametro.componente.cdComponente, 'elementAngular': elementAngular };
                        listelementsObject.push(objElement);
                        break;
                    case "FHT":
                        elementDOM = dateLong(parametro);
                        elementAngular = angular.element(elementDOM);
                        objElement = { 'cdComponente': parametro.componente.cdComponente, 'elementAngular': elementAngular };
                        listelementsObject.push(objElement);
                        break;
                    case "SELEUNICSINBUS":
                        scope["listaValores" + count] = parametro.catValues; //Generar un atributo dinamico
                        elementDOM = selectUnicoSinBusqueda(parametro, count);
                        elementAngular = angular.element(elementDOM);
                        objElement = { 'cdComponente': parametro.componente.cdComponente, 'elementAngular': elementAngular };
                        listelementsObject.push(objElement);
                        break;
                    case "SELEUNICCONBUS":
                        scope["listaValores" + count] = parametro.catValues; //Generar un atributo dinamico
                        elementDOM = selectUnicoConBusqueda(parametro, count);
                        elementAngular = angular.element(elementDOM);
                        objElement = { 'cdComponente': parametro.componente.cdComponente, 'elementAngular': elementAngular };
                        listelementsObject.push(objElement);
                        break;
                    case "SELEMULTIBYGROUP":
                        elementDOM = selectMulConGroup(parametro);
                        elementAngular = angular.element(elementDOM);
                        objElement = { 'cdComponente': parametro.componente.cdComponente, 'elementAngular': elementAngular };
                        listelementsObject.push(objElement);
                        break;
                    case "SELEMULTISINGROUP":
                        scope["listaValores" + count] = parametro.catValues; //Generar un atributo dinamico
                        elementDOM = selectMulSinGroup(parametro, count);
                        elementAngular = angular.element(elementDOM);
                        objElement = { 'cdComponente': parametro.componente.cdComponente, 'elementAngular': elementAngular };
                        listelementsObject.push(objElement);
                        break;
                    case "SWITCH":
                        elementDOM = switcher(parametro);
                        elementAngular = angular.element(elementDOM);
                        objElement = { 'cdComponente': parametro.componente.cdComponente, 'elementAngular': elementAngular };
                        listelementsObject.push(objElement);
                        break;
                    case "CHEKBOX":
                        elementDOM = checkBox(parametro);
                        elementAngular = angular.element(elementDOM);
                        objElement = { 'cdComponente': parametro.componente.cdComponente, 'elementAngular': elementAngular };
                        listelementsObject.push(objElement);
                        break;
                    case "RADIO":
                        elementDOM = redioButton(parametro);
                        elementAngular = angular.element(elementDOM);
                        objElement = { 'cdComponente': parametro.componente.cdComponente, 'elementAngular': elementAngular };
                        listelementsObject.push(objElement);
                        break;
                    case "LISTD":
                        elementDOM = listaDoble(parametro);
                        elementAngular = angular.element(elementDOM);
                        objElement = { 'cdComponente': parametro.componente.cdComponente, 'elementAngular': elementAngular };
                        listelementsObject.push(objElement);
                        $rootScope.listaValido.isListaDoble = true;
                        break;
                    default:
                        alert("No está definido el tipo de componente");
                        break;
                }
            };

            function selectMulConGroup(param) {
                var aditionalProperties = createAditionalProperties(param.parametrosPropAux);
                var elemReturn = `<div class="col-md-6">
        			<div class="form-group"
						ng-class="{\'has-error\': (formDinamico.${param.cdParametro}.$invalid && formDinamico.${param.cdParametro}.$dirty) }">
						<label>${param.nbParametro}</label>
						<div class="input-group">
							<div class="input-group-addon">
								<i class="fa fa-list-ul"></i>
							</div>
							<select class="form-control" select2
								multiple="multiple"
								data-allow-clear="true"
								idioma-s2="{{currentLanguage}}" ${aditionalProperties}
								ng-model="${param.cdParametro}" name="${param.cdParametro}" 
								id="${param.cdParametro}" >
								ng-options="sm.nbOpcion group by sm.nbGroup for sm in selectList">
							</select>
						</div>
						<div ng-show="formDinamico.${param.cdParametro}.$error.required && formDinamico.${param.cdParametro}.$dirty">
							<span ng-style="{color:\'red\'}">El campo ${param.nbParametro} es requerido</span>
						</div>
					</div>
				</div>`;
                return elemReturn; //return angular element
            };

            function selectUnicoConBusqueda(param, count) {
                var aditionalProperties = createAditionalProperties(param.parametrosPropAux);
                var fnChnage = '';
                if (param.dependencias != null && param.dependencias.length > 0)
                    fnChnage = 'ng-change=cambioSelect(' + param.cdParametro + ',' + count + ')';
                if (param.padres != null)
                    scope["listaValores" + count] = [];

                var elemReturn = `<div class="col-md-6">
					<div class="form-group"
        			ng-class="{\'has-error\': (formDinamico.${param.cdParametro}.$invalid && formDinamico.${param.cdParametro}.$dirty) }">
						<label>${param.nbParametro}</label>
						<div class="input-group">
							<div class="input-group-addon">
								<i class="fa fa-list-ul"></i>
							</div>
							<select class="form-control" select2 ${fnChnage} 
								idioma-s2="{{currentLanguage}}" ${aditionalProperties}
								ng-model="${param.cdParametro}" name="${param.cdParametro}"
								ng-options="v.ID as v.DESCRIPTION for v in listaValores${count}">
								id="${param.cdParametro}" >
								<option value="">{{\'APP.Base.mensaje.seleccioneOpcion\' | translate}}</option>
							</select>
						</div>
						<div ng-show="formDinamico.${param.cdParametro}.$error.required && formDinamico.${param.cdParametro}.$dirty">
							<span ng-style="{color:\'red\'}">El campo ${param.nbParametro} es requerido</span>
						</div>
					</div>
				</div>`;
                return elemReturn; //return angular element
            };

            function selectUnicoSinBusqueda(param, count) {
                var aditionalProperties = createAditionalProperties(param.parametrosPropAux);
                var fnChnage = '';
                if (param.dependencias != null && param.dependencias.length > 0)
                    fnChnage = 'ng-change=cambioSelect(' + param.cdParametro + ',' + count + ')';
                if (param.padres != null)
                    scope["listaValores" + count] = [];
                var elemReturn = `<div class="col-md-6">
        					<div class="form-group"
        						ng-class="{\'has-error\': (formDinamico.${param.cdParametro}.$invalid && formDinamico.${param.cdParametro}.$dirty) }">
								<label>${param.nbParametro}</label>
								<div class="input-group">
									<div class="input-group-addon">
										<i class="fa fa-list-ul"></i>
									</div>
									<select class="form-control" select2 ${fnChnage}
										data-minimum-results-for-search="Infinity"
										idioma-s2="{{currentLanguage}}" ${aditionalProperties}
										ng-model="${param.cdParametro}" name="${param.cdParametro}" 
										ng-options="v.ID as v.DESCRIPTION for v in listaValores${count}">
										id="${param.cdParametro}" >
										<option value="">{{\'APP.Base.mensaje.seleccioneOpcion\' | translate}}</option>
									</select>
								</div>
								<div ng-show="formDinamico.${param.cdParametro}.$error.required && formDinamico.${param.cdParametro}.$dirty">
									<span ng-style="{color:\'red\'}">El campo ${param.nbParametro} es requerido</span>
								</div>
							</div>
						</div>`;
                return elemReturn; //return angular element
            };

            function dateLong(param) { //dd/mm/yyyy HH:mm:ss
                var aditionalProperties = createAditionalProperties(param.parametrosPropAux);
                var elemReturn = `<div class="col-md-6">
							<div class="form-group"
								ng-class="{\'has-error\': (formDinamico.${param.cdParametro}.$invalid && formDinamico.${param.cdParametro}.$dirty) }">
								<label>${param.nbParametro}</label>
								<div class="input-group" datetime-picker options="dateTimeOptions"
										format-date-long="true" ${aditionalProperties}
										${param.cdParametro}
										ng-model="${param.cdParametro}" name="${param.cdParametro}"
										idioma-dtp="{{currentLanguage}}" id="${param.cdParametro}">
									<div class="input-group-addon labelCheck">
										<i class="fa fa-calendar-plus-o"></i>
									</div>
									<input class="form-control inputFecha" readonly/>
								</div>
								<div ng-show="formDinamico.${param.cdParametro}.$error.required && formDinamico.${param.cdParametro}.$dirty">
									<span ng-style="{color:\'red\'}">El campo ${param.nbParametro} es requerido</span>
								</div>
							</div>
						</div>`;
                return elemReturn; //return angular element
            };

            function dateShort(param) { //dd/mm/yyyy
                var aditionalProperties = createAditionalProperties(param.parametrosPropAux);
                var elemReturn = `<div class="col-md-6">
							<div class="form-group"
								ng-class="{\'has-error\': (formDinamico.${param.cdParametro}.$invalid && formDinamico.${param.cdParametro}.$dirty) }">
								<label>${param.nbParametro}</label>
								<div class="input-group">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input class="form-control inputFecha" date-picker ${aditionalProperties}
										id="${param.cdParametro}" name="${param.cdParametro}"
										ng-model="${param.cdParametro}"
										readonly/>
								</div>
								<div ng-show="formDinamico.${param.cdParametro}.$error.required && formDinamico.${param.cdParametro}.$dirty">
									<span ng-style="{color:\'red\'}">El campo ${param.nbParametro} es requerido</span>
								</div>
							</div>
						</div>`;
                return elemReturn; //return angular element
            };

            function selectMulSinGroup(param, count) {
                var aditionalProperties = createAditionalProperties(param.parametrosPropAux);
                var fnChnage = '';
                if (param.dependencias != null && param.dependencias.length > 0)
                    fnChnage = 'ng-change=cambioSelect(' + param.cdParametro + ',' + count + ')';
                if (param.padres != null)
                    scope["listaValores" + count] = [];
                var elemReturn = `<div class="col-md-6">
                			<div class="form-group"
        						ng-class="{\'has-error\': (formDinamico.${param.cdParametro}.$invalid && formDinamico.${param.cdParametro}.$dirty) }">
        						<label>${param.nbParametro}</label>
        						<div class="input-group">
        							<div class="input-group-addon">
        								<i class="fa fa-list-alt"></i>
        							</div>
        							<select class="form-control" ${fnChnage}
        								select-picker multiple data-live-search="true" data-live-search-placeholder="Buscar..." ${aditionalProperties}
        								data-actions-box="true"
        								data-selected-text-format="count > 2"
        								ng-model="${param.cdParametro}" name="${param.cdParametro}" 
        								id="${param.cdParametro}"
        								ng-options="v.ID as v.DESCRIPTION for v in listaValores${count}">
        							</select>
        						</div>
        						<div ng-show="formDinamico.${param.cdParametro}.$error.required && formDinamico.${param.cdParametro}.$dirty">
        							<span ng-style="{color:\'red\'}">El campo ${param.nbParametro} es requerido</span>
        						</div>
        					</div>
        				</div>`;
                return elemReturn; //return angular element
            };

            function input(param) {
                var aditionalProperties = createAditionalProperties(param.parametrosPropAux);
                var txAyuda = ' ';
                if (param.txAyuda != null && param.txAyuda != '') {
                    txAyuda += ` <span data-toggle="tooltip" data-placement="top"
						data-html="true"
						data-original-title="${param.txAyuda}">
							<i class="fa fa-question-circle"></i>
					</span>`;
                }
                var elemReturn = `<div class="col-md-6">
							<div class="form-group"
								ng-class="{\'has-error\': (formDinamico.${param.cdParametro}.$invalid && formDinamico.${param.cdParametro}.$dirty) }">
								<label>${param.nbParametro} ${txAyuda}</label>
								<div class="input-group">
									<div class="input-group-addon labelCheck">
										<i class="fa fa-hashtag"></i>
									</div>
									<input class="form-control" ${aditionalProperties}
									ng-model="${param.cdParametro}" name="${param.cdParametro}"  id="${param.cdParametro}">
								</div>
								<div ng-show="formDinamico.${param.cdParametro}.$error.required && formDinamico.${param.cdParametro}.$dirty">
									<span ng-style="{color:\'red\'}">El campo ${param.nbParametro} es requerido</span>
								</div>
							</div>
						</div>`;
                return elemReturn; //return angular element
            };

            function switcher(param) {
                //var aditionalProperties = createAditionalProperties(param.parametrosPropAux);

                let txValor = param.txValor; //obtener la cadena de txvalor
                let txValorSeparado = param.txValor.split(','); //separar cadena de txvalor
                let txValorUno = txValorSeparado[0].split('|'); //separar el label uno
                let txValorDos = txValorSeparado[1].split('|'); //separar el label dos


                var elemReturn = `<div class="col-md-6">
        					<div class="form-group"
        						ng-class="{\'has-error\': (formDinamico.${param.cdParametro}.$invalid && formDinamico.${param.cdParametro}.$dirty) }">
								<label for="${param.cdParametro}" class="labelCheck noneSelectedUser">${param.nbParametro}</label>
								<switcher class="swTeclo pull-right"
									id-check="${param.cdParametro}"
									name="${param.cdParametro}"
									true-label="${txValorUno[0]}"
									true-value="'${txValorUno[1]}'"
									false-label="${txValorDos[0]}"
									false-value="'${txValorDos[1]}'"
									ng-change="checkVal(newValue, oldValue)"
									ng-model="${param.cdParametro}">
								</switcher>
							</div>
						</div>`;
                return elemReturn; //return angular element
            };

            function checkBox(param) {
                let elemReturn = '';
                let strReturn = `<div class="col-md-12">
									<fieldset class="scheduler-border">
									<legend class="scheduler-border">${param.nbParametro}</legend>
									<div class="checkBoxDinamic">`;
                let elemIntercambio = '';
                if (param.catValues.length > 0) {
                    for (let i = 0; i < param.catValues.length; i++) {
                        if (i % 3 === 0 && i != 0) {
                            elemIntercambio += `<div class="row">`;
                            elemIntercambio += elemReturn;
                            elemIntercambio += `</div>`;
                            strReturn += elemIntercambio;
                            elemIntercambio = '';
                            elemReturn = '';
                        }
                        elemReturn +=
                            `<div class="col-md-4" style="padding-left: 2.5rem";>
							<div class="form-group">
        						<div class="input-group">
									<div class="checkbox">
										<label> 
											<input type="checkbox"
												checklist-model="modeloDirectiveChecKList"
												checklist-value="${param.catValues[i].ID}"
												name="${param.cdParametro}">
											<span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span>
											${param.catValues[i].DESCRIPTION}
										</label>
									</div>
								</div>
							</div>
        				</div>`;
                    }
                    strReturn += `</div></fieldset></div>`;
                }
                return strReturn;
            };

            /*ESTE ELEMENTO SERÁ DE TIPO CATÁLOGO*/
            function redioButton(param) {
                /*ESTE COMPONENTE SERÁ DE TIPO CATALOGO*/
                let elemReturn = '';
                let strReturn = `<div class="col-md-12">
									<fieldset class="scheduler-border">
									<legend class="scheduler-border">${param.nbParametro}</legend>
									<div class="radioButtonDinamic">`;
                let elemIntercambio = '';
                if (param.catValues.length > 0) {
                    for (let i = 0; i < param.catValues.length; i++) {
                        if (i % 3 === 0 && i != 0) {
                            elemIntercambio += `<div class="row">`;
                            elemIntercambio += elemReturn;
                            elemIntercambio += `</div>`;
                            strReturn += elemIntercambio;
                            elemIntercambio = '';
                            elemReturn = '';
                        }
                        elemReturn +=
                            `<div class="col-md-4">
							<div class="form-group">
								<div class="input-group">
									<div class="radio">
										<label>
										<input type="radio" name="${param.cdParametro}" value="${param.catValues[i].ID}" 
												id="${param.cdParametro}" ng-model="${param.cdParametro}">
												<span class="cr">
												<i class="cr-icon glyphicon glyphicon-remove"></i></span>
												${param.catValues[i].DESCRIPTION}
										</label>
									</div>
								</div>
							</div>
						</div>`;
                    }
                    strReturn += `</div></fieldset></div>`;
                }
                return strReturn;
            };

            function listaDoble(param) {
                var concatenacion = '';
                if (param.parametrosPropAux.length > 0)
                    for (var i = 0; i < param.parametrosPropAux.length; i++) {
                        if (param.parametrosPropAux[i].propiedad.cdPropiedad === 'QUERY') {
                            concatenacion = param.parametrosPropAux[i].txValor;
                            break;
                        }
                    }
                concatenacion
                $rootScope.listaValido.separador = concatenacion;
                var elemReturn = `<div class="row">
							<div class="col-md-12"
								ng-class="{\'has-error\': (formDinamico.busquePrevia${param.idParamtro}.$invalid && formDinamico.busquePrevia${param.idParamtro}.$dirty) }">
								<label for="${param.idParamtro}">Escriba una o más ${param.nbParametro} separadas por (${concatenacion}) :</label>
									<div class="input-group">
										<span class="input-group-addon"> <i
										class="fa fa-pencil"></i></span>
										<input class="form-control" type="text" ng-required="true" size=\'50\'
										id="busquePrevia${param.idParamtro}" name="busquePrevia${param.idParamtro}" placeholder="Buscar... "
										ng-model="busquePrevia${param.idParamtro}" /> 
										<span class="input-group-btn">
											<button class=" btn btn-danger btn-flat" 
											ng-click="busquedaPrevia(${param.idParamtro},busquePrevia${param.idParamtro})">Validar</button>
										</span>
									</div>
									<div ng-show="formDinamico.busquePrevia${param.idParamtro}.$error.required && formDinamico.busquePrevia${param.idParamtro}.$dirty">
										<span ng-style="{color:\'red\'}">El campo ${param.nbParametro} es requerido</span>
									</div>
							</div>
						</div><br/>
						<div class="row">
							<div class="col-md-12">
								<dnd-lists col="col-md-5" max-height="25vh" model="modelo"
									nb-list="nombreLista" nb-item="listado" nb-texts="Nombre:"
									nb-labels="LD_DESCRIPCION" flag-success="flag" required-list="true"
									btn-reset="false" img-cursor="static/dist/img/handheld.png">
							</div>
						</div>
						</div><br/>`;
                return elemReturn;
            };

            function createAditionalProperties(listProperties) {
                var aditionalProperties = '';
                if (listProperties != null && listProperties.length > 0) {
                    angular.forEach(listProperties, function(value, key) {
                        //Validación cuando la propiedad no tiene valor asociado
                        //ejemplo: capitalize, integer-number
                        if (value.propiedad.stValorRequerido === 0) {
                            aditionalProperties += value.propiedad.nbHtmlPropiedad + ' ';
                        } else if (value.propiedad.stValorRequerido != 0 && (value.txValor != null || value.txValor != undefined)) {
                            aditionalProperties += value.propiedad.nbHtmlPropiedad + '=' + '\"' + value.txValor + '\" ';
                        }
                    });
                }
                return aditionalProperties;
            };
        }
    };
});