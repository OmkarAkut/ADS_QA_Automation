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
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20053 **/
public class AddAbilitytoCalculateaCostCalculationGroup extends CimHelper {

	private static CimMap cimMap;
	 static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	 static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	static String calcType="Activity Volume Calc Scenario: ADS-262 Vol Calc";
	private static DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);
	
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {

		ExtentReport.reportCreate("AddAbilitytoCalculateaCostCalculationGroup", "webdriver.scripts.cim",
				"AddAbilitytoCalculateaCostCalculationGroup");
		try {
			cimMap = BuildMap.getInstance(driver, CimMap.class);
//			costing=BuildMap.getInstance(driver, CostingMap.class);
			Login.loginUser("CostAnalyst1");
			goToPage("Cost Integration Manager");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01ValidateCalculate_Warning_Popup_20053() throws Throwable {
		try {
			doClick(cimMap.getcimNewBtn());
			assertTheElementIsDisabled(cimMap.getcimCalculateOptionBtn(), printout);
			doClick(cimMap.getcimCancelCloseBtn());
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			doClick(cimMap.getcimEditBtn());
			assertTheElementIsEnabled(cimMap.getcimCalculateOptionBtn());
			doClick(cimMap.getcimCancelCloseBtn());
			doClick(cimMap.getcimCalculateBtn());
			doClick("(//div[contains(@class,'x-closable ')]//following::span[text()='Calculate Now'])");
			assertTextIsDisplayed("Are you sure you want to calculate this group?");
			assertTextIsDisplayed(" to calculate. Progress can be monitored on the Calculation Status page.");
			assertTextIsDisplayed(" to return to the previous screen without running the calculation.");
			assertTheElementIsEnabled(cimMap.getcimCalculateNowConfirmBtn());
			assertTheElementIsEnabled(cimMap.getcimCalculateCancelConfirmBtn());
			doClick(cimMap.getcimCalculateCancelConfirmBtn());
			assertTextIsNotDisplayed(" Close Calculation Status");
			ExtentReport.logPass("PASS", "test01ValidateCalculate_Warning_Popup_20030");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateCalculate_Warning_Popup_20030", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02Validate_CalculateNow_20053() throws Throwable {
		try {
			checkElements(cimMap.getcimCalculateNowBtn());
			doClick(cimMap.getcimCalculateNowConfirmBtn());
			assertTextIsDisplayed(" Close Calculation Status");
			assertElementIsDisplayedWithXpath(
					"//span[text()='Scenario Name']//following::td[contains(@class,'x-grid-cell-rownumberer')]//following::div[text()='"
							+ cimScenarioCreate + "']//following::td/div[text()='Costing']");
			assertElementIsDisplayedWithXpath(
					"//span[text()='Scenario Name']//following::td[contains(@class,'x-grid-cell-rownumberer')]//following::div[text()='"
							+ cimScenarioCreate + "']//following::td/div[text()='Cost Integration Manager (CIM)']");
			doClosePageOnLowerBar("Calculation Status");
			deleteCim();
			doClosePageOnLowerBar("Cost Integration Manager (CIM)");

			ExtentReport.logPass("PASS", "test02Validate_CalculateNow_20053");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_CalculateNow_20053", driver, e);
			fail(e.getMessage());
		}
	}
	public void applyFilter(String[] filterParameters) throws Throwable {
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(),dialog.getFilterDialogDropdownField(), filterParameters[0]);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameOperator(),dialog.getFilterDialogDropdownOperator(), filterParameters[1]);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameCondition(),dialog.getFilterDialogDropdownCondition(), filterParameters[2]);
		doClick(dialog.getFilterDialogFormFieldValue());
		dialog.getFilterDialogFormFieldValue().sendKeys(filterParameters[3]);
		
		waitUntilElementIsClickable(dialog.getFilterDialogButtonAdd());
		doClick(dialog.getFilterDialogButtonAdd());
		waitForAjaxExtJs();
		doClick(dialog.getFilterDialogButtonApplyFilter());
		waitForSpinnerToEnd();
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
