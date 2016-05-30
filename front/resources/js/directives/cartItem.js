app.directive('cartItem', function() {
	return {
		restrict: 'E',
		replace: true,
		scope: false,
		templateUrl: 'views/directives/cartItem.html'
	};
});