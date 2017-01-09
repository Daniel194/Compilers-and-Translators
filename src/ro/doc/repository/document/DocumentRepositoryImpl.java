package ro.doc.repository.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ro.doc.entities.Document;

import javax.annotation.PostConstruct;

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
    public Document getDocument() {
        return document;
    }

    @Override
    public void setDocument(Document document) {
        synchronized (this.document) {
            this.document = document;
        }

        this.publish();
    }
}
