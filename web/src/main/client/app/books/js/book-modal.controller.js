angular.module('app.books').controller('BookModalController', function($scope, $modalInstance) {
	'use strict';
	$scope.firstName = '';
	$scope.lastName = '';
	
	$scope.confirm = function() {	
		$modalInstance.close({'firstName':$scope.firstName, 'lastName':$scope.lastName});
	};
});