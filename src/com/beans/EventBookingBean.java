package com.beans;

public class EventBookingBean {

	EventsBean eventbean;
	StudentBean studentBean;
	int numberOfTickets;
	int studentId;
	int eventId;
	
	public EventsBean getEventbean() {
		return eventbean;
	}
	public void setEventbean(EventsBean eventbean) {
		this.eventbean = eventbean;
	}
	public StudentBean getStudentBean() {
		return studentBean;
	}
	public void setStudentBean(StudentBean studentBean) {
		this.studentBean = studentBean;
	}
	public int getNumberOfTickets() {
		return numberOfTickets;
	}
	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
}
