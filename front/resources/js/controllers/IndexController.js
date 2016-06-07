app.controller('IndexController', ['$scope', 'userService', 'cartService', 'tokenService', 'loginService', 'profilePictureService', 'offerService', 'createGroupService', function($scope, userService, cartService, tokenService, loginService, profilePictureService, offerService, createGroupService) {
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

	$scope.user = {
		name: userService.getName(),
		imgUrl: ""
	};

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
				      userService.login('facebook', response.id, response.name);
				      $scope.user.name =userService.getName();

				      profilePictureService.getUrl(token).success(function(data) {
				      	$scope.user.imgUrl = data;
				      	console.log($scope.user);
				      })
				      
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


		var options = new gapi.auth2.SigninOptionsBuilder(
        {'scope': 'email https://apps-apis.google.com/a/feeds/calendar/resource/'});


        googleUser.grant(options).then(
		    function(success){
		     //console.log(JSON.stringify({message: "success", value: success}));
		    },
		    function(fail){
		      alert(JSON.stringify({message: "fail", value: fail}));
		 });

        userService.login('google', googleUser.getBasicProfile().getId(), googleUser.getBasicProfile().getName());
		var profile = googleUser.getBasicProfile();
		/*  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.*/
		$scope.user.name=profile.getName();
		console.log(profile.getName());
		$scope.user.id=profile.getEmail();
		$scope.user.imgUrl= profile.getImageUrl();
		console.log($scope.user.imgUrl);
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
		$scope.user = {
			name: "",
			imgUrl: ""
		};
		$scope.offers = [];
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
				$scope.user.name = userService.getName();
				profilePictureService.getUrl(token).success(function(data) {
					$scope.user.imgUrl = data;
				})
				setDisplayElement('#loginButtons', 'none');
				setDisplayElement('#userControlls', 'block');
			});
		}
	};

	function loggedin(token) {
		userService.getGroups(token).success(function(data) {
			console.log(data);
		});
		/*userService.getUserDetails(token).success(function(data) {
			console.log(data);
		});*/
		loginService.loginServer(token).success(function(data) {
			console.log(data);
		});
		offerService.getOffers(token).success(function(data) {
			$scope.offers = data;
		});
	}

	$scope.createGroup = function() {
		createGroupService.createGroup(name).success(function(data) {

		})
	}

	$scope.goToGroup = function(id) {
		window.location.href = "#/group/" + id;
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
