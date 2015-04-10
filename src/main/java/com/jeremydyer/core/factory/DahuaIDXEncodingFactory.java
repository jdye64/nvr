package com.jeremydyer.core.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeremydyer.core.DahuaIDXEncoding;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Created by jeremydyer on 4/10/15.
 */
public class DahuaIDXEncodingFactory {

    public static DahuaIDXEncoding createDahuaIDXEncodingFromJSON(String fullJson) {
        if (fullJson != null) {
            String json = fullJson;
            if (fullJson.startsWith("EncodeFormat=")) {
                json = StringUtils.replace(fullJson, "EncodeFormat=", "");
            }

            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.readValue(json, DahuaIDXEncoding.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
