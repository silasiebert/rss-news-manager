/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.sheduler;

import br.udesc.argc.persistence.dao.core.ScheduleDAO;
import br.udesc.argc.persistence.dao.jdbc.JDBCScheduleDAO;
import br.udesc.argc.persistence.model.Schedule;
import br.udesc.argc.rss.ExecuteQueryAndNotify;
import br.udesc.argc.rss.Executer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author silajs
 */
public class Checker implements Runnable {
    
    private ScheduleDAO sDao;
    private LogWriter log;
    private Executer executer;
    
    public Checker() throws IOException {
        this.sDao = new JDBCScheduleDAO();
        this.log = new LogWriter("log.txt");
        this.executer = new ExecuteQueryAndNotify();
    }
    
    @Override
    
    public void run() {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        String time;
        try {

            //sleep de 5 minutos
            for (Schedule schedule : sDao.list()) {
                
                if (localDateFormat.format(schedule.getDate()).equals(localDateFormat.format(new Date()))) {
                    executer.execute();
                }
            }
            
            Thread.sleep(300000);
            
        } catch (IllegalArgumentException ex) {
            try {
                log.logMessage(ex.getMessage());
            } catch (IOException ex1) {
                System.out.println(ex1.getMessage());
            }
        } catch (IOException ex) {
            try {
                log.logMessage(ex.getMessage());
            } catch (IOException ex1) {
                System.out.println(ex1.getMessage());
            }
        } catch (InterruptedException ex) {
            try {
                log.logMessage(ex.getMessage());
            } catch (IOException ex1) {
                System.out.println(ex1.getMessage());
            }
        }
    }
    
}
