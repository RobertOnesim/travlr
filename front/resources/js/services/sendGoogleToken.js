app.factory('sendGoogleToken', ['$http', function($http) {
	return {
		getResponseFromServer: function(user) {
			var baseURL = domain + 'loginGoogle?';
 			return $http.get(createTokenURL(user, baseURL))
 				.success(function(data) {
 				   return data;
        	})
        		.error(function(err) {
        		return err; 
        	}); 
        }
    }    		
}]);
function createTokenURL(user, url) {
	url = addParameter(url, 'userId', user.id);
    url = addParameter(url, 'userName', user.name);
    url = addParameter(url, 'imgUrl', user.imgUrl);
	return url;
};