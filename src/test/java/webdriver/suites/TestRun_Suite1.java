package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.accessibilitytests.TestSuiteAXE;
import webdriver.globalscripts.checktests.ChecktestsSuite;
import webdriver.scripts.cim.TestSuiteForCIM;
import webdriver.scripts.episodes.EpisodesTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestSuiteForCIM.class,
	EpisodesTestSuite.class,
	ChecktestsSuite.class,
	TestSuiteAXE.class,

	

	
})

public class TestRun_Suite1 {

}










