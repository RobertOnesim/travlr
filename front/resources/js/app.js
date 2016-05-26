var app = angular.module('Travlr', ['ngRoute']);

app.config(function($routeProvider) {
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

	//$locationProvider.html5Mode(true);
});
