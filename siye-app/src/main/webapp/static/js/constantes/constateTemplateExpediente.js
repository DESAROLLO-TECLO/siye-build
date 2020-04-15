var includeDeseing=
	'	<form name="formTpDocument" novalidate>																														 					'+
	'	<div class="row">																														 										'+
	'		<div class="col-xs-12 col-sm-12 col-md-12">                                                                                                                                 '+
	'			<div class="{{isIncidencia ? \'col-xs-7 col-sm-8 col-md-8\' : \'col-xs-5 col-sm-6 col-md-8\'}}">                                                                  		'+
	'				<div class="col-xs-6"><label>No. Imagenes:</label>{{\' \'+ (listImages == undefined ? 0 : listImages.length)}} <span ng-show="maxNuImage != undefined"><label>de</label> {{\' \'+ maxNuImage}}</span></div>  '+
	'				<div class="col-xs-6"><label>Por guardar:</label>{{\' \'+((listImages | filter:{ isSuccess : false}).length)}}</div>                              					'+
	'			</div>                                                                  																								'+
	'			<div class="col-xs-3 col-sm-2 col-md-2">                                                                                                                                '+
	'				<input type="file"  ng-show="false"                                                                            				                                        '+
	'					 id="file-1{{idElementUp}}"  onchange="angular.element(this).scope().getFilesFromInput(this)" 							                                        '+
	'					class="inputfile inputfile-1" multiple />                                                                                                                       '+
	'				<label for="file-1{{idElementUp}}" capture="camera" class="btn btn-danger  pull-right">                                                                             '+
	'					<i class="fa fa-camera fa-lg" aria-hidden="true"></i>                                                                                                           '+
	'				</label>                                                                                                                                                            '+
	'			</div>                                                                                                                                                                  '+
	'			<div class="col-xs-2 col-sm-2 col-md-1" ng-hide="isIncidencia">                                                                                                         '+
	'				<Button class="btn btn-danger pull-right" ng-click="saveImagesAll(formTpDocument)" ng-disabled="(listImages | filter:{ isSuccess : false}).length == 0">            '+
	'					<i class="fa fa-floppy-o fa-lg" aria-hidden="true"></i>                                                                                                         '+
	'				</Button>                                                                                                                                                           '+
	'			</div>                                                                                                                                                                  '+
	'			<div class="col-xs-2 col-sm-2 {{isIncidencia ? \'col-md-2\' : \'col-md-1\'}}">                                                                                          '+
	'				<Button class="btn btn-danger pull-right" ng-click="cancelAllImage()" ng-disabled="listImages.length == 0">                                                         '+
	'					<i class="fa fa-trash fa-lg" aria-hidden="true"></i>                                                                                                            '+
	'				</Button>                                                                                                                                                           '+
	'			</div>                                                                                                                                                                  '+
	'		</div>                                                                                                                                                                      '+
	'		<div class="col-xs-12 col-sm-12 col-md-12">                                                                                                                                 '+
	'			<div class="col-xs-12 col-sm-12 col-md-12"                                                                                                                              '+
	'						ng-scrollbar scrollbar-config="{show: false}">													                                                            '+
	'				<div class="col-xs-12 col-sm-12 col-md-12 border-div"  file-drop on-image-drop="fileDropped">                                                                       '+
	'					<div ng-if="listImages == undefined || listImages.length == 0" id="zonaDrop{{idElementUp}}"                                                                                                '+
	'					   class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-2 border-punteado-div">                                                     '+
	'						<div class="col-xs-12 col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4 middleRow">                                                                         '+
	'							<label class="stile-puntero-pointer" for="file-1{{idElementUp}}">                                                                                       '+
	'								<i class="fa fa-upload fa-5x" aria-hidden="true"></i>                                                                                               '+
	'							</label>                                                                                                                                                '+
	'						</div>                                                                                                                                                      '+
	'					</div>                                                                                                                                                          '+
	'					<div ng-if="listImages.length > 0"		                                 												                                        '+
	'						class="col-xs-12 col-sm-12 col-md-12">                                                                                                                      '+
	'						<div ng-repeat="imagenVO in listImages | startFromGrid: ((paramConfigPage.bigCurrentPage-1)*paramConfigPage.itemsPerPage) | limitTo: paramConfigPage.itemsPerPage">                                												'+
	'							<div class="col-xs-12 col-sm-12 col-md-12 padding-per">                                                                                                 '+
	'								<div class="row border-div-child">                                                                                                                  '+
	'									<div class="{{showCombo ? \'col-xs-6 col-sm-6 col-md-2 style-parent\' : \'col-xs-6 col-sm-4 col-md-3 style-parent\'">                           '+
	'										<label class="containerCheck" ng-if="paramConfiguracion.showComponentCopy">                                                                 '+
	'											<input id="{{idElementUp+imagenVO.unic}}inputUload" type="checkbox" ng-checked="imagenVO.isCopy"                                        '+
	'												ng-false-value="false" ng-true-value="true" ng-model="fileItem.isCopy">                                                             '+
	'											<span class="checkmark"></span>                                                                                                         '+
	'										</label>                                                                                                                                    '+
	'																																			                                        '+
	'										<div ng-if="imagenVO.isImage">																                                                '+
	'											<i ng-if="imagenVO.showProgress" class="fa fa-spinner fa-lg fa-pulse fa-fw" aria-hidden="true"></i>					                    '+
	'											<img ng-if="!imagenVO.showProgress" id="img-{{idElementUp+unic}}" data-slide-to="{{imagenVO.unic}}"                                     '+
	'												ng-click="showModalImg(imagenVO)"                                                                           						'+
	'												class="stile-puntero-pointer img-img-fluid style-rep-img"                                                                           '+
	'												ng-src="data:image/jpeg;base64, {{imagenVO.strBase64}}" />							                                                '+
	'										</div>																				                                                        '+
	'										<div ng-if="!imagenVO.isImage">                                                                                                             '+
	'											<i class="fa fa-picture-o fa-3x" aria-hidden="true"></i>                                                                                '+
	'										</div>                                                                                                                                      '+
	'									</div>                                                                                                                                          '+
	'									<div class="{{showCombo ? \'col-xs-6 col-sm-3 col-md-2\' : \' col-xs-6 col-sm-2 col-md-2\'}}">                                                  '+
	'										<div class="form-group">                                                                                                                    '+
	'											<label>Nombre:</label>                                                                                                                  '+
	'											<div class="input-group">                                                                                                               '+
	'												{{imagenVO.name.length > 14 ? (imagenVO.name.substr(0,14)+\'...\') : imagenVO.name}}                   								'+
	'											</div>																																	'+
	'										</div>																																		'+
	'									</div>                                                                                                                                          '+
	'									<div class="{{showCombo ? \'col-xs-6 col-sm-3 col-md-2\' : \' col-xs-6 col-sm-2 col-md-2\'}}">                                                  '+
	'										<div class="form-group">                                                                                                                    '+
	'											<label>Tama&ntilde;o:</label>                                                                                                           '+
	'											<div class="input-group">                                                                                                               '+
	'												{{imagenVO.size/1024/1024|number:3}} Mb                                                                                             '+
	'											</div>																																	'+
	'										</div>																																		'+
	'									</div>                                                                                                                                          '+
	'									<div class="col-xs-12 col-sm-12 col-md-3" ng-if="showCombo">                                                                                    '+
	'										<div class="form-group"                                                                                 									'+
	'												ng-class="{\'has-error\': (formTpDocument[\'tpDoc\'+imagenVO.unic+\'\'+idElementUp].$invalid && formTpDocument[\'tpDoc\'+imagenVO.unic+\'\'+idElementUp].$dirty) }">									'+
	'											<label>                                                                                         										'+
	'												*Tipo de Documento                                                                          										'+
	'											</label>                                                                                        										'+
	'											<div class="input-group">                                                                       										'+
	'												<div class="input-group-addon">                                                             										'+
	'													<i class="fa fa-list-alt"></i>                                                          										'+
	'												</div>                                                                                      										'+
	'												<select ng-if="imagenVO.unic != undefined" class="form-control" name="tpDoc{{imagenVO.unic+\'\'+idElementUp}}" id="tpDoc{{imagenVO.unic+\'\'+idElementUp}}"                                            								'+
	'													ng-required="showCombo" select2 data-minimum-results-for-search="Infinity"              										'+
	'													idioma-s2="{{currentLanguage}}" ng-model="imagenVO.tipoExpediente"                      										'+
	'													ng-options="tipo as tipo.nbTipoExpediente for tipo in imagenVO.tpDocumentList">         										'+
	'													<option value="">{{\'APP.Base.mensaje.seleccioneOpcion\' | translate}}</option>         										'+
	'												</select>                                                                                   										'+
	'											</div>                                                                                          										'+
	'											<div ng-show="(formTpDocument[\'tpDoc\'+imagenVO.unic+\'\'+idElementUp].$invalid && formTpDocument[\'tpDoc\'+imagenVO.unic+\'\'+idElementUp].$dirty)">													'+
	'												<span class="help-block">																											'+
	'													El tipo de documento es requerido																								'+
	'												</span>																																'+
	'											</div>																																	'+
	'										</div>                                                                                                  									'+
	'									</div>                                                                                                                                          '+
	'									<div class="{{showCombo ? \'col-xs-12 col-sm-12 col-md-3\' : \'col-xs-12 col-sm-4 col-md-4\'}}">                                                '+
	'										<div class="col-xs-6 col-sm-6 col-md-6">                                                                                                    '+
	'											<div class="form-group">                                                                                                                '+
	'												<label>&nbsp;</label>                                                                                                               '+
	'												<div class="input-group">                                                                                                           '+
	'													<div ng-show="!imagenVO.isSuccess" class="{{!isIncidencia ? \'stile-puntero-pointer\' : \'\'}}">                                  '+
	'														<label class="stile-puntero-pointer" ng-show="!isIncidencia" ng-click="saveImageItem(imagenVO,formTpDocument,(\'tpDoc\'+imagenVO.unic+\'\'+idElementUp))">	            '+
	'															<i class="fa fa-upload fa-2x style-warnning-image" aria-hidden="true"></i>                                    			'+
	'														</label>																													'+
	'														<label ng-if="isIncidencia">	                                                                                            '+
	'															<i class="fa fa-check-square fa-2x style-warnning-image" aria-hidden="true"></i>                               			'+
	'														</label>																													'+
	'													</div>                                                                                                                          '+
	'													<label ng-if="imagenVO.isSuccess">                                                                                              '+
	'														<i class="fa fa-check-square fa-2x style-success-image" aria-hidden="true"></i>                                             '+
	'													</label>                                                                                                                        '+
	'												</div>                                                                                                                              '+
	'											</div>                                                                                                                                  '+
	'										</div>                                                                                                                                      '+
	'										<div class="col-xs-6 col-sm-6 col-md-6">                                                                                                    '+
	'											<div class="form-group">                                                                                                                '+
	'												<label>&nbsp;</label>                                                                                                               '+
	'												<div class="input-group">                                                                                                           '+
	'													<label class="stile-puntero-pointer" ng-click="cancelUploadeImageItem(imagenVO)">                                               '+
	'														<i class="fa fa-window-close fa-2x style-cancel-image" aria-hidden="true"></i>                                              '+
	'													</label>                                                                                                                        '+
	'												</div>                                                                                                                              '+
	'											</div>                                                                                                                                  '+
	'										</div>                                                                                                                                      '+
	'									</div>                                                                                                                                          '+
	'								</div>                                                                                                                                              '+
	'							</div>			                                                                                                                                        '+
	'						</div>                                                                                                                                                      '+
	'					</div>                                                                                                                                                          '+
	'				</div>                                                                                                                                                              '+
	'				<div class="col-xs-12 col-sm-12 col-md-12">                                                                                                                         '+
	'					<div class="col-xs-1 col-sm-6 col-md-6">                                                                                                                  		'+
	'         			</div> 																																							'+
	'					<div class="col-xs-11 col-sm-6 col-md-6 pull-right">                                                                                                            '+
	'						<uib-pagination previous-text="Anterior"																													'+                                                                                                                                                                
	'							next-text="Siguiente"																																	'+                                                                                                                                                                               
	'							total-items="listImages.length" num-pages="numPages" 																									'+                                                                                                                                    
	'							ng-model="paramConfigPage.bigCurrentPage"																												'+                                                                                                                                                           
	'							max-size="paramConfigPage.maxSize"																														'+                                                                                                                                                                  
	'							items-per-page="paramConfigPage.itemsPerPage"																											'+                                                                                                                                                       
	'							class="pagination-md pull-right" boundary-link-numbers="true"																							'+                                                                                                                                                  
	'							direction-links="true" rotate="false">																													'+                                                                                                                                                              
	'						</uib-pagination>																																			'+ 
	'					</div>                                                                                                                                                          '+
	'				</div>                                                                                                                                                              '+
	'			</div>                                                                                                                                                                  '+
	'		</div>                                                                                                                                                                      '+
	'	</div>                                                                                                                                                                          '+
	'	</form>																														 													';
	
