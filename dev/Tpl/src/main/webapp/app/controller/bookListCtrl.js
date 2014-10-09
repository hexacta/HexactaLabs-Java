booksApp.controller('bookListCtrl', function ($scope,$location,$rootScope,$http) {
	
	$scope.linkToCreateBook=function(){
		$location.path("/createBook");
	};
	$scope.linkToEditBook=function(bookId){
		$location.path("/editBook/" + bookId);
	};
	$scope.linkToDeleteBook=function(bookId){
		$location.path("/deleteBook/" + bookId);
    };
 
	$scope.linkToLendBook=function(bookId){
		$location.path("/lendBook/" + bookId);
	};

	$scope.linkToRegister=function(){
		$location.path("/register");
	};

	$http({
		method : 'GET',
		url: '/Tpl/rest/books',
		headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
	}).success(function(data, status, headers, config){
		$rootScope.books = [];
		$rootScope.books = data;
		$scope.books = $rootScope.books;
		$scope.predicate = 'name';
		$scope.reverse = false;
		$scope.cleanComments();
	}).error(function(data, status, headers, config){
		console.log("An Error occurred while trying to load the books");
	});	
		
		
	$scope.loadBookComments = function(book){
		$http({
			method : 'GET',
			url: '/Tpl/rest/comments/byBook/' + book.id,
			headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
			}).success(function(data, status, headers, config){
				if (status == 200 ){
					book.bookComments = data;
				}
			}).error(function(data, status, headers, config){
	    		console.log("An Error occurred while trying to get comments for the book " + $scope.selectedBook.name);
	    		book.bookComments = {};
	    	});
	};
		
		//Modal
	$scope.modifyModal=function(book){
		$scope.selectedBook = book;
		$scope.selectedBook.image = "";
		
		//Getting comments
		var bookId = $scope.selectedBook.id;
		$scope.loadBookComments(book);
				
	};
	
	
	//Method to add comments
	$scope.addComment = function(book){
		var comments = book.bookComments;
		delete book.bookComments;
		
		$scope.comment.book = book;
		var image = $scope.selectedBook.image;
		delete book.image;
			
		var jsonComment = angular.toJson($scope.comment);
		
		$http.post('/Tpl/rest/comments', jsonComment).success(function(data, status, headers, config){
    		if(status == 201){
    			$scope.loadBookComments(book);
    			console.log("Comment Creation Completed.");
    		}
    	}).error(function(data, status, headers, config){
    		console.log("An Error occurred while trying to store a comment");
    	}) ;
		book.bookComments = comments;
		book.image = image;
		$scope.cleanComments();
	};
	
	$scope.cleanComments = function(){
		$scope.comment = {};
	};
	
	
});