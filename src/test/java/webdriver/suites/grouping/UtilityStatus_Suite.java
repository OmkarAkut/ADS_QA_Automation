package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.systemmaintenance.UIValidationStatusUIValidateUtilityStatusPage;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	UIValidationStatusUIValidateUtilityStatusPage.class,
})
public class UtilityStatus_Suite {

}
