package br.udesc.argc.persistence.model;

import java.util.Date;

/**
 * @author gabrielnaoto
 * @version 1.0
 * @created 10-mai-2017 19:51:30
 */
public class Schedule {

    private int id;
    private Date date;

    public Schedule() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void finalize() throws Throwable {

    }
}//end Schedule
