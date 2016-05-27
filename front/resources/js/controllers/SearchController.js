function StepoverCity() {
	this.name = "";
}

app.controller('SearchController', ['$scope', '$routeParams', 'flightService', function($scope, $routeParams, flightService) {
	$scope.flightSearch = {
		departureCity : $routeParams.dep,
		arrivalCity : $routeParams.arr,
		departureDate : "",
		returnDate : "",
		duration : 1,
		priceMax : 100,
		type : "oneWay",
		numberAdults : 1,
		numberChildren : 0,
		numberInfants : 0,
		stepoverCities : []
	};

	$scope.returnFlight = [];
	$scope.minDate = new Date();

	$scope.searchClick = function(flight) {
		var string = '#/search/' + flight.departureCity + '/' + flight.arrivalCity;
		window.location.href = string;
	};

	flightService.success(function(data) {
		$scope.flights = data.Options;
		for (var i = 0; i < $scope.flights.length; i++) {
			var lastSegment = $scope.flights[i].Segments.length - 1;
			$scope.flights[i].departureCity = $scope.flights[i].Segments[0].OriginStation.Name;
			$scope.flights[i].arrivalCity = $scope.flights[i].Segments[lastSegment].DestinationStation.Name;
			if($scope.flights[i].Segments.length > 1) {
				$scope.flights[i].type =  "Stepover flight";
			} else {
				$scope.flights[i].type =  "Direct flight with " + $scope.flights[i].Segments[0].Carrier.Name;
			}
			if($scope.flights[i].Segments[0].InboundDate) {
				$scope.flights[i].WithReturn = true;
			} else {
				$scope.flights[i].WithReturn = false;
			}
		}
	});

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
		$scope.flightSearch.stepoverCities.push(new StepoverCity());
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
		console.log($scope.returnFlight);
	}

	$scope.oneWay = function() {
		$scope.returnFlight = [];
		console.log($scope.returnFlight);
	}
}]);