package com.jeremydyer.service.email;

import com.jeremydyer.NVRConfiguration;
import com.jeremydyer.conf.email.EmailFactory;

import javax.mail.*;
import javax.mail.internet.*;

/**
 * Created by jeremydyer on 3/22/15.
 */
public class EmailManager {

    private NVRConfiguration nvrConfiguration = null;

    public EmailManager(NVRConfiguration nvrConfiguration) {
        this.nvrConfiguration = nvrConfiguration;
    }

    public void sendEmail(String emailMessage) {

        EmailFactory emf = nvrConfiguration.getEmail();

        try {
            Message message = new MimeMessage(emf.emailSession());
            message.setFrom(new InternetAddress(emf.from()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emf.to()));
            message.setSubject(emf.subject());

            //If emailMessage is not passed explicity use the default message from the configuration
            if (emailMessage == null || emailMessage.length() == 0) {
                message.setText(emf.message());
            } else {
                message.setText(emailMessage);
            }

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public NVRConfiguration getNvrConfiguration() {
        return nvrConfiguration;
    }

    public void setNvrConfiguration(NVRConfiguration nvrConfiguration) {
        this.nvrConfiguration = nvrConfiguration;
    }
}
