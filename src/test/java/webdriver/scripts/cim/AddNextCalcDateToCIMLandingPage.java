package webdriver.scripts.cim;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20411 **/
public class AddNextCalcDateToCIMLandingPage extends CimHelper{
	private static CimMap cimMap;
	private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	static String calcType="Activity Volume Calc Scenario: ADS-262 Vol Calc";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("AddNextCalcDateToCIMLandingPage", "webdriver.scripts.cim",
				"AddNextCalcDateToCIMLandingPage");
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
	public void test01Validate_NextCalcDate_20411() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			doClick(cimMap.getcimCalculateBtn());
			driverDelay();
			setRecurrence("Daily", cimScenarioCreate);
			doClick(cimMap.scheduledPopUpCancelCloseBtn());
			ExtentReport.logPass("PASS", "test01Validate_NextCalcDate_20411");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Validate_NextCalcDate_20411", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02Validate_NextCalcDateIsUpdated_20411() throws Throwable {
		try {
			doClick("//div[text()='" + cimScenarioCreate + "']");
			doClick(cimMap.getcimCalculateBtn());
			waitForElementToBeVisible(cimMap.getschedulePopUp());
			doClick(cimMap.getScheduledEditBtn());
			waitForPresenceOfElement("//div[text()='Calculate "+cimScenarioCreate+"']");
			setRecurrence("Monthly", cimScenarioCreate);
			doClick(cimMap.scheduledPopUpCancelCloseBtn());
			deleteCim();
			ExtentReport.logPass("PASS", "test02Validate_NextCalcDateIsUpdated_20411");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_NextCalcDateIsUpdated_20411", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
			ExtentReport.report.flush();

	}
}
