var includeDeseing=
	'	<div class="row">																														 '+
	'		<div class="col-xs-12 col-sm-12 col-md-12">                                                                                          '+
	'			<div class="{{col-xs-6 col-sm-6 col-md-9"></div>                                                                                 '+
	'			<div class="col-xs-2 col-sm-2 col-md-1">                                                                                         '+
	'				<input type="file" nv-file-select ng-show="false"                                                                            '+
	'					uploader="fileUploaderDvt" id="file-1{{idElementUp}}"                                                                    '+
	'					class="inputfile inputfile-1" multiple />                                                                                '+
	'				<label for="file-1{{idElementUp}}" capture="camera" class="btn btn-danger">                                                  '+
	'					<i class="fa fa-camera fa-lg" aria-hidden="true"></i>                                                                    '+
	'				</label>                                                                                                                     '+
	'			</div>                                                                                                                           '+
	'			<div class="col-xs-2 col-sm-2 col-md-1">                                                                                         '+
	'				<Button class="btn btn-danger">                                                                                              '+
	'					<i class="fa fa-floppy-o fa-lg" aria-hidden="true"></i>                                                                  '+
	'				</Button>                                                                                                                    '+
	'			</div>                                                                                                                           '+
	'			<div class="col-xs-2 col-sm-2 col-md-1">                                                                                         '+
	'				<label class="btn btn-danger">                                                                                               '+
	'					<i class="fa fa-clipboard" aria-hidden="true"></i>                                                                       '+
	'				</label>                                                                                                                     '+
	'			</div>                                                                                                                           '+
	'		</div>                                                                                                                               '+
	'		<div class="col-xs-12 col-sm-12 col-md-12">                                                                                          '+
	'			<div class="col-xs-12 col-sm-12 col-md-12">                                                                                      '+
	'				<div class="col-xs-12 col-sm-12 col-md-12 border-div"  nv-file-drop  uploader="fileUploaderDvt" multiple>                    '+
	'					<div ng-if="(fileUploaderDvt.queue | filter:{ idEleFile : idElementUp}).length == 0"                                     '+
	'					   class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-2 border-punteado-div">              '+
	'						<div class="col-xs-12 col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4 middleRow">                                  '+
	'							<label class="stile-puntero-pointer" for="file-1{{idElementUp}}">                                                '+
	'								<i class="fa fa-upload fa-5x" aria-hidden="true"></i>                                                        '+
	'							</label>                                                                                                         '+
	'						</div>                                                                                                               '+
	'					</div>                                                                                                                   '+
	'					<div ng-if="(fileUploaderDvt.queue | filter:{ idEleFile : idElementUp}).length > 0"                                      '+
	'						class="col-xs-12 col-sm-12 col-md-12">                                                                               '+
	'						<div ng-repeat="fileItem in (fileUploaderDvt.queue | filter:{ idEleFile : idElementUp})">                            '+
	'							<div class="col-xs-12 col-sm-12 col-md-12 padding-per">                                                          '+
	'								<div class="row border-div-child">                                                                           '+
	'									<div class="col-xs-6 col-sm-6 col-md-2 style-parent">                                                    '+
	'										<label class="containerCheck" ng-if="paramConfiguracion.showComponentCopy">                          '+
	'											<input id="{{idElementUp+fileItem.unic}}inputUload" type="checkbox" ng-checked="fileItem.isCopy" '+
	'												ng-false-value="false" ng-true-value="true" ng-model="fileItem.isCopy">                      '+
	'											<span class="checkmark"></span>                                                                  '+
	'										</label>                                                                                             '+
	'																																			 '+
	'										<div ng-if="isImage(fileItem.file)">																 '+
	'											<img id="img-{{idElementUp+fileItem.unic}}"                                                      '+
	'												ng-click="showModalImg(fileItem.imagenVO.strBase64,fileItem.nameImg)"                        '+
	'												class="stile-puntero-pointer img-img-fluid style-rep-img"                                    '+
	'												ng-src="data:image/png;base64, {{fileItem.imagenVO.strBase64}}" />							 '+
	'										</div>																				                 '+
	'										<div ng-if="!isImage(fileItem.file)">                                                                '+
	'											<i class="fa fa-picture-o fa-3x" aria-hidden="true"></i>                                         '+
	'										</div>                                                                                               '+
	'									</div>                                                                                                   '+
	'									<div class="col-xs-6 col-sm-3 col-md-3">                                                                 '+
	'										{{fileItem.nameImg}}                                                                                 '+
	'										{{fileItem.file.size/1024/1024|number:2}} Mb                                                         '+
	'									</div>                                                                                                   '+
	'									<div class="col-xs-12 col-sm-3 col-md-3" ng-if="fileItem.imagenVO.tpDocumentList.length > 0">            '+
	'										<div class="form-group">                                                                             '+
	'											<label>                                                                                          '+
	'												*Tipo de Documento                                                                           '+
	'											</label>                                                                                         '+
	'											<div class="input-group">                                                                        '+
	'												<div class="input-group-addon">                                                              '+
	'													<i class="fa fa-list-alt"></i>                                                           '+
	'												</div>                                                                                       '+
	'												<select class="form-control" name="encuesta" id="encuesta"                                   '+
	'													ng-required="true" select2 data-minimum-results-for-search="Infinity"                    '+
	'													idioma-s2="{{currentLanguage}}" ng-model="fileItem.imagenVO.tipoExpediente"              '+
	'													ng-options="tipo as tipo.nbTipoExpediente for tipo in tpDocumentList">                   '+
	'													<option value="">{{\'APP.Base.mensaje.seleccioneOpcion\' | translate}}</option>          '+
	'												</select>                                                                                    '+
	'											</div>                                                                                           '+
	'										</div>                                                                                               '+
	'									</div>                                                                                                   '+
	'									<div class="col-xs-6 col-sm-2 col-md-2">                                                                 '+
	'										<div class="col-xs-6 col-sm-6 col-md-6">                                                             '+
	'											<label ng-if="!fileItem.isUploaded && !fileItem.isSuccess" class="stile-puntero-pointer"         '+
	'													ng-click="saveImageItem(fileItem)">                                                      '+
	'												<i class="fa fa-upload fa-2x style-warnning-image" aria-hidden="true"></i>                   '+
	'											</label>                                                                                         '+
	'											<label ng-if="fileItem.isUploaded && fileItem.isSuccess">                                        '+
	'												<i class="fa fa-check-square fa-2x style-success-image" aria-hidden="true"></i>              '+
	'											</label>                                                                                         '+
	'										</div>                                                                                               '+
	'										<div class="col-xs-6 col-sm-6 col-md-6">                                                             '+
	'																																			 '+
	'											<label class="stile-puntero-pointer" ng-click="cancelUploadeImageItem(fileItem)">                '+
	'												<i class="fa fa-window-close fa-2x style-cancel-image" aria-hidden="true"></i>               '+
	'											</label>                                                                                         '+
	'																																			 '+
	'										</div>                                                                                               '+
	'									</div>                                                                                                   '+
	'								</div>                                                                                                       '+
	'							</div>			                                                                                                 '+
	'						</div>                                                                                                               '+
	'					</div>                                                                                                                   '+
	'																																			 '+
	'				</div>                                                                                                                       '+
	'			</div>                                                                                                                           '+
	'		</div>                                                                                                                               '+
	'	 </div>                                                                                                                                  ';
	
