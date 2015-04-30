'use strict';

/**
 * @ngdoc service
 * @name robotApp.robot
 * @description
 * # robot
 * Service in the robotApp.
 */
angular.module('robotApp')
  .service('robotService', ['ENV','$http', function (ENV, $http) {
    $http.defaults.transformResponse = [];
  	this.createRobot = function(robotName){
  		return $http.post(ENV.apiUri + robotName);
  	};

  	this.robotList = function(){
  		return $http.get(ENV.apiUri + 'list');
  	};

  	this.placeRobot = function(robotName, position){
  		return $http.post(ENV.apiUri + robotName + '/position', position);
  	}

  	this.getRobotPosition = function(robotName){
  		return $http.get(ENV.apiUri + robotName + '/position/123');
  	}
  	this.turnRobotLeft = function(robotName){
  		var url = ENV.apiUri + robotName + '/position/left/1';
		return $http.post(url);
  		//return $http.put(ENV.apiUri + robotName + '/position/left', {method: 'PUT'});
  	}
  	this.turnRobotRight = function(robotName){
  		var url = ENV.apiUri + robotName + '/position/right/1';
  		return $http.post(url);
  	}
  	this.moveRobotForward = function(robotName){
  		var url = ENV.apiUri + robotName + '/position/move/1';
  		return $http.post(url);
  	}
    // AngularJS will instantiate a singleton by calling "new" on this function
  }]);
