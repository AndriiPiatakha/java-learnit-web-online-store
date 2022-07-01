package com.itbulls.learnit.onlinestore.core.logging.javalogging;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class CustomHandlerDemo extends Handler {

	@Override
	public void publish(LogRecord logRecord) {
		System.out.println(
				String.format("Log level: %s, message: %s", 
						logRecord.getLevel().toString(), logRecord.getMessage()));
	}

	@Override
	public void flush() {
	}

	@Override
	public void close() throws SecurityException {
	}

}
