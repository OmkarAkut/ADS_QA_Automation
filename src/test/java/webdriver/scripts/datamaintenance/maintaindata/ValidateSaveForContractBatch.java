package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

public class ValidateSaveForContractBatch extends CalculationHelper{

	static DataMaintenanceMap dmMap;
	final static String aTozPageContractBatches = "Contract Batches";
	static String contractBatch="ADS-757 Contract Batch 2";
	static String[] filter = { "Name", "Is", "Equal To", contractBatch };
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String newContractBatch="Batch "+currentDateTime;
	static String selectedModel;
	/** Support Issues: Automated test script for ADS-12620 **/

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateSaveForContractBatch", "webdriver.scripts.datamaintenance.maintaindata",
				"ValidateSaveForContractBatch");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageContractBatches);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ValidateSaveForContractBatches_12620() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getcontractBatchFilterBtn());
			waitForPresenceOfElement("//div[contains(@id,'filterwindow')][text()='Filter Contract Batches']");
			doFilterCreate(filter);
			doClick(DataMaintenanceMap.getcontractBatchEditBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getcontractBatchSelectBtn());
			doClick(DataMaintenanceMap.getcontractBatchSelectBtn());
			waitForPresenceOfElement("//div[contains(@id,'dynamicwindow')][text()='Add Contracts']");
			doClick(DataMaintenanceMap.getcontractBatchEle());
			selectedModel=DataMaintenanceMap.getcontractBatchEle().getText();
			doClick(DataMaintenanceMap.getaddContractSelectBtn());
			doClick(DataMaintenanceMap.getaddContractApplyBtn());
			doClick(DataMaintenanceMap.getaddContractSaveBtn());
			doClick(DataMaintenanceMap.getaddContractSaveCloseBtn());
			doClick(DataMaintenanceMap.getcontractBatchEditBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getcontractBatchSelectBtn());
			assertTextIsDisplayed(selectedModel);
			doClick(DataMaintenanceMap.getcontractBatchSelectBtn());
			doClick("//div[contains(@id,'dynamicwindow')][text()='Add Contracts']//following::div[text()='"+selectedModel+"']");
			doClick(DataMaintenanceMap.getaddContractRemoveBtn());
			doClick(DataMaintenanceMap.getaddContractApplyBtn());
			doClick(DataMaintenanceMap.getaddContractSaveBtn());
			ExtentReport.logPass("PASS", "test01ValidateSaveForContractBatches_12620");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveForContractBatches_12620", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test02ValidateSaveAsForContractBatches_12620() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getaddContractSaveAsBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getdynamicSaveAsWindow());
			keyInInputText(newContractBatch, DataMaintenanceMap.getdynamicWindowNameInput());
			doClick(DataMaintenanceMap.getdynamicWindowSaveClose());
			doClick(DataMaintenanceMap.getaddContractCancelCloseBtn());
			String[] filterNewBatch = { "Name", "Is", "Equal To", newContractBatch };
			doClick(DataMaintenanceMap.getaddContractClearFilterBtn());
			waitForAjaxExtJs();
			doClick(DataMaintenanceMap.getcontractBatchFilterBtn());
			waitForPresenceOfElement("//div[contains(@id,'filterwindow')][text()='Filter Contract Batches']");
			doFilterCreate(filterNewBatch);
			assertTextIsDisplayed(newContractBatch);
			doClick(DataMaintenanceMap.getaddContractDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			doClosePageOnLowerBar("Maintain Data");
			ExtentReport.logPass("PASS", "test01ValidateSaveForContractBatches_12620");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveForContractBatches_12620", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
	
	
}
