app.controller('IndexController', ['$scope', 'userService', 'cartService','sendGoogleToken', function($scope, userService, cartService,sendGoogleToken) {
		
	$scope.user = {}


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
		//console.log($scope.getAccessToken());
		setDisplayElement('#loginButtons', 'block');
		setDisplayElement('#userControlls', 'none');


		userService.logout();
		FB.logout();

		var auth2 = gapi.auth2.getAuthInstance();
    	auth2.signOut().then(function ()  {
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
