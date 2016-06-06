app.controller('HomeController', ['$scope', function($scope) {
	$scope.flightSearch = {
		departureCity : "",
		arrivalCity : "",
		type: "oneWay",
		departureDate : "",
		returnDate : ""
	};

	$scope.offers = [
		{
			imgURL: "https://thecherrylook.files.wordpress.com/2013/11/53a30-20_full1.jpg",
			text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.Aenan convallis.",
			cityName: "Iasi"
		},
		{
			imgURL: "https://thecherrylook.files.wordpress.com/2013/11/53a30-20_full1.jpg",
			text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.Aenan convallis.",
			cityName: "Bucuresti"
		},
		{
			imgURL: "https://thecherrylook.files.wordpress.com/2013/11/53a30-20_full1.jpg",
			text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.Aenan convallis.",
			cityName: "Iasi"
		},
		{
			imgURL: "https://thecherrylook.files.wordpress.com/2013/11/53a30-20_full1.jpg",
			text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.Aenan convallis.",
			cityName: "Bucuresti"
		},
		{
			imgURL: "https://thecherrylook.files.wordpress.com/2013/11/53a30-20_full1.jpg",
			text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.Aenan convallis.",
			cityName: "Iasi"
		}
	];

	$scope.returnFlight = [];
	$scope.minDate = new Date();

	$scope.searchClick = function(flight) {
		var string = '#/search/' + flight.departureCity + '/' + flight.arrivalCity + '/' + flight.departureDate;
		if(flight.returnDate != '') {
			string += '/' + flight.returnDate;
		}
		window.location.href = string;
	};

	$scope.withReturn = function() {
		$scope.returnFlight.push("yes");
	}

	$scope.oneWay = function() {
		$scope.returnFlight = [];
	}
}]);