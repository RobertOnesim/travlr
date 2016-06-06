app.factory('buyService', function(){
	var authentication = {
		token: '',
		network: ''
	};
	return {
		buy: function(cart) {
			return $http.get('http://95.76.234.124:1039/buy?', getHeaderValues(userService))
				.success(function(data, status, config, headers) {
					return parseResponse(data);
				})
				.error(function() {

				});
		}
	};
});
