describe('book controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.books');
    });

    var $scope;
    var fakeModal = {
		    result: {
		        then: function(confirmCallback, cancelCallback) {
		            this.confirmCallBack = confirmCallback;
		            this.cancelCallback = cancelCallback;
		        }
		    },
		    close: function( item ) {
		        this.result.confirmCallBack( item );
		    },
		    dismiss: function( type ) {
		        this.result.cancelCallback( type );
		    }
		};
    
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
    }));

    it('search is defined', inject(function ($controller) {
        // when
        $controller('BookSearchController', {$scope: $scope});
        // then
        expect($scope.search).toBeDefined();
    }));
  
    it('deleteBook is defined', inject(function ($controller) {
    	// when
    	$controller('BookSearchController', {$scope: $scope});
    	// then
    	expect($scope.deleteBook).toBeDefined();
    }));
  
    it('update is defined', inject(function ($controller) {
    	// when
    	$controller('BookSearchController', {$scope: $scope});
    	// then
    	expect($scope.update).toBeDefined();
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
    	$scope.books=[];
    	$scope.prefix='test';
    	var booksToReturn = [{id:3, title:'test', authors:[{id:2, firstName:'test', lastName:'test'}]}];
    	var searchDeferred = $q.defer();
    	spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
    	
    	// when
    	$scope.search();
    	searchDeferred.resolve({data: booksToReturn});
    	$scope.$digest();
    	
    	// then
    	expect(bookService.search).toHaveBeenCalledWith('test');
    	expect($scope.books.length).toBe(1);
    }));
   
    it('search should cause flash allert if promise was rejected', inject(function ($controller, $q, bookService, Flash) {
    	// given
    	$controller('BookSearchController', {$scope: $scope});
    	$scope.prefix = 'test'; 
    	$scope.books=[];
    	var searchDeferred = $q.defer();
    	spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
    	spyOn(Flash, 'create');
    	
    	// when
    	$scope.search();
    	searchDeferred.reject();
    	$scope.$digest();
    	
    	// then
    	expect(bookService.search).toHaveBeenCalledWith('test');
    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjątek', 'custom-class');
    	expect($scope.books.length).toBe(0);
    }));
  
    it('update should call bookSaveService.save', inject(function ($controller, $q, bookSaveService, $modal, Flash) {
    	// given
    	
    	$controller('BookSearchController', {$scope: $scope});
        $scope.books = [{id: 1, title: 'test', authors:[{firstName:'test', lastName:'test'}]}];
        var bookToUpdate = {id: 1, title:'test_updated', authors:[{firstName:'test', lastName:'test'}]};
    	var updateDeferred = $q.defer();
    	spyOn(bookSaveService, 'save').and.returnValue(updateDeferred.promise);
    	spyOn($modal, 'open').and.returnValue(fakeModal);
    	spyOn(Flash, 'create');
    	
    	// when
    	$scope.update($scope.books[0]);
    	fakeModal.close(bookToUpdate.title);
    	updateDeferred.resolve();
    	$scope.$digest();
    	
    	// then
    	expect(bookSaveService.save).toHaveBeenCalledWith(bookToUpdate);
    	expect(Flash.create).toHaveBeenCalledWith('success',
				'Książka została pomyślnie zaktualizowana.',
		'custom-class');
    	expect($scope.books[0].title).toBe('test_updated');
    }));
 
    it('update should cause flash allert if promise was rejected', inject(function ($controller, $q, bookSaveService, $modal, Flash) {
    	// given
   	
    	$controller('BookSearchController', {$scope: $scope});
    	$scope.books = [{id: 1, title: 'test', authors:[{firstName:'test', lastName:'test'}]}];
    	var bookToUpdate = {id: 1, title:'test_updated', authors:[{firstName:'test', lastName:'test'}]};
    	var updateDeferred = $q.defer();
    	spyOn(bookSaveService, 'save').and.returnValue(updateDeferred.promise);
    	spyOn($modal, 'open').and.returnValue(fakeModal);
    	spyOn(Flash, 'create');
    	
    	// when
    	$scope.update($scope.books[0]);
    	fakeModal.close(bookToUpdate.title);
    	updateDeferred.reject();
    	$scope.$digest();
    	
    	// then
    	expect(bookSaveService.save).toHaveBeenCalledWith(bookToUpdate);
    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjątek', 'custom-class');
    	expect($scope.books[0].title).toBe('test');
    }));
});
