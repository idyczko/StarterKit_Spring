describe('update modal controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.books');
    });

    var $scope;
    var modalInstance = { close: function() {}, dismiss: function() {} };
    
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
    }));
  
    it('confirm is defined', inject(function ($controller) {
    	// when
    	$controller('UpdateModalController', {$scope: $scope, $modalInstance: modalInstance});
    	// then
    	expect($scope.confirm).toBeDefined();
    }));

    it('confirm should call $modalInstance.close', inject(function ($controller) {
        // given
        $controller('UpdateModalController', {$scope: $scope, $modalInstance: modalInstance});
        $scope.newTitle='test';   
        spyOn(modalInstance, 'close');
        
        // when
        $scope.confirm();
        
        // then
        expect(modalInstance.close).toHaveBeenCalledWith('test');
    }));
     
});
