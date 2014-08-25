booksApp.controller('viewController', function($scope, $http, $rootScope, $sessionStorage ){

	sessionStorage.loggedIn = false;
	$rootScope.loggedIn = false;
	
/*	$scope.mostrarLogin = function(){
		return !sessionStorage.loggedIn;
	};

	$scope.mostrarUser = function(){
		return sessionStorage.loggedIn;
	};

	$scope.getNombreUsuario = function(){
		if(sessionStorage.loggedIn){
			return $scope.user = JSON.parse(sessionStorage.user).username; 
		}	
	};*/

	$scope.validateUser = function(user){
		var toEncode = user.username + ":" + user.password;
		var encoded = btoa(toEncode);
		$http({
			method : 'GET',
			url: '/Tpl/rest/login',
			headers : {'Authorization' : 'Basic ' + encoded, 'Accept' : 'application/json'}
		}).success(function(data, status, headers, config){
			if(status == 200){
				//TODO:
				sessionStorage.user = JSON.stringify(data);
				sessionStorage.loggedIn = true;
				$rootScope.loggedIn = true;
				$scope.user = "Bienvenido: " + JSON.parse(sessionStorage.user).username + "!"; 
			}
		}).error(function(data, status, headers, config){
			alert("El usuario " + user.username + " no esta registrado o escribio mal la password.");
			$scope.user.password="";
		});
	};

});

