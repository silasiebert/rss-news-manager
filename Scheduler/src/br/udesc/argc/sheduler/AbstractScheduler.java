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
public abstract class AbstractScheduler {

    public abstract void start();

    public abstract void stop();

    public AbstractScheduler getScheduler() {
        try {
            return new Scheduler();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
