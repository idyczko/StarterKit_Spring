describe('authors filter test', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.myCustomFilters');
    });

    var $filter;
    
    beforeEach(inject(function (_$filter_) {
        $filter = _$filter_;
    }));
  
    it('filter should convert json table into the string', inject(function () {
        // given
        var authorsInTable=[{firstName:'test', lastName:'test'}, {firstName:'test', lastName:'test'}];
        
        // when
        var result=$filter('authors')(authorsInTable);     
        
        // then
        expect(result).toEqual('test test, test test');
    }));

});
