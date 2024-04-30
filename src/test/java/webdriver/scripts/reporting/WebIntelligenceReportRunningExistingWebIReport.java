package webdriver.scripts.reporting;

import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ReportingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression Test for Test Ticket ADS-6638. **/
public class WebIntelligenceReportRunningExistingWebIReport extends GoHelper {
	private static ReportingMap reportMap;
	static String firstHandle;

	@BeforeClass
	public static void setupScript() throws InterruptedException, Throwable {
		ExtentReport.reportCreate("WebIntelligenceReportRunningExistingWebIReport", "webdriver.scripts.reporting",
				"WebIntelligenceReportRunningExistingWebIReport");

		try {
			reportMap = BuildMap.getInstance(driver, ReportingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Web Intelligence");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6638[all steps]
	@Test
	public void test01GoToPublicFolders_6638() throws Throwable {
		try {
			waitForAjaxExtJs();
			firstHandle = webdriverSwitchToNewWindow(printout);
			driverDelay(5000);
			waitForPresenceOfElement("//*[@name = 'servletBridgeIframe']");
			webdriverSwitchToFrame("servletBridgeIframe");
//			Omkar 19/04/2023 : xpath changes for ADS-11.2
//			waitForPresenceOfElement("//a[text()='Documents']");
//			doClick("//a[text()='Documents']");
			/*
			waitForPresenceOfElement("//span[@id='Documents-title']");
			doClick("//span[@id='Documents-title']");
			waitForElementPresence("//a[@title='Expand Folders']");
			*/
			//Shilpa updatd tc on 30.4.2024
			waitForElementToBeVisible(reportMap.reportFolders());
			
			
			
			ExtentReport.logPass("PASS", "testWebiPageLoads");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "testWebiPageLoads", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test02MoveToPublicFolders_6638() throws Throwable {
		try {
			/*
			driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[contains(@id,'iframe')])[2]")));
			doClick("//a[@title='Expand Folders']");
			waitForElementPresence("//span[contains(text(),'Public Folders')]");
			driverDelay(800);
			Actions act = new Actions(driver);
			act.moveToElement(reportMap.reportConversionTool()).doubleClick().perform();
			act.moveToElement(reportMap.reportConversionToolDoc()).doubleClick().pause(1000).perform();
			act.moveToElement(reportMap.reportConversionToolDoc()).doubleClick().pause(1000).perform();
			act.moveToElement(reportMap.reportConversionToolDoc()).doubleClick().pause(1000).perform();
			waitForDisplayedSpinnerToEnd();
			driver.switchTo().defaultContent();
			*/
			//Shilpa updatd tc on 30.4.2024
			doClick(reportMap.reportFolders());
			waitForElementToBeVisible(reportMap.publicFolders());
			doClick(reportMap.publicFolders());
			waitForElementToBeVisible(reportMap.webSample());
			doClick(reportMap.webSample());
			driverDelay(200);
			ExtentReport.logPass("PASS", "test02MoveToPuublicFolders");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02MoveToPuublicFolders", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
//	public void test03OpenConversionToolReport_6638() throws Throwable {
		public void test03ValidateChartingSamples_6638() throws Throwable {

		try {
			/*
			webdriverSwitchToFrame("servletBridgeIframe");
			driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[contains(@id,'iframe')])[3]")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='webiViewFrame']")));
			doClick(reportMap.reportToolOkBtn());
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='persFrame']")));
			assertElementIsDisplayed(reportMap.reportToolPieChart());
			*/
			//Shilpa updatd tc on 30.4.2024
			doClick(reportMap.webchartSample());
			driverDelay(2000);
//			webdriverSwitchToFrame("servletBridgeIframe");
//			waitForElementToBeVisible(reportMap.reportTab2());
			doClick(reportMap.reportTab2());
			driverDelay(300);
			waitForElementToBeVisible(reportMap.reportChart());
			doClick(reportMap.reportTab3());
			driverDelay(300);
			waitForElementToBeVisible(reportMap.reportChart());
			doClick(reportMap.reportTab4());
			driverDelay(300);
			waitForElementToBeVisible(reportMap.reportChart());
			doClick(reportMap.reportTab5());
			driverDelay(300);
			waitForElementToBeVisible(reportMap.reportChart());
			driver.switchTo().defaultContent();
			ExtentReport.logPass("PASS", "test03OpenConversionToolReport");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03OpenConversionToolReport", driver, e);
			fail(e.getMessage());
		} finally {
			try {
				driver.close();
				driver.switchTo().window(firstHandle);
			} catch (Exception e) {

			}
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
