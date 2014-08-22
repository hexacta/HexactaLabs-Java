booksApp.controller('registerCtrl', function($scope, $location, $rootScope, $http) {

	document.getElementById('user').focus();
	
	//Start of alerts
	//Use for controllers that need alerts
	
	$scope.alerts = [];
	
	$scope.addAlert = function(alertStrongMsg, alertMsg, alertType) {
		if ($scope.alerts.length < 1) {
			$scope.alerts.push({
				strong: alertStrongMsg,
				msg : alertMsg,
				type : alertType
			});
		}
	};

	$scope.closeAlert = function(index) {
		$scope.alerts.splice(index, 1);
	};
	//End of alerts
	
	$scope.save = function(aUser) {
		if ($scope.newUser.password == $scope.password2) {
			var jsonUser = angular.toJson(aUser);
			$http.post('/Tpl/rest/users', jsonUser).success(
					function(data, status, headers, config) {
						if (status == 201) {
							// Ok message and go back
							console.log("User Creation Completed.\n");
							$location.path("/");
						}
					}).error(function(data, status, headers, config) {
				console.log("An Error occurred while trying to create a new user");
			})
		} else {
			$scope.addAlert("Cuidado!","Las password no coinciden.", "danger");
			$scope.newUser.password = "";
			$scope.password2 = "";
			document.getElementById('password').focus();			
		}
	};
	
	$scope.reset = function() {
		$scope.newUser = {};
	};

	$scope.reset();
	
	$scope.backToHome = function() {
		if ($scope.newUser.password == $scope.password2) {
			$location.path("/");
		} else {
			$scope.addAlert("Cuidado!","Las password no coinciden.", "danger");
			$scope.newUser.password = "";
			$scope.password2 = "";
			document.getElementById('password').focus();
		}
	};
});