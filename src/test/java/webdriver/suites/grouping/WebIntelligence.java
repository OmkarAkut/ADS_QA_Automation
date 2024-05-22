package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.reporting.WebIntelligenceReportRunningExistingWebIReport;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	WebIntelligenceReportRunningExistingWebIReport.class,
})
public class WebIntelligence {

}
