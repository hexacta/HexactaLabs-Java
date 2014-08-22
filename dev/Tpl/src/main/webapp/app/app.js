var booksApp = angular.module('booksApp', ['ngRoute','ui.bootstrap']);

booksApp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
	$routeProvider.
		when('/', {	
			templateUrl: 'app/views/bookListPage.html',
			controller: 'bookListCtrl' 
		}).
	   	when('/createBook', { 
	   		templateUrl: 'app/views/createBookPage.html', 
	   		controller: 'createBookCtrl'
	   	}).
		when('/editBook/:bookId', {
			templateUrl: 'app/views/editBookPage.html',
			controller: 'editBookCtrl'
		}).
        when('/lendBook/:bookId', {
            templateUrl: 'app/views/lendBookPage.html',
            controller: 'lendBookCtrl'
        }).
        when('/deleteBook/:bookId', {
            templateUrl: 'app/views/deleteBookPage.html',
            controller: 'deleteBookCtrl'
        }).
        when('/register', {
            templateUrl: 'app/views/registerPage.html',
            controller: 'registerCtrl'
        }).
		otherwise({
			redirectTo: '/'
		});
}]);

booksApp.directive('userIngreso',function() {
	return {
		restrict: 'E',
		templateUrl: 'app/views/loginView.html',
		controller: ['$scope', '$http', '$location', function($scope, $http, $location) {
		
			$scope.user = {};	
	
			$scope.validateUser = function(aUser) {
				  			  
				data = {name: aUser.username, pass: aUser.password, sessionKey: "123456"}
					var jsonUser = angular.toJson(aUser);
				
					//$http.post('/Tpl/rest/login', jsonUser).success(
					//		function(data, status, headers, config) {
					//			if (status = 200) {
									$scope.sessionData = data;
									// Ok message and go back
									var usuario = $scope.sessionData;
					//				$location.path("/");
					//			}
					//		}).error(function(data, status, headers, config) {
					//	console.log("An Error occurred while trying to store the user:" + jsonUser);
					//});
				}
		}]
	}

});

booksApp.run(function($rootScope,$http) {
	$rootScope.books = [];
});
