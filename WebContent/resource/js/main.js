var part1 = angular.module("part1", []);
part1.controller("RealtimeCtrl", function($scope, $http, $timeout) {
	
	$scope.addTask = function() {
		$http.post("api/task", $scope.task);
	};
	
	$scope.getTasks = function() {
		$http.get("api/task/all")
			.success(function(data) {
				$scope.tasks = data;
			});
	};
	
	$scope.getUpdatedTasks = function() {
		$http.get("api/task")
			.success(function(data) {
				data.forEach(function(currentTask) {
					if (currentTask.status === 'IDLE') {
						$scope.tasks.push(currentTask);
					} else {
						$scope.tasks.forEach(function(taskToBeUpdated) {
							if (taskToBeUpdated.name === currentTask.name) {
								taskToBeUpdated.status = currentTask.status;
								taskToBeUpdated.running = currentTask.status === 'RUNNING';
							}
						});
					}
				});
				
				$scope.getUpdatedTasks();
			}).error(function() {
				$scope.getUpdatedTasks();
			});
	};
	
	$scope.activateRealtime = function() {
		$scope.getUpdatedTasks();
	};
	
	$scope.getTasks();
});