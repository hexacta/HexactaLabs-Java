booksApp.controller('viewController', function($scope, $http){

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
		if($scope.sessionData != undefined){
			return $scope.user = $scope.sessionData.username; 
		}			
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
				//TODO:
				$scope.sessionData = user; 
			}
		}).error(function(data, status, headers, config){
			console.log("An Error occurred while trying to login");
		});
	};

});