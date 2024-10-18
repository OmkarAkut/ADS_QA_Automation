package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.accessibilitytests.SystemMaintenanceAXE;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	SystemMaintenanceAXE.class,
})
public class SystemMaintenance {

}
