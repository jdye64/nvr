package com.jeremydyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeremydyer.conf.email.EmailFactory;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Network Video Recorder configuration class. Contains the Java object variation of the contents
 * of the yaml file used to start the NVR application.
 *
 * Created by jeremydyer on 3/13/15.
 */
public class NVRConfiguration extends Configuration {

    @JsonProperty
    @NotEmpty
    private String nvrVideoDir; //Base directory where the NVR writes the video files to.

    @JsonProperty
    @NotNull
    @Valid
    private EmailFactory email;

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public String getNvrVideoDir() {
        return nvrVideoDir;
    }

    public void setNvrVideoDir(String nvrVideoDir) {
        this.nvrVideoDir = nvrVideoDir;
    }

    public EmailFactory getEmail() {
        return email;
    }

    public void setEmail(EmailFactory email) {
        this.email = email;
    }
}
