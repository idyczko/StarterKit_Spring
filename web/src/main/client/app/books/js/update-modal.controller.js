angular.module('app.books').controller('UpdateModalController', function($scope, $modalInstance) {
	'use strict';
	$scope.newTitle='';

	$scope.confirm = function() {	
		$modalInstance.close($scope.newTitle);
	};
});