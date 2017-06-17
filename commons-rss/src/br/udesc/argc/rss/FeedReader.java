/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.rss;

import br.udesc.argc.persistence.dao.core.FactoryDAO;
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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author silajs
 */
public class FeedReader {

    private NewsDAO dao;

    public FeedReader() {
        this.dao = FactoryDAO.getPersistence().getNewsDAO();
    }

    public ArrayList retrieveFeed(String url, SubjectDAO sDao, int feedId) throws MalformedURLException, IOException, IOException, IllegalArgumentException, FeedException {
        SyndFeed feed = null;
        ArrayList<News> news = new ArrayList<>();
        List<Subject> listaAsuntos = sDao.list();
        News n = new News();
        try {
            feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
        } catch (FeedException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(feed.getTitle());
        List<SyndEntry> entries = feed.getEntries();
        for (SyndEntry entry : entries) {
            for (Subject s : listaAsuntos) {
                if (entry.getTitle().toLowerCase().contains(s.getSubject().toLowerCase()) && newsIsNew(entry.getPublishedDate(), feedId)) {
                    n.setTitle(entry.getTitle());
                    n.setUrl(entry.getLink());
                    n.setDate(newsValidDate(entry.getPublishedDate()));
                    n.setFeed(feedId);
                    news.add(n);
                    n = new News();
                    break;
                }
            }

        }
        //return array de noticias
        return news;
    }

    public void saveNews(ArrayList<News> listaNoticias, NewsDAO nDao) {
        for (News noticia : listaNoticias) {
            nDao.insert(noticia);
        }
    }

    public boolean newsIsNew(Date publishDate, int feedId) {
        News lastNewsFromFeed = dao.getLastNewsFromFeed(feedId);
        if (lastNewsFromFeed != null) {
            int dateIs = publishDate.compareTo(lastNewsFromFeed.getDate());
            if (dateIs <= 0) {
                return false;
            }
        }
        return true;

    }

    public Date newsValidDate(Date date) {
        if (date != null) {
            return date;
        }
        return new Date();
    }
}
