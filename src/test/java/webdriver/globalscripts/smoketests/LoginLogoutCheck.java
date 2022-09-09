package webdriver.globalscripts.smoketests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;

public class LoginLogoutCheck extends GoHelper {

  @BeforeClass
    public static void setupScript() throws Exception {
	 ExtentReport.reportCreate("LoginLogoutCheck", "webdriver.globalscripts.smoketests", "LoginLogoutCheck");
    System.out.println("Test Class: " + LoginLogoutCheck.class.getSimpleName());
    /*
    modified by Omkar on 26/5/22 as only aadmin user is available for qa3 env
    Login.loginUser("AutomationTester1");
    */
    Login.loginUser("AutomationTesterAdmin");
  }

  @Test
  public void testAdsLoginLogout() throws Throwable {
    try {
		waitForPresenceOfElement("//img[@alt='Harris Affinity Logo']");
		assertThatElementIsDisplayed(getWebElement("//img[@alt='Harris Affinity Logo']"));
		ExtentReport.logPass("PASS", "testAdsLoginLogout");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "testAdsLoginLogout", driver, e);
	}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
