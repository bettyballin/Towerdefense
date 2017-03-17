package tests.students.suites;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TowerdefenseTestsuiteExtended1 {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite("Student tests for Towerdefense - Extended 1");
		//suite.addTest(new JUnit4TestAdapter(ParseMapExtended1.class));
		return suite;
	}
	
}
