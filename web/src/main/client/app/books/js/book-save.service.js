angular.module('app.books').factory('bookSaveService', function (bookSaveRestService) {
    'use strict';

    return {
        save: function (bookTo) {
            return bookSaveRestService.save(bookTo);
        }
    };
});
