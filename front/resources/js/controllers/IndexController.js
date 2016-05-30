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
}]);