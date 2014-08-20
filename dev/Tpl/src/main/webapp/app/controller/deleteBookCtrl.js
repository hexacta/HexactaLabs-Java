booksApp.controller('deleteBookCtrl', function ($scope,$location,$rootScope, $http) {
	$scope.books = $rootScope.books;
	
	$scope.backToHome = function(){
    	$location.path("/");
    };
    
    $scope.borrar = function(bookId){
  //  	$rootScope.books.splice(bookId, 1);
    	var pathArray = window.location.href.split( '/' );
    	bookNum = pathArray[pathArray.length - 1];
		var jsonBook = angular.toJson(bookId);
		$http.delete('/Tpl/rest/books/' + bookNum, jsonBook).success(
				function(data, status, headers, config) {
					if (status == 200 || status == 204) {
						// Ok message and go back
						// alert('ok');
						console.log("Deletion Completed.\n Estado:" + status);
						$location.path("/");
					}
				}).error(function(data, status, headers, config) {
			console.log("An Error occurred while trying to store a book");
		});
    	
    	$location.path("/");
    };
});