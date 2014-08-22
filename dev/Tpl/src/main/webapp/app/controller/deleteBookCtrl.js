booksApp.controller('deleteBookCtrl', function ($scope, $location, $routeParams, $http) {
	$scope.backToHome = function(){
    	$location.path("/");
    };
    
    $scope.borrar = function(){
		$http.delete('/Tpl/rest/books/'+ $routeParams.bookId).success(
				function(data, status, headers, config) {
<<<<<<< HEAD
					if (status == 200) {
=======
					if (status == 204) {
>>>>>>> ee4c7d4f195a05e7e64b4422afee0982dba1b329
						console.log("Deletion Completed.\n");
						$location.path("/");
					}
				}).error(function(data, status, headers, config) {
					console.log("An Error occurred while trying to store a book");
		});
    	$location.path("/");
    };
});