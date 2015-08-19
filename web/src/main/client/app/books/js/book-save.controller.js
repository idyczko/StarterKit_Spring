angular.module('app.books').controller('BookSaveController', function ($scope, $window, $location, bookSaveService, Flash) {
    'use strict';

    $scope.gridOptions = { data: 'books' };
    
    $scope.save = function (bookTo) {
        bookSaveService.save(bookTo).then(function () {
            Flash.create('success', 'Książka została dodana.', 'custom-class');
        });
    };


});
