package webdriver.scripts.ae;

import static org.junit.Assert.fail;
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
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20250,ADS-20249 **/
public class UpdateAutomationEngineSequenceScreen extends AeHelper{
	 private static AeMap aeMap;
	 static List<String> getHeaderList = new ArrayList<>();
	 static String originalHandle = driver.getWindowHandle();
	 List<String> expHeaderList = Arrays.asList("Task","Sequence #","Status","Last Execution","Next Execution");
	 @BeforeClass
		public static void setupScript() throws Exception, Throwable {

			ExtentReport.reportCreate("UpdateAutomationEngineSequenceScreen", "webdriver.scripts.ae",
					"UpdateAutomationEngineSequenceScreen");
			try {
				aeMap=BuildMap.getInstance(driver, AeMap.class);
				Login.loginUser("AutomationTesterAdmin");
				goToPage("Automation Engine Job Manager"); //Validates ADS-20249
				switchToNewTab(driver);
				waitForElementToBeVisible(aeMap.getexecuteJobBtn());
				ExtentReport.logPass("PASS", "setupScript");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test01Validate_Headers_Buttons_20250_20249() throws Throwable {
			try {
				assertTextIsDisplayed("Job Manager");
				assertTextIsDisplayed("Create Job");
				assertThatElementIsDisplayed(aeMap.getexecuteJobBtn());
				assertThatElementIsDisplayed(aeMap.getScheduledBtn());
				assertThatElementIsDisplayed(aeMap.getexecDetailsBtn());
				ExtentReport.logPass("PASS", "test01Validate_Headers_20250");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01Validate_Headers_20250", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test02Validate_Columns_20250() throws Throwable {
			try {
				for(WebElement headers:aeMap.getjobMgrHeaders()) {
					getHeaderList.add(headers.getText());
				}
				assertListOfStringsContainsExpectedStrings(getHeaderList,expHeaderList);
				ExtentReport.logPass("PASS", "test01Validate_Headers_20250");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01Validate_Headers_20250", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test03Validate_ScheduledPopUp_20250() throws Throwable {
			try {
				doClick(aeMap.getScheduledBtn());
				waitForElementToBeVisible(aeMap.getscheduleDetailsPopUp());
				assertThatElementIsDisplayed(aeMap.getscheduleDetailsPopUp());
				doClick(aeMap.getschedulePopUpCloseBtn());
				ExtentReport.logPass("PASS", "test03Validate_ScheduledPopUp_20250");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test03Validate_ScheduledPopUp_20250", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test04Validate_ExecutionDetailsPopUp_20250() throws Throwable {
			try {
				doClick(aeMap.getexecDetailsBtn());
				waitForElementToBeVisible(aeMap.getexecDetailsPopUp());
				assertThatElementIsDisplayed(aeMap.getexecDetailsPopUp());
				doClick(aeMap.getexecutePopUpCancelBtn());
				ExtentReport.logPass("PASS", "test04Validate_ExecutionDetailsPopUp_20250");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test04Validate_ExecutionDetailsPopUp_20250", driver, e);
				fail(e.getMessage());
			}
		}
	 @AfterClass
		public static void endtest() throws Exception {
		 	closeNewTabAndReturn(driver, originalHandle);
			ExtentReport.report.flush();

		}
}
