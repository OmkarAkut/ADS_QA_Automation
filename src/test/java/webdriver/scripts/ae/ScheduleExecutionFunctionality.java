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
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AeHelper;
import webdriver.helpers.CimHelper;
import webdriver.maps.AeMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20274 **/
public class ScheduleExecutionFunctionality extends AeHelper{
	 static String currentDateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	 static String aeJobCreate = "AE" + currentDateTime;
	 static ModelLibraryMap modelMap;
	 private static AeMap aeMap;
	 static List<String> getHeaderList = new ArrayList<>();
	 static String originalHandle = driver.getWindowHandle();
	 List<String> expHeaderList = Arrays.asList("Task","Status","Last Execution","Next Execution");
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
				cancelScheduleJobs();
				ExtentReport.logPass("PASS", "setupScript");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
				fail(e.getMessage());
			}
		}
	 public void validateScheduledDetails(String jobName) throws Throwable {
		 driverDelay(240000);
		 doClick(aeMap.getScheduledBtn());
			waitForElementToBeVisible(aeMap.getscheduleDetailsPopUp());	
			scrollToView("//div[@id='scheduledContainer']//th[contains(text(),'"+jobName+"')]");
			assertElementIsDisplayedWithXpath("//div[@id='scheduledContainer']//th[contains(text(),'"+jobName+"')]");
			
	 }
	 public void validateExecutionDetails(String jobName) throws Throwable {
			String currentDay=java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("MMM d, yyyy"));
			doClick(aeMap.getexecDetailsBtn());
			waitForElementToBeVisible(aeMap.getexecDetailsPopUp());
			assertElementIsDisplayedWithXpath("(//div[@id='executionDetailsModal']//following::th[contains(text(),'"+aeJobCreate+"')])[1]");
			assertElementTextContains(driver.findElement(By.xpath("(//div[@id='executionDetailsModal']//following::th[contains(text(),'"+jobName+"')])/span")), "COMPLETED", printout);
			assertElementTextContains(driver.findElement(By.xpath("((//div[@id='executionDetailsModal']//following::th[contains(text(),'"+jobName+"')])//following::tr/td)[2]")), "PASSED", printout);
			assertElementTextContains(driver.findElement(By.xpath("((//div[@id='executionDetailsModal']//following::th[contains(text(),'"+jobName+"')])//following::tr/td)[3]")), currentDay, printout);
			doClick(aeMap.getcloseBtn());
	 }
	 @Test
		public void test01Validate_DailyRecurrence_20274() throws Throwable {
			try {
				openScheduleWindow();
				aeMap.getexecuteJobName().sendKeys(aeJobCreate);
				String time=setRecurrence("DAILY");
				doClick(aeMap.getexecuteJobSaveCloseBtn());
				doClick(aeMap.getjobCloseBtn());
				driverDelay();
				validateScheduledDetails(aeJobCreate);
				String date=driver.findElement(By.xpath("(//div[@id='scheduledContainer']//th[contains(text(),'"+aeJobCreate+"')]//following::tr/td)[4]")).getText();
				String daily=java.time.LocalDate.now().plusDays(1).format(java.time.format.DateTimeFormatter.ofPattern("MMM d, yyyy"));
				assertEqualsString(date,daily+","+" "+time);
				doClick(aeMap.getschedulePopUpCloseBtn());
				validateExecutionDetails(aeJobCreate);
				ExtentReport.logPass("PASS", "test01Validate_DailyRecurrence_20274");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01Validate_DailyRecurrence_20274", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test02Validate_MonthlyRecurrence_20274() throws Throwable {
			try {
				openExecuteJob();
				aeMap.getexecuteJobName().sendKeys(aeJobCreate);
				String time=setRecurrence("MONTHLY");
				doClick(aeMap.getexecuteJobSaveCloseBtn());
				doClick(aeMap.getjobCloseBtn());
				driverDelay();
				validateScheduledDetails(aeJobCreate);
				String date=driver.findElement(By.xpath("(//div[@id='scheduledContainer']//th[contains(text(),'"+aeJobCreate+"')]//following::tr/td)[4]")).getText();
				String monthly=java.time.LocalDate.now().plusMonths(1).format(java.time.format.DateTimeFormatter.ofPattern("MMM d, yyyy"));
				assertEqualsString(date,monthly+","+" "+time);
				doClick(aeMap.getschedulePopUpCloseBtn());
				validateExecutionDetails(aeJobCreate);
				ExtentReport.logPass("PASS", "test02Validate_MonthlyRecurrence_20274");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test02Validate_MonthlyRecurrence_20274", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test03Validate_AnnualRecurrence_20274() throws Throwable {
			try {
				openExecuteJob();
				aeMap.getexecuteJobName().sendKeys(aeJobCreate);
				String time=setRecurrence("ANNUALLY");
				doClick(aeMap.getexecuteJobSaveCloseBtn());
				doClick(aeMap.getjobCloseBtn());
				driverDelay();
				validateScheduledDetails(aeJobCreate);
				String date=driver.findElement(By.xpath("(//div[@id='scheduledContainer']//th[contains(text(),'"+aeJobCreate+"')]//following::tr/td)[4]")).getText();
				String annual=java.time.LocalDate.now().plusYears(1).format(java.time.format.DateTimeFormatter.ofPattern("MMM d, yyyy"));
				assertEqualsString(date,annual+","+" "+time);
				doClick(aeMap.getschedulePopUpCloseBtn());
				validateExecutionDetails(aeJobCreate);
				ExtentReport.logPass("PASS", "test03Validate_AnnualRecurrence_20274");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test03Validate_AnnualRecurrence_20274", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test04Validate_QuarterlyRecurrence_20274() throws Throwable {
			try {
				openExecuteJob();
				aeMap.getexecuteJobName().sendKeys(aeJobCreate);
				String time=setRecurrence("QUARTERLY");
				doClick(aeMap.getexecuteJobSaveCloseBtn());
				doClick(aeMap.getjobCloseBtn());
				driverDelay();
				validateScheduledDetails(aeJobCreate);
				String date=driver.findElement(By.xpath("(//div[@id='scheduledContainer']//th[contains(text(),'"+aeJobCreate+"')]//following::tr/td)[4]")).getText();
				String quarterly=java.time.LocalDate.now().plusMonths(3).format(java.time.format.DateTimeFormatter.ofPattern("MMM d, yyyy"));
				assertEqualsString(date,quarterly+","+" "+time);
				doClick(aeMap.getschedulePopUpCloseBtn());
				validateExecutionDetails(aeJobCreate);
				ExtentReport.logPass("PASS", "test04Validate_QuarterlyRecurrence_20274");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test04Validate_QuarterlyRecurrence_20274", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test05Validate_WeeklyRecurrence_20274() throws Throwable {
			try {
				openExecuteJob();
				aeMap.getexecuteJobName().sendKeys(aeJobCreate);
				String time=setRecurrence("WEEKLY");
				doClick(aeMap.getexecuteJobSaveCloseBtn());
				doClick(aeMap.getjobCloseBtn());
				driverDelay();
				validateScheduledDetails(aeJobCreate);
				String date=driver.findElement(By.xpath("(//div[@id='scheduledContainer']//th[contains(text(),'"+aeJobCreate+"')]//following::tr/td)[4]")).getText();
				String weekly=java.time.LocalDate.now().plusWeeks(1).format(java.time.format.DateTimeFormatter.ofPattern("MMM d, yyyy"));
				System.out.println(weekly+","+" "+time);
				assertEqualsString(date,weekly+","+" "+time);
				doClick(aeMap.getschedulePopUpCloseBtn());
				validateExecutionDetails(aeJobCreate);
				ExtentReport.logPass("PASS", "test05Validate_WeeklyRecurrence_20274");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test05Validate_WeeklyRecurrence_20274", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test06Validate_DefaultRecurrence_20274() throws Throwable {
			try {
				openExecuteJob();
				assertElementIsDisplayed(driver.findElement(By.xpath("//label[text()='Recurrence']//following::option[@value='NONE']")));
				doClick(aeMap.getschedulePopUpCloseBtn());
				ExtentReport.logPass("PASS", "test05Validate_WeeklyRecurrence_20274");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test05Validate_WeeklyRecurrence_20274", driver, e);
				fail(e.getMessage());
			}
		}
	 @AfterClass
		public static void endtest() throws Exception {
			closeNewTabAndReturn(driver, originalHandle);
			ExtentReport.report.flush();

		}
}
