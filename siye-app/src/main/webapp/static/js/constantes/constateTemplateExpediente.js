var includeDeseing=
	  '<div class="block-{{idElementUp}}">																																																		 '+
	  '    <form name="formTpDocument" novalidate>                                                                                                                                                                                                 '+
    '        <div class="row">                                                                                                                                                                                                                   '+
    '            <div class="col-xs-12 col-sm-12 col-md-12">                                                                                                                                                                                     '+
    '                <div class="{{isIncidencia ? \'col-xs-12 col-sm-8 col-md-8\' : \'col-xs-12 col-sm-6 col-md-8\'}}">                                                                                                                          '+
    '                    <div class="col-xs-6"><label>No. Imagenes:</label>{{\' \'+ (listFiles == undefined ? 0 : listFiles.length)}} <span ng-show="maxNuImage != undefined"><label>de</label> {{\' \'+ maxNuImage}}</span></div>               '+
    '                    <div class="col-xs-6"><label>Por guardar:</label>{{\' \'+((listFiles | filter:{ isSuccess : false}).length)}}</div>                                                                                                     '+
    '                </div>                                                                                                                                                                                                                      '+
    '                <div class="col-xs-4 col-sm-2 col-md-2">                                                                                                                                                                                    '+
    '                    <input type="file"  ng-show="false"                                                                                                                                                                                     '+
    '                         id="file-1{{idElementUp}}"  custom-on-change handler="getFilesFromInput(params)"                                                                                                                                   '+
    '                        class="inputfile inputfile-1" multiple />                                                                                                                                                                           '+
    '                    <label for="file-1{{idElementUp}}" capture="camera" class="btn btn-danger  pull-right">                                                                                                                                 '+
    '                        <i class="fa fa-camera fa-lg" aria-hidden="true"></i>                                                                                                                                                               '+
    '                    </label>                                                                                                                                                                                                                '+
    '                </div>                                                                                                                                                                                                                      '+
    '                <div class="col-xs-4 col-sm-2 col-md-1" ng-hide="isIncidencia">                                                                                                                                                             '+
    '                    <Button class="btn btn-danger pull-right" ng-click="saveImagesAll(formTpDocument)" ng-disabled="((listFiles | filter:{ isSuccess : false}).length == 0)">                                                               '+
    '                        <i class="fa fa-floppy-o fa-lg" aria-hidden="true"></i>                                                                                                                                                             '+
    '                    </Button>                                                                                                                                                                                                               '+
    '                </div>                                                                                                                                                                                                                      '+
    '                <div class="col-xs-4 col-sm-2 {{isIncidencia ? \'col-md-2\' : \'col-md-1\'}}">                                                                                                                                              '+
    '                    <Button class="btn btn-danger pull-right" ng-click="cancelAllImage()" ng-disabled="listFiles.length == 0">                                                                                                              '+
    '                        <i class="fa fa-trash fa-lg" aria-hidden="true"></i>                                                                                                                                                                '+
    '                    </Button>                                                                                                                                                                                                               '+
    '                </div>                                                                                                                                                                                                                      '+
    '            </div>                                                                                                                                                                                                                          '+
    '            <div class="col-xs-12 col-sm-12 col-md-12">                                                                                                                                                                                     '+
    '                <div class="col-xs-12 col-sm-12 col-md-12">                                                                                                                                                                  				 '+
    '                    <div class="col-xs-12 col-sm-12 col-md-12 border-div"  file-drop on-image-drop="fileDropped">                                                                                                                           '+
    '                        <div ng-if="listFiles == undefined || listFiles.length == 0" id="zonaDrop{{idElementUp}}"                                                                                                                           '+
    '                           class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-2 border-punteado-div">                                                                                                         '+
    '                            <div class="col-xs-12 col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4 middleRow">                                                                                                                             '+
    '                                <label class="stile-puntero-pointer" for="file-1{{idElementUp}}">                                                                                                                                           '+
    '                                    <i class="fa fa-upload fa-5x" aria-hidden="true"></i>                                                                                                                                                   '+
    '                                </label>                                                                                                                                                                                                    '+
    '                            </div>                                                                                                                                                                                                          '+
    '                        </div>                                                                                                                                                                                                              '+
    '                        <div ng-if="listFiles.length > 0"                                                                                                                                                                                   '+
    '                            class="col-xs-12 col-sm-12 col-md-12">                                                                                                                                                                          '+
    '                            <div ng-repeat="file in listFiles  | startFromGrid: ((paramConfigPage.bigCurrentPage-1)*paramConfigPage.itemsPerPage) | limitTo: paramConfigPage.itemsPerPage">                                                 '+
    '                                <div class="col-xs-12 col-sm-12 col-md-12 padding-per">                                                                                                                                                     '+
    '                                    <div class="row border-div-child">                                                                                                                                                                      '+
    '                                        <div class="text-center {{showCombo ? \'col-xs-4 col-sm-4 col-md-2 style-parent\' : \'col-xs-6 col-sm-4 col-md-3 style-parent\'}}">                                                                             '+
    '                                            <div ng-if="file.isImage">                                                                                                                                                                      '+
    '                                                 <div ng-if="file.strBase64 != undefined">                                                                                                                                                  '+
    '                                                    <img id="img-{{idElementUp+unic}}"                                                                                                                                                      '+
    '                                                        ng-click="showModalImg(file)"                                                                                                                                                       '+
    '                                                        class="stile-puntero-pointer img-img-fluid style-rep-img"                                                                                                                           '+
    '                                                        ng-src="data:image/jpeg;base64, {{file.strBase64}}" />                                                                                                                              '+
    '                                                </div>                                                                                                                                                                                      '+
    '                                                <div ng-if="file.strBase64 == undefined">                                                                                                                                					 '+
    '													<div class="form-group"> 																																								 '+
    '														<label>&nbsp;</label> 																																								 '+
    '														<div>																																							 					 '+
    '                                                    		<i class="fa fa-spinner fa-2x fa-spin text-success"></i>                                                                           												 '+
    '														</div>																																												 '+
    '													</div>																																													 '+
    '                                                </div>                                                                                                                                                                                      '+
    '                                            </div>                                                                                                                                                                                          '+
    '                                            <div ng-if="!file.isImage">                                                                                                                                                                     '+
    '                                                <i class="fa fa-picture-o fa-3x" aria-hidden="true"></i>                                                                                                                                    '+
    '                                            </div>                                                                                                                                                                                          '+
    '                                        </div>                                                                                                                                                                                              '+
    '                                        <div class="{{showCombo ? \'col-xs-4 col-sm-4 col-md-2\' : \' col-xs-6 col-sm-2 col-md-2\'}}">                                                                                                      '+
    '                                            <div class="form-group">                                                                                                                                                                        '+
    '                                                <label>Nombre: </label>                                                                                                                                                                     '+
    '                                                <div class="input-group">                                                                                                                                                                   '+
    '                                                    {{file.name.length > 14 ? (file.name.substr(0,14)+\'...\') : file.name}}                                                                                                                '+
    '                                                </div>                                                                                                                                                                                      '+
    '                                            </div>                                                                                                                                                                                          '+
    '                                        </div>                                                                                                                                                                                              '+
    '                                        <div class="{{showCombo ? \'col-xs-4 col-sm-4 col-md-2\' : \' col-xs-6 col-sm-2 col-md-2\'}}">                                                                                                      '+
    '                                            <div class="form-group">                                                                                                                                                                        '+
    '                                                <label>Tama&ntilde;o:</label>                                                                                                                                                               '+
    '                                                <div class="input-group">                                                                                                                                                                   '+
    '                                                    {{file.exedeSize ? \'0.0 Mb\' : (file.size | prettySize)}}                                                                                                                              '+
    '                                                </div>                                                                                                                                                                                      '+
    '                                            </div>                                                                                                                                                                                          '+
    '                                        </div>                                                                                                                                                                                              '+
    '                                        <div class="col-xs-12 col-sm-12 col-md-3" ng-if="showCombo">                                                                                                                                        '+
    '                                            <div class="form-group"                                                                                                                                                                         '+
    '                                                    ng-class="{\'has-error\': (formTpDocument[\'tpDoc\'+file.unic+\'\'+idElementUp].$invalid && formTpDocument[\'tpDoc\'+file.unic+\'\'+idElementUp].$dirty) }">                            '+
    '                                                <label>                                                                                                                                                                                     '+
    '                                                    *Tipo de Documento:                                                                                                                                                                     '+
    '                                                </label>                                                                                                                                                                                    '+
    '                                                <div class="input-group">                                                                                                                                                                   '+
    '                                                    <div class="input-group-addon">                                                                                                                                                         '+
    '                                                        <i class="fa fa-list-alt"></i>                                                                                                                                                      '+
    '                                                    </div>                                                                                                                                                                                  '+
    '                                                    <select ng-if="file.unic != undefined" class="form-control" name="tpDoc{{file.unic+\'\'+idElementUp}}" id="tpDoc{{file.unic+\'\'+idElementUp}}"                                         '+
    '                                                        ng-required="showCombo" select2 data-minimum-results-for-search="Infinity"                                                                                                          '+
    '                                                        idioma-s2="{{currentLanguage}}" ng-model="file.tipoExpediente"                                                                                                                      '+
    '                                                        ng-options="tipo as tipo.nbTipoExpediente for tipo in file.tpDocumentList | orderBy:\'nuOrden\'">                                                                                                         '+
    '                                                        <option value="">{{\'APP.Base.mensaje.seleccioneOpcion\' | translate}}</option>                                                                                                     '+
    '                                                    </select>                                                                                                                                                                               '+
    '                                                </div>                                                                                                                                                                                      '+
    '                                                <div ng-show="(formTpDocument[\'tpDoc\'+file.unic+\'\'+idElementUp].$invalid && formTpDocument[\'tpDoc\'+file.unic+\'\'+idElementUp].$dirty)">                                              '+
    '                                                    <span class="help-block">                                                                                                                                                               '+
    '                                                        El tipo de documento es requerido                                                                                                                                                   '+
    '                                                    </span>                                                                                                                                                                                 '+
    '                                                </div>                                                                                                                                                                                      '+
    '                                            </div>                                                                                                                                                                                          '+
    '                                        </div>                                                                                                                                                                                              '+
    '                                        <div class="{{showCombo ? \'col-xs-12 col-sm-12 col-md-3\' : \'col-xs-12 col-sm-4 col-md-4\'}}">                                                                                                    '+
    '                                            <div class="col-xs-6 col-sm-6 col-md-6">                                                                                                                                                        '+
    '                                                <div class="form-group float-rigth-icon">                                                                                                                                                   '+
    '                                                    <label>&nbsp;</label>                                                                                                                                                                   '+
    '                                                    <div class="input-group">                                                                                                                                                               '+
    '                                                        <div ng-show="!file.isSuccess" class="{{!isIncidencia ? \'stile-puntero-pointer\' : \'\'}}">                                                                                        '+
    '                                                            <label class="stile-puntero-pointer" ng-show="!isIncidencia" ng-click="saveImageItem(file,formTpDocument,(\'tpDoc\'+file.unic+\'\'+idElementUp))">                              '+
    '                                                                <i class="fa fa-upload fa-2x style-warnning-image" aria-hidden="true"></i>                                                                                                  '+
    '                                                            </label>                                                                                                                                                                        '+
    '                                                            <label ng-if="isIncidencia">                                                                                                                                                    '+
    '                                                                <i class="fa fa-check-square fa-2x style-warnning-image" aria-hidden="true"></i>                                                                                            '+
    '                                                            </label>                                                                                                                                                                        '+
    '                                                        </div>                                                                                                                                                                              '+
    '                                                        <label ng-if="file.isSuccess">                                                                                                                                                      '+
    '                                                            <i class="fa fa-check-square fa-2x style-success-image" aria-hidden="true"></i>                                                                                                 '+
    '                                                        </label>                                                                                                                                                                            '+
    '                                                    </div>                                                                                                                                                                                  '+
    '                                                </div>                                                                                                                                                                                      '+
    '                                            </div>                                                                                                                                                                                          '+
    '                                            <div class="col-xs-6 col-sm-6 col-md-6">                                                                                                                                                        '+
    '                                                <div class="form-group float-left-icon">                                                                                                                                                    '+
    '                                                    <label>&nbsp;</label>                                                                                                                                                                   '+
    '                                                    <div class="input-group">                                                                                                                                                               '+
    '                                                        <label class="stile-puntero-pointer" ng-click="cancelUploadeImageItem(file)">                                                                                                       '+
    '                                                            <i class="fa fa-window-close fa-2x style-cancel-image" aria-hidden="true"></i>                                                                                                  '+
    '                                                        </label>                                                                                                                                                                            '+
    '                                                    </div>                                                                                                                                                                                  '+
    '                                                </div>                                                                                                                                                                                      '+
    '                                            </div>                                                                                                                                                                                          '+
    '                                        </div>                                                                                                                                                                                              '+
    '                                    </div>                                                                                                                                                                                                  '+
    '                                </div>                                                                                                                                                                                                      '+
    '                            </div>                                                                                                                                                                                                          '+
    '                        </div>                                                                                                                                                                                                              '+
    '                    </div>                                                                                                                                                                                                                  '+
    '                    <div class="col-xs-12 col-sm-12 col-md-12">                                                                                                                                                                             '+
    '                        <div class="col-xs-1 col-sm-6 col-md-6">                                                                                                                                                                            '+
    '                         </div>                                                                                                                                                                                                             '+
    '                        <div class="col-xs-11 col-sm-6 col-md-6 pull-right">                                                                                                                                                                '+
    '                            <uib-pagination previous-text="Anterior"                                                                                                                                                                        '+
    '                                next-text="Siguiente"                                                                                                                                                                                       '+
    '                                total-items="listFiles.length" num-pages="numPages"                                                                                                                                                         '+
    '                                ng-model="paramConfigPage.bigCurrentPage"                                                                                                                                                                   '+
    '                                max-size="paramConfigPage.maxSize"                                                                                                                                                                          '+
    '                                items-per-page="paramConfigPage.itemsPerPage"                                                                                                                                                               '+
    '                                class="pagination-md pull-right" boundary-link-numbers="true"                                                                                                                                               '+
    '								 ng-change="pageChanged(listFiles)"																																													 '+
    '                                direction-links="true" rotate="false">                                                                                                                                                                      '+
    '                            </uib-pagination>                                                                                                                                                                                               '+
    '                        </div>                                                                                                                                                                                                              '+
    '                    </div>                                                                                                                                                                                                                  '+
    '                </div>                                                                                                                                                                                                                      '+
    '            </div>                                                                                                                                                                                                                          '+
    '        </div>                                                                                                                                                                                                                              '+
    '    </form>                                                                                                                                                                                                                                 '+
    '	<div id="div-progress-{{idElementUp}}" class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3" ng-show="showProgressBar">                                                   								 '+
	  '		<label>Cargando Imagenes</label>																																																	 '+
	  '		<div class="progress-ex-per">																																																		 '+
	  '			<div id="progresBarUno-{{idElementUp}}" class="progress-bar-ex-per" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">0%</div>                                                            			 '+
	  '			<div id="progresBarDos-{{idElementUp}}" class="progress-bar-ex-per" role="remaining" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>                                                                			 '+
	  '		</div>																																																								 '+
	  '	</div>                                                                                                                                                                                                                  				 '+
    '</div>                                                                                                                                                                                                                                      ';
	
