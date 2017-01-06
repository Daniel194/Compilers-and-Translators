taskApp.controller("TaskController", function ($http, $scope) {

    var host = window.location.hostname + ":" + window.location.port;

    $scope.getTasks = function () {

        $http.get("http://" + host + "/tasks")
            .then(function (success) {

                $scope.tasks = success.data;

            }, function (error) {

            });

    };

    $scope.addTask = function () {

        $http.post("http://" + host + "/tasks", $scope.task)
            .then(function () {

                $scope.resetTask();

            }, function (error) {

            });

    };

    $scope.resetTask = function () {
        $scope.task = {
            title: "",
            description: "",
            duration: "",
            universal: true
        };
    };

    $scope.resetTask();
    $scope.getTasks();

    // WebSocket Initialization
    var taskSocket = new WebSocket("ws://" + host + "/channel/task");

    taskSocket.onmessage = function (message) {
        $scope.tasks = JSON.parse(message.data);
        $scope.$apply();
    };

    taskSocket.onclose = function () {
        $scope.message = {
            type: "danger",
            short: "Socket error",
            long: "An error occured with the WebSocket."
        };
        $scope.$apply();
    }

});