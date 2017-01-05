package ro.doc.websocket.broadcaster;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface Broadcaster<T> {
    public void broadcast(List<T> objToBroadCast);
}
