'use strict';

angular.module('webapi')
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/robots', {
        templateUrl: 'views/robot/robots.html',
        controller: 'RobotController',
        resolve:{
          resolvedRobot: ['Robot', function (Robot) {
            return Robot.query();
          }]
        }
      })
    }]);
