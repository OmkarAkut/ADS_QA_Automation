package webdriver.scripts.ae;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AeHelper;
import webdriver.maps.AeMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20272 **/
public class VIEWCANCELScheduledJobs extends AeHelper{
	static String currentDateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	 static String aeJobCreate = "AE" + currentDateTime;
	 static ModelLibraryMap modelMap;
	 private static AeMap aeMap;
	 static List<String> getHeaderList = new ArrayList<>();
	 static String originalHandle = driver.getWindowHandle();
	 List<String> expHeaderList = Arrays.asList("Task","Status","Last Execution","Next Execution");
	 @BeforeClass
		public static void setupScript() throws Exception, Throwable {

			ExtentReport.reportCreate("VIEWCANCELScheduledJobs", "webdriver.scripts.ae",
					"VIEWCANCELScheduledJobs");
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
		public void test01Validate_Scheduled_Popup_20272() throws Throwable {
			try {
				
				openScheduleWindow();
				aeMap.getexecuteJobName().sendKeys(aeJobCreate);
				doClick(aeMap.getexecuteNowBtn());
				assertElementIsDisplayed(aeMap.getexecuteJobWarning());
				doClick(aeMap.getexecuteJobNowBtn());
				driverDelay(200);
				doClick(aeMap.getjobCloseBtn());
				doClick(aeMap.getScheduledBtn());
				waitForElementToBeVisible(aeMap.getscheduleDetailsPopUp());	
				for(WebElement header: aeMap.getexecDetailsHeader()) {
					getHeaderList.add(header.getText());
				}
				assertListOfStringsContainsExpectedStrings(getHeaderList, expHeaderList);
				scrollToView("//div[@id='scheduledContainer']//th[contains(text(),'"+aeJobCreate+"')]");
				assertElementIsDisplayedWithXpath("//div[@id='scheduledContainer']//th[contains(text(),'"+aeJobCreate+"')]");
				
				ExtentReport.logPass("PASS", "test01Validate_Scheduled_Popup_20272");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01Validate_Scheduled_Popup_20272", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test02Validate_CloseScheduled_Popup_20272() throws Throwable {
			try {
				doClick(aeMap.getschedulePopUpCloseBtn());
				doClick(aeMap.getScheduledBtn());
				waitForElementToBeVisible(aeMap.getscheduleDetailsPopUp());	
				doClick(aeMap.getschedulePopUpCloseIcon());
				closeNewTabAndReturn(driver, originalHandle);
				ExtentReport.logPass("PASS", "test02Validate_CloseScheduled_Popup_20272");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test02Validate_CloseScheduled_Popup_20272", driver, e);
				fail(e.getMessage());
			}
		}
	 @AfterClass
		public static void endtest() throws Exception {
			ExtentReport.report.flush();

		}
}
