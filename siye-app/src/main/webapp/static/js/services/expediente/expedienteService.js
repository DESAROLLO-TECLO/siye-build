angular.module(appTeclo).service("expedienteService",
function($http, config) {
	
	const ENDPOINT=config.baseUrl+"/expediente";
	
	const GET_INFO_OS=ENDPOINT+"/getInfoOS";
	const SAVE_UPDATE=ENDPOINT+"/saveEvidencia";
	const GET_CAT_TP_ARCHIVO=ENDPOINT+"/getTipoExpediente";
	const DELETE=ENDPOINT+"/delEvidencia";
	const GET_INFO_EXP_NIVEL=ENDPOINT+"/getInfoExpByNivel";
	const GET_CONF_COMPRESS_IMG=ENDPOINT+"/getConfigCompressImg";
	
	this.getInfoOs = function(tipoBusqueda,valor){
		return $http.get(GET_INFO_OS, {
				params:{"tipoBusqueda": tipoBusqueda,
						"valor": valor}
		});
	};
	
	this.getCatTpDocumento=function(){
		return $http.get(GET_CAT_TP_ARCHIVO);
	}
	
	this.saveOrUpdateExpediente = function(listExpedinetes) {
        return $http.post(SAVE_UPDATE,listExpedinetes);
    };
    
    this.saveExpediente = function(listExpedinetes) {
        return $http.post(SAVE);
    };
    
    this.updateExpediente = function(listExpedinetes) {
        return $http.put(UPDATE,listExpedinetes);
    };
    
    this.deleteExpediente = function(listExpedinetes) {
        return $http.post(DELETE,listExpedinetes);
    };
	
    this.getInfoOsNivel = function(nuOrdenServicio,cdNivel,idValor){
   	 return $http.get(GET_INFO_EXP_NIVEL,{
				params:{"nuOrdenServicio": nuOrdenServicio,
						 "cdNivel": cdNivel,
						 "idValor": idValor}
		});
   };
   
   this.getConfigCompressImg=function(){
		return $http.get(GET_CONF_COMPRESS_IMG);
	}
   
   //metodo para pasar parametros a expedienteRedirectController
   var paramsCof=null;
   this.getParams = function(){
   	return paramsCof;
   };
   
   this.setParams = function(params){
   	paramsCof=params;
   };
   
   this.getInfoDetalleOSNivel = function(tipoBusqueda,valor){
		return $http.get(ENDPOINT + "/getInfoOSNivel", {
				params:{"tipoBusqueda": tipoBusqueda,
						"valor": valor}
		});
	};
});