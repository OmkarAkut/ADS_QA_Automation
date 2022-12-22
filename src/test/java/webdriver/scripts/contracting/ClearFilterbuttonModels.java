package webdriver.scripts.contracting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class ClearFilterbuttonModels extends GoHelper {

	private static ContractingMap modelMap;
	static String contractModel = "Test";
	static String[] filter = { "Name", "Is", "Equal To", contractModel };
	static int BeforeApplyFilterContractModelListCount;
	static int AfterApplyFilterContractModelListCount;
	Actions action = new Actions(driver);
	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();

	/** Regression: Automated test script for ADS-6432,ADS-6438 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ClearFilterbuttonModels",
				"webdriver.scripts.contracting",
				"ClearFilterbuttonModels");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);

			System.out.println("Test Class: "
					+ ClearFilterbuttonModels.class.getSimpleName());
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01ContractModelClearFilter() throws Throwable {
		try {
			waitForAjaxExtJs();
			BeforeApplyFilterContractModelListCount = ContractingMap.getCostingModelElementList().size();
			modelMap.getContractModelButtonFilter().click();
			waitForAjaxExtJs();
			// Apply Filter
			contractModelsHelper.doFilterCreate(filter);
			assertElementTextWithXpath("//div[text()='Name Is Equal To " + contractModel + "']",
					"Name Is Equal To " + contractModel + "", printout);
			doClick(modelMap.getContractModelApplyFilterButton());
			waitForDisplayedSpinnerToEnd();
			ContractModelsHelper.getContractElementList(contractModel);
			// Clear Filter
			doClick(modelMap.getContractModelClearFilter());
			waitForDisplayedSpinnerToEnd();
			assertElementIsDisabled(modelMap.getContractModelClearFilter(), printout);
			AfterApplyFilterContractModelListCount = ContractingMap.getCostingModelElementList().size();
			if (BeforeApplyFilterContractModelListCount == AfterApplyFilterContractModelListCount) {
				assertTrue("Contract Model List Matches", printout);
			}
			ExtentReport.logPass("PASS", "test01ClearFilter");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ClearFilter", driver, e);
			fail(e.getMessage());

		}

	}
	/**Test - UI Validation [Contracting] Validate Contracting Model � �Search� Textbox/button (Models); ADS-6438**/
	@Test
	public void test02ContractModelSearchText() throws Throwable {
		try {
			doSearchForContractModel(contractModel);
			ContractModelsHelper.getContractElementList(contractModel);
			driver.findElement(By.name("searchText")).clear();
			driver.findElement(By.name("searchText")).sendKeys(Keys.ENTER);
			if (BeforeApplyFilterContractModelListCount == AfterApplyFilterContractModelListCount) {
				assertTrue("Contract Model List Matches", printout);
			}
			doClosePageOnLowerBar("Model Library");

			ExtentReport.logPass("PASS", "test01ClearFilter");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ClearFilter", driver, e);
			fail(e.getMessage());

		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
