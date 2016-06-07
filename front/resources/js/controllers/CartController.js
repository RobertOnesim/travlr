app.controller('CartController', ['$scope', 'userService', 'buyService', 'cartService', function($scope, userService, buyService, cartService) {
	$scope.items = cartService.getCart();

	$scope.removeFromCart = function(flight) {
		cartService.removeFromCart(flight);
		for(var i = 0; i < $scope.items.length; i++) {
			if($scope.items[i].Id == flight.Id) {
				$scope.items.splice(i, 1);
				break;
			}
		}
		$scope.$parent.refreshCart();
	}

	$scope.updateFlightAmount = function(flight, amount) {
		cartService.updateFlightAmount(flight, amount);
		$scope.items = cartService.getCart();
	}

	$scope.buy = function() {
		//buyService.buy(items);
		cartService.emptyCart();
		$scope.items = cartService.getCart();
		$scope.$parent.refreshCart();
	}
}]);