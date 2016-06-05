app.factory('loginService', ['$http', 'userService', 'tokenService', function($http, userService, tokenService){
	return {
		loginServer: function() {
			baseURL = 'http://31.5.42.203:1056/login?';
			return $http.get(createLoginURL(userService.getId(), baseURL), getHeaderValues(userService, tokenService))
				.success(function(data, status, config, headers) {
					return parseLoginResponse(data);
				})
				.error(function() {

				});
		}
	};
}]);

function createLoginURL(userID, baseURL) {
	return addParameter(baseURL, 'idUser', userID);
}

function parseLoginResponse(data) {
	return data;
}