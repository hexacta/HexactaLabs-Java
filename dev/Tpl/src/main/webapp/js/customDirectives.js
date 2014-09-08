booksApp.directive('registerForm', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/registerForm.html',
		link : function(scope, el, attrs) {
			scope.onDoneClick = function() {
				if (scope.newUser.pass1 == scope.newUser.pass2) {
					// Valido
					scope.newUser = {};
					scope.registerForm.$setPristine();
					scope.$destroy();
				} else {
					// Invalido
					scope.newUser.pass1 = "";
					scope.newUser.pass2 = "";
				}

			};
		}
	};
});

booksApp.directive('userLogin',function() {
	return {
		restrict: 'E',
		templateUrl: 'app/views/loginView.html',
		controller: ['$scope', '$http', '$location', function($scope, $http, $location) {
		
			$scope.user = {};	
	
			$scope.validateUser = function(aUser) {
				  			  
				var isValid = test(aUser.name, aUser.pass);
					  
				if( isValid == true ) {
					var jsonUser = angular.toJson(aUser);
					$http.post('/Tpl/rest/login', jsonUser).success(
							function(data, status, headers, config) {
								if (status = 200) {
									// Ok message and go back
									console.log("Creation Completed.\nUser name: "
											+ $scope.user.name);
									$location.path("/");
								}
							}).error(function(data, status, headers, config) {
						console.log("An Error occurred while trying to store the user:" + jsonUser);
					});
				}
				else {
					console.log("Usuario o contraseña invalida");  
				}
						  
			}
				
			function test(name, pass) {
				
				pass = (typeof pass === 'undefined') ? ' ' : pass;
				
				var nameRegex = /^[A-Z][A-Z0-9]{2,15}$/i; //nombre de usuario empieza con una letra de 3 a 16 caracteres y puede tener numeros
				var passRegex = /^[A-Z0-9][A-Z0-9]{5,11}$/i; //password de 6 a 12 caracteres y puede tener numeros.

				if( (nameRegex.test(name) == true) && (passRegex.test(pass) == true) ) {
					return(true);
				}
				else {
					$scope.user = {};
					return(false);
				}
			}

		}]
	}
});