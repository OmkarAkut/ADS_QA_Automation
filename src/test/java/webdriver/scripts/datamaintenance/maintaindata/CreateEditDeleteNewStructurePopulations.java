package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression: Automated test script for ADS-6396 */
public class CreateEditDeleteNewStructurePopulations extends GoHelper{
	final static String aTozPage="Populations";
	static DataMaintenanceMap dmMap;
	static ContractingMap contractMap;
	 private DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);

	static CostingMap costing;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String population = "Model " + currentDateTime;
	static String[] filter= {"Name","Is","Equal To",population};
	static String value="20";
	static String valueEdit="60";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CreateEditDeleteNewStructurePopulations",
				"webdriver.scripts.datamaintenance.maintaindata", "CreateEditDeleteNewStructurePopulations");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			costing=BuildMap.getInstance(driver, CostingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			waitForSpinnerToEnd();

			goToPage("Maintain Data");
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01CreateNewPopulationStructure() throws Throwable {
		try {
			doMaintainDataPageSelectAtoZOption(aTozPage);
			doClick(DataMaintenanceMap.getLoadDataNewButton());
			ContractModelsHelper.keyInValues(ContractingMap.getInputName(), population);
			ContractModelsHelper.keyInValues(ContractingMap.getFieldDropdown(), "Age In Years");
			ContractModelsHelper.keyInValues(ContractingMap.getValueInput(), value);
			doClick(DataMaintenanceMap.getPopulationAddButton());
			doClick(DataMaintenanceMap.getPopulationShowSizeButton());
			assertTextIsDisplayed("(Selected: 16334/2610846)");
			assertTextIsDisplayed("(Selected: 16334/2610889)");
			doClick(costing.getSaveCostModel());
			waitForDisplayedSpinnerToEnd();
			doClick(DataMaintenanceMap.getLoadDataFilterButton());
		    doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(),dialog.getFilterDialogDropdownField(), "Name");
		    doClick(dialog.getFilterDialogFormFieldValue());
		    dialog.getFilterDialogFormFieldValue().sendKeys(population);
		    doClick(dialog.getFilterDialogButtonAdd());
		    waitForAjaxExtJs();
		    doClick(dialog.getFilterDialogButtonApplyFilter());
		    waitForSpinnerToEnd();			assertTextIsDisplayed(population);
			ExtentReport.logPass("PASS", "test01CreateNewPopulationStructure");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CreateNewPopulationStructure", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02EditCreatedPopulation() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getLoadDataEditButton());
			doClick(ContractingMap.getASCFilterEditButton());
			ContractModelsHelper.keyInValues(ContractingMap.getValueInput(), valueEdit);
			doClick(contractMap.getContractModelUpdateFilterButton());
			doClick(DataMaintenanceMap.getPopulationShowSizeButton());
			assertTextIsDisplayed("(Selected: 21943/2610846)");
			doClick(costing.getSaveCostModel());
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test02EditCreatedPopulation");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditCreatedPopulation", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03DeleteCreatedPopulation() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getLoadDataDeleteButton());
			waitForElementToBeVisible(contractMap.getContractModelDeleteButtonInPopUp());
			doClick(contractMap.getContractModelDeleteButtonInPopUp());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test03DeleteCreatedPopulation");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeleteCreatedPopulation", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
