app.directive('upgradeComponent', function() {
	return {
		restrict: 'A',
		replace: 'true',
		link: function(scope, element, attr) {
			element.ready(function() {
				componentHandler.upgradeDom();
			});
		}
	};
});