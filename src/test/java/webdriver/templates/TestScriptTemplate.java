package webdriver.templates;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestScriptTemplate extends GoHelper {

  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println("Test Class: " + TestScriptTemplate.class.getSimpleName());
    System.out.println("Test Class: Test Push Imran");
    Login.loginUser("AutomationTester1");
  }

  @Test
  public void testAdsLoginLogout() {
    waitForPresenceOfElement("//img[@alt='Harris Affinity Logo']");
    assertThatElementIsDisplayed(getWebElement("//img[@alt='Harris Affinity Logo']"));
  }

}
