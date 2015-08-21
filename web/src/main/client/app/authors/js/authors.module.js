angular.module('app.authors', ['ngRoute']).config(function ($routeProvider) {
    'use strict';
    $routeProvider.when('/authors/author-list', {
        templateUrl: 'authors/html/author-list.html',
        controller: 'AuthorSearchController'
    });
});