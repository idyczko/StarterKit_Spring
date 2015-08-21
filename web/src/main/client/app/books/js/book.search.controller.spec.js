describe('book controller', function () {
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

    it('search is defined', inject(function ($controller) {
        // when
        $controller('BookSearchController', {$scope: $scope});
        // then
        expect($scope.search).toBeDefined();
    }));

    it('delete book should call bookService.deleteBook', inject(function ($controller, $q, bookService, Flash) {
        // given
        $controller('BookSearchController', {$scope: $scope});

        var bookId = 1;
        $scope.books = [{id: bookId, title: 'test'}];
        var deleteDeferred = $q.defer();
        spyOn(bookService, 'deleteBook').and.returnValue(deleteDeferred.promise);
        spyOn(Flash, 'create');
        // when
        $scope.deleteBook(bookId);
        deleteDeferred.resolve();
        $scope.$digest();
        // then
        expect(bookService.deleteBook).toHaveBeenCalledWith(bookId);
        expect(Flash.create).toHaveBeenCalledWith('success', 'Książka została usunięta.', 'custom-class');
        expect($scope.books.length).toBe(0);
    }));
    
    it('search should call bookService.search', inject(function ($controller, $q, bookService) {
    	// given
    	$controller('BookSearchController', {$scope: $scope});
    	
    	$scope.prefix='test';
    	var searchDeferred = $q.defer();
    	//searchDeferred.promise=[];
    	spyOn(bookService, 'search').and.returnValue(searchDeferred.resolve([{id:3, title:'test', authors:[{id:2, firstName:'test', lastName:'test'}]}]));
    	//spyOn(bookService, 'search').and.callThrough();
    	// when
    	$scope.search();
    	searchDeferred.resolve();
    	$scope.$digest();
    	// then
    	expect(bookService.search).toHaveBeenCalledWith('test');
    	expect($scope.books.length).toBe(0);
    }));
});
