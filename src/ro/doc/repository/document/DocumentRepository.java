package ro.doc.repository.document;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.doc.entities.Document;

import javax.annotation.PostConstruct;

@Repository
public abstract class DocumentRepository extends DocumentAppRepository<String, Document> {

    @Autowired
    private BeanFactory factory;

    @PostConstruct
    public void init() {
        this.addObserver(this.factory.getBean(DocumentRepositoryObserver.class));
    }

}
