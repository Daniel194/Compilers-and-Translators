package ro.doc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ro.doc.domain.Task;
import ro.doc.domain.TaskStatus;


@Component
@Scope("singleton")
public class TaskCreator {
    private static final int MAX_TASK_DURATION = 5000;
    private static final int MAX_TASK_CREATION_INTERVAL = 10000;
    private static final Random RANDOMIZER = new Random();

    @Autowired
    private TaskExecutor executor;

    public void start() {

        Runnable taskPoolConsumer = () -> {
            synchronized (executor) {
                while (true) {
                    try {
                        Task newTask = new Task();

                        newTask.setStatus(TaskStatus.CREATED);
                        newTask.setDuration(RANDOMIZER.nextInt(MAX_TASK_DURATION));
                        this.executor.addTask(newTask);

                        this.executor.wait(RANDOMIZER.nextInt(MAX_TASK_CREATION_INTERVAL));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(taskPoolConsumer).start();
    }
}
