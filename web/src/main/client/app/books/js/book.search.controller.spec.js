describe('book controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.books');
    });

    it('search is defined', inject(function ($controller, $rootScope) {
        // given
        var $scope = $rootScope.$new();
        // when
        $controller('BookSearchController', {$scope: $scope});
        // then
        expect($scope.search).toBeDefined();
    }));
});
