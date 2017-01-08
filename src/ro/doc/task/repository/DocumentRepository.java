package ro.doc.task.repository;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.doc.repository.DocumentAppRepository;
import ro.doc.task.domain.Document;

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
