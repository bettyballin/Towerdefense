package tests.tutors.suites;

import tests.students.testcases.KeyboardInputTest;
import tests.students.testcases.ParseMapTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TowerdefenseTestsuiteMinimal {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite("Tutor tests for Towerdefense - Minimal");
		suite.addTest(new JUnit4TestAdapter(ParseMapTest.class));
		suite.addTest(new JUnit4TestAdapter(KeyboardInputTest.class));
		return suite;
	}
}
