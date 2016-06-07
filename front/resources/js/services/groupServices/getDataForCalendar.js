app.service('getDataForCalendar', ['$http', function ($http) {
  return {
        getData: function(group) {
            var baseURL = domain + 'calendar/search?';
            return $http.get(createSetCalendarURL(group, baseURL))
                .success(function(data) {
                   return data;
            })
                .error(function(err) {
                return err; 
            }); 
        }
    }           
}]);
function createSetCalendarURL(group, url) {
    url = addParameter(url, 'groupId', group);
    return url;
};

function parse(data) {

    var ans={};

    if(data) {
        for (var i = 0; i < data.length; i++) {
            //users.(data[i].data) =
                var users = [{
                    date:"",
                    country:"US",
                    name:""
                 }];
            var aux =  data[i].data;
            users.date= aux;
            var aux1 =  data[i].name;
            users.name=aux1;
            ans[data[i].data] = users;
        }
        return ans;
    }
}