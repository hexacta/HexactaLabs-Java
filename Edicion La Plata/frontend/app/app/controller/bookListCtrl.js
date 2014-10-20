booksApp.controller('bookListCtrl', function ($scope,$location,$rootScope,$http) {
	$scope.predicate = 'name';
	$scope.reverse = false;
	
	$scope.loadBooks = function(){
		$http({
			method : 'GET',
			url: 'http://localhost:4515/api/Books',
			headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
		}).success(function(data, status, headers, config){
			$scope.books = data;
		}).error(function(data, status, headers, config){
			console.log("An Error occurred while trying to load the books");
		});		
	};
	
	$scope.loadBooks();
	
	$scope.linkToEditBook=function(bookId){
		$location.path("/editbook/" + bookId);
	};
	
	$scope.linkToCreateBook=function(){
		$location.path("/createbook");
	};	
});

