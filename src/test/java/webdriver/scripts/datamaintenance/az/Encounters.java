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
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Encounters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozEncounters = "Encounters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String consumer="--00-- SIEVERS, Consumers85 <Open> - <Open>";
	static String careDeliveryFacility="0000 PRIVATE PAY";
	static String[] activityStatsMasterFilter= {"Name","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY";
	static String updatedName;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("Encounters", "webdriver.scripts.datamaintenance.az",
				"Encounters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozEncounters);
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
			keyInInputByName("encounterId", code, "Encounter");
			doClick(DataMaintenanceMap.getconsumerSelectBtn());
			waitForDisplayedSpinnerToEnd();
			selectFormItem(consumer, "");
			doClick(DataMaintenanceMap.getencounterCareDeliverySelectBtn());
			waitForDisplayedSpinnerToEnd();
			selectFormItem(careDeliveryFacility, "");
			clickCheckboxByName("encounterTypeCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getencounterTypeDrp(), "1S1 Office");
			clickCheckboxByName("admissionSourceCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getadmissionTypeDrp(), "1SM2 Admin Source 200");
			clickCheckboxByName("dischargeStatusCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getadmissionTypeDrp(), "1SM2 Admin Source 200");
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateSaveButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addNewScenario(code,name,"Department Hierarchy",DataMaintenanceMap.getinputCode(),DataMaintenanceMap.getaddName(),deptHierarchy,DataMaintenanceMap.getactivityStatsMasterDropdown(),DataMaintenanceMap.getactivityStatsMasterDropdownList(),"Save");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(activityStatsMasterFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test02ValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateSaveCloseButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addNewScenario(code,name,"Department Hierarchy",DataMaintenanceMap.getinputCode(),DataMaintenanceMap.getaddName(),deptHierarchy,DataMaintenanceMap.getactivityStatsMasterDropdown(),DataMaintenanceMap.getactivityStatsMasterDropdownList(),"Save & Close");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(activityStatsMasterFilter);
			doClick(DataMaintenanceMap.getazPageEditBtn());
			ExtentReport.logPass("PASS", "test03ValidateSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04AddActivityStatistics() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			addDetailsInnerPages(code, name, "Save & Create New","code","name");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test04AddActivityStatistics");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddActivityStatistics", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05EditActivityStatistics() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			updatedName="Updated"+name;
			addDetailsInnerPages(null, updatedName, "Save","code","name");
			addDetailsInnerPages(null, updatedName, "Save & Close","code","name");
			driverDelay();
			assertTextIsDisplayed(updatedName);
			ExtentReport.logPass("PASS", "test05EditActivityStatistics");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05EditActivityStatistics", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06AddNewMappings() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			assertElementValueAttribute(DataMaintenanceMap.getactivityStatsInputName(), updatedName, printout);
			doClick(DataMaintenanceMap.getmappingNewButton());
			doClick(DataMaintenanceMap.getcostCompAssignEntityBtn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getentityDropdownList(), entity);
			doClick(DataMaintenanceMap.getdeptGroupSelectBtn());
			waitForPresenceOfElementText("Add Department / Group");
			doClick("//div[text()='*ALLDEPTS ALLDEPTS']");
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClick(DataMaintenanceMap.getModifierSelectBtn());
			doClick("//div[text()='ALL 0ALL 0ALL Charge associated with ALL Dept ']");
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			keyInInputText("50", driver.findElement(By.name("convFactor")));
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			DataMaintenanceMap.getmappingCancelCloseButton().click();
			assertTextIsDisplayed("0000  PRIVATE PAY");
			assertTextIsDisplayed("*ALLDEPTS");
			assertTextIsDisplayed("0ALL");
			assertTextIsDisplayed("0ALL  0ALL Charge associated with ALL Dept");
			assertTextIsDisplayed("NONE");
			assertTextIsDisplayed("U");
			assertTextIsDisplayed("50.0000");
			ExtentReport.logPass("PASS", "test06AddNewMappings");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06AddNewMappings", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07DeleteMappings() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getmappingDeleteButton());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test07DeleteMappings");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07DeleteMappings", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08DeleteActivityStatistics() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			ExtentReport.logPass("PASS", "test08DeleteActivityStatistics");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08DeleteActivityStatistics", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09DeleteActivityStatisticsMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test09DeleteActivityStatisticsMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09DeleteActivityStatisticsMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
