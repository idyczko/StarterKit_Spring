describe('author controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.authors');
    });

    var $scope;
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
    }));

    it('author search is defined', inject(function ($controller) {
        // when
        $controller('AuthorSearchController', {$scope: $scope});
        // then
        expect($scope.search).toBeDefined();
    }));
  
    it('author check is defined', inject(function ($controller) {
    	// when
    	$controller('AuthorSearchController', {$scope: $scope});
    	// then
    	expect($scope.check).toBeDefined();
    }));

    it('search should be called automatically when controller starts', inject(function ($controller, $q, authorService) {
        // given
    	var searchDeferred = $q.defer();    	
    	spyOn(authorService, 'search').and.returnValue(searchDeferred.promise);
    
    	$controller('AuthorSearchController', {$scope: $scope});

        // when
        searchDeferred.resolve({data:[{id:2, firstName:'test', lastName:'test'}]});
        $scope.$digest();
        
        // then
        expect(authorService.search).toHaveBeenCalled();
        expect($scope.authors.length).toBe(1);
    }));
   
    it('search should cause flash allert for promise rejection', inject(function ($controller, $q, authorService, Flash) {
    	// given
    	var searchDeferred = $q.defer();
    	spyOn(authorService, 'search').and.returnValue(searchDeferred.promise);
   
    	$controller('AuthorSearchController', {$scope: $scope});

    	spyOn(Flash, 'create');
    	
    	// when
    	searchDeferred.reject();
    	$scope.$digest();
    	
    	// then
    	expect(authorService.search).toHaveBeenCalled();
    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjątek', 'custom-class');
    	expect($scope.authors.length).toBe(0);
    }));
    
    it('check should return true', inject(function ($controller, $q, authorService) {
    	// given
    	var searchDeferred = $q.defer();
    	spyOn(authorService, 'search').and.returnValue(searchDeferred.promise);
    	$controller('AuthorSearchController', {$scope: $scope});
    	var author = {fistName: 'test', lastName: 'test'};
    	
    	// when
    	
    	// then
    	expect($scope.check(author, 'test')).toEqual(true);
    }));
   
    it('check should return false', inject(function ($controller, $q, authorService) {
    	// given
    	var searchDeferred = $q.defer();
    	spyOn(authorService, 'search').and.returnValue(searchDeferred.promise);
    	$controller('AuthorSearchController', {$scope: $scope});
    	var author = {fistName: 'test', lastName: 'test'};
    	
    	// when
    	
    	// then
    	expect($scope.check(author, 'some')).toEqual(false);
    }));
 
    
});
