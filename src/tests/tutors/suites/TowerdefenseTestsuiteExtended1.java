package tests.tutors.suites;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;
import tests.tutors.testcases.EntityTestExtended1;

public class TowerdefenseTestsuiteExtended1 {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Tutor tests for Towerdefense - Extended 1");
		suite.addTest(new JUnit4TestAdapter(EntityTestExtended1.class));
		return suite;
	}
	
}
