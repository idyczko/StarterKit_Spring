describe('save controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.books');
    });

    var $scope;
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
    }));

    it('save is defined', inject(function ($controller) {
        // when
        $controller('BookSaveController', {$scope: $scope});
        // then
        expect($scope.save).toBeDefined();
    }));
    
    it('save should call bookSaveService.save', inject(function ($controller, $q, bookSaveService, Flash) {
        // given
        $controller('BookSaveController', {$scope: $scope});

        $scope.title='test';
        $scope.authors=[{firstName:'test', lastName:'test'}];
        var saveDeferred = $q.defer();
        spyOn(bookSaveService, 'save').and.returnValue(saveDeferred.promise);
        spyOn(Flash, 'create');
        // when
        $scope.save();
        saveDeferred.resolve();
        $scope.$digest();
        // then
        expect(bookSaveService.save).toHaveBeenCalledWith({title:$scope.title, authors:$scope.authors});
        expect(Flash.create).toHaveBeenCalledWith('success', 'Książka została pomyślnie dodana.', 'custom-class');
    }));

});
