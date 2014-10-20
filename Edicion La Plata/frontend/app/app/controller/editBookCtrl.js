var HTTP_OK = 200;
booksApp.controller('editBookCtrl', function($scope, $location, $rootScope,
		$routeParams, $http) {
	$scope.books = $rootScope.books;
	
	$scope.pageTitle = 'Editar Libro';
	
	$scope.backToHome = function() {
		$location.path("/");
	};

	$scope.reset = function() {
		$scope.book = {};
	};

	$scope.reset();

	$scope.bookId = $routeParams.bookId;
	$scope.book = {};

	$scope.getBook = function(){
	$http({
		method : 'GET',
		url : 'http://localhost:4515/api/Books' + $scope.bookId,
		headers : {
			'Content-type' : 'application/json',
			'Accept' : 'application/json'
		}
	}).success(function(data, status, headers, config) {

		if (status == HTTP_OK) {
			$scope.book = data;
		}
	}).error(function(data, status, headers, config){
		console.log("An Error occurred while trying to get book:" + $scope.bookId);
		$scope.backToHome();
	});
	};
	
	$scope.getBook();
    
    $scope.save = function(aBook) {
		$scope.trySubmit = true;
		
		if ($scope.bookForm.$invalid) {
			return false;
		}
		
		$scope.backToHome();
		return true;
		
       	var jsonBook = angular.toJson(aBook);
       	$http.put('http://localhost:4515/api/Books' + $scope.bookId, jsonBook).success(function(data, status, headers, config){
       		if(status == HTTP_OK)
       		{
       	    	console.log("Book Saved");
       	    	$location.path("/");
       		}
       	}).error(function(data, status, headers, config){
       		console.log("An Error occurred while trying to update book id: " + 
			$scope.bookId);
    	});
       	$scope.newBook = angular.copy(aBook);
		$rootScope.books[$rootScope.books.length] = aBook;	
    };
    
});