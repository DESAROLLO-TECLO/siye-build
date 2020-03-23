angular.module(appTeclo).directive('crudConcesionaria', function(){
        return {
        	templateUrl : "views/catalogos/crudConcesionaria.html"
        }        
 })
 
 angular.module(appTeclo).directive('crudStatus', function(){
        return {
        	templateUrl : "views/catalogos/crudStatus.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudGruas', function(){
        return {
        	templateUrl : "views/catalogos/crudGruas.html"
        }        
 })

  angular.module(appTeclo).directive('crudTurnos', function(){
        return {
        	templateUrl : "views/catalogos/crudTurno.html",
        	link: function(scope, element, attrs) {
        		$("#timepickerInicio").timepicker({
    				showInputs : false,
    				timeFormat: 'h:mm:ss',
    				showMeridian:false,
    				defaultTime: false
    			});
        		$("#timepickerFin").timepicker({
    				showInputs : false,
    				timeFormat: 'h:mm:ss',
    				showMeridian:false,
    				defaultTime: false
    			});
        		
        		$('#datepickerInicio').datepicker({
    				autoclose : true,
    				dateFormat : 'dd/MM/yyyy',
    				todayHighlight : true
    			}).on('changeDate', function (selected) {
    	        	var minDate = new Date(selected.date.valueOf());
    	        	$('#datepickerFin').datepicker('setStartDate', minDate);
    	    	});
        		
        		$('#datepickerFin').datepicker({
    				autoclose : true,
    				dateFormat : 'dd/MM/yyyy',
    				todayHighlight : true
    			}).on('changeDate', function (selected) {
    	        	var minDate = new Date(selected.date.valueOf());
    	        	$('#datepickerInicio').datepicker('setEndDate', minDate);
    	    	});
        	}        	
        }        
 })
 
  angular.module(appTeclo).directive('crudZonaServicio', function(){
        return {
        	templateUrl : "views/catalogos/crudZonaServicio.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudTipoEmpleado', function(){
        return {
        	templateUrl : "views/catalogos/crudTipoEmpleado.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudTipoAlarma', function(){
        return {
        	templateUrl : "views/catalogos/crudTipoAlarma.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudEstado', function(){
        return {
        	templateUrl : "views/catalogos/crudEstado.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudRegion', function(){
        return {
        	templateUrl : "views/catalogos/crudRegion.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudDelegacion', function(){
        return {
        	templateUrl : "views/catalogos/crudDelegacion.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudUnidadTerritorial', function(){
        return {
        	templateUrl : "views/catalogos/crudUnidadTerritorial.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudSector', function(){
        return {
        	templateUrl : "views/catalogos/crudSector.html"
        }        
 })
 
 angular.module(appTeclo).directive('crudAgrupamiento', function(){
        return {
        	templateUrl : "views/catalogos/crudAgrupamiento.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudZona', function(){
        return {
        	templateUrl : "views/catalogos/crudZona.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudRegionDeposito', function(){
        return {
        	templateUrl : "views/catalogos/crudRegionDeposito.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudDeposito', function(){
        return {
        	templateUrl : "views/catalogos/crudDeposito.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudMarcaVehiculo', function(){
        return {
        	templateUrl : "views/catalogos/crudMarcaVehiculo.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudModeloVehiculo', function(){
        return {
        	templateUrl : "views/catalogos/crudModeloVehiculo.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudSubTipoVehiculo', function(){
        return {
        	templateUrl : "views/catalogos/crudSubTipoVehiculo.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudTipoVehiculo', function(){
        return {
        	templateUrl : "views/catalogos/crudTipoVehiculo.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudColorVehiculo', function(){
        return {
        	templateUrl : "views/catalogos/crudColorVehiculo.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudResponsableVehiculo', function(){
        return {
        	templateUrl : "views/catalogos/crudResponsableVehiculo.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudTipoLicencia', function(){
        return {
        	templateUrl : "views/catalogos/crudTipoLicencia.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudEvento', function(){
        return {
        	templateUrl : "views/catalogos/crudEvento.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudStatusInfracion', function(){
        return {
        	templateUrl : "views/catalogos/crudStatusInfracion.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudDenominacion', function(){
        return {
        	templateUrl : "views/catalogos/crudDenominacion.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudConcepto', function(){
        return {
        	templateUrl : "views/catalogos/crudConcepto.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudEntidad', function(){
        return {
        	templateUrl : "views/catalogos/crudEntidad.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudBanco', function(){
        return {
        	templateUrl : "views/catalogos/crudBanco.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudTipoIngreso', function(){
        return {
        	templateUrl : "views/catalogos/crudTipoIngreso.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudCausaIngreso', function(){
        return {
        	templateUrl : "views/catalogos/crudCausaIngreso.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudArea', function(){
        return {
        	templateUrl : "views/catalogos/crudArea.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudComponente', function(){
        return {
        	templateUrl : "views/catalogos/crudComponente.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudArticulo', function(){
        return {
        	templateUrl : "views/catalogos/crudArticulo.html",
        	controller: "articuloController"
        }        
 })
 
  angular.module(appTeclo).directive('crudPrograma', function(){
        return {
        	templateUrl : "views/catalogos/crudPrograma.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudCategoria', function(){
        return {
        	templateUrl : "views/catalogos/crudCategoria.html"
        }        
 })
 
  angular.module(appTeclo).directive('crudCausales', function(){
        return {
        	templateUrl : "views/catalogos/crudCausales.html"
        }        
 })
 
 