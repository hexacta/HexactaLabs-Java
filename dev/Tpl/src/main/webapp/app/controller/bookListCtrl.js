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
		
		$rootScope.books = [];
		$rootScope.books = data;
		$scope.books = $rootScope.books;

		$scope.modifyModal=function(book){
			$scope.selectedBook = book;
			$scope.selectedBook.image = "";
			
			
			//Getting comments
			var bookId = $scope.selectedBook.id;
			$http({
				method : 'GET',
				url: '/Tpl/rest/comments/byBook/' + bookId,
				headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
				}).success(function(data, status, headers, config){
					if (status == 200 ){
						$scope.selectedBook.bookComments = data;
					}
				}).error(function(data, status, headers, config){
		    		console.log("An Error occurred while trying to get comments for the book " + $scope.selectedBook.name);
		    		$scope.selectedBook.comments = "";
		    	}) ;
			
			//image from google rest service
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
		
		$scope.comment = {};
		$scope.addComment = function(book){
			var comments = book.bookComments;
			delete book.bookComments;
			
			$scope.comment.book = book;
			var image = $scope.selectedBook.image;
			delete $scope.selectedBook.image;
				
			var jsonComment = angular.toJson($scope.comment);
			console.log(jsonComment);
			$http.post('/Tpl/rest/comments', jsonComment).success(function(data, status, headers, config){
	    		if(status == 201){
	    			console.log("Comment Creation Completed.");
	    		}
	    	}).error(function(data, status, headers, config){
	    		console.log("An Error occurred while trying to store a comment");
	    	}) ;
			book.bookComments = comments;
			$scope.selectedBook.image = image;
			book.bookComments.push($scope.comment); //FIXME: SACAR ESTO
			$scope.comment = {};
		};
		
		$scope.limpiarComentarios = function(){
			$scope.comment = {};
		};
	}).error(function(data, status, headers, config){
		console.log("Error getting the data");
	});

	$scope.actionsAllowed = function(){
		return sessionStorage.loggedIn;
	};
	
});