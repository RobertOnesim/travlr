app.factory('buyService', function(){
	var authentication = {
		token: '',
		network: ''
	};
	return {
		buy: function(cart) {
			return $http.get(domain + 'buy?')
				.success(function(data, status, config, headers) {
					return parseResponse(data);
				})
				.error(function() {

				});
		}
	};
});
