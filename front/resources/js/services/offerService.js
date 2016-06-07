app.factory('offerService', ['$http', 'userService', 'cityImageService', function($http, userService, cityImageService){
	return {
		getOffers: function(token) {
			baseURL = domain + 'offers?';
			return $http.get(createOffersURL(userService.getId(), baseURL, token))
				.success(function(data, status, config, headers) {
					console.log(data);
					var parseData =  parseOffersResponse(data);
					var i;
					var finalData = [];
					console.log(data);
					for(i = 0; i < parseData.length; i++) {

						parseData[i].departureDate = new Date(parseData[i].date);
						console.log(data[i]);
						cityImageService.getImage(parseData[i]).success(function(data) {
							finalData.push(data);
						});
					}
					return finalData;
				})
				.error(function() {

				});
		}
	};
}]);

function createOffersURL(userID, baseURL, token) {
	if(userID) {
		baseURL =  addParameter(baseURL, 'userId', userID);
	}
	if(token != null) {
		baseURL = addParameter(baseURL, 'accessToken', token);
	}
	return baseURL;
}

function parseOffersResponse(data) {
	return data;
}