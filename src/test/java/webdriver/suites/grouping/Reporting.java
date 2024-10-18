package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.accessibilitytests.ReportingAXE;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ReportingAXE.class,
})
public class Reporting {

}
