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
				/*console.log('logat');
				console.log(response);*/
				var token = response.authResponse.accessToken;
				tokenService.saveToken(token);
				FB.api('/me', function(response) {
				      console.log(response);
				      console.log('token  ' + token);
				      userService.login('facebook', response.id);
				      loggedin(token);
				    });
			} else {
				//console.log('not authorised or canseled login');
			}
		}, {scope: 'email,public_profile,user_events'})
	};

	window.onSignIn = function(googleUser) {
        // Get some info
        setDisplayElement('#loginButtons', 'none');
		setDisplayElement('#userControlls', 'block');
		userService.login('google');


		var options = new gapi.auth2.SigninOptionsBuilder(
        {'scope': 'email https://apps-apis.google.com/a/feeds/calendar/resource/'});


        googleUser.grant(options).then(
		    function(success){
		     //console.log(JSON.stringify({message: "success", value: success}));
		    },
		    function(fail){
		      alert(JSON.stringify({message: "fail", value: fail}));
		 });

		var profile = googleUser.getBasicProfile();
		/*  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.*/
		$scope.user.name=profile.getName();
		console.log(profile.getName());
		$scope.user.id=profile.getEmail();
		$scope.user.imgUrl= profile.getImageUrl();
 		//$scope.id_token=googleUser.getAuthResponse().id_token;

  		/* sendGoogleToken.getResponseFromServer($scope.id_token).success(function(data){
   	 		$scope.user.email = data;			
   		});*/

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

	$scope.logat = function() {
		$scope.getAccessToken();
	}

	$scope.test = function() {
		//onsole.log(tokenService.getToken(userService.getNetwork()));
		//console.log(gapi.auth2.getAuthInstance().currentUser.get().getAuthResponse().id_token)
		FB.getLoginStatus(function(response) {
			console.log(response);
		});
	}

	$scope.verifyLoginStatus = function() {
		if(userService.isLoggedin()) {
			FB.getLoginStatus(function(response) {
				var token = response.authResponse.accessToken;
				//console.log(response);
				loggedin(token);
				setDisplayElement('#loginButtons', 'none');
				setDisplayElement('#userControlls', 'block');
			});
		}
	};

	function loggedin(token) {
		/*userService.getGroups(token).success(function(data) {
			console.log(data);
		});
		userService.getUserDetails(token).success(function(data) {
			console.log(data);
		});
		loginService.loginServer(token).success(function(data) {
			console.log(data);
		});*/
	}
}]);

function logoutFB(response) {
	if(response === 'connected') {
		setDisplayElement('#loginButtons', 'block');
		setDisplayElement('#userControlls', 'none');
		userService.logout();
		FB.logout();
	}
}
