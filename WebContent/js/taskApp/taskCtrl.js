taskApp.controller("TaskController", function ($http, $scope) {

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

    var events = ['trixInitialize', 'trixChange', 'trixSelectionChange', 'trixFocus', 'trixBlur', 'trixFileAccept', 'trixAttachmentAdd', 'trixAttachmentRemove'];

    for (var i = 0; i < events.length; i++) {
        $scope[events[i]] = function (e) {
            console.info('Event type:', e.type);
        }
    }

    var createStorageKey, host, uploadAttachment;

    $scope.trixAttachmentAdd = function (e) {
        var attachment;
        attachment = e.attachment;
        if (attachment.file) {
            return uploadAttachment(attachment);
        }
    };

    host = "https://d13txem1unpe48.cloudfront.net/";

    uploadAttachment = function (attachment) {
        var file, form, key, xhr;
        file = attachment.file;
        key = createStorageKey(file);
        form = new FormData;
        form.append("key", key);
        form.append("Content-Type", file.type);
        form.append("file", file);
        xhr = new XMLHttpRequest;
        xhr.open("POST", host, true);
        xhr.upload.onprogress = function (event) {
            var progress;
            progress = event.loaded / event.total * 100;
            return attachment.setUploadProgress(progress);
        };
        xhr.onload = function () {
            var href, url;
            if (xhr.status === 204) {
                url = href = host + key;
                return attachment.setAttributes({
                    url: url,
                    href: href
                });
            }
        };
        return xhr.send(form);
    };

    createStorageKey = function (file) {
        var date, day, time;
        date = new Date();
        day = date.toISOString().slice(0, 10);
        time = date.getTime();
        return "tmp/" + day + "/" + time + "-" + file.name;
    };


});