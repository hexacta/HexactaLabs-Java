booksApp.controller('lendBookCtrl', function ($scope, $location, $rootScope, $routeParams, $http) {
	
	var bookId = $routeParams.bookId;
	$scope.newLoan = {};
	
	$scope.backToHome = function(){
    	$location.path("/");
    };
    $scope.loadUsers = function(){
		$http({
			method : 'GET',
			url: '/Tpl/rest/users',
			headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
		}).success(function(data, status, headers, config){
			if(status == 200)
			{
				$scope.users = data;
			}
		}).error(function(data, status, headers, config){
			console.log("An Error occurred while trying to get all users");
		});
	}
	$scope.loadUsers();
	
	$scope.loadBook = function(){
		$http({
			method : 'GET',
			url : '/Tpl/rest/books/' + bookId,
			headers : {
				'Content-type' : 'application/json',
				'Accept' : 'application/json'
			}
		}).success(function(data, status, headers, config) {
			if (status == 200) {
				$scope.currentBook = data;
			}
		}).error(function(data, status, headers, config){
			console.log("An Error occurred while trying to get book:" + bookId);
		});
	}
	$scope.loadBook();
	
	$scope.loadFreeCopy = function(){
		$http({
			method: 'GET',
			url : '/Tpl/rest/copies/book/' + bookId,
			headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
		}).success(function(data, status, headers, config){
			if (status == 200){
				$scope.newLoan.book = data;
			}
		}).error(function(data, status, headers, config){
			console.log("An error ocurred while trying to get free copy from book: " + bookId);
		});
	}
	$scope.loadFreeCopy();
	
	$scope.saveLoan = function(newLoan){
		saveDate(newLoan);
		var jsonLoan = angular.toJson(newLoan);
		$http.post('/Tpl/rest/loans', jsonLoan).success(
				function(data, status, headers, config){
					if(status == 200){
						$scope.backToHome();
					}
				}).error(function(data,status,headers,config){
					console.log("An error ocurred while trying to make the Loan ");
				});
	};
	
	saveDate = function(loan){
		loan.fromDate = $scope.fromDate;
		loan.toDate = $scope.toDate;
	};

	$scope.selectAction = function(loan){
		$scope.newLoan.user = $scope.user;
	}
	$scope.selectedDate = function(){
		
	};
	$scope.today = function() {
		$scope.fromDate = new Date();
	};
	$scope.today();

	$scope.clear = function () {
		$scope.fromDate = null;
		$scope.toDate = null;
	};

	$scope.openFrom = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.openedFrom = true;
	};

	$scope.openUntil = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.openedUntil = true;
	};

	$scope.dateOptions = {
		formatYear: 'yy',
		startingDay: 1
	};

	 $scope.initDate = new Date('2016-15-20');
	 $scope.format = 'dd/MM/yyyy';
});

