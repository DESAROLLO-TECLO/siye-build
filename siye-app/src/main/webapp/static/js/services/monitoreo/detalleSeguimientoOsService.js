angular.module(appTeclo).service("detalleSeguimientoOsService", function($http, config) {

    const END_POINT="/detalleOS";
    let datosConsultaOS = new Object({});

    this.saveconsultaGeneral=function(parametros){
        datosConsultaOS = parametros;
    }
});