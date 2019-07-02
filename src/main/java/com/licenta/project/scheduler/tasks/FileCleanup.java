package com.licenta.project.scheduler.tasks;

import org.apache.log4j.Logger;

import java.io.File;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class FileCleanup {


    private static final Logger logger = Logger.getLogger(FileCleanup.class);

    public void cleanUp(String path){
        File folder = new File(path);
        for(File fileEntry : folder.listFiles()){
            if(fileEntry.isDirectory()){
                cleanUp(path + "/" + fileEntry.getName());
            }else{
                long lastModified = fileEntry.lastModified();
                long currentTime = Instant.now().toEpochMilli();

                if(TimeUnit.MILLISECONDS.toHours(currentTime - lastModified) >= 6){
                    fileEntry.delete();
                    logger.info("File with name: " + fileEntry + " deleted");
                }

            }
        }
    }

}
