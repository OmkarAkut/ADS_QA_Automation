package webdriver.scripts.cim;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
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
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20054 **///Implement other filters once filter issue is fixed
public class AddAbilitytoFilterCostCalculationGroupsDisplayed extends CimHelper{
	private static CimMap cimMap;
	private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	static String[] filter = { "Name", "Is", "Equal To", "" };
	List<String> expectedFieldOptions=Arrays.asList("Name","Description","Next Start Time","Calc Status");
	List<String> expectedOperatorOptions=Arrays.asList("Is","Is not");
	List<String> expectedConditionOptions=Arrays.asList("Equal To","Contains","Starts With","Ends With","One Of");
	List<String> expectedLastCalcOptions=Arrays.asList("Equal To","Less Than","Between","Greater Than");
	static String calcType="Activity Volume Calc Scenario: ADS-262 Vol Calc";
	private static DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);
	
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		
		ExtentReport.reportCreate("AddAbilitytoFilterCostCalculationGroupsDisplayed", "webdriver.scripts.cim",
				"AddAbilitytoFilterCostCalculationGroupsDisplayed");
		try {
			cimMap = BuildMap.getInstance(driver, CimMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
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
	public void test01Validate_Filter_Conditions_20054() throws Throwable {
		try {
			assertTheElementIsEnabled(cimMap.getcimFilterButton());
			assertTheElementIsDisabled(cimMap.getcimClearFilterButton(), printout);
			doClick(cimMap.getcimFilterButton());
			waitForElementPresence("//*[text()='Calculation Groups']");
			assertElementIsDisplayed(dialog.getFilterDialogHelpLink());
			doFilterSetFilterParameters("Name","Is","Equal To","");
			driverDelay();
			doClick(cimMap.getcalcFieldBtn());
			compareList(cimMap.getcalcFilterField(), expectedFieldOptions);
			doClick(cimMap.getcalcOperatorBtn());
			compareList(cimMap.getcalcFilterOperator(), expectedOperatorOptions);
			doClick(cimMap.getcalcConditionBtn());
			compareList(cimMap.getcalcFilterCondition(), expectedConditionOptions);
			doFilterSetFilterParametersForDate("Last Calculation Date","Is","Equal To","");
			driverDelay();
			doClick(cimMap.getcalcConditionBtn());
			compareList(cimMap.getcalcFilterCondition(), expectedLastCalcOptions);
			ExtentReport.logPass("PASS", "test01Validate_Filter_Conditions_20054");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Validate_Filter_Conditions_20054", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02Validate_Filter_ValueField_20054() throws Throwable {
		try {
			try {
				doFilterSetFilterParametersForDate("Last Calculation Date","Is","Between","");
			} catch (Exception e) {
				
			}
			assertElementIsDisplayed(cimMap.getcalcFilterDate());
			assertElementIsDisplayed(cimMap.getcalcAndDate());
			ExtentReport.logPass("PASS", "test02Validate_Filter_ValueField_20054");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_Filter_ValueField_20054", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03Validate_Filter_Buttons_20054() throws Throwable {
		try {
			doFilterSetFilterParametersForDate("Last Calculation Date","Is","Equal To","");
			selectDate("current",cimMap.getcalcFilterValDate() );
			doClick("(//div[contains(@class,'x-docked x-toolbar-footer')])[2]");
			assertTheElementIsEnabled(dialog.getFilterDialogButtonAdd());
			ExtentReport.logPass("PASS", "test03Validate_Filter_Buttons_20054");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03Validate_Filter_Buttons_20054", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04Validate_Apply_Buttons_20054() throws Throwable {
		try {
			doClick(dialog.getFilterDialogButtonAdd());
			driverDelay();
			String dateCurrent=getCurrentDate("current");
			assertElementIsDisplayedWithXpath("//div[text()='Last Calculation Date Is Equal To "+dateCurrent+"']");
			doClick(cimMap.getFilterDialogFieldValueEdit());
			selectDate("next",cimMap.getcalcFilterValDate() );
			doClick(cimMap.getstatusFilterDialogFieldValueUpdate());
			String dateNext=getCurrentDate("next");
			assertElementIsDisplayedWithXpath("//div[text()='Last Calculation Date Is Equal To "+dateNext+"']");
			ExtentReport.logPass("PASS", "test04Validate_Apply_Buttons_20054");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04Validate_Apply_Buttons_20054", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05Validate_Remove_Buttons_20054() throws Throwable {
		try {
			doClick(cimMap.getstatusstatusFilterRemoveAllBtn());//Validate Remove All
			assertElementIsNotDisplayed("//td[contains(@class,'x-grid-cell-selected')]");
			doFilterSetFilterParametersForDate("Last Calculation Date","Is","Equal To","");
			selectDate("current",cimMap.getcalcFilterValDate() );
			doClick("(//div[contains(@class,'x-docked x-toolbar-footer')])[2]");
			assertElementIsDisplayed("//td[contains(@class,'x-grid-cell-selected')]");
			doClick(cimMap.getstatusstatusFilterRemoveBtn());//Validate Remove 
			assertElementIsNotDisplayed("//td[contains(@class,'x-grid-cell-selected')]");
			ExtentReport.logPass("PASS", "test05Validate_Remove_Buttons_20054");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05Validate_Remove_Buttons_20054", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06Validate_CancelClose_Button_20054() throws Throwable {
		try {
			doClick(cimMap.getstatusFilterCancelCloseBtn());
			assertElementIsNotDisplayed(cimMap.getstatusFilterCancelCloseBtn());
			ExtentReport.logPass("PASS", "test06Validate_CancelClose_Button_20054");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06Validate_CancelClose_Button_20054", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07Validate_ApplyFilter_Button_20054() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			assertTextIsDisplayed(cimScenarioCreate);
			deleteCim();
			ExtentReport.logPass("PASS", "test07Validate_ApplyFilter_Button_20054");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07Validate_ApplyFilter_Button_20054", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test08Validate_IsOneOfCondition_20054() throws Throwable {
		try {
			cimGroupList=createMultipleCIM(cimScenarioCreate,2,calcType);
			List<WebElement> cimRows = cimMap.getcimGrid();
			compareList(cimRows, cimGroupList);
			//Delete the newely created groups
			deleteMultipleCim();
			ExtentReport.logPass("PASS", "test08Validate_IsOneOfCondition_20054");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08Validate_IsOneOfCondition_20054", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
