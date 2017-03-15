package tests.students.suites;

import tests.students.testcases.MultiplayerTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TowerdefenseTestsuiteExtended3 {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite("Student tests for Towerdefense - Extended 3");
		suite.addTest(new JUnit4TestAdapter(MultiplayerTest.class));
		return suite;
	}
	
}
