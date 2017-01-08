package ro.doc.task.websocket.broadcaster;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.doc.client.repository.ClientRepository;
import ro.doc.task.domain.Document;
import ro.doc.websocket.broadcaster.Broadcaster;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DocumentBroadcaster implements Broadcaster<Document> {
    @Autowired
    private ClientRepository clients;
    private Gson gson;

    @PostConstruct
    public void init() {
        this.gson = new Gson();
    }


    @Override
    public void broadcast(List<Document> objToBroadCast) {
        this.clients.forEach(client -> {
            try {
                client.sendText(this.gson.toJson(objToBroadCast));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