var includeDeseingModal=
	'<div ng-if="showModalBuild" class="modal fade" id="{{idElementUp}}modalUpdateImage" tabindex="-1" role="dialog" aria-hidden="true" '+
	'	data-keyboard="false" data-backdrop="static">                                                            '+
	'  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">                                 '+
	'    <div class="modal-content">                                                                             '+
	'      <div class="modal-header">                                                                            '+
	'      	<div><h3>{{paramConfComponent.titleModal == undefined ? \'Cargar Imagenes\' : paramConfComponent.titleModal}}</h3></div> '+
	'      </div>                                                                                                '+
	'      <div class="modal-body">                                                                              '+
	'      	<div class="container-fluid">                                                                        '+
	'      		<div id="containerModal"></div>                                                                  '+
						includeDeseing+
	'      	</div>                                                                                               '+
	'      </div>                                                                                                '+
	'      <div class="modal-footer">                                                                            '+
	'        <button class="btn bg-red-active btnMargin" type="button" ng-click="cerrarModal()">Cerrar</button>  '+
	'      </div>                                                                                                '+
	'    </div>                                                                                                  '+
	'  </div>                                                                                                    '+
	'</div>                                                                                                      ';


var includeCarouselModal=
	' <div ng-if="showModalCarousel"  id="{{idElementUp}}modalCarousel"																'+
	' 	class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog"                                                          '+
	' 		aria-labelledby="Imagenes de {{\'Nombre nivel\'}}" aria-hidden="true" data-keyboard="false" data-backdrop="static">     '+
	'   <div class="modal-dialog modal-lg">                                                                                         '+
	'     <div class="modal-content">                                                                                               '+
	'		<div class="modal-body">                                                                                                '+
	'			<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">                                      '+
	'				 <div class="carousel-inner">                                                                                   '+
	'					<div ng-repeat="fileItem in (fileUploaderDvt.queue | filter:{ idEleFile : idElementUp})">                                                         '+
	'						<div class="carousel-item active">                                                                      '+
	'							<img class="d-block w-100" id="img-{{idElementUp+fileItem.unic}}" alt="{{fileItem.nameImg}}"        '+
	'							 ng-src="data:image/png;base64, {{fileItem.imagenVO.strBase64}}" />                                 '+
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