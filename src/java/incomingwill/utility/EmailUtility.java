package incomingwill.utility;

/*
 *  Document   : Email Utility
 *  Created on : 08.05.22
 *  @author incomingWill
 *  CPS 316 Final Project
*/

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtility 
{

    public static void sendMail(
            String to, 
            String from,
            String subject, 
            String body, 
            boolean bodyIsHTML)
            throws MessagingException 
    {
        
        //get a mail session
        Properties props = new Properties();
        //props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "localhost");
        //props.put("mail.smtp.port", 25);        
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        //create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) 
        {
            message.setContent(body, "text/html");
        } 
        else 
        {
            message.setText(body);
        }

        //address the message
        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        //send the message
        Transport.send(message);
    }
}