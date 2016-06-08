app.factory('createGroupService', ['$http', 'userService', function($http, userService){
	return {
		createGroup: function(name) {
			baseURL = domain + 'createGroup?';
			return $http.get(createGroupURL(userService.getId(), baseURL, name))
				.success(function(data, status, config, headers) {
					return parseGropuResponse(data);
				})
				.error(function() {

				});
		}
	};
}]);

function createGroupURL(userID, baseURL, name) {
	console.log(userID);
	baseURL =  addParameter(baseURL, 'groupName', name);
	baseURL =  addParameter(baseURL, 'userId', userID);
	return baseURL;
}

function parseGroupResponse(data) {
	return data;
}