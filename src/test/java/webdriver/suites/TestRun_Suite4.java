package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.help.HelpSuite;
import webdriver.scripts.contracting.ContractingSuite;
import webdriver.scripts.datamaintenance.utilities.UtilitiesTestSuite;
import webdriver.scripts.security.ucqcroles.UcqcRoleBasedTestSuite;
import webdriver.scripts.status.calculationstatus.CalculationStatusSuite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	ContractingSuite.class,
	UtilitiesTestSuite.class,
	HelpSuite.class,
	CalculationStatusSuite.class,
	UcqcRoleBasedTestSuite.class,

	
	
})

public class TestRun_Suite4 {

}










