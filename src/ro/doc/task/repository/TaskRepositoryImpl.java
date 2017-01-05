package ro.doc.task.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ro.doc.task.domain.Task;


@Repository
@Scope("singleton")
public class TaskRepositoryImpl extends TaskRepository {
	@Autowired
	private TaskRepositoryObserver observer;
	
	private List<Task> tasks = new LinkedList<>();
	
	@PostConstruct
	public void init() {
		this.addObserver(observer);
	}
	
	@Override
	public void add(Task task) {
		synchronized (tasks) {
			this.tasks.add(task);
		}
		
		this.publish();
	}

	@Override
	public void remove(Task task) {
		synchronized (tasks) {
			this.tasks.remove(task);
		}
		
		this.publish();
	}

	@Override
	public void forEach(Consumer<Task> typeConsumer) {
		synchronized (tasks) {
			this.tasks.forEach(typeConsumer);
		}
	}
	
	public List<Task> getAll() {
		return new LinkedList<>(this.tasks);
	}

}
