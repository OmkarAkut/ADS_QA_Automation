package webdriver.globalscripts.accessibilitytests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//Last update 07-1-2020.
@RunWith(Suite.class)
@Suite.SuiteClasses({

        LogInPageAccessibilityTest.class,
        AdsAccessibilityMainPagesSuite.class,

})

public class AxeAccessibilityTestSuite {
}
