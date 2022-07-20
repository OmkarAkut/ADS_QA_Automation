package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.globalscripts.pagetests.BuildVerificationTestScript;

/* Run this suite in the build pipeline as the last step (right after deployment).
 * Updated: 06-02-2021 for 10.4.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

      BuildVerificationTestScript.class

})

public class BuildVerificationTestSuite {
}
