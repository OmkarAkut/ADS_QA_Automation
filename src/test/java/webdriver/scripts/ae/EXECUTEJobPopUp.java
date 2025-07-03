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

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AeHelper;
import webdriver.maps.AeMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20271 ,ADS-20204**/
public class EXECUTEJobPopUp extends AeHelper{
	 static String currentDateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	 static String aeJobCreateValidate = "AE ..." + currentDateTime;
	 static String aeJobCreate = "AE" + currentDateTime;
	 static ModelLibraryMap modelMap;
	 private static AeMap aeMap;
	 static String originalHandle = driver.getWindowHandle();
	 @BeforeClass
		public static void setupScript() throws Exception, Throwable {

			ExtentReport.reportCreate("EXECUTEJobPopUp", "webdriver.scripts.ae",
					"EXECUTEJobPopUp");
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
		public void test01Validate_NoTaskSelectedMessage_Popup_20204() throws Throwable {
			try {
				doClick(aeMap.getexecuteJobBtn());
				assertElementIsDisplayed(aeMap.getexecuteNoTaskSelectMessage());
				doClick(aeMap.getexecuteNoTaskCloseBtn());
				ExtentReport.logPass("PASS", "test01Validate_NoTaskSelectedMessage_Popup_20204");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01Validate_NoTaskSelectedMessage_Popup_20204", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test02Validate_ExecuteJob_Popup_20271() throws Throwable {
			try {
				openScheduleWindow();
				assertElementIsDisplayed(aeMap.getexecuteJobName());
				assertElementIsDisplayed(aeMap.getexecuteNowBtn());
				assertElementIsDisplayed(aeMap.getexecuteTomMorning());
				assertElementIsDisplayed(aeMap.getexecuteTomAfternoon());
				assertElementIsDisplayed(aeMap.getexecuteCustomDateTime());
				assertElementIsDisplayed(aeMap.getexecuteRecurrence());
				ExtentReport.logPass("PASS", "test02Validate_ExecuteJob_Popup_20271");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test02Validate_ExecuteJob_Popup_20271", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test03Validate_Cancel_Popup_20271() throws Throwable {
			try {
				doClick(aeMap.getexecutePopUpCancelBtn());
				doClick(aeMap.getexecuteJobBtn());
				waitForElementToBeVisible(aeMap.getexecuteJobPopUp());
				doClick(aeMap.getexecutePopUpCloseIcon());
				ExtentReport.logPass("PASS", "test02Validate_ExecuteJob_Popup_20271");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test02Validate_ExecuteJob_Popup_20271", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test04Validate_SaveClose_Popup_20271() throws Throwable {
			try {
				doClick(aeMap.getexecuteJobBtn());
				waitForElementToBeVisible(aeMap.getexecuteJobPopUp());
				aeMap.getexecuteJobName().sendKeys(aeJobCreateValidate);
				doClick(aeMap.getexecutePopUpSaveCloseBtn());
				driverDelay();
				assertTextIsDisplayed("Job Name can only be alphanumeric");//Validate alphanumeric
				aeMap.getexecuteJobName().clear();
				aeMap.getexecuteJobName().sendKeys(aeJobCreate);
				doClick(aeMap.getexecutePopUpSaveCloseBtn());
				assertTextIsDisplayed("Please select one execution option"); //Validate if execute option selected
				doClick(aeMap.getexecuteNowBtn());
				assertElementIsDisplayed(aeMap.getexecuteJobWarning());
				doClick(aeMap.getexecuteJobNowBtn());
				assertTextIsDisplayed("Job Created Successfully.");//Validate job created
				doClick(aeMap.getjobCloseBtn());
				doClick(aeMap.getScheduledBtn());
				waitForElementToBeVisible(aeMap.getscheduleDetailsPopUp());	
				scrollToView("//div[@id='scheduledContainer']//th[contains(text(),'"+aeJobCreate+"')]");
				assertElementIsDisplayedWithXpath("//div[@id='scheduledContainer']//th[contains(text(),'"+aeJobCreate+"')]");
				doClick(aeMap.getschedulePopUpCloseBtn());
				ExtentReport.logPass("PASS", "test04Validate_SaveClose_Popup_20271");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test04Validate_SaveClose_Popup_20271", driver, e);
				fail(e.getMessage());
			}
		}
	 @AfterClass
		public static void endtest() throws Exception {
			closeNewTabAndReturn(driver, originalHandle);
			ExtentReport.report.flush();

		}
}
