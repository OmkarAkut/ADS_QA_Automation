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
import webdriver.maps.mapbuilder.BuildMap;

public class CreateANewContractModel extends CalculationHelper {

	private static ContractingMap modelMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String contractModelName = "Contract Model" + currentDateTime;
	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();

	String[] columns = { "100  Pacific Hospital", "151  Copy of Marina Medical Center" };
	String[] columnsToSelect = { "100  Pacific Hospital", "151  Copy of Marina Medical Center", "150  Marina Medical Center" };
	String[] columnsToRemove = { "100  Pacific Hospital", "151  Copy of Marina Medical Center" };
	String addProvider = "150  Marina Medical Center";

	/** Regression: Automated test script for ADS-6413 ,ADS-6435 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CreateANewContractModel", "webdriver.scripts.contracting",
				"CreateANewContractModel");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			doClick(CostingMap.getContractingName);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	/**Test - UI Validation [Contracting] “Create a New Contract Model”.ADS-6413 **/
	@Test
	public void test01CreateNewContractModel() throws Throwable {
		try {
			doClick(ContractingMap.getNewContractModelButton());
			waitForElementToBeVisible(ContractingMap.getNewContractModelPopUp());
			assertElementIsDisplayed(ContractingMap.getNewContractModelPopUp());// assert contract model pop up
			doClick(ContractingMap.getContractModelNameInput());
			ContractingMap.getContractModelNameInput().sendKeys(contractModelName);
			doClick(ContractingMap.getContractModelAddProviderBtn());
			waitForElementToBeVisible(ContractingMap.getContractModelAddProviderPopup());
			contractModelsHelper.selectMultipleColumnsToDisplay(columnsToSelect);
			contractModelsHelper.removeMultipleColumnsToDisplay(columnsToRemove);
			doClick(modelMap.getApplySelections());
			waitForElementToBeVisible(ContractingMap.getNewContractModelPopUp());
			// Validate model name and providers
			assertElementTextContains(ContractingMap.getProviderText(), addProvider, printout);
			doClick(modelMap.getSaveContractModel());
			doClick(CostingMap.getContractingName);// Takes time to display the new contract model so just click on
													// contracting name to refresh the grid
			waitForDisplayedSpinnerToEnd();
			driver.findElement(By.name("searchText")).click();
			driverDelay(2000);
			// Takes time to display the new contract model so just click on contracting name to refresh the grid
			doClick(CostingMap.getContractingName);
			doSearchForContractModel(contractModelName);
			driverDelay(2000);
			assertTextIsDisplayed(contractModelName);
			ExtentReport.logPass("PASS", "test01CreateNewContractModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CreateNewContractModel", driver, e);
			fail(e.getMessage());
		}
	}
/*TestUIValidationContractingValidateContractingModelDeletebutton : ADS-6435*/
	@Test
	public void test02DeleteContractModel() throws Throwable {
		
		try {
			doClick(modelMap.getContractModelDeleteButton());
			waitForElementToBeVisible(modelMap.getContractModelDeletePopUp());
			assertElementIsDisplayed(modelMap.getContractModelDeletePopUp());
			doClick(modelMap.getContractModelCancelButtonInPopUp());
			doClick(modelMap.getContractModelDeleteButton());
			waitForElementToBeVisible(modelMap.getContractModelDeletePopUp());
			assertElementIsDisplayed(modelMap.getContractModelDeleteButtonInPopUp());
			assertElementIsDisplayed(modelMap.getContractModelCancelButtonInPopUp());
			doClick(modelMap.getContractModelDeleteButtonInPopUp());
			assertTextIsDisplayed("There is no data available to display.");
			doClosePageOnLowerBar("Model Library");
			ExtentReport.logPass("PASS", "test02DeleteContractModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02DeleteContractModel", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
