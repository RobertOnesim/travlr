app.factory('userService', function(){
	var userToken = '';
	return {
		setToken: function(token) {
			userToken = token;
		},
		getToken: function() {
			return userToken;
		},
		resetToken: function() {
			userToken = '';
		}
	}
})