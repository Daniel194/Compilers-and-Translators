package ro.doc.repository.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ro.doc.entities.Document;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@Repository
@Scope("singleton")
public class DocumentRepositoryImpl extends DocumentRepository {
    @Autowired
    private DocumentRepositoryObserver observer;

    private Document document = new Document();

    @PostConstruct
    public void init() {
        this.addObserver(observer);
    }

    @Override
    public void add(Document document) {
        synchronized (this.document) {
            this.document = document;
        }

        this.publish();
    }

    @Override
    public void remove(Document type) {
        //Empty
    }

    @Override
    public void forEach(Consumer<Document> typeConsumer) {
        //Empty
    }

    @Override
    public List<Document> getAll() {
        return Collections.singletonList(document);
    }
}
