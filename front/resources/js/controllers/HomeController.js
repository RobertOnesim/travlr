app.controller('HomeController', ['$scope', 'offerService', 'userService', 'cityImageService', function($scope, offerService, userService, cityImageService) {
	$scope.flightSearch = {
		departureCity : "",
		arrivalCity : "",
		type: "oneWay",
		departureDate : "",
		returnDate : ""
	};

	/*$scope.rawOffers = [
		{
			imgUrl: "http://www.lenas-donau.at/uploads/tx_ahsliderpic/wienkarte_02.jpg",
			departureCity: "Bucharestttttt",
			arrivalCity: "Vien",
			departureDate: "Tue%20Jun%2007%202016%2000:00:00%20GMT+0300%20(GTB%20Daylight%20Time)",
			lat: 48,
			lon: 2
		},
		{
			imgUrl: "http://mw2.google.com/mw-panoramio/photos/medium/59461095.jpg",
			departureCity: "Bucharest",
			arrivalCity: "Vien",
			departureDate: "Tue%20Jun%2007%202016%2000:00:00%20GMT+0300%20(GTB%20Daylight%20Time)",
			lat: 40,
			lon: 26
		},
		{
			imgUrl: "http://mw2.google.com/mw-panoramio/photos/medium/59461095.jpg",
			departureCity: "Bucharest",
			arrivalCity: "Vien",
			departureDate: "Tue%20Jun%2007%202016%2000:00:00%20GMT+0300%20(GTB%20Daylight%20Time)",
			lat: 48,
			lon: -74
		},
		{
			imgUrl: "http://mw2.google.com/mw-panoramio/photos/medium/59461095.jpg",
			departureCity: "Bucharest",
			arrivalCity: "Vien",
			departureDate: "Tue%20Jun%2007%202016%2000:00:00%20GMT+0300%20(GTB%20Daylight%20Time)",
			lat: 48,
			lon: 16
		},
		{
			imgUrl: "http://mw2.google.com/mw-panoramio/photos/medium/59461095.jpg",
			departureCity: "Bucharest",
			arrivalCity: "Vien",
			departureDate: "Tue%20Jun%2007%202016%2000:00:00%20GMT+0300%20(GTB%20Daylight%20Time)",
			lat: -37,
			lon: 144
		}
	];*/

	//$scope.offers = [];

	/*var i;
	for(i = 0; i < $scope.rawOffers.length; i++) {
		cityImageService.getImage($scope.rawOffers[i]).success(function(data) {
			$scope.offers.push(cityImageService.city);
			console.log(cityImageService.city);
			console.log(cityImageService.city.imgUrl);
		})
	}*/

	/*offerService.getOffers().success(function(data) {
		$scope.offers = data;
		console.log(data);
	});*/

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