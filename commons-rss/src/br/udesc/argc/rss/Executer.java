/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.rss;

import com.rometools.rome.io.FeedException;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.mail.MessagingException;

/**
 *
 * @author silajs
 */
public interface Executer {

    public void execute() throws IOException, MalformedURLException, IllegalArgumentException, FeedException, MessagingException;
}
