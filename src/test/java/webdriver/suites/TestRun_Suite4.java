package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.checktests.ChecktestsSuite;
import webdriver.globalscripts.help.HelpSuite;
import webdriver.globalscripts.pagetests.PagetestsSuite;
import webdriver.scripts.contracting.ContractingSuite;
import webdriver.scripts.datamaintenance.az.AZSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

	ContractingSuite.class,
	PagetestsSuite.class,
	HelpSuite.class,
	ChecktestsSuite.class,
	AZSuite.class,
	
})

public class TestRun_Suite4 {

}










