package com.beans;

import java.util.Date;

public class VisingHoursBean {

	int professorId;
	String day;
	String time;
	int vistingHourId;
	
	public int getProfessorId() {
		return professorId;
	}
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getVistingHourId() {
		return vistingHourId;
	}
	public void setVistingHourId(int vistingHourId) {
		this.vistingHourId = vistingHourId;
	}
	
}
