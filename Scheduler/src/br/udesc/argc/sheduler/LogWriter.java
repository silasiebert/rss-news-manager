/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.sheduler;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author silajs
 */
public class LogWriter {

    private String path;

    public LogWriter(String path) {
        this.path = path;
    }

    public void logMessage(String message) throws IOException {
        FileWriter write = new FileWriter(path, true);
        write.append(message);
    }
}
