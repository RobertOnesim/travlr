function StepoverCity() {
	this.name = "";
	this.number = "";
}

app.controller('SearchController', ['$scope', '$routeParams', 'flightService', function($scope, $routeParams, flightService) {
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

	if($scope.flightSearch.departureCity && $scope.flightSearch.arrivalCity && $scope.flightSearch.departureDate) {
		flightService.getFlights($scope.flightSearch).success(function(data) {
			$scope.flights = data.Options;
		});
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
		window.location.href = string;
	};

	//flightService.success(searchFlight);
	/*flightService.getFlights($scope.flightSearch).success(function(data) {
		$scope.flights = data;
	});*/
	
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
		var img = angular.element(document.querySelector('#menuDropDown'));
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
}]);