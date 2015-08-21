angular.module('app', ['ngRoute',  'app.main', 'app.component1', 'app.component2', 'app.books', 'app.myCustomFilters', 'app.authors', 'flash'])
    .config(function ($locationProvider) {
        'use strict';
        $locationProvider.html5Mode(false);
    })
    .value('config', {
        contextPath: 'workshop'
    });