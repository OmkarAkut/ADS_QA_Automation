package webdriver.globalscripts.smoketests;

import org.junit.BeforeClass;
import org.junit.Test;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;

public class LoginLogoutCheck extends GoHelper {

  @BeforeClass
    public static void setupScript() throws Exception {
    System.out.println("Test Class: " + LoginLogoutCheck.class.getSimpleName());
    /*
    modified by Omkar on 26/5/22 as only aadmin user is available for qa3 env
    Login.loginUser("AutomationTester1");
    */
    Login.loginUser("AutomationTesterAdmin");
  }

  @Test
  public void testAdsLoginLogout() {
	  System.out.println("kjsncafldjflrsejg");
    waitForPresenceOfElement("//img[@alt='Harris Affinity Logo']");
    assertThatElementIsDisplayed(getWebElement("//img[@alt='Harris Affinity Logo']"));
  }
}
