app.controller('GroupController', ['$scope', '$controller', function($scope, $controller){
	$controller('SearchController', {$scope: $scope});
	$scope.$parent.doRequest = false;
}])