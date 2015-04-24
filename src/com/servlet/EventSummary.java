package com.servlet;

import java.util.ArrayList;

import com.beans.EventsBean;

public class EventSummary {

	ArrayList<EventsBean> eventBeanList;
	double totalRevenue;
	long totalTicketsSold;
	long totalSeats;
	
	public ArrayList<EventsBean> getEventBeanList() {
		return eventBeanList;
	}
	public void setEventBeanList(ArrayList<EventsBean> eventBeanList) {
		this.eventBeanList = eventBeanList;
	}
	public double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public long getTotalTicketsSold() {
		return totalTicketsSold;
	}
	public void setTotalTicketsSold(long totalTicketsSold) {
		this.totalTicketsSold = totalTicketsSold;
	}
	public long getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(long totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	
}
