package com.optaris.testng.result.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test extends Element{
	private String name;
	private Long durationMs;
	private Date startedAt, finishedAt;
	private List<TestClass> lsTestClass;
	
	public static final String NAME = "name";
	public static final String DURATION_MS = "duration-ms";
	public static final String STARTED_AT = "started-at";
	public static final String FINISHED_AT = "finished-at";
	
	
	public Test() {
		super();
	}
	public Test(String name, Long durationMs, Date startedAt, Date finishedAt) {
		super.setNodeName("Test");;
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
	public List<TestClass> getLsTestClass() {
		return lsTestClass;
	}
	public void setLsTestClass(List<TestClass> lsTestClass) {
		this.lsTestClass = lsTestClass;
	}
	public void addTestClass(TestClass pTestClass) {
		if(lsTestClass == null) {
			lsTestClass = new ArrayList<TestClass>();
		}
		lsTestClass.add(pTestClass);
	}
	
}
