package com._12atguigu.java.jikexueyuan.protomode;

public class EventTemplate {
	private String eventSubject, eventContent;

	public EventTemplate(String eventSubject, String eventContent) {
		this.eventSubject = eventSubject;
		this.eventContent = eventContent;
	}

	public String geteventSubject() {
		return eventSubject;
	}

	public String geteventContent() {
		return eventContent;
	}
}
