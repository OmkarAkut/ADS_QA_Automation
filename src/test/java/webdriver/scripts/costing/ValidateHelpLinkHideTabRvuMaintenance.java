package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
/** Regression Test ADS-5992 **/
public class ValidateHelpLinkHideTabRvuMaintenance extends GoHelper {
	String costModel = "QA Marina";
	static CostingMap costing;
	String[] filter = {"Cost Model Name", "Is", "Equal To", costModel};

	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("ValidateHelpLinkHideTabRvuMaintenance", "webdriver.scripts.costing", "ValidateHelpLinkHideTabRvuMaintenance");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			Login.loginUser("CostingDepartmentManager1");
			Thread.sleep(10000);
			goToPage("Rvu Maintenance");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ValidateHelpAndHideOptions() throws Throwable {
		try {
			costing.getRvuMaintenanceButtonFilter().click();
			doFilterCreate(filter);
			costing.getRvuMaintenanceButtonMaintainRVUs().click();
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			doClick(costing.getRvuMaintenanceButtonMaintainRVUs());
			assertElementIsDisplayedWithXpath("//*[contains(@onclick,'csrvumfd.htm') and @class='listhelpLnk']");		
			assertElementIsDisplayedWithXpath("//div[contains(@class,'hidetoppx expand-icon')]//span[text()='Hide']");
			ExtentReport.logPass("PASS", "test01ValidateHelpAndHideOptions");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateHelpAndHideOptions", driver, e);

			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateHideAndShowFunctions() throws Throwable {
		try {
			doClick(costing.getRvuHideButton());
			assertElementIsDisplayed(costing.getRvuShowButton());
			doClick(costing.getRvuShowButton());
			assertElementIsDisplayed(costing.getRvuFilterPanel());
			ExtentReport.logPass("PASS", "test02ValidateHideAndShowFunctions");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateHideAndShowFunctions", driver, e);

			fail(e.getMessage());
		}
	}
	 @AfterClass
		public static void endtest() throws Exception {
			ExtentReport.report.flush();
		}
}
