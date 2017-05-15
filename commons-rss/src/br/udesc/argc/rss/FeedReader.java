/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.rss;

import br.udesc.argc.persistence.dao.core.NewsDAO;
import br.udesc.argc.persistence.dao.core.SubjectDAO;
import br.udesc.argc.persistence.model.News;
import br.udesc.argc.persistence.model.Subject;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
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

    }

    public ArrayList retrieveFeed(String url, SubjectDAO sDao) throws MalformedURLException, IOException, IOException, IllegalArgumentException, FeedException {
        SyndFeed feed = null;
        ArrayList<News> news = new ArrayList<>();
        List<Subject> listaAsuntos = sDao.list();
        News n = new News();
        try {
            feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
        } catch (FeedException ex) {
            Logger.getLogger(FeedReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(feed.getTitle());
        List<SyndEntry> entries = feed.getEntries();
        for (SyndEntry entry : entries) {
            for (Subject s : listaAsuntos) {
                if (entry.getTitle().contains(s.getSubject())) {
                    n.setTitle(entry.getTitle());
                    n.setTitle(entry.getLink());
                    news.add(n);
                    n = new News();
                    break;
                }
            }

            System.out.println(entry.getTitle());
            System.out.println(entry.getDescription().getValue());
            System.out.println(entry.getLink());
            System.out.println(" ");
        }
        //return array de noticias
        return news;
    }

    public void saveNews(ArrayList listaNoticias, NewsDAO nDao) {
        for (Object noticia : listaNoticias) {
            nDao.insert((News) noticia);
        }
    }
}
