booksApp.controller('headerCtrl', function ($scope,$location) {

    $scope.isActive = function (viewLocation) { 
    	return viewLocation === $location.path();
    };

});