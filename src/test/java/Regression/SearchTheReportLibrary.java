package Regression;
import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ReportingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression Test for Test Ticket ADS-5663. **/
public class SearchTheReportLibrary extends GoHelper {
	private static ReportingMap reportMap;
	static String reportName = "Cost per RVU";

	@BeforeClass
	public static void setupScript() throws InterruptedException, Throwable {
		ExtentReport.reportCreate("SearchTheReportLibrary", "webdriver.scripts.reporting", "SearchTheReportLibrary");

		try {
			reportMap = BuildMap.getInstance(driver, ReportingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Report Library");
			waitForDisplayedSpinnerToEnd();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")));
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-5663
	@Test
	public void test01SearchReportLibrary_5663() throws Throwable {
		try {
			doClick(reportMap.getReportLibraryPageFormFieldSearch());
			ContractModelsHelper.keyInValues(reportMap.getReportLibraryPageFormFieldSearch(), reportName);
			assertTextIsDisplayed("Cost per RVU");
			ExtentReport.logPass("PASS", "test01SearchReportLibrary");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01SearchReportLibrary", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test02ClearSearch() throws Throwable {
		try {
			ContractModelsHelper.keyInValues(reportMap.getReportLibraryPageFormFieldSearch(), "");
			Thread.sleep(2000);
// verify there are more than 1 results available after clearing the filter. Random 3rd element is checked
			WebElement otherValue = driver.findElement(By.xpath("//div[@class='GJT013UBH']//tr[3]"));
//			assert this element is other than reportName = "Cost per RVU" (filter applied earlier)
			assertThatElementIsDisplayed(otherValue);
			ExtentReport.logPass("PASS", "test02ClearSearch");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ClearSearch", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
//		doClosePageOnLowerBar("Report Library");
		ExtentReport.report.flush();

	}
}
