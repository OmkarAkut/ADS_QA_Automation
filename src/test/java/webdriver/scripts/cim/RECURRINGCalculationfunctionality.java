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
/** Regression test case ADS-20410 **/
public class RECURRINGCalculationfunctionality extends CimHelper {
	private static CimMap cimMap;
	private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	static String calcType="Activity Volume Calc Scenario: ADS-262 Vol Calc";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("RECURRINGCalculationfunctionality", "webdriver.scripts.cim",
				"RECURRINGCalculationfunctionality");
		try {
			cimMap = BuildMap.getInstance(driver, CimMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Cost Integration Manager");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01Validate_DailyRecurrence_20410() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			setRecurrence("Daily", cimScenarioCreate);
			doClick(cimMap.scheduledPopUpCancelCloseBtn());
			deleteCim();
			doClick(cimMap.getcimRefreshBtn());
			ExtentReport.logPass("PASS", "test01Validate_GroupScheduling_20410");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Validate_GroupScheduling_20410", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02Validate_MonthlyRecurrence_20410() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			setRecurrence("Monthly", cimScenarioCreate);
			doClick(cimMap.scheduledPopUpCancelCloseBtn());
			deleteCim();
			ExtentReport.logPass("PASS", "test02Validate_MonthlyRecurrence_20410");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_MonthlyRecurrence_20410", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03Validate_WeeklyRecurrence_20410() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			setRecurrence("Weekly", cimScenarioCreate);
			doClick(cimMap.scheduledPopUpCancelCloseBtn());
			deleteCim();
			ExtentReport.logPass("PASS", "test03Validate_WeeklyRecurrence_20410");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03Validate_WeeklyRecurrence_20410", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04Validate_AnnuallyRecurrence_20410() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			setRecurrence("Annually", cimScenarioCreate);
			doClick(cimMap.scheduledPopUpCancelCloseBtn());
			deleteCim();
			ExtentReport.logPass("PASS", "test04Validate_AnnuallyRecurrence_20410");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04Validate_AnnuallyRecurrence_20410", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05Validate_QuarterlyRecurrence_20410() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			setRecurrence("Quarterly", cimScenarioCreate);
			doClick(cimMap.scheduledPopUpCancelCloseBtn());
			deleteCim();
			ExtentReport.logPass("PASS", "test05Validate_QuarterlyRecurrence_20410");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05Validate_QuarterlyRecurrence_20410", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test06Validate_DoesNotRepeat_Recurrence_20410() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			setRecurrence("Does not repeat", cimScenarioCreate);
			doClick(cimMap.scheduledPopUpCancelCloseBtn());
			deleteCim();
			ExtentReport.logPass("PASS", "test05Validate_QuarterlyRecurrence_20410");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05Validate_QuarterlyRecurrence_20410", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
			ExtentReport.report.flush();

	}
}
