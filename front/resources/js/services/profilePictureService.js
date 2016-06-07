app.factory('profilePictureService', ['$http', 'userService', function($http, userService){
	return {
		getUrl: function(token) {
			baseURL = domain + 'photo?';
			return $http.get(createProfilePictureURL(userService.getId(), baseURL, token))
				.success(function(data, status, config, headers) {
					return parseProfilePictureResponse(data);
				})
				.error(function() {

				});
		}
	};
}]);

function createProfilePictureURL(userID, baseURL, token) {
	baseURL =  addParameter(baseURL, 'userId', userID);
	baseURL = addParameter(baseURL, 'accessToken', token);
	return baseURL;
}

function parseProfilePictureResponse(data) {
	return data;
}