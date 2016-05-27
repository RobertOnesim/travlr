app.directive('collapse', function() {
	return {
		restrict: 'E',
		replace: 'true',
		link: function(scope, elements, attrs) {
			scope.getTemplateURL = function() {
				if(scope.flightWithRerutn == true) {
					return 'views/directives/collapseWithReturn.html';
				} else {
					return 'views/directives/collapseOneWay.html';
				}
			};
		},
		scope: {
			flight: '=',
			flightWithRerutn: '='
		},
		template: '<div data-ng-include="getTemplateURL()"></div>'
	};
});