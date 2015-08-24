angular.module('app.books').controller(
		'BookSaveController',
		function($scope, bookSaveService, Flash, $modal) {
			'use strict';

			$scope.gridOptions = {
				data : 'books'
			};
			$scope.title = '';
			$scope.authors = [];
			$scope.instantiateModal = function() {
				return $modal.open({
					templateUrl : 'books/html/modal-dialog.html',
					controller : 'BookModalController',
					size : '0.5g'
				});
			};

			$scope.addAuthor = function() {
				$scope.instantiateModal().result.then(function(result) {
					$scope.authors.push({
						'firstName' : result.firstName,
						'lastName' : result.lastName
					});
				});
			};

			$scope.removeAuthor = function(authorIndex) {
				for (var i = 0; i < $scope.authors.length; i = i + 1) {
					if (i === authorIndex) {
						$scope.authors.splice(i, 1);
						break;
					}
				}
			};
			$scope.save = function() {
				if ($scope.authors.length !== 0) {
					bookSaveService.save({
						'title' : $scope.title,
						'authors' : $scope.authors
					}).then(
							function() {
								Flash.create('success',
										'Książka została pomyślnie dodana.',
										'custom-class');
							},
							function() {
								Flash.create('danger', 'Wyjątek',
										'custom-class');
							});
				} else {
					Flash.create('danger', 'Nie dodałeś żadnego autora!',
							'custom-class');
				}
			};
		});
