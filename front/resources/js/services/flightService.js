app.factory('flightService', ['$http', function($http){
	return {
		getFlights: function(flightSearch) {
			var baseURL = 'http://95.76.234.124:1035/flight?';
			return $http.get(createURL(flightSearch, baseURL), getHeaderValues())
				.success(function(data, status, config, headers) {
					return parseResponse(data);
				})
				.error(function() {

				});
		}
	}
}]);

function parseResponse(data) {
	var flights = data.Options;
	var lastSegment;
	for (var i = 0; i < flights.length; i++) {
		lastSegment = flights[i].Segments.length - 1;
		flights[i].departureCity = flights[i].Segments[0].OriginStation.Name;
		flights[i].arrivalCity = flights[i].Segments[lastSegment].DestinationStation.Name;
		if(flights[i].Segments.length > 1) {
			flights[i].type =  "Stepover flight";
		} else {
			flights[i].type =  "Direct flight with " + flights[i].Segments[0].Carrier.Name;
		}
		if(flights[i].Segments[0].InboundDate) {
			flights[i].withReturn = true;
		} else {
			flights[i].withReturn = false;
		}
	}
	return flights;
}

// function getHeaderValues(userService) {
// 	var values = {
// 			headers: {
// 				'responseType': 'application/json'
// 			}
// 		};
// 	if(userService.getToken() != '') {
// 		values.headers.userToken = userService.getToken();
// 	}
// 	return values;
// }

function getHeaderValues() {
	var values = {
			headers: {
				'responseType': 'application/json'
			}
		};
	return values;
}

function addParameter(url, paramName, paramValue) {
	if(url.substr(url.length - 1) != '?') {
		url += '&';
	}
	url += paramName + '=' + paramValue;
	return url;
}

function createURL(flightSearch, url) {
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

	return url;
}

function extractURLDate(rawDate) {
	var date = '', number;
	date += rawDate.getUTCFullYear() + '-';

	number = rawDate.getUTCMonth() + 1;
	if(number <= 9) {
		date += '0' + number + '-';
	} else {
		date += number + '-';
	}
	
	number = rawDate.getUTCDate() + 1;
	if(number <= 9) {
		date += '0' + number;
	} else {
		date += number;
	}

	return date;
}