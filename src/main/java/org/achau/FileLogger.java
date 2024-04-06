package org.achau;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileLogger {
    public static final String directoryPath = "./NewsScrapper_logs";
    public static final Logger logger = initLogger();

    private static String initFileName(){

        String time = LocalTime.now().toString().substring(0,8).replaceAll(":","-");
        return "NewScrapperLogs_" + LocalDate.now() + "_" + time  + ".txt";
    }

    private static String initDirectoryPath(){
        File loggerDirectory = new File(directoryPath);
        if(!loggerDirectory.exists()){
            loggerDirectory.mkdirs();
        }
        return directoryPath;
    }
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
