app.controller('HomeController', ['$scope', function($scope) {
	$scope.searchClick = function(flight) {
		var string = '#/search/' + flight.departureCity + '/' + flight.arrivalCity;
		window.location.href = string;
	};
}]);