package webdriver.globalscripts.accessibilitytests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		AdsAccessibilityCostingSuite.class,
        LogInPageAccessibilityTest.class,
        AdsAccessibilityMainPagesSuite.class,
        DataMaintenanceAccessibilityTestsAtoZPages.class,
        LocalAccessibilityScript.class,

})
public class AccessibilitytestsSuite {

}
