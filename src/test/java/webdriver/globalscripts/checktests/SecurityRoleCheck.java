package webdriver.globalscripts.checktests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.WaitHelper;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;

public class SecurityRoleCheck extends WaitHelper {

  static GeneralElementsMap mainMap;
  Integer[] expectedTabs = { 0, 1, 0, 0, 0, 0, 0, 0, 0 };
  WebElement[] mainTabs = {
          mainMap.getAnalyticsTab(),
          mainMap.getReportingTab(),
          mainMap.getCostingTab(),
          mainMap.getContractingTab(),
          mainMap.getEpisodesTab(),
          mainMap.getBudgetingTab(),
          mainMap.getDataMaintenanceTab(),
          mainMap.getSystemMaintenanceTab(),
          mainMap.getStatusTab()
  };

  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("SecurityRoleCheck", "webdriver.globalscripts.checktests","SecurityRoleCheck");

    try {
		System.out.println("Test Class: " + SecurityRoleCheck.class.getSimpleName());
		mainMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
		Login.loginUser("ContractReviewer1");
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
	ExtentReport.logFail("FAIL", "setupScript", driver, e);
	fail(e.getMessage());
	}
  }

  @Test
  public void testSecurityRolesTest() throws InterruptedException,Throwable{
    try {
		assertMainTabsRolesTest(expectedTabs, mainTabs, printout);
		ExtentReport.logPass("PASS", "testSecurityRolesTest");
	} catch (Exception|AssertionError e) {
	ExtentReport.logFail("FAIL", "testSecurityRolesTest", driver, e);
	fail(e.getMessage());
	}
  }

  public void assertMainTabsRolesTest(Integer[] expectedTabs, WebElement[] elements, boolean printout)
    throws InterruptedException {
    ArrayList<Integer> resultsList = new ArrayList<>();
    waitForAjaxExtJs();
    waitForElementDoWhileLoop(mainMap.getUserDropdown(), printout);
    for (WebElement element : elements) {
      try {
        assertTrue(element.isDisplayed());
        resultsList.add(1);
      } catch (Throwable e) {
        resultsList.add(0);
      }
    }
    assertThat(resultsList.toArray(), equalTo(expectedTabs));
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}