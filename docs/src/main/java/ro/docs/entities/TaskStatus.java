package ro.docs.entities;


public enum TaskStatus {
    IDLE(1), RUNNING(2), SUCCESS(3);

    private Integer taskStatus;

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    TaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

}
