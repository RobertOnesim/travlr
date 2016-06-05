app.factory('tokenService', ['$cookies', function($cookies){
	return {
		getToken: function(network) {
			var token;
			if(network == 'facebook') {
				return FB.getLoginStatus(function(response) {
					if (response.status == 'connected') {
						/*token =  response.authResponse.accessToken;
						console.log('token   ' + token);
						if(!token) {*/
							token = localStorage.token;
						//}
						return token;
					} else {
						token = '';
					}
				});
			} else {
				console.log('caca');
				//token = gapi.auth2.getAuthInstance().currentUser.get().getAuthResponse().id_token;
				return gapi.auth2.getAuthInstance().currentUser.get().getAuthResponse().id_token;
			}
			
		},
		saveToken: function(token) {
			localStorage.token = token;
		}
	};
}]);


function fbToken(response) {
	
}