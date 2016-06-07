app.factory('flightService', ['$http', 'userService', function($http, userService){
	return {
		getFlights: function(flightSearch, token) {
			var baseURL = domain + 'flight?';
			return $http.get(createFlightURL(flightSearch, baseURL, token, userService.getId()), getHeaderValues(userService))
				.success(function(data, status, config, headers) {
					return parseFlightResponse(data);
				})
				.error(function() {

				});
		}
	}
}]);

function parseFlightResponse(data) {
	var flights;
	var lastSegment;

	if(data) {
		flights = data.Options;
		if(flights) {
			for (var i = 0; i < flights.length; i++) {
				lastSegment = flights[i].Segments.length - 1;
				flights[i].outboundDetails = {};
				flights[i].outboundDetails.departureCity = flights[i].Segments[0].OriginStation.Name;
				flights[i].outboundDetails.arrivalCity = flights[i].Segments[lastSegment].DestinationStation.Name;
				flights[i].outboundDetails.departureDate = flights[i].Segments[0].DepartureDateTime;
				flights[i].outboundDetails.arrivalDate = flights[i].Segments[lastSegment].ArrivalDateTime;
				if(flights[i].Segments.length > 1) {
					flights[i].outboundDetails.type =  "Stepover flight";
				} else {
					flights[i].outboundDetails.type =  "Direct flight with " + flights[i].Segments[0].Carrier.Name;
				}
				if(flights[i].ReturnSegments) {
					lastSegment = flights[i].ReturnSegments.length - 1;
					flights[i].inboundDetail = {};
					flights[i].inboundDetail.departureCity = flights[i].ReturnSegments[0].OriginStation.Name;
					flights[i].inboundDetail.arrivalCity = flights[i].ReturnSegments[lastSegment].DestinationStation.Name;
					flights[i].inboundDetail.departureDate = flights[i].ReturnSegments[0].DepartureDateTime;
					flights[i].inboundDetail.arrivalDate = flights[i].ReturnSegments[lastSegment].ArrivalDateTime;
					if(flights[i].ReturnSegments.length > 1) {
						flights[i].inboundDetail.type =  "Stepover flight";
					} else {
						flights[i].inboundDetail.type =  "Direct flight with " + flights[i].ReturnSegments[0].Carrier.Name;
					}
				}
			}
			console.log(flights);
			return flights;
		}
	}
}


function createFlightURL(flightSearch, url, token, userId) {
	var date;
	url = addParameter(url, 'departure-city', flightSearch.departureCity);
	url = addParameter(url, 'arrival-city', flightSearch.arrivalCity);
	url = addParameter(url, 'departure-date', extractURLDate(flightSearch.departureDate));

	if(flightSearch.returnDate != '') {
		url = addParameter(url, 'return-date', extractURLDate(flightSearch.returnDate));
	}

	if(flightSearch.durationMax != 24) {
		url = addParameter(url, 'max-duration', flightSearch.durationMax);
	}

	if(flightSearch.priceMax != 5000) {
		url = addParameter(url, 'max-price', flightSearch.priceMax);
	}

	url = addParameter(url, 'adults', flightSearch.numberAdults);
	url = addParameter(url, 'children', flightSearch.numberChildren);
	url = addParameter(url, 'infants', flightSearch.numberInfants);
	//url = addParameter(url, 'min-stepovers', flightSearch.stepoverCities.length);

	for(var i = 0; i < flightSearch.stepoverCities.length; i++) {
		url = addParameter(url, 'stepover-city', flightSearch.stepoverCities[i].name);
	}

	if(userId != null) {
		url = addParameter(url, 'userId', userId);
	}

	if(token != null) {
		url = addParameter(url, 'accessToken', token);
	}

	return url;
}