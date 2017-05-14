package br.udesc.argc.persistence.model;

/**
 * @author gabrielnaoto
 * @version 1.0
 * @created 10-mai-2017 19:51:30
 */
public class Subject {

    private int id;
    private String subject;

    public Subject() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void finalize() throws Throwable {

    }

    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", subject=" + subject + '}';
    }
    
    
}//end Subject