var includeDeseingModal=
	'<div ng-show="showModalBuild" class="modal fade" id="{{idElementUp}}modalUpdateImage" tabindex="-1" role="dialog" aria-hidden="true" 		'+
	'	data-keyboard="false" data-backdrop="static">                                                            								'+
	'  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">                                 								'+
	'    <div class="modal-content">                                                                             								'+
	'      <div class="modal-header">                                                                            								'+
	'      	<div><h3>{{paramConfComponent.title == undefined ? \'Cargar Imagenes\' : paramConfComponent.title}}</h3></div> 			'+
	'      </div>                                                                                                								'+
	'      <div class="modal-body">                                                                              								'+
	'      	<div class="container-fluid">                                                                        								'+
	'      		<div id="containerModal"></div>                                                                  								'+
						includeDeseing																											 +
	'      	</div>                                                                                               								'+
	'      </div>                                                                                                								'+
	'      <div class="modal-footer">                                                                            								'+
	'        <button class="btn bg-red-active btnMargin" type="button" ng-click="cerrarModal()">Cerrar</button>  								'+
	'      </div>                                                                                                								'+
	'    </div>                                                                                                  								'+
	'  </div>                                                                                                    								'+
	'</div>                                                                                                      								';


var includeCarouselModal=
	'	<div ng-show="showModalCarousel" class="modal fade {{ (include && showInModal) ? \'style-modal-modal\' : \'\'}}" id="{{idElementUp}}modalCarousel" tabindex="-1" role="dialog" 								'+
	'					data-keyboard="false" data-backdrop="static"	 aria-hidden="false" aria-labelledby="modalIploadImage">																			    '+
	'		<div class="modal-dialog modal-lg">                                                                                                             '+
	'			<div class="modal-content">                                                                                                                 '+
	'				<div class="modal-header">                                                                                                              '+
	'					<h4 class="modal-title" id="gridSystemModalLabel">Im�genes</h4>                                             '+
	'				</div>                                                                                                                                  '+
	'				<div class="modal-body">                                                                                                                '+
	'					<div id="carousel-{{idElementUp}}" class="carousel slide" data-ride="carousel">                                                     '+
	'						                                                                                                                                '+
	'						<!-- Wrapper for slides -->                                                                                                     '+
	'						<div class="carousel-inner" role="listbox">                                                                                     '+
	' 							<div class="col-md-10 col-md-offset-1 middleRow"> 																			'+
	'								<div class="item active">                                                                                               '+
	'									<img style="max-width: 100%;" id="img-{{idElementUp+imagePreview.unic}}" alt="{{imagePreview.name}}"                                         '+
	'											ng-src="data:image/png;base64, {{imagePreview.strBase64}}" />                                               '+
	'									<div class="carousel-caption">                                                                                      '+
	'										{{imagePreview.name.length > 14 ? (imagePreview.name.substr(0,14)+\'...\') : imagePreview.name}}                '+
	'									</div>                                                                                                              '+
	'								</div>                                                                                                                  '+
	'							</div>                                                                                                                          '+
	'						</div>                                                                                                                          '+
	'	                                                                                                                                                    '+
	'						<!-- Controls -->                                                                                                               '+
	'						<a class="left carousel-control" ng-show="false" ng-click="prevImg()" role="button" data-slide="prev">                                          '+
	'							<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>                                                   '+
	'							<span class="sr-only">Previous</span>                                                                                       '+
	'						</a>                                                                                                                            '+
	'						<a class="right carousel-control" ng-show="false" ng-click="nextImg()" role="button" data-slide="next">                                         '+
	'							<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>                                                  '+
	'							<span class="sr-only">Next</span>                                                                                           '+
	'						</a>                                                                                                                            '+
	'					</div>                                                                                                                              '+
	'				</div>                                                                                                                                  '+
	'				<div class="modal-footer">																												'+
    '					<button class="btn bg-red-active btnMargin" type="button" ng-click="hideModal()">Cerrar</button>								'+
    '				</div>																																	'+  
	'			</div><!-- /.modal-content -->                                                                                                              '+
	'		</div><!-- /.modal-dialog -->                                                                                                                   '+
	'	</div><!-- /.modal -->                                                                                                                              ';

angular.module(appTeclo).constant('constTemplateExpediente', {
	'templateTableExp': includeDeseing,
    'templateModalExp': includeDeseingModal,
    'templateCarouseModal':includeCarouselModal
});