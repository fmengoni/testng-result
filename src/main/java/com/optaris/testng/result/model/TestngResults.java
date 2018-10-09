package com.optaris.testng.result.model;

public class TestngResults extends Element{
	private Integer skipped, failed, ignored, total, passed;
	private Suite suite;
	
	public Suite getSuite() {
		return suite;
	}
	public void setSuite(Suite suite) {
		this.suite = suite;
	}
	public TestngResults() {
		super();
	}
	public TestngResults(String nodeName, Integer skipped, Integer failed, Integer ignored, Integer total,
			Integer passed) {
		super();
		super.setNodeName(nodeName);
		this.skipped = skipped;
		this.failed = failed;
		this.ignored = ignored;
		this.total = total;
		this.passed = passed;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getSkipped() {
		return skipped;
	}
	public void setSkipped(Integer skipped) {
		this.skipped = skipped;
	}
	public Integer getFailed() {
		return failed;
	}
	public void setFailed(Integer failed) {
		this.failed = failed;
	}
	public Integer getIgnored() {
		return ignored;
	}
	public void setIgnored(Integer ignored) {
		this.ignored = ignored;
	}
	public Integer getPassed() {
		return passed;
	}
	public void setPassed(Integer passed) {
		this.passed = passed;
	}
}
