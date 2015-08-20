angular.module('app.books').controller(
		'BookSaveController',
		function($scope, bookSaveService, Flash, $modal) {
			'use strict';

			$scope.gridOptions = {
				data : 'books'
			};
			$scope.id = 0;
			$scope.title = '';
			$scope.authors = [];
			$scope.addAuthor = function() {
				$modal.open({
					templateUrl : 'books/html/modal-dialog.html',
					controller : 'BookModalController',
					size : '0.5g'
				}).result.then(function(result) {
					if (result.firstName !== '' && result.lastName !== '') {
						$scope.authors.push({
							'id' : result.id,
							'firstName' : result.firstName,
							'lastName' : result.lastName
						});
					}
				});
			};
			$scope.removeAuthor = function(authorId) {
				for (var i = 0; i < $scope.authors.length; i = i + 1) {
					if ($scope.authors[i].id === authorId) {
						$scope.authors.splice(i, 1);
						break;
					}
				}
			};
			$scope.save = function() {
				if ($scope.authors.length !== 0) {
					bookSaveService.save({
						'id' : $scope.id,
						'title' : $scope.title,
						'authors' : $scope.authors
					}).then(
							function() {
								Flash.create('success',
										'Książka została pomyślnie dodana.',
										'custom-class');
							});
				} else {
					Flash.create('danger', 'Nie dodałeś żadnego autora!',
							'custom-class');
				}
			};
		});
