booksApp.controller('bookListCtrl', function ($scope,$location,$rootScope,$http) {
	
	$scope.books = [];
	
	$scope.loadBooks = function(){
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
		}).error(function(data, status, headers, config){
			console.log("An Error occurred while trying to load the books");
		});		
	};
	
	$scope.loadBooks();
	
});