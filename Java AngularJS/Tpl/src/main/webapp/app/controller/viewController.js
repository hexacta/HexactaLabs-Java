booksApp.controller('viewController', function($scope, $http, $rootScope, $sessionStorage ){

	$rootScope.loggedIn = false;
	$rootScope.loggedIn = $sessionStorage.user !== undefined;
	if( $sessionStorage.user !== undefined){
		$rootScope.usuario = "Bienvenido: " + JSON.parse($sessionStorage.user).username + "!"; 
	}
	$scope.mostrarLogin = function(){
		return !sessionStorage.loggedIn;
	};

	$scope.mostrarUser = function(){
		return sessionStorage.loggedIn;
	};

	$scope.getNombreUsuario = function(){
		if(sessionStorage.loggedIn){
			return $scope.user = JSON.parse(sessionStorage.user).username; 
		}	
	};

	$scope.desloguearse = function(){
		$rootScope.loggedIn = false;
		$rootScope.usuario = undefined; 
		delete $sessionStorage.user;
	};

	$scope.validateUser = function(user){
		var toEncode = user.username + ":" + user.password;
		var encoded = btoa(toEncode);
		$http({
			method : 'GET',
			url: '/Tpl/rest/login',
			headers : {'Authorization' : 'Basic ' + encoded, 'Accept' : 'application/json'}
		}).success(function(data, status, headers, config){
			if(status == 200){
				$sessionStorage.user = JSON.stringify(data);
				$scope.usuario = "Bienvenido: " + JSON.parse($sessionStorage.user).username + "!"; 
				$rootScope.loggedIn = true;
				$scope.invalidLogin = false;
			}else if (status == 401){
				$scope.loginError();
			}
		}).error(function(data, status, headers, config){
			$scope.loginError();
		});
	};
	
	$scope.loginError = function(){
		console.log("An Error occurred while trying to login");
		$scope.user.password="";
		$scope.invalidLogin = true;
	}

});

