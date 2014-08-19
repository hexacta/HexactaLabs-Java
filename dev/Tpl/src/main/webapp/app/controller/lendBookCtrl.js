booksApp.controller('lendBookCtrl', function ($scope, $location, $rootScope, $routeParams, $http) {
	$scope.books = $rootScope.books;

	$scope.backToHome = function(){
    	$location.path("/");
    };
    
    $scope.loadUsers = function(){
		$http({
			method : 'GET',
			url: '/Tpl/rest/users',
			headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
		}).success(function(data, status, headers, config){
			if(status = 200)
			{
				$rootScope.users = [];
				$rootScope.users = data;
				$scope.users = $rootScope.users;
			}
		}).error(function(data, status, headers, config){
			console.log("An Error occurred while trying to get all u");
		});
	}
	$scope.loadUsers();

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
