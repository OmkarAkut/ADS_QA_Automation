package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class FilterbuttonModels extends GoHelper {

	private static ContractingMap modelMap;
	static String contractModel = "Test";
	static String updateContractModel = "Testing";
	String[] filter = { "Name", "Is", "Equal To", contractModel };
	Actions action = new Actions(driver);
	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();

	/** Regression: Automated test script for ADS-6431 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("FilterbuttonModels",
				"webdriver.scripts.contracting",
				"FilterbuttonModels");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
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
	public void test01ValidateFilterForContractModels() throws Throwable {
		try {
			modelMap.getContractModelButtonFilter().click();
			waitForAjaxExtJs();
			contractModelsHelper.doFilterCreateOnly(filter);
			assertElementTextWithXpath("//div[text()='Name Is Equal To " + contractModel + "']",
					"Name Is Equal To " + contractModel + "", printout);
			action.moveToElement(modelMap.getContractModelEditFilterButton()).click().pause(10).perform();
			driver.findElement(By.name("valuefield")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			driver.findElement(By.name("valuefield")).sendKeys(updateContractModel);
			doClick(modelMap.getContractModelUpdateFilterButton());
			assertElementTextWithXpath("//div[text()='Name Is Equal To " + updateContractModel + "']",
					"Name Is Equal To " + updateContractModel + "", printout);
			action.moveToElement(modelMap.getContractModelRemoveFilterButton()).click().pause(10).perform();
			contractModelsHelper.doFilterCreate(filter);
			ContractModelsHelper.getContractElementList(contractModel);
			doClosePageOnLowerBar("Model Library");
			ExtentReport.logPass("PASS", "test01ValidateFilterbuttomForContractModels");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateFilterbuttomForContractModels", driver, e);
			fail(e.getMessage());

		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
