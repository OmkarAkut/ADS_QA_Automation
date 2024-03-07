package Regression;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.ContractModelsHelper;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation.CostChangeColumnsPopulateAfterCalculateAds1230;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test ADS-5925 */
public class UpdateUCQCParmsRvuCalcScenario extends UcqcHelper {
	static ModelLibraryMap modelMap;
	private static CostingMap costingMap;
	static ContractingMap contractingMap;
	static String costingFolder="Marina Health";
	static String costModel="Marina";
	static String costModelScenario="*DM ADS-696 CMS";
	static String costModelScenarioUpdate="*DM ADS-696 CMS Update";
	static String[] filterCostModel= {"Name","Is","Equal To",costModel};
	static String[] filterCostModelCalcScenario= {"Name","Is","Equal To",costModelScenario};
	static String[] department= {"2016 CHILDREN'S DIABETES UNIT12345678901234567890123456789646596846516544351686565454",
			"2110 ICU","2111 CCU","2115 ICU EAST","2130 PED ICU","2140 NICU","2159 ADULT PSYCHIATRY","2160 LONG BEACH BURN UNIT",
			"2165 CHRONIC WOUND CARE CLINIC","3311 LAB CLINICAL LABORATORY","3520 MAMMOGRAPHY UNIT"};
	static List<String> deptList=Arrays.asList(department);
	@BeforeClass
	  public static void setupScript() throws Throwable {
		  ExtentReport.reportCreate("CreateUCQCLogMakeUCQCProcessAvailableCalculationPage","webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation", "CreateUCQCLogMakeUCQCProcessAvailableCalculationPage");
		   
		  
	    try {
			costingMap = BuildMap.getInstance(driver, CostingMap.class);
			contractingMap=BuildMap.getInstance(driver, ContractingMap.class);
			modelMap=BuildMap.getInstance(driver, ModelLibraryMap.class);
			System.out.println("Test Class: " + CostChangeColumnsPopulateAfterCalculateAds1230.class.getSimpleName());
			Login.loginUser("AutomationTesterAdmin"); 
//			Login.loginUser("AutomationTesterAdmin");
			goToPage("Costing Models");
			waitForAjaxExtJs();
			doClickTreeData("Costing");
			waitForMainPageTitle("Marina Health");
			doClickTreeData(costingFolder);
			doClick("//div[text()='Marina Health']");
			waitForDisplayedSpinnerToEnd();
			 ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL","setupScript", driver,e);
	    	fail(e.getMessage());
		} 
	}
	//ADS-5925, all steps
	@Test
	public void test01OpenCostModelCalculationScenario_5925() throws Throwable {
		try {
			doClick(modelMap.getModelLibraryButtonFilter());
			doFilterCreate(filterCostModel);
			tableDoubleClickCellFirstColumn(costModel);
			doClickTreeData("CM Test");
			waitForMainPageTitle("Cost Scnenarios");
			doClickTreeData("Cost Scnenarios");
			waitForMainPageTitle("Cost Model Calculation Scnenarios");
			doClickTreeItemWithCheckbox("Cost Model Calculation Scenarios");
			ExtentReport.logPass("PASS", "test01OpenCostModelCalculationScenario");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenCostModelCalculationScenario", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02OpenCostModelCalculationScenario_5925() throws Throwable {
		try {
			doClick(costingMap.getCostModelCalcFilterButton());
			doFilterCreate(filterCostModelCalcScenario);
			tableDoubleClickCellFirstColumn(costModelScenario);
			ExtentReport.logPass("PASS", "test01OpenCostModelCalculationScenario");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenCostModelCalculationScenario", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03UpdateCostModelScenarioName_5925() throws Throwable {
		try {
			ContractModelsHelper.keyInValues(CostingMap.getCostScenarioName(),costModelScenarioUpdate);
			doClick(CostingMap.getDeptGroupsSelect());
			assertListOfStringsContainsExpectedStrings(deptList, ContractModelsHelper.getSelectedColumnList());
			
			doClick(CostingMap.getEncounterSave());
			doClick(CostingMap.getEncounterCalculateBtn());
			waitForElementToBeVisible(costingMap.getCostModelScenariosinEvaluationOrderSave());
			doClick(costingMap.getCostModelScenariosinEvaluationOrderSave());
			ExtentReport.logPass("PASS", "test03UpdateCostModelScenarioName");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test03UpdateCostModelScenarioName", driver, e);
			fail(e.getMessage());
		}
	}
	 @AfterClass
		public static void endtest() throws Exception {

			ExtentReport.report.flush();

		}
}
