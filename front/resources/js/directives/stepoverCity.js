app.directive('stepoverCity', function() {
	return {
		restrict: 'E',
		replace: 'true',
		scope: {
			city: '='
		},
		templateUrl: 'views/directives/stepoverCity.html'
	};
});