app.factory('cartService', function(){
	return {
		addToCart: function(flight) {
			var cart = getCartFromStorage();
			var found = false;
			flight.amount = 1;
			for(var i = 0; i < cart.length; i++) {
				if(cart[i].Id == flight.Id) {
					cart[i].amount += flight.amount;
					found = true;
					break;
				}
			}
			if(found == false) {
				cart.push(flight);
			}
			saveCartToStorage(cart);
		},
		removeFromCart: function(flight) {
			var cart = getCartFromStorage();
			for(var i = 0; i < cart.length; i++) {
				if(cart[i].Id == flight.Id) {
					cart.splice(i, 1);
					break;
				}
			}
			saveCartToStorage(cart);
		},
		emptyCart: function() {
			var cart = [];
			saveCartToStorage(cart);
		},
		getCart: function() {
			var cart = getCartFromStorage();
			return cart;
		},
		getCartSize: function() {
			var cart = getCartFromStorage();
			return cart.length;
		},
		updateFlightAmount: function(flight, amount) {
			var cart = getCartFromStorage();
			for(var i = 0; i < cart.length; i++) {
				if(cart[i].Id == flight.Id) {
					cart[i].amount += amount;
				}
			}
			saveCartToStorage(cart);
		}
	};
});
