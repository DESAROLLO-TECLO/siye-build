angular.module(appTeclo).factory('msjModalFactory',[function($translate){
	
	var listaMensajes = [];
	
//	MENSAJES GENÉRICOS
	listaMensajes.push({mensajeEs: 'La función de actualizar o recargar la página está deshabilitada en el modo pantalla completa',  mensajeEn: 'The function of updating or reloading the page is disabled in full screen mode'});
	listaMensajes.push({mensajeEs: 'Registro guardado correctamente',  mensajeEn: 'Registration saved correctly'});
	listaMensajes.push({mensajeEs: 'Formulario incompleto',  mensajeEn: 'Incomplete application form'});
	listaMensajes.push({mensajeEs: 'La información se registró correctamente',  mensajeEn: 'The information was registered correctly'});
	listaMensajes.push({mensajeEs: 'El registro se guardó correctamente',  mensajeEn: 'The record was saved correctly'});
	listaMensajes.push({mensajeEs: 'Ocurrio un error al registrar',  mensajeEn: 'There was an error when registering'});
	listaMensajes.push({mensajeEs: 'No se encontraron registros',  mensajeEn: 'No records found'});
	listaMensajes.push({mensajeEs: '¡ATENCIÓN!',  mensajeEn: 'ATTENTION!'});
	listaMensajes.push({mensajeEs: 'La función de actualizar o recargar la página está deshabilitada en el modo pantalla completa',  mensajeEn: 'The function of updating or reloading the page is disabled in full screen mode'});
	
//	MENSAJES DEL NEGOCIO
	
//	Administración
	
	//Componentes Web
	listaMensajes.push({mensajeEs: 'Título del detalle de información (Opción 1)',  mensajeEn: 'Title of popover'});
	listaMensajes.push({mensajeEs: 'Título del detalle de información (Opción 2)',  mensajeEn: 'Title of webui-popover'});
	listaMensajes.push({mensajeEs: 'Contenido del detalle de información de bootstrap',  mensajeEn: 'Content popover bootstrap'});
	listaMensajes.push({mensajeEs: '¡Éxito!',  mensajeEn: 'Success!'});
	listaMensajes.push({mensajeEs: 'Notificación de éxito',  mensajeEn: 'Success notification'});
	listaMensajes.push({mensajeEs: 'Notificación de advertencia',  mensajeEn: 'Warning notification'});
	listaMensajes.push({mensajeEs: 'Notificación de error',  mensajeEn: 'Error notification'});
	listaMensajes.push({mensajeEs: 'Notificación de información',  mensajeEn: 'Information notification'});
	listaMensajes.push({mensajeEs: 'INFORMACIÓN',  mensajeEn: 'INFORMATION'});
	listaMensajes.push({mensajeEs: 'Notificación con 2 segundos de retraso',  mensajeEn: 'Notification with 2 seconds delay'});
	
	//Configurar Aplicación
	listaMensajes.push({mensajeEs: 'La imagen seleccionada no tiene formato PNG',  mensajeEn: 'The selected image does not have PNG format'});
	listaMensajes.push({mensajeEs: 'La imagen seleccionada no tiene formato PNG o JPG|JPEG',  mensajeEn: 'The selected image does not have PNG o JPG|JPEG format'});
	listaMensajes.push({mensajeEs: 'Configuración actualizada correctamente',  mensajeEn: 'Configuration updated correctly'});
	listaMensajes.push({mensajeEs: 'Ocurrió un error al actualizar la configuración',  mensajeEn: 'There was an error updating the configuration'});
	listaMensajes.push({mensajeEs: 'Recomendaciones',  mensajeEn: 'Recomendations'});
	
	var mensaje = {

        getMensaje: function(msj, lang) {
        	
        	var item = "";
        	
        	angular.forEach(listaMensajes, function(mensajes, key) {
        		
        		if(msj == mensajes.mensajeEs && lang == "es_ES") {
        			item = mensajes.mensajeEs;
        		} else if (msj == mensajes.mensajeEs && lang == "en_US") {
        			item = mensajes.mensajeEn;
        		}
        	});
        	
        	return item;
        }
    }
    return mensaje;
}]);