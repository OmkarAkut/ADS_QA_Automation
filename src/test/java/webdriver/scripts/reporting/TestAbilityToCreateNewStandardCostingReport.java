package webdriver.scripts.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ReportingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression Test for Test Ticket ADS-5654. **/
public class TestAbilityToCreateNewStandardCostingReport extends GoHelper {
	private static ReportingMap reportMap;
	static String reportName = "Cost per RVU";
	static String entity = "150 PRIVATE PAY";
	static String deptHierarchy = "Marina Department Hierarchy";
	static String deptGroup = "TBDEPTGRP  TB Dept Group";
	static String directory = "Templates";
	static String subDirectory = "Costing";
	static String newReportName = "CM1 Cost";
	static int refreshTime = 10;

	@BeforeClass
	public static void setupScript() throws InterruptedException, Throwable {

		ExtentReport.reportCreate("TestAbilityToCreateNewStandardCostingReport", "webdriver.scripts.reporting",
				"TestAbilityToCreateNewStandardCostingReport");

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

	@Test
	public void test01SearchReport() throws Throwable {
		try {
			doClick(reportMap.getReportLibraryPageFormFieldSearch());
			doClick("//div[contains(text(),'" + directory
					+ "')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), '" + subDirectory
					+ "')]");
			ContractModelsHelper.keyInValues(reportMap.getReportLibraryPageFormFieldSearch(), reportName);
			ExtentReport.logPass("PASS", "test01SearchReport");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01SearchReport", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02OpenCostingReport() throws Throwable {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;

			doClick("//*[text()='" + reportName + "']");
			doClick(reportMap.reportLibraryPageEntityRange());
			// Actions act=new Actions(driver);
			driverDelay(1000);
			executor.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//div[text()='PRIVATE PAY']//parent::td")));
			doClick("//div[text()='PRIVATE PAY']");
			driverDelay(1000);
			ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
			driverDelay(1000);
			executor.executeScript("arguments[0].click();", reportMap.reportLibraryPageEntityOkButton());
			Actions action = new Actions(driver);
			driverDelay(500);
			action.moveToElement(reportMap.reportLibraryPageDeptGrp()).click().build().perform();
			// doClick(reportMap.reportLibraryPageDeptGrp());
			driverDelay(1000);
			doClick("//div[text()='" + deptGroup + "']");
			doClick("//div[text()='" + deptGroup + "']");
			driverDelay(1000);
			doClick(reportMap.reportLibraryPageEntityOkButton());
			driverDelay(1000);
			doClick(reportMap.reportLibraryPageEntitySaveAsButton());
			driverDelay(1000);
			ContractModelsHelper.keyInValues(driver.findElement(By.xpath("//input[@class='gwt-TextBox']")),
					newReportName);
			doClick(reportMap.reportLibraryPageEntityOkButton());
			driverDelay(400);
			doClick(reportMap.reportLibraryPageEntityOkButton());
			doClick(reportMap.reportLibraryPageEntityRunButton());
			driverDelay(2000);

			// Shilpa updated script for 11.2 on 22.4.2024
			for (int i = 0; i < 5; i++) {
				doClick(reportMap.reportLibraryPageEntityRefreshButton());
				driverDelay(2500);
				if (i == 4) {
					System.out.println("Status is not updated to COMPLETED");
					fail();
					break;
				}
				try {
					if (driver.findElement(By.xpath("(//div[@class='GJT013UBH']//tbody//td/div)[7]/a")).getText()
							.equals("COMPLETED")) {
						assertElementTextWithXpath("(//div[@class='GJT013UBH']//tbody//td/div)[7]/a", "COMPLETED",
								printout);
						ExtentReport.logPass("PASS", "test02OpenCostingReport");
						break;

					}
				} catch (Exception e1) {
					if (driver.findElement(By.xpath("(//div[@class='GJT013UBH']//tbody//td/div)[7]")).isDisplayed()) {
						System.out.println(i);

						continue;
					}
					if (driver.findElement(By.xpath("(//div[@class='GJT013UBH']//tbody//td/div)[7]/a")).getText()
							.contains("FAILED")) {
						fail();
						ExtentReport.logFail("FAIL", "test02OpenCostingReport", driver, e1);
					}

				}

			}

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02OpenCostingReport", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
