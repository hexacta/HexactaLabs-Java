var HTTP_CREATE_OK = 201;

booksApp.controller('createBookCtrl', function($scope, $location, $rootScope, $http) {
	
	$scope.pageTitle = 'Crear Nuevo Libro';
	
	//This function saves the new book manually created by the admin into the database, but first
	//it checks the validity of the data input in the form.
	$scope.save = function(aBook) {
		$scope.trySubmit = true;
		
		if ($scope.bookForm.$invalid) {
			return false;
		}		
				
		$http.post('http://localhost:4515/api/Books', jsonBook)
		.success(function(data, status, headers, config) {
			if (status == HTTP_CREATE_OK) {
				$scope.backToHome();
			}
		})
		.error(function(data, status, headers, config) {
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
	
});