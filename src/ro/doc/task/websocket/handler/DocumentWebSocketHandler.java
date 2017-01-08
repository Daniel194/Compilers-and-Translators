package ro.doc.task.websocket.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;
import ro.doc.client.domain.Client;
import ro.doc.client.repository.ClientRepository;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/channel/document", configurator = SpringConfigurator.class)
public class DocumentWebSocketHandler {

    @Autowired
    private ClientRepository clientRepository;

    @OnOpen
    public void onOpen(Session session) {
        this.clientRepository.add(new Client(session));
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        this.clientRepository.remove(new Client(session));
    }

}
