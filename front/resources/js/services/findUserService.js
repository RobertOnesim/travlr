app.factory('findUserService', ['$http', function($http) {
	return {
		getUsers: function(userSearch) {
			var baseURL = 'http://31.5.42.203:1056/users?';
 			return $http.get(createFindURL(userSearch, baseURL))
 				.success(function(data) {
 				return parseResponseFind(data);
        	})
        		.error(function(err) {
        		return err; 
        	}); 
        }
    }    		
}]);

function parseResponseFind(data) {
	var users = [];

	if(data) {
		users=data;
		for (var i = 0; i < data.length; i++) {
			users[i].name = (data[i].lastName.concat(" ")).concat(data[i].firstName);
			users[i].id = data[i].id;	
		}
		return users;
	}
}

function createFindURL(userSearch, url) {
	url = addParameter(url, 'userName', userSearch.name);
	return url;
};