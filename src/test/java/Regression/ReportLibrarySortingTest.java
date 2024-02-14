package Regression;

import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ReportingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression Test for Test Ticket ADS-5662. **/
public class ReportLibrarySortingTest extends GoHelper {
	private static ReportingMap reportMap;
	static String directory1 = "Templates";
	static String directory2 = "Reports";

	@BeforeClass
	public static void setupScript() throws InterruptedException, Throwable {
		ExtentReport.reportCreate("ReportLibrarySortingTest", "webdriver.scripts.reporting",
				"ReportLibrarySortingTest");
		try {
			reportMap = BuildMap.getInstance(driver, ReportingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Report Library");
			waitForDisplayedSpinnerToEnd();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")));
			doClick(reportMap.getReportLibraryPageFormFieldSearch());
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-5662
	@Test
	public void test01TemplatesSortByColumnDescending() throws Throwable {
		try {
			doClick("//div[contains(text(),'" + directory1 + "')]");
			assertElementIsDisplayedWithXpath("//div[contains(text(),'" + directory1
					+ "')]//parent::div[@class='gwt-TreeItem gwt-TreeItem-selected']");
			doClick(reportMap.reportColumnName());
			ContractModelsHelper.sortTableGridDescending(reportMap.reportTableElementList());
			ExtentReport.logPass("PASS", "test01SortByColumnDescending");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01SortByColumnDescending", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02TemplatesSortByColumnAscending() throws Throwable {
		try {
			doClick(reportMap.reportColumnName());
			ContractModelsHelper.sortTableGridAscending(reportMap.reportTableElementList());
			ExtentReport.logPass("PASS", "test02SortByColumnAscending");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02SortByColumnAscending", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03ReportsSortByColumnDescending() throws Throwable {
		try {
			doClick("//div[contains(text(),'" + directory2 + "')]");
			assertElementIsDisplayedWithXpath("//div[contains(text(),'" + directory2
					+ "')]//parent::div[@class='gwt-TreeItem gwt-TreeItem-selected']");
			doClick(reportMap.reportColumnName());
			ContractModelsHelper.sortTableGridDescending(reportMap.reportTableElementList());
			ExtentReport.logPass("PASS", "test03ReportsSortByColumnDescending");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ReportsSortByColumnDescending", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04ReportsSortByColumnAscending() throws Throwable {
		try {
			doClick(reportMap.reportColumnName());
			ContractModelsHelper.sortTableGridAscending(reportMap.reportTableElementList());
			ExtentReport.logPass("PASS", "test04ReportsSortByColumnAscending");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ReportsSortByColumnAscending", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		driver.switchTo().parentFrame();
		doClick("//*[contains(@id,'tabbar')]//a[@title='Close Report Library']");
		ExtentReport.report.flush();

	}
}
