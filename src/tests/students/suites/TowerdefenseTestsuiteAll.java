package tests.students.suites;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TowerdefenseTestsuiteAll {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite("All student tests for Towerdefense");
		
		suite.addTest(tests.students.suites.TowerdefenseTestsuiteMinimal.suite());
		suite.addTest(tests.students.suites.TowerdefenseTestsuiteExtended1.suite());
		suite.addTest(tests.students.suites.TowerdefenseTestsuiteExtended2.suite());
		suite.addTest(tests.students.suites.TowerdefenseTestsuiteExtended3.suite());
		
		return suite;
	}
}
