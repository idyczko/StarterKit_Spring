angular.module('app.authors').controller(
		'AuthorSearchController',
		function($scope, $window, $location, authorService, Flash) {
			'use strict';

			$scope.authors = [];
			$scope.gridOptions = {
				data : 'authors'
			};

			var removeAuthorById = function(authorId) {
				for (var i = 0; i < $scope.authors.length; i = i + 1) {
					if ($scope.authors[i].id === authorId) {
						$scope.authors.splice(i, 1);
						break;
					}
				}
			};

			$scope.search = function() {
				authorService.search().then(function(response) {
					angular.copy(response.data, $scope.authors);
				}, function() {
					Flash.create('danger', 'Wyjątek', 'custom-class');
				});
			};
			$scope.search();
			$scope.deleteAuthor = function(authorId) {
				authorService.deleteAuthor(authorId).then(
						function() {
							removeAuthorById(authorId);
							Flash.create('success', 'Autor został usunięty.',
									'custom-class');
						});
			};

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
