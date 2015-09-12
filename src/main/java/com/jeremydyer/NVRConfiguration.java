package com.jeremydyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;


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

    public String getNvrVideoDir() {
        return nvrVideoDir;
    }

    public void setNvrVideoDir(String nvrVideoDir) {
        this.nvrVideoDir = nvrVideoDir;
    }

}
