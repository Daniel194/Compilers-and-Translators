angular.module("TaskApp", ['textAngular'])
    .controller("TaskController", function ($http, $scope) {
        var address = window.location.hostname + ":" + window.location.port;

        $scope.getTasks = function () {

            $http.get("http://" + address + "/tasks")
                .then(function (success) {

                    $scope.tasks = success.data;

                }, function (error) {

                });

        };

        $scope.addTask = function () {

            $http.post("http://" + address + "/tasks", $scope.task)
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
        var taskSocket = new WebSocket("ws://" + address + "/channel/task");

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
        };


        /*

         ============== Document ==============

         */

        $scope.textContent = '';

        $scope.getDocument = function () {

            $http.get("http://" + address + "/document")
                .then(function (success) {

                    $scope.textContent = success.data;

                }, function (error) {

                });

        };

        $scope.$watch('textContent', function () {

            $http.post("http://" + address + "/document", $scope.textContent)
                .then(function () {

                }, function (error) {

                });
        });

        // WebSocket Initialization
        var documentSocket = new WebSocket("ws://" + address + "/channel/document");

        documentSocket.onmessage = function (message) {
            $scope.textContent = JSON.parse(message.data);
            $scope.$apply();
        };

        documentSocket.onclose = function () {
            $scope.message = {
                type: "danger",
                short: "Socket error",
                long: "An error occured with the WebSocket."
            };
            $scope.$apply();
        };

    });