package com.itbulls.learnit.onlinestore.core.logging.javalogging;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerDemo {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public void doSomethingAndLog() {
        LOGGER.setLevel(Level.SEVERE);
        
        LOGGER.severe("SEVERE Log");
        LOGGER.warning("WARNING Log");
        LOGGER.info("Info Log");
        LOGGER.finest("FINEST Really not important");

        LOGGER.setLevel(Level.INFO);
        LOGGER.severe("SEVERE Log");
        LOGGER.warning("WARNING Log");
        LOGGER.info("Info Log");
        LOGGER.finest("Finest Really not important");
    }

    public static void main(String[] args) {
        LoggerDemo loggerDemo = new LoggerDemo();
        try {
            MyLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }
        loggerDemo.doSomethingAndLog();
    }
}
