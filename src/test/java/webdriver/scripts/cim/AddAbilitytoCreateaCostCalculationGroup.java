package webdriver.scripts.cim;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Keys;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20031 **/
public class AddAbilitytoCreateaCostCalculationGroup extends CimHelper{
	private static CimMap cimMap;
	private static String searchScenario="*";
	private static List<String> selectedListBefore;
	private static List<String> selectedListAfter;
	private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	List<String> expectedCalculationTypeOptions=Arrays.asList("<All>",
																"Activity Vol Calc Scenario",
																"Cost Model Calc Scenario",
																"Encounter Cost Calc Scenario",
																"GL Adj and Reclass Calc Scenario",
																"Overhead Model Calc",
																"Price List Calc Scenario",
																"RVU Calc Scenario",
																"Statistic Data Calc Scenario");
	static String calcType=getProperty().replaceAll("^\"|\"$", "");
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		
		ExtentReport.reportCreate("AddAbilitytoCreateaCostCalculationGroup", "webdriver.scripts.cim",
				"AddAbilitytoCreateaCostCalculationGroup");
		try {
			cimMap = BuildMap.getInstance(driver, CimMap.class);
			Login.loginUser("CostAnalyst1");
			goToPage("Cost Integration Manager");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01ValidateNewCimPage_20031() throws Throwable {
		try {
			doClick(cimMap.getcimNewBtn());
			waitForElementToBeVisible(cimMap.getcimName());
			assertTextIsDisplayed("Create New Calculation Group");
			assertElementIsDisplayed("//span[contains(@class,'x-btn-inner')][text()='Cost Integration Manager (CIM)']");
			assertElementIsDisplayed("//label[contains(@class,'onlyHelpLinkButton ')]//a[text()='Help']");
			assertElementIsDisplayed("(//span[text()='Name']//following::span[contains(text(),'*')])[1]");
			ExtentReport.logPass("PASS", "test01ValidateNewCimPage_20031");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewCimPage_20031", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test02ValidateAllowedNoOfCharacters_20031() throws Throwable {
		try {
			assertThatFieldValueContainsString(cimMap.getcimName(),"100","maxlength");
			assertThatFieldValueContainsString(cimMap.getcimDescription(),"150","maxlength");
			ExtentReport.logPass("PASS", "test01ValidateAllowedNoOfCharacters_20031");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateAllowedNoOfCharacters_20031", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateSelectCalculationScenarios_20031() throws Throwable {
		try {
			assertElementIsDisplayed("//div[text()='Select Calculation Scenarios']/span[\"*\"]");
			assertThatFieldValueContainsString(cimMap.getcimCalcTypeDrpdwn(),"<All>","placeholder");
			assertTextIsDisplayed("0 Item(s) Selected");
			assertTheElementIsDisabled(cimMap.getcimCalculationOption(), printout);
			assertTheElementIsDisabled(cimMap.getcimSaveCloseBtn(), printout);
			assertTheElementIsDisabled(cimMap.getcimSaveCreateNewBtn(), printout);
			assertTheElementIsDisabled(cimMap.getcimSave(), printout);
			ExtentReport.logPass("PASS", "test01ValidateSelectCalculationScenarios_20031");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSelectCalculationScenarios_20031", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04ValidateCalculationTypes_20031() throws Throwable{
		try {
			doClick(cimMap.getcimCalcTypeDrpdwn());
			checkDropdownValuesAscending(cimMap.getcimCalcTypeDrpdwnValues());
			compareList(cimMap.getcimCalcTypeDrpdwnValues(), expectedCalculationTypeOptions);
			ExtentReport.logPass("PASS", "test04ValidateCalculationTypes_20031");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ValidateCalculationTypes_20031", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05ValidateSearchScenario_20031() throws Throwable{
		try {
			
			cimMap.getcimScenarioSearchInput().sendKeys(searchScenario);
			
			cimMap.getsearchIcon().click();
			searchStringInList(cimMap.getcimAvailablePanel(),searchScenario);
			cimMap.getcimScenarioSearchInput().sendKeys(Keys.BACK_SPACE);
			ExtentReport.logPass("PASS", "test05ValidateSearchScenario_20031");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05ValidateSearchScenario_20031", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test06ValidateCalculationTypeFilters_20031() throws Throwable{
		try {
			
			selectDropdownOption(cimMap.getcimCalcTypeDrpdwn(),cimMap.getcimCalcTypeDrpdwnValues(),cimMap.getcimAvailablePanel());
			assertTextIsDisplayed("Note: Calculations will be executed in the order listed. Use drag-and-drop or the arrow buttons to reorder.");//Shilpa:Updated for 11.2.1
			ExtentReport.logPass("PASS", "test06ValidateCalculationTypeFilters_20031");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06ValidateCalculationTypeFilters_20031", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test07ValidateUpDownButtons_20031() throws Throwable {
		try {
			doClick(cimMap.getcimCalcTypeDrpdwn());
			doClick("//div[contains(@class,'boundlist')]/ul/li[text()='<All>']");
			selectAvailableItems(cimMap.getcimAvailablePanel(), 3);
			doClick(cimMap.getcalcTypeSelectBtn());
			for (int i = 0; i < cimMap.getcimSelectedPanel().size(); i++) {
				selectedListBefore = addElementToList(cimMap.getcimSelectedPanel());
				doClick(cimMap.getcimSelectedPanel().get(i));
				String itemAtPosition1 = cimMap.getcimSelectedPanel().get(i).getText();
				doClick(cimMap.getcimDownArrowBtn());
				String itemAtPosition2 = cimMap.getcimSelectedPanel().get(i + 1).getText();
				if (itemAtPosition1.equals(itemAtPosition2)) {
					assertTrue(printout);
				} else {
					fail();
				}
				doClick(cimMap.getcimUpArrowBtn());
				selectedListAfter = addElementToList(cimMap.getcimSelectedPanel());
				if (selectedListBefore.equals(selectedListAfter)) {
					assertTrue(printout);
					break;
				} else {
					fail();
				}
			}
			doClick(cimMap.getcalcTypeRemoveAllBtn());
			ExtentReport.logPass("PASS", "test07ValidateUpDownButtons_20031");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07ValidateUpDownButtons_20031", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test08ValidateButtonsCalcType_20031() throws Throwable {
		try {
			doClick(cimMap.getcalcTypeSelectAllBtn());
			System.out.println(cimMap.getavailableCountLabel().getText().split(" ")[4]);
			System.out.println(cimMap.getselectedCountLabel().getText().split(" ")[0]);
			if(cimMap.getavailableCountLabel().getText().split(" ")[4].contains(cimMap.getselectedCountLabel().getText().split(" ")[0])) {//Validate for SelectAll
				assertTrue(printout);
			}else {fail();}
			doClick(cimMap.getcalcTypeRemoveAllBtn());
			assertElementTextContains(cimMap.getselectedCountLabel(), "0 Item(s) Selected", printout);
			driverDelay();
			doClick("((//div[contains(@class,'hierarchyGrid ')])[1]//div/table//div)[1]");
			doClick(cimMap.getcalcTypeSelectBtn());
			assertElementTextContains(cimMap.getselectedCountLabel(), "1 Item(s) Selected", printout);
			doClick("((//div[contains(@class,'hierarchyGrid ')])[2]//div/table//div)[1]");
			doClick(cimMap.getcalcTypeRemoveBtn());
			assertElementTextContains(cimMap.getselectedCountLabel(), "0 Item(s) Selected", printout);
			ExtentReport.logPass("PASS", "test08ValidateButtonsCalcType_20031");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08ValidateButtonsCalcType_20031", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09ValidateCancelCloseBtn_20031() throws Throwable {
		try {
			doClick(cimMap.getcimCancelCloseBtn());
			try {
				if(cimMap.getcancelCloseWarningBtn().isDisplayed()) {
					cimMap.getcancelCloseWarningBtn().click();
				}
			} catch (Exception e) {
				
			}
			
			ExtentReport.logPass("PASS", "test09ValidateCancelCloseBtn_20031");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09ValidateCancelCloseBtn_20031", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10ValidateDuplicateEntryMessage_20031() throws Throwable {
		try {
			String cimFirstRecord=cimMap.getcimFormFirstEle().getText();
			createNewScenario(cimFirstRecord,calcType);
			doClick(cimMap.getcimSave());
			assertTextIsDisplayed("Your entry already exists. Please type a unique value.");
			doClick("//span[text()='OK']/ancestor::a");
			test09ValidateCancelCloseBtn_20031();
			createNewScenario(cimScenarioCreate,calcType);
			//Save with valid name
			doClick(cimMap.getcimSaveCloseBtn());
			doClick(cimMap.getcimFilterButton());
			doFilterCreateCIM(filterCim);
			//Validate new cim scenario is created
			assertTextIsDisplayed(cimScenarioCreate);
			//Delete the newely created
			doClick(cimMap.getcimDeleteButton());
			doClick(cimMap.getcimWarningDeleteButton());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(cimMap.getcimClearFilterButton());
			   waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test10ValidateDuplicateEntryMessage_20031");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10ValidateDuplicateEntryMessage_20031", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
	
		ExtentReport.report.flush();

	}
}
