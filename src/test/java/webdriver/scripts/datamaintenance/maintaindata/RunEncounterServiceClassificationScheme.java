package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.AdsHelper;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.StatusMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class RunEncounterServiceClassificationScheme extends CalculationHelper {
	final static String aTozPage = "Encounter Service Classification Schemes";
	final static String batch = "v10.4 REGRESSION Enc Serv Class Scheme";
	final static String aToZPage2 = "Encounters";
	final static String encounterName = "OPPS2021DRUGRA001";
	static String population = "v104 OPPS 2021 Radiopharm A";
	String[] serviceModels = { "OPPS 2021", "QAREGRESSION34567890123456789012345678901" };
	String[] serviceModelsAfterAssign = { "QAREGRESSION34567890123456789012345678901", "OPPS 2021" };
	static String[] filter = { "Name", "Is", "Equal To", batch };
	static String[] filterByEncounterID = { "Encounter ID", "Is", "Equal To", encounterName };
	static DataMaintenanceMap dmMap;
	static ContractingMap contractMap;
	static StatusMap statusMap;
	public static DialogsMap dialog;
	
	/** Regression: Automated test script for ADS-6406 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("RunEncounterServiceClassificationScheme",
				"webdriver.scripts.datamaintenance.maintaindata", "RunEncounterServiceClassificationScheme");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			statusMap = BuildMap.getInstance(driver, StatusMap.class);
			 dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			selectMaintainDataAtoZ(aTozPage);
			
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			fail(e.getMessage());
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			
		}
	}

	@Test
	public void test01AssertPopulationAndServiceModelsForEncounterClassificationScheme_ADS_6102() throws Throwable {
		doClick(ContractingMap.getContractModelButtonFilter());
		applyFilter(filter);
		tableDoubleClickCellFirstColumn(batch);
		try {
			assertThatString(DataMaintenanceMap.getPopulationValue(), population, printout);
			ContractModelsHelper.CompareListToArray(dmMap.getServiceModelList(), serviceModels);
			ExtentReport.logPass("PASS", "test01AssertPopulationAndServiceModelsForEncounterClassificationScheme");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AssertPopulationAndServiceModelsForEncounterClassificationScheme", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test02ValidateRemoveButtonForEncounterClassificationScheme_ADS_6102() throws Throwable {
		try {
			waitUntilElementIsClickable(contractMap.getContractModelButtonColumnsToDisplayModalRemove());
			doClick(contractMap.getContractModelButtonColumnsToDisplayModalRemove());
			Thread.sleep(1000);
			waitForPageTitle("Calculation Status");
			doClick(statusMap.getCalculationStatusPageFormFieldSearch());
			statusMap.getCalculationStatusPageFormFieldSearch().sendKeys(batch);
			doClick(statusMap.getCalculationStatusPageButtonSearchGlass());
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			driverDelay(500);
			confirmCalculationStatusDetailsContains("Process Completed");
			closeViewDialog();
			deleteCalculationStatusMyStatusPageFirstRow();
			doClosePageOnLowerBar("Calculation Status");
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			ExtentReport.logPass("PASS", "test02ValidateRemoveButtonForEncounterClassificationScheme");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateRemoveButtonForEncounterClassificationScheme", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03ValidateSaveAndEditEncounterClassificationScheme_ADS_6102() throws Throwable {
		try {
			selectMaintainDataAtoZ(aToZPage2);
			waitForDisplayedSpinnerToEnd();
			waitForElementToBeVisible(dmMap.getencounterButtonFilter());
			doClick(dmMap.getencounterButtonFilter());
			applyFilter(filterByEncounterID);
			doClick(dmMap.getencounterButtonEdit());
			driverDelay(300);
			doClick(DataMaintenanceMap.getServicesTabEncounter());
			assertTextIsDisplayed("There is no data available to display.");
			doClick("//span[text()='Cancel & Close']");
			ExtentReport.logPass("PASS", "test03ValidateSaveAndEditEncounterClassificationScheme");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveAndEditEncounterClassificationScheme", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04ValidateAssignOptionForEncounterScheme_ADS_6102() throws Throwable {
		try {
			selectMaintainDataAtoZ(aTozPage);
			doClick(ContractingMap.getContractModelButtonFilter());
			applyFilter(filter);
			tableDoubleClickCellFirstColumn(batch);
			doClick(DataMaintenanceMap.getAssignButtonEncounter());
			waitForPageTitle("Calculation Status");
			doClick(statusMap.getCalculationStatusPageFormFieldSearch());
			statusMap.getCalculationStatusPageFormFieldSearch().sendKeys(batch);
			doClick(statusMap.getCalculationStatusPageButtonSearchGlass());
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			driverDelay(5000);
			confirmCalculationStatusDetailsContains("Process Completed");
			closeViewDialog();
			deleteCalculationStatusMyStatusPageFirstRow();
			doClosePageOnLowerBar("Calculation Status");
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			ExtentReport.logPass("PASS", "test04ValidateAssignOptionForEncounterScheme");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ValidateAssignOptionForEncounterScheme", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test05VerifyServicesAssignedToEncounter_ADS_6102() throws Throwable {
		try {
			selectMaintainDataAtoZ(aToZPage2);
			waitForDisplayedSpinnerToEnd();
			doClick(dmMap.getencounterButtonFilter());
			applyFilter(filterByEncounterID);
			doClick(dmMap.getencounterButtonEdit());
			driverDelay(300);
			doClick(DataMaintenanceMap.getServicesTabEncounter());
			driverDelay(200);
			ContractModelsHelper.CompareListToArray(
					driver.findElements(By.xpath("//span[text()='Cancel & Close']")),
					serviceModelsAfterAssign);
			doClick("//span[text()='Cancel & Close']");
			doClosePageOnLowerBar("Maintain Data");
			ExtentReport.logPass("PASS", "test05VerifyServicesAssignedToEncounter");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05VerifyServicesAssignedToEncounter", driver, e);
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
