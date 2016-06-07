app.service('fileUploadService', ['$http', function ($http) {
    this.uploadFileToUrl = function(file){
          var fd = new FormData();
                fd.append('file', file);
                var uploadUrl = domain + "uploadedCalendar";
                $http.post(uploadUrl, fd, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })
                .success(function(){
                })
                .error(function(){
                });
            }
}]);