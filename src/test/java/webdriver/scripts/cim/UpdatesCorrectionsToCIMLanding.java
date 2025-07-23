package webdriver.scripts.cim;

import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20639 **/
public class UpdatesCorrectionsToCIMLanding extends CimHelper{
	private static CimMap cimMap;

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("UpdatesCorrectionsToCIMLanding", "webdriver.scripts.cim",
				"UpdatesCorrectionsToCIMLanding");
		try {
			cimMap = BuildMap.getInstance(driver, CimMap.class);
			Login.loginUser("CostAnalyst1");
			goToPage("Cost Integration Manager");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01Validate_CalcStatus_Header_HyperLink_20639() throws Throwable {
		try {
			assertElementIsDisplayed(cimMap.getcimCalcStatusHeader());
			assertElementIsDisplayed(cimMap.getcalcStatusPgeLink());
			ExtentReport.logPass("PASS", "test01Validate_CalcStatus_Header_HyperLink_20639");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Validate_CalcStatus_Header_HyperLink_20639", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02Validate_CalcStatus_HyperLink_20639() throws Throwable {
		try {
			doClick(cimMap.getcalcStatusPgeLink());
			waitForElementToBeVisible(cimMap.getcalcStatusPage());
			assertElementIsDisplayed(cimMap.getcalcStatusPage());
			assertTheElementIsDisabled(driver.findElement(By.xpath("//div[contains(@id,'statusreportlayout')]//span[text()='Clear Filter']/../../..")), printout);
			assertHasElements(cimMap.getcalcPageView());
			assertHasElements(cimMap.getcalcPageDelete());
			assertHasElements(cimMap.getcalcPageDownload());
			assertElementIsDisplayedWithXpath("//span[contains(@id,'tab') and contains(text(),' Close Calculation Status')]");
			doClosePageOnLowerBar("Calculation Status");
			ExtentReport.logPass("PASS", "test02Validate_CalcStatus_HyperLink_20639");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_CalcStatus_HyperLink_20639", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
