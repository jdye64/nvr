package com.jeremydyer.core.factory;

import com.jeremydyer.core.IDX;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by jeremydyer on 3/20/15.
 */
public class IDXFactory {

    public static IDX createIDXFromPath(Path idxPath) {
        IDX idx = new IDX();

        if (idxPath != null ){
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(idxPath.toFile()));
                String line;
                int lineCount = 0;
                while ((line = br.readLine()) != null) {
                    System.out.println("Line: " + line);

                    if (lineCount == 0) {

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
        } else {
            System.out.println("IDXPath is null");
        }

        return idx;
    }
}
