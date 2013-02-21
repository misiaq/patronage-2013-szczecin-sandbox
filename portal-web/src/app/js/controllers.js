'use strict';

/* Controllers */

wwwApp.controller('PlayersCtrl', function($scope) {
	
	//@TODO
	$scope.players = [ {
		'name' : '@TODO'
	}, {
		'name' : '@TODO'
	}, {
		'name' : '@TODO'
	} ];

})


wwwApp.controller('MainCtrl', function($scope, $http, $templateCache, $location, $timeout) {
	
	// global config
	$scope.server_host = 'https://capturetheflag.blstream.com:8080/demo'
	$scope.login_url = $scope.server_host + '/oauth/token';
	$scope.client_id = 'web_www';
	$scope.client_secret = 'secret';
	$scope.grant_type = 'password';
	
	$scope.SignIn = function() {
		/*
		 * $http({ method : "POST", url : login_url, headers : { "Content-Type" :
		 * "application/x-www-form-urlencoded; charset=UTF-8" }, data :
		 * $.param({ client_id : client_id, client_secret : client_secret,
		 * grant_type : grant_type, // username : $scope.username, // password :
		 * $scope.password username : "michal.krawczak@blstream.com", password :
		 * "FKA13#aqFR3" })
		 * 
		 * }).success(function(data, status, headers, config) { alert('success ' +
		 * data) }).error(function(data, status, headers, config) {
		 * alert('failure =>' + data) });
		 */
		$.ajax({
			type : "POST",
			beforeSend : function(request) {
				request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			},
			url : $scope.login_url,
			data : {
				client_id : $scope.client_id,
				client_secret : $scope.client_secret,
				grant_type : $scope.grant_type,
				username : $scope.username,
				password : $scope.password
			},
			success : function(authData) {
				var data = $.parseJSON(authData)
				$scope.setUserStatus( data.access_token, true, $scope.username );	
		    	$timeout(function(e){$location.path('/')}, 100)
				
			},
			error : function(errorData) {
				var response = errorData.responseText;
				var error = $.parseJSON(response);
				var reason = error.error_description;
				alert("Error: " + reason);
			}
		});
	}

});

function UserRegisterCtrl($scope, $routeParams, $cookies) {

}
