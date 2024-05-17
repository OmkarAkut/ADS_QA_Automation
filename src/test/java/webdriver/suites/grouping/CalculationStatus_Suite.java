package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.costing.ValidateCalculationStatusAllStatus;
import webdriver.scripts.costing.ValidateCalculationStatusPage;
import webdriver.scripts.status.calculationstatus.CalculationStatusFilterByTypeAds1248;
import webdriver.scripts.status.calculationstatus.CalculationStatusPageSmokeTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	ValidateCalculationStatusAllStatus.class,
	ValidateCalculationStatusPage.class,
	CalculationStatusFilterByTypeAds1248.class,
	CalculationStatusPageSmokeTest.class,
	
})
public class CalculationStatus_Suite {

	

}
