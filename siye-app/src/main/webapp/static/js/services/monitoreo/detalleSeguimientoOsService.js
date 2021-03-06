angular.module(appTeclo).service("detalleSeguimientoOsService", function($http, config) {

    const END_POINT=config.baseUrl+"/monitoreo";
    
    const GET_DETALLE_OS=END_POINT+"/getProcesos";
    
    let datosConsultaOS = null;

    this.saveconsultaGeneral=function(parametros){
        datosConsultaOS = parametros;
	};

	this.getconsultaGeneral = function(){
		return datosConsultaOS;
	}
	
	this.getProcesosByOrdenServicio= function(){
		if(datosConsultaOS != null){
			if(datosConsultaOS.dtOs!=null || datosConsultaOS.dtOs!= undefined){
				return $http.get(GET_DETALLE_OS,{
					params:{"idOrden": datosConsultaOS.dtOs.idOrdenServicio}
				});
			}
		}
	};

	this.getDetalleProcesoEspecifico = function(idOrdenServicio, procesos){
		return $http.get(END_POINT+"/getDetalleProceso",{
			params:{"idOrden": idOrdenServicio,
					"idProceso":procesos}
		});
	};

	this.getImagenBynivel=function(params){
		return $http.get(END_POINT+"/getImgSeguimiento",{
			params:{"idOrdenServicio": params.idOrdenServicio,
					"valor":params.valor,
				    "nivel":params.nivel,
					"clase":params.clase
				}
		});
	};

	this.getSeguimientoImagen = function(params){
		return $http.get(END_POINT+"/getDetalleImgOS",{
			params:{"idOrdenServicio": params.idOrdenServicio,
					"valor":params.valor,
				    "nivel":params.nivel,
					"clase":params.clase
				}
		});
	}
    
});