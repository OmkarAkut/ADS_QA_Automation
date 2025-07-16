package webdriver.scripts.contracting;

import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FilterbuttonModels extends GoHelper {

	private static ContractingMap modelMap;
	private static ModelLibraryMap modellibMap;
	static String contractModel = "Test";
	static String costModel="0-MarinaCostModel";
	static String updateFilterModel = "Testing";
	String[] filterContractModel = { "Name", "Is", "Equal To", contractModel };
	String[] filterCostModel = { "Name", "Is", "Equal To", costModel };

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
			modellibMap=BuildMap.getInstance(driver, ModelLibraryMap.class);
			Login.loginUser("AutomationTesterAdmin");
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
	public void test01ValidateFilterForContractModel() throws Throwable {
		try {
			ValidateFilterPopUp(filterContractModel,contractModel);
			ExtentReport.logPass("PASS", "test01ValidateFilterForContractModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateFilterForContractModel", driver, e);
			fail(e.getMessage());

		}
	}
	
	@Test
	public void test02ValidateFilterForCostingModel() throws Throwable {
		try {
			goToPage("Costing Models");
			waitForDisplayedSpinnerToEnd();
			ValidateFilterPopUp(filterCostModel,costModel);
			ExtentReport.logPass("PASS", "test02ValidateFilterForCostingModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateFilterForCostingModel", driver, e);
			fail(e.getMessage());

		}
	}
	
	public void ValidateFilterPopUp(String[] filterModel,String model) throws Throwable {
		try {
			modellibMap.getModelLibraryButtonSearch().click();
			ContractingMap.getContractModelButtonFilter().click();
			waitForAjaxExtJs();
			contractModelsHelper.doFilterCreateOnly(filterModel);
			assertElementTextWithXpath("//div[text()='Name Is Equal To " + model + "']",
					"Name Is Equal To " + model + "", printout);
			action.moveToElement(modelMap.getContractModelEditFilterButton()).click().pause(10).perform();
			driver.findElement(By.name("valuefield")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			driver.findElement(By.name("valuefield")).sendKeys(updateFilterModel);
			doClick(modelMap.getContractModelUpdateFilterButton());
			assertElementTextWithXpath("//div[text()='Name Is Equal To " + updateFilterModel + "']",
					"Name Is Equal To " + updateFilterModel + "", printout);
			action.moveToElement(modelMap.getContractModelRemoveFilterButton()).click().pause(10).perform();
			contractModelsHelper.doFilterCreate(filterModel);
			ContractModelsHelper.getContractElementList(model);
			doClosePageOnLowerBar("Model Library");
		} catch (Exception | AssertionError e) {
			

		}
	}
	
		@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
