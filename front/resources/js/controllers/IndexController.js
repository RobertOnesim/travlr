app.controller('IndexController', ['$scope', 'userService', 'cartService', function($scope, userService, cartService) {
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
				userService.login('facebook');
				FB.api('/me', function(response) {
				      console.log(response);
				      userService.getGroups(response.id).success(function(data) {
				      	console.log(data);
				      });
				    });
			} else {
				console.log('not authorised or canseled login');
			}
		}, {scope: 'email,public_profile,user_events'})
	};

	window.onSignIn = function(googleUser) {
        // Get some info
        var login = angular.element(document.querySelector('#loginButtons'));
		var userContolls = angular.element(document.querySelector('#userControlls'));
		login.css('display', 'none');
		userContolls.css('display', 'block');
    }
    
	$scope.home = function() {
		window.location.href = '#/';
	};

	$scope.logout = function() {
		//console.log($scope.getAccessToken());
		setDisplayElement('#loginButtons', 'block');
		setDisplayElement('#userControlls', 'none');

		userService.logout();
		FB.logout();
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

	$scope.verifyLoginStatus = function() {
		if(userService.isLoggedin()) {
			setDisplayElement('#loginButtons', 'none');
			setDisplayElement('#userControlls', 'block');
		}
	}
}]);

function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    if (response.status === 'connected') {
      	/*setDisplayElement('#loginButtons', 'none');
      	setDisplayElement('#userControlls', 'block');*/
    } else if (response.status === 'not_authorized') {
    	console.log('Please log into this app.');
    } else {
       	console.log('Please log into Facebook.');
    }
}
