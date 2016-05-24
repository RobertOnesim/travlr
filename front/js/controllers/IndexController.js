app.controller('IndexController', ['$scope', function($scope) {
	$scope.showLogin = function() {
		var layout = document.querySelector('.mdl-layout');
		layout.MaterialLayout.toggleDrawer();
	}
}]);