const BOOKID = ":bookId";  
var booksApp = angular.module('booksApp', ['ngRoute','ui.bootstrap', 'ngStorage']);

booksApp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
	$routeProvider.
		when('/' , {	
			templateUrl: 'app/views/bookListPage.html',
			controller: 'bookListCtrl',
			publicAccess: true
		}).
	   	when('/createBook' , { 
	   		templateUrl: 'app/views/createBookPage.html', 
	   		controller: 'createBookCtrl',
	   		publicAccess: false
	   	}).
		when('/editBook/' + BOOKID, {
			templateUrl: 'app/views/editBookPage.html',
			controller: 'editBookCtrl',
			publicAccess: false
		}).
        when('/lendBook/' + BOOKID, {
            templateUrl: 'app/views/lendBookPage.html',
            controller: 'lendBookCtrl',
            publicAccess: false
        }).
        when('/deleteBook/' + BOOKID, {
            templateUrl: 'app/views/deleteBookPage.html',
            controller: 'deleteBookCtrl',
            publicAccess: false
        }).
        when('/register', {
            templateUrl: 'app/views/registerPage.html',
            controller: 'registerCtrl',
            publicAccess: true
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
				  			  
				data = {name: aUser.name, pass: aUser.pass, sessionKey: "123456"}
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

booksApp.run(function($rootScope,$http, $route, $location) {
	$rootScope.books = [];
	
	$rootScope.$on('$routeChangeStart', function (event) {
		var path = $location.path();
		var currentRoute = $route.routes[path];
		if (!currentRoute){
			path = path.replace(/[\d+]$/g, '');
			path += BOOKID;
		}
		currentRoute = $route.routes[path];
		if (!currentRoute || currentRoute.publicAccess){
			return;
		}
		
	    if (!sessionStorage.loggedIn) {
	        $location.path('/');
	    }
	});	
});

