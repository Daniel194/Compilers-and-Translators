package ro.doc.entities;


import ro.doc.entities.Client;

public class Document {
    private String id;
    private Client owner;
    private String text;

    public Document() {
        this.id = this.toString();
    }

    public Client getOwner() {
        return owner;
    }

    public String getId() {
        return id;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
