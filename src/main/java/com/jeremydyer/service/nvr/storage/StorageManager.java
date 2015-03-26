package com.jeremydyer.service.nvr.storage;

import com.jeremydyer.NVRConfiguration;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeremydyer on 3/19/15.
 */
public class StorageManager {

    private NVRConfiguration nvrConfiguration;

    public StorageManager(NVRConfiguration config) {
        this.nvrConfiguration = config;
    }

    /**
     * Archives the files in the primary storage location to the Archive storage location
     */
    public void archiveFiles() {
        System.out.println("Archiving files in: " + this.nvrConfiguration.getNvrVideoDir().toString());

        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(this.nvrConfiguration.getNvrVideoDir().toString()))) {

            for (Path path : directoryStream) {
                System.out.println("Path: " + path.toString());

                fileNames.add(path.toString());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     *
     * @return
     */
    public List<Path> listAllVideoFiles() {
        final List<Path> videoFiles = new ArrayList<>();

        try {
            Files.walkFileTree(Paths.get(this.nvrConfiguration.getNvrVideoDir()), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException
                {
                    System.out.println("File Path: " + file.toString());
                    videoFiles.add(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }

        System.out.println("Found " + videoFiles.size() + " video files in the directory path");
        return videoFiles;
    }
}
