package webdriver.scripts.cim;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20406**/
public class CalculateNowFunctionality extends CimHelper {
	private static CimMap cimMap;
	private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	static String calcType=getProperty().replaceAll("^\"|\"$", "");
	static String calcStartTime;
	static String calcEndtime;
	static String cimStartTime;
	static String cimEndtime;
	static String cimNextStartTime;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		
		ExtentReport.reportCreate("CalculateNowFunctionality", "webdriver.scripts.cim",
				"CalculateNowFunctionality");
		try {
			cimMap = BuildMap.getInstance(driver, CimMap.class);
			Login.loginUser("CostAnalyst1");
			goToPage("Cost Integration Manager");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
//	@Test
	public void test01Validate_CalculateNow_20406() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			doClick(cimMap.getcimCalculateBtn());
			doClick("(//div[contains(@class,'x-closable ')]//following::span[text()='Calculate Now'])");
			assertTextIsDisplayed("Are you sure you want to calculate this group?");
			assertTextIsDisplayed(" to calculate. Progress can be monitored on the Calculation Status page.");
			assertTextIsDisplayed(" to return to the previous screen without running the calculation.");
			assertTheElementIsEnabled(cimMap.getcimCalculateNowConfirmBtn());
			assertTheElementIsEnabled(cimMap.getcimCalculateCancelConfirmBtn());
			doClick(cimMap.getcimCalculateCancelConfirmBtn());
//			doClick(cimMap.getcalcCancelCloseBtn());
//			assertTextIsNotDisplayed("Calculation Status");
//			doClosePageOnLowerBar("Calculation Status");
			ExtentReport.logPass("PASS", "test01Validate_Calc_Hyperlink_20053");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Validate_Calc_Hyperlink_20053", driver, e);
			fail(e.getMessage());
		}
	}
//	@Test
	public void test02Validate_LastStartTime_20406() throws Throwable {
		try {
			doClick("(//div[contains(@class,'x-closable ')]//following::span[text()='Calculate Now'])");
			doClick(cimMap.getcimCalculateNowConfirmBtn());
			calcStartTime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[13]/div")).getText();
			doClosePageOnLowerBar("Calculation Status");
			cimStartTime=driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[2]/div")).getText();
			assertTextEquals(calcStartTime,cimStartTime);
			ExtentReport.logPass("PASS", "test02Validate_LastStartTime_20406");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_LastStartTime_20406", driver, e);
			fail(e.getMessage());
		}
	}
//	@Test
	public void test03Validate_LastEndTime_20406() throws Throwable {
		try {
			doClick("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[5]/div/a");
			waitForFirstRowCalculationBarToReach100Percent(cimMap.getcalcRefreshBtn());
			calcEndtime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[14]/div")).getText();
			doClosePageOnLowerBar("Calculation Status");
			doClick(cimMap.getcimRefreshBtn());
			cimEndtime=driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[3]/div")).getText();
			assertTextEquals(calcEndtime,cimEndtime);
			deleteCim();
			ExtentReport.logPass("PASS", "test03Validate_LastEndTime_20406");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03Validate_LastEndTime_20406", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04Validate_LastEndTime_IsEmpty_CalcPending_20406() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("PENDING", cimScenarioCreate);
			cimEndtime=driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[3]/div")).getText();
			assertTextEquals(cimEndtime," ");
			deleteCim();
			ExtentReport.logPass("PASS", "test04Validate_LastEndTime_IsEmpty_CalcPending_20406");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04Validate_LastEndTime_IsEmpty_CalcPending_20406", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05Validate_LastEndTime_IsEmpty_CalcRunning_20406() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("RUNNING", cimScenarioCreate);
			cimEndtime=driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[3]/div")).getText();
			assertTextEquals(cimEndtime," ");
			deleteCim();
			ExtentReport.logPass("PASS", "test05Validate_LastEndTime_IsEmpty_CalcRunning_20406");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05Validate_LastEndTime_IsEmpty_CalcRunning_20406", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test06Validate_LastEndTime_IsEmpty_CalcCancelled_20406() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("CANCELLED", cimScenarioCreate);
			doClosePageOnLowerBar("Calculation Status");
			cimEndtime=driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[3]/div")).getText();
			assertTextEquals(cimEndtime," ");
			deleteCim();
			ExtentReport.logPass("PASS", "test06Validate_LastEndTime_IsEmpty_CalcCancelled_20406");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06Validate_LastEndTime_IsEmpty_CalcCancelled_20406", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07Validate_LastEndTime_IsEmpty_CalcFailed_20406() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("FAILED", cimScenarioCreate);
			cimEndtime=driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[3]/div")).getText();
			assertTextEquals(cimEndtime," ");
			deleteCim();
			ExtentReport.logPass("PASS", "test07Validate_LastEndTime_IsEmpty_CalcFailed_20406");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07Validate_LastEndTime_IsEmpty_CalcFailed_20406", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test08Validate_NextStartTime_20406() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("PENDING", cimScenarioCreate);
			cimNextStartTime=driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[4]/div")).getText().split(" ")[0];
			String nextStartTime = getSystemDate();
			assertTextEquals(cimNextStartTime,nextStartTime);
			deleteCim();
			ExtentReport.logPass("PASS", "test08Validate_NextStartTime_20406");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08Validate_NextStartTime_20406", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
			ExtentReport.report.flush();

	}
}