var includeDeseingModal=
	'<div ng-if="showModalBuild" class="modal fade" id="{{idElementUp}}modalUpdateImage" tabindex="-1" role="dialog" aria-hidden="true" 		'+
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
	'	<div ng-if="showModalCarousel" class="modal fade" id="{{idElementUp}}modalCarousel" tabindex="-1" role="dialog" 									'+
	'						aria-labelledby="myModalLabel" aria-hidden="true">																			    '+
	'		<div class="modal-dialog modal-lg">                                                                                                             '+
	'			<div class="modal-content">                                                                                                                 '+
	'				<div class="modal-header">                                                                                                              '+
	'					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">�</span></button>              '+
	'					<h4 class="modal-title" id="gridSystemModalLabel">Imagenes de {{\'Nombre nivel\'}}</h4>                                             '+
	'				</div>                                                                                                                                  '+
	'				<div class="modal-body">                                                                                                                '+
	'					<div id="carousel-{{idElementUp}}" class="carousel slide" data-ride="carousel">                                                     '+
	'						                                                                                                                                '+
	'						<!-- Wrapper for slides -->                                                                                                     '+
	'						<div class="carousel-inner" role="listbox">                                                                                     '+
	' 							<div class="col-md-10 col-md-offset-1 middleRow"> 																			'+
	'								<div class="item active">                                                                                               '+
	'									<img id="img-{{idElementUp+imagePreview.unic}}" alt="{{imagePreview.name}}"                                         '+
	'											ng-src="data:image/png;base64, {{imagePreview.strBase64}}" />                                               '+
	'									<div class="carousel-caption">                                                                                      '+
	'										{{imagePreview.name.length > 17 ? (imagePreview.name.substr(0,17)+\'...\') : imagePreview.name}}                '+
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
	'			</div><!-- /.modal-content -->                                                                                                              '+
	'		</div><!-- /.modal-dialog -->                                                                                                                   '+
	'	</div><!-- /.modal -->                                                                                                                              ';

angular.module(appTeclo).constant('constTemplateExpediente', {
	'templateTableExp': includeDeseing,
    'templateModalExp': includeDeseingModal,
    'templateCarouseModal':includeCarouselModal
});