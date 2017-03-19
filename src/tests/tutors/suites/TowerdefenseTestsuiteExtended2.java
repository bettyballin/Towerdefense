package tests.tutors.suites;

import tests.tutors.testcases.EntityTestExtended2;
import tests.tutors.testcases.OptionTestExtended2;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TowerdefenseTestsuiteExtended2 {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Tutor tests for Towerdefense - Extended 2");
		suite.addTest(new JUnit4TestAdapter(EntityTestExtended2.class));
		suite.addTest(new JUnit4TestAdapter(OptionTestExtended2.class));
		return suite;
	}
	
}
