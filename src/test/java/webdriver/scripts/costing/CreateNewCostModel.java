package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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

	/**
	 * Automates test ticket ADS-6632, ADS-6633 ,ADS-6641,ADS-6253[missing steps
	 * from step7]
	 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CreateNewCostModel", "webdriver.scripts.costing", "CreateNewCostModel");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Costing Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

//ADS-6632 , include steps for ADS-6253 from step7]
	@Test
	public void test01CreateNewCostModel_ADS_6632() throws Throwable {
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

	// ADS-6632
	@Test
	public void test02AssertNewCostModel_ADS_6632() throws Throwable {
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

	public void OpenTreeItemThenValidate(String itemName) throws Throwable {
		Actions act = new Actions(driver);
		switch (itemName) {
		case "Allocation Exceptions":
			doClickTreeItem("Allocation Exceptions");
			doClick(CostingMap.getAllocExcepNewBtn());
			waitForElementToBeVisible(CostingMap.getOverheadCancelClose());
			doClick(CostingMap.getOverheadCancelClose());
			break;

		case "General Information - Cost":
			doClickTreeItem("General Information - Cost");
			driverDelay();
			doClick(CostingMap.getOverheadCancelClose());
			break;

		case "RVU Maintenance":
			doClickTreeItem("RVU Maintenance");
			assertTextIsDisplayed("Entity");
			break;

		case "RVU Calculation Scenarios":
			doClickTreeItem("Assign Unit Costs");
			doClickTreeItem("RVU Calculation Scenarios");
			doClick("(//h1[text()='RVU Calculation Scenarios']//following::span[text()='New'])[1]");
			doClick(CostingMap.getOverheadCancelClose());
			break;
		case "Cost Model Calculation Scenarios":
			doClickTreeItem("Cost Model Calculation Scenarios");
			doClick("(//h1[text()='Cost Model Calculation Scenarios']//following::span[text()='New'])[1]");
			doClick(CostingMap.getOverheadCancelClose());
			break;
		case "Cost Model Scenario Results":
			doClickTreeItem("Cost Model Scenario Results");
			assertElementIsDisplayedWithXpath("//div[text()='There is no data available to display.']");
			break;
		case "Encounter Cost Calculation Scenarios":
			doClick("(//span[@class='x-tree-node-text '][contains(text(),'Encounter Cost')])[1]");
			act.moveToElement(CostingMap.getfirstOverheadGridElement()).doubleClick().build().perform();
			driverDelay(1200);
			try {
				if (CostingMap.getReadOnlyBtn().isDisplayed()) {
					doClick(CostingMap.getReadOnlyBtn());
					assertElementIsDisplayed(CostingMap.getOverheadCancelClose());
					doClick(CostingMap.getOverheadCancelClose());
					driverDelay();
				}
			} catch (Exception e2) {

				assertElementIsDisplayed(CostingMap.getOverheadCancelClose());
				doClick(CostingMap.getOverheadCancelClose());
				driverDelay();
			} finally {
				try {
					doClick("//span[contains(@id,'messagebox')]//following::span[text()='Cancel & Close']");
				} catch (Exception e) {

				}
			}
			break;
		case "General Information - Overhead":
			doClick("//tr[contains(@class,'x-grid-tree-node-leaf')]//span[text()='" + itemName + "']");
			doClick(CostingMap.getOverheadCancelClose());
			doClickTreeItem("Allocate Overhead");
			driverDelay();
			break;

		case "Allocation Statistic Assignments":
			doClick("//tr[contains(@class,'x-grid-tree-node-leaf')]//span[text()='" + itemName + "']");
			doClick(CostingMap.getOverheadCancelClose());
			doClickTreeItem("Allocate Overhead");
			driverDelay();
			break;
		case "Overhead Model Calculation Scenarios":
			doClick("//tr[contains(@class,'x-grid-tree-node-leaf')]//span[text()='" + itemName + "']");
			doClick(CostingMap.getOverheadModelCalcScen());
			doClick(CostingMap.getOverheadCancelClose());
			break;
		case "Overhead Model Scenario Results":
			doClickTreeItem("Overhead Model Scenario Results");
			act.moveToElement(driver.findElement(By.xpath(
					"((//h1[text()='Overhead Model Scenario Results']//following::div[@class='x-grid-item-container'])//tr//td[2]//div)[1]")))
					.doubleClick().build().perform();
			driverDelay(1000);
			doClick(CostingMap.getOverheadModelScenResultsCloseBtn());
			break;

		case "Product Types":
			doClickTreeItem("Product Types");
			act.moveToElement(CostingMap.getfirstOverheadGridElement()).doubleClick().build().perform();
			driverDelay(1000);
			doClick(CostingMap.getOverheadModelDynamicCloseBtn());
			break;

		case "Overhead Account Variability Masters":

			doClickTreeItem("Overhead Account Variability Masters");
			act.moveToElement(driver.findElement(By.xpath(
					"((//h1[text()='Overhead Account Variability Masters']//following::div[@class='x-grid-item-container'])//tr//td[2]//div)[1]")))
					.doubleClick().build().perform();
			driverDelay(1000);
			try {
				if (CostingMap.getReadOnlyBtn().isDisplayed()) {
					doClick(CostingMap.getReadOnlyBtn());
					assertElementIsDisplayed(CostingMap.getOverheadCancelClose());
					doClick(CostingMap.getOverheadCancelClose());
					driverDelay();
				}
			} catch (Exception e2) {

				assertElementIsDisplayed(CostingMap.getOverheadCancelClose());
				doClick(CostingMap.getOverheadCancelClose());
				driverDelay();
			}

			break;

		default:
			doClick("//tr[contains(@class,'x-grid-tree-node-leaf')]//span[text()='" + itemName + "']");

			waitForElementsToBeVisible(CostingMap.getOverheadGridElement());
			act.moveToElement(CostingMap.getfirstOverheadGridElement()).doubleClick().build().perform();
			driverDelay(1200);
			try {
				if (CostingMap.getReadOnlyBtn().isDisplayed()) {
					doClick(CostingMap.getReadOnlyBtn());
					assertElementIsDisplayed(CostingMap.getOverheadCancelClose());
					doClick(CostingMap.getOverheadCancelClose());
					driverDelay();
				}
			} catch (Exception e2) {

				assertElementIsDisplayed(CostingMap.getOverheadCancelClose());
				doClick(CostingMap.getOverheadCancelClose());
				driverDelay();
			} finally {
				try {
					doClick("//span[contains(@id,'messagebox')]//following::span[text()='Cancel & Close']");
				} catch (Exception e) {

				}
			}
			break;
		}

	}

	// ADS-6253
	@Test
	public void test03CheckCostingElements_ADS_6253() throws Throwable {
		try {
			tableDoubleClickCellFirstColumn(costModel);
			doClickTreeItem("Prepare Costing Elements");
			driverDelay();
			OpenTreeItemThenValidate("Encounters");
			OpenTreeItemThenValidate("Entities");
			OpenTreeItemThenValidate("Department Masters");
			OpenTreeItemThenValidate("Department Hierarchies");
			OpenTreeItemThenValidate("Department Groups");
			OpenTreeItemThenValidate("GL Account Types");
			OpenTreeItemThenValidate("GL Account Masters");
			OpenTreeItemThenValidate("GL Account Hierarchies");
			OpenTreeItemThenValidate("Product Types");
			OpenTreeItemThenValidate("Charge Masters");
			OpenTreeItemThenValidate("Chargeable Activity Groups");
			OpenTreeItemThenValidate("Price Lists");
			OpenTreeItemThenValidate("Price List Calculation Scenarios");
			doClick("//span[text()='Statistics']");
			driverDelay();
			OpenTreeItemThenValidate("Activity Statistic Masters");
			OpenTreeItemThenValidate("Ad Hoc Statistic Masters");
			OpenTreeItemThenValidate("GL Statistic Masters");
			OpenTreeItemThenValidate("Charge Masters");
			OpenTreeItemThenValidate("Supply Code Masters");
			OpenTreeItemThenValidate("Vendor Masters");
			doClickTreeItem("Prepare Data");
			driverDelay();
			doClickTreeItem("Activity Volume Data Scenarios");
			driverDelay();
			OpenTreeItemThenValidate("Activity Volume Data Scenarios");
			OpenTreeItemThenValidate("Activity Volume Data Calculation Scenarios");
////			OpenTreeItemThenValidate("GL Data Scenarios"); //doubleclick to open for edit is not working ADS-12368
			doClickTreeItem("Statistic Data Scenarios");
			driverDelay();
			OpenTreeItemThenValidate("Statistic Data Scenarios");
			OpenTreeItemThenValidate("Statistic Data Calculation Scenarios");
			doClickTreeItem("Perform GL Adjustments and Reclassifications");
			driverDelay();
			OpenTreeItemThenValidate("GL Adjustment Masters");
			OpenTreeItemThenValidate("GL Reclassification Masters");
			OpenTreeItemThenValidate("GL Adjustment and Reclassification Calculation Scenarios");
			doClickTreeItem("Assign Unit Costs");
			driverDelay();
			OpenTreeItemThenValidate("Cost Component Masters");
			OpenTreeItemThenValidate("Cost Component Variability Masters");
			OpenTreeItemThenValidate("Cost Method Masters");
			OpenTreeItemThenValidate("General Information - Cost");
			OpenTreeItemThenValidate("RVU Calculation Scenarios");
			OpenTreeItemThenValidate("RVU Maintenance");
			OpenTreeItemThenValidate("Cost Model Calculation Scenarios");
			OpenTreeItemThenValidate("Cost Model Scenario Results");
			doClickTreeItem("Assign Costs to Encounters");
			driverDelay();
			OpenTreeItemThenValidate("Cost Component Group Masters");
			OpenTreeItemThenValidate("Assigned Cost Destinations");
			OpenTreeItemThenValidate("Encounter Cost Calculation Scenarios");
			ExtentReport.logPass("PASS", "test02AssertNewCostModel_6253");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AssertNewCostModel_6253", driver, e);
			fail(e.getMessage());
		} finally {
			try {

				doClick("//span[text()='Model Library']");
			} catch (Exception e) {
				doClick("//span[text()='Model Library']");
			}

		}
	}

	// ADS-6633
	@Test
	public void test04DeleteNewelyCreatedCostModel_ADS_6633() throws Throwable {
		try {
			driverDelay();
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
			waitForElementToBeVisible(
					driver.findElement(By.xpath("//*[text()='There is no data available to display.']")));
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test03DeleteNewelyCreatedCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeleteNewelyCreatedCostModel", driver, e);
			fail(e.getMessage());
		}
	}

	// ADS-6633
	@Test
	public void test05CancelButtonInCostModelPopUp_ADS_6633() throws Throwable {
		try {
			doClick(modelMap.getNewContractModelButton());
			waitUntilElementIsVisible(CostingMap.getNewCostModelPopUp());
			doClick(costing.getCancelCostModel());
			ExtentReport.logPass("PASS", "test04CancelButtonInCostModelPopUp");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04CancelButtonInCostModelPopUp", driver, e);
			fail(e.getMessage());
		}
	}

	// ADS-6641
	@Test
	public void test06CreateOverheadCostModel_ADS_6641() throws Throwable {
		try {
			doClick(modelMap.getNewContractModelButton());
			waitUntilElementIsVisible(CostingMap.getNewCostModelPopUp());
			doDropdownSelectUsingOptionText(costing.getModelTypedropdown(), "Overhead");
			doClick("//div[contains(@id,'dynamicwindow')]//input[@name='name']");
			driver.findElement(By.xpath("//div[contains(@id,'dynamicwindow')]//input[@name='name']"))
					.sendKeys(overheadModel);
			doClick(costing.getSaveCostModel());
			driverDelay(200);
			ExtentReport.logPass("PASS", "test05CreateOverheadCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05CreateOverheadCostModel", driver, e);
			fail(e.getMessage());
		}
	}

	// ADS-6254
	@Test
	public void test07AssertCreatedOverheadCostModel_ADS_6254() throws Throwable {
		try {
			goToPage("Cost Models");
			doSearchForContractModel(overheadModel);
			driverDelay(2000);
			assertTextIsDisplayed(overheadModel);
			tableDoubleClickCellFirstColumn(overheadModel);
			doClickTreeItem("Allocate Overhead");
			driverDelay();
			OpenTreeItemThenValidate("Overhead Account Variability Masters");
			OpenTreeItemThenValidate("General Information - Overhead");
			OpenTreeItemThenValidate("Allocation Statistic Assignments");
			OpenTreeItemThenValidate("Allocation Exceptions");
			OpenTreeItemThenValidate("Overhead Model Calculation Scenarios");
			OpenTreeItemThenValidate("Overhead Model Scenario Results");
			doClosePageOnLowerBar("OverheadModel...");
			ExtentReport.logPass("PASS", "test06AssertCreatedOverheadCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06AssertCreatedOverheadCostModel", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test08DeleteNewelyCreatedOverheadModel() throws Throwable {
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
			waitForElementToBeVisible(
					driver.findElement(By.xpath("//*[text()='There is no data available to display.']")));
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test07DeleteNewelyCreatedOverheadModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07DeleteNewelyCreatedOverheadModel", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("Costing Models");

		ExtentReport.report.flush();

	}
}
