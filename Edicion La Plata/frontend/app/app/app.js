var booksApp = angular.module('booksApp', ['ngRoute','ngStorage']);
booksApp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
	$routeProvider.
		when('/' , {	
			templateUrl: 'app/views/bookListPage.html',
			controller: 'bookListCtrl',
			publicAccess: true
		}).
		when('/createbook' , {	
			templateUrl: 'app/views/createOrEditBookPage.html',
			controller: 'createBookCtrl',
			publicAccess: true
		}).
		when('/editbook/:bookId' , {	
			templateUrl: 'app/views/createOrEditBookPage.html',
			controller: 'editBookCtrl',
			publicAccess: true
		}).
		otherwise({
			redirectTo: '/'
		});
	
}]);


