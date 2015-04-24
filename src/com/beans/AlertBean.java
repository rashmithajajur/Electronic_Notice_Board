package com.beans;

import java.io.Serializable;

public class AlertBean implements Serializable {

	PersonBean person;
	String alertDescription;
	boolean viewed;
	int alertid;
	int personid;
	
	public PersonBean getPerson() {
		return person;
	}
	public void setPerson(PersonBean person) {
		this.person = person;
	}
	public String getAlertDescription() {
		return alertDescription;
	}
	public void setAlertDescription(String alertDescription) {
		this.alertDescription = alertDescription;
	}
	public boolean isViewed() {
		return viewed;
	}
	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}
	public int getAlertid() {
		return alertid;
	}
	public void setAlertid(int alertid) {
		this.alertid = alertid;
	}
	public int getPersonid() {
		return personid;
	}
	public void setPersonid(int personid) {
		this.personid = personid;
	}
	
}
