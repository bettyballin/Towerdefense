package tests.students.suites;

import tests.students.testcases.EntityTestExtended2;
import tests.tutors.testcases.OptionTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TowerdefenseTestsuiteExtended2 {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite("Student tests for Towerdefense - Extended 2");
		suite.addTest(new JUnit4TestAdapter(EntityTestExtended2.class));
		suite.addTest(new JUnit4TestAdapter(OptionTest.class));
		return suite;
	}
	
}
