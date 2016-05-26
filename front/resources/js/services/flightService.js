app.factory('flightService', ['$http', function($http) {
 	return $http.get('http://95.76.234.124:8080/flight')
 		.success(function(data) {
 			return data;
        })
        .error(function(err) {
        	return err; 
        }); 
}]);