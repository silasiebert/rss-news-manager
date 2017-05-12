/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.rss;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author silajs
 */
public class MailSender {

    /**
     *
     * @param fromEmail
     * @param toEmail
     * @param password
     * @param assuntoEmail
     * @param corpoEmail
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public void sendMail(String fromEmail, String toEmail, String password, String assuntoEmail, String corpoEmail) throws MessagingException, UnsupportedEncodingException {

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);
        //set message headers

        msg.addHeader(
                "Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader(
                "format", "flowed");
        msg.addHeader(
                "Content-Transfer-Encoding", "8bit");

        msg.setFrom(
                new InternetAddress(toEmail, "NoReply-JD"));

        msg.setReplyTo(InternetAddress.parse(toEmail, false));

        msg.setSubject(
                assuntoEmail, "UTF-8");

        msg.setText(
                corpoEmail, "UTF-8");

        msg.setSentDate(
                new Date());

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        System.out.println(
                "Message is ready");
        Transport.send(msg);

        System.out.println(
                "EMail Sent Successfully!!");

    }

}
