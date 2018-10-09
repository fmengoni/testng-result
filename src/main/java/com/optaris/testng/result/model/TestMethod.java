package com.optaris.testng.result.model;

import java.util.Date;

public class TestMethod extends Element{
	private String status, signature, name;
	private Long durationMs;
	private Date startedAt, finishedAt;
	
	public static final String STATUS = "status";
	public static final String SIGNATURE = "signature";
	public static final String NAME = "name";
	public static final String DURATION_MS = "duration-ms";
	public static final String STARTED_AT = "started-at";
	public static final String FINISHED_AT = "finished-at";
	
	public TestMethod() {
		super();
	}
	public TestMethod(String status, String signature, String name, Long durationMs, Date startedAt, Date finishedAt) {
		super.setNodeName("TestMethod");
		this.status = status;
		this.signature = signature;
		this.name = name;
		this.durationMs = durationMs;
		this.startedAt = startedAt;
		this.finishedAt = finishedAt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
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
	public Date getStartedAt() {
		return startedAt;
	}
	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}
	public Date getFinishedAt() {
		return finishedAt;
	}
	public void setFinishedAt(Date finishedAt) {
		this.finishedAt = finishedAt;
	}
}
