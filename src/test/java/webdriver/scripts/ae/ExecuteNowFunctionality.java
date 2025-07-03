package webdriver.scripts.ae;

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
import webdriver.helpers.AeHelper;
import webdriver.maps.AeMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20273 **/
public class ExecuteNowFunctionality extends AeHelper{
	 static String currentDateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	 static String aeJobCreate = "AE" + currentDateTime;
	 static ModelLibraryMap modelMap;
	 private static AeMap aeMap;
	 static String originalHandle = driver.getWindowHandle();
	 @BeforeClass
		public static void setupScript() throws Exception, Throwable {

			ExtentReport.reportCreate("ExecuteNowFunctionality", "webdriver.scripts.ae",
					"ExecuteNowFunctionality");
			try {
				aeMap=BuildMap.getInstance(driver, AeMap.class);
				Login.loginUser("AutomationTesterAdmin");
				goToPage("Automation Engine Job Manager");
				switchToNewTab(driver);
				waitForElementToBeVisible(aeMap.getexecuteJobBtn());
				ExtentReport.logPass("PASS", "setupScript");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test01Validate_WarningMessage_WhenNoTaskSelected_Popup_20273() throws Throwable {
			try {
				doClick(aeMap.getexecuteJobBtn());
				assertTextIsDisplayed("Please select at least one task for execution");
				doClick(aeMap.getexecuteNoTaskCloseBtn());
				ExtentReport.logPass("PASS", "test01Validate_WarningMessage_WhenNoTaskSelected_Popup_20273");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01Validate_WarningMessage_WhenNoTaskSelected_Popup_20273", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test02Validate_WarningMessage_WhenNoJobName_Popup_20273() throws Throwable {
			try {
				openScheduleWindow();
				doClick(aeMap.getexecuteNowBtn());
				assertTextIsDisplayed("Job Name is required");
				doClick(aeMap.getexecutePopUpCancelBtn());
				ExtentReport.logPass("PASS", "test02Validate_WarningMessage_WhenNoJobName_Popup_20273");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test02Validate_WarningMessage_WhenNoJobName_Popup_20273", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test03Validate_ExecuteNow_20273() throws Throwable {
			try {
				doClick(aeMap.getexecuteJobBtn());
				waitForElementToBeVisible(aeMap.getexecuteJobPopUp());
				aeMap.getexecuteJobName().sendKeys(aeJobCreate);
				doClick(aeMap.getexecuteNowBtn());
				doClick(aeMap.getexecuteJobNowBtn());
				assertTextIsDisplayed("Job Created Successfully.");//Validate job created
				doClick(aeMap.getjobCloseBtn());
				doClick(aeMap.getScheduledBtn());
				//Validate under Scheduled Details
				waitForElementToBeVisible(aeMap.getscheduleDetailsPopUp());	
				scrollToView("//div[@id='scheduledContainer']//th[contains(text(),'"+aeJobCreate+"')]");
				assertElementIsDisplayedWithXpath("//div[@id='scheduledContainer']//th[contains(text(),'"+aeJobCreate+"')]");
				doClick(aeMap.getschedulePopUpCloseBtn());
				ExtentReport.logPass("PASS", "test03Validate_ExecuteNow_20273");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test03Validate_ExecuteNow_20273", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test04Validate_ExecuteNow_20273() throws Throwable {
			try {
				driverDelay(240000);
				doClick(aeMap.getexecDetailsBtn());
				waitForElementToBeVisible(aeMap.getexecDetailsPopUp());
				assertElementIsDisplayedWithXpath("(//div[@id='executionDetailsModal']//following::th[contains(text(),'"+aeJobCreate+"')])[1]");
				assertElementTextContains(driver.findElement(By.xpath("(//div[@id='executionDetailsModal']//following::th[contains(text(),'"+aeJobCreate+"')])/span")), "COMPLETED", printout);
				doClick(aeMap.getcloseBtn());
				ExtentReport.logPass("PASS", "test03Validate_ExecuteNow_20273");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test03Validate_ExecuteNow_20273", driver, e);
				fail(e.getMessage());
			}
		}
	 @AfterClass
		public static void endtest() throws Exception {
		 	closeNewTabAndReturn(driver, originalHandle);
			ExtentReport.report.flush();

		}
}
