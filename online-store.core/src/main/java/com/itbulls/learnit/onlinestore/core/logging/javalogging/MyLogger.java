package com.itbulls.learnit.onlinestore.core.logging.javalogging;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*
		The following lists the Log Levels in descending order:

		SEVERE (highest)
	
		WARNING
	
		INFO
	
		CONFIG
	
		FINE
	
		FINER
	
		FINEST
	
		LOGGER.setLevel(Level.INFO);
		In addition to that you also have the levels OFF and ALL to turn the logging off or to log everything.


=========== HANDLERS

		ConsoleHandler: A ConsoleHandler records all the log messages to System.err. By default, a Logger is associated with this handler.
		
		FileHandler: A FileHandler is used to record all the log messages to a specific file or to a rotating set of files.
		
		StreamHandler: A StreamHandler publishes all the log messages to an OutputStream.
		
		SocketHandler: The SocketHandler publish the LogRecords to a network stream connection.
		
		MemoryHandler: It is used to keep the LogRecords into a memory buffer. If the buffer gets full, the new LogRecords starts overwriting the old LogRecords.

=========== FORMATTERS
		
		Available formatter

		SimpleFormatter: Generate all messages as text

		XMLFormatter: Generates XML output for the log messages

		You can also build your own formatter. 
*/


public class MyLogger {
    static private FileHandler simpleTextFileHandler;
    static private SimpleFormatter simpleFormatter;

    static private FileHandler htmlFileHandler;
    static private Formatter htmlFormatter;
    
   

    static public void setup() throws IOException {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        logger.setLevel(Level.FINE);
        
        simpleTextFileHandler = new FileHandler("log-example-simple-text-formatter.txt");
        htmlFileHandler = new FileHandler("log-example-html-formatter.html");
        
        simpleTextFileHandler.setLevel(Level.SEVERE);

        // create a TXT formatter
        simpleFormatter = new SimpleFormatter();
        simpleTextFileHandler.setFormatter(simpleFormatter);
        logger.addHandler(simpleTextFileHandler);

        // create an HTML formatter
        htmlFormatter = new MyHtmlFormatter();
        htmlFileHandler.setFormatter(htmlFormatter);
        logger.addHandler(htmlFileHandler);
    }
}
