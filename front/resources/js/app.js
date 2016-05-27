var app = angular.module('Travlr', ['ngRoute', 'ngMaterial']);

app.config(function($routeProvider, $mdThemingProvider) {
	$routeProvider
		.when('/', {
			controller: 'HomeController',
    		templateUrl: 'views/pages/home.html'
		})
		.when('/flights/:id', {
			controller: 'FlightController',
			templateUrl: 'views/pages/flight.html'
		})
		.when('/search/:dep/:arr', {
			controller: 'SearchController',
			templateUrl: 'views/pages/search.html'
		})
		.otherwise({
			redirectTo: '/'
		});

	$mdThemingProvider.theme('default')
		.primaryPalette('amber')
		.accentPalette('blue');
	//$locationProvider.html5Mode(true);
});
