package webdriver.globalscripts.accessibilitytests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	AnalyticsAXE.class,
	ContractingAXE.class,
	CostingAXE.class,
	DataMaintenanceAccessibilityTestsAtoZPages.class,
	EpisodesAXE.class,
//	LocalTestWebPageAXE.class,
	LogInPageAXE.class,
	MiscellaneousAXE.class,
	ReportingAXE.class,
	StatusAXE.class,
	SystemMaintenanceAXE.class,
})

public class TestSuiteAXE {

}
