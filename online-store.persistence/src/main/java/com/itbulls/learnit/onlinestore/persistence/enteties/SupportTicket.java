package com.itbulls.learnit.onlinestore.persistence.enteties;

public interface SupportTicket {

	Priority getPriority();

	/**
	 * This method returns the unique sequential number of the support ticket.
	 * This number can be used as an identifier. 
	 * Order is started from 1.
	 * The less the return number is - that support ticket was created earlier.
	 * 
	 * @return unique sequence number
	 */
	int getSequentialNumber();

	RequestType getRequestType();

}
