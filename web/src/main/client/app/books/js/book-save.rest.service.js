angular.module('app.books').factory('bookSaveRestService', function ($http, currentContextPath) {
    'use strict';

    return {
        save: function (bookTo) {
            return $http.post(currentContextPath.get() + 'rest/save', bookTo);
        }
    };
});
