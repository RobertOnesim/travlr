function StepoverCity() {
	this.name = "";
	this.number = "";
}

app.controller('SearchController', ['$scope', '$routeParams', 'flightService', 'userService', 'cartService', function($scope, $routeParams, flightService, userService, cartService) {
	$scope.flightSearch = {
		departureCity : $routeParams.dep,
		arrivalCity : $routeParams.arr,
		departureDate : new Date($routeParams.startDate),
		returnDate : "",
		durationMax : 24,
		priceMax : 5000,
		type : "oneWay",
		numberAdults : 1,
		numberChildren : 0,
		numberInfants : 0,
		stepoverCities : []
	};
	$scope.returnFlight = [];
	$scope.minDate = new Date();

	if($routeParams.returnDate) {
		$scope.flightSearch.returnDate = new Date($routeParams.returnDate);
		$scope.flightSearch.type = "withReturn";
		$scope.returnFlight = ["yes"];
	}

	if($routeParams.numberAdults) {
		$scope.flightSearch.numberAdults = $routeParams.numberAdults;
	}
	if($routeParams.numberChildren) {
		$scope.flightSearch.numberChildren = $routeParams.numberChildren;
	}
	if($routeParams.numberInfants) {
		$scope.flightSearch.numberInfants = $routeParams.numberInfants;
	}
	if($routeParams.priceMax) {
		$scope.flightSearch.priceMax = $routeParams.priceMax;
	}
	if($routeParams.durationMax) {
		$scope.flightSearch.durationMax = $routeParams.durationMax;
	}

	if($scope.flightSearch.departureCity && $scope.flightSearch.arrivalCity && $scope.flightSearch.departureDate) {
		flightService.getFlights($scope.flightSearch).success(function(data) {
			$scope.flights = data.Options;
		});
		//$scope.flights = flightService.getFlights();
	}

	$scope.searchClick = function(flight, baseURL) {
		var string = baseURL + flight.departureCity + '/' + flight.arrivalCity + '/' + flight.departureDate;
		string += '/';
		if(flight.returnDate != '') {
			string += flight.returnDate;
		}
		string += '/';
		if(flight.numberAdults != 1) {
			string += flight.numberAdults;
		}
		string += '/';
		if(flight.numberChildren != 0) {
			string += flight.numberChildren;
		}
		string += '/';
		if(flight.numberInfants != 0) {
			string += flight.numberInfants;
		}
		string += '/';
		if(flight.priceMax != 5000) {
			string += flight.priceMax;
		}
		string += '/';
		if(flight.durationMax != 24) {
			string += flight.durationMax;
		}
		window.location.href = string;
	};
	
	$scope.range = function(start, finish, step) {
		step = step || 1;
		var array = [];
		for(var i = start; i <= finish; i += step) {
			array.push(i);
		}
		return array;
	}

	$scope.degrees = 0;

	$scope.addStepoverCity = function() {
		var city = new StepoverCity();
		var count = $scope.flightSearch.stepoverCities.length;
		city.number = count;
		$scope.flightSearch.stepoverCities.push(city);
	}

	$scope.removeStepoverCity = function(index) {
		for(var i = 0; i < $scope.flightSearch.stepoverCities.length; i++) {
			if($scope.flightSearch.stepoverCities[i].number == index) {
				$scope.flightSearch.stepoverCities.splice(i, 1);
				angular.element(document.querySelector('city' + index)).html('');
			}
		}
	}

	$scope.rotate = function() {
		var img = angular.element(document.querySelector('#filtersDropDown'));
		$scope.degrees += 180;

		img.css('-ms-transform', 'rotate(' + $scope.degrees + 'deg)');
        img.css('-webkit-transform', 'rotate(' + $scope.degrees + 'deg)');
        img.css('transform', 'rotate(' + $scope.degrees + 'deg)');
	}

	$scope.withReturn = function() {
		$scope.returnFlight.push("yes");
	}

	$scope.oneWay = function() {
		$scope.returnFlight = [];
	}

	$scope.addToCart = function(flight) {
		flight.numberAdults = $scope.flightSearch.numberAdults;
		flight.numberChildren = $scope.flightSearch.numberChildren;
		flight.numberInfants = $scope.flightSearch.numberInfants;
		cartService.addToCart(flight);
		$scope.$parent.refreshCart();
	}
}]);