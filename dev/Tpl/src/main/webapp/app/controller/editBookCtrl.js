booksApp.controller('editBookCtrl', function($scope, $location, $rootScope,
		$routeParams, $http) {
	$scope.books = $rootScope.books;
	
	$http({
		method : 'GET',
		url: '/Tpl/rest/categories',
		headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
	}).success(function(data, status, headers, config){
		if(status == 200){
			$scope.categories = [];
			$scope.categories = data;
			for (var i = 0; i < $scope.categories.length; i++){
				$scope.categories[i].selected = false;
			}
		}
	}).error(function(data, status, headers, config){
		console.log("An Error occurred while trying to get all categories");
	});
	
	$scope.backToHome = function() {
		$location.path("/");
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

		if (status == 200) {
			$scope.currentBook = data;
			for (var i = 0; i < $scope.currentBook.bookCategories.length; i++){
				$scope.categories[$scope.currentBook.bookCategories[i].id -1].selected = true;
			}
		}
	}).error(function(data, status, headers, config){
		console.log("An Error occurred while trying to get book:" + $scope.bookId);
	});
    
    $scope.save = function(aBook) {
    	for (var i = 0; i < aBook.bookCategories.length; i++){
			delete aBook.bookCategories[i].selected;
		}
    	
       	var jsonBook = angular.toJson(aBook);
       	$http.put('/Tpl/rest/books/'+$scope.bookId, jsonBook).success(function(data, status, headers, config){
       		if(status == 200)
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
    
    $scope.toggleSelection = function(category, book){
		if (!book.bookCategories){
			book.bookCategories = [];
		}
		var selected = !category.selected; //it's toggling, so the new state it's the opposite to the current state  
		
		if (selected){
			book.bookCategories.push(category);
		}else{
			var index = -1;
			
			for (var i = 0; i < book.bookCategories.length; i++){
				if (book.bookCategories[i].id == category.id){
					index = i;
				}
			} // este else es el que guarda si se esta desseleccionando una categoría
//			var index = book.bookCategories.indexOf(category);
			
			if (index > -1){
				book.bookCategories.splice(index,1);
			}
		}
	};
});