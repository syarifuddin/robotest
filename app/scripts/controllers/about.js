'use strict';

/**
 * @ngdoc function
 * @name webapiApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the webapiApp
 */
angular.module('robotApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
