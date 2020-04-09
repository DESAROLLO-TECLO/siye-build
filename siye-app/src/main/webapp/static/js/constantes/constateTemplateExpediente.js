var includeDeseing=
	'	<div class="row">																														 					'+
	'		<div class="col-xs-12 col-sm-12 col-md-12">                                                                                                             '+
	'			<div class="{{isIncidencia ? \'col-xs-7 col-sm-8 col-md-8\' : \'col-xs-5 col-sm-6 col-md-8\'}}"></div>                                                                                                    '+
	'			<div class="col-xs-3 col-sm-2 col-md-2">                                                                                                            '+
	'				<input type="file"  ng-show="false"                                                                            				                    '+
	'					 id="file-1{{idElementUp}}"  onchange="angular.element(this).scope().getFilesFromInput(this)" 							                    '+
	'					class="inputfile inputfile-1" multiple />                                                                                                   '+
	'				<label for="file-1{{idElementUp}}" capture="camera" class="btn btn-danger  pull-right">                                                                     '+
	'					<i class="fa fa-camera fa-lg" aria-hidden="true"></i>                                                                                       '+
	'				</label>                                                                                                                                        '+
	'			</div>                                                                                                                                              '+
	'			<div class="col-xs-2 col-sm-2 col-md-1" ng-hide="isIncidencia">                                                                                                            '+
	'				<Button class="btn btn-danger pull-right" ng-click="saveImagesAll()" ng-disabled="(listImages | filter:{ isSuccess : false}).length > 0">                  '+
	'					<i class="fa fa-floppy-o fa-lg" aria-hidden="true"></i>                                                                                     '+
	'				</Button>                                                                                                                                       '+
	'			</div>                                                                                                                                              '+
	'			<div class="col-xs-2 col-sm-2 {{isIncidencia ? \'col-md-2\' : \'col-md-1\'}}">                                                                                                            '+
	'				<Button class="btn btn-danger pull-right" ng-click="cancelAllImage()" ng-disabled="listImages.length == 0">                                                '+
	'					<i class="fa fa-trash fa-lg" aria-hidden="true"></i>                                                                                        '+
	'				</Button>                                                                                                                                       '+
	'			</div>                                                                                                                                              '+
	'		</div>                                                                                                                                                  '+
	'		<div class="col-xs-12 col-sm-12 col-md-12">                                                                                                             '+
	'			<div class="col-xs-12 col-sm-12 col-md-12"                                                                                                          '+
	'						ng-scrollbar scrollbar-config="{show: false}">													                                        '+
	'				<div class="col-xs-12 col-sm-12 col-md-12 border-div"  file-drop on-image-drop="fileDropped">                                                '+
	'					<div ng-if="listImages.length == 0" id="zonaDrop{{idElementUp}}"                                                                            '+
	'					   class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-2 border-punteado-div">                                 '+
	'						<div class="col-xs-12 col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4 middleRow">                                                     '+
	'							<label class="stile-puntero-pointer" for="file-1{{idElementUp}}">                                                                   '+
	'								<i class="fa fa-upload fa-5x" aria-hidden="true"></i>                                                                           '+
	'							</label>                                                                                                                            '+
	'						</div>                                                                                                                                  '+
	'					</div>                                                                                                                                      '+
	'					<div ng-if="listImages.length > 0"		                                 												                    '+
	'						class="col-xs-12 col-sm-12 col-md-12">                                                                                                  '+
	'						<div ng-repeat="imagenVO in listImages | startFromGrid: viewPag.currentPage * viewPag.pageSize | limitTo: viewPag.pageSize">            '+
	'							<div class="col-xs-12 col-sm-12 col-md-12 padding-per">                                                                             '+
	'								<div class="row border-div-child">                                                                                              '+
	'									<div class="col-xs-6 col-sm-6 col-md-2 style-parent">                                                                       '+
	'										<label class="containerCheck" ng-if="paramConfiguracion.showComponentCopy">                                             '+
	'											<input id="{{idElementUp+imagenVO.unic}}inputUload" type="checkbox" ng-checked="imagenVO.isCopy"                    '+
	'												ng-false-value="false" ng-true-value="true" ng-model="fileItem.isCopy">                                         '+
	'											<span class="checkmark"></span>                                                                                     '+
	'										</label>                                                                                                                '+
	'																																			                    '+
	'										<div ng-if="imagenVO.isImage">																                            '+
	'											<i ng-if="imagenVO.showProgress" class="fa fa-spinner fa-lg fa-pulse fa-fw" aria-hidden="true"></i>					'+
	'											<img ng-if="!imagenVO.showProgress" id="img-{{idElementUp+unic}}"                                                   '+
	'												ng-click="showModalImg(imagenVO.strBase64,imagenVO.name)"                                                       '+
	'												class="stile-puntero-pointer img-img-fluid style-rep-img"                                                       '+
	'												ng-src="data:image/jpeg;base64, {{imagenVO.strBase64}}" />							                            '+
	'										</div>																				                                    '+
	'										<div ng-if="!imagenVO.isImage">                                                                                         '+
	'											<i class="fa fa-picture-o fa-3x" aria-hidden="true"></i>                                                            '+
	'										</div>                                                                                                                  '+
	'									</div>                                                                                                                      '+
	'									<div class="col-xs-6 col-sm-3 col-md-3">                                                                                    '+
	'										{{imagenVO.name.length > 14 ? (imagenVO.name.substr(0,14)+\'...\') : imagenVO.name}}                                    '+
	'										{{imagenVO.size/1024/1024|number:2}} Mb                                                                                 '+
	'									</div>                                                                                                                      '+
	'									<div class="col-xs-12 col-sm-3 col-md-3" ng-if="imagenVO.tpDocumentList.length > 0">                                        '+
	'										<div class="form-group">                                                                                                '+
	'											<label>                                                                                                             '+
	'												*Tipo de Documento                                                                                              '+
	'											</label>                                                                                                            '+
	'											<div class="input-group">                                                                                           '+
	'												<div class="input-group-addon">                                                                                 '+
	'													<i class="fa fa-list-alt"></i>                                                                              '+
	'												</div>                                                                                                          '+
	'												<select class="form-control" name="encuesta" id="encuesta"                                                      '+
	'													ng-required="true" select2 data-minimum-results-for-search="Infinity"                                       '+
	'													idioma-s2="{{currentLanguage}}" ng-model="imagenVO.tipoExpediente"                                          '+
	'													ng-options="tipo as tipo.nbTipoExpediente for tipo in tpDocumentList">                                      '+
	'													<option value="">{{\'APP.Base.mensaje.seleccioneOpcion\' | translate}}</option>                             '+
	'												</select>                                                                                                       '+
	'											</div>                                                                                                              '+
	'										</div>                                                                                                                  '+
	'									</div>                                                                                                                      '+
	'									<div class="col-xs-6 col-sm-2 col-md-2">                                                                                    '+
	'										<div class="col-xs-6 col-sm-6 col-md-6">                                                                                '+
	'											<label ng-if="!imagenVO.isSuccess" class="stile-puntero-pointer"                                                    '+
	'													ng-click="saveImageItem(imagenVO)">                                                                         '+
	'												<i class="fa fa-upload fa-2x style-warnning-image" aria-hidden="true"></i>                                      '+
	'											</label>                                                                                                            '+
	'											<label ng-if="imagenVO.isSuccess">                                                                                  '+
	'												<i class="fa fa-check-square fa-2x style-success-image" aria-hidden="true"></i>                                 '+
	'											</label>                                                                                                            '+
	'										</div>                                                                                                                  '+
	'										<div class="col-xs-6 col-sm-6 col-md-6">                                                                                '+
	'																																			                    '+
	'											<label class="stile-puntero-pointer" ng-click="cancelUploadeImageItem(imagenVO)">                                   '+
	'												<i class="fa fa-window-close fa-2x style-cancel-image" aria-hidden="true"></i>                                  '+
	'											</label>                                                                                                            '+
	'																																			                    '+
	'										</div>                                                                                                                  '+
	'									</div>                                                                                                                      '+
	'								</div>                                                                                                                          '+
	'							</div>			                                                                                                                    '+
	'						</div>                                                                                                                                  '+
	'					</div>                                                                                                                                      '+
	'				</div>                                                                                                                                          '+
	'				<div class="col-xs-12 col-sm-12 col-md-12">                                                                                                     '+
	'					<div class="col-xs-6 col-sm-6 col-md-6"></div>                                                                                              '+
	'					<div class="col-xs-6 col-sm-6 col-md-6">                                                                                         '+
	'						<div class="btn-group pull-right" ng-if="listImages.length > viewPag.pageSize">                                                                    '+
	'						  <button type="button" class="btn btn-danger" ng-disabled="viewPag.currentPage == 0"                                                   '+
	'									ng-click="viewPag.currentPage = viewPag.currentPage - 1">&laquo;</button>                                                   '+
	'						  <button type="button" class="btn btn-default" ng-disabled="viewPag.currentPage == page.no - 1"                                        '+
	'									ng-repeat="page in viewPag.pages" ng-click="setPage(page.no)">{{page.no}}</button>                                          '+
	'						  <button type="button" class="btn btn-danger" ng-disabled="viewPag.currentPage >= listImages.length/viewPag.pageSize - 1" ,            '+
	'								ng-click="viewPag.currentPage = viewPag.currentPage + 1">&raquo;</button>                                                       '+
	'						</div>                                                                                                                                  '+
	'					</div>                                                                                                                                      '+
	'				</div>                                                                                                                                          '+
	'			</div>                                                                                                                                              '+
	'		</div>                                                                                                                                                  '+
	'	 </div>                                                                                                                                                     ';
	
