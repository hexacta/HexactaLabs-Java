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
        when('/lendBook/:bookid', {
            templateUrl: 'app/views/lendBookPage.html',
            controller: 'lendBookCtrl'
        }).
        when('/deleteBook/:bookid', {
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

//sessionStorage.sessionData = "";

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
					//				console.log("Data: " + data);
									$scope.sessionData = data;
					
					//				console.log("Con stringify: " + sessionStorage.sessionData);
									// Ok message and go back
									var usuario = $scope.sessionData;
									console.log("Login Completed.\nUser name: "
											+ usuario.name);
					//				$location.path("/");
					//			}
					//		}).error(function(data, status, headers, config) {
					//	console.log("An Error occurred while trying to store the user:" + jsonUser);
					//});
				}

		}]
	}

});

booksApp.controller('mainController', function($scope){
	
$scope.mostrarLogin = function(){
	if($scope.sessionData == undefined){
		return true;
	}else {
		return false;
	}
	
};

$scope.mostrarUser = function(){
	if($scope.sessionData == undefined){
		return false;
	}else {
		return true;
	}
};

$scope.getNombreUsuario = function(){
	if($scope.sessionData != undefined)
		document.getElementById("Usuario").innerHTML = "Nombre: " + $scope.sessionData.name;
};

});

booksApp.run(function($rootScope,$http) {
	$rootScope.books = [];
});
