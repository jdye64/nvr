package com.jeremydyer.conf.email;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dropwizard.jackson.Discoverable;

import javax.mail.Session;
import java.util.Properties;

/**
 * Created by jeremydyer on 3/25/15.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface EmailFactory extends Discoverable {

    public String username();
    public String password();
    public String host();
    public int port();
    public String from();
    public String to();
    public String subject();
    public String message();
    public Properties emailProperties();
    public Session emailSession();
}