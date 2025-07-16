package webdriver.scripts.cim;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.util.List;
import java.util.Arrays;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20405 **/
public class CALCULATEPopUp extends CimHelper{
	private static CimMap cimMap;
	private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	List<String> recurringOptions= Arrays.asList("Does not repeat","Daily","Weekly","Monthly","Quarterly","Annually","Custom");
	static String calcType=getProperty().replaceAll("^\"|\"$", "");
	private static DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
	
		ExtentReport.reportCreate("CALCULATEPopUp", "webdriver.scripts.cim",
				"CALCULATEPopUp");
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
	public void test01ValidatePopup_CalculationOptions_20405() throws Throwable {
		try {
			assertElementIsDisplayed(cimMap.getcimCalculateBtn());
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			doClick(cimMap.getcimCalculateBtn());
			waitForPresenceOfElement("//*[text()='Calculate "+cimScenarioCreate+"']");
			assertElementIsDisplayedWithXpath("//*[text()='Calculate "+cimScenarioCreate+"']");
			assertElementIsDisplayedWithXpath("//div[text()='Calculation Options']/span[@style='color:red;font-size:12px;']");
			assertElementIsDisplayed(cimMap.getcimCalculateNowButton());
			assertElementIsDisplayed(cimMap.getcimCalculatePreset1Btn());
			assertElementIsDisplayed(cimMap.getcimCalculatePreset2Btn());
			assertElementIsDisplayed(cimMap.getcimCalculatePreset3Btn());
			assertElementIsDisplayedWithXpath("//div[text()='Calculate "+cimScenarioCreate+"']//following::a[text()='Help']");
			doClick(cimMap.getcimCalculatePreset3Btn());
			assertElementIsDisplayed(cimMap.getcimCustomDate());
			assertElementIsDisplayed(cimMap.getcustomTime());
			ExtentReport.logPass("PASS", "test01ValidatePopup_CalculationOptions_20405");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidatePopup_CalculationOptions_20405", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02Validate_CalculationPopupClose_20405() throws Throwable {
		try {
			doClick("//div[@data-qtip='Close dialog']");
			assertElementIsNotDisplayed("//*[text()='Calculate "+cimScenarioCreate+"']");
			doClick(cimMap.getcimCalculateBtn());
			waitForPresenceOfElement("//*[text()='Calculate "+cimScenarioCreate+"']");
			doClick("//div[text()='Calculate "+cimScenarioCreate+"']//following::span[text()='Cancel & Close']/ancestor::a");
			assertElementIsNotDisplayed("//*[text()='Calculate "+cimScenarioCreate+"']");
			ExtentReport.logPass("PASS", "test02Validate_CalculationPopupClose_20405");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_CalculationPopupClose_20405", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03Validate_CalculationRecurringOptions_20405() throws Throwable {
		try {
			doClick(cimMap.getcimCalculateBtn());
			waitForPresenceOfElement("//*[text()='Calculate "+cimScenarioCreate+"']");
			doClick(cimMap.getcimCalculatePreset3Btn());
			selectDate("next",cimMap.getcimCustomDate());
			doClick(cimMap.getcustomTime());
			doDropdownSelectUsingOptionTextOnly(cimMap.getcustomTimeList(), "12:30 am");
			doClick(cimMap.getcimRecurringDrpdwn());
			compareList(cimMap.getcimRecurringValues(), recurringOptions);
			doClick("//div[text()='Calculate "+cimScenarioCreate+"']//following::span[text()='Cancel & Close']/ancestor::a");
			ExtentReport.logPass("PASS", "test03Validate_CalculationRecurringOptions_20405");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03Validate_CalculationRecurringOptions_20405", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04Validate_CalculationPopupSaveClose_20405() throws Throwable {
		try {
			doClick(cimMap.getcimCalculateBtn());
			waitForPresenceOfElement("//*[text()='Calculate "+cimScenarioCreate+"']");
			doClick(cimMap.getcimCalculatePreset3Btn());
			selectDate("next",cimMap.getcimCustomDate());
			doClick(cimMap.getcustomTime());
			doDropdownSelectUsingOptionTextOnly(cimMap.getcustomTimeList(), "12:30 am");
			doClick("//div[text()='Calculate "+cimScenarioCreate+"']//following::span[text()='Save & Close']");
			deleteCim();
			ExtentReport.logPass("PASS", "test04Validate_CalculationPopupSaveClose_20405");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04Validate_CalculationPopupSaveClose_20405", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
