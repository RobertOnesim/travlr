app.factory('createGroupService', ['$http', 'userService', function($http, userService){
	return {
		createGroup: function(name) {
			baseURL = domain + 'createGroup?';
			return $http.get(createGropuURL(userService.getId(), baseURL, name))
				.success(function(data, status, config, headers) {
					return parseGropuResponse(data);
				})
				.error(function() {

				});
		}
	};
}]);

function createGroupURL(userID, baseURL, name) {
	baseURL =  addParameter(baseURL, 'groupName', name);
	baseURL =  addParameter(baseURL, 'userId', userID);
	return baseURL;
}

function parseGroupResponse(data) {
	return data;
}