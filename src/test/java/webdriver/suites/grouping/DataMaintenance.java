package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.accessibilitytests.DataMaintenanceAccessibilityTestsAtoZPages;
@RunWith(Suite.class)
@Suite.SuiteClasses({
	DataMaintenanceAccessibilityTestsAtoZPages.class,
})
public class DataMaintenance {

}
