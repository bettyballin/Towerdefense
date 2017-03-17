package tests.students.suites;

import tests.students.testcases.TowerTest;
import tests.students.testcases.PathTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TowerdefenseTestsuiteExtended2 {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite("Student tests for Towerdefense - Extended 2");
		suite.addTest(new JUnit4TestAdapter(PathTest.class));
		suite.addTest(new JUnit4TestAdapter(TowerTest.class));
		return suite;
	}
	
}
