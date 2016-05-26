app.controller('SearchController', ['$scope', '$routeParams', 'flightService', function($scope, $routeParams, flightService) {
	$scope.flight = {
		departureCity : "",
		arrivalCity : "",
		departureDate : "",
		arrivalDate : ""
	}
	$scope.flight.departureCity = $routeParams.dep;
	$scope.flight.arrivalCity = $routeParams.arr;
	$scope.searchClick = function(flight) {
		var string = '#/search/' + flight.departureCity + '/' + flight.arrivalCity;
		window.location.href = string;
	};

	flightService.success(function(data) {
		console.log(data);
		$scope.flights = data.Options;
	});

	/*$scope.flights = [
		{
			type: 'WithReturn',
			name: 'aa'
		},
		{
			type: 'OneWay',
			name: 'bb'
		},
		{
			type: 'WithReturn',
			name: 'ma-ta'
		},
		{
			type: 'OneWay',
			name: 'ma-ta'
		}
	];*/
}]);