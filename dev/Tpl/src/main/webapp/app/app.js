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

	}

});

booksApp.run(function($rootScope,$http, $route, $location, $rootScope) {
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
		
	    if (!$rootScope.loggedIn) {
	        $location.path('/');
	    }
	});	
});

