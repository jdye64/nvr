package com.jeremydyer.core.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeremydyer.core.IDX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by jeremydyer on 3/20/15.
 */
public class IDXFactory {

    private static final Logger logger = LoggerFactory.getLogger(IDXFactory.class);
    private ObjectMapper mapper = new ObjectMapper();

    public static IDX createIDXFromPath(Path idxPath) {
        IDX idx = null;

        if (idxPath != null) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(idxPath.toFile()));
                idx = createIDXFromReader(br);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("IDXPath is null");
        }

        return idx;
    }

    public static IDX createIDXFromReader(BufferedReader br) {
        IDX idx = new IDX();

        if (br != null) {
            try {
                String line;
                int lineCount = 0;
                while ((line = br.readLine()) != null) {
                    logger.info("Line: " + line);

                    if (lineCount == 0) {
                        logger.debug("Creating DahuaIDXEvent from JSON: " + line);
                        idx.setIdxEvent(DahuaIDXEventFactory.createDahuaIDXEventFromJSON(line));
                    } else if (lineCount == 1) {

                    } else {
                        //Just exit the loop because really we don't care about the frames right now
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return idx;
    }
}
