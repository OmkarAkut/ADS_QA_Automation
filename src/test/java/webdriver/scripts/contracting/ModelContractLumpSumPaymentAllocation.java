package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

public class ModelContractLumpSumPaymentAllocation extends CalculationHelper {
	private static ContractingMap contractingMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String lumpSumPaymentAlloc = "Allocation " + currentDateTime;
	static final String ContractModelName = "ADS-1320 Contract Model D";
	static String fieldName = "AP DRG Code";
	static String fieldValue = "2";
	static String LumpSumPaymentAllocTerm="Lump Sum Payment Allocation Rules";

	/** Regression: Automated test script for ADS-6468 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ModelContractLumpSumPaymentAllocation", "webdriver.scripts.contracting",
				"ModelContractLumpSumPaymentAllocation");
		try {
			contractingMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
			waitForAjaxExtJs();
			assertThatString(contractingMap.getContractModelHeader(), "Contracting Model Library", printout);
			doClick(CostingMap.getContractingName);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01NewLumpSumPaymentAllocation() throws Throwable {
		try {
			doClickTreeItem("Contracting");
			doSearchForContractModel(ContractModelName);
			tableDoubleClickCellFirstColumn(ContractModelName);
			driverDelay(1200);
			assertTextIsDisplayed("Unpublished Contract Task List");
			assertTextIsDisplayed("Build Structure Elements");
			assertTextIsDisplayed("Model Contract");
			assertTextIsDisplayed("Publish Contract");
			assertTextIsDisplayed("Export Contract");
			doClickTreeItem("Model Contract");
			driverDelay(200);
			assertTextIsDisplayed("General Information - Unpublished Contract");
			assertTextIsDisplayed("Define Payment Terms");
			assertElementIsDisplayed(ContractingMap.getAssertCalculateOption());
			doClickTreeItem("Define Payment Terms");
			driverDelay(200);
			doClickTreeItemWithCheckbox("Lump Sum Payment Allocation Rules");
			doClick(driver.findElement(By.xpath("//h1[text()='"+LumpSumPaymentAllocTerm+"']//following::span[1]//parent::button")));
			waitForPageTitle("New Lump Sum Payment Allocation Rule");
			assertTextIsDisplayed("General");
			assertTextIsDisplayed("Allocation Rules");
			ContractModelsHelper.keyInValues(ContractingMap.getInputName(), lumpSumPaymentAlloc);
			ContractModelsHelper.keyInValues(ContractingMap.getFieldDropdown(), fieldName);
			ContractModelsHelper.keyInValues(ContractingMap.getValueInput(), fieldValue);
			doClick(ContractingMap.getlumpSumAddButton());
			doClick(ContractingMap.getContractFeeForServicePaymentWarningPopUpContinueButton());
			assertTextIsDisplayed(lumpSumPaymentAlloc);
			doClick(contractingMap.getContractModelRiskLimiterMessageBox());
			assertElementIsDisplayed(ModelLibraryMap.getReturnButton());
			doClick(ModelLibraryMap.getReturnButton());
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			assertElementIsDisplayed(ContractingMap.getSaveButton());
			doClick(ContractingMap.getSaveButton());
			doClickTreeItemWithCheckbox("Lump Sum Payment Allocation Rules");
			waitForAjaxExtJs();
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(@id,'lumpsumpaymentallocationrulesmaingrid')]//following::div[text()='"+lumpSumPaymentAlloc+"']")));
			doClick(contractingMap.getTermDeleteButton());
			waitUntilElementIsClickable(contractingMap.getContractModelDeleteButtonInPopUp());
			doClick(ContractingMap.getDeleteButtonMesaageBox());
			driverDelay(200);
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			doClick(ContractingMap.getSaveButton());
			doClosePageOnLowerBar("ADS-1320 Contract...");
			ExtentReport.logPass("PASS", "test01NewLumpSumPaymentAllocation");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01NewLumpSumPaymentAllocation", driver, e);
			fail(e.getMessage());
		} 
		finally {
			

			doClosePageOnLowerBar("Model Library");
		}
	
	}
	
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
