app.service('setUserCalendarService', ['$http', function ($http) {
  return {
        setCalendar: function(user,group) {
            var baseURL = domain + 'setCalendar?';
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