package webdriver.globalscripts.accessibilitytests_old;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import ExtentReport.ExtentReport;
import webdriver.globalstatic.BeforeAfterStatic;
import webdriver.utilities.Axe;

public class LogInPageAccessibilityTest extends BeforeAfterStatic {

  private Axe ax = new Axe();
  private boolean createJsonReport = false;
  //private static final Logger logger = LogManager.getLogger();

  /** Updated 09-09-19. Reports Axe accessibility violations on login page of ADS.
   */

  @Rule
  public TestName name = new TestName();

  @Test
  public void testLogInPageAccessibilityCheck() throws Throwable {
	  ExtentReport.reportCreate("LogInPageAccessibilityTest", "webdriver.globalscripts.accessibilitytests", "LogInPageAccessibilityTest");
    try {
		ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
		ExtentReport.logPass("PASS", "testLocalAccessibilityScript");
	} catch (Exception|AssertionError e) {
	ExtentReport.logFail("FAIL", "testLocalAccessibilityScript", driver, e);
	fail(e.getMessage());
	}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
