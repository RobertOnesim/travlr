app.controller('HomeController', ['$scope', 'offerService', 'userService', 'cityImageService', function($scope, offerService, userService, cityImageService) {
	$scope.flightSearch = {
		departureCity : "",
		arrivalCity : "",
		type: "oneWay",
		departureDate : "",
		returnDate : ""
	};

	if(userService.isLoggedin()) {
		FB.getLoginStatus(function(response) {
			console.log(response);
			offerService.getOffers(response.authResponse.accessToken).success(function(data) {
				$scope.offers = data;
			});
		});
	} else {
		offerService.getOffers(null).success(function(data) {
			console.log(data);
			$scope.offers = data;
		});
	}

	$scope.returnFlight = [];
	$scope.minDate = new Date();

	$scope.searchClick = function(flight) {
		var string = '#/search/' + flight.departureCity + '/' + flight.arrivalCity + '/' + flight.departureDate;
		if(flight.returnDate != '' && flight.returnDate) {
			string += '/' + flight.returnDate;
		}
		console.log(string);
		window.location.href = string;
	};

	$scope.withReturn = function() {
		$scope.returnFlight.push("yes");
	}

	$scope.oneWay = function() {
		$scope.returnFlight = [];
	}
}]);