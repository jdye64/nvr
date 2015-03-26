package com.jeremydyer.conf.email;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Factory for creating GMail configuration
 *
 * Created by jeremydyer on 3/25/15.
 */
@JsonTypeName("gmail")
public class GmailFactory implements EmailFactory {

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonProperty
    private String from;

    @JsonProperty
    private String to;

    @JsonProperty
    private String subject;

    @JsonProperty
    private String message;

    @Override
    public String username() {
        return this.username;
    }

    @Override
    public String password() {
        return this.password;
    }

    @Override
    public String host() {
        return "smtp.gmail.com";
    }

    @Override
    public int port() {
        return 587;
    }

    @Override
    public String from() {
        return this.from;
    }

    @Override
    public String to() {
        return this.to;
    }

    @Override
    public String subject() {
        return this.subject;
    }

    @Override
    public String message() {
        return this.message;
    }

    @Override
    public Properties emailProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host());
        props.put("mail.smtp.port", new Integer(port()).toString());
        return props;
    }

    @Override
    public Session emailSession() {
        Session session = Session.getInstance(emailProperties(),
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }
}
