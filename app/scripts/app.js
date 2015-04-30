'use strict';

/**
 * @ngdoc overview
 * @name webapiApp
 * @description
 * # webapiApp
 *
 * Main module of the application.
 */
angular
  .module('robotApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'config'
  ])
  .config(['$httpProvider', '$routeProvider', function ($httpProvider, $routeProvider) {
    $httpProvider.defaults.headers.put = {};
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'RobotCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  }]);
