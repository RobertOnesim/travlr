app.factory('loginService', ['$http', 'userService', 'tokenService', function($http, userService, tokenService){
	return {
		loginServer: function(token) {
			baseURL = 'http://31.5.42.203:1056/login?';
			return $http.get(createLoginURL(userService.getId(), baseURL, token), getHeaderValues(userService, tokenService))
				.success(function(data, status, config, headers) {
					return parseLoginResponse(data);
				})
				.error(function() {

				});
		}
	};
}]);

function createLoginURL(userID, baseURL, token) {
	baseURL =  addParameter(baseURL, 'userId', userID);
	baseURL = addParameter(baseURL, 'accessToken', token);
	return baseURL;
}

function parseLoginResponse(data) {
	return data;
}