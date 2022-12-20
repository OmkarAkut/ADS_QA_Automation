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
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class TestUIValidationContractingCreateANewContractModel extends CalculationHelper {

	private static CostingMap modelMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String contractModelName = "Contract Model" + currentDateTime;
	private static ContractingHelperMethods contractHelper = new ContractingHelperMethods();
	String[] columns = { "100  Pacific Hospital", "151  Copy of Marina Medical Center" };
	String[] columnsToSelect = { "100  Pacific Hospital", "150  Marina Medical Center",
			"151  Copy of Marina Medical Center" };
	String[] columnsToRemove = { "100  Pacific Hospital", "151  Copy of Marina Medical Center" };
	String addProvider = "150  Marina Medical Center";

	/** Regression: Automated test script for ADS-6413 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("TestUIValidationContractingCreateANewContractModel", "webdriver.scripts.contracting",
				"TestUIValidationContractingCreateANewContractModel");
		try {
			modelMap = BuildMap.getInstance(driver, CostingMap.class);

			System.out.println("Test Class: " + TestUIValidationContractingCreateANewContractModel.class.getSimpleName());
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForSpinnerToEnd();
			doClick(CostingMap.getContractingName);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01CreateNewContractModel() throws Throwable {
		try {
			modelMap = BuildMap.getInstance(driver, CostingMap.class);
			doClick(CostingMap.getNewContractModelButton());
			waitForElementToBeVisible(CostingMap.getNewContractModelPopUp());
			assertElementIsDisplayed(CostingMap.getNewContractModelPopUp());// assert contract model pop up
			doClick(CostingMap.getContractModelNameInput());
			CostingMap.getContractModelNameInput().sendKeys(contractModelName);
			doClick(CostingMap.getContractModelAddProviderBtn());
			waitForElementToBeVisible(CostingMap.getContractModelAddProviderPopup());
			contractHelper.selectMultipleColumnsToDisplay(columnsToSelect);
			contractHelper.removeMultipleColumnsToDisplay(columnsToRemove);
			doClick(modelMap.getApplySelections());
			waitForElementToBeVisible(CostingMap.getNewContractModelPopUp());
			// Validate model name and providers
			assertElementTextContains(CostingMap.getProviderText(), addProvider, printout);
			doClick(modelMap.getSaveContractModel());
			doClick(CostingMap.getContractingName);// Takes time to display the new contract model so just click on
													// contracting name to refresh the grid
			waitForDisplayedSpinnerToEnd();
			driver.findElement(By.xpath("//input[@name='searchText']")).click();
			driverDelay();
			// Takes time to display the new contract model so just click on contracting name to refresh the grid
			doClick(CostingMap.getContractingName);
			doSearchForContractModel(contractModelName);
			driverDelay();
			assertTextIsDisplayed(contractModelName);
			ExtentReport.logPass("PASS", "test01CreateNewContractModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CreateNewContractModel", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
