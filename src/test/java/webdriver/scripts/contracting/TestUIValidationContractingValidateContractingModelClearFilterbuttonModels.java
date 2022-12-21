package webdriver.scripts.contracting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class TestUIValidationContractingValidateContractingModelClearFilterbuttonModels extends GoHelper {

	private static ContractingMap modelMap;
	static String contractModel = "Test";
	static String[] filter = { "Name", "Is", "Equal To", contractModel };
	static int BeforeApplyFilterContractModelListCount;
	static int AfterApplyFilterContractModelListCount;
	Actions action = new Actions(driver);
	private static ContractingHelperMethods contractHelper = new ContractingHelperMethods();

	/** Regression: Automated test script for ADS-6432 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("TestUIValidationContractingValidateContractingModelClearFilterbuttonModels",
				"webdriver.scripts.contracting",
				"TestUIValidationContractingValidateContractingModelClearFilterbuttonModels");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);

			System.out.println("Test Class: "
					+ TestUIValidationContractingValidateContractingModelClearFilterbuttonModels.class.getSimpleName());
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForAjaxExtJs();
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
			Thread.sleep(2000);
			waitForAjaxExtJs();
			// Apply Filter
			contractHelper.doFilterCreate(filter);
			assertElementTextWithXpath("//div[text()='Name Is Equal To " + contractModel + "']",
					"Name Is Equal To " + contractModel + "", printout);
			doClick(modelMap.getContractModelApplyFilterButton());
			waitForDisplayedSpinnerToEnd();
			for (WebElement costingElement : ContractingMap.getCostingModelElementList()) {
				assertThatString(costingElement, contractModel, printout);
			}
			// Clear Filter
			doClick(modelMap.getContractModelClearFilter());
			waitForDisplayedSpinnerToEnd();
			assertElementIsDisabled(modelMap.getContractModelClearFilter(), printout);
			AfterApplyFilterContractModelListCount = ContractingMap.getCostingModelElementList().size();
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
