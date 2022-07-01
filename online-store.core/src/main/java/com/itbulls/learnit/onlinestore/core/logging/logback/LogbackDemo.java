package com.itbulls.learnit.onlinestore.core.logging.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackDemo {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogbackDemo.class);

	public static void main(String[] args) {
		LOGGER.info("Example log from {}", LogbackDemo.class.getSimpleName());
	}
}
