app.factory('sendGoogleToken', ['$http', function($http) {
	return {
		getResponseFromServer: function(token) {
			var baseURL = domain + 'tokensignin?';
 			return $http.get(createTokenURL(token, baseURL))
 				.success(function(data) {
 				   return data;
        	})
        		.error(function(err) {
        		return err; 
        	}); 
        }
    }    		
}]);
function createTokenURL(token, url) {
	url = addParameter(url, 'token', token);
	return url;
};