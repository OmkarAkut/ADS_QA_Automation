package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
	static String costModelScenarioUCQC="*DM ADS-696 CMS Update_UCQC";
	static String[] filterCostModel= {"Name","Is","Equal To",costModel};
	static String[] filterCostModelCalcScenario= {"Name","Is","Equal To",costModelScenario};
	static String[] filterCostModelCalcScenarioUpdate= {"Name","Is","Equal To",costModelScenarioUpdate};
	static String[] filterCostModelCalcUpdatedScenario= {"Name","Is","Equal To",costModelScenarioUCQC};
	static String[] department= {"2016 CHILDREN'S DIABETES UNIT12345678901234567890123456789646596846516544351686565454",
			"2110 ICU","2111 CCU","2115 ICU EAST","2130 PED ICU","2140 NICU","2159 ADULT PSYCHIATRY","2160 LONG BEACH BURN UNIT",
			"2165 CHRONIC WOUND CARE CLINIC","3311 LAB CLINICAL LABORATORY","3520 MAMMOGRAPHY UNIT"};
	static List<String> deptList=Arrays.asList(department);
	 static final String[] requiredFields3520 = {
			    "Marina",
			    costModelScenarioUpdate,
			    "150 Marina Medical Center",
			    "3520", 
			    "Apr 2004 to Mar 2005"
			  };
	 static final String[] requiredFields2140 = {
			    "Marina",
			    costModelScenarioUpdate,
			    "150 Marina Medical Center",
			    "2140", 
			    "May 2004 to May 2004"
			  };
	 static final String[] requiredFields2110 = {
			    "Marina",
			    costModelScenarioUpdate,
			    "200 Southgate",
			    "2110", 
			    "May 2004 to May 2004"
			  };
	 public static String entity="150 Marina Medical Center'";
	
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
	public  void gotoCostingModel() throws Exception {
		goToPage("Costing Models");
		waitForAjaxExtJs();
		doClickTreeData("Costing");
		waitForMainPageTitle("Marina Health");
		doClickTreeData(costingFolder);
//		doClick("//div[text()='Marina Health']");
		//Shilpa Xpath update for 11.2 on 21.5.2024
		doClick("//span[text()='Marina Health']");
		waitForDisplayedSpinnerToEnd();
		doClick(modelMap.getModelLibraryButtonFilter());
		doFilterCreate(filterCostModel);
		tableDoubleClickCellFirstColumn(costModel);
		doClickTreeData("Assign Unit Costs");
		driverDelay();
		waitForMainPageTitle("Cost Model Calculation Scenarios");
		doClickTreeItem("Cost Model Calculation Scenarios");
	}
	public void gotoUCQC(String[] parameters,String chargeCode,String ScenarioName) throws Exception {
		goToPage("Unit Cost Quick Calculation");
		waitForAjaxExtJs();
		ucqcDisplayChargeCodeGrid(parameters);
		ucqcUpdateGridCellValue(chargeCode,"Quick Salaries and Wages RVU","15",printout); 
		costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs().click();
		ucqcWaitForSpinnerToEnd();
		costingMap.getUnitCostQuickCalculationButtonCalculate().click();;
		ucqcWaitForSpinnerToEnd();
		doClosePageOnLowerBar("Unit Cost Quick Calculation");
		driverDelay();
		goToPage("Calculation Status");
		waitForAjaxExtJs();
		waitForDisplayedSpinnerToEnd();
		String[] filterScenario = { "Scenario Name", "Is", "Equal To", ScenarioName };
		doClick(contractingMap.getCalculationStatusButtonFilter());
		doFilterCreate(filterScenario);
		waitForFirstRowCalculationBarToReach100Percent();
		assertElementIsDisplayedWithXpath("(//div[text()='"+costModelScenarioUCQC+"']//following::td[5]/div[contains(text(),'Completed')])[1]");
		doClosePageOnLowerBar("Calculation Status");
	}
	public void filterCreate(String[] filterScenario,String scenario) throws Exception {
		doClick(costingMap.getCostModelCalcFilterButton());
		driverDelay();
		doFilterCreate(filterScenario);
		waitForAjaxExtJs();
		tableDoubleClickCellFirstColumn(scenario);
	}
	//ADS-5925, all steps
	@Test
	public void test01OpenCostModelCalculationScenario_5925() throws Throwable {
		try {
			gotoCostingModel();
			ExtentReport.logPass("PASS", "test01OpenCostModelCalculationScenario");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenCostModelCalculationScenario", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02OpenCostModelCalculationScenario_5925() throws Throwable {
		try {
			filterCreate(filterCostModelCalcScenario,costModelScenario);
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
			driverDelay();
			doClick(CostingMap.getCostScenarioCalculateButton());
			//Shilpa updated 10.18.2024
			String[] filterScenario = { "Scenario Name", "Is", "Equal To", costModelScenarioUpdate };
			doClick(contractingMap.getCalculationStatusButtonFilter());
			doFilterCreate(filterScenario);
			waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Calculation Status");
			doClick(CostingMap.getCostScenarioCancelCloseButton());
			doClosePageOnLowerBar("Marina");
			doClosePageOnLowerBar("Costing Models");
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "test03UpdateCostModelScenarioName");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test03UpdateCostModelScenarioName", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test04VerifyUpdatedCostModelScenarioNameInUCQC_5925() throws Throwable {
		try {
			gotoUCQC(requiredFields3520, "5800628",costModelScenarioUCQC);
			
			ExtentReport.logPass("PASS", "test04VerifyUpdatedCostModelScenarioNameInUCQC_5925");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test04VerifyUpdatedCostModelScenarioNameInUCQC_5925", driver, e);
			fail(e.getMessage());
		}
		}
	
	@Test
	public void test05VerifyUpdatedParametersInCostModelScenarioNameInUCQC_5925() throws Throwable {
		try {
			gotoCostingModel();
			filterCreate(filterCostModelCalcUpdatedScenario, costModelScenarioUCQC);
			selectOptions(CostingMap.getGldataDesc(), "* MHFY05 Reclass TB");
			CostingMap.getGldataDesc().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='* MHFY05 Reclass TB']");
			selectOptions(CostingMap.getVolScenario(), "2TBMHFY05VOL");
			CostingMap.getVolScenario().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='2TBMHFY05VOL']");
			selectOptions(CostingMap.getVariablilityMaster(), "ASESC2060 CC Var Master");
			CostingMap.getVariablilityMaster().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='ASESC2060 CC Var Master']");
			selectOptions(CostingMap.getOverheadDrpDwn(), "TB OVHD Calc Scen Exp Vol for Rpt");
			CostingMap.getOverheadDrpDwn().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='TB OVHD Calc Scen Exp Vol for Rpt']");
			assertElementIsDisplayedWithXpath("//div[text()='Entities']//following::li[text()='150 Marina Medical Center']");
			assertElementIsDisplayedWithXpath("//div[text()='Departments / Groups']//following::li[text()='3520 MAMMOGRAPHY UNIT']");
			selectOptions(CostingMap.getCostModelPriceListDrpdown(), "150FY05  Marina Hosp Price List FY05");
			CostingMap.getCostModelPriceListDrpdown().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected x-boundlist')][text()='150FY05  Marina Hosp Price List FY05']");
			assertElementIsDisplayedWithXpath("(//span[text()='Start Month:']//following::div[text()='Apr 2004'])[2]");
			assertElementIsDisplayedWithXpath("(//span[text()='End Month:']//following::div[text()='Mar 2005'])[2]");
			selectOptions(driver.findElement(By.xpath("//input[@name='hostLocation']")), "<SFTP_SERVER>/PATH/TO/CALC_LOGS_SHARED_DIRECTORY2/");
			driver.findElement(By.xpath("//input[@name='hostLocation']")).click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected x-boundlist')][text()='<SFTP_SERVER>/PATH/TO/CALC_LOGS_SHARED_DIRECTORY2/']");
			doClick(costingMap.getRvuMaintenanceFilterButtonCancelAndClose());
			ExtentReport.logPass("PASS", "test04VerifyUpdatedCostModelScenarioNameInUCQC_5925");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test04VerifyUpdatedCostModelScenarioNameInUCQC_5925", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06UpdateParametersForCostModelScenario_5925() throws Throwable {
		try {
//			gotoCostingModel();
			doClick(costingMap.getCostModelCalcClearFilterButton());
			waitForDisplayedSpinnerToEnd();
			filterCreate(filterCostModelCalcScenarioUpdate, costModelScenarioUpdate);
			selectOptions(CostingMap.getGldataDesc(), "MH FY05 Reclass");
			selectOptions(CostingMap.getVolScenario(), "MHFY05");
			selectOptions(CostingMap.getVariablilityMaster(), "MHS");
			selectOptions(CostingMap.getOverheadDrpDwn(), "MH FY05 Overhead");
			ContractModelsHelper.scrollToView(CostingMap.getEntitiesSelect());
			doClick(CostingMap.getEntitiesSelect());
			doClick(CostingMap.getUnitCostQuickCalculationDepartmentButtonFilter());
			driverDelay();
			selectOptions(driver.findElement(By.xpath("//input[@name='field']")), "Entity Code");
			ContractModelsHelper.applyMultipleFilters("Entity Code","200");
			selectOptions(driver.findElement(By.xpath("//div[text()='200  Southgate']//following::input[@name='priceList']")), "200FY05  Southgate Price List FY05");
			selectOptions(driver.findElement(By.xpath("//input[@name='savedStartMonth']")), "May 2004");
			doClick("//input[@name='savedEndMonth']");
			doClick("(//li[text()='May 2004'])[2]");
//			selectOptions(driver.findElement(By.xpath("//input[@name='savedEndMonth']")), "May 2004");
			doClick(CostingMap.getCostScenarioSaveBtn());
			driverDelay();
			doClick(CostingMap.getCostScenarioCalculateButton());
			String[] filterScenario = { "Scenario Name", "Is", "Equal To", costModelScenarioUpdate };
			doClick(contractingMap.getCalculationStatusButtonFilter());
			doFilterCreate(filterScenario);
			waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Calculation Status");
			doClick(CostingMap.getCostScenarioCancelCloseButton());
			doClosePageOnLowerBar("Marina");
			doClosePageOnLowerBar("Costing Models");
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "test06UpdateParametersForCostModelScenario_5925");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test06UpdateParametersForCostModelScenario_5925", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07VerifyUpdatedCostModelScenarioNameInUCQC_5925() throws Throwable {
		try {
			gotoUCQC(requiredFields2140, "1100569",costModelScenarioUCQC);
			
			ExtentReport.logPass("PASS", "test04VerifyUpdatedCostModelScenarioNameInUCQC_5925");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test04VerifyUpdatedCostModelScenarioNameInUCQC_5925", driver, e);
			fail(e.getMessage());
		}
		}
	@Test
	public void test08VerifyUpdatedParametersInCostModelScenarioNameInUCQC_5925() throws Throwable {
		try {
			gotoCostingModel();
			filterCreate(filterCostModelCalcUpdatedScenario, costModelScenarioUCQC);
			selectOptions(CostingMap.getGldataDesc(), "MH FY05 Reclass");
			CostingMap.getGldataDesc().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='MH FY05 Reclass']");
			selectOptions(CostingMap.getVolScenario(), "MHFY05");
			CostingMap.getVolScenario().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='MHFY05']");
			selectOptions(CostingMap.getVariablilityMaster(), "MHS");
			CostingMap.getVariablilityMaster().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='MHS']");
			selectOptions(CostingMap.getOverheadDrpDwn(), "MH FY05 Overhead");
			CostingMap.getOverheadDrpDwn().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='MH FY05 Overhead']");
			assertElementIsDisplayedWithXpath("//div[text()='Entities']//following::li[text()='150 Marina Medical Center']");
			assertElementIsDisplayedWithXpath("//div[text()='Departments / Groups']//following::li[text()='3520 MAMMOGRAPHY UNIT']");
			assertElementIsDisplayedWithXpath("//div[text()='Departments / Groups']//following::li[text()='2140 NICU']");
			selectOptions(CostingMap.getCostModelPriceListDrpdown(), "150FY05  Marina Hosp Price List FY05");
			CostingMap.getCostModelPriceListDrpdown().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='150FY05  Marina Hosp Price List FY05']");
			assertElementIsDisplayedWithXpath("(//span[text()='Start Month:']//following::div[text()='May 2004'])[2]");
			assertElementIsDisplayedWithXpath("(//span[text()='End Month:']//following::div[text()='May 2004'])[2]");
			selectOptions(driver.findElement(By.xpath("//input[@name='hostLocation']")), "<SFTP_SERVER>/PATH/TO/CALC_LOGS_SHARED_DIRECTORY2/");
			doClick("//input[@name='hostLocation']");
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected x-boundlist')][text()='<SFTP_SERVER>/PATH/TO/CALC_LOGS_SHARED_DIRECTORY2/']");
			doClick(costingMap.getRvuMaintenanceFilterButtonCancelAndClose());
			doClosePageOnLowerBar(costModel);
			doClosePageOnLowerBar("Costing Models");
			ExtentReport.logPass("PASS", "test04VerifyUpdatedCostModelScenarioNameInUCQC_5925");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test04VerifyUpdatedCostModelScenarioNameInUCQC_5925", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09UpdateUCQCForEntity200Southgate_5925() throws Throwable {
		try {
			driver.navigate().refresh();
			waitForDisplayedSpinnerToEnd();
			gotoUCQC(requiredFields2110, "1100023",costModelScenarioUCQC);
			ExtentReport.logPass("PASS", "test09UpdateUCQCForEntity200Southgate_5925");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test09UpdateUCQCForEntity200Southgate_5925", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10VerifyUpdatedCostScenarioForUpdatedEntity_5925() throws Throwable {
		try {
			gotoCostingModel();
			filterCreate(filterCostModelCalcUpdatedScenario, costModelScenarioUCQC);
			selectOptions(CostingMap.getGldataDesc(), "MH FY05 Reclass");
			CostingMap.getGldataDesc().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='MH FY05 Reclass']");
			selectOptions(CostingMap.getVolScenario(), "MHFY05");
			CostingMap.getVolScenario().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='MHFY05']");
			selectOptions(CostingMap.getVariablilityMaster(), "MHS");
			CostingMap.getVariablilityMaster().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='MHS']");
			selectOptions(CostingMap.getOverheadDrpDwn(), "MH FY05 Overhead");
			CostingMap.getOverheadDrpDwn().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='MH FY05 Overhead']");
			assertElementIsDisplayedWithXpath("//div[text()='Entities']//following::li[text()='150 Marina Medical Center']");
			assertElementIsDisplayedWithXpath("//div[text()='Departments / Groups']//following::li[text()='3520 MAMMOGRAPHY UNIT']");
			assertElementIsDisplayedWithXpath("//div[text()='Departments / Groups']//following::li[text()='2140 NICU']");
			assertElementIsDisplayedWithXpath("//div[text()='Departments / Groups']//following::li[text()='2110 ICU']");
			selectOptions(CostingMap.getCostModelPriceListDrpdown(), "150FY05  Marina Hosp Price List FY05");
			CostingMap.getCostModelPriceListDrpdown().click();
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='150FY05  Marina Hosp Price List FY05']");
			selectOptions(driver.findElement(By.xpath("//div[text()='200  Southgate']//following::input[@name='priceList']")), "200FY05  Southgate Price List FY05");
			doClick("//div[text()='200  Southgate']//following::input[@name='priceList']");
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected')][text()='200FY05  Southgate Price List FY05']");

			assertElementIsDisplayedWithXpath("(//span[text()='Start Month:']//following::div[text()='May 2004'])[2]");
			assertElementIsDisplayedWithXpath("(//span[text()='End Month:']//following::div[text()='May 2004'])[2]");
			selectOptions(driver.findElement(By.xpath("//input[@name='hostLocation']")), "<SFTP_SERVER>/PATH/TO/CALC_LOGS_SHARED_DIRECTORY2/");
			doClick("//input[@name='hostLocation']");
			assertElementIsDisplayedWithXpath("//li[contains(@class,'x-boundlist-selected x-boundlist')][text()='<SFTP_SERVER>/PATH/TO/CALC_LOGS_SHARED_DIRECTORY2/']");
			doClick(costingMap.getRvuMaintenanceFilterButtonCancelAndClose());
			ExtentReport.logPass("PASS", "test10VerifyUpdatedCostScenarioForUpdatedEntity_5925");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test10VerifyUpdatedCostScenarioForUpdatedEntity_5925", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClick(CostingMap.getCostScenarioDeleteButton());
			waitForElementToBeVisible(costingMap.getWarningDeleteButton());
			doClick(costingMap.getWarningDeleteButton());
			doClick(CostingMap.getCostScenarioClearFilterButton());
			doClick(costingMap.getCostModelCalcFilterButton());
			driverDelay();
			doFilterCreate(filterCostModelCalcScenarioUpdate);
			waitForAjaxExtJs();
			doClick(CostingMap.getCostScenarioDeleteButton());
			waitForElementToBeVisible(costingMap.getWarningDeleteButton());
			doClick(costingMap.getWarningDeleteButton());
			doClosePageOnLowerBar(costModel);
			doClosePageOnLowerBar("Costing Models");
		}
	}
	public void selectOptions(WebElement dropdown,String elementName) throws Exception {
		doClick(dropdown);
		scrollToView("//li[text()='"+elementName+"']");
		doClick("//li[text()='"+elementName+"']");
	}
	 public void assertThatDropdownSelectedValue(WebElement elementMenuList, String expectedValue) throws Throwable {
		    waitForSpinnerToEnd();
		    waitForAjaxExtJs();
		    WebElement classificationList = elementMenuList;
		    List<WebElement> classificationListing = classificationList.findElements(By.tagName("li"));
		    for (WebElement item : classificationListing) {
		      String clss = item.getAttribute("class");
		   //   if (clss.contains("selected")) {
		      	scrollToView("//li[text()='"+expectedValue+"']");
		    	 if (clss.contains("x-boundlist-selected")) {// venkat update required selected from 07-09-2022
		    	
		    		 MatcherAssert.assertThat(item.getText(), equalTo(expectedValue));
		        System.out.println("Selected option = " + item.getText());
		        break;
		      }
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
	 @AfterClass
		public static void endtest() throws Exception {

			ExtentReport.report.flush();

		}
}
