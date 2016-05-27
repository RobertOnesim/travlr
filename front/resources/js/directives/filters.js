app.directive('filters', function() {
	return {
		restrict: 'E',
		replace: 'true',
		scope: false,
		templateUrl: 'views/directives/filters.html'
	};
});