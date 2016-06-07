app.factory('groupUsersService', ['$http', function($http) {
	return {
		getUsers: function(groupId) {
			var baseURL = domain + 'groupUsers?';
 			return $http.get(createGroupUsersURL(groupId, baseURL))
 				.success(function(data) {
                    console.log(data);
 				return parseResponseFind(data);
        	})
        		.error(function(err) {
        		return err; 
        	}); 
        }
    }    		
}]);

function createGroupUsersURL(groupId, url) {
	url = addParameter(url, 'groupId', groupId);
	return url;
};