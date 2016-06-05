app.factory('offerService', ['$http', 'userService', function($http, userService){
	return {
		getOffers: function() {
			baseURL = 'http://31.5.42.203:1056/offers/';
			return $http.get(createOffersURL(userService.getId(), baseURL))
				.success(function(data, status, config, headers) {
					return parseOffersResponse(data);
				})
				.error(function() {

				});
		}
	};
}]);

function createOffersURL(userID, baseURL) {
	return baseURL + '/' + userID;
}

function parseOffersResponse(data) {
	return data;
}