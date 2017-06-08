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

public class ExecuteQueryAndNotify extends Executer {

    private FeedReader feedReader;
    private MailSender mailsSender;
    private FeedDAO fDao;

    public ExecuteQueryAndNotify() throws IOException, MalformedURLException, IllegalArgumentException, FeedException {
        this.feedReader = new FeedReader();
        this.mailsSender = new MailSender();
        this.fDao = FactoryDAO.getPersistence().getFeedDAO();
    }

    @Override
    public void execute() throws IOException, MalformedURLException, IllegalArgumentException, FeedException, MessagingException {
        List<Feed> listaFeeds = fDao.list();
        ArrayList<News> listaNoticias = new ArrayList<>();
        ArrayList<News> noticiasDoFeed = null;
        for (Feed feed : listaFeeds) {
            noticiasDoFeed = feedReader.retrieveFeed(feed.getUrl(), FactoryDAO.getPersistence().getSubjectDAO(), feed.getId());
            if (!noticiasDoFeed.isEmpty()) {
                System.out.println("Tem noticias neste feed!");
                feedReader.saveNews(noticiasDoFeed, FactoryDAO.getPersistence().getNewsDAO());
                listaNoticias.addAll(noticiasDoFeed);
            } else {
                System.out.println("Nao tem noticias neste feed!");
            }
        }
        if (!listaNoticias.isEmpty()) {
            bulkNSendNews(listaNoticias);
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
