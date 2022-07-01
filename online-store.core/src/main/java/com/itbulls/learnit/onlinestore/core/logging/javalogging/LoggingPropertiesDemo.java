package com.itbulls.learnit.onlinestore.core.logging.javalogging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingPropertiesDemo {
	static {
		String path = LoggingPropertiesDemo
							.class.getClassLoader()
							.getResource("logging.properties")
							.getFile();
		System.setProperty("java.util.logging.config.file", path);
	}

	private static final Logger LOGGER = 
			Logger.getLogger(LoggingPropertiesDemo.class.getName());

	public static void main(String[] args) {

		LOGGER.fine("This is level fine logging");

		// Log a fine level msg
		LOGGER.info("This is level info logging");

		LOGGER.log(Level.SEVERE, "This is level severe logging");

		LOGGER.log(Level.WARNING, "This is level warning logging");

	}

}
