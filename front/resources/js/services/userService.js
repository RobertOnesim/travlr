app.factory('userService', function(){
	var userToken = '';
	var cart = [];
	return {
		setToken: function(token) {
			userToken = token;
		},
		getToken: function() {
			return userToken;
		},
		resetToken: function() {
			userToken = '';
		},
		addToCart: function(flight) {
			cart.push(flight);
		},
		removeFromCart: function(flight) {
			for(var i = 0; i < cart.lenght; i++) {
				if(cart[i].id == flight.id) {

				}
			}
		},
		enptyCart: function() {
			cart = [];
		},
		getCart: function() {
			return cart;
		}
	};
})