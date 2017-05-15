/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.rss;

import com.rometools.rome.io.FeedException;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author silajs
 */
public class Executer {

    private FeedReader feedReader;
    private MailSender mailsSender;

    public Executer() throws IOException, MalformedURLException, IllegalArgumentException, FeedException {
        this.feedReader = new FeedReader();
        this.mailsSender = new MailSender();
    }

}
