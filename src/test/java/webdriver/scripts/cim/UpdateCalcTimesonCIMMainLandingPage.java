package webdriver.scripts.cim;

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
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-21962 **/
public class UpdateCalcTimesonCIMMainLandingPage extends CimHelper {
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
		
		ExtentReport.reportCreate("UpdateCalcTimesonCIMMainLandingPage", "webdriver.scripts.cim",
				"UpdateCalcTimesonCIMMainLandingPage");
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
	@Test
	public void test01Validate_LastStartTime_21962() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			doClick(cimMap.getcimCalculateBtn());
			doClick("(//div[contains(@class,'x-closable ')]//following::span[text()='Calculate Now'])");
			doClick(cimMap.getcimCalculateNowConfirmBtn());
			calcStartTime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[13]/div")).getText();
			dateTimeCheck(calcStartTime);//Validate Time is in HH:MM:SS
			doClosePageOnLowerBar("Calculation Status");
			cimStartTime=driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[2]/div")).getText();
			assertTextEquals(calcStartTime,cimStartTime);
			ExtentReport.logPass("PASS", "test01Validate_LastStartTime_21962");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Validate_LastStartTime_21962", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02Validate_LastEndTime_21962() throws Throwable {
		try {
			doClick("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[5]/div/a");
			waitForFirstRowCalculationBarToReach100Percent(cimMap.getcalcRefreshBtn());
			calcEndtime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[14]/div")).getText();
			doClosePageOnLowerBar("Calculation Status");
			doClick(cimMap.getcimRefreshBtn());
			cimEndtime=driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[3]/div")).getText();
			assertTextEquals(calcEndtime,cimEndtime);
			deleteCim();
			ExtentReport.logPass("PASS", "test02Validate_LastEndTime_21962");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_LastEndTime_21962", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03Validate_NextStartTime_21962() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("PENDING", cimScenarioCreate);
			cimNextStartTime=driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[4]/div")).getText().split(" ")[0];
			String nextStartTime = getSystemDate();
			assertTextEquals(cimNextStartTime,nextStartTime);
			deleteCim();
			ExtentReport.logPass("PASS", "test03Validate_NextStartTime_21962");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03Validate_NextStartTime_21962", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
			ExtentReport.report.flush();

	}
}
