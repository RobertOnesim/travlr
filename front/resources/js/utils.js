function getHeaderValues(userService) {
	var values = {
			headers: {
				'responseType': 'application/json'
			}
		};
	/*if(userService.getToken() != '') {
		values.headers.userToken = userService.getToken();
	}*/
	return values;
}

function addParameter(url, paramName, paramValue) {
	if(url.substr(url.length - 1) != '?') {
		url += '&';
	}
	url += paramName + '=' + paramValue;
	return url;
}

function extractURLDate(rawDate) {
	var date = '', number;
	date += rawDate.getUTCFullYear() + '-';

	number = rawDate.getUTCMonth() + 1;
	if(number <= 9) {
		date += '0' + number + '-';
	} else {
		date += number + '-';
	}
	
	number = rawDate.getUTCDate() + 1;
	if(number <= 9) {
		date += '0' + number;
	} else {
		date += number;
	}

	return date;
}

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
		saveCartToStorage(cart);
	}
	return cart;
}

function saveCartToStorage(cart) {
	expireDate = addMinutes(new Date(), 60);
	localStorage.cart = JSON.stringify(cart);
	localStorage.expireDate = expireDate;
}

function getUserFronStorage() {
	var user = {network: ''};
	if(!localStorage.user) {
		return user;
	}

	return JSON.parse(localStorage.user);
}

function saveUserToStorage(user) {
	localStorage.user = JSON.stringify(user);
}

function setDisplayElement(querySelector, value) {
	var element = angular.element(document.querySelector(querySelector));
	element.css('display', value);
}