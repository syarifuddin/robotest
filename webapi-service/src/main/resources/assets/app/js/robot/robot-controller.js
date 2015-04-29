'use strict';

angular.module('webapi')
  .controller('RobotController', ['$scope', '$modal', 'resolvedRobot', 'Robot',
    function ($scope, $modal, resolvedRobot, Robot) {

      $scope.robots = resolvedRobot;

      $scope.create = function () {
        $scope.clear();
        $scope.open();
      };

      $scope.update = function (id) {
        $scope.robot = Robot.get({id: id});
        $scope.open(id);
      };

      $scope.delete = function (id) {
        Robot.delete({id: id},
          function () {
            $scope.robots = Robot.query();
          });
      };

      $scope.save = function (id) {
        if (id) {
          Robot.update({id: id}, $scope.robot,
            function () {
              $scope.robots = Robot.query();
              $scope.clear();
            });
        } else {
          Robot.save($scope.robot,
            function () {
              $scope.robots = Robot.query();
              $scope.clear();
            });
        }
      };

      $scope.clear = function () {
        $scope.robot = {
          
          "name": "",
          
          "myattr": "",
          
          "id": ""
        };
      };

      $scope.open = function (id) {
        var robotSave = $modal.open({
          templateUrl: 'robot-save.html',
          controller: 'RobotSaveController',
          resolve: {
            robot: function () {
              return $scope.robot;
            }
          }
        });

        robotSave.result.then(function (entity) {
          $scope.robot = entity;
          $scope.save(id);
        });
      };
    }])
  .controller('RobotSaveController', ['$scope', '$modalInstance', 'robot',
    function ($scope, $modalInstance, robot) {
      $scope.robot = robot;

      

      $scope.ok = function () {
        $modalInstance.close($scope.robot);
      };

      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
    }]);
