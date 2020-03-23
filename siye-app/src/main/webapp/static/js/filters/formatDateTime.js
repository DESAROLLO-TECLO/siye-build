angular.module(appTeclo).filter("formatDateTime", [ function() {
	var result = function(date) {
		if (date === null) {
			return date;
		}
		return moment(date).locale("es").format("DD-MMMM-YYYY HH:mm");
	}
	return result;
} ]);