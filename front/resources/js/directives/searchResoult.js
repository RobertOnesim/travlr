app.directive('searchResoult', function() {
	return {
		restrict: 'E',
		replace: 'true',
		link: function(scope, elements, attrs) {
			scope.getTemplateURL = function() {
				if(scope.flight.withReturn == true) {
					return 'views/directives/searchResoultWithReturn.html';
				} else {
					return 'views/directives/searchResoultOneWay.html';
				}
			};
		},
		scope: false,
		template: '<div data-ng-include="getTemplateURL()"></div>'
	};
});