angular.module("TaskApp", ['textAngular'])
    .controller("TaskController", function ($http, $scope) {
        var address = window.location.hostname + ":" + window.location.port;

        $scope.document = {};
        $scope.document.text = '';

        $scope.getDocument = function () {

            $http.get("http://" + address + "/document")
                .then(function (success) {

                    $scope.document = success.data;

                }, function () {
                    //Empty
                });

        };

        $scope.$watch('document.text', function () {

            $http.post("http://" + address + "/document", $scope.document)
                .then(function () {
                    //Empty
                }, function () {
                    //Empty
                });
        });

        $scope.getDocument();

        // WebSocket Initialization
        var documentSocket = new WebSocket("ws://" + address + "/channel/document");

        documentSocket.onmessage = function (message) {
            $scope.document = JSON.parse(message.data)[0];
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