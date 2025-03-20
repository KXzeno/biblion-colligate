package com.karnovah.biblion;

public class Observer {
	private String sighted;

	public Observer() {

	}

	public Observer(String sighted) {
		this.sighted = sighted;
	}

	public String getSighted() {
		return this.sighted;
	}

	public void setSighted(String sighted) {
		this.sighted = sighted;
	}
}
