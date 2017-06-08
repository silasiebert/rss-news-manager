/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.sheduler;

/**
 *
 * @author silajs
 */
public class Status {

    private boolean stopped;
    
    public Status(){
        stopped = false;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void start() {
        this.stopped = true;
    }
    
    public void stop() {
        this.stopped = false;
    }
    
    
}

