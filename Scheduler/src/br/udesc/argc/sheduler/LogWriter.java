/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.sheduler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author silajs
 */
public class LogWriter {

    private String path;

    public LogWriter() throws IOException {
        this.path = "log.txt";
        startLog();
    }

    public void logMessage(String message) throws IOException {
        FileWriter filewriter = new FileWriter(path, true);
        BufferedWriter out = new BufferedWriter(filewriter);
        out.write(new Date().toString() + " - " + message);
        out.newLine();
        //close buffer writer
        out.close();

    }

    public void startLog() throws IOException {
        FileWriter filewriter = new FileWriter(path, true);
        BufferedWriter out = new BufferedWriter(filewriter);
        out.write(new Date().toString() + " - Log started");
        out.newLine();
        //close buffer writer
        out.close();

    }

    public void endLog() throws IOException {
        FileWriter filewriter = new FileWriter(path, true);
        BufferedWriter out = new BufferedWriter(filewriter);
        out.write(new Date().toString() + " - Log ended");
        out.newLine();
        //close buffer writer
        out.close();
    }
}
