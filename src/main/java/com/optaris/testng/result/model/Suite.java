package com.optaris.testng.result.model;

import java.util.Date;

public class Suite extends Element{
	private String name;
	private Long durationMs;
	private Date startedAt, finishedAt;
	private Test test;
	
	public static final String NAME = "name";
	public static final String DURATION_MS = "duration-ms";
	public static final String STARTED_AT = "started-at";
	public static final String FINISHED_AT = "finished-at";
	
	public Suite() {
		super();
	}
	public Suite(String name, Long durationMs, Date startedAt, Date finishedAt) {
		super.setNodeName("Suite");
		this.name = name;
		this.durationMs = durationMs;
		this.startedAt = startedAt;
		this.finishedAt = finishedAt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDurationMs() {
		return durationMs;
	}
	public void setDurationMs(Long durationMs) {
		this.durationMs = durationMs;
	}
	public Date getFinishedAt() {
		return finishedAt;
	}
	public void setFinishedAt(Date finishedAt) {
		this.finishedAt = finishedAt;
	}
	public Date getStartedAt() {
		return startedAt;
	}
	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	
	
}
