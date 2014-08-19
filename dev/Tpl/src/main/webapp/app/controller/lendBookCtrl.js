booksApp.controller('lendBookCtrl', function ($scope, $location, $rootScope, $routeParams, $http) {
	$scope.books = $rootScope.books;
	
	$scope.backToHome = function(){
    	$location.path("/");
    };
    
});    
    

var DatepickerDemoCtrl = function ($scope) {
	$scope.today = function() {
    $scope.fromDt = new Date();
  };
  $scope.today();

  $scope.clear = function () {
    $scope.fromDt = null;
    $scope.untilDt = null;
  };

  $scope.openFrom = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.openedFrom = true;
  };

  $scope.openUntil = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.openedUntil = true;
  };

  $scope.dateOptions = {
    formatYear: 'yy',
    startingDay: 1
  };

  $scope.initDate = new Date('2016-15-20');
  $scope.formats = ['dd MMMM yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
  $scope.format = $scope.formats[0];
};
