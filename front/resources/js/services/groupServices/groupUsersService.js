app.factory('groupUsersService', ['$http', function($http) {
	return {
		getUsers: function(groupId) {
			var baseURL = domain + 'groupUsers?';
 			return $http.get(createGroupURL(groupId, baseURL))
 				.success(function(data) {
 				return parseResponseFind(data);
        	})
        		.error(function(err) {
        		return err; 
        	}); 
        }
    }    		
}]);

function createGroupURL(groupId, url) {
	url = addParameter(url, 'groupId', groupId);
	return url;
};