package webdriver.scripts.contracting;

import static org.junit.Assert.assertTrue;
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
import webdriver.maps.DialogsMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClearFilterbuttonModels extends GoHelper {

	private static ContractingMap modelMap;
	private static ModelLibraryMap modelLibMap;
	static String Model = "Test";
	static String[] filter = { "Name", "Is", "Equal To", Model };
	static int BeforeApplyFilterContractModelListCount;
	static int AfterApplyFilterContractModelListCount;
	Actions action = new Actions(driver);
	private DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);
	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();


	/** Regression: Automated test script for ADS-6432,ADS-6438 ,ADS-6647,ADS-6648,ADS-6650*/

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ClearFilterbuttonModels",
				"webdriver.scripts.contracting",
				"ClearFilterbuttonModels");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			modelLibMap=BuildMap.getInstance(driver, ModelLibraryMap.class);
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
	
	
//ADS-6432
	@Test
	public void test01ContractModelClearFilter_ADS_6432_ADS_6647() throws Throwable {
		try {
			ClearFilter(filter,Model);
			ExtentReport.logPass("PASS", "test01ContractModelClearFilter");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ContractModelClearFilter", driver, e);
			fail(e.getMessage());

		}
	}
	//ADS-6438
	@Test
	public void test02ContractModelSearchText_ADS_6438() throws Throwable {
		try {
			ModelSearchText(Model);
			ExtentReport.logPass("PASS", "test02ContractModelSearchText");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ContractModelSearchText", driver, e);
			fail(e.getMessage());

		}
	}
	@Test
	public void test03CostModelClearFilter_ADS_6648() throws Throwable {
		try {
			goToPage("Costing Models");
			waitForDisplayedSpinnerToEnd();
			ClearFilter(filter,Model);
			ExtentReport.logPass("PASS", "test03CostModelClearFilter");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03CostModelClearFilter", driver, e);
			fail(e.getMessage());

		}
	}
	//ADS-6650
	@Test
	public void test04CostModelSearchText_ADS_6650() throws Throwable {
		try {
			ModelSearchText(Model);
			ExtentReport.logPass("PASS", "test04CostModelSearchText");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04CostModelSearchText", driver, e);
			fail(e.getMessage());

		}
	}

	public void addFilter(String model) throws Throwable {
		contractModelsHelper.doFilterSetFilterParameters("Name","Is","Equal To",model);
		doClick(dialog.getFilterDialogButtonAdd());
	    waitForAjaxExtJs();
	}
	public void ClearFilter(String[] filter,String model) throws Throwable {
		try {
			waitForAjaxExtJs();
			BeforeApplyFilterContractModelListCount = ContractingMap.getCostingModelElementList().size();
			modelLibMap.getModelLibraryButtonSearch().click();
			waitForDisplayedSpinnerToEnd();
			ContractingMap.getContractModelButtonFilter().click();
			waitForAjaxExtJs();
			// Apply Filter
			addFilter(model);
			assertElementTextWithXpath("//div[text()='Name Is Equal To " + model + "']",
					"Name Is Equal To " + model + "", printout);
			doClick(modelMap.getContractModelRemoveFilterButton());
			addFilter(model);
			doClick(ContractingMap.getContractModelApplyFilterButton());
			waitForAjaxExtJs();
			waitForDisplayedSpinnerToEnd();
			ContractModelsHelper.getContractElementList(model);
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

	public void ModelSearchText(String model) throws Throwable {
		try {
			doSearchForContractModel(model);
			waitForDisplayedSpinnerToEnd();
			driverDelay();
			ContractModelsHelper.getContractElementList(model);
			driver.findElement(By.name("searchText")).clear();
			driver.findElement(By.name("searchText")).sendKeys(Keys.ENTER);
			if (BeforeApplyFilterContractModelListCount == AfterApplyFilterContractModelListCount) {
				assertTrue("Model List Matches", printout);
			}
			ExtentReport.logPass("PASS", "test01ClearFilter");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ClearFilter", driver, e);
			fail(e.getMessage());

		}
	}
	@Test
	public void test04ValidateFilterButton_ADS_6431() throws Throwable {
		ContractingMap.getContractModelButtonFilter().click();
		waitForAjaxExtJs();
		// Apply Filter
		contractModelsHelper.doFilterSetFilterParameters("Name","Is","Equal To",Model);
		doClick(dialog.getFilterDialogButtonAdd());
	    waitForAjaxExtJs();
	    doClick("//span[text()='Edit']");
	    ContractModelsHelper.keyInValues(driver.findElement(By.name("valuefield")), "Testing");
	    doClick("//span[text()='Update']");
//	    doFilterSetFilterParameters("ASC Scheme Name", "Is", "Contains", "Testing");
	    doClick(ContractingMap.getASCFilterRemoveButton());
	    Thread.sleep(1000);
	    doFilterCreate(filter);
//	    assertElementIsDisplayedWithXpath("//div[text()='"+ascScheme+"']");
	    assertListOfElementsContainsExpectedString(driver.findElements(By.xpath("(//div[contains(@id,'adynamicgrid')]//tr//td[1]/div)")), Model);
	}
	
	@AfterClass
	public static void endtest() throws Exception {
//		doClosePageOnLowerBar("Model Library");
		ExtentReport.report.flush();

	}
}
