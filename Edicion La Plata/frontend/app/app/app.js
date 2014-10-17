var booksApp = angular.module('booksApp', ['ngRoute','ngStorage']);
booksApp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
	$routeProvider.
		when('/' , {	
			templateUrl: 'app/views/bookListPage.html',
			controller: 'bookListCtrl',
			publicAccess: true
		}).
		otherwise({
			redirectTo: '/'
		});
	
}]);


