app.controller('CartController', ['$scope', 'userService', function($scope, userService) {
	$scope.items = userService.getCart();

	$scope.removeFromCart = function(flight) {
		userService.removeFromCart(flight);
		for(var i = 0; i < $scope.items.length; i++) {
			if($scope.items[i].Id == flight.Id) {
				$scope.items.splice(i, 1);
				break;
			}
		}
		$scope.$parent.refreshCart();
	}

	$scope.updateFlightAmount = function(flight, amount) {
		userService.updateFlightAmount(flight, amount);
		//console.log(userService.getCart());
	}
}]);