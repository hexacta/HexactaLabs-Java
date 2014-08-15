booksApp.controller('editBookCtrl', function($scope, $location, $rootScope,
		$routeParams, $http) {
	$scope.books = $rootScope.books;
	
	$scope.backToHome = function() {
		$location.path("/");
	};

	$scope.save = function(aBook) {
		$scope.newBook = angular.copy(aBook);
		$rootScope.books[$rootScope.books.length] = aBook;
	};

	$scope.reset = function() {
		$scope.newBook = {};
	};

	$scope.reset();

	$scope.bookId = $routeParams.bookId;
	$scope.currentBook = null;

	$http({
		method : 'GET',
		url : '/Tpl/rest/books/' + $scope.bookId,
		headers : {
			'Content-type' : 'application/json',
			'Accept' : 'application/json'
		}
	}).success(function(data, status, headers, config) {

		if (status = 200) {
			$scope.currentBook = data;
		}

	}).error(function(data, status, headers, config){
		console.log("An Error occurred while trying to get book:" + $scope.bookId);
	});
    
    $scope.save = function(aBook) {
       	var jsonBook = angular.toJson(aBook);
       	$http.put('/Tpl/rest/books/'+$scope.bookId, jsonBook).success(function(data, status, headers, config){
       		if(status = 200)
       		{
       	    	console.log("Book Saved");
       	    	$location.path("/");
       		}
       	}).error(function(data, status, headers, config){
       		console.log("An Error occurred while trying to update book id: " +$scope.bookId);
    	});
       	$scope.newBook = angular.copy(aBook);
		$rootScope.books[$rootScope.books.length] = aBook;	
    };
});