booksApp.controller('bookListCtrl', function ($scope,$location,$rootScope,$http) {
	
	$scope.books = $rootScope.books === undefined? [] : $rootScope.books;
	
	$scope.loadBooks = function(){
		$http({
			method : 'GET',
			url: 'http://localhost:4515/api/Books',
			headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
		}).success(function(data, status, headers, config){
			$rootScope.books = [];
			$rootScope.books = data;
			$scope.books = $rootScope.books;
			$scope.predicate = 'name';
			$scope.reverse = false;
		}).error(function(data, status, headers, config){
			console.log("An Error occurred while trying to load the books");
		});		
	};
	
	//$scope.loadBooks();
	
	$scope.linkToEditBook=function(bookId){
		$location.path("/editbook/" + bookId);
	};
	
	$scope.linkToCreateBook=function(){
		$location.path("/createbook");
	};
	
	$scope.books = [
		{
			title: 'el principito',
			publisher: 'alguno',
			description: 'description',
			genre: 'Fable',
			id: 1
		}
	];
	$rootScope.books = $scope.books;
});

