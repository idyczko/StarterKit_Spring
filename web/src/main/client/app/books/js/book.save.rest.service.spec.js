describe('book save rest service', function () {
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

    it('save is defined', inject(function (bookSaveRestService) {
        // then
        expect(bookSaveRestService.save).toBeDefined();
    }));

    
    it('save should send post request to http', inject(function ($httpBackend, bookSaveRestService) {
        // given
    	var bookToSave = {id: 1, title: 'test', authors: [{firstName:'test', lastName:'test'}]};
    	var httpBackend = $httpBackend;
    	httpBackend.expect('POST', '/context.html/rest/books/book', bookToSave).respond(200, {data: bookToSave});

        // when
    	var savePromise = bookSaveRestService.save(bookToSave);
    	httpBackend.flush();
    	
        // then
    	savePromise.then(function(response) {
    		expect(response.status).toEqual(200);
    		expect(response.data).toEqual(bookToSave);  		
    	});
    }));

});
