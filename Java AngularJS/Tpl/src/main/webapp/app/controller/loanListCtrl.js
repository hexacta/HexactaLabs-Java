booksApp.controller('loanListCtrl', function ($scope,$location,$rootScope,$http) {
	
	$scope.linkToRegister=function(){
		$location.path("/register");
	};

	$http({
		method : 'GET',
		url: '/Tpl/rest/loans',
		headers : {'Content-type' : 'application/json', 'Accept' : 'application/json'}
	}).success(function(data, status, headers, config){
		$rootScope.loans = [];
		$rootScope.loans = data;
		$scope.loans = $rootScope.loans;
		$scope.predicate = 'title';
		$scope.reverse = false;
	}).error(function(data, status, headers, config){
		console.log("An Error occurred while trying to load the loans");
	});	
		
});