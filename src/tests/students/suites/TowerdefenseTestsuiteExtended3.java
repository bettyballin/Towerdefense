package tests.students.suites;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;
import tests.tutors.testcases.AdvancedLogicTest;

public class TowerdefenseTestsuiteExtended3 {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Student tests for Towerdefense - Extended 3");
		suite.addTest(new JUnit4TestAdapter(AdvancedLogicTest.class));
		return suite;
	}
	
}
