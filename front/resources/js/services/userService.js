app.factory('userService', ['$http', function($http){
	return {
		login: function(network) {
			var user = getUserFronStorage();
			user.network = network;
			saveUserToStorage(user);
		},
		logout: function() {
			localStorage.removeItem('user');
		},
		isLoggedin: function() {
			if(localStorage.user) {
				return true;
			}
			return false;
		},
		getGroups: function(userID) {
			baseURL = 'http://31.5.42.203:1056/userGroups?'
			return $http.get(createGroupsOfUserURL(userID, baseURL), getHeaderValues(this))
				.success(function(data, status, config, headers) {
					return parseGroupsOfUserResponse(data);
				})
				.error(function() {

				});
		}
	};
}]);

function createGroupsOfUserURL(userID, url) {
	url = addParameter(url, 'userId', userID);
	return url;
}

function parseGroupsOfUserResponse(data) {
	return data;
}