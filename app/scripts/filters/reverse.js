'use strict';

/**
 * @ngdoc filter
 * @name robotApp.filter:reverse
 * @function
 * @description
 * # reverse
 * Filter in the robotApp.
 */
angular.module('robotApp')
  .filter('reverse', function () {
	 return function(items) {
	    return items.slice().reverse();
	  };
  });
