booksApp.controller('createBookCtrl', function($scope, $location, $rootScope,
		$http) {
	
	$scope.categories = [
	                 { id: 1, name: 'eBook', descripcion: 'Libro en formato digital'},
	                 { id: 2, name: 'Fisico', descripcion: 'Libro en formato fisico'}
	               ];
	$scope.selection = [];
	$scope.selectedCategory = function selectedCategory() {
	      return filterFilter($scope.category, { selected: true });
	    };
	
    $scope.$watch('categories|filter:{selected:true}', function (nv) {
        $scope.selection = nv.map(function (category) {
          return category;
        });
      }, true);
	    

	$scope.save = function(aBook) {
		var jsonBook = angular.toJson(aBook);
		$http.post('/Tpl/rest/books', jsonBook).success(
				function(data, status, headers, config) {
					if (status == 200) {
						// Ok message and go back
						console.log("Creation Completed.\n");
						$location.path("/");
					}
				}).error(function(data, status, headers, config) {
			console.log("An Error occurred while trying to store a book");
		});
	};

	$scope.reset = function() {
		$scope.newBook = {};
	};

	$scope.reset();

	$scope.backToHome = function() {
		$location.path("/");
	};

});