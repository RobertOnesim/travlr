app.factory('groupUsersService', ['$http', function($http) {
	return {
		getUsers: function(groupId) {
			var baseURL = 'http://31.5.42.203:1056/groupUsers?';
 			return $http.get(createGroupURL(groupId, baseURL))
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

function createGroupURL(groupId, url) {
	url = addParameter(url, 'groupId', groupId);
	return url;
};