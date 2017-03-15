package tests.tutors.suites;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TowerdefenseTestsuiteAll {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite("All tutor tests for Towerdefense");
		
		suite.addTest(tests.tutors.suites.TowerdefenseTestsuiteMinimal.suite());
		suite.addTest(tests.tutors.suites.TowerdefenseTestsuiteExtended1.suite());
		suite.addTest(tests.tutors.suites.TowerdefenseTestsuiteExtended2.suite());
		suite.addTest(tests.tutors.suites.TowerdefenseTestsuiteExtended3.suite());
		
		return suite;
	}
}
