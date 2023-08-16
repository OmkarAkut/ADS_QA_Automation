package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
//import webdriver.maps.xpath;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateDeleteCostMethodDeptMasterDischargeStatus extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	static ContractingMap contractMap;
	final static String aTozPageCostMethodMaster="Cost Method Masters";
	final static String aTozPageDeptMaster="Department Masters";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String deptCode = currentDateCode.replaceAll("\\W", "");
	static String costModel = "Model " + currentDateTime;
	static String costDeptModel = "Model " + currentDateTime;
	static String costComponentMaster="Actual CCM";
	static CostingMap costing;
	static String encounterName="Model"+currentDateTime;
	static String encounterShortName="Test";
	static String encounterNameBeforeEdit;
	static String encounterShortNameBeforeEdit;
	static String encounterNameAfterEdit;
	static String encounterShortNameAfterEdit;
	static String dischargeStatusName="Status"+currentDateTime;
	static int dischargeCode=javaGetRandomNumber(56,printout);
	static String[] filter= {"Name of the Cost Method Master","Is","Equal To",costModel};
	static String[] filterDeptMaster= {"Name","Is","Equal To",costModel};
	static String[] filterDichargeStatus= {"Name","Is","Equal To",dischargeStatusName};

	/** Regression: Automated test script for ADS-6655,ADS-6657,ADS-6656,ADS-6658 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CreateDeleteCostMethodDeptMasterDischargeStatus",
				"webdriver.scripts.datamaintenance.maintaindata", "CreateDeleteCostMethodDeptMasterDischargeStatus");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			costing=BuildMap.getInstance(driver, CostingMap.class);

			Login.loginUser("AutomationTesterAdmin");
			goToPage("Costing Data Maintenance");
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01AssertTreeTabMaintainData() throws Throwable {
		try {
			
			assertThatString(CostingMap.getCostMaintenanceContracting(), "Contracting", printout);
			assertThatString(CostingMap.getCostMaintenanceCosting(), "Costing", printout);
			assertThatString(CostingMap.getCostMaintenanceEpisode(), "Episode", printout);
			assertThatString(CostingMap.getCostMaintenanceGeneral(), "General", printout);
			ExtentReport.logPass("PASS", "test03AssertTreeTabMaintainData");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AssertTreeTabMaintainData", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02GotoDepartmentMasters() throws Throwable {
		try {
			doClickTreeData("Costing");
			waitForMainPageTitle("Codes and Hierarchies");
			doClickTreeData("Codes and Hierarchies");
			waitForMainPageTitle("Departments");
			doClickTreeData("Departments");
			driverDelay(200);
//			Omkar 8/8/2023 : xpath changes for 11.2
//			doClick("//div[@id='treepanelId-body']//following::div[text()='Department Masters']/img[@class='x-tree-elbow-end']");
			doClick("//div[@id='treepanelId-body']//following::span[text()='Department Masters']/../div[5]");
			ExtentReport.logPass("PASS", "test04GotoDepartmentMasters");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04GotoDepartmentMasters", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03CreateDepartmentMaster() throws Throwable {
		try {
			doClick(CostingMap.getCostDeptMasterNewButton());
			ContractModelsHelper.keyInValues(ContractingMap.getMedicareCode(), deptCode);
			ContractModelsHelper.keyInValues(ContractingMap.getInputName(), costDeptModel);
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			waitForDisplayedSpinnerToEnd();
			doClick(CostingMap.getCostDeptMasterFilterButton());
			doFilterCreate(filterDeptMaster);
			assertTextIsDisplayed(costDeptModel);
			ExtentReport.logPass("PASS", "test03CreateDepartmentMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03CreateDepartmentMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteDepartmentMaster() throws Throwable {
		try {
			doClick(CostingMap.getCostDeptMasterDeleteButton());
			waitForElementToBeVisible(ContractingMap.getWarningPopUpDeleteButton());
			assertElementIsDisplayed(ContractingMap.getWarningPopUpDeleteButton());
			assertElementIsDisplayed(ContractingMap.getContractCalculationCloseViewDialog());
			doClick(ContractingMap.getWarningPopUpDeleteButton());
			waitForDisplayedSpinnerToEnd();
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04DeleteDepartmentMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteDepartmentMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05EditEncounterType() throws Throwable {
		try {
			doClickTreeData("Encounters");
			driverDelay(200);
//			Omkar 11/8/2023 : xpath changes for 11.2
//			doClick("//div[@id='treepanelId-body']//following::div[text()='Encounter Types']/img[contains(@class,'x-tree-icon')]");
			doClick("//div[@id='treepanelId-body']//following::span[text()='Encounter Types']/../div[5]");
			encounterNameBeforeEdit=CostingMap.getEncounterNameinGrid().getText();
			encounterShortNameBeforeEdit=CostingMap.getEncounterShortNameinGrid().getText();
			doClick(CostingMap.getEncounterTypeEditButton());
			ContractModelsHelper.keyInValues(ContractingMap.getInputName(), encounterName);
			ContractModelsHelper.keyInValues(CostingMap.getEncounterShortName(), encounterShortName);
			doClick(ContractingMap.getNewFolderNameSave());
			waitForAjaxExtJs();
			assertElementTextContains(CostingMap.getEncounterNameinGrid(), encounterName, printout);
			assertElementTextContains(CostingMap.getEncounterShortNameinGrid(), encounterShortName, printout);
			if(!encounterNameBeforeEdit.equals(CostingMap.getEncounterNameinGrid().getText())) {
				assertTrue(printout);
			}
			if(!encounterShortNameBeforeEdit.equals(CostingMap.getEncounterShortNameinGrid().getText())) {
				assertTrue(printout);
			}
			ExtentReport.logPass("PASS", "test05EditEncounterType");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05EditEncounterType", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06CreateNewDischargeStatus() throws Throwable {
		try {
//			Omkar 16/8/2023 : xpath changes for 11.2
//			doClick("//div[@id='treepanelId-body']//following::div[text()='Discharge Statuses']/img[contains(@class,'x-tree-icon')]");
			doClick("//div[@id='treepanelId-body']//following::span[text()='Discharge Statuses']/../div[5]");
			doClick(CostingMap.getCostDischargeStatusNewButton());
			ContractModelsHelper.keyInValues(ContractingMap.getMedicareCode(),String.valueOf(dischargeCode)+"D");
			ContractModelsHelper.keyInValues(ContractingMap.getInputName(), dischargeStatusName);
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			waitForDisplayedSpinnerToEnd();
			doClick(CostingMap.getCostDischargeStatusFilterButton());
			doFilterCreate(filterDichargeStatus);
			assertTextIsDisplayed(dischargeStatusName);
			ExtentReport.logPass("PASS", "test06CreateNewDischargeStatus");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06CreateNewDischargeStatus", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeleteNewDischargeStatus() throws Throwable {
		try {
			doClick(CostingMap.getCostDischargeStatusDeleteButton());
			waitForElementToBeVisible(ContractingMap.getWarningPopUpDeleteButton());
			assertElementIsDisplayed(ContractingMap.getWarningPopUpDeleteButton());
			assertElementIsDisplayed(ContractingMap.getContractCalculationCloseViewDialog());
			doClick(ContractingMap.getWarningPopUpDeleteButton());
			waitForDisplayedSpinnerToEnd();
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test06DeleteNewDischargeStatus");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06DeleteNewDischargeStatus", driver, e);
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void test07CreateCostMethodMaster() throws Throwable {
		try {
			selectMaintainDataAtoZ(aTozPageCostMethodMaster);

			doClick(CostingMap.getCostMethodMasterNewButton());
			ContractModelsHelper.keyInValues(ContractingMap.getInputName(), costModel);
			doDropdownSelectUsingOptionText(CostingMap.getCostMethodMasterCostComponentMaster(), CostingMap.getCostMethodMasterCostComponentMasterScenarioOptions(), costComponentMaster);
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			waitForDisplayedSpinnerToEnd();
			doClick(CostingMap.getCostMethodMasterFilterButton());
			doFilterCreate(filter);
			assertTextIsDisplayed(costModel);
			ExtentReport.logPass("PASS", "test07CreateCostMethodMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07CreateCostMethodMaster", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test08DeleteCostMethodMaster() throws Throwable {
		try {
		doClick(CostingMap.getCostMethodMasterDeleteButton());
		waitForElementToBeVisible(ContractingMap.getWarningPopUpDeleteButton());
		assertElementIsDisplayed(ContractingMap.getWarningPopUpDeleteButton());
		assertElementIsDisplayed(ContractingMap.getContractCalculationCloseViewDialog());
		doClick(ContractingMap.getWarningPopUpDeleteButton());
		waitForDisplayedSpinnerToEnd();
		assertTextIsDisplayed("There is no data available to display.");
		ExtentReport.logPass("PASS", "test08DeleteCostMethodMaster");
	} catch (Exception | AssertionError e) {
		ExtentReport.logFail("FAIL", "test08DeleteCostMethodMaster", driver, e);
		fail(e.getMessage());
	}
	}
	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("Maintain Data");
		ExtentReport.report.flush();
	}
	
}
