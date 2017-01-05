package ro.doc.task.websocket.broadcaster;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import ro.doc.client.repository.ClientRepository;
import ro.doc.task.domain.Task;
import ro.doc.websocket.broadcaster.Broadcaster;


@Component
public class TaskBroadcaster implements Broadcaster<Task> {
	@Autowired
	private ClientRepository clients;
	private Gson gson;
	
	@PostConstruct
	public void init() {
		this.gson = new Gson();
	}
	
	@Override
	public void broadcast(List<Task> task) {
		this.clients.forEach(client -> {
			try {
				client.sendText(this.gson.toJson(task));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
