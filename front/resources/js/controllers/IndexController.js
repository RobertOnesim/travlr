app.controller('IndexController', ['$scope', 'userService', 'cartService', 'tokenService', 'loginService', function($scope, userService, cartService, tokenService, loginService) {
	$scope.cartCount = cartService.getCartSize();
	$scope.badgeOn = false;
	if($scope.cartCount > 0) {
		$scope.badgeOn = true;
	}

	$scope.refreshCart = function() {
		$scope.cartCount = cartService.getCart().length;
		if($scope.cartCount > 0) {
			$scope.badgeOn = true;
		} else {
			$scope.badgeOn = false;
		}
	}

	$scope.visitCart = function() {
		window.location.href = "#/cart";
	}

	$scope.FBLogin = function() {
		FB.login(function(response) {
			if(response.authResponse) {
				setDisplayElement('#loginButtons', 'none');
				setDisplayElement('#userControlls', 'block');
				console.log('logat');
				console.log(response);
				var token = response.authResponse.accessToken;
				tokenService.saveToken(token);
				FB.api('/me', function(response) {
				      console.log(response);
				      console.log('token  ' + token);
				      userService.login('facebook', response.id);
				      userService.getGroups(token).success(function(data) {
				      	console.log(data);
				      });
				      userService.getUserDetails(token).success(function(data) {
				      	console.log(data);
				      });
				      loginService.loginServer().success(function(data) {

				      });
				    });
			} else {
				//console.log('not authorised or canseled login');
			}
		}, {scope: 'email,public_profile,user_events'})
	};

	window.onSignIn = function(googleUser) {
        // Get some info
        var login = angular.element(document.querySelector('#loginButtons'));
		var userContolls = angular.element(document.querySelector('#userControlls'));
		login.css('display', 'none');
		userContolls.css('display', 'block');
		userService.login('google', googleUser.wc.hg);
		var token = googleUser.getAuthResponse().id_token;
		userService.getGroups(token).success(function(data) {
			console.log(data);
		});
		userService.getUserDetails(token).success(function(data) {
			console.log(data);
		});
		loginService.loginServer().success(function(data) {

		});
		console.log(googleUser);
		console.log(googleUser.getAuthResponse().id_token);
    }
    
	$scope.home = function() {
		window.location.href = '#/';
	};

	$scope.logout = function() {
		FB.getLoginStatus(function(response) {
			logoutFB(response)
		});
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function () {
			userService.logout();
			setDisplayElement('#loginButtons', 'block');
			setDisplayElement('#userControlls', 'none');
		});
	};

	$scope.getAccessToken = function() {
		FB.getLoginStatus(function(response) {
		    statusChangeCallback(response);
		});
		return FB.getAuthResponse()['accessToken'];
	}

	$scope.logat = function() {
		$scope.getAccessToken();
	}

	$scope.test = function() {
		console.log(tokenService.getToken(userService.getNetwork()));
		//console.log(gapi.auth2.getAuthInstance().currentUser.get().getAuthResponse().id_token)
	}

	$scope.verifyLoginStatus = function() {
		if(userService.isLoggedin()) {
			/*userService.getUserDetails().success(function(data) {
				$scope.userDetails = data;
			});
			userService.getGroups().success(function(data) {
				$scope.groups = data;
			});*/
			setDisplayElement('#loginButtons', 'none');
			setDisplayElement('#userControlls', 'block');
		}
	};
}]);

function logoutFB(response) {
	if(response === 'connected') {
		setDisplayElement('#loginButtons', 'block');
		setDisplayElement('#userControlls', 'none');
		userService.logout();
		FB.logout();
	}
}
