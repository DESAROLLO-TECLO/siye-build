angular.module(appTeclo).config(function($translateProvider) {
 
	$translateProvider.useStaticFilesLoader({
	    prefix: 'i18n/locale-',
	    suffix: '.json'
	});
 
	$translateProvider.useSanitizeValueStrategy('escape');
    $translateProvider.preferredLanguage('es_ES');
    $translateProvider.useCookieStorage();

});