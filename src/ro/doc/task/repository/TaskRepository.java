package ro.doc.task.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.doc.repository.TaskAppRepository;
import ro.doc.task.domain.Task;


@Repository
public abstract class TaskRepository extends TaskAppRepository<String, Task> {
	@Autowired
	private BeanFactory factory;
	
	@PostConstruct
	public void init() {
		this.addObserver(this.factory.getBean(TaskRepositoryObserver.class));
	}
}
