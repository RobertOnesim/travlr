app.controller('IndexController', ['$scope', 'userService', function($scope, userService) {
	$scope.cartCount = userService.getCartSize();
	$scope.badgeOn = false;
	if($scope.cartCount > 0) {
		$scope.badgeOn = true;
	}

	$scope.refreshCart = function() {
		$scope.cartCount = userService.getCart().length;
		if($scope.cartCount > 0) {
			$scope.badgeOn = true;
		}
	}

	$scope.visitCart = function() {
		window.location.href = "#/cart";
	}

	$scope.FBLogin = function() {
		FB.login(function(response) {
			if(response.authResponse) {
				var login = angular.element(document.querySelector('#loginButtons'));
				var userContolls = angular.element(document.querySelector('#userControlls'));
				login.css('display', 'none');
				userContolls.css('display', 'block');
				console.log('Welcome.......');
				FB.api('/me', function(response) {
					console.log(response.name);
					console.log(response);
				});
			} else {
				console.log('not authorised or canseled login');
			}
		})
	};

	$scope.home = function() {
		window.location.href = '#/';
	}
}]);