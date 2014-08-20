booksApp.controller('deleteBookCtrl', function ($scope,$location,$rootScope) {
	$scope.books = $rootScope.books;
	
	$scope.backToHome = function(){
    	$location.path("/");
    };
    
    $scope.borrar = function(bookId){
    	$rootScope.books.splice(bookId, 1);
    	console.log("Intenta borrar");
    	/*
		var jsonBook = angular.toJson(bookId);
		$http.put('/Tpl/rest/books', jsonBook).success(
				function(data, status, headers, config) {
					if (status == 200) {
						// Ok message and go back
						// alert('ok');
						console.log("Deletion Completed.\n");
						$location.path("/");
					}
				}).error(function(data, status, headers, config) {
			console.log("An Error occurred while trying to store a book");
		});
    	*/
    	$location.path("/");
    };
});