angular.module('app.authors').controller(
		'AuthorSearchController',
		function($scope, authorService, Flash) {
			'use strict';

			$scope.gridOptions = {
					data : 'authors'
			};
			$scope.authors = [];

			$scope.search = function() {
				authorService.search().then(function(response) {
					angular.copy(response.data, $scope.authors);
				}, function() {
					Flash.create('danger', 'Wyjątek', 'custom-class');
				});
			};
			
			$scope.search();

			$scope.check = function(actual, expected) {
				var firstName = actual.firstName;
				var lastName = actual.lastName;
				var phrase = expected;
				firstName += '';
				lastName += '';
				phrase += '';
				if (firstName.toLowerCase().substring(0, phrase.length) === phrase.toLowerCase() || lastName.toLowerCase().substring(0, phrase.length) === phrase.toLowerCase()) {
					return true;
				}
				return false;
			};

		});
