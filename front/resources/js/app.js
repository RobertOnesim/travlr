var app = angular.module('Travlr', ['ngRoute', 'ngMaterial', 'ngCookies']);

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
		.when('/search/:dep/:arr/:startDate/:returnDate?/:numberAdults?/:numberChildren?/:numberInfants?/:priceMax?/:durationMax?', {
			controller: 'SearchController',
			templateUrl: 'views/pages/search.html'
		})
		.when('/group', {
			controller: 'GroupController',
			templateUrl: 'views/pages/group.html'
		})
		.when('/cart', {
			controller: 'CartController',
			templateUrl: 'views/pages/cart.html'
		})
		.otherwise({
			redirectTo: '/'
		});

	$mdThemingProvider.theme('default')
		.primaryPalette('amber')
		.accentPalette('blue');
});
