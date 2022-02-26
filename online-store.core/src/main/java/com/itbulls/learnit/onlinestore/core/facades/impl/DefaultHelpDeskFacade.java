package com.itbulls.learnit.onlinestore.core.facades.impl;

import java.util.PriorityQueue;
import java.util.Queue;

import com.itbulls.learnit.onlinestore.core.facades.HelpDeskFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.SupportTicket;
import com.itbulls.learnit.onlinestore.persistence.utils.comparators.CustomSupportTicketsComparator;

public class DefaultHelpDeskFacade implements HelpDeskFacade {
	
	private Queue<SupportTicket> tickets;
	
	{
		tickets = new PriorityQueue<>(new CustomSupportTicketsComparator());
	}

	@Override
	public void addNewSupportTicket(SupportTicket supportTicket) {
		tickets.offer(supportTicket);
	}

	@Override
	public SupportTicket getNextSupportTicket() {
		return tickets.poll();
	}

	@Override
	public int getNumberOfTickets() {
		return tickets.size();
	}

}
