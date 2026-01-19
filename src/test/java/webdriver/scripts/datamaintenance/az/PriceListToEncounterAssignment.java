package webdriver.scripts.datamaintenance.az;

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
import webdriver.helpers.AzHelper;
import webdriver.helpers.CimHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PriceListToEncounterAssignment extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPriceListToEncounterAssignment = "Price List to Encounters Assignments";
	public static DialogsMap dialog;
	public static ContractingMap contractMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] priceListFilter= {"Name","Is","Equal To",name};
	static String[] careFacility= {"0000 PRIVATE PAY"};
	static String[] practitioner= {"00000  Maggie  Goertz"};
	static String[] priceListCalcFilter= {"Scenario Name","Is","Equal To",name};
	static String sourceList="150FY02 Marina Hosp Price List FY02";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("PriceListToEncounterAssignment", "webdriver.scripts.datamaintenance.az",
				"PriceListToEncounterAssignment");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			contractMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPriceListToEncounterAssignment);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01ValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name, "Price List to Encounters Assignment");
			keyInInputByName("description", "Price List Description", "Price List to Encounters Assignment");
			clickCheckboxByName("priceListId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getpriceListAssignDrp(), sourceList);
			expandPanel("Patient Criteria");
			doClickButton("Other Fields");
			doClickButton("Care Delivery Facilities");
			doClick("(//div[text()='Price List to Encounters Assignment']//following::span[text()='Select'])[2]");
			driverDelay();
			ContractModelsHelper.selectMultipleColumnsToDisplay(careFacility);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClickButton("Attending Practitioners");
			doClick("(//div[text()='Price List to Encounters Assignment']//following::span[text()='Select'])[4]");
			driverDelay();
			ContractModelsHelper.selectMultipleColumnsToDisplay(practitioner);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			clickCheckboxByName("useAdmissionTimePeriod");
			clickCheckboxByName("shareLog");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(priceListFilter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed("150FY02  Marina Hosp Price List FY02");
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02EditPriceListEncounterAssignment() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClickButton("Patient Charges");
			clickCheckboxByName("sourceChargeScenario");
			selectDropdown("Estimated Charges 1", "Price List to Encounters Assignment");
			expandPanel("Patient Criteria");
			doClick("(//div[text()='Price List to Encounters Assignment']//following::span[text()='Select'])[4]");
			driverDelay();
			ContractModelsHelper.selectMultipleColumnsToDisplay(practitioner);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClick(DataMaintenanceMap.getazSaveBtn());
			
			
			ExtentReport.logPass("PASS", "test02EditPriceListEncounterAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditPriceListEncounterAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03AssignPriceListEncounterAssignment() throws Throwable {
		try {
			doClickButtons("Price List to Encounters Assignment", "Assign");
			driverDelay(200);
			doFilterCalculationPage(priceListCalcFilter);
			waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Calculation Status");
			ExtentReport.logPass("PASS", "test03AssignPriceListEncounterAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AssignPriceListEncounterAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04RemovePriceListEncounterAssignment() throws Throwable {
		try {
			doClickButtons("Price List to Encounters Assignment", "Remove");
			driverDelay(200);
			doFilterCalculationPage(priceListCalcFilter);
			waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Calculation Status");
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test04RemovePriceListEncounterAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04RemovePriceListEncounterAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeletePriceListEncounterAssignment() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsNotDisplayed("There is not data available to display.");
			ExtentReport.logPass("PASS", "test05DeletePriceListEncounterAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeletePriceListEncounterAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06AddPopulationPriceListEncounterAssignment() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name, "Price List to Encounters Assignment");
			keyInInputByName("description", "Price List Description", "Price List to Encounters Assignment");
			clickCheckboxByName("priceListId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getpriceListAssignDrp(), sourceList);
			expandPanel("Patient Criteria");
			doClickButton("Population");
			doClick("(//div[text()='Price List to Encounters Assignment']//following::span[text()='Select'])[1]");
			selectFormItem("# ASESC-2471", "");
			clickCheckboxByName("shareLog");
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			waitForDisplayedSpinnerToEnd();
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(priceListFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsNotDisplayed("There is not data available to display.");
			ExtentReport.logPass("PASS", "test05DeletePriceListEncounterAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeletePriceListEncounterAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}

