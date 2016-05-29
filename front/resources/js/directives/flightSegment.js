app.directive('flightSegment', function() {
	return {
		restrict: 'E',
		replace: 'true',
		scope: {
			segment: '='
		},
		templateUrl: 'views/directives/flightSegment.html'
	};
});