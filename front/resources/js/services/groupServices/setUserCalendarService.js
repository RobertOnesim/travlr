app.service('setUserCalendarService', ['$http', function ($http) {
  return {
        setCalendar: function(user,group) {
            var baseURL = 'http://95.76.254.97:1056/setCalendar?';
            return $http.get(createSetCalendarURL(user,group, baseURL))
                .success(function(data) {
                   return data;
            })
                .error(function(err) {
                return err; 
            }); 
        }
    }           
}]);
function createSetCalendarURL(user,group, url) {
    url = addParameter(url, 'userId', user);
    url = addParameter(url, 'groupId', group);
    return url;
};