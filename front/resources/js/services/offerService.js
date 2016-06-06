app.factory('offerService', ['$http', 'userService', 'cityImageService', function($http, userService, cityImageService){
	return {
		getOffers: function(token) {
			baseURL = 'http://31.5.42.203:1056/offers?';
			return $http.get(createOffersURL(userService.getId(), baseURL, token))
				.success(function(data, status, config, headers) {
					var parseData =  parseOffersResponse(data);
					var i;
					var finalData = [];
					for(i = 0; i < parseData.length; i++) {
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
	baseURL =  addParameter(baseURL, 'userId', userID);
	if(token != null) {
		baseURL = addParameter(baseURL, 'accessToken', token);
	}
	return baseURL;
}

function parseOffersResponse(data) {
	return data;
}