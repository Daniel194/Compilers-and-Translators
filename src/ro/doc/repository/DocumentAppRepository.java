package ro.doc.repository;


import java.util.Observable;

public abstract class DocumentAppRepository<K, T> extends Observable {

    public abstract T getDocument();

    public abstract void setDocument(T t);

    protected void publish() {
        this.setChanged();
        this.notifyObservers();
    }
}
