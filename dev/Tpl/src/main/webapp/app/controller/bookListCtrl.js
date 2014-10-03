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

		//Modal presentation
		$scope.modifyModal=function(book){
			
			//Set current book as the selected one
			$scope.selectedBook = book;
			$scope.selectedBook.image = "";
			
			//Getting comments
			$scope.getBookComments(book);

			// Get image from google rest service
			$scope.getImageFromGoogleRestService(book)
		};
	
		//Add comments to the book (and the modal)
		$scope.addComment = function(book){
			var comments = book.bookComments;
			delete book.bookComments;
			
			$scope.comment.book = book;
			var image = $scope.selectedBook.image;
			delete $scope.selectedBook.image;
				
			var jsonComment = angular.toJson($scope.comment);
			
			$http.post('/Tpl/rest/comments', jsonComment).success(function(data, status, headers, config){
	    		if(status == 201){
	    			console.log("Comment Creation Completed.");
	    		}
	    	}).error(function(data, status, headers, config){
	    		console.log("An Error occurred while trying to store a comment");
	    	}) ;
			book.bookComments = comments;
			$scope.selectedBook.image = image;
			
			$scope.comment = {};
			
			$scope.selectedBook.bookComments = comments;
			
		};
	
		$scope.limpiarComentarios = function(){
			$scope.comment = {};
		};
			
		$scope.getBookComments=function(aBook) {
			var bookId = aBook.id;
			$http({
				method : 'GET',
				url: '/Tpl/rest/comments/byBook/' + bookId,
				headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
			}).success(function(data, status, headers, config){
				if (status == 200 ){
					aBook.bookComments = data;
				}
			}).error(function(data, status, headers, config){
	    		console.log("An Error occurred while trying to get comments for the book " + aBook.name);
	    		aBook.comments = "";
	    	});			
			console.log(aBook);
		}
		
		$scope.getImageFromGoogleRestService = function(aBook) {
			var find = '-';
			var re = new RegExp(find, 'g');
			var isbn = aBook.isbn.replace(re , '');
			$http({
				method : 'GET',
				url: 'https://www.googleapis.com/books/v1/volumes?q=isbn+' + isbn,
				headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
				}).success(function(data, status, headers, config){
					if (status == 200 ){
						aBook.image = data.items[0].volumeInfo.imageLinks.thumbnail;
					}
				}).error(function(data, status, headers, config){
		    		console.log("An Error occurred while trying to connect to Google API");
		    		aBook.image = "";
		    	}) ;
		}
			
		//Load books into the scope
		$scope.loadBooks = function(){
			$rootScope.books = [];
			$rootScope.books = data;
			$scope.books = $rootScope.books;
		}
	
		$scope.loadBooks();
		
		$scope.comment = {};
		
		$scope.predicate = 'name';
		$scope.reverse = false;
	
	});
	
});