var includeDeseingModal=
	'<div ng-if="showModalBuild" class="modal fade" id="{{idElementUp}}modalUpdateImage" tabindex="-1" role="dialog" aria-hidden="true" 		'+
	'	data-keyboard="false" data-backdrop="static">                                                            								'+
	'  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">                                 								'+
	'    <div class="modal-content">                                                                             								'+
	'      <div class="modal-header">                                                                            								'+
	'      	<div><h3>{{paramConfComponent.titleModal == undefined ? \'Cargar Imagenes\' : paramConfComponent.titleModal}}</h3></div> 			'+
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
	' <div ng-if="showModalCarousel"  id="{{idElementUp}}modalCarousel"																'+
	' 	class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog"                                                          '+
	' 		aria-labelledby="Imagenes de {{\'Nombre nivel\'}}" aria-hidden="true" data-keyboard="false" data-backdrop="static">     '+
	'   <div class="modal-dialog modal-lg">                                                                                         '+
	'     <div class="modal-content">                                                                                               '+
	'		<div class="modal-body">                                                                                                '+
	'			<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">                                      '+
	'				 <div class="carousel-inner">                                                                                   '+
	'					<div ng-repeat="imagenVO in listImages">                                                         '+
	'						<div class="carousel-item active">                                                                      '+
	'							<img class="d-block w-100" id="img-{{idElementUp+imagenVO.unic}}" alt="{{imagenVO.name}}"        '+
	'							 ng-src="data:image/png;base64, {{imagenVO.strBase64}}" />                                 '+
	'						</div>                                                                                                  '+
	'					</div>                                                                                                      '+
	'				</div>                                                                                                          '+
	'				<a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">               '+
	'					<span class="carousel-control-prev-icon" aria-hidden="true"></span>                                         '+
	'					<span class="sr-only">Anterior</span>                                                                       '+
	'				</a>                                                                                                            '+
	'				<a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">               '+
	'					<span class="carousel-control-next-icon" aria-hidden="true"></span>                                         '+
	'					<span class="sr-only">Siguiente</span>                                                                      '+
	'				</a>                                                                                                            '+
	'			</div>                                                                                                              '+
	'		</div>                                                                                                                  '+
	'		<div class="modal-footer">                                                                                              '+
	'			<button class="btn bg-red-active btnMargin" type="button" ng-click="hideModal()">Cerrar</button>                    '+
	'	    </div>                                                                                                                  '+
	'     </div>                                                                                                                    '+
	'   </div>                                                                                                                      '+
	' </div>                                                                                                                        ';

angular.module(appTeclo).constant('constTemplateExpediente', {
	'templateTableExp': includeDeseing,
    'templateModalExp': includeDeseingModal,
    'templateCarouseModal':includeCarouselModal
});