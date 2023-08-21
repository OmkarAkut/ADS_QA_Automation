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
	static AdsHelper adsHelper = new AdsHelper();
	/** Regression: Automated test script for ADS-6406 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("RunEncounterServiceClassificationScheme",
				"webdriver.scripts.datamaintenance.maintaindata", "RunEncounterServiceClassificationScheme");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			statusMap = BuildMap.getInstance(driver, StatusMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			selectMaintainDataAtoZ(aTozPage);
			doClick(ContractingMap.getContractModelButtonFilter());
			adsHelper.doFilterCreate(filter);
			tableDoubleClickCellFirstColumn(batch);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01AssertPopulationAndServiceModelsForEncounterClassificationScheme() throws Throwable {
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
	public void test02ValidateRemoveButtonForEncounterClassificationScheme() throws Throwable {
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
	public void test03ValidateSaveAndEditEncounterClassificationScheme() throws Throwable {
		try {
			selectMaintainDataAtoZ(aToZPage2);
			waitForDisplayedSpinnerToEnd();
			waitForElementToBeVisible(dmMap.getencounterButtonFilter());
			doClick(dmMap.getencounterButtonFilter());
			adsHelper.doFilterCreate(filterByEncounterID);
			doClick(dmMap.getencounterButtonEdit());
			driverDelay(300);
			doClick(DataMaintenanceMap.getServicesTabEncounter());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			ExtentReport.logPass("PASS", "test03ValidateSaveAndEditEncounterClassificationScheme");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveAndEditEncounterClassificationScheme", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04ValidateAssignOptionForEncounterScheme() throws Throwable {
		try {
			selectMaintainDataAtoZ(aTozPage);
			doClick(ContractingMap.getContractModelButtonFilter());
			adsHelper.doFilterCreate(filter);
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
	public void test05VerifyServicesAssignedToEncounter() throws Throwable {
		try {
			selectMaintainDataAtoZ(aToZPage2);
			waitForDisplayedSpinnerToEnd();
			doClick(dmMap.getencounterButtonFilter());
			adsHelper.doFilterCreate(filterByEncounterID);
			doClick(dmMap.getencounterButtonEdit());
			driverDelay(300);
			doClick(DataMaintenanceMap.getServicesTabEncounter());
			driverDelay(200);
			ContractModelsHelper.CompareListToArray(
					driver.findElements(By.xpath("//*[text()='" + batch + "']//following::div[1]")),
					serviceModelsAfterAssign);
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			doClosePageOnLowerBar("Maintain Data");
			ExtentReport.logPass("PASS", "test05VerifyServicesAssignedToEncounter");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05VerifyServicesAssignedToEncounter", driver, e);
			fail(e.getMessage());
		}

	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
