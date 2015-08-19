angular.module('app.books').controller('BookSaveController', function ($scope, bookSaveService, Flash) {
    'use strict';

    $scope.gridOptions = { data: 'books' };
   $scope.id=0;
    $scope.title='';
    $scope.authorFirstName='';
    $scope.authorLastName='';
   $scope.save = function () {
    	var authors=$scope.authorFirstName;
    	authors+=' ';
    	authors+=$scope.authorLastName;
    	bookSaveService.save({'id':$scope.id, 'title':$scope.title, 'authors':authors}).then(function () {
            Flash.create('success', 'Książka została dodana.', 'custom-class');
        });
    };
    //bookSaveService.save({"id":$scope.id, "title":$scope.title, "authors":authors})
});
