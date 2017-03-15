package tests.students.suites;

import tests.students.testcases.LimitedAmmoTest;
import tests.students.testcases.ParseMapExtended2;
import tests.students.testcases.TimeLimitTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TowerdefenseTestsuiteExtended2 {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite("Student tests for Towerdefense - Extended 2");
		suite.addTest(new JUnit4TestAdapter(ParseMapExtended2.class));
		suite.addTest(new JUnit4TestAdapter(TimeLimitTest.class));
		suite.addTest(new JUnit4TestAdapter(LimitedAmmoTest.class));
		return suite;
	}
	
}
