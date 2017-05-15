/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.sheduler;

import br.udesc.argc.persistence.dao.core.ScheduleDAO;
import br.udesc.argc.persistence.dao.jdbc.JDBCScheduleDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author silajs
 */
public class Checker implements Runnable {

    private ScheduleDAO sDao;
    //private Executer;
    public Checker() {
        this.sDao = new JDBCScheduleDAO();
    }

    @Override
    public void run() {
        try {
            
            //sleep de 5 minutos
            Thread.sleep(300000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
