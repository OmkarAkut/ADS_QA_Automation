package webdriver.templates;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.globalstatic.LoginRolesTesting;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

public class StaticUserRolesParameterizedScriptSingleTest extends LoginRolesTesting {

  public StaticUserRolesParameterizedScriptSingleTest() {
    super(Users.AutomationTester1);
  }

  @Test
  public void testAssertUcqcLinkInCostingBubbleIsDisplayed() throws InterruptedException ,Throwable{
	  ExtentReport.reportCreate("StaticUserRolesParameterizedScriptSingleTest", "webdriver.templates", "StaticUserRolesParameterizedScriptSingleTest");
	  try {
		GeneralElementsMap geMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
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

