'use strict';

angular.module('webapi')
  .factory('Robot', ['$resource', function ($resource) {
    return $resource('webapi/robots/:id', {}, {
      'query': { method: 'GET', isArray: true},
      'get': { method: 'GET'},
      'update': { method: 'PUT'}
    });
  }]);
