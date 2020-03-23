const APP = angular.module(appTeclo);

APP.filter('groupBy', function($parse) {
	return _.memoize(function(items, filtro) {
		var getter = $parse(filtro);
		return _.groupBy(items, function(item) {
			return getter(item);
		});
	});
});

APP.filter('cortarTexto', function() {
	return function(input, limit) {
 		return (input.length > limit) ? input.substr(0, limit): input;
	};

});
APP.filter('tel', function () {
	   return function (tel) {

	if (!tel) { return ''; }

	       let value = tel.toString().trim().replace(/^\+/, '');

	       if (value.match(/[^0-9]/)) {
	           return tel;
	       }

	       let country, city, number;

	       switch (value.length) {
	           case 10: // +1PPP####### -> C (PPP) ###-####
	               country = 1;
	               city = value.slice(0, 3);
	               number = value.slice(3);
	               break;

	           case 11: // +CPPP####### -> CCC (PP) ###-####
	               country = value[0];
	               city = value.slice(1, 4);
	               number = value.slice(4);
	               break;

	           case 12: // +CCCPP####### -> CCC (PP) ###-####
	               country = value.slice(0, 3);
	               city = value.slice(3, 5);
	               number = value.slice(5);
	               break;

	           default:
	               return tel;
	       }

	       if (country == 1) {
	           country = "";
	       }

	number = `${number.slice(0,3)}-${number.slice(3)}`;

	return (`${country} (${city}) ${number}`).trim();
	   };
	});