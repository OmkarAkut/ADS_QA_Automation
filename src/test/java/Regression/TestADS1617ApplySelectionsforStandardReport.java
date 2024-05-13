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
/** Regression Test for Test Ticket ADS-5657. **/
public class TestADS1617ApplySelectionsforStandardReport extends GoHelper {
	private static ReportingMap reportMap;
	static String reportName = "# ASESC-2338";
	static String directory = "Reports";
	static String subDirectory = "Contract";

	@BeforeClass
	public static void setupScript() throws InterruptedException, Throwable {
		ExtentReport.reportCreate("TestADS1617ApplySelectionsforStandardReport", "webdriver.scripts.reporting",
				"TestADS1617ApplySelectionsforStandardReport");
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
//ADS-5657
	@Test
	public void test01SearchReport_5657() throws Throwable {
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
	public void test02OpenContractAndARManagementReport_5657() throws Throwable {
		try {
			doClick("//*[text()='" + reportName + "']");
			assertElementIsDisplayedWithXpath("//div[text()='" + reportName + "']");
			ExtentReport.logPass("PASS", "test02OpenContractAndARManagementReport");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02OpenContractAndARManagementReport", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03assertContractNamesPopUp_5657() throws Throwable {
		try {
			doClick(reportMap.reportContractNameLink());
			waitForElementToBeVisible(reportMap.reportContractNamePopUp());
			assertElementIsDisplayed(reportMap.reportContractNamePopUp());
			String contractName = reportMap.reportContractNameLink().getText();
			assertElementIsDisplayedWithXpath(
					"//div[contains(text(),'Selected (')]//following::div[text()='" + contractName + "']");
			doClick(reportMap.reportLibraryPageEntityCancelButton());
			ExtentReport.logPass("PASS", "test03assertContractNamesPopUp");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03assertContractNamesPopUp", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04assertCareDeliveryFacilitiesPopUp_5657() throws Throwable {
		try {
			doClick("//div[text()='Care Delivery Facilities']//following::a[1]/u");
			waitForElementToBeVisible(reportMap.reportCareDeliveryFacilitiesPopUp());
			assertElementIsDisplayed(reportMap.reportCareDeliveryFacilitiesPopUp());
			assertElementIsDisplayedWithXpath(
					"//div[contains(text(),'Selected (')]//following::div[text()='Marina Medical Center']");
			doClick(reportMap.reportLibraryPageEntityCancelButton());
			ExtentReport.logPass("PASS", "test04assertCareDeliveryFacilitiesPopUp");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04assertCareDeliveryFacilitiesPopUp", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		driver.switchTo().defaultContent();
		doClick("//*[contains(@id,'tabbar')]//following::span[text()='Report Library']//following::span[@class='x-tab-close-btn']");
		ExtentReport.report.flush();

	}
}
