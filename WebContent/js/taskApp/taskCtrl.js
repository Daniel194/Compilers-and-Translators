angular.module("TaskApp", ['textAngular'])
    .controller("TaskController", function ($http, $scope, textAngularManager) {

        var address = window.location.hostname + ":" + window.location.port;
        $scope.textContent = '<p>dfsf</p><p>sfsdfsdf</p><p><br/></p><p>sdfsdfsdf</p><p>sdfsdfsdf</p><p>sdfsdf</p><p>l<span id="selectionBoundary_1483814084781_7623380121544683" class="rangySelectionBoundary">&#65279;</span></p><p><br/></p>';

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

        $scope.$watch('textContent', function () {
            console.log($scope.textContent);
        });

    });