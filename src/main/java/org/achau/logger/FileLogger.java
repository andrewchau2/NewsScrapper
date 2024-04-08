package org.achau.logger;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Informational Static Class that provides info/warnings/severe Messages during the execution of the web-scrapping
 * @author Andrew Chau
 * @version 1.0
 */
public class FileLogger {
    public static final String directoryPath = "./NewsScrapper_logs";
    public static final Logger logger = initLogger();

    /**
     * Creates a string in a timestamp format for the logs
     * @return a timestamp in a string format for the log filename
     */
    private static String initFileName(){

        String time = LocalTime.now().toString().substring(0,8).replaceAll(":","-");
        return "NewScrapperLogs_" + LocalDate.now() + "_" + time  + ".txt";
    }

    /**
     * Checks that the directoryPath is exists before log files are generated
     * Creates a new directory with the given directoryPath if not found
     * @return the name of the directoryPath
     */
    private static String initDirectoryPath(){
        File loggerDirectory = new File(directoryPath);
        if(!loggerDirectory.exists()){
            loggerDirectory.mkdirs();
        }
        return directoryPath;
    }

    /**
     * Defines the setup for the logger before it can be used.
     * File name and directoryPath are called during this process
     * Also, objects for the logger object is defined here
     * @return a new logger object with the preferred options
     */
    private static Logger initLogger(){

        String fileName = initFileName();
        String directoryPath = initDirectoryPath();

        Logger initLogger = Logger.getLogger(FileLogger.class.toString());
        try {
            FileHandler handler = new FileHandler(directoryPath + "/" + fileName);
            initLogger.addHandler(handler);
            SimpleFormatter formatter = new SimpleFormatter();
            handler.setFormatter(formatter);
        }catch (IOException e){
            System.err.println("FileLogger.initLogger() Failed to initalize Logger Object");
            e.printStackTrace();
        }
        return initLogger;
    }
}
