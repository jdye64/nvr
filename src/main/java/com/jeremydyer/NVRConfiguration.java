package com.jeremydyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeremydyer.conf.email.EmailFactory;
import io.dropwizard.Configuration;
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

    @NotEmpty
    private String nvrWatchDirectory;

    @JsonProperty
    @NotNull
    @Valid
    private EmailFactory email;

    @JsonProperty
    public String getNvrWatchDirectory() {
        return nvrWatchDirectory;
    }

    @JsonProperty
    public void setNvrWatchDirectory(String nvrWatchDirectory) {
        this.nvrWatchDirectory = nvrWatchDirectory;
    }

    public EmailFactory getEmail() {
        return email;
    }

    public void setEmail(EmailFactory email) {
        this.email = email;
    }
}
