app.directive('collapse', function() {
	return {
		restrict: 'E',
		replace: 'true',
		link: function(scope, elements, attrs) {
			scope.getTemplateURL = function() {
				if(scope.flightType == 'WithReturn') {
					return 'views/directives/collapseWithReturn.html';
				} else {
					return 'views/directives/collapseOneWay.html';
				}
			};
		},
		scope: {
			flight: '=',
			flightType: '='
		},
		template: '<div data-ng-include="getTemplateURL()"></div>'
	};
});