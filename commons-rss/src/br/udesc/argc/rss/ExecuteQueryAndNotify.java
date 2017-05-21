package br.udesc.argc.rss;

import br.udesc.argc.persistence.dao.core.FactoryDAO;
import br.udesc.argc.persistence.dao.core.FeedDAO;
import br.udesc.argc.persistence.model.Feed;
import br.udesc.argc.persistence.model.News;
import com.rometools.rome.io.FeedException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.mail.MessagingException;

public class ExecuteQueryAndNotify implements Executer {

    private FeedReader feedReader;
    private MailSender mailsSender;
    private FeedDAO fDao;

    public ExecuteQueryAndNotify() throws IOException, MalformedURLException, IllegalArgumentException, FeedException {
        this.feedReader = new FeedReader();
        this.mailsSender = new MailSender("rssnewsfeedmanager", "sila.siebert@gmail.com", "36FhZuNXm3H2");
        this.fDao = FactoryDAO.getPersistence().getFeedDAO();
    }

    @Override
    public void execute() throws IOException, MalformedURLException, IllegalArgumentException, FeedException, MessagingException {
        List<Feed> listaFeeds = fDao.list();
        for (Feed feed : listaFeeds) {

            ArrayList listaNoticias = feedReader.retrieveFeed(feed.getUrl(), FactoryDAO.getPersistence().getSubjectDAO());
            if (!listaNoticias.isEmpty()) {
                feedReader.saveNews(listaNoticias, FactoryDAO.getPersistence().getNewsDAO());
                bulkNSendNews(listaNoticias);
            }
        }
    }

    public void bulkNSendNews(ArrayList<News> listaNoticias) throws MessagingException, UnsupportedEncodingException {
        String corpoDoEMail = "Foram encontradas as seguintes noticias \n";
        for (News noticia : listaNoticias) {
            corpoDoEMail += noticia.getTitle() + "\n" + noticia.getUrl() + "\n\n";
        }
        corpoDoEMail += "Obrigado pela preferencia!";

        mailsSender.sendMail("Noticias da consulta de " + Calendar.getInstance().getTime().toString(), corpoDoEMail);

    }
}
