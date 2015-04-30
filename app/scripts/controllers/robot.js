'use strict';

/**
 * @ngdoc function
 * @name robotApp.controller:RobotCtrl
 * @description
 * # RobotCtrl
 * Controller of the robotApp
 */
angular.module('robotApp')
  .controller('RobotCtrl',['$scope', '$document', 'robotService', function ($scope, $document, robotService) {
    
   function populateBoard(){
   	var rows = [];
	for(var y = 4; y >= 0; y-- ){
		rows[y] = {};
		rows[y].cells = []
		for(var x=0; x < 5; x++){
			rows[y].cells.push({x:x, y:y});
		}
	}
	return rows;
   }

    $scope.newRobot = { name: ''}; 
    $scope.allRobots = [];
    var initPosition = {angle:'', x_pos:'', y_pos:''};
	$scope.robotCommand = initPosition;
    $scope.currentRobot = {position: initPosition };

    robotService.robotList().then(function(res){
    	setAllRobots(res);
    });

    function setAllRobots(res){
    	$scope.allRobots = angular.fromJson(res.data);
    	return $scope.allRobots;
    };

    function setCurrentRobot(res){
    	console.log(res);
	      $scope.currentRobot.name = res.data;
	      return res.data;
    }
    function getCommandPosition(){
    	var xy = $scope.robotCommand.position.split(',');
    	if (xy.length===2){
    		return {
    			angle: $scope.robotCommand.angle,
    			x_pos: xy[0],
    			y_pos: xy[1]
    		}
    	}
    }
    function setCurrentPosition(res){
    	$scope.currentRobot.position = angular.fromJson(res.data);
    }

    $scope.createRobot = function(){
    	console.log('create robot:' + $scope.newRobot.name);
    	robotService.createRobot($scope.newRobot.name)
    	.then(setCurrentRobot)
  		.then(robotService.get)
    	.then(robotService.robotList()
    	.then(function(res){
    		setAllRobots(res);
    	 })
    	);
    };

    $scope.placeRobot = function (){
    	robotService.placeRobot($scope.currentRobot.name, getCommandPosition())
    	.then(function(res){
    		setCurrentPosition(res);
    	});
    };

    $scope.getCurrentRobotPosition = function(){
    	return robotService.getRobotPosition($scope.currentRobot.name)
    	.then(function(res){
    		setCurrentPosition(res);
    	});
    }
  
    $scope.moveForward = function (){
    	    return robotService.moveRobotForward($scope.currentRobot.name)
    		.then(function(res){
    			setCurrentPosition(res);
    		});
    };
    $scope.turnRight = function() {
    	return robotService.turnRobotRight($scope.currentRobot.name)
    		.then(function(res){
    			setCurrentPosition(res);
    		});
    }
    $scope.turnLeft = function() {
    	return robotService.turnRobotLeft($scope.currentRobot.name)
    		.then(function(res){
    			setCurrentPosition(res);
    		});
    }
    $scope.board = { rows:populateBoard()};

    $scope.currentRobot.isRobotCurrentPosition = function(cell){
    	 return $scope.currentRobot.position.x_pos === cell.x && $scope.currentRobot.position.y_pos === cell.y;
    };
  }]);
