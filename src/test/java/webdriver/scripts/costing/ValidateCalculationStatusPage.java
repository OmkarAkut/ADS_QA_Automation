package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
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
	static String costModel = "ADS-1232 / ADS-958 CMS 1 " + currentDateTime;
	static CostingMap costing;
	static ContractingMap modelMap;
	static String glDataDescription="* MHFY05 Reclass TB";
	static String actVol="TBMHFY05VOL";
	static String varMaster="ASESC2060 CC Var Master";
	static String OHMaster="TB OVHD Calc Scen Exp Vol for Rpt";
	static String priceList="150FY05 Marina Hosp Price List FY05";
	static String calcMonths="Apr 2004 to Mar 2005";
	AdsHelper adsHelper = new AdsHelper();

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
	
	@Test
	public void test01GotoCostModelCalculationScenario() throws Throwable {
		try {
			doSearchForModel("QA Cost Model");
			tableDoubleClickCellFirstColumn("QA Cost Model");
			driverDelay(500);
//			doClickTreeItem("Cost Model Task List");
			waitForMainPageTitle("CM Test");
			doClickTreeItem("CM Test");
			waitForMainPageTitle("Cost Scnenarios");
			doClickTreeItem("Cost Scnenarios");
			waitForMainPageTitle("Cost Model Calculation Scenarios");
			doClickTreeItemWithCheckbox("Cost Model Calculation Scenarios");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test01GotoCostModelCalculationScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01GotoCostModelCalculationScenario", driver, e);
			fail(e.getMessage());
		}

	}
	
	@Test
	public void test02SelectSourceData() throws Throwable {
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
//			doDropdownSelectUsingOptionText(CostingMap.getGldataDesc(), glDataDescription);
//			doClick(CostingMap.getVolScenario());
//			doDropdownSelectUsingOptionTextWithelement(driver.findElement(By.xpath("(//div[contains(@class,'floating')]/div[contains(@id,'listEl')]/ul)[2]")), actVol);
//			doClick(CostingMap.getVariablilityMaster());
//			doDropdownSelectUsingOptionTextWithelement(driver.findElement(By.xpath("(//div[contains(@class,'floating')]/div[contains(@id,'listEl')]/ul)[3]")), varMaster);
//			doClick(CostingMap.getOverheadDrpDwn());
//			doDropdownSelectUsingOptionTextWithelement(driver.findElement(By.xpath("(//div[contains(@class,'floating')]/div[contains(@id,'listEl')]/ul)[4]")), OHMaster);
			

			ExtentReport.logPass("PASS", "test02CreateCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02CreateCostModel", driver, e);
			fail(e.getMessage());
		}

		
	}
	@Test
	public void test03EntityAndDepartmentGroupSelection() throws Throwable {
		try {
			ContractModelsHelper.scrollToView(CostingMap.getEntitiesSelect());
			doClick(CostingMap.getEntitiesSelect());
			doClick(CostingMap.getUnitCostQuickCalculationDepartmentButtonFilter());
			ContractModelsHelper.applyMultipleFilters("Entity Code","150");
			ContractModelsHelper.groupSelectApplyFilters("Department Code","2016");
			ContractModelsHelper.groupSelectApplyFilters("Department Code","2110");
			ContractModelsHelper.groupSelectApplyFilters("Department Code","2111");
			ContractModelsHelper.groupSelectApplyFilters("Department Code","2115");
			ContractModelsHelper.groupSelectApplyFilters("Department Code","2140");
			ContractModelsHelper.groupSelectApplyFilters("Department Code","2159");
			ContractModelsHelper.groupSelectApplyFilters("Department Code","2160");
			ContractModelsHelper.groupSelectApplyFilters("Department Code","2165");
			ContractModelsHelper.groupSelectApplyFilters("Department Code","3110");
			ContractModelsHelper.groupSelectApplyFilters("Department Code","3120");
			ExtentReport.logPass("PASS", "test02CreateCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02CreateCostModel", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04SelectPriceListStartEndMonth() throws Throwable {
		try {
			driverDelay();
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
					"Mar 2005"
					);
			doClick(CostingMap.getCostModelSharedLogCheckbox());
			ExtentReport.logPass("PASS", "test04SelectPriceListStartEndMonth");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04SelectPriceListStartEndMonth", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05SaveAndCalculate() throws Throwable {
		try {
			doClick(ContractingMap.getSaveBenefitPlan());
			driverDelay(100);
			doClick(ContractingMap.getCalculateButton());
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			//implement later steps from step15 after issueresolved
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

