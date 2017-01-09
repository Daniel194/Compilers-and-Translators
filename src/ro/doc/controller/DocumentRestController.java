package ro.doc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.doc.entities.Document;
import ro.doc.repository.document.DocumentRepository;

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
