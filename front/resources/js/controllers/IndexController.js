app.controller('IndexController', ['$scope', 'userService', 'cartService', 'tokenService', 'loginService', '$mdDialog','$mdMedia', 'profilePictureService', 'offerService', 'createGroupService','sendGoogleToken', function($scope, userService, cartService, tokenService, loginService, $mdDialog,$mdMedia, profilePictureService, offerService, createGroupService,sendGoogleToken) {
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
	console.log($scope.user);

	$scope.visitCart = function() {
		window.location.href = "#/cart";
	}

	$scope.groupName ="";

	$scope.newGroup= function(group){
		console.log(group);
		createGroupService.createGroup(group).success(function(data) {
			userService.getGroups().success(function(data) {
				$scope.groups = data;
			});
		})

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

        //userService.login('google', googleUser.getBasicProfile().getId(), googleUser.getBasicProfile().getName());
		var profile = googleUser.getBasicProfile();
		/*  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.*/
		$scope.user.name=profile.getName();
		console.log(profile.getName());
		$scope.user.id=profile.getEmail();
		$scope.user.imgUrl= profile.getImageUrl();
		console.log($scope.user.imgUrl);
		userService.login('google', $scope.user.id, googleUser.getBasicProfile().getName());
		loggedin(googleUser.getAuthResponse().id_token);
 		//$scope.id_token=googleUser.getAuthResponse().id_token;

  		sendGoogleToken.getResponseFromServer($scope.user);

    }
    
	$scope.home = function() {
		window.location.href = '#/';
	};

	$scope.logout = function() {
		$scope.offers = [];
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
		userService.getGroups().success(function(data) {
			$scope.groups = data;
		});
		/*userService.getUserDetails(token).success(function(data) {
			console.log(data);
		});*/
		loginService.loginServer(token).success(function(data) {
			//console.log(data);
		});
		offerService.getOffers(token).success(function(data) {
			$scope.offers = data;
		});
	}

	$scope.goToGroup = function(id) {
		window.location.href = "#/group/" + id;
	}

	$scope.createGroup = function(ev) {
        $mdDialog.show({
          controller: DialogController,
          templateUrl: 'views/directives/groupMembers/newGroup.html',
          parent: angular.element(document.body),
          targetEvent: ev,
        });
        $scope.$watch(function() {
          return $mdMedia('xs') || $mdMedia('sm');
        });
    };
    console.log($scope.user);

}]);

function logoutFB(response) {
	if(response === 'connected') {
		setDisplayElement('#loginButtons', 'block');
		setDisplayElement('#userControlls', 'none');
		userService.logout();
		FB.logout();
	}
}
function DialogController($scope, $mdDialog) {
  $scope.hide = function() {
    $mdDialog.hide();
  };
  $scope.cancel = function() {
    $mdDialog.cancel();
  };
}