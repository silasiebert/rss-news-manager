/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.sheduler;

import java.io.IOException;

/**
 *
 * @author silajs
 */
public class Scheduler extends AbstractScheduler {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    private Thread thread;
    private Status s;

    public Scheduler() throws Exception {
        s = new Status();
        this.thread = new Thread(new Checker(s));
    }

    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void stop() {
        s.stop();
    }

}
