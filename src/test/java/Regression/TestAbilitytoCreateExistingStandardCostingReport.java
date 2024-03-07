package Regression;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

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
/** Regression Test for Test Ticket ADS-5659. **/
public class TestAbilitytoCreateExistingStandardCostingReport extends GoHelper {
	private static ReportingMap reportMap;
	static String reportName = "CM Cost Model per RVU";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String newReportName = reportName + currentDateTime;
	static String directory = "Reports";
	static String subDirectory = "Costing";
	static int refreshTime = 10;

	@BeforeClass
	public static void setupScript() throws InterruptedException, Throwable {
		ExtentReport.reportCreate("TestAbilitytoCreateExistingStandardCostingReport", "webdriver.scripts.reporting",
				"TestAbilitytoCreateExistingStandardCostingReport");

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
//ADS-5659
	@Test
	public void test01SearchReport_5659() throws Throwable {
		try {
			doClick(reportMap.getReportLibraryPageFormFieldSearch());
			doClick("//div[contains(text(),'" + directory
					+ "')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), '" + subDirectory
					+ "')]");
			ContractModelsHelper.keyInValues(reportMap.getReportLibraryPageFormFieldSearch(), reportName);
			driver.findElement(By.linkText(reportName)).click();

			ExtentReport.logPass("PASS", "test01SearchReport");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01SearchReport", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02SaveAsThenRunReport() throws Throwable {
		try {
			doClick(reportMap.reportLibraryPageEntitySaveAsButton());
			ContractModelsHelper.keyInValues(driver.findElement(By.xpath("//input[@class='gwt-TextBox']")),
					newReportName);
			doClick(reportMap.reportLibraryPageEntityOkButton());
			doClick(reportMap.reportLibraryPageEntityRunButton());
			waitForElementToBeVisible(reportMap.reportLibraryPageEntityRefreshButton());
			try {
				for (int i = 0; i <= refreshTime; i++) {
					waitForPresenceOfElement(("//span[text()='" + newReportName + "']"));
					try {
						doClick(reportMap.reportLibraryPageEntityRefreshButton());
						waitForPresenceOfElement(("//span[text()='" + newReportName + "']"));

						if (driver
								.findElement(By.xpath("(//span[text()='" + newReportName + "']//following::div/a)[1]"))
								.getText().equals("COMPLETED")) {
							assertTrue(printout);
							break;
						}
						else if (driver
								.findElement(By.xpath("(//span[text()='" + newReportName + "']//following::div/a)[1]"))
								.getText().equals("FAILED")) {
							assertTrue(printout);
							break;
						}

						continue;
					} catch (Exception | AssertionError e) {
						continue;
					}
				}
			} 
			catch (Exception e) {

			}
			driverDelay(1000);
			driver.findElement(By.xpath("(//span[text()='" + newReportName + "']//following::div/a)[1]")).click();
			driverDelay(1000);
//			waitForElementPresence("//iframe[contains(@src,'QueryCrystalReportInstance.jsp')]");
			waitForPresenceOfElement("//iframe[contains(@src,'QueryCrystalReportInstance.jsp')]");
			driver.switchTo()
			.frame(driver.findElement(By.xpath("//iframe[contains(@src,'QueryCrystalReportInstance.jsp')]")));
//			waitForElementPresence("//iframe[contains(@id,'bobjid')]");
			waitForPresenceOfElement("//iframe[contains(@id,'bobjid')]");
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'bobjid')]")));
			System.out.println(
					driver.findElement(By.xpath("//span[text()='Entity:']//preceding::div[@id='Text6-0-0']//span/span"))
					.getText());
			assertElementTextContainsWithXpathLocator(
					"//span[text()='Entity:']//preceding::div[@id='Text6-0-0']//span/span", "Marina Medical Center",
					printout);
			assertElementTextContainsWithXpathLocator("//div[@id='Text8-0-0']//following::span[1]", "PATCARE",
					printout);
			ExtentReport.logPass("PASS", "test02SaveAsThenRunReport");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02SaveAsThenRunReport", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		driver.switchTo().defaultContent();
//		doClick("//*[contains(@id,'tabbar')]//following::span[text()='Report Library']//following::a[@class='x-tab-close-btn']");
		ExtentReport.report.flush();

	}
}
