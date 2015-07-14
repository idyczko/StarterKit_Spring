angular.module('app.books', ['ngRoute']).config(function ($routeProvider) {
    'use strict';
    $routeProvider.when('/books/book-list', {
        templateUrl: 'books/html/book-list.html',
        controller: 'BookSearchController'
    });
    $routeProvider.when('/books/add-book', {
        templateUrl: 'books/html/add-book.html'
    });
});