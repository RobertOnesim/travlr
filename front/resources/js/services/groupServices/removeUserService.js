app.factory('removeUserService', ['$http', function($http) {
	return {
		addUser: function(userSearch) {
			var baseURL = 'http://31.5.42.203:1056/removeUserFromGroup?';
 			return $http.get(createAddURL(userSearch, baseURL))
 				.success(function(data) {
 				return data;
        	})
        		.error(function(err) {
        		return err; 
        	}); 
        }
    }    		
}]);


function createAddURL(userSearch, url) {
	url = addParameter(url, 'userId', userSearch.idUser);
	url = addParameter(url, 'groupId', userSearch.idGroup);
	return url;
};