/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.rss;

import br.udesc.argc.persistence.model.News;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndLink;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author silajs
 */
public class FeedReader {

    public FeedReader() throws MalformedURLException, IOException, IllegalArgumentException, FeedException {
        //Falta implementar filtro de palavras chave
        //Falta opcao de carrreegar noticias do dia.
    }

    public ArrayList retrieveFeed(String url) throws MalformedURLException, IOException, IOException, IllegalArgumentException, FeedException {
        //String url = "http://www.valor.com.br/rss";
        SyndFeed feed = null;
        ArrayList<News> news = new ArrayList<>();
        News n = new News();
        try {
            feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
        } catch (FeedException ex) {
            Logger.getLogger(FeedReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(feed.getTitle());
        List<SyndEntry> entries = feed.getEntries();
        for (SyndEntry entry : entries) {
            n.setTitle(entry.getTitle());
            n.setTitle(entry.getLink());
            news.add(n);
            System.out.println(entry.getTitle());
            System.out.println(entry.getDescription().getValue());
            System.out.println(entry.getLink());
            System.out.println(" ");
        }
        //return array de noticias
        return news;
    }
}
