angular.module('app.myCustomFilters', []).filter('authors', function() {
	'use strict';
	return function(authors) {
		var authorsString = ' ';
		for (var i = 0; i < authors.length; i++) {
			authorsString += authors[i].firstName;
			authorsString += ' ';
			authorsString += authors[i].lastName;
			if (i < authors.length - 1) {
				authorsString += ', ';
			}
		}
		return authorsString;
	};
});