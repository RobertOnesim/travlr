var app = angular.module('Travlr', ['ngRoute']);

app.config(function($routeProvider) {
	$routeProvider
		.when('/', {
			controller: 'HomeController',
    		templateUrl: 'views/home.html'
		})
		.when('/flights/:id', {
			controller: 'FlightController',
			templateUrl: 'views/flight.html'
		})
		.otherwise({
			redirectTo: '/'
		});

	//$locationProvider.html5Mode(true);
});
