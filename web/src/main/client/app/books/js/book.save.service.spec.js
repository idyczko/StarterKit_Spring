describe('book save service', function () {
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

    it('save is defined', inject(function (bookSaveService) {
        // then
        expect(bookSaveService.save).toBeDefined();
    }));

    
    it('save should call bookRestSaveService.save', inject(function (bookSaveService, bookSaveRestService) {
        // given
        var bookToSave = {id: 1, title: 'test', authors: [{firstName:'test', lastName:'test'}]};
        
        spyOn(bookSaveRestService, 'save').and.returnValue(bookToSave);
        
        // when
        bookSaveService.save(bookToSave);
        
        // then
        expect(bookSaveRestService.save).toHaveBeenCalledWith(bookToSave);
    }));

});
