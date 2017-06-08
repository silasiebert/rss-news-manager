/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.sheduler;

import br.udesc.argc.persistence.dao.core.FactoryDAO;
import br.udesc.argc.persistence.dao.core.ScheduleDAO;
import br.udesc.argc.persistence.model.Schedule;
import br.udesc.argc.rss.ExecuteQueryAndNotify;
import br.udesc.argc.rss.Executer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author silajs
 */
public class Checker implements Runnable {

    private ScheduleDAO sDao;
    private LogWriter log;
    private Executer executer;
    private Status s;

    public Checker(Status s) throws Exception {
        this.sDao = FactoryDAO.getPersistence().getScheduleDAO();
        this.log = new LogWriter();
        this.executer = new ExecuteQueryAndNotify();
        this.s = s;
    }

    @Override

    public void run() {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
        try {
            while (true) {
                //sleep de 1 minutos

                if (s.isStopped()) {
                    log.endLog();
                    break;
                }

                for (Schedule schedule : sDao.list()) {
                    if (localDateFormat.format(schedule.getDate()).equals(localDateFormat.format(new Date()))) {
                        executer.execute();
                        log.logMessage("Query running...");
                    }
                }
                log.logMessage("Running...");
                Thread.sleep(60000);
            }
        } catch (Exception ex) {
            try {
                log.logMessage(ex.getLocalizedMessage());
            } catch (IOException ex1) {
                System.out.println(ex1.getLocalizedMessage());
            }
        }
    }

}
