package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
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
	 static final String[] requiredFields = {
			    "Marina",
			    costModelScenarioUpdate,
			    "150 Marina Medical Center",
			   // "Q302  QA Dept for ADS-302",
			    "3520", //Venkat updated Department filed 21.09.2022
			    "Apr 2004 to Apr 2004"
			  };
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
//			doClick("//div[text()='Marina Health']");
			//Shilpa Xpath update for 11.2 on 21.5.2024
			doClick("//span[text()='Marina Health']");
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
//			doClickTreeData("CM Test");
//			waitForMainPageTitle("Cost Scnenarios");
//			doClickTreeData("Cost Scnenarios");
			doClickTreeData("Assign Unit Costs");
			driverDelay();
			waitForMainPageTitle("Cost Model Calculation Scenarios");
			doClickTreeItem("Cost Model Calculation Scenarios");
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
			doClick("//span[text()='Apply']");
			doClick(CostingMap.getCostScenarioSaveButton());
			doClick(CostingMap.getCostScenarioCalculateButton());
			waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Calculation Status");
			doClick(CostingMap.getCostScenarioSaveCloseButton());
			doClosePageOnLowerBar("Marina");
			doClosePageOnLowerBar("Costing Models");
//			waitForElementToBeVisible(costingMap.getCostModelScenariosinEvaluationOrderSave());
//			doClick(costingMap.getCostModelScenariosinEvaluationOrderSave());
			ExtentReport.logPass("PASS", "test03UpdateCostModelScenarioName");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test03UpdateCostModelScenarioName", driver, e);
			fail(e.getMessage());
		}
	}
	public static void filterByCalculationStartTimeInCalculationStatusPage() throws Throwable {
		Format f = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = f. format(new Date());
		CostingMap costingMap = BuildMap.getInstance(driver, CostingMap.class);
		doClick(driver.findElement(By.xpath("//div[contains(@id,'statustoolbar')]//span[text()='Filter']/parent::button")));
		doDropdownSelectUsingOptionText(CostingMap.getcalculationFilterPopUpFilterDrop(),costingMap.getCalculationFilterDropdownMenuList(),"Calculation Start Time");

		doClick(driver.findElement(By.xpath("//input[@name='valuedate']")));
		driver.findElement(By.xpath("//input[@name='valuedate']")).sendKeys(strDate);
		doClick(driver.findElement(By.xpath("//div[contains(@id,'filter')]//span[text()='Add']/parent::button")));
		doClick(driver.findElement(By.xpath("//span[text()='Apply Filter']/parent::button")));
	}
	public static void waitForFirstRowCalculationBarToReach100Percent() throws Exception {
		boolean calculate = true;
		String percent;
		byte counter = 0;
		try {
			filterByCalculationStartTimeInCalculationStatusPage();
		} catch (Throwable e1) {

		}
		while (calculate) {
			try {
				//    	  Omkar 14/04/2023 : xpath changes for 11.2
				//        driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
				driver.findElement(By.xpath("//span[text()='Refresh']")).click();
				waitForSpinnerToEnd();
				percent = driver.findElement(By.xpath("//*[contains(@class,'x-progress-text-back')]")).getText();
				System.out.println("Percent complete: " + percent);
				assertTrue(percent.contains("100%"));
				break;
			} catch (Throwable e) {
				System.out.println("percent less than 100");
				Thread.sleep(1000);
				if (counter == 120) {
					fail("Calculation did not finish in allotted time");
					break;
				}
				counter++;
			}
		}
		Thread.sleep(500);
	}
	@Test
	public void test04VerifyUpdatedCostModelScenarioNameInUCQC_5925() throws Throwable {
		try {
			goToPage("Unit Cost Quick Calculation");
			waitForAjaxExtJs();
			ucqcDisplayChargeCodeGrid(requiredFields);
			ucqcUpdateGridCellValue("5800628","Quick Salaries and Wages RVU","15",printout); //venkat update test data 07-09-2022
			costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs().click();
			ucqcWaitForSpinnerToEnd();
			costingMap.getUnitCostQuickCalculationButtonCalculate();
			ucqcWaitForSpinnerToEnd();
			doClosePageOnLowerBar("Unit Cost Quick Calculation");
			driverDelay();
			goToPage("Calculation Status");
			waitForAjaxExtJs();
			waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Calculation Status");
			ExtentReport.logPass("PASS", "test04VerifyUpdatedCostModelScenarioNameInUCQC_5925");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test04VerifyUpdatedCostModelScenarioNameInUCQC_5925", driver, e);
			fail(e.getMessage());
		}
		}
	
	@Test
	public void test05VerifyUpdatedCostModelScenarioNameInUCQC_5925() throws Throwable {
		try {
			goToPage("Costing Models");
			waitForAjaxExtJs();
			
			ExtentReport.logPass("PASS", "test04VerifyUpdatedCostModelScenarioNameInUCQC_5925");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test04VerifyUpdatedCostModelScenarioNameInUCQC_5925", driver, e);
			fail(e.getMessage());
		}
	}
	 @AfterClass
		public static void endtest() throws Exception {

			ExtentReport.report.flush();

		}
}
