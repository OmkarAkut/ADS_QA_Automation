package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.accessibilitytests.AnalyticsAXE;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	AnalyticsAXE.class,
})
public class Analytics {

}
