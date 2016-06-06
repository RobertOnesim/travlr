app.factory('cityImageService', ['$http', function($http){
	return {
		city: {},
		getImage: function(cityCoordinates) {
			var self = this;
			baseURL = 'http://www.panoramio.com/map/get_panoramas.php?';
			return $http.jsonp(createCityPhotoURL(cityCoordinates))
				.success(function(data, status, config, headers) {
					cityCoordinates.imgUrl = data.photos[0].photo_file_url;
					//console.log(cityCoordinates);
					self.city = cityCoordinates;
				})
				.error(function() {

				});
		}
	};
}]);

function createCityPhotoURL(cityCoordinates) {
	baseURL =  addParameter(baseURL, 'set', 'public');
	baseURL =  addParameter(baseURL, 'from', '0');
	baseURL =  addParameter(baseURL, 'to', '1');
	baseURL =  addParameter(baseURL, 'minx', cityCoordinates.lon);
	baseURL =  addParameter(baseURL, 'miny', cityCoordinates.lat);
	baseURL =  addParameter(baseURL, 'maxx', cityCoordinates.lon + 1);
	baseURL =  addParameter(baseURL, 'maxy', cityCoordinates.lat + 1);
	baseURL =  addParameter(baseURL, 'size', 'medium');
	baseURL =  addParameter(baseURL, 'mapfilter', 'true');
	baseURL =  addParameter(baseURL, 'callback', 'JSON_CALLBACK');
	//console.log(baseURL);
	return baseURL;
}

function parseCityPhotoResponse(data, cityCoordinates) {
	cityCoordinates.imgUrl = data.photos[0].photo_file_url;
	return cityCoordinates;
}
