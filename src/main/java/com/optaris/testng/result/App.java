package com.optaris.testng.result;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.optaris.testng.result.model.Suite;
import com.optaris.testng.result.model.Test;
import com.optaris.testng.result.model.TestClass;
import com.optaris.testng.result.model.TestMethod;
import com.optaris.testng.result.model.TestngResults;

/**
 * Hello world!
 *String jdbcDriver = "com.mysql.jdbc.Driver";  
    	String dbUrl = "jdbc:mysql://localhost:3306/at";
    	String USER = "root";
    	String PASS = "root";
 */
public class App 
{
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost:3306/at";
	private static final String USER = "root";
	private static final String PASS = "root";
	
    public static void main( String[] args )
    {
    	try {
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	    	File testngResult = new File("testng-results.xml");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(testngResult);
	    	
	    	//optional, but recommended
	    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	    	doc.getDocumentElement().normalize();

	    	Element testNgResultElement = doc.getDocumentElement();
	    	TestngResults testNgResult = 
	    			new TestngResults(
	    					testNgResultElement.getNodeName(),
	    					new Integer(testNgResultElement.getAttribute("skipped")),
	    					new Integer(testNgResultElement.getAttribute("failed")),
	    					new Integer(testNgResultElement.getAttribute("ignored")),
	    					new Integer(testNgResultElement.getAttribute("total")),
	    					new Integer(testNgResultElement.getAttribute("passed"))
	    					);

	    	{
	    		NodeList testNgResultchildrens = testNgResultElement.getChildNodes();
	    		for (int i = 0; i < testNgResultchildrens.getLength(); i++) {
	    			Node testNgResultChild = testNgResultchildrens.item(i);
	    			if (testNgResultChild.getNodeType() == Node.ELEMENT_NODE) {
	    				if(testNgResultChild.getNodeName().equals("suite")) {
	    					NamedNodeMap attributes = testNgResultChild.getAttributes();
	    					Suite suite = new Suite(
	    							getAttributeValue(attributes, Suite.NAME), 
	    							new Long(getAttributeValue(attributes, Suite.DURATION_MS)), 
	    							df.parse(getAttributeValue(attributes, Suite.STARTED_AT)), 
	    							df.parse(getAttributeValue(attributes, Suite.FINISHED_AT)));
	    					testNgResult.setSuite(suite);  
		    	            
	    					NodeList suiteChildren = testNgResultChild.getChildNodes();
	    					for (int j = 0; j < suiteChildren.getLength(); j++) {
	    		    			Node suiteChild = suiteChildren.item(j);
	    		    			if (suiteChild.getNodeType() == Node.ELEMENT_NODE) {
	    		    				if(suiteChild.getNodeName().equals("test")) {
	    		    					NamedNodeMap testAttributes = suiteChild.getAttributes();
	    		    					Test test = new Test(
	    		    							getAttributeValue(testAttributes, Test.NAME), 
	    		    							new Long(getAttributeValue(testAttributes, Test.DURATION_MS)), 
	    		    							df.parse(getAttributeValue(testAttributes, Test.STARTED_AT)), 
	    		    							df.parse(getAttributeValue(testAttributes, Test.FINISHED_AT)));
	    		    					suite.setTest(test);
	    		    					
	    		    					NodeList testChildren = suiteChild.getChildNodes();
	    		    					for (int k = 0; k < testChildren.getLength(); k++) {
	    		    		    			Node testChild = testChildren.item(k);
	    		    		    			if (testChild.getNodeType() == Node.ELEMENT_NODE) {
	    		    		    				if(testChild.getNodeName().equals("class")) {
	    		    		    					NamedNodeMap testClassAttributes = testChild.getAttributes();
	    		    		    					TestClass testClass = new TestClass(
	    		    		    						getAttributeValue(testClassAttributes, TestClass.NAME)	
	    		    		    					);
	    		    		    					test.addTestClass(testClass);
	    		    		    					
	    		    		    					//testChild es el nodo class
	    		    		    					NodeList classChildren = testChild.getChildNodes();
	    		    		    					for (int a = 0; a < classChildren.getLength(); a++) {
	    		    		    						Node classChild = classChildren.item(a);
	    		    		    		    			if (classChild.getNodeType() == Node.ELEMENT_NODE) {
	    		    		    		    				if(classChild.getNodeName().equals("test-method")) {
	    		    		    		    					NamedNodeMap testMethodAttributes = classChild.getAttributes();
	    		    		    		    					TestMethod testMethod = new TestMethod(
	    		    		    		    						getAttributeValue(testMethodAttributes, TestMethod.STATUS),
	    		    		    		    						getAttributeValue(testMethodAttributes, TestMethod.SIGNATURE),
	    		    		    		    						getAttributeValue(testMethodAttributes, TestMethod.NAME),
	    		    		    		    						new Long(getAttributeValue(testMethodAttributes, TestMethod.DURATION_MS)),
	    		    		    		    						df.parse(getAttributeValue(testMethodAttributes, TestMethod.STARTED_AT)),
	    		    		    		    						df.parse(getAttributeValue(testMethodAttributes, TestMethod.FINISHED_AT))
	    		    		    		    					);
	    		    		    		    					testClass.addTestMethod(testMethod);
	    		    		    		    				}
	    		    		    		    			}
	    		    		    					}
	    		    		    				}
	    		    		    			}
	    		    					}
	    		    				}
	    		    			}
	    					}
	    				}
	    	        }
				}
	    	}
	    	saveResults(testNgResult);
	    	
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    }
    
    private static void saveResults(TestngResults testNgResult) {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		
    		pstmt = conn.prepareStatement("INSERT INTO at.results(id_ejecucion, total, passed, failed, skipped, ignored) values(1, ?, ?, ?, ?, ?)");
    		
    		pstmt.setInt(1, testNgResult.getTotal());
    		pstmt.setInt(2, testNgResult.getPassed());
    		pstmt.setInt(3, testNgResult.getFailed());
    		pstmt.setInt(4, testNgResult.getSkipped());
    		pstmt.setInt(5, testNgResult.getIgnored());
    		int result = pstmt.executeUpdate();
    		pstmt.close();
    		
    		if(result == 1) {
    			Statement stmt = conn.createStatement();
    			ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM at.results");
    			rs.next();
    			int resultId = rs.getInt(1);
    			
    			stmt.close();
    			rs.close();
    			pstmt = conn.prepareStatement("INSERT INTO at.suites(id_result, name, duration_ms, started_at, finished_at) values(?, ?, ?, ?, ?)");
    			pstmt.setLong(1, resultId);
    			pstmt.setString(2, testNgResult.getSuite().getName());
    			pstmt.setLong(3, testNgResult.getSuite().getDurationMs());
    			pstmt.setTimestamp(4, new Timestamp(testNgResult.getSuite().getStartedAt().getTime()));
    			pstmt.setTimestamp(5, new Timestamp(testNgResult.getSuite().getFinishedAt().getTime()));
    			result = pstmt.executeUpdate();
    			pstmt.close();
    			
    			if(result == 1) {
    				stmt = conn.createStatement();
    				rs = stmt.executeQuery("SELECT MAX(id) FROM at.suites");
    				rs.next();
    				resultId = rs.getInt(1);
    				stmt.close();
    				rs.close();
    				
    				pstmt = conn.prepareStatement("INSERT INTO at.tests(id_suite, name, duration_ms, started_at, finished_at) values(?, ?, ?, ?, ?)");
    				pstmt.setLong(1, resultId);
    				pstmt.setString(2, testNgResult.getSuite().getTest().getName());
    				pstmt.setLong(3, testNgResult.getSuite().getTest().getDurationMs());
    				pstmt.setTimestamp(4, new Timestamp(testNgResult.getSuite().getTest().getStartedAt().getTime()));
    				pstmt.setTimestamp(5, new Timestamp(testNgResult.getSuite().getTest().getFinishedAt().getTime()));
    				result = pstmt.executeUpdate();
    				pstmt.close();
    				
    				if(result == 1) {
    					stmt = conn.createStatement();
        				rs = stmt.executeQuery("SELECT MAX(id) FROM at.tests");
        				rs.next();
        				resultId = rs.getInt(1);
        				stmt.close();
        				rs.close();
        				
        				for (Iterator<TestClass> iterator = testNgResult.getSuite().getTest().getLsTestClass().iterator(); iterator.hasNext();) {
							TestClass testClass = iterator.next();
							pstmt = conn.prepareStatement("INSERT INTO at.tests_class(id_test, name) values (?, ?)");
							pstmt.setInt(1, resultId);
							pstmt.setString(2, testClass.getName());
							result = pstmt.executeUpdate();
							pstmt.close();
							
							if(result == 1) {
								stmt = conn.createStatement();
		        				rs = stmt.executeQuery("SELECT MAX(id) FROM at.tests_class");
		        				rs.next();
		        				resultId = rs.getInt(1);
		        				stmt.close();
		        				rs.close();
		        				
		        				for (Iterator<TestMethod> iterator2 = testClass.getLsTestMethod().iterator(); iterator2.hasNext();) {
									TestMethod testMethod = iterator2.next();
									pstmt = conn.prepareStatement("INSERT INTO at.test_method(id_testclass, status, signature, name, duration_ms, started_at, finished_at) values (?, ?, ?, ?, ?,?, ?)");
									pstmt.setInt(1, resultId);
									pstmt.setString(2, testMethod.getStatus());
									pstmt.setString(3, testMethod.getSignature());
									pstmt.setString(4, testMethod.getName());
									pstmt.setLong(5, testMethod.getDurationMs());
									pstmt.setTimestamp(6, new Timestamp(testMethod.getStartedAt().getTime()));
									pstmt.setTimestamp(7, new Timestamp(testMethod.getFinishedAt().getTime()));
									result = pstmt.executeUpdate();
									pstmt.close();
									
									System.out.println(result);
								}
							}
						}
        				
        				System.out.println(resultId);
    				} else {
    					System.out.println("Error al querer guardar los resultados del objeto Test");
    				}
    			} else {
    				System.out.println("Error al querer guardar los resultados del objeto Suite");	
    			}
    		} else {
    			System.out.println("Error al querer guardar los resultados del objeto testNgResult");
    		}
    	} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static String getAttributeValue(NamedNodeMap attributes, String attributeName) {
        Attr attribute = (Attr) attributes.getNamedItem(attributeName);
        if (attribute != null) {
            return attribute.getValue();
        }

        return null;
    }
}
