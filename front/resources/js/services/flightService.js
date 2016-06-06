app.factory('flightService', ['$http', 'userService', function($http, userService){
	return {
		getFlights: function(flightSearch) {
			var baseURL = 'http://31.5.42.203:1056/flight?';
			/*var aa = {
			    "Options": [
			        {
			            "Id": "15116-1606152040-A3-1-11616-1606161015",
			            "Price": 337.83,
			            "Segments": [
			                {
			                    "ArrivalDateTime": "2016-06-15T22:10:00",
			                    "Carrier": {
			                        "ImageUrl": "http://s1.apideeplink.com/images/airlines/A3.png",
			                        "Name": "Aegean Airlines"
			                    },
			                    "DepartureDateTime": "2016-06-15T20:40:00",
			                    "DestinationStation": {
			                        "Name": "Athens International"
			                    },
			                    "Id": 1,
			                    "OriginStation": {
			                        "Name": "Bucharest Otopeni"
			                    }
			                }
			            ],
			            "ReturnSegments": [
			                {
			                    "ArrivalDateTime": "2016-06-16T10:15:00",
			                    "Carrier": {
			                        "ImageUrl": "http://s1.apideeplink.com/images/airlines/A3.png",
			                        "Name": "Aegean Airlines"
			                    },
			                    "DepartureDateTime": "2016-06-16T08:15:00",
			                    "DestinationStation": {
			                        "Name": "Frankfurt am Main"
			                    },
			                    "Id": 2,
			                    "OriginStation": {
			                        "Name": "Athens International"
			                    }
			                },
			                {
			                    "ArrivalDateTime": "2016-06-15T22:10:00",
			                    "Carrier": {
			                        "ImageUrl": "http://s1.apideeplink.com/images/airlines/A3.png",
			                        "Name": "Aegean Airlines"
			                    },
			                    "DepartureDateTime": "2016-06-15T20:40:00",
			                    "DestinationStation": {
			                        "Name": "Athens International"
			                    },
			                    "Id": 1,
			                    "OriginStation": {
			                        "Name": "Bucharest Otopeni"
			                    }
			                }
			            ]
			        },
			        {
			            "Id": "15116-1606150630-AB-1-11616-1606151005",
			            "Price": 346.89,
			            "Segments": [
			                {
			                    "ArrivalDateTime": "2016-06-15T07:35:00",
			                    "Carrier": {
			                        "ImageUrl": "http://s1.apideeplink.com/images/airlines/AB.png",
			                        "Name": "Air Berlin"
			                    },
			                    "DepartureDateTime": "2016-06-15T06:30:00",
			                    "DestinationStation": {
			                        "Name": "Berlin Tegel"
			                    },
			                    "Id": 3,
			                    "OriginStation": {
			                        "Name": "Bucharest Otopeni"
			                    }
			                },
			                {
			                    "ArrivalDateTime": "2016-06-15T10:05:00",
			                    "Carrier": {
			                        "ImageUrl": "http://s1.apideeplink.com/images/airlines/AB.png",
			                        "Name": "Air Berlin"
			                    },
			                    "DepartureDateTime": "2016-06-15T08:50:00",
			                    "DestinationStation": {
			                        "Name": "Frankfurt am Main"
			                    },
			                    "Id": 4,
			                    "OriginStation": {
			                        "Name": "Berlin Tegel"
			                    }
			                }
			            ],
			            "ReturnSegments": [
			                {
			                    "ArrivalDateTime": "2016-06-15T10:05:00",
			                    "Carrier": {
			                        "ImageUrl": "http://s1.apideeplink.com/images/airlines/AB.png",
			                        "Name": "Air Berlin"
			                    },
			                    "DepartureDateTime": "2016-06-15T08:50:00",
			                    "DestinationStation": {
			                        "Name": "Frankfurt am Main"
			                    },
			                    "Id": 4,
			                    "OriginStation": {
			                        "Name": "Berlin Tegel"
			                    }
			                }
			            ]
			        },
			        {
			            "Id": "15116-1606151415-AB-1-11616-1606151840",
			            "Price": 355.58,
			            "Segments": [
			                {
			                    "ArrivalDateTime": "2016-06-15T15:20:00",
			                    "Carrier": {
			                        "ImageUrl": "http://s1.apideeplink.com/images/airlines/AB.png",
			                        "Name": "Air Berlin"
			                    },
			                    "DepartureDateTime": "2016-06-15T14:15:00",
			                    "DestinationStation": {
			                        "Name": "Berlin Tegel"
			                    },
			                    "Id": 5,
			                    "OriginStation": {
			                        "Name": "Bucharest Otopeni"
			                    }
			                },
			                {
			                    "ArrivalDateTime": "2016-06-15T18:40:00",
			                    "Carrier": {
			                        "ImageUrl": "http://s1.apideeplink.com/images/airlines/AB.png",
			                        "Name": "Air Berlin"
			                    },
			                    "DepartureDateTime": "2016-06-15T17:25:00",
			                    "DestinationStation": {
			                        "Name": "Frankfurt am Main"
			                    },
			                    "Id": 6,
			                    "OriginStation": {
			                        "Name": "Berlin Tegel"
			                    }
			                }
			            ],
			            "ReturnSegments": [
			                {
			                    "ArrivalDateTime": "2016-06-15T18:40:00",
			                    "Carrier": {
			                        "ImageUrl": "http://s1.apideeplink.com/images/airlines/AB.png",
			                        "Name": "Air Berlin"
			                    },
			                    "DepartureDateTime": "2016-06-15T17:25:00",
			                    "DestinationStation": {
			                        "Name": "Frankfurt am Main"
			                    },
			                    "Id": 6,
			                    "OriginStation": {
			                        "Name": "Berlin Tegel"
			                    }
			                },
			                {
			                    "ArrivalDateTime": "2016-06-15T15:20:00",
			                    "Carrier": {
			                        "ImageUrl": "http://s1.apideeplink.com/images/airlines/AB.png",
			                        "Name": "Air Berlin"
			                    },
			                    "DepartureDateTime": "2016-06-15T14:15:00",
			                    "DestinationStation": {
			                        "Name": "Berlin Tegel"
			                    },
			                    "Id": 5,
			                    "OriginStation": {
			                        "Name": "Bucharest Otopeni"
			                    }
			                }
			            ]
			        },
			        {
			            "Id": "15116-1606151415-AB-1-11616-1606151840",
			            "Price": 355.58,
			            "Segments": [
			                {
			                    "ArrivalDateTime": "2016-06-15T15:20:00",
			                    "Carrier": {
			                        "ImageUrl": "http://s1.apideeplink.com/images/airlines/AB.png",
			                        "Name": "Air Berlin"
			                    },
			                    "DepartureDateTime": "2016-06-15T14:15:00",
			                    "DestinationStation": {
			                        "Name": "Berlin Tegel"
			                    },
			                    "Id": 5,
			                    "OriginStation": {
			                        "Name": "Bucharest Otopeni"
			                    }
			                },
			                {
			                    "ArrivalDateTime": "2016-06-15T18:40:00",
			                    "Carrier": {
			                        "ImageUrl": "http://s1.apideeplink.com/images/airlines/AB.png",
			                        "Name": "Air Berlin"
			                    },
			                    "DepartureDateTime": "2016-06-15T17:25:00",
			                    "DestinationStation": {
			                        "Name": "Frankfurt am Main"
			                    },
			                    "Id": 6,
			                    "OriginStation": {
			                        "Name": "Berlin Tegel"
			                    }
			                }
			            ]
			        },
			        {
			            "Id": "15116-1606151415-AB-1-11616-1606151840",
			            "Price": 355.58,
			            "Segments": [
			                {
			                    "ArrivalDateTime": "2016-06-15T15:20:00",
			                    "Carrier": {
			                        "ImageUrl": "http://s1.apideeplink.com/images/airlines/AB.png",
			                        "Name": "Air Berlin"
			                    },
			                    "DepartureDateTime": "2016-06-15T14:15:00",
			                    "DestinationStation": {
			                        "Name": "Berlin Tegel"
			                    },
			                    "Id": 5,
			                    "OriginStation": {
			                        "Name": "Bucharest Otopeni"
			                    }
			                }
			            ]
			        }
			    ]
			}*/
			return $http.get(createFlightURL(flightSearch, baseURL), getHeaderValues(userService))
				.success(function(data, status, config, headers) {
					return parseFlightResponse(data);
				})
				.error(function() {

				});
			//return parseResponse(aa);
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


function createFlightURL(flightSearch, url) {
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