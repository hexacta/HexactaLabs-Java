booksApp.controller('bookListCtrl', function ($scope,$location,$rootScope,$http) {
	
	$scope.linkToCreateBook=function(){
		$location.path("/createBook");
	};
	$scope.linkToEditBook=function(bookId){
		$location.path("/editBook/"+bookId);
	};
	$scope.linkToDeleteBook=function(bookId){
		$location.path("/deleteBook/"+bookId);
    };
 
	$scope.linkToLendBook=function(bookId){
		$location.path("/lendBook/"+bookId);
	};

	$scope.linkToRegister=function(){
		$location.path("/register");
	};
	
	$http({
		method : 'GET',
		url: '/Tpl/rest/books',
		headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
	}).success(function(data, status, headers, config){

	$scope.modifyModal=function(book){
		$scope.selectedBook = book;
		
		var find = '-';
		var re = new RegExp(find, 'g');
		var isbn = $scope.selectedBook.isbn.replace(re , '');
		$http({
			method : 'GET',
			url: 'https://www.googleapis.com/books/v1/volumes?q=isbn+' + isbn,
			headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
			}).success(function(data, status, headers, config){
				if (status == 200 ){
					$scope.selectedBook.image = data.items[0].volumeInfo.imageLinks.thumbnail;
				}
			}).error(function(data, status, headers, config){
	    		console.log("An Error occurred while trying to connect to Google API");
	    		$scope.selectedBook.image = "";
	    	}) ;
		
	};
	
	$scope.loadBooks = function(){
		$http({
			method : 'GET',
			url: '/Tpl/rest/books',
			headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
		}).success(function(data, status, headers, config){

			if(status == 200)
			{
				$rootScope.books = [];
				$rootScope.books = data;
				$scope.books = $rootScope.books;
			}

		}).error(function(data, status, headers, config){
			console.log("An Error occurred while trying to get all books");
		});
	}
	$scope.loadBooks();
	
	$scope.comment = {};
	$scope.addComment = function(book){
		$scope.comment.book = book.id;
		
		if (!book.bookComments){  //FIXME: SACAR ESTO
			book.bookComments = [];  
		}
		
		var jsonComment = angular.toJson($scope.comment);
		$http.post('/Tpl/rest/comments', jsonComment).success(function(data, status, headers, config){
    		if(status == 200){
    			console.log("Comment Creation Completed.");
    		}
    	}).error(function(data, status, headers, config){
    		console.log("An Error occurred while trying to store a comment");
    	}) ;
		
		book.bookComments.push($scope.comment); //FIXME: SACAR ESTO
		$scope.loadBooks();
		
		$scope.comment = {};
	};
	
	$scope.limpiarComentarios = function(){
		$scope.comment = {};
	};
	
	});	
});