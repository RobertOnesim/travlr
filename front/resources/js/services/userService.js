app.factory('userService', ['$http', 'tokenService', function($http, tokenService){
	return {
		login: function(network, id, name) {
			var user = getUserFronStorage();
			user.network = network;
			user.id = id;
			user.name = name;
			saveUserToStorage(user);
		},
		logout: function() {
			localStorage.removeItem('user');
		},
		getId: function() {
			var user = getUserFronStorage();
			console.log(user);
			return user.id;
		},
		getName() {
			var user = getUserFronStorage();
			return user.name;
		},
		isLoggedin: function() {
			if(localStorage.user) {
				return true;
			}
			return false;
		},
		getNetwork: function() {
			var user = getUserFronStorage();
			return user.network;
		},
		getGroups: function(token) {
			baseURL = domain + 'userGroups?';
			var user = getUserFronStorage();
			return $http.get(createGroupsOfUserURL(user.id, baseURL, token))
				.success(function(data, status, config, headers) {
					return parseGroupsOfUserResponse(data);
				})
				.error(function() {

				});
		},
		getUserDetails: function(token) {
			baseURL = domain + 'user?';
			var user = getUserFronStorage();
			return $http.get(createUserDetailsURL(user.id, baseURL, token))
				.success(function(data, status, config, headers) {
					return parseUserDetailsResponse(data);
				})
				.error(function() {

				})
		}
	};
}]);

function createGroupsOfUserURL(userID, url, token) {
	url = addParameter(url, 'userId', userID);
	return url;
}

function parseGroupsOfUserResponse(data) {
	return data;
}

function createUserDetailsURL(userID, url, token) {
	url = addParameter(url, 'userId', userID);
	return url;
}

function parseUserDetailsResponse(data) {
	return data;
}