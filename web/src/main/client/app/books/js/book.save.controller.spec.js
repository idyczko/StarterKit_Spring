describe('book save controller', function () {
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

    it('save is defined', inject(function ($controller) {
        // when
        $controller('BookSaveController', {$scope: $scope});
        // then
        expect($scope.save).toBeDefined();
    }));
   
    it('addAuthor is defined', inject(function ($controller) {
    	// when
    	$controller('BookSaveController', {$scope: $scope});
    	// then
    	expect($scope.addAuthor).toBeDefined();
    }));
   
    it('removeAuthor is defined', inject(function ($controller) {
    	// when
    	$controller('BookSaveController', {$scope: $scope});
    	// then
    	expect($scope.removeAuthor).toBeDefined();
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

    it('save should cause flash allert if promise was rejected', inject(function ($controller, $q, bookSaveService, Flash) {
    	// given
    	$controller('BookSaveController', {$scope: $scope});
    	
    	$scope.title='test';
    	$scope.authors=[{firstName:'test', lastName:'test'}];
    	var saveDeferred = $q.defer();
    	spyOn(bookSaveService, 'save').and.returnValue(saveDeferred.promise);
    	spyOn(Flash, 'create');
    	// when
    	$scope.save();
    	saveDeferred.reject();
    	$scope.$digest();
    	// then
    	expect(bookSaveService.save).toHaveBeenCalledWith({title:$scope.title, authors:$scope.authors});
    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjątek',
		'custom-class');
    }));
 
    it('save should cause flash allert if authors table is empty', inject(function ($controller, $q, Flash) {
    	// given
    	$controller('BookSaveController', {$scope: $scope});
    	
    	$scope.authors=[];
    	var saveDeferred = $q.defer();
    	spyOn(Flash, 'create');
    	// when
    	$scope.save();
    	saveDeferred.resolve();
    	$scope.$digest();
    	// then

    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Nie dodałeś żadnego autora!',
		'custom-class');
    }));

    it('addAuthor should add new object to authors table', inject(function ($controller, $q, $modal) {
    	// given
    	$controller('BookSaveController', {$scope: $scope});
    	
    	$scope.authors=[];
    	var author ={firstName:'test', lastName:'test'};
    	spyOn($modal, 'open').and.returnValue(fakeModal);
    	// when
    	$scope.addAuthor();
    	fakeModal.close(author);
    	$scope.$digest();
    	// then
    	expect($scope.authors.length).toBe(1);
    }));
 
    it('removeAuthor should remove author from authors table', inject(function ($controller) {
    	// given
    	$controller('BookSaveController', {$scope: $scope});
    	$scope.authors=[{firstName:'test', lastName:'test'}];

    	// when
    	$scope.removeAuthor(0);
    	// then
    	expect($scope.authors.length).toBe(0);
    }));
});