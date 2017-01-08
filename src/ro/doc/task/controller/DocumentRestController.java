package ro.doc.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.doc.task.domain.Document;
import ro.doc.task.repository.DocumentRepository;

import java.util.List;

@RestController
public class DocumentRestController {

    @Autowired
    private DocumentRepository documentRepository;

    @RequestMapping(path = "/document", method = RequestMethod.GET)
    @ResponseBody
    public Document getTasks() {
        return documentRepository.getDocument();
    }

    @RequestMapping(path = "/document", method = RequestMethod.POST)
    public void addTask(@RequestBody Document document) {
        documentRepository.setDocument(document);
    }

}
