app.directive('userRemove', function() {
	return {
		restrict: 'E',
		replace: 'true',
		scope: false,
		templateUrl: 'views/directives/groupMembers/userRemove.html'
	};
});