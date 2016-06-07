app.factory('findUserService', ['$http', function($http) {
	return {
		getUsers: function(userSearch) {
			var baseURL = domain + 'usersNotInGroupByName?';
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

function createFindURL(userSearch, url) {
	url = addParameter(url, 'userName', userSearch.name);
    url = addParameter(url, 'groupId', userSearch.idGroup);
	return url;
};

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