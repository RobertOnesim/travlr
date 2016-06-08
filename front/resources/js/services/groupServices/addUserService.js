app.factory('addUserService', ['$http', function($http) {
	return {
		addUser: function(userSearch) {
			var baseURL = domain + 'addUserToGroup?';
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
	console.log(userSearch);
	url = addParameter(url, 'userId', userSearch.idUser);
	url = addParameter(url, 'groupId', userSearch.idGroup);
	return url;
};