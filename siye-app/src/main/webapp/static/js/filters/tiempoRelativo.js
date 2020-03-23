angular.module(appTeclo).filter("tiempoRelativo", [ function() {
	var result = function(date) {
		if (date === null) {
			return date;
		}
		var fecha = moment(date);
		var ahora = moment();
		return humanizeDuration(ahora.diff(fecha), { language: 'es', largest: 2, unitis: ['y', 'mo', 'w', 'd', 'h', 'm']});
	}
	return result;
} ]);

angular.module(appTeclo).filter("timeMinutes", [ function() {
	var result = function(date) {
		if (date === null) {
			return date;
		}
		 var fecha = moment(date);
		 var ahora = moment();
		
		 var st = new Date(moment(fecha).valueOf()).getTime();
         var et = new Date(moment(ahora).valueOf()).getTime();
         var duration = moment.duration(moment(et).diff(moment(st)));
         var minutes = duration.asMinutes();
		
		return minutes;
	}
	return result;
} ]);