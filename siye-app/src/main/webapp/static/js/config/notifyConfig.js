angular.module(appTeclo).config(['growlProvider', function(growlProvider) {
	
//	Tiempo de vida de la notificación: aplicado a todos los tipos de notificación
	growlProvider.globalTimeToLive(1500);
//	Tiempo de vida de la notificación: aplicado a cada tipo de notificación
//	growlProvider.globalTimeToLive({success: 3000, warning: 3000, error: 3000, info: 3000});
//	Activar/desactivaro una sola notificación por tipo
	growlProvider.onlyUniqueMessages(true);
//	Mostrar arriba/abajo notificación entrante
	growlProvider.globalReversedOrder(true);
//	Mostrar/Ocultar el contador de las notificaciones
	growlProvider.globalDisableCountDown(true);
//	Activar/Desactivar íconos
	growlProvider.globalDisableIcons(false);
//	Activar/Desactivar botón de cerrar
	growlProvider.globalDisableCloseButton(false);
//	Posición de la notificación 'top-left | top-right | bottom-left | bottom-right | top-center | bottom-center'
	growlProvider.globalPosition('top-center');
	
}]);