package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.accessibilitytests.AnalyticsAXE;
import webdriver.globalscripts.accessibilitytests.ContractingAXE;
import webdriver.globalscripts.accessibilitytests.CostingAXE;
import webdriver.globalscripts.accessibilitytests.DataMaintenanceAccessibilityTestsAtoZPages;
import webdriver.globalscripts.accessibilitytests.EpisodesAXE;
import webdriver.globalscripts.accessibilitytests.LogInPageAXE;
import webdriver.globalscripts.accessibilitytests.MiscellaneousAXE;
import webdriver.globalscripts.accessibilitytests.ReportingAXE;
import webdriver.globalscripts.accessibilitytests.StatusAXE;
import webdriver.globalscripts.accessibilitytests.SystemMaintenanceAXE;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AnalyticsAXE.class,
	ContractingAXE.class,
	CostingAXE.class,
	DataMaintenanceAccessibilityTestsAtoZPages.class,
	EpisodesAXE.class,
	LogInPageAXE.class,
	MiscellaneousAXE.class,
	ReportingAXE.class,
	StatusAXE.class,
	SystemMaintenanceAXE.class,


})
public class AccessibilityTests {

}
