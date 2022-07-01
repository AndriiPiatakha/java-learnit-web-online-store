package com.itbulls.learnit.onlinestore.core.logging.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jDemo {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jDemo.class);

    public static void main(String[] args) {
        LOGGER.debug("Debug log message");
        LOGGER.info("Info log message");
        LOGGER.error("Error log message");
    }

}
