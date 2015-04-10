package com.jeremydyer.core.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeremydyer.core.DahuaIDXEvent;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Creates the DahuaIDXEvent from the JSON read from the .dav peer .idx file.
 *
 * Created by jeremydyer on 4/10/15.
 */
public class DahuaIDXEventFactory {

    public static DahuaIDXEvent createDahuaIDXEventFromJSON(String fullJson) {
        if (fullJson != null) {
            String json = null;
            if (fullJson.startsWith("Event=")) {
                json = StringUtils.replace(fullJson, "Event=", "");
            } else {
                //Event=metadata must have already been stripped ...
                json = fullJson;
            }

            //Parse the JSON into the actual Object.
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.readValue(json, DahuaIDXEvent.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
