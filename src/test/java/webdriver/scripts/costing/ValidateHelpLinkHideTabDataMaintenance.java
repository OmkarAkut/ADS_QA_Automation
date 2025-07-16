package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression Test ADS-5992 **/
public class ValidateHelpLinkHideTabDataMaintenance extends CalculationHelper {
	final static String aTozPage = "RVU Maintenance";
	static String[] filter= {"Cost Model Name","Is","Equal To","QA Marina"};
	static CostingMap costing;
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("ValidateHelpLinkHideTabDataMaintenance", "webdriver.scripts.costing", "ValidateHelpLinkHideTabDataMaintenance");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			Login.loginUser("CostAnalyst1");
			waitForDisplayedSpinnerToEnd();	
			goToPage("Maintain Data");
			waitForDisplayedSpinnerToEnd();
			selectMaintainDataAtoZ(aTozPage);			
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01VerifyRvuMaintenancePageIsShown_ADS_5992() throws Throwable {
		try {
			assertElementTextWithXpath("//*[text()='RVU Maintenance'][contains(@id,'rvumasterlist')]", "RVU Maintenance", printout);
			ExtentReport.logPass("PASS", "test01VerifyRvuMaintenancePageIsShown");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01VerifyRvuMaintenancePageIsShown", driver, e);

			fail(e.getMessage());
		}
	}
	@Test
	public void test02VerifyHideHelpOptions_ADS_5992() throws Throwable {
		try {
			doClick("//*[contains(@id,'-body')]/descendant::span[text()='Filter']");
			doFilterCreate(filter);
			tableDoubleClickCellFirstColumn("QA Marina");
			assertElementIsDisplayedWithXpath("//*[contains(@onclick,'csrvumfd.htm') and @class='listhelpLnk']");		
			assertElementIsDisplayedWithXpath("//a[contains(@class,'expand-icon')]//span[text()='Hide']");
			} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01VerifyRvuMaintenancePageIsShown", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateHideAndShowFunctions_ADS_5992() throws Throwable {
		try {
			doClick(costing.getRvuHideButton());
			assertElementIsDisplayed(costing.getRvuShowButton());
			doClick(costing.getRvuShowButton());
			assertElementIsDisplayed(costing.getRvuFilterPanel());
			ExtentReport.logPass("PASS", "test03ValidateHideAndShowFunctions");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateHideAndShowFunctions", driver, e);

			fail(e.getMessage());
		}
	}
	 @AfterClass
		public static void endtest() throws Exception {
			ExtentReport.report.flush();
		}
}
