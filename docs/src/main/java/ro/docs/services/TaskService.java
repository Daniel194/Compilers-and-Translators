package ro.docs.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.docs.components.TaskExecutor;
import ro.docs.entities.Task;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskService {

    @Autowired
    private TaskExecutor taskExecutor;

    @RequestMapping(method = RequestMethod.GET)
    public List getTasks() {
        return this.taskExecutor.getPool();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addTask(@RequestBody Task taskToAdd) {
        this.taskExecutor.addTask(taskToAdd);
    }

    public void startIdleTasks() throws InterruptedException {
        this.taskExecutor.startAllTasks();
    }
}

