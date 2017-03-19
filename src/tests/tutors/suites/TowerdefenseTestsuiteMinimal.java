package tests.tutors.suites;

import tests.students.testcases.MainMenuTest;
import tests.tutors.testcases.EntityTestMinimal;
import tests.tutors.testcases.PathTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TowerdefenseTestsuiteMinimal {
	
	public static Test suite() {		
		TestSuite suite = new TestSuite("Tutor tests for Towerdefense - Minimal");
		suite.addTest(new JUnit4TestAdapter(MainMenuTest.class));
		suite.addTest(new JUnit4TestAdapter(PathTest.class));
		suite.addTest(new JUnit4TestAdapter(EntityTestMinimal.class));
		return suite;
	}
}
