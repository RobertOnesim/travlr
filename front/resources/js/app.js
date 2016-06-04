var app = angular.module('Travlr', ['ngRoute', 'ngMaterial', 'materialCalendar']);

app.config(function($routeProvider, $mdThemingProvider) {
	$routeProvider
		.when('/', {
			controller: 'HomeController',
    		templateUrl: 'views/pages/home.html'
		})
		.when('/search/:dep/:arr/:startDate/:returnDate?/:numberAdults?/:numberChildren?/:numberInfants?/:priceMax?/:durationMax?', {
			controller: 'SearchController',
			templateUrl: 'views/pages/search.html'
		})
		.when('/group/:id', {
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

window.fbAsyncInit = function() {
        FB.init({
          appId      : '471546956374592',
          oath       : true,
          cookie     : true,
          xfbml      : true,
          version    : 'v2.6'
        });
      };

      (function(d, s, id){
         var js, fjs = d.getElementsByTagName(s)[0];
         if (d.getElementById(id)) {return;}
         js = d.createElement(s); js.id = id;
         js.src = "//connect.facebook.net/en_US/sdk.js";
         fjs.parentNode.insertBefore(js, fjs);
       }(document, 'script', 'facebook-jssdk'));