/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.rss;

import com.rometools.rome.io.FeedException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author silajs
 */
public abstract class Executer {

    public abstract void execute() throws IOException, MalformedURLException, IllegalArgumentException, FeedException, MessagingException;

    public static Executer getExecuter() {
        try {
            return new ExecuteQueryAndNotify();
        } catch (Exception ex) {
//           do logs here
            return null;
        }
    }

}
