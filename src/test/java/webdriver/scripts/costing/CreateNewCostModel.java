package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateNewCostModel extends GoHelper {
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String costModel = "Model " + currentDateTime;
	static String overheadModel = "OverheadModel " + currentDateTime;
	static CostingMap costing;
	static ContractingMap modelMap;

/** Automates test ticket ADS-6632, ADS-6633 ,ADS-6641,ADS-6253[missing steps from step7] */
	
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("CreateNewCostModel", "webdriver.scripts.costing", "CreateNewCostModel");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			modelMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Costing Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6632 , include steps for ADS-6253 from step7]
	@Test
	public void test01CreateNewCostModel_6632() throws Throwable {
		try {
		doClick(modelMap.getNewContractModelButton());
		waitUntilElementIsVisible(CostingMap.getNewCostModelPopUp());
		doClick(ContractingMap.getInputName());
		ContractingMap.getInputName().sendKeys(costModel);
		doClick(costing.getSaveCostModel());
		driverDelay(200);
		ExtentReport.logPass("PASS", "test01CreateNewCostModel");
	} catch (Exception | AssertionError e) {
		ExtentReport.logFail("FAIL", "test01CreateNewCostModel", driver, e);
		fail(e.getMessage());
	}
	}
	//ADS-6632
	@Test
	public void test02AssertNewCostModel_6632() throws Throwable {
		try {
			goToPage("Cost Models");
			doSearchForContractModel(costModel);
			driverDelay(2000);
			assertTextIsDisplayed(costModel);
			ExtentReport.logPass("PASS", "test02AssertNewelyCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AssertNewelyCostModel", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6633
	@Test
	public void test03DeleteNewelyCreatedCostModel_6633() throws Throwable {
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
			driverDelay(200);
			waitForElementToBeVisible(driver.findElement(By.xpath("//*[text()='There is no data available to display.']")));
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test03DeleteNewelyCreatedCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeleteNewelyCreatedCostModel", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6633
	@Test
	public void test04CancelButtonInCostModelPopUp_6633() throws Throwable {
		try {
			doClick(modelMap.getNewContractModelButton());
			waitUntilElementIsVisible(CostingMap.getNewCostModelPopUp());
			doClick(costing.getCancelCostModel());
			ExtentReport.logPass("PASS", "test04CancelButtonInCostModelPopUp");

			}
		catch(Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04CancelButtonInCostModelPopUp", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6641,include steps for ADS-6253 from step7
	@Test
	public void test05CreateOverheadCostModel_6641() throws Throwable {
		try {
			doClick(modelMap.getNewContractModelButton());
			waitUntilElementIsVisible(CostingMap.getNewCostModelPopUp());
			doDropdownSelectUsingOptionText(costing.getModelTypedropdown(), "Overhead");
			doClick(ContractingMap.getInputName());
			ContractingMap.getInputName().sendKeys(overheadModel);
			doClick(costing.getSaveCostModel());
			driverDelay(200);
			ExtentReport.logPass("PASS", "test05CreateOverheadCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05CreateOverheadCostModel", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6641
	@Test
	public void test06AssertCreatedOverheadCostModel_6641() throws Throwable {
		try {
			goToPage("Cost Models");
			doSearchForContractModel(overheadModel);
			driverDelay(2000);
			assertTextIsDisplayed(overheadModel);
			ExtentReport.logPass("PASS", "test06AssertCreatedOverheadCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06AssertCreatedOverheadCostModel", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test07DeleteNewelyCreatedOverheadModel() throws Throwable {
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
			driverDelay(200);
			waitForElementToBeVisible(driver.findElement(By.xpath("//*[text()='There is no data available to display.']")));
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test07DeleteNewelyCreatedOverheadModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07DeleteNewelyCreatedOverheadModel", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("Model Library");

		ExtentReport.report.flush();

	}
}


