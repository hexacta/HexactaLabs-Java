booksApp.controller('createBookCtrl', function($scope, $location, $rootScope,
		$http) {
	
	$scope.pageTitle = 'Crear Nuevo Libro';
	
	$http({
		method : 'GET',
		url: '/Tpl/rest/categories',
		headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
	}).success(function(data, status, headers, config){
		if(status == 200){
			$scope.categories = [];
			$scope.categories = data;
		}
	}).error(function(data, status, headers, config){
		console.log("An Error occurred while trying to get all categories");
	});
	
	//This function saves the new book manually created by the admin into the database, but first
	//it checks the validity of the data input in the form.
	$scope.save = function(aBook) {
		$scope.trySubmit = true;
		
		if ($scope.createBookForm.$invalid) {
			return false;
		}		
		
		for (var i = 0; i < aBook.bookCategories.length; i++){
			delete aBook.bookCategories[i].selected;
		}
		var jsonBook = angular.toJson(aBook);
		$http.post('/Tpl/rest/books', jsonBook).success(
				function(data, status, headers, config) {

					if (status == 201) {
						$location.path("/");
					}
				}).error(function(data, status, headers, config) {
			console.log("An Error occurred while trying to store a book");
		});
	};

	$scope.reset = function() {
		$scope.book = {};
	};

	$scope.reset();

	$scope.backToHome = function() {
		$location.path("/");
	};
	
	$scope.toggleSelection = function(category, book){
		if (!book.bookCategories){
			book.bookCategories = [];
		}
		var selected = !category.selected; //it's toggling, so the new state it's the opposite to the current state  
		
		if (selected){
			book.bookCategories.push(category);
		}else{
			var index = book.bookCategories.indexOf(category);
			if (index > -1){
				book.bookCategories.splice(index,1);
			}
		}

	};

});