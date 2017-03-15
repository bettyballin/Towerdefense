package tests.tutors.suites;

import tests.students.testcases.HighscoreTest;
import tests.students.testcases.LoadGameTest;
import tests.students.testcases.ParseMapExtended1;
import tests.students.testcases.SaveGameTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TowerdefenseTestsuiteExtended1 {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Tutor tests for Towerdefense - Extended 1");
		suite.addTest(new JUnit4TestAdapter(HighscoreTest.class));
		suite.addTest(new JUnit4TestAdapter(LoadGameTest.class));
		suite.addTest(new JUnit4TestAdapter(SaveGameTest.class));
		suite.addTest(new JUnit4TestAdapter(ParseMapExtended1.class));
		return suite;
	}
	
}
