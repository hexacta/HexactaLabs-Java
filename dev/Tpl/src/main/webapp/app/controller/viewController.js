booksApp.controller('viewController', function($scope){
	
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
//		document.getElementById("Usuario").innerHTML = "Bienvenido: " + $scope.sessionData.name;
		$scope.usuario = "Bienvenido: " + $scope.sessionData.name;
	}
};

});

