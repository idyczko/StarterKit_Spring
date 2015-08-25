angular.module('app.books').controller(
		'BookSearchController',
		function($scope, $window, $location, bookService, bookSaveService, Flash, $modal) {
			'use strict';

			$scope.books = [];
			$scope.prefix = '';
			$scope.gridOptions = {
				data : 'books'
			};
			
			var instantiateModal=function(){return $modal.open({
				templateUrl : 'books/html/update-modal.html',
				controller : 'UpdateModalController',
				size : '0.5g'
			});};
			
			var removeBookById = function(bookId) {
				for (var i = 0; i < $scope.books.length; i = i + 1) {
					if ($scope.books[i].id === bookId) {
						$scope.books.splice(i, 1);
						break;
					}
				}
			};

			$scope.search = function() {
				bookService.search($scope.prefix).then(function(response) {
					angular.copy(response.data, $scope.books);
				}, function() {
					Flash.create('danger', 'Wyjątek', 'custom-class');
				});
			};

			$scope.deleteBook = function(bookId) {
				bookService.deleteBook(bookId).then(
						function() {
							removeBookById(bookId);
							Flash
									.create('success',
											'Książka została usunięta.',
											'custom-class');
						});
			};

			
			$scope.update = function(book) {
				instantiateModal().result.then(function(result) {
						var bookToSave={id: book.id, title: result, authors: book.authors};
						bookSaveService.save(bookToSave).then(function() {
							book.title=bookToSave.title;
							Flash.create('success',
									'Książka została pomyślnie zaktualizowana.',
									'custom-class');
						}, function() {
							Flash.create('danger', 'Wyjątek', 'custom-class');
						} );
				});
				
			};

			$scope.addBook = function() {
				$location.url('/books/add-book');
			};

		});
