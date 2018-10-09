package com.optaris.testng.result.model;

import java.util.ArrayList;
import java.util.List;

public class TestClass extends Element{
	private String name;
	private List<TestMethod> lsTestMethod;
	
	public static final String NAME = "name";

	public TestClass() {
		super();
	}

	public TestClass(String name) {
		super.setNodeName("testClass");
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TestMethod> getLsTestMethod() {
		return lsTestMethod;
	}

	public void setLsTestMethod(List<TestMethod> lsTestMethod) {
		this.lsTestMethod = lsTestMethod;
	}
	
	public void addTestMethod(TestMethod pTestMethod) {
		if(lsTestMethod == null) {
			lsTestMethod = new ArrayList<TestMethod>();
		}
		lsTestMethod.add(pTestMethod);
	}
}
