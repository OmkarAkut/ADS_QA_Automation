package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.AdsHelper;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateCalculationStatusPage extends CalculationHelper {
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String costModel = "Testing" + currentDateTime;
	static CostingMap costing;
	static ContractingMap modelMap;
	static String glDataDescription="* MHFY05 Reclass TB";
	static String actVol="TBMHFY05VOL";
	static String varMaster="ASESC2060 CC Var Master";
	static String OHMaster="TB OVHD Calc Scen Exp Vol for Rpt";
	static String priceList="150FY05 Marina Hosp Price List FY05";
	static String calcMonths="Apr 2004 to Mar 2005";
	static String[] calcfilter= {"Scenario Name","Is","Equal To",costModel};
	AdsHelper adsHelper = new AdsHelper();
	CalculationHelper calcHelper=new CalculationHelper();

	static String[] filterEntity= {"Entity Code","Is","Equal To","151 Copy of Marina Medical Center"};
/** Automates test ticket ADS-5771*/
	
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("ValidateCalculationStatusPage", "webdriver.scripts.costing", "ValidateCalculationStatusPage");
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
	//ADS-5771[add step 15-25]
	@Test
	public void test01GotoCostModelCalculationScenario_5771() throws Throwable {
		try {
			doSearchForModel("QA Cost Model");
			tableDoubleClickCellFirstColumn("QA Cost Model");
			driverDelay(500);
			doClickTreeItem("Assign Unit Costs");
			/*
			waitForMainPageTitle("CM Test");
			doClickTreeItem("CM Test");
			waitForMainPageTitle("Cost Scnenarios");
			doClickTreeItem("Cost Scnenarios");
			waitForMainPageTitle("Cost Model Calculation Scenarios");
			doClickTreeItem("Cost Model Calculation Scenarios");
			waitForDisplayedSpinnerToEnd();
			*/
			doClickTreeItem("Cost Model Calculation Scenarios");
			ExtentReport.logPass("PASS", "test01GotoCostModelCalculationScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01GotoCostModelCalculationScenario", driver, e);
			fail(e.getMessage());
		}

	}
	
	@Test
	public void test02SelectSourceData_5771() throws Throwable {
		try {
			doClick(CostingMap.getCostCalcNewButton());
			waitForElementToBeVisible(CostingMap.getCostScenarioName());
			ContractModelsHelper.keyInValues(CostingMap.getCostScenarioName(), costModel);
//			CostingMap.getCostScenarioName().sendKeys(costModel);
			doDropdownSelectUsingOptionText(
					CostingMap.getGldataDesc(),
					CostingMap.getCostModelGLDataScenarioOptions(),
					glDataDescription
					);
			doDropdownSelectUsingOptionText(
					CostingMap.getVolScenario(),
					CostingMap.getCostModelVolScenarioOptions(),
					actVol
					);
			doDropdownSelectUsingOptionText(
					CostingMap.getVariablilityMaster(),
					CostingMap.getCostModelVarMasterScenarioOptions(),
					varMaster
					);
			doDropdownSelectUsingOptionText(
					CostingMap.getOverheadDrpDwn(),
					CostingMap.getCostModelOHMasterScenarioOptions(),
					OHMaster
					);
			ExtentReport.logPass("PASS", "test02CreateCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02CreateCostModel", driver, e);
			fail(e.getMessage());
		}

		
	}
	@Test
	public void test03EntityAndDepartmentGroupSelection_5771() throws Throwable {
		try {
			ContractModelsHelper.scrollToView(CostingMap.getEntitiesSelect());
			doClick(CostingMap.getEntitiesSelect());
			doClick(CostingMap.getUnitCostQuickCalculationDepartmentButtonFilter());
			ContractModelsHelper.applyMultipleFilters("Entity Code","150");
			ContractModelsHelper.groupSelectApplyFilters("Department Code","2016");
//			ContractModelsHelper.groupSelectApplyFilters("Department Code","2110");
//			ContractModelsHelper.groupSelectApplyFilters("Department Code","2111");
//			ContractModelsHelper.groupSelectApplyFilters("Department Code","2115");
//			ContractModelsHelper.groupSelectApplyFilters("Department Code","2140");
//			ContractModelsHelper.groupSelectApplyFilters("Department Code","2159");
//			ContractModelsHelper.groupSelectApplyFilters("Department Code","2160");
//			ContractModelsHelper.groupSelectApplyFilters("Department Code","2165");
//			ContractModelsHelper.groupSelectApplyFilters("Department Code","3110");
//			ContractModelsHelper.groupSelectApplyFilters("Department Code","3120");
			ExtentReport.logPass("PASS", "test02CreateCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02CreateCostModel", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04SelectPriceListStartEndMonth_5771() throws Throwable {
		try {
			driverDelay();
			CostingMap.getCostModelPriceListDrpdown().click();
			doDropdownSelectUsingOptionText(
					CostingMap.getCostModelPriceListDrpdown(),
					CostingMap.getCostModelPriceListScenarioOptions(),
					priceList
					);
			doDropdownSelectUsingOptionText(
					CostingMap.getCostModelStartMonthDrpdown(),
					CostingMap.getCostModelStartMonthScenarioOptions(),
					"Apr 2004"
					);
			doDropdownSelectUsingOptionText(
					CostingMap.getCostModelEndMonthDrpdown(),
					CostingMap.getCostModelEndMonthScenarioOptions(),
					"Apr 2004"
					);
			doClick(CostingMap.getCostModelSharedLogCheckbox());
			ExtentReport.logPass("PASS", "test04SelectPriceListStartEndMonth");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04SelectPriceListStartEndMonth", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05SaveAndCalculate_5771() throws Throwable {
		try {
			doClick(ContractingMap.getSaveBenefitPlan());
			driverDelay(100);
			doClick(ContractingMap.getCalculateButton());
//			doClick(driver.findElement(By.xpath("//div[text()='"+costModel+"']")));
//			assertElementIsDisplayed(driver.findElement(By.xpath("(//span[text()='Cancel'])[2]")));
			assertElementIsDisplayed(driver.findElement(By.xpath("(//div[text()='"+costModel+"']//following::span[text()='Cancel'])")));
			calcHelper.doFilterCalculationPage(calcfilter);
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			doClick("(//div[text()='View Log']//following::span[text()='Cancel'])[1]");
			doClick("(//div[text()='"+costModel+"']//following::span[text()='Download'])[1]");
			waitForPresenceOfElementText("Download Log");
//			doClick(driver.findElement(By.xpath("(//input[@name='hostLocation']/../..)[2]")));
			//Shilpa: xpath update for 11.2 on 5.17.2024
			doactionClick(driver.findElement(By.xpath("(//input[@name='hostLocation']/../..)[2]")));
			doClick(modelMap.getContractModelCalcFileSharedLocOption());
			doClick(modelMap.getContractModelCalcFilename());
			driverDelay();
			Actions act=new Actions(driver);
			act.moveToElement(modelMap.getContractModelCalcFilename()).click().sendKeys("Testing"+currentDateTime).perform();
			act.moveToElement(modelMap.getContractModelCalcContinueBtn()).click().perform();
			waitForDisplayedSpinnerToEnd();
			driverDelay();
					driver.findElement(By.xpath("(//div[text()='"+costModel+"']//following::div[text()='Completed'])[1]/..")).click();
//			act.moveToElement(driver.findElement(By.xpath("(//div[text()='"+costModel+"']//following::span[text()='View'])[1]/.."))).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN).build().perform();
//			act.moveToElement(driver.findElement(By.xpath("(//div[text()='"+costModel+"']//following::span[text()='View'])[1]/.."))).sendKeys(Keys.ARROW_UP).sendKeys(Keys.RIGHT).sendKeys(Keys.ENTER).perform();
			act.moveToElement(driver.findElement(By.xpath("(//div[text()='"+costModel+"']//following::a[text()='View'])[1]/.."))).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN).build().perform();
			act.moveToElement(driver.findElement(By.xpath("(//div[text()='"+costModel+"']//following::a[text()='View'])[1]/.."))).sendKeys(Keys.ARROW_UP).sendKeys(Keys.RIGHT).sendKeys(Keys.ENTER).perform();
			//Keyboard access not working ADS-11287
			assertTextIsDisplayed("View Log");
			driverDelay();
			act.moveToElement(driver.findElement(By.xpath("(//div[text()='View Log']//following::span[text()='Cancel'])[1]/../../.."))).click().click().perform();
			deleteCalculationStatusMyStatusPageFirstRow();
			ExtentReport.logPass("PASS", "test05SaveAndCalculate");
		}catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05SaveAndCalculate", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}

