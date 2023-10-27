package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.accessibilitytests.TestSuiteAXE;
import webdriver.globalscripts.help.HelpSuite;
import webdriver.globalscripts.pagetests.PagetestsSuite;
import webdriver.globalscripts.securitytests.SecurityTestsSuite;
import webdriver.scripts.contracting.ContractingSuite;
import webdriver.scripts.datamaintenance.maintaindata.MaintaindataTestSuite;
import webdriver.scripts.datamaintenance.utilities.UtilitiesTestSuite;
import webdriver.scripts.episodes.EpisodesTestSuite;
import webdriver.scripts.reporting.ReportingTestSuite;
import webdriver.scripts.systemmaintenance.SystemMaintenanceTestSuite;





@RunWith(Suite.class)
@Suite.SuiteClasses({
	SystemMaintenanceTestSuite.class,
	EpisodesTestSuite.class,
	ReportingTestSuite.class,
	ContractingSuite.class,
	UtilitiesTestSuite.class,
	MaintaindataTestSuite.class,
	TestSuiteAXE.class,
	PagetestsSuite.class,
	HelpSuite.class,
	SecurityTestsSuite.class,
})

public class TestRun_Suite {

}










