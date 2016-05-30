app.factory('userService', function($cookies){
	var authentication = {
		token: '',
		network: ''
	};
	return {
		setToken: function(token, network) {
			authentication.token = token;
			authentication.network = network;
		},
		getToken: function() {
			return authentication.token;
		},
		resetToken: function() {
			authentication.token = '';
			authentication.network = '';
		},
		removeToken: function() {

		},
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
		enptyCart: function() {
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
			console.log(cart);
			console.log(flight);
			console.log(amount);
			for(var i = 0; i < cart.length; i++) {
				if(cart[i].Id == flight.Id) {
					cart[i].amount += amount;
				}
			}
		}
	};
});

function addMinutes(date, minutes) {
	return new Date(date.getTime() + minutes * 60000);
}

function getCartFromStorage() {
	var cart;
	var expireDate;
	if(localStorage.cart) {
		cart = JSON.parse(localStorage.cart);
		expireDate = new Date(localStorage.expireDate);
	}
	if(!expireDate || expireDate < new Date()) {
		cart = [];
	}
	return cart;
}

function saveCartToStorage(cart) {
	expireDate = addMinutes(new Date(), 60);
	localStorage.cart = JSON.stringify(cart);
	localStorage.expireDate = expireDate;
}