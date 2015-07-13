angular.module('book-app', []).controller('TestCtrl', function ($scope, $window, $location, bookService) {

    $scope.books = [];
    $scope.gridOptions = { data: 'books' };
    $scope.prefix = undefined;

    var removeBookById = function (bookId) {
        for (var i = 0; i < $scope.books.length; i = i + 1) {
            if ($scope.books[i].id === bookId) {
                $scope.books.splice(i, 1);
                break;
            }
        }
    };

    $scope.search = function () {
        bookService.search($scope.prefix).then(function (response) {
            angular.copy(response.data, $scope.books);
        });
    };

    $scope.delete = function (bookId) {
        bookService.deleteBook(bookId).then(function () {
            removeBookById(bookId);
        });
    };

    $scope.addBook = function () {
        $window.open('/workshop/resources/addBook.html', '_self');
    };

}).factory('bookService', function ($http) {
    return {
        search: function (titlePrefix) {
            return $http.get('/workshop/books-by-title', {params: {titlePrefix: titlePrefix}});
        },
        deleteBook: function (bookId) {
            return $http.delete('/workshop/book/' + bookId);
        }
    }
});
