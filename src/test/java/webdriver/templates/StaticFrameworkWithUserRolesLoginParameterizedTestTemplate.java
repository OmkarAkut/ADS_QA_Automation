package webdriver.templates;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ExtentReport.ExtentReport;
import webdriver.globalstatic.LoginRolesTesting;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

/**
 This template is used for running parameterized login (user roles) based tests in the static framework.
 The parameters are Users or Roles and then test will run a full login-logout cycle the @Test method.
 It is designed for testing user role security authorizations.
 Configuration: The LoginRolesTesting and BeforeAfterRolesTesting
 classes take the place of LoginStatic and BeforeAfterStatic, resp., in the static framework.
 The test script then extends LoginRolesTesting.
 */

@RunWith(Parameterized.class)
public class StaticFrameworkWithUserRolesLoginParameterizedTestTemplate
        extends LoginRolesTesting {

  GeneralElementsMap geMap;

  public StaticFrameworkWithUserRolesLoginParameterizedTestTemplate(Users user) {
    super(user);
    System.out.println("User under test: " + user);
  }

  @Parameterized.Parameters (name = "{index}: Users={0}")
  public static Collection<Object[]> users() {
    Object[][] users = new Object[][] {
            {Users.valueOf("CostingDepartmentManager1")},
            {Users.valueOf("CustomRoleWithUcqcAdded")},
    };
    return Arrays.asList(users);
  }

  @Test
  public void testAssertUcqcLinkInCostingBubbleIsDisplayed() throws InterruptedException,Throwable {
	  ExtentReport.reportCreate("StaticFrameworkWithUserRolesLoginParameterizedTestTemplate", "webdriver.templates", "StaticFrameworkWithUserRolesLoginParameterizedTestTemplate");
    try {
		geMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
		waitForAjaxExtJs();
		assertThatElementIsDisplayed(geMap.getLandingPageBubbleCostingQuickLinkUnitCostQuickCalculation());
		ExtentReport.logPass("PASS", "testAssertUcqcLinkInCostingBubbleIsDisplayed");
	} catch (Exception|AssertionError e) {
	ExtentReport.logFail("FAIL", "testAssertUcqcLinkInCostingBubbleIsDisplayed", driver, e);
	fail(e.getMessage());
}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
