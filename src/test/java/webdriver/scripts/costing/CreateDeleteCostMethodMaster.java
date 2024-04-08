package webdriver.scripts.costing;

import static org.junit.Assert.assertTrue;
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
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateDeleteCostMethodMaster extends GoHelper{
	static String costModel = "Test Cost Model 100";
	static CostingMap costing;
	static ContractingMap modelMap;
	static SystemMaintenanceMap systemMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateTimeCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateTimeCode.replaceAll("\\W", "");
	static String costMethodMaster = "Cost Method" + currentDateTime;
	static String deptMaster="150 old master 1501";
	static String[] filter = { "Code", "Is", "Equal", code };
	/** Automates test ticket ADS-6671, ADS-6670, ADS-6669 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CreateDeleteCostMethodMaster", "webdriver.scripts.costing", "CreateDeleteCostMethodMaster");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			systemMap=BuildMap.getInstance(driver, SystemMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			ContractModelsHelper.saveCustomSettings("Use Custom", "Costing Models");
//			goToPage("Costing Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01OpenAllMasters_ADS_6669() throws Throwable {
		try {
			doSearchForModel(costModel);
			tableDoubleClickCellFirstColumn(costModel);
			driverDelay(300);
			assertElementIsDisplayedWithXpath("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='CM Test']");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='FISCAL YEAR SETUP']");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='COST PROCESS']");
			doClick("(//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='CM Test'])");
			waitForPresenceOfElement("(//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='All Masters'])");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='All Masters']");
//			assertTextIsDisplayed("Cost Scnenarios");
			doClick("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='All Masters']");
			waitForPresenceOfElement("(//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='Charge Masters'])[1]");
			assertElementIsDisplayed("Activity Statistic Masters");
			assertElementIsDisplayed("Ad Hoc Statistic Masters");
			assertElementIsDisplayed("Charge Masters");
			assertElementIsDisplayed("Cost Component Group Masters");
			assertElementIsDisplayed("Cost Component Masters");
			assertElementIsDisplayed("Cost Component Variability Masters");
			assertElementIsDisplayed("Charge Masters");
			assertElementIsDisplayed("Cost Method Masters");
			assertElementIsDisplayed("Department Groups");
			assertElementIsDisplayed("Department Masters");
			assertElementIsDisplayed("Department Hierarchies");
			assertElementIsDisplayed("GL Account Masters");
			assertElementIsDisplayed("GL Adjustment Masters");
			assertElementIsDisplayed("Vendor Masters");
			assertElementIsDisplayed("GL Statistic Masters");
			assertElementIsDisplayed("Supply Code Masters");
			assertElementIsDisplayed("GL Statistic Masters");
			assertElementIsDisplayed("GL Adjustment and Reclassification Calculation Scenarios");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='Groupings']");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='Miscellaneous']");
			ExtentReport.logPass("PASS", "test01OpenAllMasters");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenAllMasters", driver, e);
			fail(e.getMessage());
		}

	}
	//ADS-6670
	@Test
	public void test02CreateNewMethodMaster_ADS_6670() throws Throwable {
		try {
//			doClick("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='All Masters']");
//			waitForPresenceOfElement("(//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='Charge Masters'])[1]");
			doClick("(//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='Charge Masters'])[1]");
			waitForElementToBeVisible(CostingMap.getCostModelMethodMasterNew());
			doClick(CostingMap.getCostModelMethodMasterNew());
			ContractModelsHelper.keyInValues(ContractingMap.getMedicareCode(), code);
			ContractModelsHelper.keyInValues(ContractingMap.getInputName(), costMethodMaster);
			doDropdownSelectUsingOptionText(CostingMap.getCostModelMethodMasterDeptMasterdropdown(), CostingMap.getCostModelMethodMasterDeptMasterOptions(), deptMaster);
			driverDelay(200);
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			doClick(CostingMap.getCostModelMethodMasterFilter());
			doFilterCreate(filter);
			assertTextIsDisplayed(costMethodMaster);
			ExtentReport.logPass("PASS", "test02CreateNewMethodMaster");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test02CreateNewMethodMaster", driver, e);
			fail(e.getMessage());
		} 
			}
	//ADS-6669
		@Test
		public void test03EditNewMethodMaster_ADS_6669() throws Throwable {
			try {
				doClick("(//div[contains(@id,'masterlist')]//h1[text()='Charge Masters']//following::span[text()='Edit'])[1]");
				String chargeNameBeforeEdit=driver.findElement(By.xpath("(//h1[text()='Charge Masters']//following::table[@class='x-grid-item x-grid-item-selected']//td/div)[3]")).getText();
				ContractModelsHelper.keyInValues(ContractingMap.getInputName(), costMethodMaster+" "+"Test");
				doClick(ContractingMap.getContractFeeForServicePaymentSave());
				waitForDisplayedSpinnerToEnd();
				String chargeNameAfterEdit=driver.findElement(By.xpath("(//h1[text()='Charge Masters']//following::table[@class='x-grid-item x-grid-item-selected']//td/div)[3]")).getText();
				if(!(chargeNameBeforeEdit.equals(chargeNameAfterEdit))){
					assertTrue(printout);
				}
				else {
					fail();
				}
				
				
				ExtentReport.logPass("PASS", "test03EditNewMethodMaster_ADS_6669");
			} catch (Exception|AssertionError e) {
				ExtentReport.logFail("FAIL", "test03EditNewMethodMaster_ADS_6669", driver, e);
				fail(e.getMessage());
			} 
		}
	//ADS-6671
	@Test
	public void test04DeleteNewMethodMaster_ADS_6671() throws Throwable {
		try {
			doClick(CostingMap.getCostModelMethodMasterDelete());
			waitForElementPresence("//div[contains(@id,'warningwindow')]/div//span[text()='Delete']");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'warningwindow')]/div//span[text()='Delete']");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'warningwindow')]/div//span[text()='Cancel']");
			ContractingMap.getWarningPopUpDeleteButton().click();
			waitForDisplayedSpinnerToEnd();
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test03DeleteNewTimePeriod");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeleteNewTimePeriod", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar(costModel);
			doClosePageOnLowerBar("Costing Models");
			
		}
	}
	@AfterClass
	public static void endtest() {
		ContractModelsHelper.revertCustomSettings();
		ExtentReport.report.flush();
	}
}

