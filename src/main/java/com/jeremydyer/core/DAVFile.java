package com.jeremydyer.core;

import java.nio.file.Path;

/**
 * Created by jeremydyer on 3/18/15.
 */
public class DAVFile {

    private Path path;
    private DahuaIDXEvent idxFile;
    private DahuaIDXEncoding encoding;

    public DAVFile(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public DahuaIDXEvent getIdxFile() {
        return idxFile;
    }

    public void setIdxFile(DahuaIDXEvent idxFile) {
        this.idxFile = idxFile;
    }

    public DahuaIDXEncoding getEncoding() {
        return encoding;
    }

    public void setEncoding(DahuaIDXEncoding encoding) {
        this.encoding = encoding;
    }
}
